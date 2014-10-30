var event = angular.module('ddb-app', []);

event.controller('ticketTypeController', ['$scope', function($scope) {
        $scope.colors = [
            {name:'免费', shade:'free', display : 'hide'},
            {name:'收费', shade:'charge', display : "show"}
        ];
        $scope.myColor = $scope.colors[0];
    }]);

event.controller('provinceCityCtrl', ['$scope', '$http', function($scope, $http) {
        $scope.provinces = [
            {
                "provinceId": 1,
                "provinceCode": "110000",
                "provinceName": "北京市"
            },
            {
                "provinceId": 2,
                "provinceCode": "120000",
                "provinceName": "天津市"
            },
            {
                "provinceId": 3,
                "provinceCode": "130000",
                "provinceName": "河北省"
            },
            {
                "provinceId": 4,
                "provinceCode": "140000",
                "provinceName": "山西省"
            },
            {
                "provinceId": 5,
                "provinceCode": "150000",
                "provinceName": "内蒙古自治区"
            },
            {
                "provinceId": 6,
                "provinceCode": "210000",
                "provinceName": "辽宁省"
            },
            {
                "provinceId": 7,
                "provinceCode": "220000",
                "provinceName": "吉林省"
            },
            {
                "provinceId": 8,
                "provinceCode": "230000",
                "provinceName": "黑龙江省"
            },
            {
                "provinceId": 9,
                "provinceCode": "310000",
                "provinceName": "上海市"
            },
            {
                "provinceId": 10,
                "provinceCode": "320000",
                "provinceName": "江苏省"
            },
            {
                "provinceId": 11,
                "provinceCode": "330000",
                "provinceName": "浙江省"
            },
            {
                "provinceId": 12,
                "provinceCode": "340000",
                "provinceName": "安徽省"
            },
            {
                "provinceId": 13,
                "provinceCode": "350000",
                "provinceName": "福建省"
            },
            {
                "provinceId": 14,
                "provinceCode": "360000",
                "provinceName": "江西省"
            },
            {
                "provinceId": 15,
                "provinceCode": "370000",
                "provinceName": "山东省"
            },
            {
                "provinceId": 16,
                "provinceCode": "410000",
                "provinceName": "河南省"
            },
            {
                "provinceId": 17,
                "provinceCode": "420000",
                "provinceName": "湖北省"
            },
            {
                "provinceId": 18,
                "provinceCode": "430000",
                "provinceName": "湖南省"
            },
            {
                "provinceId": 19,
                "provinceCode": "440000",
                "provinceName": "广东省"
            },
            {
                "provinceId": 20,
                "provinceCode": "450000",
                "provinceName": "广西壮族自治区"
            },
            {
                "provinceId": 21,
                "provinceCode": "460000",
                "provinceName": "海南省"
            },
            {
                "provinceId": 22,
                "provinceCode": "500000",
                "provinceName": "重庆市"
            },
            {
                "provinceId": 23,
                "provinceCode": "510000",
                "provinceName": "四川省"
            },
            {
                "provinceId": 24,
                "provinceCode": "520000",
                "provinceName": "贵州省"
            },
            {
                "provinceId": 25,
                "provinceCode": "530000",
                "provinceName": "云南省"
            },
            {
                "provinceId": 26,
                "provinceCode": "540000",
                "provinceName": "西藏自治区"
            },
            {
                "provinceId": 27,
                "provinceCode": "610000",
                "provinceName": "陕西省"
            },
            {
                "provinceId": 28,
                "provinceCode": "620000",
                "provinceName": "甘肃省"
            },
            {
                "provinceId": 29,
                "provinceCode": "630000",
                "provinceName": "青海省"
            },
            {
                "provinceId": 30,
                "provinceCode": "640000",
                "provinceName": "宁夏回族自治区"
            },
            {
                "provinceId": 31,
                "provinceCode": "650000",
                "provinceName": "新疆维吾尔自治区"
            },
            {
                "provinceId": 32,
                "provinceCode": "710000",
                "provinceName": "台湾省"
            },
            {
                "provinceId": 33,
                "provinceCode": "810000",
                "provinceName": "香港特别行政区"
            },
            {
                "provinceId": 34,
                "provinceCode": "820000",
                "provinceName": "澳门特别行政区"
            }
        ];
        $scope.defProvince = $scope.provinces[8];

        $scope.cities = [
        ];

        $scope.getCities = function (province) {
            var p = $http({
                method: 'GET',
                url: $ctx + '/city?provinceCode=' + province.provinceCode
            });
            p.success(function(response, status, headers, config){
                $scope.cities = response;
                $scope.defCity = $scope.cities[0];
            });
        }
        $scope.getCities($scope.defProvince);

        $scope.provinceChange = function(defProvince) {
            $scope.getCities(defProvince);
        }
    }]);


