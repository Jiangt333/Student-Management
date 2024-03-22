/*
 * 整个项目api的管理
 */
import request from './request';
export default {
  GetUser(params) {
    return request({
      url: '/api/test',
      method: 'post',
      mock: false,
      data: params,
    });
  }
};