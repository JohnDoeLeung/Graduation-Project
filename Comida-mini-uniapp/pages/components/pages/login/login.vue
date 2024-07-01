<template>
  <layout>
    <uv-navbar :fixed="false" :title="title" left-arrow @leftClick="$onClickLeft" />
    <view class="wrap">
      <view class="top"></view>
      <view class="content">
        <view class="title">欢迎登录</view>
        
        <input class="u-border-bottom" style="height: 100rpx" type="number" v-model="mobile" placeholder="请输入注册时的手机号" />

        <input class="u-border-bottom" type="password" v-model="password" placeholder="请输入密码" />

        <button @click="submit" style="color: #fff; background-color: #f9ae3d" type="primary" class="login">
          登录
        </button>
		
		<button @click="navgateToRegister">注册</button>
	
      </view>
	  
      <view class="buttom">
        <view class="loginType">
<!--          #ifdef MP-WEIXIN -->
          <button type="primary" size="default" class="login-btn" open-type="getPhoneNumber"
            @getphonenumber="loginForWechatMini">
            <image src="/static/images/mine/wechat.png"></image>
            手机号快捷登录
          </button>
					<!-- #endif -->
        </view>
        <view class="hint">
          <!-- 	<label class="label"> -->
          <radio value="isChecked" @tap.stop="onChange" />
          我已经阅读并遵守
          <text class="link" @tap="serv(29, '用户协议')">《用户协议》</text>与
          <text class="link" @tap="serv(30, '隐私政策')">《隐私政策》</text>
          <!-- 	</label> -->
        </view>
      </view>
      <uv-toast ref="uToast"></uv-toast>
    </view>
  </layout>
</template>

<script setup>
import { ref, computed } from "vue";
import { onLoad } from "@dcloudio/uni-app";
import { useMainStore } from "@/store/store";
import { userLoginForWechatMini, loginByTel } from "@/api/auth";
import * as util from "@/utils/util";
import { mobile as testMobible } from "@/uni_modules/uv-ui-tools/libs/function/test";
const main = useMainStore();
const title = ref("");
const mobile = ref("17011960827");
const password = ref("123456");
const isChecked = ref(false);
const openid = ref(main.openid);
const uToast = ref();

const navgateToRegister = () =>{
	uni.navigateTo({
		url: '/pages/components/pages/register/register'
	})
}

const loginForWechatMini = async (e) => {
  if (!isChecked.value) {
    uToast.value.show({
      message: "请勾选下面协议",
      type: "error",
    });
    return;
  }
  if (e.detail.encryptedData && e.detail.iv) {
    let data = await userLoginForWechatMini({
      encryptedData: e.detail.encryptedData,
      iv: e.detail.iv,
      openid: openid.value,
    });
    if (data) {
      main.SET_MEMBER(data.userInfo);
      main.SET_TOKEN(data.accessToken);
	  console.log('1111111',data.userInfo)
      uToast.value.show({
        title: "登录成功",
        type: "success",
      });
      setTimeout(function () {
        uni.navigateBack();
      }, 2000);
    }
  }
};

// 提交
const submit = () => {
  if (testMobible(mobile.value) == false) {
    uToast.value.show({
      message: "手机号码格式不对",
      type: "error",
    });
    return;
  }

  if (!isChecked.value) {
    uToast.value.show({
      message: "请勾选下面协议",
      type: "error",
    });
    return;
  }

  login();
};

// 登录
const login = async () => {
  let from = "routine";
  // #ifdef H5
  from = "h5";
  if (util.isWeixin()) {
    from = "wechat";
  }

  // #endif
  let data = await loginByTel({
    mobile: mobile.value,
    password: password.value,
  });
  if (data) {
    console.log('登录数据',data);
    main.SET_MEMBER(data.userInfo);
	console.log('userInfo',data.userInfo)
    main.SET_TOKEN(data.accessToken);
	console.log('userInfo',data.accessToken)
	
    uni.setStorage({
      key: "userinfo",
      data: data.userInfo,
    });
    uni.setStorage({
      key: "accessToken",
      data: data.accessToken,
    });
    uToast.value.show({
      message: "登录成功",
      type: "success",
    });
    setTimeout(function () {
      uni.navigateBack({
		  delta:1
	  });
    }, 2000);
  }
};

const serv = (id, name) => {
  uni.navigateTo({
    url: "/pages/components/pages/mine/content?id=" + id + "&name=" + name,
  });
};

const onChange = () => {
  isChecked.value = !isChecked.value;
};
</script>

<style lang="scss" scoped>
.wrap {
  background-color: #ffffff;
  font-size: 28rpx;
  position: relative;
  height: 100%;

  .content {
    width: 600rpx;
    margin: 0 auto;

    .title {
      text-align: center;
      font-size: 60rpx;
      font-weight: 500;
      margin-bottom: 100rpx;
    }

    input {
      text-align: left;
      margin-bottom: 10rpx;
      padding-bottom: 6rpx;
    }

    .tips {
      color: $uv-info;
      margin-bottom: 60rpx;
      margin-top: 8rpx;
    }

    .getCaptcha {
      background-color: rgb(253, 243, 208);
      color: $uv-tips-color;
      border: none;
      font-size: 30rpx;
      padding: 12rpx 0;

      &::after {
        border: none;
      }
    }

    .login {
      background-color: rgb(253, 243, 208);
      color: $uv-tips-color;
      border: none;
      font-size: 30rpx;
      padding: 12rpx 0;
      margin-top: 40rpx;

      &::after {
        border: none;
      }
    }

    .alternative {
      color: $uv-tips-color;
      display: flex;
      justify-content: space-between;
      margin-top: 30rpx;
    }
  }

  .buttom {
    //position: absolute;
    bottom: 0;

    //display: flex;
    //flex-direction: column;
    //align-items: center;
    //justify-content: center;
    .loginType {
      padding: 80rpx;
      //justify-content:space-between;

      .login-btn {
        background-color: #1aad19 !important;
        width: 100%;
        border-radius: 50rem !important;
        display: flex;
        align-items: center;
        justify-content: center;
        padding: 10rpx 0;
        text-align: center;

        image {
          width: 36rpx;
          height: 30rpx;
          margin-right: 10rpx;
          vertical-align: middle;
        }
      }
    }

    .hint {
      display: flex;
      align-items: center;
      justify-content: center;
      padding: 20rpx 40rpx;
      font-size: 20rpx;
      color: $uv-tips-color;

      .link {
        color: $uv-warning;
      }
    }
  }
}
</style>
