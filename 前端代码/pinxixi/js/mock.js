/** 存放mockjs的代码：拦截ajax请求 **/

// 注册
// Mock.mock("/web-service/regist","post",{
//     "errno":"@integer(0,1)", // 返回状态码  0 成功  1  失败
//     "errmsg":"@csentence(5,20)" // 返回错误信息
// })

// 发送短信验证码
// Mock.mock("/web-service/sms","post",{
//     "errno":"@integer(0,1)", // 返回状态码  0 成功  1  失败
//     "errmsg":"@csentence(5,20)" // 返回错误信息
// })

// 登录接口
// Mock.mock("/auth-service/login","post",{
//     "errno":"@integer(0,1)", // 返回状态码  0 成功  1  失败
//     "errmsg":"@csentence(5,20)", // 返回错误信息
//     "token":"@string(64)", // 64位随机字符串
//     "name":"@cname" // 中文名
// })


// 模拟网站快照---不带参数
// Mock.mock("/web-service/news","get",{
//     "errno":"@integer(0,1)", // 返回状态码  0 成功  1  失败
//     "errmsg":"@csentence(5,20)", // 返回错误信息
//     "total":"@integer(200,400)",//总条数
//     "data|8":[{
//         "id|+1":1,
//         "title":"@ctitle(5,10)",
//         "created_at":"@date"
//     }]
// })
// 模拟网站快照---带参数
// get请求传递参数的时候，url需要正则表达式匹配  / /
// Mock.mock(/\/web-service\/news/,"get",{
//     "errno":"@integer(0,1)", // 返回状态码  0 成功  1  失败
//     "errmsg":"@csentence(5,20)", // 返回错误信息
//     "total":"@integer(200,400)",//总条数
//     "data|8":[{
//         "id|+1":1,
//         "title":"@ctitle(5,10)",
//         "created_at":"@date"
//     }]
// })

// 三级分类列表数据
// Mock.mock("/web-service/categorys","get",{
//     "errno":"@integer(0,1)", // 返回状态码  0 成功  1  失败
//     "errmsg":"@csentence(5,20)", // 返回错误信息
//     "data|5-13":[{ // 一级分类
//         "id|+1":1,//一级分类id
//         "cat_name":"@ctitle(2,4)",//一级分类名字
//         "children|3-9":[{// 二级分类
//             "id|+1":1,//二级分类id
//             "cat_name":"@ctitle(2,4)",//儿级分类名字
//             "children|3-8":[{// 三级分类
//                 "id|+1":1,//三级分类id
//                 "cat_name":"@ctitle(2,4)"//三级分类名字
//             }]
//         }]
//     }]
// })

// 楼层
Mock.mock("/web-service/floors","get",{
    "data|3-9":[{
        "floor_name":"@ctitle(2,4)",//楼层名字
        "sub_categorys|5-15":[{//为二级类目
            "id|+1":1,
            "cat_name":"@ctitle(2,4)"
        }],
        "left_ad":{
            "img":"@dataImage(208x170)",
            "link":"@url"
        },
        "right_ad":{
            "img":"@dataImage(310x100)",
            "link":"@url"
        },
        "news|2-5":[{
            "id|+1":1,
            "title":"@ctitle(8-20)"
        }],
        "brands|3-9":[{
            "id|+1":1,
            "logo":"@dataImage(98x35)"
        }],
        "rec_goods|3-8":[{
            "id|+1":1,
            "goods_name":"@ctitle(5,10)",
            "logo":"@dataImage(130x130)",
            "price":"@float(0.01,10000,2,2)"
        }],
        "rec_categorys|4":[{
            "id|+1":1,
            "cat_name":"@ctitle(2,4)",
            "goods|3-8":[{
                "id|+1":1,
                "goods_name":"@ctitle(5,10)",
                "logo":"@dataImage(130x130)",
                "price":"@float(0.01,10000,2,2)"
            }]
        }]

    }]

})


