import axios from "axios";

const _axios = axios.create(
    {
        baseURL: '/api',
        timeout: 10000,
    }
)

_axios.interceptors.request.use(config => {
    return config;
}, error => {
    return Promise.reject(error);
})
_axios.interceptors.response.use(config =>{
    return config;
},error => {
    return Promise.reject(error);
})

export default _axios;