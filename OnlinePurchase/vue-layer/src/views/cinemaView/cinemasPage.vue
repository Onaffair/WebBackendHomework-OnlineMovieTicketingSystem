<script setup>
/* eslint-disable */
import {cinemas} from "@/assets/conmon";
import {reactive} from "vue";
import {useRoute,useRouter} from "vue-router";
import BottomInfo from "@/components/BottomInfo.vue";

const router = useRouter()
const route = useRoute()

const selectedBrand = reactive({
    name: route.query.name || "全部"
})
const updateQuery = () =>{
    router.push({
        name: 'cinemaList',
        query:{
            name:selectedBrand.name
        }
    })
}
const changeBrand = async (item) =>{
    selectedBrand.name=item
    updateQuery()
}

</script>

<template>
    <div>
        <div class="container">
            <div class="tags-panel">
                <ul class="tags-lines">
                    <li class="tags-line">
                        <div class="tags-title"> 品牌:</div>
                        <ul class="tags">
                            <li
                                v-for="(item,index) of cinemas"
                                :key="index"
                                :class="{active:selectedBrand.name===item}"
                            >
                                <a
                                    :style=" selectedBrand === item?'color:white':''"
                                    @click="changeBrand(item)"
                                    style="cursor: pointer"
                                >{{ item }}</a>
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

.container{
    -webkit-text-size-adjust: 100%;
    font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
    -webkit-font-smoothing: subpixel-antialiased;
    width: 1200px;
    margin: 0 auto;
    .tags-panel{
        -webkit-text-size-adjust: 100%;
        font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
        -webkit-font-smoothing: subpixel-antialiased;
        border: 1px solid #e5e5e5;
        padding: 0 20px;
        margin: 40px 0;
        position: relative;
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
                        color: #fff;
                    }
                }
            }
        }
    }

}
</style>