jQuery(document).ready(function() {
    App.init();
    App.initUniform();
    $('.form_datetime').datetimepicker({
    });

    $("#eventForm").submit(function () {
        var content = UM.getEditor('myEditor').getContent();
        $("#content0").val(content);
    });
    $("#createTicket").click(function() {
        var data = $("#ticketForm").serialize();
        var ajaxCallUrl = $ctx + "/ticket/create";
        $.ajax({
            cache: true,
            type: "POST",
            url:ajaxCallUrl,
            data:data,
            async: false,
            error: function(request) {
                alert("Connection error");
            },
            success: function(data) {
                location.reload();
            }
        });
    });

    $(".form_datetime>input").focus(function () {
        $(this).parent().data("DateTimePicker").show();
    });

});


$(function () {
    'use strict';
    // Change this to the location of your server-side upload handler:
    var url = window.location.hostname === 'blueimp.github.io' ?
            '//jquery-file-upload.appspot.com/' : $ctx + '/files/create',
        uploadButton = $('<button/>')
            .addClass('btn btn-primary')
            .prop('disabled', true)
            .text('Processing...')
            .on('click', function () {
                var $this = $(this),
                    data = $this.data();
                    $this
                    .off('click')
                    .text('Abort')
                    .on('click', function () {
                        $this.remove();
                        data.abort();
                    });
                data.submit().always(function () {
                    $this.remove();
                });
            });
    $('#fileupload').fileupload({
        url: url,
        dataType: 'json',
        autoUpload: true,
        acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
        maxFileSize: 5000000, // 5 MB
        // Enable image resizing, except for Android and Opera,
        // which actually support image resizing, but fail to
        // send Blob objects via XHR requests:
        disableImageResize: /Android(?!.*Chrome)|Opera/
            .test(window.navigator.userAgent),
        previewMaxWidth: 100,
        previewMaxHeight: 100,
        previewCrop: true,
        formData : {
            eventId : $("#eventId").val()
        }
    }).on('fileuploadadd', function (e, data) {
        data.context = $('<div/>').appendTo('#files');
        $.each(data.files, function (index, file) {
            var node = $('<p/>')
                .append($('<span/>').text(file.name));
            if (!index) {
                /*node
                    .append('<br>')
                    .append(uploadButton.clone(true).data(data));*/
                $("#uploadPanel").hide();
            }
            node.appendTo(data.context);
        });
    }).on('fileuploadprocessalways', function (e, data) {
        var index = data.index,
            file = data.files[index],
            node = $(data.context.children()[index]);
        if (file.preview) {
            node
                .prepend('<br>')
                .prepend(file.preview);
        }
        if (file.error) {
            node
                .append('<br>')
                .append($('<span class="text-danger"/>').text(file.error));
        }
        if (index + 1 === data.files.length) {
            data.context.find('button')
                .text('上传')
                .prop('disabled', !!data.files.error);
        }
    }).on('fileuploadprogressall', function (e, data) {
        var progress = parseInt(data.loaded / data.total * 100, 10);
        $('#progress .progress-bar').css(
            'width',
                progress + '%'
        );
    }).on('fileuploaddone', function (e, data) {
        if (data.result.id) {
            $("#progress>div").html("上传成功");
        }
        /*
        $.each(data.result.files, function (index, file) {
            if (file.url) {
                var link = $('<a>')
                    .attr('target', '_blank')
                    .prop('href', file.url);
                $(data.context.children()[index])
                    .wrap(link);
            } else if (file.error) {
                var error = $('<span class="text-danger"/>').text(file.error);
                $(data.context.children()[index])
                    .append('<br>')
                    .append(error);
            }
        });*/
    }).on('fileuploadfail', function (e, data) {
        $.each(data.files, function (index, file) {
            var error = $('<span class="text-danger"/>').text('File upload failed.');
            $(data.context.children()[index])
                .append('<br>')
                .append(error);
        });
    }).prop('disabled', !$.support.fileInput)
        .parent().addClass($.support.fileInput ? undefined : 'disabled');
});