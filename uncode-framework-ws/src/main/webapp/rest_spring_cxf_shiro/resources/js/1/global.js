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