// 模拟商品信息
// 第一个参数：当请求是get的时候，如果传参，需要使用正则
// \d 在正则表达式中，代表数字0-9； +至少重复1次
/* Mock.mock(/\/web-service\/goods\/\d+/,"get",{
    "skuid|+1":1,
    "spuid|+1":1,
    "goods_name":"@ctitle(5,20)",
    "price":"@float(0.01,10000000,2,2)",
    "on_sale_date":"@datetime",// 上架时间
    "comment_count":"@integer",// 评论数量
    "comment_level":"@integer(1,5)",
    "cat1_info":{
        "id|+1":1,
        "cat_name":"@ctitle(2,4)"
    },
    "cat2_info":{
        "id|+1":1,
        "cat_name":"@ctitle(2,4)"
    },
    "cat3_info":{
        "id|+1":1,
        "cat_name":"@ctitle(2,4)"
    },
    "logo":{
        "smlogo":"@dataImage(50x50)",
        "biglogo":"@dataImage(350x350)",
        "xbiglogo":"@dataImage(800x800)",
    },
    "photos|3-10":[{
        "smimg":"@dataImage(50x50)",
        "bigimg":"@dataImage(350x350)",
        "xbigimg":"@dataImage(800x800)",
    }],
    "description":"@ctitle(2,20)",
    "aftersale":"@ctitle(2,20)",
    "stock":"@integer",//库存量
    "spec_list":[
        {
            "id":1,
            "spec_name":"颜色",
            "options":[
                {
                    "id":"3",
                    "option_name":"红色" 
                },
                {
                    "id":"4",
                    "option_name":"亮黑色" 
                },{
                    "id":"5",
                    "option_name":"深空灰色" 
                }
            ]
        },
        {
            "id":2,
            "spec_name":"内存",
            "options":[
                {
                    "id":"6",
                    "option_name":"64g" 
                },
                {
                    "id":"7",
                    "option_name":"256g" 
                }
            ]
        }
    ],
    "spec_info":{// 当前商品的默认信息  亮黑色   256g
        "id_list":"1:5|2:6",
        "id_txt":"颜色:亮黑色|内存:256g"
    },
    "sku_list":[
        {
            "skuid":"@integer(1,200000)",
            "id_list":"1:3|2:6"  // 红  64
        },
        {
            "skuid":"@integer(1,200000)",
            "id_list":"1:4|2:6"// 亮黑 64
        },
        {
            "skuid":"@integer(1,200000)",
            "id_list":"1:5|2:6" //深空灰  64
        },
        {
            "skuid":"@integer(1,200000)",
            "id_list":"1:3|2:7" // 红 256 
        },
        {
            "skuid":"@integer(1,200000)",
            "id_list":"1:4|2:7" // 亮黑 256
        },
        {
            "skuid":"@integer(1,200000)",
            "id_list":"1:5|2:7" // 深空灰  256
        }
    ]

})
 */

// 模拟商品评价的数据
// 带参数的get请求--注意点：需要使用正则表达式匹配url
/* Mock.mock(/\/web-service\/comments\/\d+/,"get",{
    "impressions|3-10":[{
        "title":"@ctitle(1,5)",
        "count":"@integer(1,100000000)"
    }],
    "ratio":{
        "goods":65,
        "common":30,
        "bad":5
    },
    "comment_count":"@integer(200,300)",
    "comments|3-10":[{// 具体评论内容
        "id|+1":1,
        "star":"@integer(1,5)",
        "created_at":"@datetime",
        "content":"@ctitle(1,200)",
        "user":{
            "id|+1":1,
            "face":"@dataImage(66x66)",
            "name":"@cname"
        }
    }] */



// })

// 获取购物车信息
// Mock.mock("/cart-service/carts","get",{
//     "data|3-10":[{
//         "skuid|+1":1,
//         "goods_name":"@ctitle(5,20)",
//         "price":"@float(0.01,100000,2,2)",
//         "count":"@integer(1,200)",
//         "checked":"@boolean",
//         "midlogo":"@dataImage(50x50)",
//         "spec_info":"颜色：红色|内存：64g"
//     }]
// })

