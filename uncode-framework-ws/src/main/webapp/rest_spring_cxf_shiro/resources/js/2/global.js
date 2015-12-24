var AjaxUtil = function() {
    $.ajaxSetup({
        statusCode: {
            401: function() {
                alert('401 - Unauthorized');
            },
            403: function() {
                alert('403 - Forbidden');
            }
        }
    });
    return {
        sendGetRequest: function(url, callback) {
            $.ajax({
                type: 'get',
                url: url,
                dataType: 'json',
                success: function(data) {
                    callback(data);
                }
            });
        },
        sendFormData: function(url, json, callback) {
            $.ajax({
                type: 'post',
                url: url,
                data: json,
                dataType: 'json',
                contentType: 'application/x-www-form-urlencoded',
                success: function(data) {
                    callback(data);
                }
            });
        },
        sendPostData: function(url, json, callback) {
            $.ajax({
                type: 'post',
                url: url,
                data: JSON.stringify(json),
                dataType: 'json',
                contentType: 'application/json',
                success: function(data) {
                    callback(data);
                }
            });
        },
        sendPutData: function(url, json, callback) {
            $.ajax({
                type: 'put',
                url: url,
                data: JSON.stringify(json),
                dataType: 'json',
                contentType: 'application/json',
                success: function(data) {
                    callback(data);
                }
            });
        },
        sendDeleteRequest: function(url, callback) {
            $.ajax({
                type: 'delete',
                url: url,
                dataType: 'json',
                success: function(data) {
                    callback(data);
                }
            });
        }
    };
}();

var TemplateUtil = function() {
    var templates = {};
    $('script[type="x-tmpl-mustache"]').each(function() {
        var id = $(this).attr('id');
        var content = $(this).html();
        Mustache.parse(content);
        templates[id] = content;
    });
    return {
        getTemplate: function(id) {
            return templates[id];
        },
        renderHtml: function(id, data, partials) {
            return Mustache.render(TemplateUtil.getTemplate(id), data, partials);
        }
    };
}();