<template>
    <div style="display:flex;height: 100%">
        <!-- 侧边导航 -->
        <div style="width: 200px;">
            <el-menu
                :default-active="currentTag"
                class="el-space--vertical"
                mode="vertical"
                @select="changeView"
            >
                <el-menu-item index="1">
                    <el-icon><UserFilled /></el-icon>
                    <template #title>
                        <span>个人信息</span>
                    </template>
                </el-menu-item>
                <el-menu-item index="2">
                    <el-icon><Collection /></el-icon>
                    <template #title>我的收藏</template>
                </el-menu-item>
                <el-menu-item index="3">
                    <el-icon><Document /></el-icon>
                    <template #title>我的订单</template>
                </el-menu-item>
                <el-menu-item index="4">
                    <el-icon><VideoCameraFilled /></el-icon>
                    <template #title>退出</template>
                </el-menu-item>
            </el-menu>
        </div>
        <div>
            <el-backtop :bottom="100">
                <div
                    style="
                        height: 100%;
                        width: 100%;
                        background-color: var(--el-bg-color-overlay);
                        box-shadow: var(--el-box-shadow-lighter);
                        text-align: center;
                        line-height: 40px;
                        color: #1989fa;"
                >
                    UP
                </div>
            </el-backtop>
        </div>
        <!-- 内容区域 -->
        <div style="flex: 1; padding: 20px">
            <router-view />
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import {UserFilled, Collection, Document, VideoCameraFilled} from '@element-plus/icons-vue';

// 使用 Vue Router
const router = useRouter();
const route = useRoute();


// 当前选中的菜单项
const currentTag = ref('1');

// 选择视图时切换路由
const changeView = (index) => {
    currentTag.value = index;
    switch (index) {
        case '1':
            router.push({ name: 'userInfo'});
            break;
        case '2':
            router.push({name: 'userCollection'});
            break;
        case '3':
            router.push({name: 'userOrder'});
            break;
        default:
            router.push({
                name:'dashboard'
            })
            break;
    }
};

// 初始化页面时根据当前路由决定选中的菜单项
onMounted(() => {
    const path = route.path;
    if (path.includes('info')) {
        currentTag.value = '1';
    } else if (path.includes('collection')) {
        currentTag.value = '2';
    } else {
        currentTag.value = '3';
    }
});
</script>

<style scoped>
.el-space--vertical {
    width: 200px;
    min-height: 100%;
    border-right: 1px solid #e6e6e6;
}

</style>
