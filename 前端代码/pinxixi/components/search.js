Vue.component("search",{
    template:`<div class="search fl">
                <div class="search_form">
                    <div class="form_left fl"></div>
                    <form action="" name="serarch" method="get" class="fl">
                        <input type="text" class="txt" v-model="keyWord" value="请输入商品关键字" />
                        <input type="button" class="btn" value="搜索" @click.prevent="submit" />
                    </form>
                    <div class="form_right fl"></div>
                </div>
                
                <div style="clear:both;"></div>

                <div class="hot_search">
                    <strong>热门搜索:</strong>
                    <a href="">D-Link无线路由</a>
                    <a href="">休闲男鞋</a>
                    <a href="">TCL空调</a>
                    <a href="">耐克篮球鞋</a>
                </div>
            </div>`,
    data(){// 要求data一定是一个方法，必须要有返回值，其他都是一样
        return {
            keyWord:sessionStorage.getItem("keyWord")
        }
    },
    methods:{
        submit(){
            // 页面跳转至list.html 
            // location.href="list.html?keyWord="this.keyWord 方法一

            // 方法二
            // 页面跳转前，将数据放入sessionStorage中
            sessionStorage.setItem("keyWord",this.keyWord)

            location.href="list.html"

        }
    },
    created(){

    },
    mounted(){

    }        

})