// 登录之后，加入购物车接口
// Mock.mock("/cart-service/carts","post",{
//     "errno":"@integer(0,1)", // 返回状态码  0 成功  1  失败
//     "errmsg":"@csentence(5,20)" // 返回错误信息
// })

// 登录之后，加入购物车接口
// Mock.mock(/\/cart-service\/carts\/\d+/,"put",{
//     "errno":"@integer(0,1)", // 返回状态码  0 成功  1  失败
//     "errmsg":"@csentence(5,20)" // 返回错误信息
// })

// 获取商品列表页数据
// Mock.mock(/\/search-service\/goods/,"get",{
//     "errno":"@integer(0,1)", // 返回状态码  0 成功  1  失败
//     "errmsg":"@csentence(5,20)", // 返回错误信息
//     "count":"@integer(100,200)",
//     "catid":"@integer(1,100)",//第三级分类id
//     "data|30":[{
//         "id|+1":1,
//         "goods_name":"@ctitle(2,10)",
//         "price":"@float(0.01,100000,2,2)",
//         "midlogo":"@dataImage(130x130)",
//         "comment_count":"@integer(0,10000000)"
//     }]

// })

// 获取品牌--根据三级分类的id
// Mock.mock(/\/web-service\/brands\/\d+/,"get",{
//     "errno":"@integer(0,1)", // 返回状态码  0 成功  1  失败
//     "errmsg":"@csentence(5,20)", // 返回错误信息
//     "data|3-10":[{
//         "id|+1":1,
//         "brand_name":"@ctitle(2,5)",
//         "logo":"@dataImage(98x35)"
//     }]
// })

// 获取规格名字
// Mock.mock(/\/web-service\/specifications\/\d+/,"get",{
//     "errno":"@integer(0,1)", // 返回状态码  0 成功  1  失败
//     "errmsg":"@csentence(5,20)", // 返回错误信息
//     "data|3-5":[{
//         "id|+1":1,
//         "spec_name":"@ctitle(2,5)",//规格名字
//         "selected":"",//默认选中值不确定，先设置为空
//         "options|3-10":[{
//             "id|+1":1,
//             "option_name":"@ctitle(2,5)"
//         }]
//     }]


// })


//获取收货地址
Mock.mock("/web-service/address","get",{
    "data|3-10":[{
        "id|+1":1,
        "shr_name":"@cname",
        "shr_mobile":/1[34578]\d{9}/,
        "shr_province":"@province",
        "shr_city":"@city",
        "shr_county":"@county",
        "shr_address":"@ctitle(5,10)",
        "shr_default":"@integer(0,1)"
    }]
})

// 新增收件人地址
Mock.mock("/web-service/address","post",{
    "errno":"@integer(0,1)", // 返回状态码  0 成功  1  失败
    "errmsg":"@csentence(5,20)", // 返回错误信息
    "id":"@integer(10,100)"// 当前新增的收件人地址的id
})  

// 下单
Mock.mock("/order-service/orders","post",{
    "errno":"@integer(0,1)", // 返回状态码  0 成功  1  失败
    "errmsg":"@csentence(5,20)", // 返回错误信息
    "sn":"@integer(32)"// 订单编号
})

// 获取微信支付url
Mock.mock("/order-service/pay","post",{
    "errno":"@integer(0,1)", // 返回状态码  0 成功  1  失败
    "errmsg":"@csentence(5,20)", // 返回错误信息
    "wxurl":"@url"// 微信支付url
})

// 获取微信支付状态
Mock.mock(/\/order-service\/order_status\/\d+/,"get",{
    "errno":"0", // 返回状态码  0 成功  1  失败
    "errmsg":"@csentence(5,20)", // 返回错误信息
    "pay_status":"1"// 微信支付url
})

// 修改收货地址的数据
Mock.mock("/web-service/address","put",{
    "errno":"0", // 返回状态码  0 成功  1  失败
    "errmsg":"@csentence(5,20)"// 返回错误信息
})



