$(document).ready(() => {


    const quantity = $('#quantity')
    $('#add').on('click', (e) => {
        e.preventDefault()
        const amount = parseInt(quantity.text())
        quantity.text(amount + 1)
    })

    // sub quantity
    $('#sub').on('click', (e) => {
        e.preventDefault()
        const amount = parseInt(quantity.text())
        if (amount > 1) {
            quantity.text(amount - 1)
        }
    })


    const like = $('.reviews-detail-content div #like')
    for (let i = 0; i < like.length; ++i) {
        $(like[i]).on('click', (e) => {
            const review = $(like[i])
            const reviewId = review.children('#ID').val();
            console.log(reviewId);

            // const listReview  = $('input[name="reviewId"]')
            // const userId = $('#userId').data('id')
            // for (var i = 0; i < listReview.length; i++) {
            //     let oneReview = $(listReview[i]).val();
            //     if (reviewId == oneReview) {
            // console.log(reviewId)
            let isLike = 'rgb(204, 204, 204)' === $(`.like` + reviewId).css('color') ? true : false
            const totalLike = $(`.total-like` + reviewId)
            const userId = $('#userId').data('id')
            const object = {
                'reviewId': reviewId,
                'userId': userId
            }
            $.ajax({
                method: 'PUT',
                contentType: 'application/json',
                url: 'http://localhost:8080/api/reviews/like',
                data: JSON.stringify(object),
                success: (data) => {
                    if (isLike) {
                        $(`.like` + reviewId).css({
                            'color': '#ee4d2d'
                        })
                        $('total-like' + reviewId).text(data[0].totalLike)
                    } else {
                        $(`.like` + reviewId).css({
                            'color': '#ccc'
                        })
                        $('total-like' + reviewId).text(data[0].totalLike)
                    }
                    if (parseInt((data[0].isLike)) < 1) {
                        totalLike.text('? Hữu ích')
                    } else {
                        totalLike.text(data[0].isLike)
                    }
                }
            })
        })
    }
})