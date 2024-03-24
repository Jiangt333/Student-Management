
const jwt = require('jsonwebtoken');
// 检测JWT令牌是否过期
function isTokenExpired(token) {
    try {
        const decoded = jwt.verify(token, 'your_secret_key_here');
        // 读取过期时间
        const expTime = decoded.exp;
        console.log('JWT 过期时间戳：', expTime);
        // 获取当前时间戳
        const currentTime = Math.floor(Date.now() / 1000);
        console.log('当前时间戳：', currentTime);
        // 判断是否过期
        if (currentTime > expTime) {
            return true;
        } else {
            return false;
        }
      } catch (error) {
        console.error('JWT 解析失败：', error.message);
      }
}
  
// 刷新JWT令牌
async function refreshJwtToken() {
    try {
        const refreshToken = localStorage.getItem('refreshToken'); // 从LocalStorage中获取刷新令牌
        const response = await axios.post('http://43.136.61.147:1000/api//refresh-token', { refreshToken });
        const newJwtToken = response.data.jwtToken; // 获取到新的JWT令牌
        localStorage.setItem('jwtToken', newJwtToken); // 更新LocalStorage中的JWT令牌
        console.log('刷新JWT令牌成功');
    } catch (error) {
        console.error('刷新JWT令牌失败', error);
    }
}

export { isTokenExpired, refreshJwtToken }