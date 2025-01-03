<template>
    <div class="movie-home">
        <el-row class="content" gutter="20">
            <el-col :span="2"/>
            <!-- 左侧：电影分类（上下排列） -->
            <el-col :span="14">
                <div class="movie-category">
                    <!-- 正在热映 -->
                    <el-card class="movie-card" shadow="hover" style="width: 100%; margin-bottom: 20px" v-if="hotMovies">
                        <div class="card-header">
                            <span class="category-title">正在热映</span>
                            <router-link :to="{name:'movieList',query:{bar:'hotScreening'}}" style="float: right">全部</router-link>
                        </div>
                        <div class="movie-list">
                            <el-row :gutter="20">
                                <el-col v-for="(movie, index) in hotMovies" :key="index" :span="8">
                                    <el-card class="movie-item" shadow="hover">
                                        <img :src="movie.posterUrl" alt="movie poster" class="movie-poster" />
                                        <div class="movie-info">
                                            <div class="movie-name">
                                                <router-link :to="{name:'movieDetail', query:{movieId:movie.movieId}}">
                                                    {{ movie.title }}
                                                </router-link>
                                            </div>
                                            <div class="movie-rating">{{ movie.rating===0?'暂无评分' : movie.rating }}</div>
                                            <el-button  color="red" @click="switch2Purchase(movie.movieId)">购票</el-button>
                                        </div>
                                    </el-card>
                                </el-col>
                            </el-row>
                        </div>
                    </el-card>

                    <!-- 即将上映 -->
                    <el-card class="movie-card" shadow="hover" style="width: 100%" v-if="upComingMovies">
                        <div class="card-header">
                            <span class="category-title">即将上映</span>
                            <router-link :to="{name:'movieList',query:{bar:'upComing'}}" style="float: right">全部</router-link>
                        </div>
                        <div class="movie-list">
                            <el-row :gutter="20">
                                <el-col v-for="(movie, index) in upComingMovies" :key="index" :span="8">
                                    <el-card class="movie-item" shadow="hover">
                                        <img :src="movie.posterUrl" alt="movie poster" class="movie-poster" />
                                        <div class="movie-info">


                                            <div class="movie-name">
                                                <router-link :to="{name:'movieDetail', query:{movieId:movie.movieId}}">
                                                    {{ movie.title }}
                                                </router-link>
                                            </div>
                                            <div class="movie-releaseDate">{{ movie.releaseDate.substr(0,10) }}</div>

                                        </div>
                                    </el-card>
                                </el-col>
                            </el-row>
                        </div>
                    </el-card>
                </div>
            </el-col>
            <el-col :span="1"/>
            <!-- 右侧：电影排行榜 -->
            <el-col :span="5">
                <el-card class="movie-rank" shadow="hover" v-if="rankedMovies">
                    <div class="card-header">
                        <span class="rank-title">电影排行榜</span>
                    </div>
                    <el-list>
                        <el-list-item v-for="(movie, index) in rankedMovies" :key="index" class="rank-item">
                            <div class="rank-number">{{ index + 1 }}</div>
                            <div class="rank-movie">
                                <img :src="movie.posterUrl" alt="movie poster" class="rank-poster" />
                                <div class="rank-info">
                                    <div class="rank-name">
                                        <router-link :to="{name:'movieDetail', query:{movieId:movie.movieId}}">
                                            {{ movie.title }}
                                        </router-link>
                                    </div>
                                    <div class="rank-rating">{{ movie.rating }}</div>
                                </div>
                            </div>
                        </el-list-item>
                    </el-list>
                </el-card>
            </el-col>
            <el-col :span="2"/>
        </el-row>
    </div>
</template>

<script setup>
/* eslint-disable */
import {onBeforeMount, reactive, ref} from "vue";
import {getMovieList} from "@/api";
import {useRouter} from "vue-router";

const router = useRouter()
const hotMovies =ref(null)
const upComingMovies = ref(null)
const rankedMovies = ref(null)
const pageSize = 9;


const switch2Purchase = (movieId) =>{
    router.push({
        name:'cinemaList',
        query:{
            movieId:movieId
        }
    })
}

onBeforeMount(() =>{
    const queryHot = {
        bar:'hotScreening',
        type:'全部',
        region:'全部',
        year:'全部',
        page:1,
        size:pageSize
    }
    const queryUpcoming = {
        bar:'upComing',
        type:'全部',
        region:'全部',
        year:'全部',
        page:1,
        size:pageSize
    }
    const queryRank = {
        bar:'hot',
        type:'全部',
        region:'全部',
        year:'全部',
        page:1,
        size:100 //假设当前热映电影不超过100
    }
    getMovieList(queryHot)
        .then(res => res.data)
        .then(res => {
            hotMovies.value = res.data
        })
    getMovieList(queryUpcoming)
        .then(res => res.data)
        .then(res => {
            upComingMovies.value = res.data
            console.log(res.data)
        })
    getMovieList(queryRank)
        .then(res => res.data)
        .then(res => {
            rankedMovies.value = res.data
            rankedMovies.value = rankedMovies.value.sort((a,b) => b.rating - a.rating).slice(0,20)
        })

})




</script>

<style scoped>
.movie-home {
    padding: 20px;
    background-color: #f9f9f9;
}

.category-title {
    font-size: 18px;
    font-weight: bold;
    margin-bottom: 10px;
}

.movie-card {
    padding: 20px;
}

.movie-list {
    margin-top: 10px;
}
.movie-releaseDate{
    color: #666666;
}
.movie-item {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 10px;
}
a{
    text-decoration: none;
    color: black;
}
a:hover{
    text-decoration: underline;
}
.movie-poster {
    width: 100%;
    height: 200px;
    object-fit: cover;
    border-radius: 8px;
}

.movie-info {
    margin-top: 10px;
    text-align: center;
}

.movie-name {
    font-size: 16px;
    font-weight: bold;
}

.movie-rating {
    color: #f34d41;
}

.rank-title {
    font-size: 18px;
    font-weight: bold;
}

.movie-rank {
    padding: 20px;
}

.rank-item {
    display: flex;
    align-items: center;
    margin-bottom: 10px;
}

.rank-number {
    font-size: 18px;
    font-weight: bold;
    width: 30px;
    margin-right: 10px;
}

.rank-movie {
    display: flex;
    align-items: center;
}

.rank-poster {
    width: 50px;
    height: 75px;
    object-fit: cover;
    border-radius: 5px;
    margin-right: 10px;
}

.rank-info {
    font-size: 14px;
}

.rank-name {
    font-weight: bold;
}

.rank-rating {
    color: #f34d41;
}
</style>
