
$('#province').on('change', () => {

    var provinceName = $("#province").find(":selected").text();
    // console.log(provinceName)
    var district = $("#district")
    var result;
    var pro = 'Nam Định'
    const json = {
        'id' : 1,
    };

    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        dataType: 'json',
        url: 'http://localhost:8080/api/district',
        data: JSON.stringify(json),
        success: (data) => {
            alert(data)
        }
    })
})
// function callAPI(tile, value, type) {
//     fetch('http://localhost:8080/api/district', {
//         method: "GET",
//         body: JSON.stringify(value)
//     }).then(res => {
//         return res.json()
//     }).then(data => {
//         let html = `<option value="Chọn ${type}">Chọn ${type}</option>`
//         const arrays = JSON.parse(data)
//         for (let i = 0; i < arrays.length; ++i) {
//             html += `<option value="${arrays[i]}">${arrays[i]}</option>`
//         }
//         $('#' + tile).html(html)
//     })
// }
//
// $('#province').on('change', () => {
//     let province = $('#province').val().toString();
//     callAPI('district', {'name': province}, 'Quận Huyện')
// })
//
// $('#district').on('change', () => {
//     let district = $('#district').val().toString();
//     callAPI('ward', {'district': district}, 'Phường Xã')
// })
