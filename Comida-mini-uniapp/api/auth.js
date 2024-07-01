import api from './api'


/**
 * 使用手机 + 密码登录  member/auth/login
 */
export function loginByTel(data) {
  return api.post('/member/auth/login', data, { login: false })
}

/**
 * 小程序 member/auth/auth-miniapp-login         
 */
export function userLoginForWechatMini(data) {
  return api.post('/member/auth/auth-miniapp-login', data, { login: false })
}

/**
 * userAuthSession   
 */
export function userAuthSession(data) {
  return api.post('/member/auth/auth-session', data, { login: false })
}


