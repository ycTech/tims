<!DOCTYPE html>

<html lang="en">
<head>
    <title>图片预览图</title>
    <link rel="stylesheet" href="css/viewer.min.css">
    <link rel="stylesheet" href="css/loading.css">
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.css" />
    <link rel="stylesheet" href="css/index.css">
</head>

<body>

<div class="file-preview__wrapper">
    <div class="file-preview__sidebar">
        <ul>
            <li class="file-item">
                <span data-url="/demo/image3.png" >image3.jpg</span>
            </li>
            <li class="file-item">
                <span data-url="/demo/test.pdf">test.pdf</span>
            </li>
            <li class="file-item">
                <span data-url="/demo/test5.xlsx">test5.xlsx</span>
            </li>

            <li class="file-item">
                <span data-url="http://127.0.0.1:8012/demo/test5.doc">test5.doc</span>
            </li>

        </ul>
    </div>
    <div class="file-preview__main">
        <iframe id="file-preview__iframe" 
            src="/onlinePreview?url=/demo/test5.xlsx" frameborder="0"></iframe>
    </div>
</div>

<script src="js/jquery-3.0.0.min.js" type="text/javascript"></script>
<script src="https://cdn.bootcss.com/jquery.form/3.09/jquery.form.min.js" type="text/javascript"></script>
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.js"></script>
<script src="js/index.js"></script>
</body>
</html>