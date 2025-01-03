<script setup>
/* eslint-disable */
import {onBeforeMount, ref, toRefs} from "vue";
import {getMovieInfo, postCollectionInfo} from "@/api";
import {CollectionTag, Star, StarFilled} from "@element-plus/icons-vue";
import {useUserStore} from "@/store/userStore";

const props = defineProps({
    movieId: {
        type: String,
        required: true
    }
})

const user = useUserStore()
const movieInfo = ref(null)


const collect = () =>{
    if (user.getIsLogin === false){
        alert("请先登录")
        return
    }
    const collectionData = {
        userId: user.getUser.userId,
        movieId: props.movieId,
        collectionDate : new Date()
    }
    postCollectionInfo(collectionData)
        .then(res => res.data)
        .then(res =>{
            if (res.msg.includes("删除")){
                user.removeCollection(movieInfo.value.movie.movieId)
            }
            else{
                user.addCollection(movieInfo.value.movie.movieId)
            }
        })
}

onBeforeMount(async () => {
    const res = await getMovieInfo(props)
    if (res && res.status === 200) {
        movieInfo.value = res.data.data
        movieInfo.value.movie.tags = movieInfo.value.movie.tags.replace(",", " ")
        movieInfo.value.movie.releaseDate = movieInfo.value.movie.releaseDate.substr(0, 16).replace("T", " ")
    }
})


</script>

<template>
    <div v-if="movieInfo">

        <div class="banner">
            <div class="wrapper">
                <div class="info-left">
                    <div class="avatar-shadow">
                        <img :src="movieInfo.movie.posterUrl"/>
                        <div class="movie-ver"></div>
                    </div>
                </div>
                <div class="info-right">
                    <div class="movie-brief-container">
                        <h1 class="name">{{ movieInfo.movie.title }}</h1>
                        <ul>
                            <li class="ellipsis">{{ movieInfo.movie.tags }}</li>
                            <li class="ellipsis">{{ movieInfo.movie.productionRegion }}</li>
                            <li class="ellipsis">{{ movieInfo.movie.releaseDate }}上映</li>
                        </ul>
                    </div>
                    <div class="action-buyBtn">
                        <div>
                            <a class="collect">
                                <div @click="collect">
                                    <i>
                                        <el-icon size="18">
                                            <star v-if="!user.getCollection.includes(movieInfo.movie.movieId)"/>
                                            <star-filled v-else/>
                                        </el-icon>
                                    </i>
                                    <span class="collect-msg">收藏</span>
                                </div>
                            </a>
                        </div>
                        <router-link :to="{name:'cinemaList',query:{movieId:movieInfo.movie.movieId}}" class="btn-buy" >购票</router-link>
                    </div>
                    <div class="movie-status-container">
                        <p class="movie-index-title">评分</p>
                        <div class="info-num">
                            <span>{{ movieInfo.movie.rating===0 ? "暂无评分" : movieInfo.movie.rating }}</span>
                            <el-rate
                                :max="10"
                                disabled
                                v-model="movieInfo.movie.rating"
                            />
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="main-content">
                <div class="tab-movie-info">
                    <div class="tab-content-container">
                        <div class="module">
                            <div class="mod-title">
                                <h2>剧情简介</h2>
                            </div>


                            <div class="mod-content">
                                {{ movieInfo.movie.synopsis }}
                            </div>
                        </div>
                        <div class="module">
                            <div class="mod-title">
                                <h2>演职人员</h2>
                            </div>
                            <div class="mod-content">
                                <div class="celebrity-container">
                                    <div class="celebrity-group">
                                        <div class="celebrity-type">导演</div>
                                        <ul class="celebrity-list">
                                            <li>
                                                {{movieInfo.movie.director}}
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="celebrity-group">
                                    <div class="celebrity-type">演员</div>
                                    <ul class="celebrity-list">
                                        <li v-for="(item,index) in movieInfo.actors" :key="index">
                                            <div class="info">
                                                <div class="name">{{item.actor.actorName}}</div>
                                                <span class="role">饰:{{item.role}}</span>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>

    </div>
</template>

