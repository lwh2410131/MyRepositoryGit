<template>
  <div class="g_container">
    <div class="g_inputForm">
      <el-form v-model="Department">
        <el-row style="margin-left:20px;">
          <el-col :span="6">
            部门名：
            <el-input
              v-model.trim="Department.dep"
              placeholder="请输入内容"
              style="width:70%;"
              clearable
            />
          </el-col>
          <el-col :span="6">
            地址:
            <el-input
              v-model.trim="Department.address"
              placeholder="请输入内容"
              style="width:70%;"
              clearable
            />
          </el-col>
        <el-col :span="6">
            负责人:
            <el-input
              v-model.trim="Department.address"
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
      <el-button type="primary" plain icon="el-icon-edit" @click="handleEdit()">修改</el-button>
      <download-excel
            :data = "tableData"
            name = "报告导出.xls"
            style="float:right;margin-left:10px">
            <el-button type="primary" plain icon="el-icon-upload">导出</el-button>
      </download-excel>
    </div>


  <!--修改dialog-->
    <el-dialog title="修改部门对话框" :visible.sync="dialogUpdateVisible" width="30%" top="10%" :close-on-click-modal="false">
      <el-form ref="editForm" :model="Dep" label-width="120px" required="required">
        <el-form-item label="联系电话：" prop="telephone">
          <el-input
            v-model="Dep.telephone"
            placeholder="请输入内容"
            clearable
            @input="inputChange()"
          />
        </el-form-item>
        <br>
        <br>
        <el-form-item label="负责人" prop="manager">
          <el-input
            v-model="Dep.manager"
            placeholder="请输入内容"
            clearable
            @input="inputChange()"
          />
        </el-form-item>
        <br>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" plain icon="el-icon-back" @click="editHandleNo()">取消</el-button>
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          :disabled="edit"
          @click="editHandleYes()"
        >修改</el-button>
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
      <el-table-column label="部门" align="center">
        <template slot-scope="{row}">
          <span>{{ row.dep }}</span>
        </template>
      </el-table-column>
      <el-table-column label="地址" align="center">
        <template slot-scope="{row}">
          <span>{{ row.address }}</span>
        </template>
      </el-table-column>
      <el-table-column label="联系方式" align="center">
        <template slot-scope="{row}">
          <span>{{ row.telephone}}</span>
        </template>
      </el-table-column>
      <el-table-column label="负责人" align="center">
        <template slot-scope="{row}">
          <span>{{ row.manager }}</span>
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
      Department: {
        dep: '',
        address: null,
        telephone: null,
        manager: null,
      },
      Dep:{
        dep: '',
        address: null,
        telephone: null,
        manager: null,
      },
      options: [],
      value: '',
      show: false,
      currentPage: 1,
      total: 100,
      pageSize: 10,
      tableData: [],
      multipleTable: [],
      dialogUpdateVisible: false, // 修改对话框
      edit: true // 修改按钮禁用
    }
  },
  created() {
    this.search();
  },
  methods: {
    search: function() {
      axios.get('/dep/selectDepByCondition', { params: this.getParamMap() }).then(response => {
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
        dep: this.Department.dep,
        address: this.Department.address,
        phone: this.Department.phone,
        identity: this.Department.identity,
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
    handleEdit: function() {
      if (this.multipleTable.length === 0) {
        if (this.notifyInstance) {
          this.notifyInstance.close()
        }
        this.notifyInstance = this.$notify({
          title: '警告信息',
          message: '请选择至少一条数据',
          type: 'warning',
          position: 'bottom-right'
        })
      } else if (this.multipleTable.length > 1) {
        if (this.notifyInstance) {
          this.notifyInstance.close()
        }
        this.notifyInstance = this.$notify({
          title: '警告信息',
          message: '最多只能选择一条数据进行操作',
          type: 'warning',
          position: 'bottom-right'
        })
      } else {
        this.Dep.telephone = this.multipleTable[0].telephone
        this.Dep.manager = this.multipleTable[0].manager
        this.dialogUpdateVisible = true
        this.inputChange()
        if (
          this.Dep.telephone === this.multipleTable[0].telephone &&
          this.Dep.manager === this.multipleTable[0].manager
        ) {
          this.edit = true
        } else {
          this.edit = false
        }
      }
    },
    // 确认修改
    editHandleYes: function() {
      const data = {}
      data.id = this.multipleTable[0].id
      data.telephone = this.Dep.telephone
      data.manager = this.Dep.manager
      axios.put('/dep/updateDep', data).then(res => {
        if (res.data.resultData === 1) {
          if (this.notifyInstance) {
            this.notifyInstance.close()
          }
          this.notifyInstance = this.$notify({
            title: '提示信息',
            message: '修改成功',
            type: 'success',
            position: 'bottom-right'
          })
          this.dialogUpdateVisible = false
          this.$refs['editForm'].resetFields()
          this.$refs.multipleTable.clearSelection()
          this.resetDep()
          this.multipleTable = []
          this.search()
        } else if (res.data.msg === 'DEPERROR') {
          if (this.notifyInstance) {
            this.notifyInstance.close()
          }
          this.notifyInstance = this.$notify({
            title: '错误信息',
            message: '后台修改出错',
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
          message: '修改操作未完成',
          type: 'info',
          position: 'bottom-right'
        })
      })
    },
    // 取消修改
    editHandleNo() {
      this.dialogUpdateVisible = false
      this.$refs['editForm'].resetFields()
    },
    resetDep() {
      this.Dep.telephone = ''
      this.Dep.manager = ''
    },
    inputChange() {
      if (this.dialogUpdateVisible === true) {
        if (
          this.Dep.telephone !== this.multipleTable[0].telephone ||
          this.Dep.manager !== this.multipleTable[0].manager 
        ) {
          this.edit = false
        } else {
          this.edit = true
        }
      }
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

