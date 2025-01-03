<script setup>
/* eslint-disable */
import { ref, defineEmits } from "vue";
import {getCollectionInfo, getOrderInfo, postLoginInfo} from "@/api";
import {ElMessage} from "element-plus";
import {useUserStore} from "@/store/userStore";

const loginInfo = ref({
    username: '',
    password: ''
});
const state = useUserStore()

const emit = defineEmits(['close']);

// 定义表单引用
const formRef = ref(null);

const login = async () => {
    // 调用表单校验
    formRef.value.validate(async (valid) => {
        if (valid) {
            await postLoginInfo(loginInfo.value)
                .then(res => {
                    if (res.code === 200) {
                        /*更新用户信息*/
                        state.setIsLogin(true)
                        state.setUser(res.data)

                        getCollectionInfo(state.getUser.userId)
                            .then(res => res.data)
                            .then(res => {
                                state.setCollection(res.data)
                            })
                        getOrderInfo(state.getUser.userId)
                            .then(res => res.data)
                            .then(res =>{
                                state.setOrder(res.data)
                            })
                        cancel();
                    } else {
                        ElMessage("账号或密码错误")
                    }
                })
        }
    });
};

const cancel = () => {
    emit('close');
};

// 定义校验规则
const rules = {
    username: [
        { required: true, message: "用户名不能为空", trigger: "blur" },
    ],
    password: [
        { required: true, message: "密码不能为空", trigger: "blur" },
    ]
};
</script>

<template>
    <div class="modal-overlay">
        <div class="modal-content">
            <h2 style="text-align: center">登录</h2>
            <!-- 添加表单校验 -->
            <el-form
                ref="formRef"
                :model="loginInfo"
                :rules="rules"
                @keydown.enter="login"
                label-width="auto"
            >
                <el-form-item label="用户名" prop="username">
                    <el-input
                        v-model="loginInfo.username"
                        placeholder="请输入用户名"
                        clearable
                    ></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input
                        v-model="loginInfo.password"
                        type="password"
                        placeholder="请输入密码"
                        clearable
                    ></el-input>
                </el-form-item>
                <div style="margin-top: 20%" class="btn-pos">
                    <el-button type="primary" @click="login"  class="btn-size" >登录</el-button>
                </div>
                <div class="btn-pos">
                    <el-button type="primary" @click="cancel" class="btn-size">取消</el-button>
                </div>

            </el-form>
        </div>
    </div>
</template>

<style scoped>
.btn-pos{
    margin-top: 5%;
    display: flex;
    justify-content: center;
}
/* 模态窗口遮罩层 */
.modal-overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.5);
    display: flex;
    align-items: center;
    justify-content: center;
    z-index: 1000;
}

/* 模态窗口内容 */
.modal-content {
    background: white;
    width: 25%;
    height: 40%;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}
.btn-size {
    height: 40px;
    margin: auto;
    width: 80%;
}
</style>
