<template>
  <div class="el-container">
    <div class="form-wrapper">
      <div>欢迎注册</div>
      <br>
      <el-form :model="user" :rules="loginRules" :status-icon="false" ref="loginForm" label-width="100px"
               class="demo-ruleForm login-form">
        <el-form-item label="用户名" prop="admin">
          <el-input v-model="user.admin"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="pass">
          <el-input type="password" v-model="user.pass" auto-complete="off" ref="password"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="checkPass">
          <el-input type="password" v-model="user.checkPass" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleLogin" ref="submit">提交</el-button>
          <el-button @click="resetForm('userInfo')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
  import {checkRegAdmin, validatePass, validatePass2, register} from '@/common/login';
  import user from '@/common/config';

  export default {
    data() {
      return {
        user: user,
        loginRules: {
          pass: [
            {validator: validatePass, trigger: 'blur'}
          ],
          checkPass: [
            { validator: validatePass2, trigger: 'blur' }
          ],
          admin: [
            {validator: checkRegAdmin, trigger: 'blur'}
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
          let admin = _this.user.admin;
          let pass = _this.user.pass;
          register(admin, pass).then((res) => {
            console.log(res);
            if (res.status === 0) {
              _this.$router.push({path: '/login'});
            } else {
              alert(res.msg);
            }
          });
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
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
