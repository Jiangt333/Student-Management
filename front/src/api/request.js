import axios from 'axios';
import EnvConfig from '../config';
import { ElMessage } from 'element-plus';
import {isTokenExpired, refreshJwtToken} from './token'


// 创建axios实例对象
const service = axios.create({
  baseURL: EnvConfig.baseApi,
});

// 请求之前
service.interceptors.request.use((req) => {
  // jwt-token认证
  const jwtToken = localStorage.getItem('jwtToken');
  // 检测JWT令牌是否过期
  if (jwtToken && isTokenExpired(jwtToken)) { 
    refreshJwtToken(); // 刷新JWT令牌
  }
  if (jwtToken) {
    req.headers.Authorization = `Bearer ${jwtToken}`;
  return req;
  }
});

// 请求之后
service.interceptors.response.use((res) => {
  console.log(res.data);
  const { code, data, msg } = res.data;
  console.log(code);
  if (code == 200) {
    return data;
  } 
  else if (code == 404) {
    ElMessage.error('网络请求失败，请稍后再试！');
  } 
  else {
    ElMessage.error(msg || '网络请求异常，请稍后重试！');
    return Promise.reject(msg || '网络请求异常，请稍后重试！');
  }
});

// 封装的核心函数
function request(options) {
  if (options.method.toLowerCase() == 'post') {
    options.params = options.data;
  }
  //对mock的处理
  let isMock = options.mock;
  service.defaults.baseURL = isMock ? EnvConfig.mockApi : EnvConfig.baseApi;
  return service(options);
}

export default request;