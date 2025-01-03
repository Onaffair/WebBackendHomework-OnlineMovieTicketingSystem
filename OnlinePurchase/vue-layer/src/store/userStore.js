/* eslint-disable */
import {defineStore} from 'pinia'
import {computed, reactive, ref} from "vue";


export const useUserStore = defineStore(
    'userStore',
    {
        state: () => ({
            isLogin: false,
            user: null,
            collection:[],
            order:[]
        }),
        getters: {
            getState: (state) => state,
            getUser: (state) => state.user,
            getIsLogin: (state) => state.isLogin,
            getCollection: (state) => state.collection,
            getOrder: (state) => state.order,
        },
        actions: {
            setUser(data) {
                this.user = data
            },
            setIsLogin(data) {
                this.isLogin = data
            },
            addCollection(data) {
                this.collection.push(data)
            },
            removeCollection(data) {
                this.collection.splice(this.collection.indexOf(data),1)
            },
            setCollection(data) {
                this.collection = data
            },
            addOrder(data) {
                this.order.push(data)
            },
            removeOrder(data) {
                this.order.splice(this.order.indexOf(data),1)
            },
            setOrder(data) {
                this.order = data
            },
            quit() {
                this.setUser(null)
                this.setIsLogin(false)
                this.setCollection([])
                this.setOrder([])
            }
        },
        persist: {
            key: 'userStore',
            storage: localStorage
        }
    }
)