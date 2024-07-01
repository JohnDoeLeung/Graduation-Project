import api from './api'

/**
 * 注册=== 用户名+昵称+手机号+密码  member/auth/register
 */
export function register(data) {
  return api.post('/member/auth/register', data, { login: false })
}