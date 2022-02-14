
function getOrderItemUrl() {
    var baseUrl = $("meta[name=baseUrl]").attr("content")
    return baseUrl + "/api/orderItem";
}

//BUTTON ACTIONS
function addOrderItem(idd) {
    //Set the values to update
    // console.log(idd)
    var $form = $("#collapsed-order-" + idd + " form:first-child");
    var json = toJson($form);
    // console.log($form)
    var url = getOrderItemUrl();

    $.ajax({
        url: url,
        type: 'POST',
        data: json,
        headers: {
            'Content-Type': 'application/json'
        },
        success: function (response) {
            getOrderItemList();
        },
        error: handleAjaxError
    });

    return false;
}



function getOrderItemList() {
    var url = getOrderItemUrl();
    $.ajax({
        url: url,
        type: 'GET',
        success: function (data) {
            displayOrderItemList(data);
        },
        error: handleAjaxError
    });
} function getOrderItemListByOrderId(orderId) {
    var url = getOrderItemUrl() + "/orderId/" + orderId;
    $.ajax({
        url: url,
        type: 'GET',
        success: function (data) {
            displayOrderItemList(data);
        },
        error: handleAjaxError
    });
}

function deleteOrderItem(id) {
    var url = getOrderItemUrl() + "/" + id;

    $.ajax({
        url: url,
        type: 'DELETE',
        success: function (data) {
            getOrderItemList();
        },
        error: handleAjaxError
    });
}

//UI DISPLAY METHODS

function displayOrderItemList(data) {
    // $tbody.appendChild();
    console.log(data)
    for (var i in data) {
        var e = data[i];
        var tbody = document.getElementById('collapsed-order-' + e.orderId + '-display').querySelector("#order-item-table").querySelector("#order-item-table-tbody");
        tbody.innerHTML = "";
    }
    for (var i in data) {
        var e = data[i];
        var buttonHtml = '<button onclick="deleteOrderItem(' + e.id + ')">delete</button>';
        buttonHtml += ' <button onclick="displayEditOrderItem(' + e.id + ')">edit</button>';

        var rowHtml = `<tr>
            <td>`+ e.id + `</td>
            <td>`+ e.productId + `</td>
            <td>`+ e.quantity + `</td>
            <td>`+ e.sellingPrice + `</td>
            <td>`+ buttonHtml + `</td>
        </tr>`
        var tbody = document.getElementById('collapsed-order-' + e.orderId + '-display').querySelector("table").querySelector("tbody")
        tbody.insertAdjacentHTML('beforeend', rowHtml)
    }
    // $("table").each(function (i, v) {
    //     if ($(this).find("tbody").html().trim().length === 0) {
    //         $(this).hide()
    //     }
    // })
}
function getOrderUrl() {
    var baseUrl = $("meta[name=baseUrl]").attr("content")
    return baseUrl + "/api/order";
}

//BUTTON ACTIONS
function addOrder(event) {
    //Set the values to update
    // var $form = $("#order-form");
    // var json = toJson($form);
    var url = getOrderUrl();

    $.ajax({
        url: url,
        type: 'POST',
        // data: json,
        headers: {
            'Content-Type': 'application/json'
        },
        success: function (response) {
            getOrderList();
        },
        error: handleAjaxError
    });

    return false;
}
function collapseDetails(orderId) {
    $('#collapsed-order-' + orderId).collapse("toggle");
}


function getOrderList() {
    var url = getOrderUrl();
    $.ajax({
        url: url,
        type: 'GET',
        success: function (data) {
            displayOrderList(data);
        },
        error: handleAjaxError
    });
}

function deleteOrder(id) {
    var url = getOrderUrl() + "/" + id;

    $.ajax({
        url: url,
        type: 'DELETE',
        success: function (data) {
            getOrderList();
        },
        error: handleAjaxError
    });
}
function sendOrder(id) {
    var url = getOrderUrl() + "/sendOrder/" + id;
    $.ajax({
        url: url,
        type: 'PUT',
        success: function (data) {
            getOrderList();
        },
        error: handleAjaxError
    });
}
//UI DISPLAY METHODS

