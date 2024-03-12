<template>
  <meta charset="charset=utf-8"/>
    <div class="bgimg">
      <div>
        <el-card class="card">
          <el-button class="login" onclick="window.location.href = 'https://gitlab.igem.org/2023/software-tools/sysu-software'">
          登录
          </el-button>
          <el-button class="help">
          忘记密码
          </el-button>
          <el-button class="help">
          需要帮助
          </el-button>
        </el-card>
      </div>
    </div>

</template>
  
<script  setup>
import {ref} from 'vue'
import {useRoute, useRouter} from 'vue-router'
const router = useRouter()
const route = useRoute()
</script>

<style scoped>
.bgimg{
  position: fixed;
  height: 100%;
  width: 100%;
  background-image: url('../assets/body.jpg');
  background-size:100% 100%;
}

.login{
  z-index: 1;
  margin-top:5%;
  margin-left: 10%;
  margin-right: 10%;
  border-radius: 2rem;
  height: 3rem;
  width: 80%;
  font-size: 1.2rem;
  border: none;
  background-color: rgba(12, 64, 196, 0.58);
  color: aliceblue;
}
.help{
  z-index: 1;
  margin-top:7%;
  margin-left: 5%;
  margin-right: 5%;
  border-radius: 2rem;
  height: 50%;
  width: 100%;
  font-size: 1rem;
  width: 40%;
  border: none;
}
.card{
  margin-top:15%;
  margin-left: 60%;
  opacity: 80%;
  width: 23%;
  height: 11rem;
  color: aliceblue;
}
</style>
  