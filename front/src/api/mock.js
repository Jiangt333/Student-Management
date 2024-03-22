import Mock from 'mockjs'
 
// mock的配置
Mock.setup({
  // 随机延时500-1000毫秒
  timeout: '500-1000'
})


// 拦截请求
Mock.mock(/\/my\/test/, 'get', () => {
    const arr = []
    for (let i = 0; i <= 4; i++) {
      arr.push(Mock.mock({
        // 生成唯一id 和 name
        id: '@id',
        name: '@cname'
      }))
    }
    return { msg: '获取数据成功', result: arr }
})