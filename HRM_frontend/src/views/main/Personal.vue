<template>
  <div>
    <div>
      <div style="float:left">
        <img style="width:200px" src="@/assets/person.png">
      </div>
      <div style="float:left">
        <div style="font-size:45px;color:#336699"><b>&nbsp;{{name}}</b></div>
        <div style="float:left;color:gray">&nbsp;&nbsp;&nbsp;&nbsp;{{dep}}&nbsp;&nbsp;&nbsp;&nbsp;{{job}}</div>
        <div style="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
        <div style="color:gray">&nbsp;&nbsp;&nbsp;&nbsp;Email:&nbsp;{{email}}&nbsp;|&nbsp;Live:{{address}}</div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  data(){
    return {
      name:"",
      job:"",
      email:"",
      dep:"",
      address:""
    }
  },
  created() {
    this.findRolebyAccount();
    this.findDepbyAccount();
  },
  methods: {
    findRolebyAccount:function(){
      axios.get('/role/findRoleByAccount'
      , {params: {username:sessionStorage.getItem('userName')}}
        ).then(response => {
        if (response.data.status === 1) {
          this.name = response.data.resultData.rows[0].name
          this.job = response.data.resultData.rows[0].job
          this.email = response.data.resultData.rows[0].email 
          this.address = response.data.resultData.rows[0].address 
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
        }
      })
    },
    findDepbyAccount:function(){
    axios.get('/dep/findDepByAccount'
    , {params: {username:sessionStorage.getItem('userName')}}
      ).then(response => {
      if (response.data.status === 1) {
        this.dep= response.data.resultData.rows[0].dep
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
      }
    })
  }
}
}
</script>

<style>
</style>
