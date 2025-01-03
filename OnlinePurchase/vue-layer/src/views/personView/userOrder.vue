
<template>
    <div>
        <el-row>
            <el-col :span="4"></el-col>
            <el-col :span="16">
                <div v-if="orderInfo">
                    <order-item
                        v-for="item of orderInfo"
                        :key="item.orderId"
                        :order-data="item"
                        @cancel-order="updateOrderInfo"
                    />
                </div>
               <div v-else>
                   <p>您还没有订单</p>
               </div>
            </el-col>
        </el-row>
    </div>
</template>
<script setup>
/* eslint-disable */

import {useUserStore} from "@/store/userStore";
import {onBeforeMount, ref} from "vue";
import {getOrderInfo, getOrderList} from "@/api";
import OrderItem from "@/components/OrderItem.vue";

const state = useUserStore()
const orderInfo = ref(null)

onBeforeMount(() =>{
    if (state.getOrder.length !== 0){
        getOrderList(state.getOrder)
            .then(res => res.data)
            .then(res =>{
                orderInfo.value = res.data
            })
    }
})

const updateOrderInfo = (id) =>{
    state.removeOrder(id)
    orderInfo.value = orderInfo.value.filter(item => item.orderId !== id);
    console.log(orderInfo.value)
}

</script>
<style scoped>

</style>