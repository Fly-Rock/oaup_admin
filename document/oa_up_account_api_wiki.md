账号相关接口文档整理：

域名：http://oaupccount.soa.com

1.用户注册 
url:/account
request:
```
{
    "userName": "18515526908",
    "passWord": "88903Hiejj*&^45",
    "email": "lichuanjie@aoup.com",
    "mobile": "",
    "smsCode": ""
}
```

response:
```
{
    "status": 0,
    "message": "OK",
    "data": {
        "userId": 26730447,
        "userName": "jas_on_08",
        "avatar": "http://i2.hjfile.cn/f200/04/47/26730447.jpg?r=635675512141745622",
        "email": "1111@erew_e.com",
        "status": 0,
        "accessToken": "000197DFCF.b8650b4882b9cb804400033add6a6667",
        "expireIn": 1209600,
        "refreshToken": "df99eec7cd5a0c407dc69412d5f5f0737b62cd97",
        "unionId": "e71d265c8b4e80255619f28022e4eacb"
    }
}
```
2.登录：
url:/authorize
request:
```
{
    "account": "String",   //用户名、手机号或邮箱地址
    "passWord": "String",  //密码，md5加密，32位或16位
    "smsCode": "String",  //登录短信码,当使用手机号登录时该项为必填
    "loginType": 0       //登录类型:帐号登录 - 1000,短信登录 - 1001(不传默认1000)
}
```
response:
```
{
    "status": 0,
    "message": "OK",
    "data": {
        "userId": 33395724,
        "userName": "qinfeng1229",
        "avatar": "http://7xtdhi.com2.z0.glb.qiniucdn.com/u/avator/200/default.jpg",
        "email": "qinfeng1229@hujiang.com",
        "mobile": "",
        "status": 0,
        "accessToken": "0001fd940c.21d90bcd24442c619791216a16b2cce6",
        "expireIn": 2592000,
        "refreshToken": "947d4e39f81f18c7a9e139fb8f70d1cb411bb060",
        "unionId": "15a1db5219784ba82625c4beede0f99c"
    }
}
```

