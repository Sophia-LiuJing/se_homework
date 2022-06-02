import os

from flask import request, redirect, Flask, send_file
from pathlib import Path

app = Flask(__name__)
root = Path.cwd() / "__files__"
root.mkdir(exist_ok=True)

# 亟待优化，建议单独创建一个css文件夹，看着更工程化
css = """ 
.form {
    display: flex;
    flex-direction: column;
    gap: 1rem;
    width: max-content;
}
.logo-box {
        height: 200px;
        left: calc(50% - 175px);
        position: absolute;
        top: calc(50% - 300px);
        width: 350px;
    }
.main-box {
    margin: 0 calc(50% - 112px);
    margin-top: 0px;
    margin-right: calc(50% - 112px);
    margin-bottom: 0px;
    margin-left: calc(50% - 112px);
    position: absolute;
    top: 60%;
    transition: opacity .25s;
    transition-property: opacity;
    transition-duration: 0.25s;
    transition-timing-function: ease;
    transition-delay: 0s;
    width: 224px;
    z-index: 1;
}
.next-box {
    margin: 0 calc(50% - 112px);
    margin-top: 0px;
    margin-right: calc(50% - 112px);
    margin-bottom: 0px;
    margin-left: calc(50% - 112px);
    position: absolute;
    top: 50%;
    transition: opacity .25s;
    transition-property: opacity;
    transition-duration: 0.25s;
    transition-timing-function: ease;
    transition-delay: 0s;
    width: 224px;
    z-index: 1;
}
.upload-file{
      position: relative;
      width: 100%;
      height: 49px;
      padding: 0px;
      border: 1px solid rgb(30,144,255);
      border-radius: 50px;
      background-color: rgb(30,144,255);
      color: #ffffff;
      font-size: 14px;
      text-align: center;
      overflow: hidden;
    }

    .upload-file span{ //单行显示
      text-overflow: ellipsis;
      white-space: nowrap;
      overflow: hidden;
    }

    .upload-file:hover{ //简单的hover效果
      font-size: 15px;
      border-color: rgb(39, 226, 81);
    }

    .upload-file input[type='file']{
      height: 100%;
      width: 100%;
      position: absolute; //设置为绝对定位，不会影响到其他元素
      top: 0;
      right: 0;
      opacity: 0;   //透明度为0
      filter: alpha(opacity=0);
      cursor: pointer;
    }

.main-btn {
    position: relative;
    width: 100%;
    height: 49px;
    padding: 0px;
    border: 1px solid rgb(176,226,255);
    border-radius: 50px;
    background-color: rgb(255,250,250);
    color: #1E90FF;
    font-size: 17px;
    font-weight:bold;
    text-align: center;
}
.test ul{list-style:none;}
.test li{float:left;}
"""

# 上传和下载功能，未来功能设想，删除已上传文件-->设置用户权限区分--->应用数据库
@app.route("/upload/<path:name>", methods=["POST"])
def upload_file(name):
    if "main.py" in name:
        return "Failure"
    with open(root / name, "wb+") as fout:
        for line in request.files["file"].stream:
            fout.write(line)

    return "Success"

@app.route("/file", methods=["POST"])
def file():
    if "filename" in request.files:
        file = request.files["filename"]
        with open(root / file.filename, "wb+") as fout:
            fout.write(file.stream.read())
    return redirect("/")


@app.route("/files/<path:name>", methods=["GET"])
def files(name):
    return send_file(root / name)


@app.route("/", methods=["GET"])

# html页面，亟待优化
def index():
    return f"""
    <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
    {css}
    </style>
    <title>SWUFE快传</title>
    </head>
    <body style="background-color:#FFFFFC;">
    <center>

    <div id="header" class="main-box">
    <picture class="logo-box">
        <source srcset="https://airportal.cn/img/mainLogo_2020.webp" type="image/webp">
        <img src="https://assets.retiehe.com/ap-main-logo-2.png" alt draggable="false" width="350" height="200">
    </picture>
    </div>

    <div id="main-box" class="next-box">
    <form action="/file" method="post" enctype="multipart/form-data">
        <div class="upload-file">
            <input type="file" name="filename" class="input-file" multiple="true"/> <center><h3>选择文件</h3></center>
        </div>
        <div>
            <br>
            <input type="submit" value="上传文件" name="submitted" class="main-btn"/>
        <div>
    </form>
    </div>
    <div>
    <br><br>
    <h2>Files</h2>
        <div class="test">
            <ul>
                {get_available_files_html()}
            </ul>
        </div>
    </div>
    </center>
    </body>
    
    """


def get_available_files_html():
    return ''.join(
        f"<li><a href='/files/{file}'>{file}</a></li>"
        for file in os.listdir(str(root))
    )


if __name__ == "__main__":
    app.run(host="127.0.0.1", debug=True, port=8080)
