    function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}
    $(document).ready(function(){
    $(".print-receipt").click(function(){
        if($("#report-end").val() != "" && $("#report-start").val() != ""){
            var end = new Date($("#report-end").val());
            var start = new Date($("#report-start").val());
            if(start <= end){
                window.print();
            }else{
                alert("Hãy chọn khoảng thời gian hợp lệ");
            }
        }else{
            $("#print").click();
        }
    });
    if($("#report-end").val() != "" && $("#report-start").val() != ""){
    if($("#size").val() == 0 ){
    $("#line-null").text("Không có hóa đơn trong khoảng thời gian này");
    $("#line-null").show();
    $("#total-table").hide();
}else{
    $("#total-table").show();
    let totalAmount = 0;
    let totalDiscount = 0;
    let totalSale = 0;
    let totalValue = 0;
    for(let i = 0 ; i < $("#size").val(); i++){
    var detail = $(".main-content").find("tr.detail-line").eq(i);
    var val = parseInt(detail.children("td.total").text());
    totalValue += val;
    val = numberWithCommas(val);
    detail.children("td.total").text(val);
    val = parseInt(detail.children("td.discount").text());
    totalDiscount += val;
    val = numberWithCommas(val);
    detail.children("td.discount").text(val);
    val = parseInt(detail.children("td.sale").text());
    totalSale += val;
    val = numberWithCommas(val);
    detail.children("td.sale").text(val);
    val = parseInt(detail.children("td.amount").text());
    totalAmount += val;
}
    $("#totalAmount").text(numberWithCommas(totalAmount));
    $("#totalSale").text(numberWithCommas(totalSale));
    $("#totalDiscount").text(numberWithCommas(totalDiscount));
    $("#totalValue").text(numberWithCommas(totalValue));
}
}
    else{
    $("#line-null").text("Chọn khoảng thời gian");
    $("#line-null").show();
    $("#total-table").hide();
}
    $("#report-start").change(function(){
    if($("#report-end").val() != "" && $("#report-start").val() != ""){
    var end = new Date($("#report-end").val());
    var start = new Date($("#report-start").val());
    if(start > end){
    $("#total-table").hide();
    $("#line-null").text("Khoảng thời gian không hợp lệ");
    $("#line-null").show();
    $("tr.detail-line").remove();
    $("#error-start").text("Ngày bắt đầu phải bé hơn ngày kết thúc");
    $("#error-start").show();
    $("#error-end").hide();
}else{
    $(".view-but").click();
}
}
    else{
    $("#total-table").hide();
    $("#line-null").text("Khoảng thời gian không hợp lệ");
    $("#line-null").show();
    $("tr.detail-line").remove();
}
});
    $("#report-end").change(function(){
    if($("#report-end").val() != "" && $("#report-start").val() != ""){
    var end = new Date($("#report-end").val());
    var start = new Date($("#report-start").val());
    if(start > end){
    $("#line-null").text("Khoảng thời gian không hợp lệ");
    $("#line-null").show();
    $("tr.detail-line").remove();
    $("#error-end").text("Ngày kết thúc phải lớn hơn ngày bắt đầu");
    $("#error-end").show();
    $("#error-start").hide();
    $("#total-table").hide();
}else{
    $(".view-but").click();

}
}
    else{
    $("#total-table").hide();
    $("#line-null").text("Khoảng thời gian không hợp lệ");
    $("#line-null").show();
    $("tr.detail-line").remove();
}
});
    $(".menu-bar").click(function(event){
    const locate = $(event.delegateTarget).children("span.menu-txt").text();
    switch(locate){
    case "Trang chủ":
    window.location.href="/";
    break;
    case "Sản phẩm":
    window.location.href="/products";
    break;
    case "Doanh thu":
    window.location.href="/report/view";
    break;
    case "Khuyến mại":
    window.location.href="/discounts";
    break;
    case "Đăng xuất":
    window.location.href="/account/sign-out";
    break;
}
});

});
