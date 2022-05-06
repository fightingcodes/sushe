package com.chen.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chen.common.Constants;
import com.chen.common.Result;
import com.chen.controller.dto.UserDto;
import com.chen.entity.Qinshi;
import com.chen.exception.MyExceptionHandler;
import com.chen.exception.ServiceExciption;
import com.chen.mapper.QinshiMapper;
import com.chen.service.IQinshiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 陈志浩
 * @since 2022-04-29
 */
@Service
public class QinshiServiceImpl extends ServiceImpl<QinshiMapper, Qinshi> implements IQinshiService {
    @Resource
    private QinshiMapper qinshiMapper;//必须注册
    public boolean saveUser(Qinshi qinshi){
//        if(user.getId()==null){//无id新增，否则是更新
//            return userMapper.(user);
//        }else{
//             return userMapper.update(user);
//        }
        return saveOrUpdate(qinshi);
    }

    @Override
    public UserDto login(UserDto userDto) {
        Qinshi one= getUserInfo(userDto);
        if(one!=null){
            BeanUtil.copyProperties(one,userDto,true);//one数据库查询的copy到userDto
            return userDto;
        }else {
            throw new ServiceExciption(Constants.CODE_600,"出错了");
        }
    }
/**注册
 * */
    @Override
    public Result register(UserDto userDto) {
        Qinshi one=getUserInfo(userDto);
        if(one==null){
            Qinshi second=new Qinshi();
            BeanUtil.copyProperties(userDto,second,true);
            save(second);
        }else{
            throw new ServiceExciption(Constants.CODE_600,"用户已存在");
        }
        return null;
    }
    /**
     * 封装的方法
     * */
    public Qinshi getUserInfo(UserDto userDto){
        QueryWrapper<Qinshi> qinshiQueryWrapper=new QueryWrapper<>();
        qinshiQueryWrapper.eq("name",userDto.getName());
        qinshiQueryWrapper.eq("password",userDto.getPassword());
        Qinshi one;
        try {
            one = getOne(qinshiQueryWrapper);
        } catch (Exception e) {
            throw new ServiceExciption(Constants.CODE_500,"系统错误");
        }
       return one;
    }

}
