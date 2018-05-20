<!DOCTYPE html>

<html lang="en">
<head>
    <title>影像扫描系统</title>
    <link rel="stylesheet" href="css/viewer.min.css">
    <link rel="stylesheet" href="css/loading.css">
    <link rel="stylesheet" href="lib/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="lib/bootstrap-table/1.11.1/bootstrap-table.min.css">
    <link rel="stylesheet" href="lib/jstree/themes/default/style.min.css">
    <link rel="stylesheet" href="lib/layui/css/layui.css">
    <link rel="stylesheet" href="css/index.css">
</head>

<body class="layui-layout-body">
    <div class="layui-layout layui-layout-admin">
        <div class="layui-header">
            <div class="menu-hamburger" onclick="toggleSidebar()">
                <i class="glyphicon glyphicon-menu-hamburger"></i>
            </div>
            <div class="layui-logo">
                影像扫描系统
            </div>
            <ul class="layui-nav layui-layout-left">
                <li class="layui-nav-item">
                    <span id="billInfo"></span>
                </li>
                <li class="layui-nav-item ">
                    <a href="javascript:void(0);" onclick="hrefToScanner()">影像扫描</a>
                </li>
                <li class="layui-nav-item layui-this">
                    文件预览
                </li>
            </ul>
        </div>
        <div class="layui-side layui-bg-black">
            <div class="layui-side-scroll">
                <div id="jstree" style="overflow-x: auto; overflow-y: hidden; position: absolute;top:45px; left: 0; right: 0;bottom: 0;"></div>
            </div>
        </div>

        <div class="layui-body">
            <div class="file-preview__main">
                <iframe id="file-preview__iframe" src="" frameborder="0"></iframe>
            </div>
        </div>

        <div class="layui-footer">
            <div class="pull-left prev-file" style="cursor: pointer; line-height: 44px;">
                <i class="layui-icon">&#xe603;</i>
                <span>
                    上一个文档
                </span>
            </div>
            <div class="pull-right next-file" style="cursor: pointer; line-height: 44px;">
                <span>
                    下一个文档
                </span>
                <i class="layui-icon">&#xe602;</i>
            </div>
        </div>
    </div>
    
    <script src="lib/html5shiv/html5.min.js"></script>
    <script src="lib/respond.min.js"></script>
    <script src="lib/layui/layui.all.js"></script>
    <script src="js/jquery-3.0.0.min.js" type="text/javascript"></script>
    <script src="lib/jquery.form/3.09/jquery.form.min.js"></script>
    <script src="lib/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="lib/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
    <script src="lib/jstree/jstree.min.js"></script>
    <script src="js/index.js"></script>
</body>
</html>