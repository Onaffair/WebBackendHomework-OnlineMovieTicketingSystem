<script setup>
/* eslint-disable */
import {ref, reactive, defineEmits} from "vue";
import {Plus} from "@element-plus/icons-vue";
import {postAvatar, postRegisterInfo} from "@/api";
import {ElMessage} from "element-plus";
import {useUserStore} from "@/store/userStore";

const emit = defineEmits(['close'])
const state = useUserStore()

const registerInfo = reactive({
    username: '',
    password: '',
    confirmPwd: '',
    email: '',
    phone: '',
    nickname: '',
    avatar: '',
});

const rules = reactive({
    username: [
        { required: true, message: '用户名不能为空', trigger: 'blur' },
    ],
    password: [
        { required: true, message: '密码不能为空', trigger: 'blur' },
    ],
    confirmPwd: [
        { required: true, message: '确认密码不能为空', trigger: 'blur' },
        {
            validator: (rule, value) => {
                if (value !== registerInfo.password) {
                    return Promise.reject('两次密码不一致');
                }
                return Promise.resolve();
            },
            trigger: 'blur',
        },
    ],
    nickname: [
        { required: true, message: '昵称不能为空', trigger: 'blur' },
    ],
    contact: [
        {
            validator: () => {
                if (!registerInfo.email && !registerInfo.phone) {
                    return Promise.reject('邮箱和手机号至少填写一个');
                }
                return Promise.resolve();
            },
            trigger: 'blur',
        },
    ],
});

const formRef = ref(null);

const cancel = () => {
    emit('close');
};


const handleChanges = (file) => {
    if (file.raw.size / 1024 / 1024 > 5){
        ElMessage("头像大小不能大于5MB")
        return false
    }
    const param = new FormData()
    param.append('file', file.raw)
    postAvatar(param)
        .then(res => res.data)
        .then(res => {
            registerInfo.avatar = res.data
        })
        .catch(error => console.log(error))
}

const register = () => {
    formRef.value.validate(async (valid) => {
        if (valid) {
            delete registerInfo.confirmPwd
            const res = await postRegisterInfo(registerInfo)
            if (res.code === 200){
                cancel()
                ElMessage("注册成功，已为您自动登录")
                state.setIsLogin(true)
                state.setUser(res.data)
            } else{
                ElMessage(res.msg)
            }
        }
    });
};
</script>


<template>
    <div class="modal-overlay">
        <div class="modal-content">
            <h2 style="text-align: center">注册</h2>

            <el-form
                ref="formRef"
                :model="registerInfo"
                :rules="rules"
                label-width="auto"
            >
                <div style="display:flex;justify-content: center" >
                    <el-upload
                        class="avatar-uploader"
                        :on-change="handleChanges"
                        :show-file-list="false"
                        :limit="1"
                        action="#"
                        accept=".jpg"
                        :auto-upload="false"
                    >
                        <el-avatar v-if="registerInfo.avatar" :src="registerInfo.avatar" class="avatar" />
                        <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
                    </el-upload>
                </div>
                <div style="margin: 20px">
                    <el-form-item label="用户名" prop="username" >
                        <el-input
                            v-model="registerInfo.username"
                            placeholder="请输入用户名"
                            clearable
                        ></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                        <el-input
                            v-model="registerInfo.password"
                            placeholder="请输入密码"
                            clearable
                            show-password
                        ></el-input>
                    </el-form-item>
                    <el-form-item label="确认密码" prop="confirmPwd">
                        <el-input
                            v-model="registerInfo.confirmPwd"
                            placeholder="请确认密码"
                            clearable
                            show-password
                        ></el-input>
                    </el-form-item>
                    <el-form-item label="昵称" prop="nickname">
                        <el-input
                            v-model="registerInfo.nickname"
                            placeholder="请输入昵称"
                            clearable
                        ></el-input>
                    </el-form-item>
                    <el-form-item label="邮箱" prop="contact">
                        <el-input
                            v-model="registerInfo.email"
                            placeholder="请输入邮箱"
                            clearable
                        ></el-input>
                    </el-form-item>
                    <el-form-item label="手机号" prop="contact">
                        <el-input
                            v-model="registerInfo.phone"
                            placeholder="请输入手机号"
                            clearable
                        ></el-input>
                    </el-form-item>
                </div>
                <div  style="margin-top: 5%" class="btn-pos">
                    <el-button type="primary" @click="register" class="btn-size">注册</el-button>
                </div>
                <div class="btn-pos">
                    <el-button type="primary" @click="cancel" class="btn-size">取消</el-button>
                </div>
            </el-form>
        </div>
    </div>
</template>


<style scoped>
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
    width: 30%;
    height: 75%;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}
.btn-size{
    height: 40px;
    margin: auto;
    width: 80%;
}
.avatar-uploader .avatar {
    width: 100px;
    height: 100px;
    display: block;
}
</style>
<style>
.btn-pos{
    margin-top: 1%;
    display: flex;
    justify-content: center;
}
.avatar-uploader .el-upload {
    border: 1px dashed var(--el-border-color);
    border-radius: 100px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    transition: var(--el-transition-duration-fast);
}
.avatar-uploader .el-upload:hover {
    border-color: var(--el-color-primary);
}
.el-icon.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 100px;
    height: 100px;
    text-align: center;
}
</style>