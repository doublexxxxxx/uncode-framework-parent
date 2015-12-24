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
    var partials = {};
    $('script[type^="text/x-handlebars-template"]').each(function() {
        var id = $(this).attr('id');
        templates[id] = $(this).html();
        if ($(this).attr('type').lastIndexOf('partial') != -1) {
            partials[id] = $(this).html();
        }
    });
    return {
        getTemplate: function(id) {
            return templates[id];
        },
        registerPartical: function(name, id) {
            Handlebars.registerPartial(name, partials[id]);
        },
        renderHtml: function(id, data) {
            return Handlebars.compile(templates[id])(data);
        }
    };
}();