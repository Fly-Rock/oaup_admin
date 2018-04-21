# 详细页面结构比较复杂，可以抽象具体细节

整个页面结构可以分成下面几个结构骨架：

1.顶级头图展示：moduleName=top_banner(头图)

2.中间内容详细介绍：moduleName=detail_text_image（图文详解）

3.页面底部部分：moduleName=question（常见问题）

3.页面底部部分：moduleName=customer_hot_line(客服热线)

大体分成3个部分：通过moduleName来定义

每个内容中需要抽象具体的展示内容类型形式：
通过关键子：style 来定义类型：


detail_reminder  ——>温馨提示

detail_table     ——>表格
    table_style=a
    table_style=d
    
detail_image     ——>图片说明

detail_content   ——>文字说明


具体的接口文档：
request:
/cms/v1/product/detail?city=shanghai&productId=12344


response:
```
{
    "data": {
        "detail": [
            {
                "style": "detail_table",
                "table_style": "a",
                "contentListRowTable": [
                    {
                        "tableRow": [
                            {
                                "title": "项目名称",
                                "routerData": ""
                            },
                            {
                                "title": "非会员价格",
                                "routerData": ""
                            },
                            {
                                "title": "会员价格",
                                "routerData": ""
                            }
                        ]
                    },
                    {
                        "tableRow": [
                            {
                                "title": "单人床",
                                "routerData": ""
                            },
                            {
                                "title": "138元/张",
                                "routerData": ""
                            },
                            {
                                "title": "127元/张",
                                "routerData": ""
                            }
                        ]
                    }
                ]
            },
            {
                "style": "detail_zonghe",
                "contentListBlocks": [
                    {
                        "style": "detail_reminder",
                        "title": "温馨提示：",
                        "contentList": [
                            {
                                "title": "1.单人床与双人床上除螨仅包含一套床上四件套；"
                            },
                            {
                                "title": "2.1.2米及以下为单人床，其他规格为双人床;"
                            }
                        ]
                    }
                ],
                "title": "",
                "subtitle": ""
            },
            {
                "style": "detail_zonghe",
                "contentListBlocks": [
                    {
                        "style": "detail_image",
                        "bannerList": [
                            {
                                "image": "https://ayipic.ayibang.com/cms__a6ebc9c72d21de090d8cfbd49259e1c2_1495787073.jpg",
                                "pictureName": "XQY深度除螨_服务场景"
                            }
                        ]
                    }
                ],
                "title": "服务场景",
                "subtitle": "多方位舒适体验"
            },
            {
                "style": "detail_zonghe",
                "contentListBlocks": [
                    {
                        "style": "detail_content",
                        "title": "螨虫存在于居室的阴暗角落、地毯、床垫、枕头、沙发、空调、凉席等处，是过敏性皮炎、鼻炎和呼吸道疾病的重要元凶！"
                    },
                    {
                        "style": "detail_image",
                        "bannerList": [
                            {
                                "image": "https://ayipic.ayibang.com/cms__76020f3b04b0b2d73be2fb0d09f2a659_1495787172.jpg",
                                "pictureName": "XQY深度除螨_业务介绍"
                            }
                        ]
                    },
                    {
                        "style": "detail_reminder",
                        "title": "温馨提示：",
                        "contentList": [
                            {
                                "title": "即使室内环境非常干净的家庭，平均每张床上也有200万只螨虫。"
                            },
                            {
                                "title": "阳光暴晒6小时的螨虫杀灭率仅为3.8%。"
                            },
                            {
                                "title": "84.3%的皮肤过敏为螨虫过敏，儿童更高达91.6%"
                            }
                        ]
                    }
                ],
                "title": "业务介绍",
                "subtitle": "选择我们，选择放心"
            },
            {
                "style": "detail_zonghe",
                "contentListBlocks": [
                    {
                        "style": "detail_image",
                        "bannerList": [
                            {
                                "image": "https://ayipic.ayibang.com/cms__834e20d273ff93476b021d0f47ab4deb_1495787088.jpg",
                                "pictureName": "XQY深度除螨_服务流程"
                            }
                        ]
                    }
                ],
                "title": "服务流程",
                "subtitle": "多个流程，精细化服务"
            },
            {
                "style": "detail_zonghe",
                "contentListBlocks": [
                    {
                        "style": "detail_image",
                        "bannerList": [
                            {
                                "image": "https://ayipic.ayibang.com/cms__63a43df4defc4963ec43329dfa146aaa_1495787138.jpg",
                                "pictureName": "XQY深度除螨_工具装备"
                            }
                        ]
                    }
                ],
                "title": "工具装备",
                "subtitle": "专业装备、安全放心"
            },
            {
                "style": "detail_zonghe",
                "contentListBlocks": [
                    {
                        "style": "detail_image",
                        "bannerList": [
                            {
                                "image": "https://ayipic.ayibang.com/cms__1b4f5649867500dc63ce8b45f64c293d_1495787105.jpg",
                                "pictureName": "XQY深度除螨_服务特色"
                            }
                        ]
                    },
                    {
                        "style": "detail_content",
                        "title": "水过滤除螨避免污染"
                    },
                    {
                        "style": "detail_content",
                        "title": "云佳洁深度除螨使用的除螨仪依靠高频拍打和强大动力吸除螨虫，并通过水过滤系统收集螨虫，避免吸入机器中的螨虫弥散到空气中，造成螨虫在居室中的二次污染。"
                    },
                    {
                        "style": "detail_image",
                        "bannerList": [
                            {
                                "image": "https://ayipic.ayibang.com/cms__a2f8968dc6c5715881b374b8d1a72560_1495787123.jpg",
                                "pictureName": "XQY深度除螨_服务特色2"
                            }
                        ]
                    },
                    {
                        "style": "detail_content",
                        "title": "标准化流程全面除螨"
                    },
                    {
                        "style": "detail_content",
                        "title": "“灭-除-止”三步综合作用，消灭螨虫、吸除螨虫、喷洒除螨剂抑制螨虫，阿姨帮深度除螨从根源着手，兼顾长期效果。"
                    }
                ],
                "title": "服务特色",
                "subtitle": "安全、彻底、放心"
            }
        ],
        "share": {
            "title": "企业服务",
            "link": "",
            "isShow": false
        },
        "placeOrder": {
            "price": "89.99"
        },
        "supplier": {
            "title": "此服务由云佳洁平台优质服务商提供"
        },
        "topBanner": {
            "moduleType": 1,
            "moduleName": "头图",
            "bannerList": [
                {
                    "image": "https://ayipic.ayibang.com/cms__5207bd6bb5a2c54ec4484129df0a715d_1515484425.png",
                    "pictureName": "企业长期保洁-头图"
                }
            ]
        },
        "question": {
            "title": "常见问题",
            "link": "http://www.baidu.com"
        },
        "customerHotLine": {
            "title": "服务热线",
            "hotPhone": "010-89988484",
            "link": "http://www.baidu.com"
        }
    },
    "status": 0,
    "message": "success"
}
```