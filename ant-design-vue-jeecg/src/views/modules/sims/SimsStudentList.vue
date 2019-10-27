<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">

          <!--<a-col :md="6" :sm="8">-->
            <!--<a-form-item label="所在学院">-->
              <!--<a-input placeholder="请输入所在学院" v-model="queryParam.collegeId"></a-input>-->
            <!--</a-form-item>-->
          <!--</a-col>-->
          <!--<a-col :md="6" :sm="8">-->
            <!--<a-form-item label="所在班级">-->
              <!--<a-input placeholder="请输入所在班级" v-model="queryParam.classId"></a-input>-->
            <!--</a-form-item>-->
          <!--</a-col>-->


        <!--<template v-if="toggleSearchStatus">-->
        <!--<a-col :md="6" :sm="8">-->
            <!--<a-form-item label="对接平台账号ID(如腾讯)">-->
              <!--<a-input placeholder="请输入对接平台账号ID(如腾讯)" v-model="queryParam.simsid"></a-input>-->
            <!--</a-form-item>-->
          <!--</a-col>-->
          <!--<a-col :md="6" :sm="8">-->
            <!--<a-form-item label="对接平台密码(如腾讯)">-->
              <!--<a-input placeholder="请输入对接平台密码(如腾讯)" v-model="queryParam.simsPassword"></a-input>-->
            <!--</a-form-item>-->
          <!--</a-col>-->
          <a-col :md="6" :sm="8">
            <a-form-item label="学生姓名">
              <a-input placeholder="请输入学生姓名" v-model="queryParam.name"></a-input>
            </a-form-item>
          </a-col>

          <a-col :md="6" :sm="8">
            <a-form-item label="手机号">
              <a-input placeholder="请输入手机号" v-model="queryParam.mobilePhone"></a-input>
            </a-form-item>
          </a-col>

          <!--</template>-->
          <a-col :md="6" :sm="8" >
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
              <!--<a @click="handleToggleSearch" style="margin-left: 8px">-->
                <!--{{ toggleSearchStatus ? '收起' : '展开' }}-->
                <!--<a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>-->
              <!--</a>-->
            </span>
          </a-col>

        </a-row>
      </a-form>
    </div>

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('学生后台管理')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        @change="handleTableChange">

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>
    <!-- table区域-end -->

    <!-- 表单区域 -->
    <simsStudent-modal ref="modalForm" @ok="modalFormOk"></simsStudent-modal>
  </a-card>
</template>

<script>
  import SimsStudentModal from './modules/SimsStudentModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "SimsStudentList",
    mixins:[JeecgListMixin],
    components: {
      SimsStudentModal
    },
    data () {
      return {
        description: '学生后台管理管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
           },
      {
           title: '学生编号',
           align:"center",
           dataIndex: 'id'
          },
		   // {
       //      title: '所在学院',
       //      align:"center",
       //      dataIndex: 'collegeId'
       //     },
		   // {
       //      title: '所在班级',
       //      align:"center",
       //      dataIndex: 'classId'
       //     },
		   // {
       //      title: '对接平台账号ID(如腾讯)',
       //      align:"center",
       //      dataIndex: 'simsid'
       //     },
		   // {
       //      title: '对接平台密码(如腾讯)',
       //      align:"center",
       //      dataIndex: 'simsPassword'
       //     },
		   {
            title: '学生姓名',
            align:"center",
            dataIndex: 'name'
           },
		   {
            title: '身份证号',
            align:"center",
            dataIndex: 'idCardNo'
           },
		   {
            title: '英文名',
            align:"center",
            dataIndex: 'engName'
           },
		   {
            title: '手机号',
            align:"center",
            dataIndex: 'mobilePhone'
           },
		   {
            title: '密码',
            align:"center",
            dataIndex: 'password'
           },
		   {
            title: '性别',
            align:"center",
            dataIndex: 'gender'
           },
		   {
            title: '出生日期',
            align:"center",
            dataIndex: 'birth'
           },
		   // {
       //      title: '头像',
       //      align:"center",
       //      dataIndex: 'avatar'
       //     },
		   // {
       //      title: '身高',
       //      align:"center",
       //      dataIndex: 'height'
       //     },
		   // {
       //      title: '体重',
       //      align:"center",
       //      dataIndex: 'weight'
       //     },
		   // {
       //      title: '名族',
       //      align:"center",
       //      dataIndex: 'nation'
       //     },
		   // {
       //      title: '政治面貌',
       //      align:"center",
       //      dataIndex: 'political'
       //     },
		   // {
       //      title: '婚姻状况',
       //      align:"center",
       //      dataIndex: 'marital'
       //     },
		   // {
       //      title: '籍贯（省） 国标行政区域代码-省级',
       //      align:"center",
       //      dataIndex: 'domicilePlaceProvince'
       //     },
		   // {
       //      title: '籍贯（市） 国标行政区域代码-市级',
       //      align:"center",
       //      dataIndex: 'domicilePlaceCity'
       //     },
		   // {
       //      title: '户籍地址',
       //      align:"center",
       //      dataIndex: 'domicilePlaceAddress'
       //     },
		   // {
       //      title: '爱好',
       //      align:"center",
       //      dataIndex: 'hobby'
       //     },
		   {
            title: '简要介绍',
            align:"center",
            dataIndex: 'intro'
           },
		   {
            title: '居住地址',
            align:"center",
            dataIndex: 'presentAddress'
           },
		   {
            title: '电子邮件',
            align:"center",
            dataIndex: 'email'
           },
		   // {
       //      title: '入学日期',
       //      align:"center",
       //      dataIndex: 'entryDate'
       //     },
		   // {
       //      title: '状态',
       //      align:"center",
       //      dataIndex: 'status'
       //     },
		   {
            title: '在线',
            align:"center",
            dataIndex: 'online'
           },
		   // {
       //      title: 'token',
       //      align:"center",
       //      dataIndex: 'token'
       //     },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/sims/simsStudent/list",
          delete: "/sims/simsStudent/delete",
          deleteBatch: "/sims/simsStudent/deleteBatch",
          exportXlsUrl: "sims/simsStudent/exportXls",
          importExcelUrl: "sims/simsStudent/importExcel",
       },
    }
  },
  computed: {
    importExcelUrl: function(){
      return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
    }
  },
    methods: {

    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>