<style scoped>
.banner {
    -webkit-text-size-adjust: 100%;
    font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: subpixel-antialiased;
    width: 100%;
    min-width: 1200px;
    background: #392f59;

    .wrapper {
        -webkit-text-size-adjust: 100%;
        font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: subpixel-antialiased;
        width: 1200px;
        margin: 0 auto;
        height: 376px;
        position: relative;

        .info-left {
            -webkit-text-size-adjust: 100%;
            font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
            -webkit-font-smoothing: subpixel-antialiased;
            width: 300px;
            float: left;
            position: relative;
            top: 70px;
            overflow: hidden;
            z-index: 9;

            .avatar-shadow {
                -webkit-text-size-adjust: 100%;
                font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
                -webkit-font-smoothing: subpixel-antialiased;
                position: relative;
                margin: 0 30px;
                width: 240px;
                height: 330px;
                padding-bottom: 40px;

                img {
                    -webkit-text-size-adjust: 100%;
                    font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
                    -webkit-font-smoothing: subpixel-antialiased;
                    border: 4px solid #fff;
                    height: 322px;
                    width: 232px;
                }

                .movie-ver {
                    -webkit-text-size-adjust: 100%;
                    font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
                    -webkit-font-smoothing: subpixel-antialiased;
                    position: absolute;
                    top: 4px;
                    left: -2px;
                    font-size: 12px;
                    color: #fff;
                }
            }
        }

        .info-right {
            -webkit-text-size-adjust: 100%;
            font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
            -webkit-font-smoothing: subpixel-antialiased;
            position: relative;
            margin-right: 30px;
            margin-left: 300px;

            .movie-brief-container {
                -webkit-text-size-adjust: 100%;
                font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
                -webkit-font-smoothing: subpixel-antialiased;
                position: absolute;
                color: #fff;
                font-size: 14px;
                z-index: 1;
                margin-top: 70px;

                .name {
                    -webkit-text-size-adjust: 100%;
                    font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
                    -webkit-font-smoothing: subpixel-antialiased;
                    color: #fff;
                    margin: .67em 0;
                    width: 900px;
                    margin-top: 0;
                    font-size: 26px;
                    line-height: 32px;
                    font-weight: 700;
                    margin-bottom: 0;
                    overflow: hidden;
                    text-overflow: ellipsis;
                    display: -webkit-box;
                    -webkit-line-clamp: 2;
                    -webkit-box-orient: vertical;
                    max-height: 64px;
                }

                ul {
                    -webkit-text-size-adjust: 100%;
                    font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
                    -webkit-font-smoothing: subpixel-antialiased;
                    color: #fff;
                    font-size: 14px;
                    margin: 0;
                    padding: 0;
                    line-height: 1;
                    width: 250px;
                    list-style: none;
                    padding-left: 0;
                    margin-bottom: 20px;

                    .ellipsis {
                        -webkit-text-size-adjust: 100%;
                        font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
                        -webkit-font-smoothing: subpixel-antialiased;
                        color: #fff;
                        font-size: 14px;
                        text-overflow: ellipsis;
                        overflow: hidden;
                        white-space: nowrap;
                        padding: 0;
                        list-style: none;
                        margin: 12px 0;
                        line-height: 100%;
                    }
                }

            }

            .action-buyBtn {
                -webkit-text-size-adjust: 100%;
                font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
                -webkit-font-smoothing: subpixel-antialiased;
                position: absolute;
                bottom: -350px;

                .collect {
                    -webkit-text-size-adjust: 100%;
                    font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
                    -webkit-font-smoothing: subpixel-antialiased;
                    text-decoration: none;
                    cursor: pointer;
                    float: left;
                    display: block;
                    width: 250px;
                    height: 25px;
                    background-color: #756189;
                    margin-right: 10px;
                    padding-top: 11px;
                    text-align: center;
                    font-size: 14px;
                    line-height: 16px;
                    color: #fff;
                    border-radius: 2px;

                    div {
                        display: block;

                        i {
                            -webkit-text-size-adjust: 100%;
                            font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
                            -webkit-font-smoothing: subpixel-antialiased;
                            cursor: pointer;
                            text-align: center;
                            font-size: 14px;
                            line-height: 16px;
                            color: #fff;
                            display: inline-block;
                            vertical-align: middle;
                            margin-top: -2px;
                            margin-right: 2px;
                            width: 16px;
                            height: 16px;
                        }

                        .collect-msg {
                            -webkit-text-size-adjust: 100%;
                            font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
                            -webkit-font-smoothing: subpixel-antialiased;
                            cursor: pointer;
                            text-align: center;
                            font-size: 16px;
                            line-height: 16px;
                            color: #fff;
                        }
                    }
                }

                .btn-buy {
                    -webkit-text-size-adjust: 100%;
                    font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
                    -webkit-font-smoothing: subpixel-antialiased;
                    text-decoration: none;
                    display: inline-block;
                    color: #fff;
                    background-color: #df2d2d;
                    margin-top: 10px;
                    width: 250px;
                    height: 40px;
                    font-size: 16px;
                    line-height: 40px;
                    text-align: center;
                    border-radius: 2px;
                    padding: 0;
                }
            }

            .movie-status-container {
                -webkit-text-size-adjust: 100%;
                font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
                -webkit-font-smoothing: subpixel-antialiased;
                position: absolute;
                top: 208px;
                left: 342px;

                .movie-index-title {
                    -webkit-text-size-adjust: 100%;
                    font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
                    -webkit-font-smoothing: subpixel-antialiased;
                    color: #fff;
                    margin: 0;
                    padding: 0;
                    list-style: none;
                    line-height: 1;
                    font-size: 12px;
                    margin-bottom: 8px;
                }

                .info-num {
                    -webkit-text-size-adjust: 100%;
                    font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
                    -webkit-font-smoothing: subpixel-antialiased;
                    font-size: 30px;
                    color: #ffc600;
                    height: 30px;
                    line-height: 30px;
                    float: left;
                }
            }
        }
    }
}

