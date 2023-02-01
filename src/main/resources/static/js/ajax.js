function generateProduct(val){
return `
<div class="col-lg-4 col-sm-6" >
                                <!-- product grid item start -->
                                <div class="product-item mb-53">
                                    <div class="product-thumb">
                                        <a href="/detailProduct/`+val.id+`">
                                            <img src="/images/`+val.photo+`" alt="">
                                        </a>
                                    </div>
                                    <div class="product-content">
                                        <h5 class="product-name">
                                            <a href="/detailProduct/`+val.id+`" >`+val.name+`</a>
                                        </h5>
                                        <div class="price-box">
                                            <span class="price-regular" >`+val.price+`</span>

                                        </div>
                                        <div class="product-action-link">
                                            <a href="#" data-toggle="tooltip" title="Wishlist"><i class="ion-android-favorite-outline"></i></a>
                                            <a  href="/addToCart/`+val.id+`" data-toggle="tooltip" title="Add To Cart"><i class="ion-bag"></i></a>
                                            <a onclick="showQuickView(`+val.id+`);" data-toggle="modal" data-target="#quick_view"> <span data-toggle="tooltip"
                                                                                                             title="Quick View"><i class="ion-ios-eye-outline"></i></span> </a>
                                        </div>
                                    </div>
                                </div>
                                <!-- product grid item end -->

                                <!-- product list item start -->
                                <div class="product-list-item mb-30" >
                                    <div class="product-thumb">
                                        <a href="/detailProduct/`+val.id+`">
                                            <img  src="/images/`+val.photo+`"  alt="product thumb">
                                        </a>
                                    </div>
                                    <div class="product-content-list">
                                        <h5 class="product-name">
                                            <a href="/detailProduct/`+val.id+`" >`+val.name+`</a>
                                        </h5>
                                        <div class="price-box">
                                            <span class="price-regular" >`+val.price+`</span>

                                        </div>
                                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce posuere
                                            metus vitae arcu imperdiet, id aliquet ante scelerisque. Sed sit amet
                                            sem vitae urna fringilla tempus.</p>
                                        <div class="product-link-2 position-static">
                                            <a href="#" data-toggle="tooltip" title="Wishlist"><i class="ion-android-favorite-outline"></i></a>
                                            <a href="/addToCart/`+val.id+`" data-toggle="tooltip" title="Add To Cart"><i class="ion-bag"></i></a>
                                            <a onclick="showQuickView(`+val.id+`);" data-toggle="modal" data-target="#quick_view"> <span data-toggle="tooltip"
                                                                                                             title="Quick View"><i class="ion-ios-eye-outline"></i></span> </a>
                                        </div>
                                    </div>
                                </div>
                                <!-- product list item start -->
                            </div>`
}

function showQuickView(id){

    $.ajax({
    type:"GET",
        url:"api/showQuickView/" + id,
        success:function (val){
        var content = '';

            content += `
        <div class="modal-dialog modal-lg modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <!-- product details inner end -->
                <div class="product-details-inner">
                    <div class="row">
                        <div class="col-lg-5">
                            <div class="product-large-slider mb-20">
                                <div class="pro-large-img img-zoom">
                                    <img src="/images/` + val.photo + `" alt="product thumb" />
                                </div>
                               
                            </div>
                         
                        </div>
                        <div class="col-lg-7">
                            <div class="product-details-des">
                                <h3 class="pro-det-title">` + val.name + `</h3>
                                <div class="pro-review">
                                    <span><a href="#">1 Review(s)</a></span>
                                </div>
                                <div class="price-box">
                                    <span class="regular-price">` + val.price + ` vnd</span>
                                  
                                </div>
                                <div>
                                 <p style="padding-bottom: 0px;padding-top: 0px; margin-top: 10px">Cpu : ` + val.cpu + ` </p>
                                    
                                <p style="padding-bottom: 0px;padding-top: 0px" >Ram :` + val.ram + ` </p>

                                <p style="padding-bottom: 0px;padding-top: 0px" >Hard Drive :` + val.hardDrive + `</p>
 
                                <p style="padding-bottom: 0px;padding-top: 0px">Screen :` + val.screen + ` </p>
                                </div>
                               
                               
                                <div class="quantity-cart-box d-flex align-items-center mb-20">
                                    <div class="quantity">
                                        <div class="pro-qty"><input type="text" value="1"></div>
                                    </div>
                                    <a href="/addToCart/`+val.id+`" class="btn btn-default">Add To Cart</a>
                                </div>
                                <div class="availability mb-20">
                                    <h5 class="cat-title">Availability:</h5>
                                    <span>In Stock</span>
                                </div>
                                <div class="share-icon">
                                    <h5 class="cat-title">Share:</h5>
                                    <a href="#"><i class="fa fa-facebook"></i></a>
                                    <a href="#"><i class="fa fa-twitter"></i></a>
                                    <a href="#"><i class="fa fa-pinterest"></i></a>
                                    <a href="#"><i class="fa fa-google-plus"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- product details inner end -->
            </div>
        </div>
     </div>`


            $('#quick_view').html(content);
        }

    })
}

