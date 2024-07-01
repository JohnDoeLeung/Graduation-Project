<template>
	<view class="container">
		<view class="banner">
			<swiper indicator-dots circular autoplay class="banner-swiper" :interval="5000" :duration="1000">
				<swiper-item class="banner-swiper-item" v-for="(item, index) in listAds" :key="index">
					<image :src="item.image" mode="widthFix"></image>
				</swiper-item>
			</swiper>
		</view>

		<view class="content">
			<!-- section-1 begin -->
			<view class="section-1">
				<navigator class="item" open-type="switchTab" url="/pages/menu/menu" hover-class="none" @click="takein">
					<image src="/static/images/index/order.png" mode="widthFix"></image>
					<view class="wenyue-font">堂食点餐</view>
					<view class="text-color-assist">下单免排队</view>
				</navigator>
				<navigator class="item" open-type="switchTab" url="/pages/menu/menu" hover-class="none" @tap="takeout">
					<image src="/static/images//index//takeout.png" mode="widthFix"></image>
					<view class="wenyue-font">外卖配送</view>
					<view class="text-color-assist">即刻达</view>
				</navigator>
			</view>
			<!-- section-1 end -->
			<!-- section-2 begin -->
			<view class="section-2">
				<navigator class="my-integral" hover-class="none">
					<view class="integrals">
						<view>我的卡券</view>
						<view class="neutra-font">{{ member.couponCount }}</view>
					</view>
					<view class="tips"> 可抵扣商品价格哦 </view>
				</navigator>
				<navigator class="my-code" hover-class="none">
					<image src="/static/images/index/coupon.png"></image>
					<view @click="coupons">去领个券</view>
				</navigator>
			</view>
			<!-- section-2 end -->
			<!-- section-3 begin -->
			<view class="section-3">
				<view class="intro">
					<view class="greet">您好，{{ isLogin ? member.nickname : "客官" }}</view>
					<view class="note">欢迎使用！</view>
				</view>
			</view>
			<!-- section-3 end -->
		</view>
	</view>
</template>

<script setup>
	import {
		ref
	} from "vue";
	import {
		onLoad
	} from "@dcloudio/uni-app";
	import {
		menuAds
	} from "@/api/market";
	import {
		storeToRefs
	} from "pinia";
	import {
		useMainStore
	} from "@/store/store";
	//
	const main = useMainStore();
	const {
		member,
		store,
		isLogin
	} = storeToRefs(main);
	//const store = ref(main.store)
	const listAds = ref([]);
	// const isLogin = ref(main.isLogin)

	const handGetListAds = async () => {
		let shop_id = store.id ? store.id : 0;
		let data = await menuAds({
			shop_id: shop_id,
		});
		if (data) {
			listAds.value = data.list;
			console.log("listAds:", listAds.value);
			uni.setStorage({
				key: "isActive",
				data: data.isActive,
			});
			if (data.list.length > 0) {
				uni.setStorage({
					key: "shopAd",
					data: data.list[0].image,
				});
			}
		}
	};

	const takein = () => {
		main.SET_ORDER_TYPE("takein");
		// uni.switchTab({
		//   url: "/pages/menu/menu",
		// });
	};

	const takeout = () => {
		main.SET_ORDER_TYPE("takeout");
		// uni.switchTab({
		//   url: "/pages/menu/menu",
		// });
	};

	const coupons = () => {
		console.log("--> % orderType:\n", main.orderType);
		console.log("--> % isLogin:\n", main.isLogin);
		if (!main.isLogin) {
			uni.navigateTo({
				url: "/pages/components/pages/login/login"
			});
			return;
		}
		uni.navigateTo({
			url: "/pages/components/pages/coupons/coupons",
		});
	};

	const goScore = () => {
		uni.navigateTo({
			url: "/pages/components/pages/scoreproduct/list",
		});
	};

	onLoad(() => {
		// main.init()
		handGetListAds();
	});
</script>

<style lang="scss" scoped>
	page {
		max-height: 100%;
	}

	.banner-swiper {
		width: 100%;
		height: 600rpx;

		.banner-swiper-item {
			image {
				width: 100%;
			}
		}
	}

	.content {
		width: 100%;
		padding: 0 30rpx;
		position: relative;
	}

	.section-1 {
		margin-top: -35rpx;
		display: flex;
		justify-content: space-around;
		padding: 60rpx 0;
		background-color: #ffffff;
		border-radius: 10px;
		box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
		margin-bottom: 20px;

		.item {
			flex: 1;
			flex-shrink: 0;
			display: flex;
			flex-direction: column;
			align-items: center;
			text-align: center;
			justify-content: center;
			background-color: #ffffff;
			padding: 15px;
			transition: transform 0.3s ease, box-shadow 0.3s ease;

			&:nth-child(1):after {
				content: "";
				position: absolute;
				right: 0;
				top: 0;
				bottom: 0;
				width: 2rpx;
				background-color: $border-color;
			}

			image {
				width: 100rpx;
				margin-bottom: 20rpx;
			}

			.wenyue-font {
				font-size: 48rpx;
				margin-bottom: 10rpx;
			}
		}
	}

	.section-2 {
		margin-bottom: 50rpx;
		display: flex;
		justify-content: space-between;
		align-items: center;
		font-size: $font-size-base;
		color: $text-color-assist;
		padding: 0 10rpx;

		.my-integral {
			flex: 1;
			display: flex;
			flex-direction: column;

			.integrals {
				display: flex;
				align-items: center;
				font-size: $font-size-lg;
				color: $text-color-base;
				margin-bottom: 10rpx;

				.neutra-font {
					margin-left: 10rpx;
					font-size: 42rpx;
				}
			}
		}

		.my-code {
			display: flex;
			flex-direction: column;
			align-items: center;
			padding: 0 30rpx;
			position: relative;

			image {
				width: 60rpx;
				height: 60rpx;
				margin-bottom: $spacing-col-sm;
			}
		}
	}

	.section-3 {
		display: flex;
		justify-content: space-between;

		.intro {
			position: absolute;
			left: 40rpx;
			color: #000000;
			display: flex;
			flex-direction: column;

			.greet {
				font-size: $font-size-lg;
				margin-bottom: 20rpx;
			}

			.note {
				font-size: $font-size-sm;
			}
		}
	}
</style>