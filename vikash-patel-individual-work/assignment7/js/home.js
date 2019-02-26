var totalDeposit = 0;
var currentProduct = null;

$(document).ready(function () {

    loadItems();

    $('#addDollar, #addQuarter, #addDime, #addNickel').click(function () {
        if (this.id == 'addDollar') {
            totalDeposit += 1;
        } else if (this.id == 'addQuarter') {
            totalDeposit += .25;
        } else if (this.id == 'addDime') {
            totalDeposit += .10;
        } else if (this.id == 'addNickel') {
            totalDeposit += .05;
        }

        $('#inputMoney').val('$' + totalDeposit.toFixed(2));
        $('#change').val('');
    });

    $('#changeReturn').click(function () {
        var quarters = Math.floor(totalDeposit / .25);
        var remainingChange = totalDeposit % .25;
        var dimes = Math.floor(remainingChange / .10);
        var remainingChange2 = remainingChange % .10;
        var nickels = Math.floor(remainingChange2 / .05);

        $('#change').val(quarters + ' Q(s), '
                + dimes + ' D(s), and '
                + nickels + ' N(s)');

        totalDeposit = 0;
        $('#inputMoney').val('$' + totalDeposit.toFixed(2));
        currentProduct = null;
        $('#itemSelected').val('');

    });


    $('#makePurchase').click(function () {
        if (currentProduct !== null) {
            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/money/'
                        + totalDeposit.toFixed(2) + '/item/'
                        + currentProduct.find('.id').text(),
                success: function (change) {
                    $('#change').val(change.quarters + ' Q(s), '
                            + change.dimes + ' D(s), and '
                            + change.nickels + ' N(s)');
                    totalDeposit = 0;
                    $('#inputMoney').val('$' + totalDeposit.toFixed(2));
                    currentProduct = null;
                    $('#itemSelected').val('');
                    $('#message').val('Thank You!');
                    loadItems();
                },
                error: function (xhr, statusCode) {
                    $('#message').val(xhr.responseJSON.message);
                }
            })
        }
    });



});

function loadItems() {
    var productDiv = $('#productDiv');
    productDiv.html('');

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/items',
        success: function (productArray) {

            $.each(productArray, function (index, product) {
                var productSquare = '<div class="productClass col-md-4 id="tile' + product.id + '" style="border: 1px solid black">';

                productSquare += '<div class="itemText">';
                productSquare += '<p class="pull-left id" style="font-size:10px">' + product.id + '</p>';
                productSquare += '<br>';
                productSquare += '<p class="text-center name" style="font-size:22px">' + product.name + '</p>';
                productSquare += '<br><br>';
                productSquare += '<p class="text-center price" style="font-size:18px"> $' + product.price.toFixed(2) + '</p>';
                productSquare += '<br>';
                productSquare += '<p class="text-center quantity" style="font-size:18px"> Quantity Left: <span>' + product.quantity + '</span></p>';
                productSquare += '</div>';

                productSquare += '</div>';

                productDiv.append(productSquare);
            });

            $('.productClass').click(function () {
                $('#itemSelected').val('');
                $('#itemSelected').val($(this).find('.id').text());
                $('#change').val('');
                currentProduct = $(this);
                $('#message').val('');
            });

        },
        error: function () {
            console.log('ERROR LOADING ITEMS');
        }
    });
}
