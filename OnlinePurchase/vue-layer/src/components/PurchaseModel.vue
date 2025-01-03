<script setup>
/* eslint-disable */
import {onBeforeMount, onMounted, reactive, ref} from "vue";
import {getSeatInfo, postOrderInfo} from "@/api";
import {useUserStore} from "@/store/userStore";

const emit = defineEmits(['close']);
const user = useUserStore();
const props = defineProps(
    {
        screeningId:{
            required:true
        },
        userId:{
            required:true
        },
        price:{
            type:Number,
            default:null
        }
    }
)

// 定义64个座位的数据
const seatMap = ref(
    Array(64).fill(null).map(() => ({
        available: true,
        selected: false
    }))
);
const selectedSeatIndex = ref(null);
const purchaseInfo = reactive({
    visible:false,
    data:{
        userId:props.userId,
        screeningId:props.screeningId,
        seatId:0
    }
})

// 切换座位选中状态
const toggleSeatSelection = (index) => {
    const seat = seatMap.value[index];

    // 仅可选择可用座位
    if (seat.available) {
        // 如果之前已经选中座位，取消选中
        if (selectedSeatIndex.value !== null && selectedSeatIndex.value !== index) {
            seatMap.value[selectedSeatIndex.value].selected = false;
        }

        // 选中当前座位
        seat.selected = !seat.selected;
        selectedSeatIndex.value = seat.selected ? index : null;
    }
};

// 确认选中座位
const submit = () => {

    purchaseInfo.data = {
        userId:props.userId,
        screeningId: props.screeningId,
        seatId: selectedSeatIndex.value+1
    }
    purchaseInfo.visible = true;
};

// 关闭模态框
const closeModel = () => {
    emit('close');
};
const ensurePurchase = () => {
    postOrderInfo(purchaseInfo.data)
        .then(res => res.data)
        .then(res => {
            if (res.code === 200){
                user.addOrder(res.data)
                console.log(user.getOrder)
            }
        })

    purchaseInfo.visible = false
    closeModel()
}
onBeforeMount(() =>{
    getSeatInfo(props.screeningId)
        .then(res => res.data)
        .then(res =>{
            res.data.forEach(seat =>{
                seatMap.value[seat-1].available = false
            })
        })
})
</script>


<template>
    <div class="modal-overlay">
        <div class="modal-content">
            <h3 style="text-align: center;">选择座位</h3>
            <!-- 座位表 -->
            <div class="seat-map">
                <div
                    class="seat"
                    v-for="(seat, index) in seatMap"
                    :key="index"
                    :class="{'available': seat.available, 'selected': seat.selected, 'unavailable': !seat.available}"
                    @click="toggleSeatSelection(index)"
                >
                    {{ index + 1 }}
                </div>
            </div>

            <div style="margin-top: 5%" class="btn-pos">
                <el-button type="primary" @click="submit"  class="btn-size" >买票</el-button>
            </div>
            <div class="btn-pos">
                <el-button type="primary" @click="closeModel" class="btn-size">取消</el-button>
            </div>
        </div>
        <el-dialog
            v-model="purchaseInfo.visible"
            title="确认购买"
            width="500"
            align-center
            :before-close="ensurePurchase"
            :close-on-click-modal="true"
        >
            <span>确认花费{{props.price}}元购买此票？</span>
            <template #footer>
                <el-button @click="purchaseInfo.visible=false">取消</el-button>
                <el-button type="primary" @click="ensurePurchase">确认</el-button>
            </template>
        </el-dialog>
    </div>
</template>


<style scoped>
.seat-map {
    display: grid;
    grid-template-columns: repeat(8, 1fr);  /* 每行8个座位 */
    grid-gap: 10px;
    margin-bottom: 20px;
}

/* 座位样式 */
.seat {
    width: 40px;
    height: 40px;
    border: 1px solid #ccc;
    display: flex;
    align-items: center;
    justify-content: center;
    cursor: pointer;
    font-size: 14px;
    background-color: #f0f0f0;
}

.seat.available {
    background-color: #c3e4a1;  /* 可选座位背景色 */
}

.seat.selected {
    background-color: #d77d23;  /* 已选座位背景色 */
}

.seat.unavailable {
    background-color: #3c3636;  /* 不可选座位背景色 */
    cursor: not-allowed;
}

.seat:hover {
    background-color: #a5d6a7;  /* 鼠标悬停时座位颜色 */
}
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
    width: 50%;
    height: 80%;
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