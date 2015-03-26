$(document).ready(function () {
    loadMessages();

    $('#messageContainer').on('click', '.message-title', function (event) {
        getSingleMessage($(this).data('message-id'));
    });

});

function loadMessages() {
    clearMessages();
    $.ajax({
        url: 'messages'
    }).success(function (data, status) {
        $.each(data, function (index, message) {
            displayMessage(message);
        });
    });
}

//clear posts from display before redisplaying with changed content
function clearMessages() {
    $('#messageContainer').empty();
}

function deleteMessage(id) {
    //confirmation before deletion
    var answer = confirm("Do you really want to delete this message?");
    if (answer === true) {
        $.ajax({
            type: 'DELETE',
            url: 'messages/' + id
        }).success(function () {
            clearMessages();
            loadMessages();
        });
    }
}

function displayMessage(message) {
    $('#messageContainer').prepend($('<div>').attr('class', 'row')
            .append($('<div>').attr('class', 'col-md-12')
                    .append($('<div>')
                            .addClass('panel panel-default')
                            .append($('<h3>')
                                    .attr('data-message-id', message.contactId)
                                    .text("#" + message.contactId + " - " + message.firstName + " " + message.lastName)
                                    .addClass('panel-heading')
                                    .addClass('message-title'))
                            .append($('<div>').addClass('panel-body').text(message.message)
                                    )
                            .append($('<div>').addClass('panel-footer')
                                    .append($('<div>').addClass('row').attr('id', 'footer-row')
                                            .append($('<div>').addClass('col-xs-6').append($('<p>')
                                                    .attr('font-size', '8px')
//													.text('Reply to: ' + message.email + " on " + message.date)
                                                    .text('Reply to: ').append($('<a>').attr("href", "mailto:" + message.email).text(message.email))
                                                    )
                                                    )
                                            .append($('<div>').addClass('col-xs-6').css('text-align', 'right')
                                                    .append($('<a>')
                                                            .attr({
                                                                'onClick': 'deleteMessage(' + message.contactId + ')'
                                                            })
                                                            .addClass('delete-button')
                                                            .text('Delete')
                                                            )
                                                    )
                                            )
                                    )
                            )
                    )
            );
}
;

function getSingleMessage(id) {
    clearMessages();
    $.ajax({
        url: 'message/' + id
    }).success(function (data, status) {
        displayMessage(data);
//		readMore();
    });
}