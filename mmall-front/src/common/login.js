import api from './api';
import user from './config';

// 校验用户名
export function checkAdmin(rule, value, callback) {
  if (value === '') {
    return callback(new Error('请输入用户名'));
  }
  let url = 'http://192.168.1.107:8080/mmall/user/check.do';
  let data = {
    str: value,
    type: 'username'
  };
  api('post', url, data).then(function (res) {
      console.log(res);
      if (res.status === 0) {
        return callback(new Error(res.msg));
      } else {
        return callback();
      }
    }
  );
}

export function checkRegAdmin(rule, value, callback) {
  if (value === '') {
    return callback(new Error('请输入用户名'));
  }
  if (value.length < 2) {
    return callback(new Error('用户名不少于2位'));
  }
  return callback();
}

export function validatePass2(rule, value, callback) {
  if (value === '') {
    callback(new Error('请再次输入密码'));
  } else if (value !== user.pass) {
    callback(new Error('两次输入密码不一致!'));
  } else {
    callback();
  }
}

// 校验密码
export function validatePass(rule, value, callback) {
  if (value === '') {
    return callback(new Error('请输入密码'));
  } else if (value.length < 6) {
    return callback(new Error('密码不能少于6位'));
  } else {
    return callback();
  }
}

// 提交登录信息
export function submit(userName, pass) {
  let url = 'http://192.168.1.107:8080/mmall/user/login.do';
  let data = {
    username: userName,
    password: pass
  };
  return api('post', url, data);
}

export function register(userName, pass) {
  let url = 'http://192.168.1.107:8080/mmall/user/register.do';
  let data = {
    username: userName,
    password: pass
  };
  return api('post', url, data);
}
