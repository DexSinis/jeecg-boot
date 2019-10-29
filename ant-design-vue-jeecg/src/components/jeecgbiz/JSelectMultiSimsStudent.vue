<template>
  <j-select-biz-component
    :value="value"

    name="用户"
    displayKey="name"

    :returnKeys="returnKeys"
    :listUrl="url.list"
    :columns="columns"
    queryParamText="账号"

    v-on="$listeners"
    v-bind="$attrs"
  />
</template>

<script>
  import JSelectBizComponent from './JSelectBizComponent'

  export default {
    name: 'JSelectMultiSimsStudent',
    components: { JSelectBizComponent },
    props: ['value'],
    data() {
      return {
        returnKeys: ['id', 'name'],
        url: { list: '/sims/simsStudent/list' },
        columns: [
          { title: '编号', align: 'center', width: 100, dataIndex: 'id' },
          { title: '姓名', align: 'center', width: 100, dataIndex: 'name' },
          { title: '电话', align: 'center', width: 100, dataIndex: 'mobilePhone' }
        ]
      }
    }
  }
</script>

<style lang="scss" scoped></style>