//filter by price
function filterByPrice(){
    var x = document.getElementById("amount").value;
    var myarray = x.split(' ');
    var from = myarray[0].split('$')[1]*1e5;
    var to = myarray[2].split('$')[1]*1e5;
console.log(from,to)
    $.ajax({

        type:"GET",
        url:'api/filterByPrice/' + from + '/' + to,
        success:function (result){
            var content ="";
            $.each(result,function (key,val){
                content +=`<div class="col-lg-4 col-sm-6" >
                                <!-- product grid item start -->
                                <div class="product-item mb-53">
                                    <div class="product-thumb">
                                        <a href="/detailProduct/`+val.id+`">
                                            <img src="/images/`+val.photo+`" alt="">
                                        </a>
                                    </div>
                                    <div class="product-content">
                                        <h5 class="product-name">
                                            <a href="/detailProduct/`+val.id+`" >`+val.name+`</a>
                                        </h5>
                                        <div class="price-box">
                                            <span class="price-regular" >`+val.price+`</span>

                                        </div>
                                        <div class="product-action-link">
                                            <a href="#" data-toggle="tooltip" title="Wishlist"><i class="ion-android-favorite-outline"></i></a>
                                            <a href="/addToCart/`+val.id+`" data-toggle="tooltip" title="Add To Cart"><i class="ion-bag"></i></a>
                                            <a onclick="showQuickView(`+val.id+`);" data-toggle="modal" data-target="#quick_view"> <span data-toggle="tooltip"
                                                                                                             title="Quick View"><i class="ion-ios-eye-outline"></i></span> </a>
                                        </div>
                                    </div>
                                </div>
                                <!-- product grid item end -->

                                <!-- product list item start -->
                                <div class="product-list-item mb-30" >
                                    <div class="product-thumb">
                                        <a href="/detailProduct/`+val.id+`">
                                            <img  src="/images/`+val.photo+`"  alt="product thumb">
                                        </a>
                                    </div>
                                    <div class="product-content-list">
                                        <h5 class="product-name">
                                            <a href="/detailProduct/`+val.id+`" >`+val.name+`</a>
                                        </h5>
                                        <div class="price-box">
                                            <span class="price-regular" >`+val.price+`</span>

                                        </div>
                                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce posuere
                                            metus vitae arcu imperdiet, id aliquet ante scelerisque. Sed sit amet
                                            sem vitae urna fringilla tempus.</p>
                                        <div class="product-link-2 position-static">
                                            <a href="#" data-toggle="tooltip" title="Wishlist"><i class="ion-android-favorite-outline"></i></a>
                                            <a href="/addToCart/`+val.id+`" data-toggle="tooltip" title="Add To Cart"><i class="ion-bag"></i></a>
                                            <a onclick="showQuickView(`+val.id+`);" data-toggle="modal" data-target="#quick_view"> <span data-toggle="tooltip"
                                                                                                             title="Quick View"><i class="ion-ios-eye-outline"></i></span> </a>
                                        </div>
                                    </div>
                                </div>
                                <!-- product list item start -->
                            </div>
                            `
            });
            $('#listProduct').html(content);
        }
    })
}

