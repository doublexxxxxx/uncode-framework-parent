var ProductView = function() {
    var generateProductTableTrHtml = function(product) {
        var html = '';
        html += '<tr data-id="' + product.id + '" data-name="' + product.name + '">';
        html += '  <td>' + product.id + '</td>';
        html += '  <td>' + product.name + '</td>';
        html += '  <td>' + product.price + '</td>';
        html += '  <td>';
        html += '    <button class="btn btn-xs btn-primary product_edit" title="Edit"><i class="fa fa-fw fa-edit"></i></button>';
        html += '    <button class="btn btn-xs btn-default product_delete" title="Delete"><i class="fa fa-fw fa-trash-o"></i></button>';
        html += '  </td>';
        html += '</tr>';
        return html;
    };
    return {
        renderProductTable: function(productList) {
            var html = '';
            if (productList.length) {
                html += '<table class="table table-hover" id="product_table">';
                html += '  <thead>';
                html += '    <tr>';
                html += '      <th>ID</th>';
                html += '      <th>Product Name</th>';
                html += '      <th>Price</th>';
                html += '      <th>Action</th>';
                html += '    </tr>';
                html += '  </thead>';
                html += '  <tbody>';
                $.each(productList, function(i, product) {
                    html += generateProductTableTrHtml(product);
                });
                html += '  </tbody>';
                html += '</table>';
            } else {
                html += '<div class="alert alert-warning">Can not find any data!</div>';
            }
            $('#product_list').html(html);
        },
        renderProductModal: function(title, product) {
            var $modal = $('#product_modal');
            $modal.find('.modal-title').text(title);
            var html = '';
            html += '<form>';
            html += '  <input type="hidden" id="id" value="' + product.id +'">';
            html += '  <div class="form-group">';
            html += '    <label for="name">Product Name:</label>';
            html += '    <input type="text" value="' + product.name + '" class="form-control" id="name">';
            html += '  </div>';
            html += '  <div class="form-group">';
            html += '    <label for="price">Price:</label>';
            html += '    <input type="text" value="' + product.price + '" class="form-control" id="price">';
            html += '  </div>';
            html += '</form>';
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