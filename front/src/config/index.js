/* 环境配置文件
开发环境 测试环境 线上环境 */

// 当前的环境
const env = import.meta.env.MODE || 'prod';

const EnvConfig = {
  development: {
    baseApi: 'http://43.136.61.147:1000/api/',
    mockApi: 'https://www.fastmock.site/mock/',
  },
  test: {
    baseApi: '/http://43.136.61.147:1000/api/',
    mockApi: 'https://www.fastmock.site/mock/',
  },
  pro: {
    baseApi: 'http://43.136.61.147:1000/api/',
    mockApi: 'https://www.fastmock.site/mock/',
  },
};

export default {
  env,
  // mock开关
  mock: true,
  ...EnvConfig[env],
};