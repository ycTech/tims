<!DOCTYPE html>

<html lang="en">
<head>
    <link rel="stylesheet" href="lib/bootstrap/3.3.7/css/bootstrap.min.css">
    <style type="text/css">
        html, body {
            width: 100%;
            height: 100%;
            margin: 0;
            padding: 0;
            border: 0;
            overflow: hidden;
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
</body>
<script src="lib/jquery.min.js"></script>
<script src="lib/jquery.media.js"></script>
<script src="lib/jquery.metadata.js"></script>
<script src="js/util.js"></script>
<script type="text/javascript">
    $(function () {
        var IEVersion = CheckIEVersion()

        if (IEVersion >= 6 && IEVersion <= 11) {
            $('iframe').remove()
            $('a.media').show().media({width: '100%', height: '100%'})
        } else {
            $('a.media').hide()
            $('body').append($(
                '    <iframe\n' +
                '        src="/pdfjs/web/viewer.html?file=${finalUrl}"\n' +
                '        width="100%"\n' +
                '        frameborder="0" class="chrome-iframe">\n' +
                '    </iframe>'
            ))
            document.getElementsByTagName('iframe')[0].height = document.documentElement.clientHeight;
            /**
             * 页面变化调整高度
             */
            window.onresize = function(){
                var fm = document.getElementsByTagName("iframe")[0];
                fm.height = window.document.documentElement.clientHeight;
            }
        }
    })
</script>
</html>