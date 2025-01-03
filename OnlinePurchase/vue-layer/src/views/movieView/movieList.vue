<script setup>
/* eslint-disable */

import {onBeforeMount, onMounted, reactive, ref} from "vue";
import {getMovieList} from "@/api";
const props = defineProps({
    'bar':{
       type: String,
        default:'hotScreening'
    },
    'type':{
        type:String,
        default:'全部'
    },
    'region':{
        type:String,
        default:'全部'
    },
    'year':{
        type:String,
        default:'全部'
    },
})

const currentPage = ref(1)
const pageSize = 30
const movieInfo = ref(null)

const fetchDefaultData = () =>{
    const movieQuery = {
        bar:props.bar,
        type:props.type,
        region:props.region,
        year:props.year,
        page:currentPage.value,
        size:pageSize
    }
    getMovieList(movieQuery)
        .then(res => res.data)
        .then(res =>{
            movieInfo.value = res.data
        })

}
onBeforeMount(() =>{
    fetchDefaultData()
})


</script>

<template>
    <div class="movies-panel">
        <dl class="movie-list">
            <dd v-for="(item,index) of movieInfo" :key="index">
                <div class="movie-item">
                    <router-link :to="{name:'movieDetail',query:{movieId:item.movieId}}">
                        <div class="movie-poster">
                            <img :src="item.posterUrl" :alt="item.title+'海报封面'"/>
                        </div>
                    </router-link>
                </div>
                <div class="channel-detail">
                    <router-link :to="{name:'movieDetail',query:{movieId:item.movieId}}">
                        {{item.title}}
                    </router-link>
                </div>
                <div class="channel-detail-orange">
                    <i v-if="item.rating===0"> 暂无评分 </i>
                    <i class="fraction" v-else>{{item.rating}} </i>
                </div>
            </dd>
        </dl>
        <div class="example-pagination-block" style="display: flex;justify-content: center">
            <el-pagination
                background
                layout="prev, pager, next"
                :page-count="100"
                v-model:current-page="currentPage"
                @change="fetchDefaultData"
            />
        </div>
    </div>

</template>

<style scoped>
.movies-panel{
    -webkit-text-size-adjust: 100%;
    font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
    -webkit-font-smoothing: subpixel-antialiased;
    margin-top: 40px;
    .movie-list{
        -webkit-text-size-adjust: 100%;
        font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
        -webkit-font-smoothing: subpixel-antialiased;
        margin: -29px 0 20px -25px;
        dd{
            -webkit-text-size-adjust: 100%;
            font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
            -webkit-font-smoothing: subpixel-antialiased;
            margin: 30px 0 0 30px;
            display: inline-block;
            vertical-align: top;
            position: relative;
            .movie-item{
                -webkit-text-size-adjust: 100%;
                font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
                -webkit-font-smoothing: subpixel-antialiased;
                position: relative;
                border: 1px solid #efefef;
                margin: -1px;
                a{
                    -webkit-text-size-adjust: 100%;
                    font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
                    -webkit-font-smoothing: subpixel-antialiased;
                    background-color: transparent;
                    text-decoration: none;
                    .movie-poster{
                        -webkit-text-size-adjust: 100%;
                        font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
                        -webkit-font-smoothing: subpixel-antialiased;
                        background-color: #fcfcfc;
                        width: 160px;
                        height: 220px;
                        overflow: hidden;
                        position: relative;
                        img{
                            -webkit-text-size-adjust: 100%;
                            font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
                            -webkit-font-smoothing: subpixel-antialiased;
                            border-style: none;
                            width: 100%;
                            position: absolute;
                            top: 0;
                            left: 0;
                        }
                    }
                }
            }
            .channel-detail{
                -webkit-text-size-adjust: 100%;
                font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
                -webkit-font-smoothing: subpixel-antialiased;
                width: 160px;
                white-space: nowrap;
                overflow: hidden;
                text-overflow: ellipsis;
                text-align: center;
                font-size: 16px;
                color: #333;
                margin-top: 10px;

                a{
                    -webkit-text-size-adjust: 100%;
                    font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
                    -webkit-font-smoothing: subpixel-antialiased;
                    white-space: nowrap;
                    text-align: center;
                    font-size: 16px;
                    background-color: transparent;
                    text-decoration: none;
                    color: #333;
                }
            }
            .channel-detail-orange{
                -webkit-text-size-adjust: 100%;
                font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
                -webkit-font-smoothing: subpixel-antialiased;
                width: 160px;
                white-space: nowrap;
                overflow: hidden;
                text-overflow: ellipsis;
                text-align: center;
                font-size: 16px;
                margin-top: 10px;
                color: #ffb400;
                .fraction{
                    -webkit-text-size-adjust: 100%;
                    font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
                    -webkit-font-smoothing: subpixel-antialiased;
                    white-space: nowrap;
                    text-align: center;
                    color: #ffb400;
                    font-size: 22px;
                }
            }
        }
    }
}



</style>