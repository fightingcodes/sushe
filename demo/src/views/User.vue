<template>
    <div>

        <div style="padding: 10px 0">
            <el-input style="width: 250px"  placeholder="按名字搜索" suffix-icon="el-icon-search" v-model="name"></el-input>
            <el-input style="width: 250px"  placeholder="按地址搜索" suffix-icon="el-icon-search" v-model="adr"></el-input>
            <el-input style="width: 250px"  placeholder="按电话搜索" suffix-icon="el-icon-search" v-model="tel"></el-input>
            <el-button type="primary" @click="load">搜索</el-button>
            <el-button type="warning" @click="reset">重置</el-button>
        </div>

        <div>
            <el-button type="margin" @click="handeradd" style="margin-right: 5px"><i class="el-icon-circle-plus-outline">新增</i></el-button>
            <el-popconfirm
                    confirm-button-text='好的'
                    cancel-button-text='还是不了'
                    icon="el-icon-info"
                    icon-color="red"
                    title="你确定要删除他or她吗？"
                    @confirm="delBatch"
            >

                <el-button type="danger" slot="reference"><i class="el-icon-remove-outline">批量删除</i></el-button>
            </el-popconfirm>
            <el-upload action="http://localhost:8182/user/import" :show-file-list="false" accept="xlsx" :on-success="handleImportSuccess"  style="display: inline-block;margin-right:5px;margin-left: 5px ">
            <el-button type="primary"  ><i class="el-icon-download">导入</i></el-button>
            </el-upload>
            <el-button type="primary"><i class="el-icon-upload2" @click="exp">导出</i></el-button>
        </div>
        <el-table :data="tableData" border stripe :header-cell-class-name="headBg" @selection-change="handlesleectChange">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="id" label="id" width="80">
            </el-table-column>
            <el-table-column prop="name" label="姓名" width="120">
            </el-table-column>
            <el-table-column prop="age" label="年龄" width="100">
            </el-table-column>
            <el-table-column prop="tel" label="电话" width="120">
            </el-table-column>
            <el-table-column prop="qq" label="qq" width="120">
            </el-table-column>
            <el-table-column prop="adr" label="家庭地址">
            </el-table-column>
            <el-table-column label="您的操作">
                <template slot-scope="scope">
                    <el-button @click="handleEdit(scope.row)" type="success" round><i class="el-icon-edit">编辑</i></el-button>
                    <el-popconfirm
                            confirm-button-text='好的'
                            cancel-button-text='还是不了'
                            icon="el-icon-info"
                            icon-color="red"
                            title="你确定要删除他or她吗？"
                            @confirm="del(scope.row.id)"
                    >
                        <el-button  type="danger" slot="reference" round><i class="el-icon-bell">删除</i></el-button>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>



        <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="30%">
            <el-form label-width="60px">
                <el-form-item label="用户名" >
                    <el-input v-model="form.name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="年龄" >
                    <el-input v-model="form.age" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="qq" >
                    <el-input v-model="form.qq" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="tel" >
                    <el-input v-model="form.tel" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="adr" >
                    <el-input v-model="form.adr" autocomplete="off"></el-input>
                </el-form-item>

            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="save">确 定</el-button>
            </div>
        </el-dialog>
        <div style="padding: 10px 0">
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="pageNum"
                    :page-sizes="[2, 5, 8, 12]"
                    :page-size="pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total">
            </el-pagination>
        </div>
    </div>
</template>

<script>


   export default {
       name:"User",
       data() {
           return {
               pageNum:1,
               pageSize:5,
               name:"",
               adr:"",
               tel:"",
               tableData: [],
               total: 0,
               dialogFormVisible:false,
               form:{},
               isCollapse:false,
               headBg:'headBg',
               mult:[]
           }
       },
       created() {
           this.load()
       },

       methods:{
           reset(){
               this.name=""
               this.tel=""
               this.adr=""
               this.load()
           },
           load(){
               //
               // let _this = this
               //
               // axios.get('http://localhost:8182/user/page?pageNum='+this.pageNum+'&pageSize='+this.pageSize+"&name="+this.name).then(function (resp) {
               //  _this.tableData=resp.data.records
               //  _this.total=resp.data.total
               // })
               axios.get('http://localhost:8182/user/page',{
                   params:{
                       pageNum:this.pageNum,
                       pageSize:this.pageSize,
                       name:this.name,
                       adr:this.adr,
                       tel:this.tel
                   }
               }).then(resp=>{
                   this.tableData=resp.data.records
                   this.total=resp.data.total
               })

               // fetch("http://localhost:8182/user/page?pageNum="+this.pageNum+"&pageSize="+this.pageSize).then(res=>res.json()).then(res=>{
               //  console.log(res)
               //  this.tableData=res.records
               //  this.total=res.total
               // })
           },
           handeradd(){
               this.dialogFormVisible=true
               this.form={}
               this.load()
           },
           handlesleectChange(val){
               this.mult=val
           },delBatch(){
               let ids=this.mult.map(v=>v.id)//把一个对象数组改为一个纯数组
               axios.post("http://localhost:8182/user/deleteBatch/",ids).then(resp=>{
                   if(resp){
                       this.$message.success("删除成功")
                       this.dialogFormVisible=false
                       this.load()
                   }else{
                       this.$message.error("删除失败")
                   }
               })
           },
           del(id){
               axios.delete("http://localhost:8182/user/deleteById/"+id).then(resp=>{
                   if(resp){
                       this.$message.success("删除成功")
                       this.dialogFormVisible=false
                       this.load()
                   }else{
                       this.$message.error("删除失败")
                   }
               })
           },
           handleEdit(row){
               this.form=row
               this.dialogFormVisible=true

           },
           save(){
               axios.post("http://localhost:8182/user/save",this.form).then(resp=>{
                   if(resp){
                       this.$message.success("保存成功")
                       this.dialogFormVisible=false
                       this.load()
                   }else{
                       this.$message.error("保存失败")
                   }
               })
           },
           handleSizeChange(pageSize){
               this.pageSize=pageSize
               this.load()
           },
           handleCurrentChange(pageNum){
               this.pageNum=pageNum
               this.load()
           },
           exp(){
               window.open("http://localhost:8182/user/export")
           },
           handleImportSuccess(){
               this.$message.success("导入成功")
               this.load()
           }
       }
   };

</script>

<style scoped>
    .headBg{
        background: #eee!important;
    }
</style>