<script setup>
/* eslint-disable */
import PurchaseModel from "@/components/PurchaseModel.vue";
import {computed, nextTick, onBeforeMount, reactive, ref} from "vue";
import {getCinemaMovieScreening} from "@/api";
import {useUserStore} from "@/store/userStore";

const props = defineProps(
    {
        cinemaId: {
            type: String,
            required: true,
        }
    }
)
const user = useUserStore()
const cinemaInfo = ref(null)
const currentMovie = ref(null)
const currentDate = ref(null)
const purchaseModel = ref(false)
const selectedScreeningInfo = reactive({})


const loadCinemaInfo = async () => {
    try {
        const res = await getCinemaMovieScreening(props.cinemaId);
        cinemaInfo.value = groupScreeningsByDate(res.data.data);

        // 设置默认值
        currentMovie.value = cinemaInfo.value.movieDetails[0].movie.movieId;
        const keys = Object.keys(cinemaInfo.value.movieDetails[0].screeningDetails);
        currentDate.value = keys[0];
    } catch (error) {
        console.error("Error loading cinema info:", error);
    }
};
const getNew = (curr, prev) => {
    currentMovie.value = cinemaInfo.value.movieDetails[curr].movie.movieId
    const keys = Object.keys(cinemaInfo.value.movieDetails[curr].screeningDetails);
    currentDate.value = keys[0]
}

// 数据分组逻辑
const groupScreeningsByDate = (data) => {
    if (!data || !data.movieDetails) return data;

    // 遍历每部电影的放映安排，按日期分组
    data.movieDetails.forEach((movieDetail) => {
        const groups = {};
        movieDetail.screeningDetails.forEach((screeningDetail) => {
            const date = screeningDetail.screening.screeningTime.split("T")[0]; // 提取日期部分
            if (!groups[date]) {
                groups[date] = [];
            }
            groups[date].push(screeningDetail);
            screeningDetail.screening.screeningTime = screeningDetail.screening.screeningTime.substr(11,5)
        });
        movieDetail.screeningDetails = groups; // 添加分组后的数据到当前电影详情
    });

    return data;
};
const showPurchaseModel = (screening) =>{

    if (user.getIsLogin === false) {
        alert("请先登录")
        return
    }

    purchaseModel.value = true
    selectedScreeningInfo.screeningId = screening.screeningId
    selectedScreeningInfo.price = screening.price
    selectedScreeningInfo.userId = user.getUser.userId
}
const closePurchaseModel =  ()=>{
    purchaseModel.value = false
}

onBeforeMount(() => {
    loadCinemaInfo()
})
</script>

<template>
    <div v-if="cinemaInfo">
        <div class="banner">
            <div class="wrapper">
                <div class="cinema-main">
                    <div class="cinema-brief-container">
                        <h1 class="name"> {{ cinemaInfo.cinema.name }}</h1>
                        <div class="address">{{ cinemaInfo.cinema.location }}</div>
                        <div class="telephone">{{ cinemaInfo.cinema.contactNumber }}</div>
                        <div class="features-group">
                            <div class="group-title">影院服务</div>
                            <div class="feature">
                                <span class="tag ">退</span>
                                <p class="desc text-ellipsis" title="未取票用户放映前1分钟可退票">
                                    未取票用户放映前1分钟可退票</p>
                            </div>
                            <div class="feature">
                                <span class="tag ">改签</span>
                                <p class="desc text-ellipsis" title="未取票用户放映前1分钟可改签">
                                    未取票用户放映前1分钟可改签</p>
                            </div>
                            <div class="feature">
                                <span class="tag ">3D眼镜自费购买</span>
                                <p class="desc text-ellipsis" title="3D眼镜：5元/副  挂镜：10/副">3D眼镜：5元/副
                                    挂镜：10/副</p>
                            </div>
                            <div class="feature">
                                <span class="tag ">儿童优惠</span>
                                <p class="desc text-ellipsis" title="1.2米（含）以下儿童免票无座，每位家长限带一名儿童">
                                    1.2米（含）以下儿童免票无座，每位家长限带一名儿童</p>
                            </div>
                            <div class="feature">
                                <span class="tag ">WiFi</span>
                                <p class="desc text-ellipsis" title="至潮影城">至潮影城</p>
                            </div>
                            <div class="feature">
                                <span class="tag ">可停车</span>
                                <p class="desc text-ellipsis" title="万洋广场有停车场">万洋广场有停车场</p>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <div class="container">
            <div class="movie-list-container">
                <el-carousel
                    @change="getNew"
                    height="550px"
                    type="card"
                    :autoplay="false"
                >
                    <el-carousel-item
                        v-for="(item,index) of cinemaInfo.movieDetails" :key="item"
                    >
                        <img :src="item.movie.posterUrl">
                    </el-carousel-item>
                </el-carousel>
            </div>
            <div class="show-list" v-for="(item,index) of cinemaInfo.movieDetails" :key="index"
                 :class="{active:currentMovie===item.movie.movieId}">
                <div class="movie-info">
                    <div>
                        <router-link :to="{name:'movieDetail',query:{movieId:item.movie.movieId}}"><h2 class="movie-name">{{ item.movie.title }}</h2></router-link>
                        <span class="score">{{ item.movie.rating !== 0 ? item.movie.rating + '分' : '暂无评分' }}</span>
                    </div>
                    <div class="movie-desc">
                        <div>
                            <span class="key">类型 :</span>
                            <span class="value">
                                <a class="text-link"> {{item.movie.tags}} </a>
                            </span>
                        </div>
                        <div>
                            <span class="key">导演 :</span>
                            <span> {{item.movie.director}}</span>

                        </div>
                    </div>
                </div>
                <div class="show-date">
                    <span>观影时间</span>
                    <span
                        class="date-item"
                        v-for="(item,index) of item.screeningDetails"
                        :key="index"
                        :class="{active:currentDate===index}"
                        @click="currentDate=index"
                    >
                        {{index}}</span>
                </div>
                <div v-for="(detail,index) of item.screeningDetails" >
                    <el-table
                        v-if="currentDate === index"
                        stripe
                        :data="detail"
                        style="width: 100%"
                    >
                        <el-table-column
                            prop="screening.screeningTime"
                            label="放映时间"
                            width="300%"
                            class-name="begin-time"
                        />
                        <el-table-column
                            prop="screeningRoom.roomNumber"
                            label="影厅"
                            width="300%"
                        />
                        <el-table-column
                            prop="screening.price"
                            label="票价"
                            width="200%"
                        />
                        <el-table-column
                            label="选座购票"
                        >
                            <template #default="scope">
                                <el-button @click="showPurchaseModel(scope.row.screening)">购票</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>
            </div>
        </div>
        <div style="height: 500px">
        </div>

        <PurchaseModel
            :screeningId="selectedScreeningInfo.screeningId"
            :userId="selectedScreeningInfo.userId"
            :price="selectedScreeningInfo.price"
            @close="closePurchaseModel"
            v-if="purchaseModel"
        />
    </div>
