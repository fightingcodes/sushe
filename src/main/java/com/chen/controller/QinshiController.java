package com.chen.controller;


import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.common.Constants;
import com.chen.common.Result;
import com.chen.controller.dto.UserDto;
import com.chen.entity.Qinshi;
import com.chen.service.AutoIncrementService;
import com.chen.service.IQinshiService;
import com.sun.deploy.net.URLEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 陈志浩
 * @since 2022-04-29
 */
@RestController
@RequestMapping("/user")
public class QinshiController {


    @Autowired
    private IQinshiService qinshiService;
    @Autowired
    private AutoIncrementService autoIncrementService;




    @PostMapping("/save")
    private boolean save(@RequestBody Qinshi qinshi){
        return this.qinshiService.saveUser(qinshi);
    }



    @GetMapping("/list")
    public List<Qinshi> list(){
        return this.qinshiService.list();
    }

    @GetMapping("/username/{name}")
    public Qinshi findone(@PathVariable String name){
        QueryWrapper<Qinshi> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        return qinshiService.getOne(queryWrapper);
    }

    @GetMapping("/page")
    public IPage<Qinshi> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String name,
                                @RequestParam(defaultValue = "") String adr,
                                @RequestParam(defaultValue = "") String tel
    ){
        IPage<Qinshi> page=new Page<>(pageNum,pageSize);
        QueryWrapper<Qinshi> queryWrapper=new QueryWrapper<>();
        if(!"".equals(name)){
            queryWrapper.like("name",name);
        }
        if(!"".equals(adr)){
            queryWrapper.like("adr",adr);
        }
        if(!"".equals(tel)){
            queryWrapper.like("tel",tel);
        }
        return qinshiService.page(page,queryWrapper);
    }

    @DeleteMapping("/deleteById/{id}")
    public Boolean deleteById(@PathVariable("id") Integer id){
        boolean b = this.qinshiService.removeById(id);
        autoIncrementService.changeAuto();
        return b;
    }
    @PostMapping("/deleteBatch")
    public Boolean deleteBatch(@RequestBody List<Integer> ids){

        return this.qinshiService.removeBatchByIds(ids);
    }


    @GetMapping("/export")
    public  void export(HttpServletResponse response) throws Exception {
        List<Qinshi> list=qinshiService.list();//查出数据
        ExcelWriter writer= ExcelUtil.getWriter();//在内存中操作写出浏览器
//        writer.addHeaderAlias("name","用户名");
//        writer.addHeaderAlias("password","密码");
//        writer.addHeaderAlias("age","年龄");
//        writer.addHeaderAlias("adr","家庭地址");
//        writer.addHeaderAlias("qq","QQ号");
//        writer.addHeaderAlias("tel","电话");

        //写出
        writer.write(list,true);

        //设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String filename= URLEncoder.encode("用户信息","UTF-8");
        response.setHeader("Content-Disposition","attachment;filename="+filename+".xlsx");

        ServletOutputStream out=response.getOutputStream();
        writer.flush(out,true);
        out.close();
        writer.close();

    }

    /**
     * 导入
     * */
    @PostMapping("/import")
    public Boolean imp(MultipartFile file)throws Exception{
        InputStream inputStream=file.getInputStream();
        ExcelReader excelReader=ExcelUtil.getReader(inputStream);
        List<Qinshi> list=excelReader.readAll(Qinshi.class);
        qinshiService.saveBatch(list);
        return true;
    }
    /**
     * 登录RequestBody把前端传过来的json转化后台java对象
     * */
    @PostMapping("/login")
    public Result login(@RequestBody UserDto userDto){
        String name = userDto.getName();
        String password = userDto.getPassword();
        if(StrUtil.isBlank(name)||StrUtil.isBlank(password)){
            return  Result.error(Constants.CODE_400,"参数错误");
        }else {
            UserDto login = qinshiService.login(userDto);
            return Result.success(login);
        }
    }
    @PostMapping("/register")
    public Result register(@RequestBody UserDto userDto){
        String name = userDto.getName();
        String password = userDto.getPassword();
        if(StrUtil.isBlank(name)||StrUtil.isBlank(password)){
            return  Result.error(Constants.CODE_400,"参数错误");
        }else {
            return Result.success(qinshiService.register(userDto));
        }

    }

}

