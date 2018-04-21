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
})
function setPreviewIframeSrc (fileUrl) {
    var srcPrefix = '/onlinePreview?url='
    $('#file-preview__iframe').attr('src', srcPrefix + fileUrl)
}
function getTreeData () {
    $.ajax({
        type: 'POST',
        url: 'bill/file/list',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: JSON.stringify(urlQuery),
        success: function (res) {
            var treeData = res.data.file
            var fileArray = res.data.fileStoreList
            $('#jstree').jstree({
                core: {
                    data: parseTreeData(treeData)
                }
            })
            $('#jstree').on('changed.jstree', function(e, data) {
                var fileUrl = data.node.li_attr.fileUrl
                setPreviewIframeSrc(fileUrl)
            })
        },
        error: function (error) {
            console.log(JSON.stringify(error))
        }
    })
}

function parseTreeData (treeArray) {
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