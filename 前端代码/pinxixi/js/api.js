/** axios：ajax请求 **/
/* 所有ajax的代码写到这里 */
axios.defaults.baseURL = 'http://localhost:9091/v1'
// 设置AJAX超时时间
axios.defaults.timeout = 3000
// 设置提交数据时的格式
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'
// request请求拦截器
axios.interceptors.request.use(function (config) {
    // 判断如果用户登录了就把 token 配置到 axios 的协议头中
    let token = localStorage.getItem('token')
    if (token) {
        config.headers['authorization'] = token
    }
    // 处理请求前代码
    return config;
}, function (error) {
    // Do something with request error
    return Promise.reject(error);
});



// 注册 
function regist(params) {
    // 忘记填写return
    return axios.post("/web-service/regist", params)
}

// 发送短信验证码
function sendSMS(params) {
    return axios.post("/web-service/sms", params)
}

// 登录
function login(params) {
    return axios.post("/auth-service/login", params)
}

// 获取网站快报--不带参数
// function getTopNews(){
//     return axios.get("/web-service/news")
// } 


// 获取网站快报--带参数
function getTopNews() {
    return axios.get("/web-service/news", {
        params: {
            // 查询的条数
            "limit": 8,
            // 排序字段
            "sort_by": "id",
            // 排序规则,降序
            "sort_way": "desc"
        }
    })
}

// 获取三级分类数据
function getCategorys() {
    return axios.get("/web-service/categorys")
}

// 获取楼层数据
function getFloors() {
    return axios.get("/web-service/floors")
}


// 获取商品详情
function getGoodsInfo(skuid) {
    return axios.get("/web-service/goods/" + skuid)
}

// 获取商品评论内容
// spuid:商品的评价是针对SPU的
// per_page：每页条数
// page:当前页
function getComments(spuid, per_page, page) {
    return axios.get("/web-service/comments/" + spuid + "?limit=" + per_page + "&page=" + page + "&sort_by=id&sort_way=desc")
}

// 获取购物车数据
function getCarts() {
    return axios.get("/cart-service/carts")
}

// 加入购物车
function addCarts(params) {
    return axios.post("/cart-service/carts", params)
}
//删除购物车选项
function deleteCart(id){
    return axios.delete("/cart-service/carts/"+id)
}

// 修改购物车
function updateCarts(skuid, params) {
    return axios.post("/cart-service/carts/" + skuid, params)
}


// 获取商品数据
function searchGoods(search){
    return axios.get("/search-service/goods",{
        params:search
    })
}

// 获取品牌数据--根据第三级分类的id
function getBrands(catid){
    return axios.get("/web-service/brands/"+catid)
}

// 获取品牌数据
function getSpecifications(catid){
    return axios.get("/web-service/specifications/"+catid)
}

// 获取收货人的地址
function getAddress(){
    return axios.get("/web-service/address")
}

// 新增收货人地址
function addAddress(params){
    return axios.post("/web-service/address",params)
}

// 下单 
function addOrder(params){
    return axios.post("/order-service/orders",params)
}

// 获取微信支付url
function pay(params){
    return axios.post("/order-service/pay",params)
}

// 获取支付状态
function getOrderStatus(sn){
    return axios.get("/order-service/order_status/"+sn)
}

// 修改收货地址
function updateAddress(params){
    return axios.put("/web-service/address",params)
}

