<template>
  <view class="container">
    <view class="content">
      <image
        src="/static/images/scan_code.png"
        mode="widthFix"
        class="item"
      ></image>
      <button
        type="primary"
        @click="ScanQRCode"
        class="custom-button"
        hover-class="none"
      >
        {{ title }}
      </button>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      title: "扫码",
    };
  },
  onLoad() {
    this.ScanQRCode();
  },
  onShow() {},
  methods: {
    // 判断是否是数字
    isNumber(str) {
      return /^\d+$/.test(str);
    },

    ScanQRCode() {
      wx.scanCode({
        onlyFromCamera: false,
        scanType: [],
        success: (res) => {
          // console.log('成功扫码',this.isNumber(res.result))
          // console.log(res)
          if (this.isNumber(res.result)) {
            // uni.setStorageSync("tabNum", res.result)
            uni.navigateTo({
              url: '../../../components/pages/pay/pay'
            })
          }
        },
      });
    },
  },
};
</script>

<style lang="scss" scoped>
page {
  max-height: 100%;
}

.content {
  position: relative;
  align-items: center;
  margin-top: 150rpx;
}

.item {
  display: flex;
  margin: 0 auto;
}

.custom-button {
  width: 80%;
  margin: 0 auto;
  background-color: #fe6634;
  color: #ffffff;
  border: 1px solid #ccc;
  border-radius: 50rpx;
  font-size: 15px;
  font-weight: bolder;
  padding: 8px 16px;
}
</style>
