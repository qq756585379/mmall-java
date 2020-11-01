<template>
  <div class="el-container">
    <div class="form-wrapper">
      <div>用户登录</div>
      <br>
      <el-form :model="userInfo" :rules="loginRules" :status-icon="false" ref="loginForm" label-width="100px" class="demo-ruleForm login-form">
        <el-form-item label="用户名" prop="admin">
          <el-input v-model="userInfo.admin"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="pass">
          <el-input type="password" v-model="userInfo.pass" auto-complete="off" ref="password"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleLogin" ref="submit">登录</el-button>
          <el-button type="primary">
            <router-link to="/register" class="register">注册</router-link>
          </el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
  import {checkAdmin, validatePass, submit} from '@/common/login';

  export default {
    data() {
      return {
        userInfo: {
          pass: '',
          admin: ''
        },
        loginRules: {
          pass: [
            {validator: validatePass, trigger: 'blur'}
          ],
          admin: [
            {validator: checkAdmin, trigger: 'blur'}
          ]
        }
      };
    },
    methods: {
      handleLogin() {
        var _this = this;
        this.$refs.loginForm.validate((valid) => {
          if (!valid) {
            return false;
          }
          let admin = _this.userInfo.admin;
          let pass = _this.userInfo.pass;
          submit(admin, pass).then((res) => {
            console.log(res);
            if (res.status === 0) {
              _this.$router.push({path: '/hello'});
            } else {
              alert(res.msg);
            }
          }).catch(function (err) {
            console.log(err);
            return false;
          });
        });
      }
    }
  };
</script>

<style rel="stylesheet/scss" lang="scss" scope>
  .el-container {
    background-color: #ff6f94;
    padding: 50px 0;
    .form-wrapper {
      width: 30%;
      margin: 0 auto;
      background: #fff;
      padding: 30px;
      border-radius: 10px;
      .el-dialog__title {
        margin-bottom: 30px;
      }
      .login-form {
        position: relative;
        right: 30px;
        .register {
          color: #ffffff;
          text-decoration: none;
        }
      }
    }
  }
</style>
