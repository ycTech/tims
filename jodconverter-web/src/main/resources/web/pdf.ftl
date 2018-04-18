<!DOCTYPE html>

<html lang="en">
<head>
    <link rel="stylesheet" href="lib/bootstrap-3.3.7/css/bootstrap.min.css">
    <style type="text/css">
        body{
            margin: 0;
            padding:0;
            border:0;
        }
        html, body {
            width: 100%;
            height: 100%;
            margin: 0;
            padding: 0;
            border: 0;
        }
        .media {
            width: 100%;
            height: 100%;
        }
    </style>
</head>
<body>
    <#if pdfUrl?contains("http://")>
        <#assign finalUrl="${pdfUrl}">
    <#else>
        <#assign finalUrl="${baseUrl}${pdfUrl}">
    </#if>
    <a class="media" href="${finalUrl}"></a>
    <#--<iframe src="/pdfjs/web/viewer.html?file=${finalUrl}" width="100%" frameborder="0"></iframe>-->
</body>
<script src="lib/jquery.min.js"></script>
<script src="lib/jquery.media.js"></script>
<script src="lib/jquery.metadata.js"></script>
<script type="text/javascript">
    $(function () {
        $('a.media').media({width: '100%', height: '100%'})
    })
    // document.getElementsByTagName('iframe')[0].height = document.documentElement.clientHeight-10;
    // /**
    //  * 页面变化调整高度
    //  */
    // window.onresize = function(){
    //     var fm = document.getElementsByTagName("iframe")[0];
    //     fm.height = window.document.documentElement.clientHeight-10;
    // }
</script>
</html>