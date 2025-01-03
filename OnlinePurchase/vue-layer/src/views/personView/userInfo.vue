
<template>
    <div v-if="user">
        <el-row>
            <el-col :span="4"></el-col>
            <el-col :span="16">
                <el-card class="user-card">
                    <div class="card-header">
                        <el-avatar
                            :size="125"
                            :src="user.avatarUrl"
                            class="avatar"
                        />
                        <h1 class="nickname">{{ user.nickname }}</h1>
                    </div>
                    <div class="user-info">
                        <div>账号: {{ user.username }}</div>
                        <div>电话: {{ user.phoneNumber }}</div>
                        <div>邮箱: {{ user.email }}</div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script setup>
/* eslint-disable */
import { ElCard, ElAvatar, ElRow, ElCol, ElTag, ElButton, ElDivider } from 'element-plus';
import {useUserStore} from "@/store/userStore";

const state = useUserStore()
const user = state.getUser;

</script>

<style scoped>
.user-card {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    border-radius: 8px;
    background-color: #fff;
}

.card-header {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-bottom: 20px;
}

.avatar {
    margin-bottom: 10px;
}

.nickname {
    font-size: 24px;
    margin: 0;
    color: #333;
}

.user-info {
    text-align: center;
    margin-top: 20px;
}

.user-info div {
    margin: 10px 0;
    font-size: 16px;
    color: #666;
}
</style>