.container {
    -webkit-text-size-adjust: 100%;
    font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: subpixel-antialiased;
    width: 1200px;
    margin: 0 auto;

    .main-content {
        -webkit-text-size-adjust: 100%;
        font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: subpixel-antialiased;
        margin-top: 80px;
        width: 730px;
        .tab-movie-info {
            -webkit-text-size-adjust: 100%;
            font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
            -webkit-font-smoothing: subpixel-antialiased;

            .tab-content-container {
                -webkit-text-size-adjust: 100%;
                font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
                -webkit-font-smoothing: subpixel-antialiased;
                .module{
                    -webkit-text-size-adjust: 100%;
                    font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
                    -webkit-font-smoothing: subpixel-antialiased;
                    margin-bottom: 60px;
                    position: relative;
                    .mod-title {
                        -webkit-text-size-adjust: 100%;
                        font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
                        -webkit-font-smoothing: subpixel-antialiased;

                        h2 {
                            -webkit-text-size-adjust: 100%;
                            font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
                            -webkit-font-smoothing: subpixel-antialiased;
                            display: inline-block;
                            margin: 0;
                            padding: 0;
                            font-weight: 400;
                            font-size: 18px;
                            color: #333;
                            line-height: 18px;
                        }

                        h2:before {
                            float: left;
                            content: "";
                            display: inline-block;
                            width: 4px;
                            height: 18px;
                            margin-right: 6px;
                            background-color: #ef4238;
                        }
                    }
                    .mod-content {
                        -webkit-text-size-adjust: 100%;
                        font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
                        -webkit-font-smoothing: subpixel-antialiased;
                        margin-top: 20px;
                        color: #333;
                        .celebrity-container{
                            -webkit-text-size-adjust: 100%;
                            font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
                            -webkit-font-smoothing: subpixel-antialiased;
                            font-size: 16px;
                            color: #333;
                            .celebrity-group{
                                -webkit-text-size-adjust: 100%;
                                font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
                                -webkit-font-smoothing: subpixel-antialiased;
                                font-size: 16px;
                                color: #333;
                                margin-left: 0;
                                .celebrity-type{
                                    -webkit-text-size-adjust: 100%;
                                    font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
                                    -webkit-font-smoothing: subpixel-antialiased;
                                    font-size: 16px;
                                    color: #333;
                                    margin-bottom: 16px;
                                }
                                .celebrity-list{
                                    -webkit-text-size-adjust: 100%;
                                    font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
                                    -webkit-font-smoothing: subpixel-antialiased;
                                    font-size: 16px;
                                    color: #333;
                                    list-style: none;
                                    padding-left: 0;
                                    margin: 0;
                                    margin-left: -20px;
                                    li{
                                        -webkit-text-size-adjust: 100%;
                                        font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
                                        -webkit-font-smoothing: subpixel-antialiased;
                                        font-size: 16px;
                                        color: #333;
                                        list-style: none;
                                        display: inline-block;
                                        width: 128px;
                                        margin-left: 20px;
                                        text-overflow: ellipsis;
                                        white-space: nowrap;
                                        margin-bottom: 0;
                                        .info{
                                            -webkit-text-size-adjust: 100%;
                                            font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
                                            -webkit-font-smoothing: subpixel-antialiased;
                                            font-size: 16px;
                                            color: #333;
                                            list-style: none;
                                            white-space: nowrap;
                                            .name{
                                                -webkit-text-size-adjust: 100%;
                                                font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
                                                -webkit-font-smoothing: subpixel-antialiased;
                                                font-size: 16px;
                                                list-style: none;
                                                display: inline-block;
                                                text-align: center;
                                                padding-bottom: 1px;
                                                margin-bottom: -1px;
                                                text-overflow: ellipsis;
                                                overflow: hidden;
                                                white-space: nowrap;
                                                margin-top: 8px;
                                                line-height: 1.2;
                                                color: #333;
                                                width: 70px;
                                                overflow:hidden;
                                            }
                                            .role{
                                                -webkit-text-size-adjust: 100%;
                                                font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
                                                -webkit-font-smoothing: subpixel-antialiased;
                                                list-style: none;
                                                display: inline-block;
                                                text-align: center;
                                                padding-bottom: 1px;
                                                margin-bottom: -1px;
                                                text-overflow: ellipsis;
                                                overflow: hidden;
                                                white-space: nowrap;
                                                font-size: 14px;
                                                line-height: 1.2;
                                                color: #666;
                                                width: 70px;
                                                overflow: hidden;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

</style>