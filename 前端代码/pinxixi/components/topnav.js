Vue.component("topnav",{
    template:`<div class="topnav">
                <div class="topnav_bd w1210 bc">
                    <div class="topnav_left">
                        
                    </div>
                    <div class="topnav_right fr">
                        <ul>
                            <li v-if="name==null">您好，欢迎来到商城！[<a href="login.html">登录</a>] [<a href="register.html">免费注册</a>] </li>
                            <li v-else>欢迎{{name}}来到商城！[<a href="order.html">我的订单</a>] [<a href="" @click.prevent="logout">注销</a>] </li>
                            <li class="line">|</li>
                            <li>客户服务</li>

                        </ul>
                    </div>
                </div>
            </div>`,
    data(){
        return {
            name:localStorage.getItem("name")// 从localStorage中获取name
        }
    },
    methods:{
        logout(){
            // 移除localStorage中的token
            localStorage.removeItem("token")
            // 移除localStorage中的name
            localStorage.removeItem("name")
            // 清空name 的数据
            this.name=null
            
        }
    }        


})

// 一个*.js文件中允许定义多个组件
// Vue.component("",{

// })



