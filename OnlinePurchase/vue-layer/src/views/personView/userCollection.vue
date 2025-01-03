<script setup>
/* eslint-disable */
import {useUserStore} from "@/store/userStore";
import {onBeforeMount, ref} from "vue";
import {getCollectionList} from "@/api";
import CollectionItem from "@/components/CollectionItem.vue";


const state = useUserStore();
const collectionInfo = ref(null)

const cancelCollection = (movieId) =>{
    state.removeCollection(movieId)
    collectionInfo.value = collectionInfo.value.filter(item => item.movieId !== movieId)
}

onBeforeMount(() =>{
    if (state.getCollection.length !== 0){
        getCollectionList(state.getCollection)
            .then(res => res.data)
            .then(res =>{
                collectionInfo.value = res.data
            })
    }
})

</script>

<template>
        <el-row>
            <el-col :span="4"></el-col>
            <el-col :span="16">
                <div v-if="state.getCollection.length !== 0">
                    <CollectionItem
                        :userId="state.getUser.userId"
                        :movieData="item"
                        v-for="(item,index) of collectionInfo"
                        :key="index"
                        @cancel="cancelCollection"
                    />
                </div>
                <div v-else>
                    无收藏
                </div>
            </el-col>
        </el-row>
</template>

<style scoped>

</style>