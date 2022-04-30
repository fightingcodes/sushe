<template>
 <div style="height: 100%">
  <el-container style="height: 100%">
   <el-aside :width="sidewith+'px'" style="background-color: rgb(238, 241, 246);height:100%;overflow-x: hidden">
       <Aside :isCollapse="isCollapse" :LogoTextShow="LogoTextShow" />
   </el-aside>

   <el-container>
    <el-header style="border-bottom:1px solid #ccc">
      <Header :collapseBtnClass="collapseBtnClass" :Collapse="Collapse"/>
    </el-header>

    <el-main>
    <router-view />
    </el-main>


   </el-container>
  </el-container>

 </div>
</template>


<script>

 import Aside from "@/components/Aside";
 import Header from "@/components/Header";
 export default {
  data() {
   return {
    collapseBtnClass:'el-icon-s-fold',
    isCollapse:false,
    sidewith:200,
    LogoTextShow:true,
   }
  },
  components:{
   Aside,
   Header
  },
  methods:{
   Collapse(){
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

   }
  }
 };
</script>
<style>
 .el-header {
  color: #333;
  line-height: 60px;
 }

 .el-aside {
  color: green;
 }
</style>
