var urlQuery = {
    vsystem: getQueryString('vsystem'),
    pk_corp: getQueryString('pk_corp'),
    userCode: getQueryString('userCode'),
    billType: getQueryString('billType'),
    billtypename: getQueryString('billtypename'),
    billId: getQueryString('billId'),
    billNo: getQueryString('billNo'),
    isFolder: getQueryString('isFolder'),
    path: getQueryString('path')
}
var g_fileList = []
var g_curFileId = null
$(function () {
    getTreeData()
    // $('li.file-item').on('click', function() {
    //     console.log('on click')
    //     var srcPrefix = '/onlinePreview?url='
    //     var fileUrl = $('span' ,$(this)).attr('data-url')
    //     console.log($('span', $(this)))
    //     console.log(fileUrl)
    //     $('#file-preview__iframe')
    //     .attr('src', srcPrefix + fileUrl)
    // })

    $('.layui-footer .prev-file').on('click', function () {
        showPrevFile()
    })

    $('.layui-footer .next-file').on('click', function () {
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
                var fileUrl = data.node.li_attr.fileUrl
                g_curFileId = data.node.id
                setPreviewIframeSrc(fileUrl)
            })
            $('#jstree').on('ready.jstree', function (e, data) {
                showNextFile()
            })
        },
        error: function (error) {
            console.log(JSON.stringify(error))
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
    var nextFileId = g_fileList[idx].id
    $('#' + nextFileId + '_anchor').click()
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
    var prevFileId = g_fileList[idx].id
    $('#' + prevFileId + '_anchor').click()
}

function getIndexOfFileList (fileList, fileId) {
    for (var i = 0; i < fileList.length; i ++) {
        if (fileList[i].id == fileId) {
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
            item.icon = 'glyphicon glyphicon-folder-open'
            item.children = parseTreeData(item.children)
        } else {
            item.icon = 'glyphicon glyphicon-file'
            item.li_attr = {
                fileUrl: item.url
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
        return unescape(r[2])
    } else {
        return null
    }
}
