
// active paging button
var x = $(".pagination-box>li");

x[0].classList.add("active") ;
$(".pagination-box>li").click(function (e){

    $.each(x, function (key, val){

        if(val.classList.contains("active")){
            val.classList.remove("active");

        }
    });
    e.currentTarget.classList.add("active");

})
