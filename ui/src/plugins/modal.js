import {
  Message,
  MessageBox,
  Notification,
  Loading
} from 'element-ui'

let loadingInstance;

export default {
  // 消息提示
  msg(message) {
    Message.info({
      message,
      showClose: true
    })
  },
  // 错误消息
  msgError(message) {
    Message.error({
      message,
      showClose: true
    })
  },
  // 成功消息
  msgSuccess(message) {
    Message.success({
      message,
      showClose: true
    })
  },
  // 警告消息
  msgWarning(message) {
    Message.warning({
      message,
      showClose: true
    })
  },
  // 弹出提示
  alert(content) {
    MessageBox.alert(content, "系统提示")
  },
  // 错误提示
  alertError(content) {
    MessageBox.alert(content, "系统提示", {
      type: 'error'
    })
  },
  // 成功提示
  alertSuccess(content) {
    MessageBox.alert(content, "系统提示", {
      type: 'success'
    })
  },
  // 警告提示
  alertWarning(content) {
    MessageBox.alert(content, "系统提示", {
      type: 'warning'
    })
  },
  // 通知提示
  notify(content) {
    Notification.info(content)
  },
  // 错误通知
  notifyError(content) {
    Notification.error("123");
  },
  // 成功通知
  notifySuccess(content) {
    Notification.success(content)
  },
  // 警告通知
  notifyWarning(content) {
    Notification.warning(content)
  },
  // 确认窗体
  confirm(content) {
    return MessageBox.confirm(content, "系统提示", {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: "warning",
    })
  },
  // 提交内容
  prompt(content) {
    return MessageBox.prompt(content, "系统提示", {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: "warning",
    })
  },
  // 打开遮罩层
  loading(content) {
    loadingInstance = Loading.service({
      lock: true,
      text: content,
      spinner: "el-icon-loading",
      background: "rgba(0, 0, 0, 0.7)",
    })
  },
  // 关闭遮罩层
  closeLoading() {
    loadingInstance.close();
  }
}
