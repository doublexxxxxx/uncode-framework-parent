var ProductService = function() {
    var BASE = '/ws/rest';
    return {
        retrieveAllProducts: function(callback) {
            AjaxUtil.sendGetRequest(BASE + '/products', function(productList) {
                ProductView.renderProductTable(productList);
                callback();
            });
        },
        retrieveProductById: function(id) {
            AjaxUtil.sendGetRequest(BASE + '/product/' + id, function(product) {
                var title = 'Edit Product';
                ProductView.renderProductModal(title, product);
            });
        },
        retrieveProductsByName: function(name) {
            AjaxUtil.sendFormData(BASE + '/products', {
                name: name
            }, function(productList) {
                ProductView.renderProductTable(productList);
            });
        },
        createProduct: function(product) {
            AjaxUtil.sendPostData(BASE + '/product', product, function(product) {
                ProductView.insertProductRow(product);
            });
        },
        updateProductById: function(id, product) {
            AjaxUtil.sendPutData(BASE + '/product/' + id, product, function(product) {
                ProductView.updateProductRow(product);
            });
        },
        deleteProductById: function(id) {
            AjaxUtil.sendDeleteRequest(BASE + '/product/' + id, function() {
                ProductView.deleteProductRow(id);
            });
        }
    }
}();