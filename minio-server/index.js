const express = require('express');
const Minio = require('minio');

const app = express();
const port = 3000;

// 创建 MinIO 客户端对象
const minioClient = new Minio.Client({
  endPoint: '43.136.61.147', // MinIO 服务器的IP 地址
  port: 9000, // MinIO 服务器的端口号
  useSSL: false,
  accessKey: 'I8Fw4gWfTBYUQoW4TYOE',
  secretKey: '2RpvvAZmX9FXDIWN5Q2ayOu8KoIR2qiuWirA9H8L'
});



// 处理预签名 URL 的请求
app.get('/generatePresignedURL', (req, res) => {
  const bucketName = req.query.bucketName;
  const objectName = req.query.objectName;
  const expiryTimeInSeconds = parseInt(req.query.expiryTimeInSeconds);
  minioClient.presignedPutObject(bucketName, objectName, expiryTimeInSeconds, function(err, presignedURL) {
    if (err) return console.log(err)
    console.log(`presignedURL: ${presignedURL}`);
    // 设置允许跨域访问的头部
    res.header("Access-Control-Allow-Origin", "*");
    res.send(presignedURL);
  })
});


// 启动 Express 服务器
app.listen(port, () => {
  console.log(`Server is running at http://localhost:${port}`);
});
