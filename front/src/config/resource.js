import Minio from 'minio'

const minioClient = new Minio.Client({
    endPoint: 'play.min.io',
    port: 9000,
    useSSL: true,
    accessKey: 'YOUR_ACCESS_KEY',
    secretKey: 'YOUR_SECRET_KEY'
  })
  
  // 上传文件到MinIO
export const uploadFile = (bucketName, objectName, filePath, callback) => {
  minioClient.fPutObject(bucketName, objectName, filePath, (err, etag) => {
    if (err) {
      callback(err, null)
      return
    }
    callback(null, etag)
  })
}

// 下载文件从MinIO
export const downloadFile = (bucketName, objectName, filePath, callback) => {
  minioClient.fGetObject(bucketName, objectName, filePath, (err) => {
    if (err) {
      callback(err)
      return
    }
    callback(null)
  })
}