import {createRouter,createWebHashHistory} from "vue-router";


const routes = [
    {
        path: '/',
        redirect: '/dashboard',
    },
    {
        path: '/dashboard',
        name: 'dashboard',
        redirect: '/u/main',
        component: () => import('../views/dashboard.vue'),
        children: [
            {
              path: '/u/main',
              name: 'main',
              component: () => import('../views/mainPage.vue'),
            },
            {
                path: '/u/cinemas',
                name : 'cinemas',
                component: () => import('../views/cinemaView/cinemasPage.vue'),
                redirect: '/u/cinemas/cinemaList',
                children: [
                    {
                        path: '/u/cinemas/cinemaList',
                        name: 'cinemaList',
                        component: () => import('../views/cinemaView/cinemaList.vue'),
                        props(route){
                            return route.query
                        }
                    }
                ]
            },
            {
                path: '/u/movies',
                name : 'movies',
                component: () => import('../views/movieView/moviesPage.vue'),
                redirect: '/u/movies/movieList',
                children: [
                    {
                        path: '/u/movies/movieList',
                        name: 'movieList',
                        component: () => import('../views/movieView/movieList.vue'),
                        props(route){
                            return route.query
                        }
                    },
                ]
            },
            {
                path: '/u/movieDetail',
                name: 'movieDetail',
                component: () => import('../views/movieView/movieDetail.vue'),
                props(route){
                    return route.query
                }
            },
            {
                path: '/u/cinemaDetail',
                name: 'cinemaDetail',
                component: () => import('../views/cinemaView/cinemaDetail.vue'),
                props(route){
                    return route.query
                }
            }
        ]
    },
    {
        path:'/PersonalCenter',
        name: 'PersonalCenter',
        redirect: '/PersonalCenter/userinfo',
        component : () => import('../views/personView/PersonalCenter.vue'),
        children: [
            {
                path: '/PersonalCenter/order',
                name: 'userOrder',
                component: () => import('../views/personView/userOrder.vue'),
            },
            {
                path: '/PersonalCenter/collection',
                name: 'userCollection',
                component: () => import('../views/personView/userCollection.vue'),
            },
            {
                path: '/PersonalCenter/userInfo',
                name: 'userInfo',
                component: () => import('../views/personView/userInfo.vue'),
            },
        ]
    },
    {
        path: "/404",
        name: "404",
        component: () => import('../views/NotFound.vue'),
    },
    { path: '/:path(.*)', redirect: '/404' },
]


const router = createRouter({
    history: createWebHashHistory(),
    routes,
})

export default router