<template>
  <!--四色丰阳-->
  <TopBar></TopBar>
  <div class="title-1" id="home1"><p>—学 生 管 理 平 台—</p></div>
  <div class="container-1">
    
  </div>



</template>

<script setup>
import TopBar from "./TopBar.vue";
import { useRoute, useRouter, onBeforeRouteUpdate } from 'vue-router'
import { ref, reactive, inject, onMounted } from 'vue'
const router = useRouter()
const route = useRoute()
  // mounted() {
  //   // 改变网页背景颜色
  //   document.querySelector('body').setAttribute('style', 'background-color:#d9e0dc');
  // },
  // beforeDestroy() {
  //   document.querySelector('body').removeAttribute('style')
  // },

</script>
<style scoped>
.title-1 {
  width: 100%;
  font-size: 80px;
  font-family: STXingkai;
  font-weight: 100;
  color: #474141;
  margin-top: 100px;
  margin-bottom: 10px;
  display: flex;
  justify-content: center;
}

img{
  transition: all .5s;
}

.container-1 {
  .image-container0 {
    aspect-ratio: 670/360;
    .image-wrapper {
      width: 100%;
      height: 100%;
      overflow: hidden;
      align-items: center;
      justify-content: center;
      .responsive-image {
        width: 100%;
        height: 100%;
        object-fit: cover;
      }
    }
  }
  .txt-container {
    background: url(../../assets/HomePage/index-pic-2.jpg);
    .fengyang_subtitle {
      color: rgb(0, 0, 0);
      font-size: 34px;
      top: 6%;
      margin: 20px;
      text-align: center;
      font-family: 宋体;
      
    }
    .content_txt
    {
      top: 8%;
      left: 5%;
      width: 90%;
      text-indent:2em;
      font-size: 18px;
      font-family: 宋体;
      color: rgb(0, 0, 0);
      display: flex;
      text-align: center;
      word-break: keep-all;
      line-height: 30px;
      
    }
  }
}

.image-container0:hover img {
    transform: scale(1.1);
  }
</style>