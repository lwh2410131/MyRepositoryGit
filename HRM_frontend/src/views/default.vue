<template>
  <div class="g_container">
    <div class="g_inputForm">
      <el-form v-model="caraccess">
        <el-row style="margin-left:20px;">
          <el-col :xs="2" :sm="4" :md="6" :lg="8" :xl="5">
            <el-form-item label="车牌号：" prop="carNumber">
              <el-input
                v-model="caraccess.carNumber"
                placeholder="请输入车牌号"
                clearable
                style="width:60%;"
                @keyup.enter.native="handleFilter"
              />
            </el-form-item>
          </el-col>
          <el-button type="primary" plain icon="el-icon-search" @click="handleFilter">查询</el-button>
        </el-row>
      </el-form>
    </div>

    <el-table
      :data="tableData"
      class="g_table"
      :header-cell-style="{background:'#ecf5ff',fontSize:'14px',color:'#606266'}"
      border
    >

      <el-table-column label="序号" width="60px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column label="车牌号" align="center">
        <template slot-scope="{row}">
          <span>{{ row.carNumber }}</span>
        </template>
      </el-table-column>
      <el-table-column label="司机" align="center">
        <template slot-scope="{row}">
          <span>{{ row.driverName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="日期" align="center">
        <template slot-scope="{row}">
          <span>{{ row.carTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="动作" align="center">
        <template slot-scope="{row}">
          <span>{{ row.type }}</span>
        </template>
      </el-table-column>
      <el-table-column v-if="show" label="园区经度" align="center">
        <template slot-scope="{row}">
          <span>{{ row.parkLon }}</span>
        </template>
      </el-table-column>
      <el-table-column v-if="show" label="园区纬度" align="center">
        <template slot-scope="{row}">
          <span>{{ row.parkLat }}</span>
        </template>
      </el-table-column>
      <el-table-column v-if="show" label="园区ID" align="center">
        <template slot-scope="{row}">
          <span>{{ row.parkId }}</span>
        </template>
      </el-table-column>
      <el-table-column v-if="show" label="预约ID" align="center">
        <template slot-scope="{row}">
          <span>{{ row.reservationId }}</span>
        </template>
      </el-table-column>
      <el-table-column v-if="show" label="司机ID" align="center">
        <template slot-scope="{row}">
          <span>{{ row.driverId }}</span>
        </template>
      </el-table-column>
      <el-table-column v-if="show" label="园区名称" align="center">
        <template slot-scope="{row}">
          <span>{{ row.parkName }}</span>
        </template>
      </el-table-column>
      <el-table-column v-if="show" label="抽取时间" align="center">
        <template slot-scope="{row}">
          <span>{{ row.createTime }}</span>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'Caraccess',
  data() {
    return {
      caraccess: {
        carNumber: ''
      },
      tableData: [],
      show: false
    }
  },
  created() {
    this.search()
  },
  methods: {
    search: function() {
      axios.get('/api/b06report/caraccess/selectCarNumber', {
        params: {
          carNumber: this.caraccess.carNumber
        }
      }).then((response) => {
        // console.log(response.data)
        this.tableData = response.data
        // 用list接收这个数据;
      }).catch((error) => { // 接收错误
        console.log(error)
      })
    },
    handleFilter: function() {
      this.search()
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
