<script setup>
/* eslint-disable */
import {onBeforeMount, ref} from "vue";
import {getCinemaList} from "@/api";
import BottomInfo from "@/components/BottomInfo.vue";


//query参数传递为String
const props = defineProps({
    name:{
        default:'全部'
    },
    movieId:{
        default:null
    }
})

const currentPage = ref(1)
const cinemaList = ref(null)

const fetchDefaultData = () =>{
    const cinemaQuery = {
        movieId:Number(props.movieId),
        brand:props.name,
        page:currentPage.value,
        size:12
    }
    getCinemaList(cinemaQuery)
        .then(res => res.data)
        .then(res =>{
            cinemaList.value = res.data
        })
        .catch(error => console.log(error))
}

onBeforeMount(() =>{
    fetchDefaultData()
})

</script>

<template>
    <div>
        <div class="cinema-list">
            <h2>影院列表</h2>
            <div class="cinema-cell" v-for="(item,index) of cinemaList" :key="index">
                <div class="cinema-info">
                    <router-link
                        :to="{name:'cinemaDetail',query:{cinemaId:item.cinemaId}}"
                        class="cinema-name"
                        style="cursor:pointer;"
                    >{{item.name}}</router-link>
                    <p class="cinema-address">地址：{{item.location}}</p>
                </div>
            </div>
        </div>
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
.cinema-list{
    -webkit-text-size-adjust: 100%;
    font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
    -webkit-font-smoothing: subpixel-antialiased;
    h2{
        -webkit-text-size-adjust: 100%;
        font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
        -webkit-font-smoothing: subpixel-antialiased;
        font-size: 18px;
        color: #333;
        border-left: 4px solid #f03d37;
        padding-left: 6px;
        line-height: 18px;
        margin: 0;
        position: relative;
    }
    .cinema-cell{
        -webkit-text-size-adjust: 100%;
        font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
        -webkit-font-smoothing: subpixel-antialiased;
        display: block;
        padding: 20px 0;
        border-bottom: 1px dashed #e5e5e5;
        .cinema-info{
            -webkit-text-size-adjust: 100%;
            font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
            -webkit-font-smoothing: subpixel-antialiased;
            display: inline-block;
            max-width: 80%;
            .cinema-name{
                -webkit-text-size-adjust: 100%;
                font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
                -webkit-font-smoothing: subpixel-antialiased;
                background-color: transparent;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                line-height: 18px;
                color: #333;
                margin-bottom: 10px;
            }
            .cinema-address{
                -webkit-text-size-adjust: 100%;
                font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
                -webkit-font-smoothing: subpixel-antialiased;
                margin: 0;
                font-size: 14px;
                line-height: 14px;
                color: #999;
            }
        }
        .buy-btn{
            -webkit-text-size-adjust: 100%;
            font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
            -webkit-font-smoothing: subpixel-antialiased;
            float: right;
            width: 80px;
            height: 45px;
            line-height: 45px;
            margin-left: 36px;
            margin-right: 20px;
            a{
                -webkit-text-size-adjust: 100%;
                font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
                -webkit-font-smoothing: subpixel-antialiased;
                text-decoration: none;
                display: inline-block;
                width: 100%;
                height: 30px;
                color: #fff;
                background-color: #f03d37;
                font-size: 14px;
                line-height: 30px;
                border-radius: 100px;
                text-align: center;
                box-shadow: 0 2px 10px -2px #f03d37;
            }
        }
    }
}
</style>