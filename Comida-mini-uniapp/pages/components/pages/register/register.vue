<template>
  <view class="container">
    <view class="input-group">
      <text>用户名</text>
      <input v-model="username" placeholder="请输入用户名" />
    </view>
    <view class="input-group">
      <text>密码</text>
      <input type="password" v-model="password" placeholder="请输入密码" />
    </view>
    <view class="input-group">
      <text>昵称</text>
      <input v-model="nickname" placeholder="请输入昵称" />
    </view>
    <view class="input-group">
      <text>手机号</text>
      <input type="number" v-model="mobile" placeholder="请输入手机号" />
    </view>
    <button @click="submit" type="primary">注册</button>
    <view>{{ username }}, {{ password }}, {{ nickname }}, {{ mobile }}</view>
  </view>
</template>

<script setup>
import { ref } from "vue";
import { useMainStore } from "@/store/store";
import { register } from "@/api/register";
import { mobile as testMobile } from "@/uni_modules/uv-ui-tools/libs/function/test";

const main = useMainStore();

const username = ref("test");
const password = ref("123456");
const nickname = ref("测试");
const mobile = ref("15229473657");

const submit = () => {
  if (!testMobile(mobile.value)) {
    uni.showToast({
      title: "手机号有误",
      icon: "error",
    });
    return;
  }
  handleRegister();
};

const handleRegister = async () => {
  try {
    let data = await register({
      username: username.value,
      password: password.value,
      nickname: nickname.value,
      mobile: mobile.value,
    });
    if (data) {
      uni.showToast({
        title: "注册成功",
        icon: "success",
      });
	  uni.navigateBack({
		  delta:1
	  });
    } else {
      uni.showToast({
        title: "请重试",
        icon: "none",
      });
    }
  } catch (error) {
    uni.showToast({
      title: "注册失败",
      icon: "none",
    });
  }
};
</script>

<style scoped>
.container {
  padding: 20px;
  max-width: 400px;
  margin: 0 auto;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.input-group {
  margin-bottom: 30px;
  height: 150rpx;
  
}

.input-group text {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

input {
  width: 100%;
  height: 100rpx;
  padding: 0 10px;
  border: 1px solid #ccc;
  border-radius: 15px;
  box-sizing: border-box;
  line-height: 100rpx;
}

.submit-button {
  width: 100%;
  padding: 10px;
  background-color: #007aff;
  color: white;
  border: none;
  border-radius: 4px;
  font-size: 16px;
  cursor: pointer;
  margin-top: 10px;
}

.submit-button:hover {
  background-color: #005bb5;
}

.debug-info {
  margin-top: 20px;
  font-size: 14px;
  color: #333;
  background-color: #eee;
  padding: 10px;
  border-radius: 4px;
}
</style>
