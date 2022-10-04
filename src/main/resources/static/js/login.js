$('button[type="submit"]').click(function(event) {
    event.preventDefault();

    $.post("/email", {
        'email': $('#email').val()
    }, function() {
        iziToast.show({
            title: 'Success',
            message: 'Provided email address is valid',
            position: 'topRight',
            timeout: 500000,
            color: 'green'
        });

    }).fail(function() {
        iziToast.show({
            title: 'Fail',
            message: 'This is not a valid email address',
            position: 'topRight',
            color: 'red'
        });
    });
});