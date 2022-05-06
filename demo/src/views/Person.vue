<template>
    <div>
    <el-card style="width: 500px;">
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
            <el-form-item>
                <el-button type="primary" @click="$router.push('/')">返回主页</el-button>
                <el-button type="primary" @click="save">确 定</el-button>
            </el-form-item>
        </el-form>
    </el-card>
    </div>
</template>

<script>
    export default {
        data(){
         return{
             form:{},
             user:localStorage.getItem("user")?JSON.parse(localStorage.getItem("user")):{}
         }
        },
        created() {
            console.log(this.user.name)
            axios.get("http://localhost:8182/user/username/"+this.user.name).then(res=>{
                    this.form=res.data
            })
        },
        methods:{
            save(){
                axios.post("http://localhost:8182/user/save",this.form).then(resp=>{
                    if(resp.data){
                        this.$message.success("保存成功")
                    }else{
                        this.$message.error("保存失败")
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>