</template>


<style scoped>
.banner {
    -webkit-text-size-adjust: 100%;
    font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: subpixel-antialiased;
    width: 100%;
    min-width: 1200px;
    background: #392f59 no-repeat 50%;

    .wrapper {
        -webkit-text-size-adjust: 100%;
        font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: subpixel-antialiased;
        width: 1200px;
        margin: 0 auto;
        height: 330px;
        position: relative;
        top: 49px;

        .cinema-main {
            -webkit-text-size-adjust: 100%;
            font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
            -webkit-font-smoothing: subpixel-antialiased;
            position: relative;
            float: left;
            max-width: 600px;
            margin-top: -16px;

            .cinema-brief-container {
                -webkit-text-size-adjust: 100%;
                font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
                -webkit-font-smoothing: subpixel-antialiased;
                color: #fff;

                .name {
                    -webkit-text-size-adjust: 100%;
                    font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
                    -webkit-font-smoothing: subpixel-antialiased;
                    color: #fff;
                    overflow: hidden;
                    white-space: nowrap;
                    text-overflow: ellipsis;
                    margin: 0;
                    font-size: 26px;
                    margin-bottom: 9px;
                    font-weight: 400;
                }

                .address {
                    -webkit-text-size-adjust: 100%;
                    font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
                    -webkit-font-smoothing: subpixel-antialiased;
                    color: #fff;
                    overflow: hidden;
                    white-space: nowrap;
                    text-overflow: ellipsis;
                    font-size: 14px;
                    margin-bottom: 6px;
                }

                .telephone {
                    -webkit-text-size-adjust: 100%;
                    font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
                    -webkit-font-smoothing: subpixel-antialiased;
                    color: #fff;
                    font-size: 14px;
                    margin-bottom: 20px;
                }

                .features-group {

                    -webkit-text-size-adjust: 100%;
                    font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
                    -webkit-font-smoothing: subpixel-antialiased;
                    color: #fff;
                    position: relative;

                    .group-title {
                        -webkit-text-size-adjust: 100%;
                        font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
                        -webkit-font-smoothing: subpixel-antialiased;
                        color: #fff;
                        font-size: 14px;
                        margin-bottom: 5px;
                        overflow: hidden;
                        width: 410px;
                    }

                    .group-title:after {
                        border-top: 1px solid hsla(0, 0%, 100%, .7);
                        display: block;
                        content: "";
                        position: relative;
                        top: -10px;
                        left: 70px;
                    }

                    .feature {
                        -webkit-text-size-adjust: 100%;
                        font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
                        -webkit-font-smoothing: subpixel-antialiased;
                        color: #fff;
                        font-size: 12px;
                        margin-bottom: 2px;
                        position: relative;
                        min-height: 22px;
                        line-height: 23px;
                        transform-origin: 0;
                        transform: scale(.8);

                        .tag {
                            -webkit-text-size-adjust: 100%;
                            font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
                            -webkit-font-smoothing: subpixel-antialiased;
                            color: #fff;
                            font-size: 12px;
                            display: inline-block;
                            border: 1px solid hsla(0, 0%, 100%, .6);
                            border-radius: 2px;
                            min-width: 60px;
                            height: 22px;
                            line-height: 23px;
                            text-align: center;
                        }

                        .desc {
                            -webkit-text-size-adjust: 100%;
                            font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
                            -webkit-font-smoothing: subpixel-antialiased;
                            color: #fff;
                            font-size: 12px;
                            overflow: hidden;
                            white-space: nowrap;
                            text-overflow: ellipsis;
                            margin: 0;
                            padding: 0;
                            list-style: none;
                            line-height: 1;
                            display: inline-block;
                            max-width: 438px;
                            margin-left: 5px;
                            vertical-align: middle;
                        }
                    }
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
    margin-top: 120px;

    .movie-list-container {
        -webkit-text-size-adjust: 100%;
        font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: subpixel-antialiased;
        height: 550px;
        padding: 22px 5px;
        box-sizing: border-box;
        overflow: hidden;
        box-shadow: inset 0 0 100px 80px #ededed;
        position: relative;

        img {
            width: 100%;
            height: 100%;
        }
    }

    .show-list {
        -webkit-text-size-adjust: 100%;
        font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: subpixel-antialiased;
        display: none;


        .movie-info {
            -webkit-text-size-adjust: 100%;
            font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
            -webkit-font-smoothing: subpixel-antialiased;
            margin-top: 20px;
            border-bottom: 1px solid #e5e5e5;

            .movie-name {
                -webkit-text-size-adjust: 100%;
                font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
                -webkit-font-smoothing: subpixel-antialiased;
                display: inline-block;
                margin: 0 20px 20px 0;
                font-size: 26px;
                font-weight: 400;
                color: #333;
            }

            .score {
                -webkit-text-size-adjust: 100%;
                font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
                -webkit-font-smoothing: subpixel-antialiased;
                display: inline-block;
                font-size: 24px;
                color: #faaf00;
            }
        }
        .movie-desc{
            -webkit-text-size-adjust: 100%;
            font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
            -webkit-font-smoothing: subpixel-antialiased;
            div{
                -webkit-text-size-adjust: 100%;
                font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
                -webkit-font-smoothing: subpixel-antialiased;
                display: inline-block;
                font-size: 14px;
                color: #151515;
                margin-bottom: 20px;
                margin-right: 40px;
                .key{
                    -webkit-text-size-adjust: 100%;
                    font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
                    -webkit-font-smoothing: subpixel-antialiased;
                    font-size: 14px;
                    color: #999;
                }
                .value{
                    -webkit-text-size-adjust: 100%;
                    font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
                    -webkit-font-smoothing: subpixel-antialiased;
                    font-size: 14px;
                    color: #151515;
                    a{
                        -webkit-text-size-adjust: 100%;
                        font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
                        -webkit-font-smoothing: subpixel-antialiased;
                        font-size: 14px;
                        background-color: transparent;
                        text-decoration: none;
                        color: inherit;
                    }
                }
            }
        }
        .show-date {
            -webkit-text-size-adjust: 100%;
            font-family: Microsoft YaHei, Helvetica, Arial, sans-serif;
            -webkit-font-smoothing: subpixel-antialiased;
            padding: 30px 0;
            font-size: 14px;
            color: #999;
            .date-item{
                cursor: pointer;
                -webkit-text-size-adjust: 100%;
                font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
                -webkit-font-smoothing: subpixel-antialiased;
                font-size: 14px;
                display: inline-block;
                background-color: transparent;
                border-radius: 100px;
                color: #333;
                padding: 2px 10px;
                margin-left: 12px;
                cursor: default;
            }
            .active{
                color: #fff;
                background-color: #f03d37;
            }
        }
        .begin-time{
            -webkit-text-size-adjust: 100%;
            font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
            -webkit-font-smoothing: subpixel-antialiased;
            border-spacing: 0;
            text-align: center;
            font-size: 18px;
            color: #333;
            font-weight: 700;
        }
        .hall{
            -webkit-text-size-adjust: 100%;
            font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
            -webkit-font-smoothing: subpixel-antialiased;
            border-spacing: 0;
            text-align: center;
            font-size: 14px;
            color: #333;
        }
        .sell-price{
            -webkit-text-size-adjust: 100%;
            font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
            -webkit-font-smoothing: subpixel-antialiased;
            border-spacing: 0;
            text-align: center;
            font-size: 18px;
            color: #f03d37;
            font-weight: 700;
        }
        .active {
            display: block;
        }
    }

    .active {
        display: block;
    }
}

</style>