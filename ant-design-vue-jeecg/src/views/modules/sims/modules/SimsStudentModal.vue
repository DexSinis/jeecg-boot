<template>
  <a-modal
    :title="title"
    :width="800"
    :visible="visible"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">
    
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">
      
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="所在学院">
          <a-input placeholder="请输入所在学院" v-decorator="['collegeId', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="所在班级">
          <a-input placeholder="请输入所在班级" v-decorator="['classId', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="对接平台账号ID(如腾讯)">
          <a-input placeholder="请输入对接平台账号ID(如腾讯)" v-decorator="['simsid', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="对接平台密码(如腾讯)">
          <a-input placeholder="请输入对接平台密码(如腾讯)" v-decorator="['simsPassword', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="学生姓名">
          <a-input placeholder="请输入学生姓名" v-decorator="['name', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="身份证号">
          <a-input placeholder="请输入身份证号" v-decorator="['idCardNo', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="英文名">
          <a-input placeholder="请输入英文名" v-decorator="['engName', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="手机号">
          <a-input placeholder="请输入手机号" v-decorator="['mobilePhone', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="密码">
          <a-input placeholder="请输入密码" v-decorator="['password', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="性别">
          <a-input placeholder="请输入性别" v-decorator="['gender', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="出生日期">
          <a-date-picker v-decorator="[ 'birth', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="头像">
          <a-input placeholder="请输入头像" v-decorator="['avatar', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="身高">
          <a-input-number v-decorator="[ 'height', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="体重">
          <a-input-number v-decorator="[ 'weight', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="名族">
          <a-input placeholder="请输入名族" v-decorator="['nation', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="政治面貌">
          <a-input placeholder="请输入政治面貌" v-decorator="['political', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="婚姻状况">
          <a-input placeholder="请输入婚姻状况" v-decorator="['marital', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="籍贯（省） 国标行政区域代码-省级">
          <a-input placeholder="请输入籍贯（省） 国标行政区域代码-省级" v-decorator="['domicilePlaceProvince', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="籍贯（市） 国标行政区域代码-市级">
          <a-input placeholder="请输入籍贯（市） 国标行政区域代码-市级" v-decorator="['domicilePlaceCity', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="户籍地址">
          <a-input placeholder="请输入户籍地址" v-decorator="['domicilePlaceAddress', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="爱好">
          <a-input placeholder="请输入爱好" v-decorator="['hobby', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="简要介绍">
          <a-input placeholder="请输入简要介绍" v-decorator="['intro', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="居住地址">
          <a-input placeholder="请输入居住地址" v-decorator="['presentAddress', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="电子邮件">
          <a-input placeholder="请输入电子邮件" v-decorator="['email', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="入学日期">
          <a-date-picker v-decorator="[ 'entryDate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="状态">
          <a-input placeholder="请输入状态" v-decorator="['status', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="online">
          <a-input-number v-decorator="[ 'online', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="token">
          <a-input placeholder="请输入token" v-decorator="['token', {}]" />
        </a-form-item>
		
      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>
  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import moment from "moment"

  export default {
    name: "SimsStudentModal",
    data () {
      return {
        title:"操作",
        visible: false,
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },

        confirmLoading: false,
        form: this.$form.createForm(this),
        validatorRules:{
        },
        url: {
          add: "/sims/simsStudent/add",
          edit: "/sims/simsStudent/edit",
        },
      }
    },
    created () {
    },
    methods: {
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'collegeId','classId','simsid','simsPassword','name','idCardNo','engName','mobilePhone','password','gender','avatar','height','weight','nation','political','marital','domicilePlaceProvince','domicilePlaceCity','domicilePlaceAddress','hobby','intro','presentAddress','email','status','online','token'))
		  //时间格式化
          this.form.setFieldsValue({birth:this.model.birth?moment(this.model.birth):null})
          this.form.setFieldsValue({entryDate:this.model.entryDate?moment(this.model.entryDate):null})
        });

      },
      close () {
        this.$emit('close');
        this.visible = false;
      },
      handleOk () {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            let formData = Object.assign(this.model, values);
            //时间格式化
            formData.birth = formData.birth?formData.birth.format():null;
            formData.entryDate = formData.entryDate?formData.entryDate.format():null;
            
            console.log(formData)
            httpAction(httpurl,formData,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
              that.close();
            })



          }
        })
      },
      handleCancel () {
        this.close()
      },


    }
  }
</script>

<style lang="less" scoped>

</style>