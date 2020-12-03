function download() {
    debugger
    $.ajax({
        url:"/user/download",
        type:"GET",
        data:{},
        success:function (data) {
            if (data=="200"){
                alert("导出成功")
            }else {
                alert("导出失败")
            }
            
        }

    })
    
}


function UpladFile(fileObj) {
    debugger
    var form = new FormData(); // FormData 对象
    form.append("file", fileObj); // 文件对象
    $.ajax({
        url: '/user/upload',                      //url地址
        type: 'POST',                 //上传方式
        data: form,                   // 上传formdata封装的数据
        dataType: 'JSON',
        cache: false,                  // 不缓存
        processData: false,        // jQuery不要去处理发送的数据
        contentType: false,         // jQuery不要去设置Content-Type请求头
        success:function (data) {           //成功回调
            console.log(data);
            if(data=='200'){
                alert("上传成功");
            }else {
                alert("上传失败")
            }

        },
        error:function (data) {           //失败回调
            console.log(data);
        }
    });
}

function f(fileName) {
    $('#excel').submit();

}


function upload() {
    $("#file").click();
    $('#file').change(function (e) {
        var fileName = e.target.files[0];//js 获取文件对象
        if (fileName !== undefined) {
            var file_typename = fileName.name.substring(fileName.name.lastIndexOf('.'));
            if (file_typename === '.xlsx' || file_typename === '.xls') {
                $("#filename").css("display", "block");
                $("#filename").val(fileName.name);
                UpladFile(fileName);
            } else {
                console.log("请选择正确的文件类型！")
            }
        } else {
            console.log("请选择正确的文件！")
        }
    })
}