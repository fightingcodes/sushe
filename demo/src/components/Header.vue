<template>
    <div style=" font-size: 12px;display: flex">
        <div style="flex: 1;font-size: 18px">
            <span :class="collapseBtnClass" style="cursor: pointer" @click="Collapse"></span>
        </div>
        <el-dropdown style="width: 100px;cursor: pointer " >
            <div style="display: inline-block">
                <img :src="user.avatarurl" alt=""
                style="width: 30px;border-radius: 50px;position: relative;top: 10px;right: 5px">
                <span>{{user.nickname}}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
            </div>
           
            <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>
                    <router-link to="/person">个人信息</router-link>
                </el-dropdown-item>
                <el-dropdown-item>
                <span  style="text-decoration: none" @click="logout">退出</span>
                </el-dropdown-item>
            </el-dropdown-menu>
        </el-dropdown>
    </div>
</template>

<script>
    export default {
        name: "Header",
        props:{
            collapseBtnClass:String,
            Collapse:""
        },
        data(){
            return{
              user:localStorage.getItem("user")?JSON.parse(localStorage.getItem("user")):{}
            }
        },
        methods:{
            logout(){

                this.$router.push("/login")
                localStorage.removeItem("user")
                this.$message.success("退出成功")
            }
        }
    }
</script>

<style scoped>

</style>