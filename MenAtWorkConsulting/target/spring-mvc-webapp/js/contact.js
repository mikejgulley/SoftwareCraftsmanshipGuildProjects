$(document).ready(function () {
    $('#contact-button').click(function (event) {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            //request is sent to following url in ContactController
            url: 'sendMessage',
            data: JSON.stringify({
                firstName: $('#firstName').val(),
                lastName: $('#lastName').val(),
                email: $('#email').val(),
                message: $('#message').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
//        }).success(function () {


        }).success(function (data, status) {

            window.location.replace("/MenAtWorkConsulting/thankyou");


        }).error(function (data, status) {
            //clears the following fields; but currently clears all of these fields if the form is not completely valid.
            //maybe NO fields should be cleared and just error messages displayed. The problem is that even valid fields are cleared 
            //when there is an error in one of them

//        $('#email').val('');
//        $('#firstName').val('');            

// #1 - remove all validation error messages
            $('#validationErrors').empty();
            //#2 - Go through each of the fieldErrors and display the associated error
            // message in the validationErrors div
            $.each(data.responseJSON.fieldErrors, function (index, validationError) {
                var errorDiv = $('#validationErrors');
                errorDiv.append(validationError.message).append($('<br>'));
            });
        });


    });
});