//filterByCate
function getDetailCategory(id){

    $.ajax({
        type:"GET",
        url:"api/detailCategory/" + id,
        success:function (result){
            var content ="";
            $.each(result,function (key,val){
                content +=`<div class="col-lg-4 col-sm-6" >
                                <!-- product grid item start -->
                                <div class="product-item mb-53">
                                    <div class="product-thumb">
                                        <a href="/detailProduct/`+val.id+`">
                                            <img src="/images/`+val.photo+`" alt="">
                                        </a>
                                    </div>
                                    <div class="product-content">
                                        <h5 class="product-name">
                                            <a href="/detailProduct/`+val.id+`" >`+val.name+`</a>
                                        </h5>
                                        <div class="price-box">
                                            <span class="price-regular" >`+val.price+`</span>

                                        </div>
                                        <div class="product-action-link">
                                            <a href="#" data-toggle="tooltip" title="Wishlist"><i class="ion-android-favorite-outline"></i></a>
                                            <a href="/addToCart/`+val.id+`" data-toggle="tooltip" title="Add To Cart"><i class="ion-bag"></i></a>
                                            <a onclick="showQuickView(`+val.id+`);" data-toggle="modal" data-target="#quick_view"> <span data-toggle="tooltip"
                                                                                                             title="Quick View"><i class="ion-ios-eye-outline"></i></span> </a>
                                        </div>
                                    </div>
                                </div>
                                <!-- product grid item end -->

                                <!-- product list item start -->
                                <div class="product-list-item mb-30" >
                                    <div class="product-thumb">
                                        <a href="/detailProduct/`+val.id+`">
                                            <img  src="/images/`+val.photo+`"  alt="product thumb">
                                        </a>
                                    </div>
                                    <div class="product-content-list">
                                        <h5 class="product-name">
                                            <a href="/detailProduct/`+val.id+`" >`+val.name+`</a>
                                        </h5>
                                        <div class="price-box">
                                            <span class="price-regular" >`+val.price+`</span>

                                        </div>
                                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce posuere
                                            metus vitae arcu imperdiet, id aliquet ante scelerisque. Sed sit amet
                                            sem vitae urna fringilla tempus.</p>
                                        <div class="product-link-2 position-static">
                                            <a href="#" data-toggle="tooltip" title="Wishlist"><i class="ion-android-favorite-outline"></i></a>
                                            <a href="/addToCart/`+val.id+`" data-toggle="tooltip" title="Add To Cart"><i class="ion-bag"></i></a>
                                            <a onclick="showQuickView(`+val.id+`);" data-toggle="modal" data-target="#quick_view"> <span data-toggle="tooltip"
                                                                                                             title="Quick View"><i class="ion-ios-eye-outline"></i></span> </a>
                                        </div>
                                    </div>
                                </div>
                                <!-- product list item start -->
                            </div>
                            `
            });
            $('#listProduct').html(content);
        }
    })
}

//searchByname shop_page
function searchByName()
{
    let result = document.getElementById("result").value;
    console.log(result);
    $.ajax({
        type:"GET",
        url:"api/searchProduct/"+result,
        success:function (data){
            var content = '';
            $.each(data,function (key,val){
                content +=`<div class="col-lg-4 col-sm-6" >
                                <!-- product grid item start -->
                                <div class="product-item mb-53">
                                    <div class="product-thumb">
                                        <a href="/detailProduct/`+val.id+`">
                                            <img src="/images/`+val.photo+`" alt="">
                                        </a>
                                    </div>
                                    <div class="product-content">
                                        <h5 class="product-name">
                                            <a href="/detailProduct/`+val.id+`" >`+val.name+`</a>
                                        </h5>
                                        <div class="price-box">
                                            <span class="price-regular" >`+val.price+`</span>

                                        </div>
                                        <div class="product-action-link">
                                            <a href="#" data-toggle="tooltip" title="Wishlist"><i class="ion-android-favorite-outline"></i></a>
                                            <a href="/addToCart/`+val.id+`" data-toggle="tooltip" title="Add To Cart"><i class="ion-bag"></i></a>
                                            <a onclick="showQuickView(`+val.id+`);" data-toggle="modal" data-target="#quick_view"> <span data-toggle="tooltip"
                                                                                                             title="Quick View"><i class="ion-ios-eye-outline"></i></span> </a>
                                        </div>
                                    </div>
                                </div>
                                <!-- product grid item end -->

                                <!-- product list item start -->
                                <div class="product-list-item mb-30" >
                                    <div class="product-thumb">
                                        <a href="/detailProduct/`+val.id+`">
                                            <img  src="/images/`+val.photo+`"  alt="product thumb">
                                        </a>
                                    </div>
                                    <div class="product-content-list">
                                        <h5 class="product-name">
                                            <a href="/detailProduct/`+val.id+`" >`+val.name+`</a>
                                        </h5>
                                        <div class="price-box">
                                            <span class="price-regular" >`+val.price+`</span>

                                        </div>
                                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce posuere
                                            metus vitae arcu imperdiet, id aliquet ante scelerisque. Sed sit amet
                                            sem vitae urna fringilla tempus.</p>
                                        <div class="product-link-2 position-static">
                                            <a href="#" data-toggle="tooltip" title="Wishlist"><i class="ion-android-favorite-outline"></i></a>
                                            <a href="/addToCart/`+val.id+`" data-toggle="tooltip" title="Add To Cart"><i class="ion-bag"></i></a>
                                            <a onclick="showQuickView(`+val.id+`);" data-toggle="modal" data-target="#quick_view"> <span data-toggle="tooltip"
                                                                                                             title="Quick View"><i class="ion-ios-eye-outline"></i></span> </a>
                                        </div>
                                    </div>
                                </div>
                                <!-- product list item start -->
                            </div>
                            `
            });
            $('#listProduct').html(content);
            alert(data.length + " sản phẩm được tìm thấy!");
        }
        })
}




