<template>
    <div style="height: 100%">
        <el-container style="height: 100%">
            <el-aside :width="sidewith+'px'" style="background-color: rgb(238, 241, 246);height:100%;overflow-x: hidden">
                <el-menu :default-openeds="['1', '3']" style="height: 100%"  background-color="rgb(48,65,86)"
                         text-color="#fff" :collapse-transition="false" :collapse="isCollapse"
                >
                    <div style="height: 60px;line-height: 60px;text-align: center;color: aliceblue">
                        <img src="../assets/logo.png" alt="" style="width: 15px;position: relative;top:2px;margin-right: 5px">
                        <b v-show="LogoTextShow">312管理系统</b></div>

                    <el-submenu index="1">
                        <template slot="title"><i class="el-icon-message"></i><span slot="title">编辑信息</span></template>
                    </el-submenu>

                </el-menu>
            </el-aside>

            <el-container>
                <el-header style=" font-size: 12px;display: flex">
                    <div style="flex: 1;font-size: 18px">
                        <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>
                    </div>
                    <el-dropdown style="width: 100px;cursor: pointer " >
                        <span>王小虎</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item>个人信息</el-dropdown-item>
                            <el-dropdown-item>退出</el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>

                </el-header>

                <el-main>
                    <el-form :model="ruleForm" style="width: 600px" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                        <el-form-item label="编号">
                            <el-input type="text" readonly v-model="ruleForm.id" autocomplete="off"></el-input>
                        </el-form-item>
                        <el-form-item label="用户名" prop="username">
                            <el-input type="text" v-model="ruleForm.name" autocomplete="off"></el-input>
                        </el-form-item>
                        <el-form-item label="密码" prop="password">
                            <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
                        </el-form-item>
                        <el-form-item label="年龄" prop="age">
                            <el-input v-model.number="ruleForm.age"></el-input>
                        </el-form-item>
                        <el-form-item label="qq">
                            <el-input type="text" readonly v-model="ruleForm.qq" autocomplete="off"></el-input>
                        </el-form-item>
                        <el-form-item label="tel">
                            <el-input type="text" readonly v-model="ruleForm.tel" autocomplete="off"></el-input>
                        </el-form-item>
                        <el-form-item label="adr">
                            <el-input type="text" readonly v-model="ruleForm.adr" autocomplete="off"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                            <el-button @click="resetForm('ruleForm')">重置</el-button>
                        </el-form-item>
                    </el-form>


                </el-main>

            </el-container>

        </el-container>

    </div>
</template>
<script>
    export default {
        name: "Update",
        data() {
            var checkAge = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('年龄不能为空'));
                }
                setTimeout(() => {
                    if (!Number.isInteger(value)) {
                        callback(new Error('请输入数字值'));
                    } else {
                        if (value < 18) {
                            callback(new Error('必须年满18岁'));
                        } else {
                            callback();
                        }
                    }
                }, 1000);
            };
            var validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入密码'));
                } else {
                    if (this.ruleForm.checkPass !== '') {
                        this.$refs.ruleForm.validateField('checkPass');
                    }
                    callback();
                }
            };
            var validateName = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入用户名'));
                } else {
                    callback();
                }
            };
            return {
                collapseBtnClass:'el-icon-s-fold',
                isCollapse:false,
                sidewith:200,
                LogoTextShow:true,
                headBg:'headBg',
                ruleForm: {
                    id:'',
                    name: '',
                    password: '',
                    age: '',
                    qq:'',
                    adr:'',
                    tel:''
                },
                rules: {
                    username: [
                        { validator: validateName, trigger: 'blur' }
                    ],
                    password: [
                        { validator: validatePass, trigger: 'blur' }
                    ],
                    age: [
                        { validator: checkAge, trigger: 'blur' }
                    ]
                }
            }
        },
        methods:{
            collapse(){
                this.isCollapse=!this.isCollapse
                if(this.isCollapse){//收缩
                    this.sidewith=64
                    this.LogoTextShow=false
                    this.collapseBtnClass='el-icon-s-unfold'
                }else{//展开
                    this.sidewith=200
                    this.collapseBtnClass='el-icon-s-fold'
                    this.LogoTextShow=true
                }
            },
            submitForm(formName) {
                let _this = this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        //调后台接口，添加数据
                        axios.put('http://localhost:8181/update',_this.ruleForm).then(function (response) {
                            if(response.data){
                                //跳转到Table
                                _this.$router.push('/table')
                            }
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        },
        created() {
            let _this = this
            axios.get('http://localhost:8182/findById/'+this.$route.query.id).then(function (response) {
                _this.ruleForm = response.data
            })
        }
    };
</script>
<style scoped>
    .el-header {
        color: #333;
        line-height: 60px;
    }
    .headBg{
        background: #eee!important;
    }
    .el-aside {
        color: #333;
    }
</style>