var ProductView = function() {
    var generateProductTableTrHtml = function(product) {
        return TemplateUtil.renderHtml('product_table_tr_template', product);
    };
    return {
        renderProductTable: function(productList) {
            var html = TemplateUtil.renderHtml('product_table_template', {
                data: productList
            }, {
                tr: TemplateUtil.getTemplate('product_table_tr_template')
            });
            $('#product_list').html(html);
        },
        renderProductModal: function(title, product) {
            var $modal = $('#product_modal');
            $modal.find('.modal-title').text(title);
            var html = TemplateUtil.renderHtml('product_modal_form_template', product);
            $modal.find('.modal-body').html(html);
        },
        insertProductRow: function(product) {
            var html = generateProductTableTrHtml(product);
            $('#product_table').find('tbody').prepend(html);
        },
        updateProductRow: function(product) {
            var html = generateProductTableTrHtml(product);
            $('#product_table').find('tbody').find('tr[data-id="' + product.id + '"]').replaceWith(html);
        },
        deleteProductRow: function(id) {
            $('#product_table').find('tbody').find('tr[data-id="' + id + '"]').remove();
        }
    };
}();