//searchByname home_page
function searchByNameInHomePage()
{
    let result = document.getElementById("result").value;
    $.ajax({
        type:"GET",
        url:"api/searchProduct/"+result,
        success:function (data){

            var content = ``;
            $.each(data,function (key,val){
                content +=`  <div className="col-4" style="width: 25%;" >
                            
                            <div class="product-data " style=" margin: 10px;padding: 20px;">
                            <div class="product-thumb">
                                <a href="/detailProduct/`+val.id+`">
                                    <img src="/images/`+val.photo+`" alt="">
                                </a>
                            </div>
                            <div class="product-content">
                                <h5 class="product-name">
                                    <a href="/detailProduct/`+val.id+`" >`+val.name+`</a>
                                </h5>
                                <div class="price-box">
                                    <span class="price-regular" >`+val.price+`</span>

                                </div>
                                <div class="product-action-link">
                                    <a href="#" data-toggle="tooltip" title="Wishlist"><i class="ion-android-favorite-outline"></i></a>
                                    <a href="/addToCart/`+val.id+`" data-toggle="tooltip" title="Add To Cart"><i class="ion-bag"></i></a>
                                    <a onclick="showQuickView(` + val.id+`);" data-toggle="modal" data-target="#quick_view"> <span data-toggle="tooltip" title="Quick View"><i class="ion-ios-eye-outline"></i></span> </a>
                                </div>
                            </div>
                        </div>
                        </div>
                            
                        `
            });
            // content += ``
            $('#listProduct').html(content);
            alert(data.length + " sản phẩm được tìm thấy!");
        }
    })
}

