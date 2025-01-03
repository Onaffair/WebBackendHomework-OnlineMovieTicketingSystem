<script setup>
/* eslint-disable */
import {VideoCameraFilled} from '@element-plus/icons-vue'
import {onBeforeMount, ref, watch} from "vue";
import {useUserStore} from "@/store/userStore";
import LoginModal from "@/components/LoginModal.vue";
import RegisterModal from "@/components/RegisterModal.vue";
import {useRoute, useRouter} from "vue-router";
import BottomInfo from "@/components/BottomInfo.vue";

const route = useRoute()
const router = useRouter()

const state = useUserStore()
const presentPage = ref('main')
const currentHash = ref(route.path)

watch(()=> route.path , () =>{
    currentHash.value = route.path
    if (currentHash.value.includes('main')){
        presentPage.value = 'main'
    }else if (currentHash.value.includes('movie')){
        presentPage.value = 'movies'
    }else if (currentHash.value.includes('cinema')){
        presentPage.value = 'cinemas'
    }
},{
    immediate:true
})


const formDialog = ref({
    loginDialog: false,
    registerDialog: false,
})

const handleSelect = (key, keyPath) => {
    switch (key){
        case "1-1":
            formDialog.value.loginDialog = true
            break
        case '1-2':
            formDialog.value.registerDialog = true
            break
        case '1-3':
            router.push({
                name:'PersonalCenter'
            })
            break
        case '1-4':
            state.quit()
            break
        default:
            console.log("no")
            break
    }
}
</script>
<template>
    <div>
        <div class="header">
            <div class="header-inner">
                <div class="logo"><el-icon size="80"><VideoCameraFilled/></el-icon></div>
                <div class="nav">
                    <div class="word-container" ><router-link :to="{name:'main'}"><div :class="presentPage==='main'?'active':''">首页</div></router-link></div>
                    <div class="word-container" ><router-link :to="{name:'movies'}"><div :class="presentPage==='movies'?'active':''">电影</div></router-link></div>
                    <div class="word-container" ><router-link :to="{name:'cinemas'}"><div :class="presentPage==='cinemas'?'active':''">影院</div></router-link></div>
                </div>
                <div >
                    <el-menu
                        style="z-index: 20"
                        @select="handleSelect"
                        :close-on-click-outside='true'
                    >
                        <el-sub-menu index="1">
                            <template #title><el-avatar :src="state.getIsLogin?state.getUser.avatarUrl:''"/></template>
                            <div v-if="state.getIsLogin===false">
                                <el-menu-item index="1-1" >登录</el-menu-item>
                                <el-menu-item index="1-2">注册</el-menu-item>
                            </div>
                            <div v-else>
                                <el-menu-item index="1-3"><p>{{state.getUser.nickname}}</p></el-menu-item>
                                <el-menu-item index="1-4">退出</el-menu-item>
                            </div>
                        </el-sub-menu>
                    </el-menu>
                </div>
            </div>
        </div>
        <LoginModal v-if="formDialog.loginDialog" @close="formDialog.loginDialog=false"/>
        <RegisterModal v-if="formDialog.registerDialog" @close="formDialog.registerDialog=false"/>
        <el-divider style="z-index: -1" />


        <router-view/>

        <div style="margin-top: 3%">
            <el-divider />
            <BottomInfo />
        </div>

    </div>
</template>
<style scoped>
*{
    margin: 0;
    padding: 0;
}

.header{
    -webkit-text-size-adjust: 100%;
    font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
    -webkit-font-smoothing: subpixel-antialiased;
    width: 1200px;
    margin: 0 auto;
    height: 80px;
    .header-inner{
        -webkit-text-size-adjust: 100%;
        font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
        -webkit-font-smoothing: subpixel-antialiased;
        display: inline-flex;
        width: 1200px;
        margin: 0 auto;
        height: 80px;
        .nav{
            -webkit-text-size-adjust: 100%;
            font-family: Microsoft YaHei,Helvetica,Arial,sans-serif;
            -webkit-font-smoothing: subpixel-antialiased;
            width: 1200px;
            margin: 0 auto;
            height: 80px;
            display: inline-flex;
            .word-container{
                width: 80px;
                height: 80px;
                a{
                    text-decoration: none;
                    color:black;
                    div{
                        font-size: 20px;
                        width: 80px;
                        height: 80px;
                        align-content: center;
                        text-align: center;
                    }
                    div:hover{
                        color: red;
                    }
                    .active{
                        color: white;
                        background-color: red;
                    }
                    .active:hover{
                        color: white;
                    }
                }
            }
        }
    }
}

</style>


