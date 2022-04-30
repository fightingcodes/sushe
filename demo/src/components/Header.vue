<template>
    <div style=" font-size: 12px;display: flex">
        <div style="flex: 1;font-size: 18px">
            <span :class="collapseBtnClass" style="cursor: pointer" @click="Collapse"></span>
        </div>
        <el-dropdown style="width: 100px;cursor: pointer " >
            <span>王小虎</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
            <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>个人信息</el-dropdown-item>
                <el-dropdown-item>
                <router-link to="/login" style="text-decoration: none">退出</router-link>
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
        }
    }
</script>

<style scoped>

</style>