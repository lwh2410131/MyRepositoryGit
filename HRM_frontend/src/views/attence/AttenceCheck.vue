<template>
  <div class="g_container">
    <div class="g_inputForm">
      <el-form v-model="Attence">
        <el-row style="margin-left:20px;">
          <el-col :span="6">
            姓名：
            <el-input
              v-model.trim="Attence.name"
              placeholder="请输入内容"
              style="width:70%;"
              clearable
            />
          </el-col>
          <el-col :span="6">
            日期：
            <el-date-picker
              v-model="Attence.beginDate"
              placeholder="请输入内容"
              style="width:70%;"
              clearable
            />
          </el-col>
          <el-col :span="1">
            <el-form-item label="至" />
          </el-col>
          <el-col :span="6">
            <el-date-picker
              v-model="Attence.endDate"
              placeholder="请输入内容"
              style="width:70%;"
              clearable
            />
          </el-col>
        </el-row>
      </el-form>
    </div>
    <div style="float:right;margin-bottom:20px">
      <el-button type="primary" plain icon="el-icon-search" @click="handleSerach()">查询</el-button>
      <el-button type="primary" plain icon="el-icon-plus" @click="handleAdd()">添加绩效</el-button>
      <download-excel
            :data = "tableData"
            name = "报告导出.xls"
            style="float:right;margin-left:10px">
            <el-button type="primary" plain icon="el-icon-upload">导出</el-button>
      </download-excel>
    </div>

    <!--添加dialog-->
    <el-dialog title="添加绩效信息对话框" :visible.sync="dialogAddVisible" :close-on-click-modal="false" width="50%" top="10%">
      <el-form ref="addForm" :model="addAttence" label-width="120px">
        <el-form-item label="姓名" prop="name">
          <el-input
            v-model.trim="addAttence.name"
            placeholder="请输入内容"
            clearable
            @input="inputChange()"
          />
        </el-form-item>
        <el-form-item label="绩效成绩" prop="attence">
          <el-select
            v-model.trim="addAttence.attence"
            placeholder="请选择"
            @input="inputChange()">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="审核员" prop="checker">
          <el-input
            v-model.trim="addAttence.checker"
            placeholder="请输入内容"
            clearable
            @input="inputChange()"
          />
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input
            v-model.trim="addAttence.description"
            placeholder="请输入内容"
            clearable
            type="textarea"
            @input="inputChange()"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" plain icon="el-icon-back" @click="addHandleNo()">取消</el-button>
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          :disabled="add"
          @click="addHandleYes()"
        >添加</el-button>
      </div>
    </el-dialog>

    <!-- @row-click="handleRowClick" -->
    <el-table
      ref="multipleTable"
      :data="tableData"
      class="g_table"
      :row-key="getRowKeys"
      :header-cell-style="{background:'#ecf5ff',fontSize:'14px',color:'#606266'}"
      border
      @selection-change="handleSelectionChange"
    >
      <!-- 全选 -->
      <el-table-column class="g_tableSelection" type="selection" :reserve-selection="true" />
      <!-- 测试ID(hidden) -->
      <el-table-column v-if="show" label="ID">
        <template slot-scope="{row}">
          <span>{{ row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column type="index" :index="table_index" label="序号" width="60px" align="center" />
      <el-table-column label="员工名" align="center">
        <template slot-scope="{row}">
          <span>{{ row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="绩效成绩" align="center">
        <template slot-scope="{row}">
          <span>{{ row.attence }}</span>
        </template>
      </el-table-column>
      <el-table-column label="描述" align="center">
        <template slot-scope="{row}">
          <span>{{ row.description}}</span>
        </template>
      </el-table-column>
      <el-table-column label="时间" align="center">
        <template slot-scope="{row}">
          <span>{{ row.date }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审核员" align="center">
        <template slot-scope="{row}">
          <span>{{ row.checker }}</span>
        </template>
      </el-table-column>
    </el-table>
    <div>
      <el-pagination
        :current-page.sync="currentPage"
        :page-sizes="[10, 20, 30, 40]"
        :page-size="pageSize"
        layout="sizes, prev, pager, next"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </div>
  </div>
</template>

<script>
export default {
  name: 'TestTable',
  data() {
    return {
      Attence: {
        name: '',
        beginDate: null,
        endDate: null
      },
      addAttence:{
        name: '',
        attence: null,
        description: null,
        checker: null
      },
      options: [{
        value: '优秀',
        label: '优秀'
      }, {
        value: '良好',
        label: '良好'
      }, {
        value: '一般',
        label: '一般'
      }, {
        value: '不合格',
        label: '不合格'
      }],
      value: '',
      show: false,
      currentPage: 1,
      total: 100,
      pageSize: 10,
      tableData: [],
      multipleTable: [],
      add: false,
      dialogAddVisible : false,
      dialogUpdateVisible : false
    }
  },
  created() {
    this.search();
  },
  methods: {
    search: function() {
      axios.get('/attence/selectAttenceByCondition', { params: this.getParamMap() }).then(response => {
        if (response.data.status === 1) {
          this.tableData = response.data.resultData.rows 
          this.total = response.data.resultData.total
        } else if (response.data.msg === 'ERROR') {
          if (this.notifyInstance) {
            this.notifyInstance.close()
          }
          this.notifyInstance = this.$notify({
            title: '错误',
            message: '后台查询出错',
            type: 'error',
            position: 'bottom-right'
          })
        }
      })
    },
    getParamMap: function() {
      return {
        page: this.currentPage,
        rows: this.pageSize,
        name: this.Attence.name,
        beginDate: this.Attence.beginDate,
        endDate: this.Attence.endDate
      }
    },
    getRowKeys: function(row) {
      return row.id
    },
    handleSizeChange: function(val) {
      this.pageSize = val
      this.search()
    },
    handleCurrentChange: function(val) {
      this.currentPage = val
      this.search()
    },
    handleSelectionChange(val) {
      this.multipleTable = val
    },
    table_index(index) {
      return (this.currentPage - 1) * this.pageSize + index + 1
    },
    handleSerach: function(){
      this.search();
    },
    handleAdd: function() {
      // 清空添加表单数据
      this.resetFrom()
      this.add = true
      // 打开添加对话框
      this.dialogAddVisible = true
    },
    resetFrom() {
      this.addAttence.name = '',
      this.addAttence.attence = '',
      this.addAttence.description = '',
      this.addAttence.checker = ''
    },
     // 确认添加
    addHandleYes: function() {
      axios.post('/attence/insertAttence', this.addAttence).then(res => {
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
          this.resetFrom()
          this.search()
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
      }).catch(() => {
        if (this.notifyInstance) {
          this.notifyInstance.close()
        }
        this.notifyInstance = this.$notify({
          title: '提示信息',
          message: '添加操作已取消',
          type: 'info',
          position: 'bottom-right'
        })
      })
    },
    // 取消添加
    addHandleNo: function() {
      this.dialogAddVisible = false
      this.$refs['addForm'].resetFields()
    },
    inputChange() {
      if (this.dialogAddVisible === true && this.dialogUpdateVisible === false) {
        if (this.addAttence.name !== '' && this.addAttence.attence !== '' && this.addAttence.description !== '') {
          this.add = false
        } else {
          this.add = true
        }
      } else if (this.dialogUpdateVisible === true && this.dialogAddVisible === false) {
        if (this.Attence.name !== this.multipleTable[0].name ) {
          this.edit = false
        } else {
          this.edit = true
        }
      }
    },
  
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

