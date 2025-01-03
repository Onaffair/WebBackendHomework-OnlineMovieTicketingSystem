import _axios from "@/utils";
/* eslint-disable */


const postRegisterInfo = (data) =>{
    return _axios({
        url:'/api/user/register',
        method:'post',
        data:data
    }).then(res => res.data)
        .catch(error =>{
            console.log(error)
        })
}
const postLoginInfo = (data) =>{
    return _axios({
        url:'/api/user/login',
        method:'post',
        data:data
    }).then(res => res.data)
        .catch(error =>{
            console.log(error)
        })
}
const postAvatar = (data) =>{
    return _axios({
        url:'/api/user/avatar',
        method:'post',
        data:data
    })
}
const getMovieList = (data) =>{
    return _axios({
        url:'/api/movie/list',
        method:'get',
        params:data
    }).catch(error =>{
        console.log(error)
    })
}

const getCinemaList = (data) =>{
    return _axios({
        url:'/api/cinema/list',
        method:'get',
        params:data
    })
}

const getMovieInfo = (id) =>{
    return _axios({
        url:'/api/movie/getMv',
        method:'get',
        params: id
    })
}

const getCinemaMovieScreening = (id) =>{
    return _axios({
        url:'/api/cinema/screening',
        method:'get',
        params:{cinemaId:id}
    }).catch(error => console.log(error))
}

const getAllUsers = () =>{
    return _axios({
        url:'/api/user/all',
        method:'get'
    }).catch(error => console.log(error))
}
const postOrderInfo = (data) =>{
    return _axios({
        url:'/api/order/changeOrder',
        method:'post',
        data:data
    }).catch(error => console.log(error))
}
const getOrderInfo= (id) =>{
    return _axios({
        url:'/api/order/getOrder',
        method:'get',
        params:{
            userId:id
        }
    }).catch(error => console.log(error))
}
const getOrderList = (idList) =>{
    return _axios({
        url:'/api/order/orderList',
        method:'get',
        params:{idList:idList.join(',')}
    }).catch(error => console.log(error))
}
const postCollectionInfo = (data) =>{
    return _axios({
        url:'/api/collection/changeCollection',
        method:'post',
        data:data
    }).catch(error => console.log(error))
}
const getCollectionInfo = (id) =>{
    return _axios({
        url:'/api/collection/collectionList',
        method:'get',
        params:{
            userId:id
        }
    }).catch(error => console.log(error))
}
const getCollectionList = (movieIds) =>{
    return _axios({
        url:'/api/collection/getCollection',
        method:'get',
        params:{
            movieIds:movieIds.join(',')
        }
    }).catch(error => console.log(error))
}
const getSeatInfo = (screeningId) =>{
    return _axios({
        url:'/api/seat/getSeatByScreening',
        method:'get',
        params:{
            screeningId:screeningId
        }
    }).catch(error => console.log(error))
}



export {
    postRegisterInfo,
    postLoginInfo,
    postAvatar,
    getMovieList,
    getCinemaList,
    getMovieInfo,
    getCinemaMovieScreening,
    getAllUsers,
    postOrderInfo,
    postCollectionInfo,
    getCollectionInfo,
    getOrderInfo,
    getSeatInfo,
    getOrderList,
    getCollectionList,


}