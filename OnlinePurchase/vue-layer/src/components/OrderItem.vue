<template>
    <el-card class="order-card" shadow="always" style="width: 100%; margin-bottom: 20px;">
        <template #header>
            <div  class="card-header">
                <span>订单信息</span>
            </div>

        </template>

        <el-row class="order-info">
            <el-col :span="12" class="order-item">
                <span class="label">订单ID:</span>
                <span>{{ orderData.orderId }}</span>
            </el-col>

            <el-col :span="12" class="order-item">
                <span class="label">影城名称:</span>
                <span>{{ orderData.cinemaName }}</span>
            </el-col>

            <el-col :span="12" class="order-item">
                <span class="label">电影名称:</span>
                <span>{{ orderData.movieName }}</span>
            </el-col>

            <el-col :span="12" class="order-item">
                <span class="label">价格:</span>
                <span>{{ orderData.price }} 元</span>
            </el-col>

            <el-col :span="12" class="order-item">
                <span class="label">影厅号:</span>
                <span>{{ orderData.roomNumber }}</span>
            </el-col>

            <el-col :span="12" class="order-item">
                <span class="label">座位号:</span>
                <span>{{ orderData.seatId }}</span>
            </el-col>

            <el-col :span="12" class="order-item">
                <span class="label">放映时间:</span>
                <span>{{ formatDate(orderData.screeningTime) }}</span>
            </el-col>
            <el-col :span="12">
                <el-button @click="cancelOrder">退票</el-button>
            </el-col>
        </el-row>
    </el-card>
</template>

<script setup>
/* eslint-disable */
import {nextTick, ref} from 'vue';
import {ElCard, ElRow, ElCol, ElMessage} from 'element-plus';
import {postOrderInfo} from "@/api";

let timer
const delay = 500
const emits = defineEmits(['cancelOrder'])
const props = defineProps(['orderData'])
const orderData = ref(props.orderData);


const cancelOrder = () =>{

    clearTimeout(timer)

    timer = setTimeout(() => {
        const data = {orderId: orderData.value.orderId}
        postOrderInfo(data)
            .then(res => res.data)
            .then(res =>{
                console.log(res)
                if (res.code === 200){
                    emits('cancelOrder',data.orderId)
                    nextTick(() =>{
                        ElMessage.success("退票成功")
                    })
                }else{
                    ElMessage.error("退票失败")
                }
            })
            .catch(() => ElMessage.error("退票失败"))
    },delay)
}


// 格式化时间
const formatDate = (dateString) => {
    const date = new Date(dateString);
    return date.toLocaleString(); // 将日期格式化为本地时间
};
</script>

<style scoped>
.order-card {
    max-width: 600px;
    margin: 20px auto;
}

.card-header {
    font-size: 18px;
    font-weight: bold;
}

.order-info {
    margin-top: 20px;
}

.order-item {
    margin-bottom: 10px;
}

.label {
    font-weight: bold;
    color: #333;
    margin-right: 10px;
}
</style>
