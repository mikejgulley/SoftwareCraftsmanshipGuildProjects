$('document').ready(function () {
    loadInventory();

    // displays the current balance
    displayCurrentBalance();

    // adds funds to current balance
    $('#add-button').click(function (event) {
        event.preventDefault();
        var valToAdd = $('#add-funds').val();
        $.ajax({
            type: 'PUT',
            url: 'balance',
            data: valToAdd,
            'dataType': 'text'
        }).success(function (data, status) {
            $('#add-funds').val('');
            clearBalance();
            displayCurrentBalance();
        });
    });

    // makes change
    $('#change-button').click(function (event) {
        event.preventDefault();
        var valToAdd;
        $.ajax({
            url: 'balance'
        }).success(function (data, status) {
            valToAdd = data;
            $.ajax({
                type: 'PUT',
                url: 'resetBalance',
                data: '0.00',
                'dataType': 'text'
            }).success(function (data, status) {
                clearBalance();
                displayCurrentBalance();
            });
        });
    });

    // buy item
    $('#confirm-buy-button').click(function (event) {
        event.preventDefault();
        var itemChoice = $('input[name="menuChoices"]:checked').val();
        $.ajax({
            type: 'PUT',
            url: 'buy',
            data: itemChoice,
            'dataType': 'text'
        }).success(function (data, status) {
            clearBalance();
            displayCurrentBalance();
            clearInventory();
            loadInventory();
        });
    });

});

// =======================
// FUNCTIONS
// =======================
function loadInventory() {
    // clear table so we don't repeat info
    clearInventory();

    // grab element to write data into
    var productTable = $('#contentRows');

    // iterate through objects
    $.ajax({
        url: 'products'
    }).success(function (data, status) {
        $.each(data, function (index, product) {
            var prodPrice = product.price;
            if (product.quantity === 0) {
                product.imgSrc = "./img/outOfStock.png";
            }
            productTable.append($('<tr>')
                    .append($('<td>')
                            .append($('<img>')
                                    .attr({'src': product.imgSrc})
                                    )
                            )
                    .append($('<td>')
                            .append($('<ul>')
                                    .addClass('product-details')
                                    .append($('<li>')
                                            .text('Slot: ' + product.id)
                                            )
                                    .append($('<li>')
                                            .text('Name: ' + product.name)
                                            )
                                    .append($('<li>')
                                            .text('Price: $' + prodPrice.toFixed(2))
                                            )
                                    .append($('<li>')
                                            .text('Qty: ' + product.quantity)
                                            )
                                    )
                            )
                    );
        });
    });
}

function clearInventory() {
    $('#contentRows').empty();
}

function clearBalance() {
    $('#current-balance').empty();
}

function getCurrentBalance() {
    $.ajax({
        url: 'balance'
    });
}

function displayCurrentBalance() {
    $.ajax({
        url: 'balance'
    }).success(function (data, status) {
        $('#current-balance').append('$' + data);
    });
}

function clearBuyModal() {
    $('#confirm-product-purchase').empty();
}

//// runs in response to the show.bs.modal event
//$('#addFundsModal').on('show.bs.modal', function (event) {
//    var element = $(event.relatedTarget);
//    var amountToAdd = element.data('#add-funds');
//
//    var modal = $(this);
//});

// =====================
// MODALS
// =====================

$('#makeChangeModal').on('show.bs.modal', function (event) {
    var modal = $(this);

    $.ajax({
        url: 'balance'
    }).success(function (data) {
        modal.find('#change-due').text('$' + data);

        var changeDue = data * 100;
        var quarter = 25;
        var dime = 10;
        var nickel = 5;
        var penny = 1;

        var numQuarters = Math.floor(changeDue / quarter);
        changeDue %= quarter;
        var numDimes = Math.floor(changeDue / dime);
        changeDue %= dime;
        var numNickels = Math.floor(changeDue / nickel);
        changeDue %= nickel;
        var numPennies = Math.floor(changeDue / penny);
        changeDue %= penny;


        modal.find('#change-quarters').text(numQuarters);
        modal.find('#change-dimes').text(numDimes);
        modal.find('#change-nickels').text(numNickels);
        modal.find('#change-pennies').text(numPennies);
    });
});

$('#buyItemModal').on('show.bs.modal', function (event) {
    var modal = $(this);
    var productSpace = $('#confirm-product-purchase');
    var itemChoice = $('input[name="menuChoices"]:checked').val();

    $.ajax({
        url: 'products'
    }).success(function (data, status) {
        clearBuyModal();
        $.each(data, function (index, product) {
            var prodPrice = product.price;
            if (product.id === itemChoice) {
                if (product.quantity === 0) {
                    product.imgSrc = "./img/outOfStock.png";
                }
                productSpace.append($('<tbody>')
                        .append($('<tr>')
                        .append($('<td>')
                                .addClass('text-center')
                                .append($('<img>')
                                        .attr({'src': product.imgSrc})
                                        )
                                .append($('<ul>')
                                        .addClass('product-details text-center')
                                        .append($('<li>')
                                                .text('Slot: ' + product.id)
                                                )
                                        .append($('<li>')
                                                .text('Name: ' + product.name)
                                                )
                                        .append($('<li>')
                                                    .text('Price: $' + prodPrice.toFixed(2))
                                                )
                                        )
                                )
                        )
                );
            }
        });
    });
});