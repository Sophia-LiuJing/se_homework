import os

from flask import request, redirect, Flask, send_file
from pathlib import Path

app = Flask(__name__)
root = Path.cwd() / "__files__"
root.mkdir(exist_ok=True)

# 亟待优化，建议单独创建一个css文件夹，看着更工程化
css = """ 
form {
    display: flex;
    flex-direction: column;
    gap: 1rem;
    width: max-content;
}
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
    <body>
    <h1>Hello world</h1>
    <h2>Submit a file</h2>

    # 创建上传表单
    <form action="/file" method="post" enctype="multipart/form-data">
        <input type="file" id="filename" name="filename" />
        <input type="submit" value="Upload" name="submitted" />
    </form>

    <h2>Files</h2>
    <ul>
        {get_available_files_html()}
    </ul>
    </body>
    """


def get_available_files_html():
    return ''.join(
        f"<li><a href='/files/{file}'>{file}</a></li>"
        for file in os.listdir(str(root))
    )


if __name__ == "__main__":
    app.run(host="127.0.0.1", debug=True, port=8080)