function sortbyPrice(){
    var select = document.getElementById('sortbyPrice');
    var value = select.options[select.selectedIndex].value;
    $.ajax({
        type:"GET",
        url:"api/sortProductByPrice/"+value,
        success:function (data){
            var content = '';
            $.each(data,function (key,val){
                content +=`<div class="col-lg-4 col-sm-6" >
                                <!-- product grid item start -->
                                <div class="product-item mb-53">
                                    <div class="product-thumb">
                                        <a href="/detailProduct/`+val.id+`">
                                            <img src="/images/`+val.photo+`" alt="">
                                        </a>
                                    </div>
                                    <div class="product-content">
                                        <h5 class="product-name">
                                            <a href="/detailProduct/`+val.id+`" >`+val.name+`</a>
                                        </h5>
                                        <div class="price-box">
                                            <span class="price-regular" >`+val.price+`</span>

                                        </div>
                                        <div class="product-action-link">
                                            <a href="#" data-toggle="tooltip" title="Wishlist"><i class="ion-android-favorite-outline"></i></a>
                                            <a href="/addToCart/`+val.id+`" data-toggle="tooltip" title="Add To Cart"><i class="ion-bag"></i></a>
                                            <a onclick="showQuickView(`+val.id+`);" data-toggle="modal" data-target="#quick_view"> <span data-toggle="tooltip"
                                                                                                             title="Quick View"><i class="ion-ios-eye-outline"></i></span> </a>
                                        </div>
                                    </div>
                                </div>
                                <!-- product grid item end -->

                                <!-- product list item start -->
                                <div class="product-list-item mb-30" >
                                    <div class="product-thumb">
                                        <a href="/detailProduct/`+val.id+`">
                                            <img  src="/images/`+val.photo+`"  alt="product thumb">
                                        </a>
                                    </div>
                                    <div class="product-content-list">
                                        <h5 class="product-name">
                                            <a href="/detailProduct/`+val.id+`" >`+val.name+`</a>
                                        </h5>
                                        <div class="price-box">
                                            <span class="price-regular" >`+val.price+`</span>

                                        </div>
                                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce posuere
                                            metus vitae arcu imperdiet, id aliquet ante scelerisque. Sed sit amet
                                            sem vitae urna fringilla tempus.</p>
                                        <div class="product-link-2 position-static">
                                            <a href="#" data-toggle="tooltip" title="Wishlist"><i class="ion-android-favorite-outline"></i></a>
                                            <a href="/addToCart/`+val.id+`" data-toggle="tooltip" title="Add To Cart"><i class="ion-bag"></i></a>
                                            <a onclick="showQuickView(`+val.id+`);" data-toggle="modal" data-target="#quick_view"> <span data-toggle="tooltip"
                                                                                                             title="Quick View"><i class="ion-ios-eye-outline"></i></span> </a>
                                        </div>
                                    </div>
                                </div>
                                <!-- product list item start -->
                            </div>
                            `
            });
            $('#listProduct').html(content);

        }
    })
}

//paging product

function pagingProduct(pageNo){
console.log(pageNo);
    $.ajax({
        type: "GET",
        url:"api/pagingProduct/" + pageNo,
        success: function (result){
            var content = "";
            $.each(result,function (key,val){
                content += generateProduct(val);
            });

            $('#listProduct').html(content);

        }

    })


}


// edit cart

$(document).ready(function () {
    getValue();
    getItemCartMini();
})

function getValue() {
    var cart_item = document.getElementById("cart-item");
    // console.log(cart_item);
    var cart_rows = cart_item.getElementsByClassName("cart-row");
    // console.log(cart_rows);
    var total = 0;
    for (i = 0; i < cart_rows.length; i++) {
        var cart_row = cart_rows[i];
        console.log("cart_row:")
        console.log(cart_row)
        var price_item = cart_row.getElementsByClassName("cart_price")[0];
        console.log("price-item:")
        console.log(price_item)
        var quantity_item = cart_row.getElementsByClassName("cart-quantity-input")[0];
        var price = parseFloat(price_item.innerText);
        var quantity = quantity_item.value
        total = total + (price * quantity);
    }
    console.log(total)
    document.getElementById("cart-total-price").innerHTML = total + "vnd";
}



function changeQuantity(itemNumber,id){
    console.log({
        itemNumber,
        id,
    });
    if (itemNumber == 0) {
        var option = confirm("Bạn có muốn xóa sản phẩm này không ?")
        if (option == true){
            removeProductInCart(id);
        }
    }
    document.getElementById("cart-quantity-input-"+id).textContent = parseInt(itemNumber);
    var price_item = document.getElementById("cart-price-" + id).innerText;
    price_item = parseFloat(price_item);
    document.getElementById("pro-subtotal-" + id).textContent = parseInt(itemNumber) * price_item;
    getValue();
}


//remove product in cart
function removeProductInCart(id){
    $.ajax({
        type: "GET",
        url: "api/removeProduct/" + id,
        success: function (result) {
            location.reload();
            alert("Delete success!");
        }


    })

}

//total in minicart
function getItemCartMini(){
    var cart_mini_item = document.getElementsByClassName("minicart-content");
    var total = 0;
    for (i = 0; i < cart_mini_item.length; i++){
        var quantity = cart_mini_item[i].getElementsByClassName("cart-quantity")[0].textContent.split(':')[1];
        var price = cart_mini_item[i].getElementsByClassName("cart-price")[0].textContent.split(':')[1];
        total = total + parseFloat(quantity * price);

    }
    console.log("total" + total)
   document.getElementById("total-minicart").innerHTML = total + ' vnd'

}