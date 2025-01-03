<script setup>
/* eslint-disable */
import {movie_tags,movie_regions,release_date} from "@/assets/conmon";
import {nextTick, onMounted, reactive, ref} from "vue";
import {useRoute, useRouter} from "vue-router";


const route = useRoute()
const router = useRouter()

const bar = ref(route.query.bar || 'hotScreening')


const selectedTag = reactive({
    type: route.query.type || "全部",
    region: route.query.region || "全部",
    year: route.query.year || "全部",
});

const updateQuery = () =>{
    router.push({
        name: 'movieList',
        query: {bar:bar.value,...selectedTag}
    })
}

const changeBar = async (index) => {
    bar.value = index
    updateQuery()
}
const changeTag = async (category, name) => {
    selectedTag[category] = name;
    updateQuery()
};


</script>

<template>
    <div>
        <div class="nav">

            <ul class="navbar">
                <li >
                    <a
                        @click="changeBar('hotScreening')"
                        :class="{ active: bar === 'hotScreening' }"
                        style="cursor: pointer"
                    >
                        正在热映
                    </a>
                </li>
                <li >
                    <a
                        @click="changeBar('upComing')"
                        :class="{ active: bar === 'upComing' }"
                        style="cursor: pointer"
                    >
                        即将上映
                    </a>
                </li>
                <li >
                    <a
                        @click="changeBar('classic')"
                        :class="{ active: bar === 'classic' }"
                        style="cursor: pointer"
                    >
                        经典电影
                    </a>
                </li>
            </ul>
        </div>
        <div class="movies-channel">
            <div class="tags-panel">
                <ul class="tags-lines">
                    <li class="tags-line">
                        <div class="tags-title">类型:</div>
                        <ul class="tags">
                            <li
                                v-for="(item,index) in movie_tags"
                                :key="index"
                                :class="{ active: selectedTag.type === item.name }"
                            >
                                <a
                                    @click="changeTag('type', item.name)"
                                    :style=" selectedTag.type === item.name?'color:white':''"
                                    style="cursor: pointer"
                                >{{ item.name }}</a>
                            </li>
                        </ul>
                    </li><el-divider/>
                    <li class="tags-line">
                        <div class="tags-title">区域:</div>
                        <ul class="tags">
                            <li
                                v-for="(item,index) in movie_regions"
                                :key="index"
                                :class="{ active: selectedTag.region === item.name }"
                            >
                                <a
                                    :style=" selectedTag.region === item.name?'color:white':''"
                                    @click="changeTag('region', item.name)"
                                    style="cursor: pointer"
                                >{{ item.name }}</a>
                            </li>
                        </ul>
                    </li><el-divider/>
                    <li class="tags-line">
                        <div class="tags-title">年代</div>
                        <ul class="tags">
                            <li
                                v-for="(item,index) in release_date"
                                :key="index"
                                :class="{ active: selectedTag.year === item.name  }"
                            >
                                <a
                                    :style=" selectedTag.year === item.name?'color:white':''"
                                    @click="changeTag('year', item.name)"
                                    style="cursor: pointer"
                                >{{ item.name }}</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
            <router-view :key="$route.fullPath"/>
        </div>
    </div>
</template>

<style scoped>
*{
    padding: 0;
    margin: 0;
}
.nav{
    -webkit-text-size-adjust: 100%;
    font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
    -webkit-font-smoothing: subpixel-antialiased;

    background-color: #47464a;
    height: 60px;
    width: 100%;
    min-width: 1200px;
    text-align: center;
    .navbar{
        -webkit-text-size-adjust: 100%;
        font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
        -webkit-font-smoothing: subpixel-antialiased;
        text-align: center;
        margin: 0;
        padding: 0;
        list-style: none;
        display: inline-block;
        overflow: hidden;
        li{
            -webkit-text-size-adjust: 100%;
            font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
            -webkit-font-smoothing: subpixel-antialiased;
            margin: 0;
            padding: 0;
            list-style: none;
            display: inline-block;
            float: left;
            a{
                -webkit-text-size-adjust: 100%;
                font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
                -webkit-font-smoothing: subpixel-antialiased;
                list-style: none;
                background-color: transparent;
                text-decoration: none;
                display: block;
                font-size: 16px;
                color: #999;
                height: 60px;
                line-height: 60px;
                padding: 0 40px;
            }
            .active{
                color: red;
            }
        }
    }
}
.movies-channel{
    display: block;
    -webkit-text-size-adjust: 100%;
    font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
    -webkit-font-smoothing: subpixel-antialiased;
    width: 1120px;
    margin: auto;
    .tags-panel{
        -webkit-text-size-adjust: 100%;
        font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
        -webkit-font-smoothing: subpixel-antialiased;
        border: 1px solid #e5e5e5;
        padding: 0 20px;
        margin-top: 40px;
        display: block;
        .tags-lines{
            -webkit-text-size-adjust: 100%;
            font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
            -webkit-font-smoothing: subpixel-antialiased;
            margin: 0;
            padding: 0;
            list-style-type: none;
            .tags-line{
                -webkit-text-size-adjust: 100%;
                font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
                -webkit-font-smoothing: subpixel-antialiased;
                padding: 10px 0!important;
                margin: 0;
                list-style-type: none;
                .tags-title{
                    -webkit-text-size-adjust: 100%;
                    font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
                    -webkit-font-smoothing: subpixel-antialiased;
                    list-style-type: none;
                    height: 24px;
                    line-height: 24px;
                    float: left;
                    color: #999;
                    font-size: 14px;
                }
                .tags{
                    -webkit-text-size-adjust: 100%;
                    font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
                    -webkit-font-smoothing: subpixel-antialiased;
                    margin-left: 40px!important;
                    margin: 0;
                    padding: 0;
                    list-style-type: none;
                    li{
                        -webkit-text-size-adjust: 100%;
                        font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
                        -webkit-font-smoothing: subpixel-antialiased;
                        margin: 0;
                        list-style-type: none;
                        border-radius: 14px;
                        padding: 3px 9px;
                        display: inline-block;
                        margin-left: 12px;
                        a{
                            -webkit-text-size-adjust: 100%;
                            font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
                            -webkit-font-smoothing: subpixel-antialiased;
                            list-style-type: none;
                            background-color: transparent;
                            text-decoration: none;
                            color: #333;
                            font-size: 14px;
                        }

                    }
                    .active{
                        -webkit-text-size-adjust: 100%;
                        font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
                        -webkit-font-smoothing: subpixel-antialiased;
                        margin: 0;
                        list-style-type: none;
                        border-radius: 14px;
                        padding: 3px 9px;
                        display: inline-block;
                        margin-left: 12px;
                        background: #f34d41;
                    }
                }
            }
        }
    }

}
</style>