function displayOrderList(data) {
    var tbody = document.getElementById('collapsable-order')
    tbody.innerHTML = "";
    // $tbody.appendChild();
    for (var i in data) {
        var e = data[i];
        var timeStr = new Date(e.time).toLocaleString();
        console.log(e.complete)
        if (e.complete == 1) {
            buttonColor = 'btn btn-success';
            // $('#' + e.id + '').find('*').attr('disabled', true);
        }
        else {
            buttonColor = 'btn btn-warning';
        }
        orderItemContent = `<form class="form-inline" id="order-form">
        <div class="form-group">
            <label for="inputProductId" class="col-sm-2 col-form-label">Product Id</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="productId" id="inputProductId" placeholder="enter product id">
            </div>
        </div>
        <div class="form-group">
            <label for="inputQuantity" class="col-sm-2 col-form-label">Quantity</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="quantity" id="inputQuantity" placeholder="enter quantity">
            </div>
        </div>
        <div class="form-group">
            <label for="inputSellingPrice" class="col-sm-2 col-form-label">SellingPrice</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="sellingPrice" id="inputSellingPrice" placeholder="enter sellingPrice">
            </div>
        </div>
        <input type="hidden" name="id">
        <input type="hidden" name="orderId" value="`+ e.id + `">
        
        <button type="button" class="btn btn-primary" id="add-order-item" onClick="addOrderItem(` + e.id + `)">Add</button>
        &nbsp;
        <button type="button" class="btn btn-primary" id="refresh-order-item">Refresh</button>
				</form>`
        collapsableContent = `<div class="row">
            <button type="button" class="btn btn-danger" onClick="deleteOrder(` + e.id + `)">
            Delete Order</button>
            <button type="button" class="btn btn-primary" onClick="sendOrder(` + e.id + `)">
            Send Order</button>
            <button type="button" class="`+ buttonColor + `" onClick="collapseDetails(` + e.id + `)" >
            `+ 'OrderId: ' + e.id + ' | ' + timeStr + `
            </button>
            <div id="collapsed-order-`+ e.id + `" class="collapse">
                `+ orderItemContent + `  
                <div id="collapsed-order-`+ e.id + `-display" > 
                <table class="table table-striped" id="order-item-table">
                    <thead >
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Product Id</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Selling Price</th>
                        </tr>
                    </thead>
                    <tbody id="order-item-table-tbody">
                    </tbody>
                </table>
                </div>
            </div>
        </div>`
        tbody.insertAdjacentHTML('beforeend', collapsableContent);

        // $('#collapsed-order-' + e.id).click(getOrderItemListByOrderId(e.id));

        // if (e.complete == 1) {
        //     $('#' + e.id + '').find('*').attr('disabled', true);
        // }
        // $tbody.append(row);
    }
    $('.collapse').on('show.bs.collapse', function (e) {
        console.log(e.target.id);
        var orderId = e.target.id.split('-').at(-1);
        console.log(orderId);
        getOrderItemListByOrderId(orderId);
    });
    // getOrderItemList();
}



//INITIALIZATION CODE
function init() {
    $('#add-order').click(addOrder);
    $('#refresh-data').click(getOrderList);
    // $('#add-order-item').click(addOrderItem);
    // $('#refresh-order-item').click(getOrderItemList);
    // $('#collapsed-order-' + e.id).on('shown.bs.collapse', function () {
    //     getOrderItemListByOrderId(e.id);
    // });
}

$(document).ready(init);
$(document).ready(getOrderList);
// $('.collapse').on('show.bs.collapse', function (e) {
//     console.log(e.target.id);
// });
// $(document).ready(getOrderItemList);

