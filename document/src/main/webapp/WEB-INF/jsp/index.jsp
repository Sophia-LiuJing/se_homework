<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>图片上传</title>
    <style type="">
        body {
            font-size: 30px;
            background: url("${pageContext.request.contextPath}/bg.gif");
        }

        * {
            margin: 0;
            padding: 0;
        }

        .box {
            width: 600px;
            background-color: rgba(254, 254, 254, 0.5);
            border-radius: 10px;
            padding: 20px;

            margin-top: 10% !important;
            margin: 0 auto;
        }
        .box2 {
            width: 600px;

            margin-top: 20px !important;
            background-color: rgba(254, 254, 254, 0.5);
            border-radius: 10px;
            padding: 20px;
            margin: 0 auto;

        }
        .xzwj {

            font-size: 24px;

        }

        .dwsc {
            width: 20%;
            height: 40px;
            font-size: 24px;
            background-color: white;
            color: #ccc;
            border-radius: 50px;
            border: 0;
            float: right;
            margin-right: 10px;
            text-align: center;
            line-height: 40px;

        }

        .ule {


        }
        .uls {


        }

        li {
            list-style: none;
            height: 40px;
            margin-top: 20px;
            line-height: 40px;

        }

        li a {
            text-decoration: none;
            color: #fff;
            float: right;
            font-size: 18px;
            margin-top: 3px;
            margin-left: 5px;
            margin-right: 5px;
        }
        .uls{

        }
       span{
           width: 300px;
           height: 40px;
           color: #fff;
           overflow:hidden;
           text-overflow:ellipsis;
           white-space:nowrap;
           /*display: block;*/


       }#input {position: absolute;top: 0;left: 0;opacity: 0;z-index: -10;}
        .fz{
            width: 10%;
            height: 20px;
            font-size: 12px;
            background-color: white;
            color: #ccc;
            border-radius: 50px;
            border: 0;
            text-align: center;
            line-height: 20px;
           float: right;
            margin-right: 10px;
            margin-top: 13px;
        }
        .fz2{
            width: 10%;
            height: 20px;
            font-size: 12px;
            background-color: white;
            color: #ccc;
            border-radius: 50px;
            border: 0;
            text-align: center;
            line-height: 20px;
            margin-bottom: 10px;

        }
    </style>
</head>
<body>
<input id="input" type="text"/>
<div class="box">
    <div>
        <form action="/upload" method="post" enctype="multipart/form-data">
            <input type="file" id="file" class="xzwj" name="file" multiple/>
            <button type="submit" class="dwsc">点我上传</button>
        </form>
        <div id="notice"></div>
    </div>
    <ul class="ule">
    </ul>

</div>
<div class="box2">
<form action="/ztbAdd" method="post" enctype="multipart/form-data">
    <textarea rows="4" cols="60" name="content"></textarea>
    <button type="submit" class="fz2">点我上传</button>

</form><ul class="uls">

</ul>
</div>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script>
    var a,
        b,
        str = '',
        urlxz = '',
        urlsc=''

    const ule = document.querySelector('.ule');
    axios.get('${pageContext.request.contextPath}/fileList')
        .then((value) => {
            a = value.data;
            for (var i = 0; i < a.length; i++) {
                urlxz = "/download/" + a[i].url;
                urlsc = "/delete/" + a[i].url;
                str += '<li>' + a[i].url + '&nbsp'+'<a href="' + urlxz + '">' + '点击下载' + '  </a>'+ '<a href="' + urlsc + '">' + '点击删除' + '  </a>'  + '</li>'
            }
            ule.innerHTML = str;
        })

var c,
    str23=''
    const uls = document.querySelector('.uls');
    axios.get('${pageContext.request.contextPath}/ztbList')
        .then((value) => {
            c= value.data;
            for (var y= 0; y<c.length; y++) {
                urlxz = "/download/" + c[y].body;
                str23 += '<li>' +'<span>' +c[y].body +'</span> '+ '<button class'+'="fz">'+'复制'+'</button>'+'</li>'
                console.log(c)
            }
            uls.innerHTML = str23;

            var btn=document.querySelector('.fz')
            console.log(btn)
            btn.addEventListener('click',function (){
                var text = document.querySelector("span").innerText;
                var input = document.getElementById("input");
                input.value = text; // 修改文本框的内容
                input.select(); // 选中文本
                document.execCommand("copy"); // 执行浏览器复制命令

            })
        })


</script>
<script>

</script>
</body>
</html>