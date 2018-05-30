var urlQuery = {
    vsystem: getQueryString('vsystem'),
    pk_corp: getQueryString('pk_corp'),
    userCode: getQueryString('userCode'),
    billType: getQueryString('billType'),
    billtypename: getQueryString('billtypename'),
    funName: getQueryString('funName'),
    billId: getQueryString('billId'),
    billNo: getQueryString('billNo'),
    isFolder: getQueryString('isFolder'),
    path: getQueryString('path')
}
var g_fileList = []
var g_curFileId = null

$(function () {
    getTreeData()
    if (urlQuery.funName || urlQuery.billNo) {
        $('#billInfo').html((urlQuery.funName || '') + '/' + (urlQuery.billNo || '')).css({
            margin: '0 15px'
        })
    }

    $('.file-preview-toolbar .prev-file').on('click', function () {
        showPrevFile()
    })

    $('.file-preview-toolbar .next-file').on('click', function () {
        showNextFile()
    })

})
function toggleSidebar() {
    $('.layui-layout.layui-layout-admin').toggleClass('collapsed')
}
function setPreviewIframeSrc(fileUrl) {
    var srcPrefix = '/onlinePreview?url='
    $('#file-preview__iframe').attr('src', srcPrefix + fileUrl)
}
function getTreeData() {
    $.ajax({
        type: 'POST',
        url: 'preview/file/list',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: JSON.stringify(urlQuery),
        success: function (res) {
            var treeData = res.data.file
            g_fileList = res.data.fileStoreList || []
            $('#jstree').jstree({
                core: {
                    data: parseTreeData(treeData)
                }
            })
            $('#jstree').on('changed.jstree', function (e, data) {
                if (data.node.children && data.node.children.length > 0) {
                    return false
                }
                var fileUrl = data.node.li_attr.fileUrl
                g_curFileId = data.node.id
                setPreviewIframeSrc(fileUrl)
            })
            $('#jstree').on('ready.jstree', function (e, data) {
                var nextFileId = getQueryString('path')
                if (!nextFileId) {
                    showNextFile()
                } else {
                    $(document.getElementById(nextFileId + '_anchor')).click()
                    // $('#' + nextFileId + '_anchor').click()
                }
            })
        },
        error: function (error) {
            alert(JSON.stringify(error))
        }
    })
}
function showNextFile () {
    if (g_fileList.length <= 0) {
        layer.msg('单据中还没有任何文件！')
        return false
    }
    var idx = getIndexOfFileList(g_fileList, g_curFileId) + 1
    if (idx >= g_fileList.length) {
        layer.msg('没有更多文件了！')
        return false
    }
    var nextFileId = g_fileList[idx].filePath + '/' + g_fileList[idx].imageName
    var el = document.getElementById(nextFileId + '_anchor')
    $(el).click()
}

function showPrevFile () {
    if (g_fileList.length <= 0) {
        layer.msg('单据中还没有任何文件！')
        return false
    }
    var idx = getIndexOfFileList(g_fileList, g_curFileId) - 1
    if (idx < 0) {
        layer.msg('已经是第一个文件了！')
        return false
    }
    var prevFileId = g_fileList[idx].filePath + '/' + g_fileList[idx].imageName
    var el = document.getElementById(prevFileId + '_anchor')
    $(el).click()
}

function getIndexOfFileList (fileList, fileId) {
    for (var i = 0; i < fileList.length; i ++) {
        if (fileList[i].filePath + '/' + fileList[i].imageName == fileId) {
            return i
        }
    }
    return -1
}

function parseTreeData(treeArray) {
    for (var i = 0; i < treeArray.length; i++) {
        var item = treeArray[i]
        item.text = item.name
        item.state = {
            opened: true
        }
        if (item.children && item.children.length > 0) {
            item.icon = 'timsicon timsicon-folder';
            item.li_attr = item.li_attr || {}
            item.li_attr.path = item.path
            item.children = parseTreeData(item.children)
        } else {
            if (item.isFolder == 'y') {
                item.icon = 'timsicon timsicon-folder';
                item.li_attr = {
                path: item.path
                }
            } else {
                var suffix = item.name.split('.').pop()
                switch (suffix) {
                    case 'jpg':
                    case 'gif':
                    case 'png':
                        item.icon = 'timsicon timsicon-file-image'
                        break
                    case 'pdf':
                        item.icon = 'timsicon timsicon-file-pdf'
                        break
                    case 'wps':
                    case 'doc':
                        item.icon = 'timsicon timsicon-file-word'
                        break
                    case 'xls':
                    case 'xlsx':
                        item.icon = 'timsicon timsicon-file-excel'
                        break
                    default:
                        item.icon = 'timsicon timsicon-file-pdf'
                        break
                }
                item.li_attr = {
                    fileUrl: item.url,
                    path: item.path
                }
            }
        }
    }
    return treeArray
}


// 获取location参数
function getQueryString(name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i')
    var r = window.location.search.substr(1).match(reg)
    if (r != null) {
        return decodeURI(r[2])
    } else {
        return null
    }
}
function hrefToScanner() {
    var hostname = window.location.hostname
    var search = window.location.search
    window.location.href=('http://' + hostname + ':10060/scanner-index.html' +  search)
}