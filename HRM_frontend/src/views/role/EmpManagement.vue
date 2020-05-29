<template>
      <el-form ref="addForm" :model="addEmp" >
        <el-form-item label="姓名*" prop="name">
          <el-input
            v-model.trim="addEmp.name"
            placeholder="请输入内容"
            clearable
            @input="inputChange()"
          />
        </el-form-item>
        <el-form-item label="籍贯*" prop="address">
          <el-input
            v-model.trim="addEmp.address"
            placeholder="请输入内容"
            clearable
            @input="inputChange()"
          />
        </el-form-item>
        <el-form-item label="联系方式*" prop="phone">
          <el-input
            v-model.trim="addEmp.phone"
            placeholder="请输入内容"
            clearable
            @input="inputChange()"
          />
        </el-form-item>
        <el-form-item label="身份证号*" prop="identity">
          <el-input
            v-model.trim="addEmp.identity"
            maxLength='18'
            placeholder="请输入内容"
            clearable
            @input="inputChange()"
          />
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input
            v-model.trim="addEmp.age"
            type="number"
            maxLength='3'
            placeholder="请输入内容"
            clearable
            @input="inputChange()"
          />
        </el-form-item>
        <el-form-item label="职位*" prop="job">
          <el-input
            v-model.trim="addEmp.job"
            placeholder="请输入内容"
            clearable
            @input="inputChange()"
          />
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input
            v-model.trim="addEmp.email"
            placeholder="请输入内容"
            clearable
            @input="inputChange()"
        />
        </el-form-item>

        <el-form-item label="部门*">
        <el-select v-model="addEmp.dep"  @change="inputChange()" placeholder="请选择部门" >
          <el-option 
            v-for="item in depList" 
            :key="item.name"
            :label="item.name"
            :value="item.name"
          ></el-option>
        </el-select>
      </el-form-item>
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          :disabled="add"
          @click="addHandleYes()"
        >添加</el-button>
      </el-form>
</template>

<script>
export default {
  name: 'TestTable',
  data() {
    return {
      addEmp:{
        name:'',
        address:'',
        phone:'',
        identity:'',
        age: 0,
        job:'',
        email: '',
        dep:''
      },
      add:true,
      depList:[
          {id:0,name:'初级部门'},
          {id:1,name:'中级部门'},
          {id:2,name:'高级部门'},
          {id:3,name:'后勤部门'},
          {id:4,name:'人事部门'},
        ]
    }
  },
  methods: {
    inputChange() {
        if (this.addEmp.name !== '' && this.addEmp.address !== '' && this.addEmp.phone !== '' && this.addEmp.identity !== '' && this.addEmp.job !== '' && this.addEmp.dep !== '') {
          this.add = false
        } else {
          this.add = true
        }

    },
    getParamMap: function() {
      return {
        name:this.addEmp.name,
        address:this.addEmp.address,
        phone:this.addEmp.phone,
        identity:this.addEmp.identity,
        age:this.addEmp.age,
        job:this.addEmp.job,
        email: this.addEmp.email,
        dep:this.addEmp.dep,
      }
    },
    
    // 确认添加
    addHandleYes: function() {
      this.$confirm('确认无误后将提交, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        })
          .then(() => {
            axios.post('/role/insertRole', this.getParamMap()).then(res => {
        if (res.data.status === 1) {
          if (this.notifyInstance) {
            this.notifyInstance.close()
          }
          this.notifyInstance = this.$notify({
            message: '添加成功',
            type: 'success',
            position: 'bottom-right'
          })
          // 添加成功关闭对话框
          this.dialogAddVisible = false
          // 清空表单
          this.$refs['addForm'].resetFields()
        } else {
          if (this.notifyInstance) {
            this.notifyInstance.close()
          }
          this.notifyInstance = this.$notify({
            title: '错误信息',
            message: '添加失败（后台错误）',
            type: 'error',
            position: 'bottom-right'
          })
        }
      })
          })
          .catch(() => {
            if (this.notifyInstance) {
              this.notifyInstance.close()
            }
            this.notifyInstance = this.$notify({
              title: '提示信息',
              type: 'info',
              message: '添加操作已取消',
              position: 'bottom-right'
            })
          })
    },
    // 取消添加
    addHandleNo: function() {
      this.dialogAddVisible = false
      this.$refs['addForm'].resetFields()
    }
  }
}
</script>

<style>
.el-form-item__label {
    font-size: 16px;
    color: #606266;
    font-weight: normal;
}
</style>

