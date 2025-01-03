<template>
    <el-card class="movie-card" shadow="always" style="width: 100%; margin-bottom: 20px;">
        <el-row class="movie-info" type="flex" justify="start" align="middle">
            <!-- 电影海报 -->
            <el-col :span="6" class="movie-poster">
                <img :src="movieData.posterUrl" alt="电影海报" style="width: 50%; height: auto; border-radius: 8px;">
            </el-col>

            <!-- 电影名称与评分（保持上下排列） -->
            <el-col :span="10" class="movie-details">
                <div class="movie-name">
                    <span class="label">电影名称：</span>
                    <router-link :to="{name:'movieDetail',query:{movieId:movieData.movieId}}"><span>{{ movieData.movieName }}</span></router-link>
                </div>

                <div class="movie-rating">
                    <span class="label">评分：</span>
                    <span>{{ movieData.rating===0 ? '暂无评分' : movieData.rating }}</span>
                </div>
            </el-col>

            <!-- 收藏按钮 -->
            <el-col :span="4" class="movie-collection">

                <el-button type="primary" style="width: 100%;" @click="handleAddToCollection">
                    <el-icon><StarFilled style="color: white"/></el-icon>
                    {{ movieData.collectionCount }}
                </el-button>
            </el-col>
        </el-row>
    </el-card>
</template>

<script setup>
/* eslint-disable */
import { ref } from 'vue';
import {StarFilled} from "@element-plus/icons-vue";
import {postCollectionInfo} from "@/api";

let timer
const delay = 500

// 接收父组件传递的电影数据
const props = defineProps({
    movieData: {
        type: Object,
        required: true
    },
    userId: {
        type: Number,
        required: true
    }
});
const emits = defineEmits(['cancel'])
// 处理点击加入收藏按钮的逻辑
const handleAddToCollection = () => {

    clearTimeout(timer)

    timer = setTimeout(() =>{
        const data ={
            userId: props.userId,
            movieId: props.movieData.movieId
        }
        postCollectionInfo(data)
            .then(res =>res.data)
            .then(res =>{
                if (res.msg.includes("删除")){
                    emits('cancel',props.movieData.movieId)

                }
            })
    },delay)
};
</script>

<style scoped>
.movie-card {
    max-width: 100%;
    margin: 20px auto;
}

.card-header {
    font-size: 18px;
    font-weight: bold;
}

.movie-info {
    margin-top: 20px;
}

.movie-poster {
    text-align: center;
}

.movie-details {
    padding-left: 20px;
    display: flex;
    flex-direction: column; /* 保持电影名称和评分上下排列 */
    justify-content: center; /* 在垂直方向居中对齐 */
}

.movie-name,
.movie-rating {
    margin-bottom: 10px;
    font-size: 25px;
}

.label {
    font-weight: bold;
    color: #333;
    margin-right: 10px;
}
a{
    text-decoration: none;
}
a:hover{
    text-decoration: underline;
}

.el-button {
    width: 100%;
}

/* Vertically center the content in el-col */
.movie-info .el-col {
    display: flex;
    align-items: center; /* 确保垂直对齐 */
}
</style>
