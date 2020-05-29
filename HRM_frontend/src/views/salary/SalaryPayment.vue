<template>
  <div class="g_container">
    <div class="g_inputForm">
      <el-form v-model="Salary">
        <el-row style="margin-left:20px;">
          <el-col :span="5">
            姓名：
            <el-input
              v-model.trim="Salary.name"
              placeholder="请输入内容"
              style="width:70%;"
              clearable
            />
          </el-col>
          <el-col :span="5">
            部门:
            <el-input
              v-model.trim="Salary.dep"
              placeholder="请输入内容"
              style="width:70%;"
              clearable
            />
          </el-col>
          <el-col :span="5">
            日期：
            <el-date-picker
              v-model="Salary.beginDate"
              placeholder="请输入内容"
              style="width:70%;"
              clearable
            />
          </el-col>
          <el-col :span="1">
            <el-form-item label="至" />
          </el-col>
          <el-col :span="5">
            <el-date-picker
              v-model="Salary.endDate"
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
      <el-button type="primary" plain icon="el-icon-plus" @click="handleSerach()">添加</el-button>
      <download-excel
            :data = "tableData"
            name = "报告导出.xls"
            style="float:right;margin-left:10px">
            <el-button type="primary" plain icon="el-icon-upload">导出</el-button>
      </download-excel>
    </div>

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
      <el-table-column label="姓名" align="center">
        <template slot-scope="{row}">
          <span>{{ row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="部门" align="center">
        <template slot-scope="{row}">
          <span>{{ row.dep }}</span>
        </template>
      </el-table-column>
      <el-table-column label="基本工资" align="center">
        <template slot-scope="{row}">
          <span>{{ row.salary }}</span>
        </template>
      </el-table-column>
      <el-table-column label="加班补贴" align="center">
        <template slot-scope="{row}">
          <span>{{ row.reward }}</span>
        </template>
      </el-table-column>
      <el-table-column label="社保个税" align="center">
        <template slot-scope="{row}">
          <span>{{ row.tax }}</span>
        </template>
      </el-table-column>
      <el-table-column label="实发工资" align="center">
        <template slot-scope="{row}">
          <span>{{ row.sum }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结算日期" align="center">
        <template slot-scope="{row}">
          <span>{{ row.date}}</span>
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
      Salary: {
        name: '',
        dep: null,
        beginDate: null,
        endDate: null,
      },
      options: [],
      value: '',
      show: false,
      currentPage: 1,
      total: 100,
      pageSize: 10,
      tableData: [],
      multipleTable: []
    }
  },
  created() {
    this.search();
  },
  methods: {
    search: function() {
      axios.get('/salary/selectSalaryByCondition', { params: this.getParamMap() }).then(response => {
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
        name: this.Salary.name,
        dep: this.Salary.dep,
        beginDate: this.Salary.beginDate,
        endDate: this.Salary.endDate,
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

