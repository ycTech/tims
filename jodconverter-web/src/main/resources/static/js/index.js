$(function () {
    $('li.file-item').on('click', function() {
        console.log('on click')
        var srcPrefix = '/onlinePreview?url='
        var fileUrl = $('span' ,$(this)).attr('data-url')
        console.log($('span', $(this)))
        console.log(fileUrl)
        $('#file-preview__iframe')
            .attr('src', srcPrefix + fileUrl)
    })
})
