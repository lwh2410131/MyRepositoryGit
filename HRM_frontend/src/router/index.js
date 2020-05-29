import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import ElementUI from 'element-ui';

import 'element-ui/lib/theme-chalk/index.css';

Vue.use(ElementUI);
Vue.use(Router)

let menu=[
    {
    path:'/main',
    name:'Main',
    component:()=>import('@/views/layout/Layout'),
    meta:{
      title:'首页',
      icon:'el-icon-info',
      menu:true,
      funcNode:'1'
    },
    children:[
      {
        path:'/main/Personal',
        name:'Personal',
        component:()=>import('@/views/main/Personal'),
        meta:{
          title:'账号信息',
          icon:'el-icon-view',
          menu:true,
          funcNode:'1-1'
        }
      }
    ]
  },
  //1栏
  {
    path:'/attence',
    name:'Attence',
    component:()=>import('@/views/layout/Layout'),
    meta:{
      title:'考勤绩效管理',
      icon:'el-icon-date',
      menu:true,
      funcNode:'2'
    },
    children:[
      {
        path:'/attence/attenceCheck',
        name:'AttenceCheck',
        component:()=>import('@/views/attence/attenceCheck'),
        meta:{
          title:'员工绩效考核',
          icon:'el-icon-message',
          menu:true,
          funcNode:'2-1'
        }
      }
    ]
  },
  //2栏
  {
    path:'/role',
    name:'Role',
    component:()=>import('@/views/layout/Layout'),
    meta:{
      title:'人事管理',
      icon:'el-icon-user',
      menu:true,
      funcNode:'3'
    },
    children:[
      {
        path:'/role/entryManagement',
        name:'EntryManagement',
        component:()=>import('@/views/role/EntryManagement'),
        meta:{
          title:'入职申请管理',
          icon:'el-icon-notebook-1',
          menu:true,
          funcNode:'3-1'
        }
      },
      {
        path:'/role/depManagement',
        name:'DepManagement',
        component:()=>import('@/views/role/DepManagement'),
        meta:{
          title:'部门管理',
          icon:'el-icon-notebook-1',
          menu:true,
          funcNode:'3-2'
        }
      },
      {
        path:'/role/empManagement',
        name:'EmpManagement',
        component:()=>import('@/views/role/EmpManagement'),
        meta:{
          title:'员工入职申请',
          icon:'el-icon-notebook-1',
          menu:true,
          funcNode:'3-3'
        }
      }
    ]
  },
  //3栏
  {
    path:'/salary',
    name:'Salary',
    component:()=>import('@/views/layout/Layout'),
    meta:{
      title:'薪酬管理',
      icon:'el-icon-star-off',
      menu:true,
      funcNode:'4'
    },
    children:[
      {
        path:'/salary/salaryPayment',
        name:'SalaryPayment',
        component:()=>import('@/views/salary/SalaryPayment'),
        meta:{
          title:'员工薪酬发放',
          icon:'el-icon-news',
          menu:true,
          funcNode:'4-1'
        }
      }
    ]
  },
  //4栏
  {
    path:'/employee',
    name:'Employee',
    component:()=>import('@/views/layout/Layout'),
    meta:{
      title:'员工信息管理',
      icon:'el-icon-setting',
      menu:true,
      funcNode:'5'
    },
    children:[
      {
        path:'/employee/empInfo',
        name:'EmpInfo',
        component:()=>import('@/views/employee/EmpInfo'),
        meta:{
          title:'员工基本信息',
          icon:'el-icon-notebook-1',
          menu:true,
          funcNode:'5-1'
        }
      }
    ]
  },
  // {
  //   path:'/setting',
  //   name:'Setting',
  //   component:()=>import('@/views/layout/Layout'),
  //   meta:{
  //     title:'测试页面',
  //     icon:'el-icon-setting',
  //     menu:true,
  //     funcNode:'6'
  //   },
  //   children:[
  //     {
  //       path:'/setting/test',
  //       name:'Test',
  //       component:()=>import('@/views/default'),
  //       meta:{
  //         title:'测试',
  //         icon:'el-icon-notebook-1',
  //         menu:true,
  //         funcNode:'6-1'
  //       }
  //     }
  //   ]
  // },
  //登录栏
  {
    path:'/',
    redirect:'login',
    meta:{
      menu:false
    }
  },
  {
    path:'/login',
    name:'Login',
    component:()=>import('@/views/login/Login'),
    meta:{
      menu:false
    }
  }
]


export default new Router({
  routes: menu
})
