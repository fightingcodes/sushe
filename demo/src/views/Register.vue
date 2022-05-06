<template>
    <div class="wrapper">
        <div style="margin: 150px auto;background-color: #fff;width: 350px;height: 400px;padding: 20px;border-radius: 10px">
            <div style="margin: 20px 0;text-align: center;font-size: 24px"><b>注册</b></div>
            <el-form :rules="rules" :model="user" ref="userForm" >
                <el-form-item prop="name">
                    <el-input  placeholder="请输入账号"  style="width: 350px;padding:10px 0" prefix-icon="el-icon-user" v-model="user.name" ></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input  placeholder="请输入密码"  style="width: 350px;" prefix-icon="el-icon-lock" show-password v-model="user.password"></el-input>
                </el-form-item>
                <el-form-item prop="comfirmPassword">
                    <el-input  placeholder="确认密码"  style="width: 350px;" prefix-icon="el-icon-lock" show-password v-model="user.comfirmPassword"></el-input>
                </el-form-item>

                <div style="margin-left: 100px;padding-top: 30px">
                    <el-button type="primary"  autocomplete="off" @click="register">注册</el-button>
                    <el-button type="warning"  autocomplete="off" @click="$router.push('/login')">取消</el-button>
                </div>
            </el-form>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Login",
        data(){
            return{
                user:{},
                rules: {
                    name: [
                        {required: true, message: '请输入用户名', trigger: 'blur'},
                        {min: 3, max: 10, message: '长度在 3 到 5 个字符', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'blur'},
                        {min: 1, max: 10, message: '长度在 3 到 5 个字符', trigger: 'blur'}
                    ],
                    comfirmPassword: [
                        {required: true, message: '请确认密码', trigger: 'blur'},
                        {min: 1, max: 10, message: '长度在 3 到 5 个字符', trigger: 'blur'}
                    ]
                }
            }
        },
        methods:{
            register(){
                    this.$refs['userForm'].validate((valid) => {
                        if (valid) {
                            if(this.user.password!==this.user.comfirmPassword){
                                this.$message.error("两次输入不同")
                                return false
                            }
                            axios.post("http://localhost:8182/user/register", this.user).then(res => {
                                if (res.data.code==='200') {
                                  this.$message.success("注册成功")
                                } else {
                                    this.$message.error("注册失败")
                                }
                            })
                        }
                    });
            }
        }
    }
</script>

<style scoped>
        .wrapper{
            background-repeat: no-repeat;
            background-image: url("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F201812%2F01%2F20181201195928_zz5Be.jpeg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1653575299&t=ad46c6483038bcd767765b4671715130");
            overflow: hidden;
        }
</style>