//this is a shortcut for $(document).ready(function() {})

//
//$(function () {
//
//
//    $('#signup-button').click(function () {
//        event.preventDefault();
//        $.ajax({
//            type: 'POST',
//            url: 'addUser',
//            data: JSON.stringify({
//                userName: $('#userName').val(),
//                passwordHash: $('#passwordHash').val(),
//                passwordSalt: $('#passwordSalt').val(),
//                email: $('#email').val()
//            }),
//            headers: {
//                'Accept': 'applicatoin/json',
//                'Content-Type': 'application/json'
//            }
//        });
//    });
//
//});     