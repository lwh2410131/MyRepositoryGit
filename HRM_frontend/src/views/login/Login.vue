<template>
  <div class="box">
    <div id="login" style="width: 320px;height: 300px;text-align: center;">
      <el-form :model="loginForm" ref="loginForm" :rules="rules" style="margin-top:000px;">
        <el-form-item>
          <span style="color: white;font-size: 26px;">人&nbsp;力&nbsp;资&nbsp;源&nbsp;管&nbsp;理&nbsp;系&nbsp;统&nbsp;</span>
        </el-form-item>
        <el-form-item prop="userName">
          <el-input type="text" v-model="loginForm.userName" placeholder="用户名">
            <template slot="prepend"><i class="el-icon-user" style="font-size: 20px; color: white;"></i></template>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input type="text" v-model="loginForm.password" placeholder="密码" show-password>
            <template slot="prepend"><i class="el-icon-lock" style="font-size: 20px;color: white;"></i></template>
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" size="medium" :loading="loading" style="font-size: 20px;font-family: 微软雅黑;width: 320px;"
            @click="clickLogin">登&nbsp;&nbsp;&nbsp;录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
  export default {
    //刷新获取当前用户信息  
    mounted() {
      this.getSessionStorage()
    },
    data() {
      var validateUserName = (rule, value, callback) => {
        if (value.length === 0) {
          return callback(new Error('请输入用户名'))
        } else {
          callback()
        }
      }
      var validatePassword = (rule, value, callback) => {
        if (value.length === 0) {
          callback(new Error('请输入密码'))
        } else if (value.length < 3) {
          callback(new Error('密码不能小于3位'))
        } else {
          callback()
        }
      }
      return {
        loginForm: {
          userName: '',
          password: ''
        },
        loginCheck : false,
        loading: false,   //登陆加载效果
        rules: {
          userName: [{
            required: true,
            trigger: 'blur',
            validator: validateUserName
          }],
          password: [{
            required: true,
            trigger: 'blur',
            validator: validatePassword
          }]
        }
      }
    },
    methods: {
      AccountCheck:function(){
        axios.get('/account/accountCheck', {params: {username:this.loginForm.userName,password:this.loginForm.password}}
          ).then(response => {
          if (response.data.status === 1) {
            if(response.data.resultData !== null){
              this.loginCheck =  response.data.resultData = 1 ?true:false
            }
          } else if (response.data.msg === 'Error') {
            if (this.notifyInstance) {
              this.notifyInstance.close()
            }
            this.notifyInstance = this.$notify({
              title: '错误信息',
              message: '后台查询出错',
              type: 'error',
              position: 'bottom-right'
            })
            this.loginCheck =  false
          }
        })
      },
      clickLogin() {
        this.AccountCheck();
        this.$refs.loginForm.validate(valid => {
          if (valid) {
            this.loading = true
            setTimeout(() => {
              if (this.loginCheck) {
                sessionStorage.setItem('userName',this.loginForm.userName);
                sessionStorage.setItem('password',this.loginForm.password);
                this.$router.push({
                  name: 'Main',
                });
              } else {
                this.$notify({
                  title: '登录提示',
                  message: '用户名或密码错误',
                  position: 'bottom-right',
                  type: 'error'
                });
                this.loading=false;
              }
            }, 3000);
          } else {
            return false;
          }
        })
      },
      getSessionStorage(){
        var username = sessionStorage.getItem('userName')
        var password = sessionStorage.getItem('password')
        if (username != null && password !=null) {
          this.$router.push({
            name: 'Main',
          });
        } else {
          this.$message('欢迎登陆');
          sessionStorage.clear();
        }
      }
    }
  }
</script>

<style scoped="scoped">
  .box {
    display: flex;
    height: 100%;
    justify-content: center;
    align-items: center;
  }
</style>
<style>
  .el-input-group__prepend {
    padding: 0px 15px;
    background-color: #CCCCCC;
    border: 1 solid #72767B;
  }

  body {
    background-color: #001f3f;
    margin: 0px;
  }
</style>
