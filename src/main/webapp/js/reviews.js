

$(document).ready(()=>{
    $('#submit').on('click', (e)=>{
        e.preventDefault()
        const file = $('.file-upload-input')[0].files[0]
        const array = $('#feedback').serializeArray()
        const object = {}
        $.each(array, (index, value) => {
            object[value.name] = value.value
        })

        if (!file) {
            $.ajax({
                type: 'POST',
                contentType: 'application/json',
                url: 'api/reviews',
                data: JSON.stringify(object),
                success: (data) => {
                    alert(data);
                },
                error: (error) => {
                    alert(error)
                }
            })
        } else {
            object['urlImage'] = 'image/' + file.name
            const formData = new FormData()
            formData.append("file", file)
            const request = new XMLHttpRequest()
            request.responseType = 'json'
            request.addEventListener("load", () => {
                if (request.status === 200) {
                    $.ajax({
                        type: 'POST',
                        contentType: 'application/json',
                        url: 'api/reviews',
                        data: JSON.stringify(object),
                        success: (data) => {
                            alert(data);
                        },
                        error: (error) => {
                            alert(error)
                        }
                    })
                }
            })
            request.open("POST", "fileUpload")
            request.send(formData)
        }
    })
})