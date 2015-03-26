$(document).ready(function () {
    loadPosts();

    $(function () {
        $("#from").datepicker({
            defaultDate: "+1w",
            changeMonth: true,
            maxDate: new Date(),
            numberOfMonths: 1
//			onClose: function (selectedDate) {
//				$("#to").datepicker("option", "minDate", selectedDate);
//			}
        });
        $("#to").datepicker({
            defaultDate: "+1w",
            changeMonth: true,
            maxDate: new Date(),
            numberOfMonths: 1
//			onClose: function (selectedDate) {
//				$("#from").datepicker("option", "maxDate", selectedDate);
//			}
        });
        $("#creation").datepicker({
            defaultDate: "+1w",
            changeMonth: false,
            numberOfMonths: 1,
            onClose: function (selectedDate) {
                $("#creation").datepicker("option", "maxDate", selectedDate);
            }
        });
    });

    $('#postContainer').on('click', '.tag', function (event) {
        getPostsByTag($(this).text());
    });

    $('#date-range-button').click(function (event) {
        event.preventDefault();

        var date = $('#from').datepicker('getDate');
        var date1 = (date.getMonth() + 1) + "/" + date.getDate() + "/" + date.getFullYear();
        date = $('#to').datepicker('getDate');
        var date2 = (date.getMonth() + 1) + "/" + date.getDate() + "/" + date.getFullYear();

        getPostsByDateRange(date1, date2);
    });

    $('#createModal').on('show.bs.modal', function (event) {
        var element = $(event.relatedTarget);
        var postId = element.data('post-id');
        $('#postId').text(postId);
    });

    $('#add-button').click(function () {
        $.ajax({
            type: 'PUT',
            url: '',
            data: JSON.stringify({
//                    userId: $('#active-user').val(),
                userId: $('#active-user').val(),
                comment: $('#add-comment').val(),
                postId: $('#postId').text(),
                date: "DATE"
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json; charset=utf-8'
            }
        }).success(function () {
            $('#add-comment').val("");
            loadComments();
        });
    });

    $('#bangers-button').click(function () {
        $('p').css('font-family', 'Bangers');
        $('h3').css('font-family', 'Bangers');
        $('a').css('font-family', 'Bangers');
    });

    $('#rancho-button').click(function () {
        $('p').css('font-family', 'Rancho');
        $('h3').css('font-family', 'Rancho');
        $('a').css('font-family', 'Rancho');
    });

    $('#default-button').click(function () {
        $('p').css('font-family', 'Verdana');
        $('h3').css('font-family', 'Verdana');
        $('a').css('font-family', 'Verdana');
    });
});

function loadPosts() {
    clearPosts();
    $.ajax({
        url: 'posts'
    }).success(function (data, status) {
        $.each(data, function (index, post) {
            displayPost(post);
        });
        loadComments();
        readMore();
    });
}

//clear posts from display before redisplaying with changed content
function clearPosts() {
    $('#postContainer').empty();
}

function deletePost(id) {
    //confirmation before deletion
    var answer = confirm("Do you really want to delete this post?");
    if (answer === true) {
        $.ajax({
            type: 'DELETE',
            url: 'post/' + id
        }).success(function () {
            loadPosts();
        });
    }
}

function deleteComment(id) {
    var answer = confirm("Do you really want to delete this comment?");
    if (answer === true) {
        $.ajax({
            type: 'DELETE',
            url: 'comment/' + id
        }).success(function () {
            clearComments();
            loadComments();
        });
    }
}

// runs in response to the show.bs.modal event
$('#createModal').on('show.bs.modal', function (event) {
    var element = $(event.relatedTarget);
    var commentId = element.data('comment-id');

    var modal = $(this);

});

function getSinglePost(id) {
    clearPosts();
    $.ajax({
        url: 'post/' + id
    }).success(function (data, status) {
        displayPost(data);
        loadComments();
        readMore();
    });
}

function getPostsByTag(tagName) {
    clearPosts();
    $.ajax({
        url: 'posts/' + tagName
    }).success(function (data, status) {
        clearPosts();
        $.each(data.reverse(), function (index, post) {
            displayPost(post);
        });
        loadComments();
        readMore();
    });
}

function getPostsByDateRange(date1, date2) {
    $.ajax({
        url: 'post/daterange',
        type: 'POST',
        data: JSON.stringify({
            date1: date1,
            date2: date2
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    }).success(function (data, status) {
        clearPosts();
        $.each(data, function (index, post) {
            displayPost(post);
        });
        loadComments();
        readMore();
    });
}

function readMore() {
    // Read More button functionality
    $('.panel-body').each(function () {
        var max_length = 500; /* set the max content length before a read more link will be added */
        var content_length = $(this).text().length;

        if (content_length > max_length) { /* check for content length */
            var short_content = $(this).text().substr(0, max_length);
            var long_content = $(this).html().substr(max_length);

//                    console.log(content_length);
//                    console.log(short_content);
//                    console.log(long_content);

            $(this).html(short_content +
                    '<a href="#" class="read_more"><br/>Read More</a>' +
                    '<span class="more_text" style="display:none;">' + long_content + '</span>'); /* Alter the html to allow the read more functionality */

            $(this).find('a.read_more').click(function (event) { /* find the a.read_more element within the new html and bind the following code to it */

                event.preventDefault(); /* prevent the a from changing the url */
                $(this).css('display', 'none').hide(); /* hide the read more button */
                $(this).parents('.item').find('.more_text').show(); /* show the .more_text span */
            });
        }
    });
}
;

function displayPost(post) {
    $('#postContainer').prepend($('<div>').attr('class', 'row')
            .append($('<div>').attr('class', 'col-md-12')
                    .append($('<div>')
                            .addClass('panel panel-default')
                            .append($('<h3>')
                                    .attr('data-post-id', post.postId)
                                    .text(post.title)
                                    .addClass('panel-heading')
                                    .addClass('post-title'))
                            .append($('<div>').addClass('panel-body').append($(post.body)
//                                            .addClass('panel-text')
                                    ))

                            // start tags
                            .append($('<div>').attr('id', 'td' + post.postId).addClass('tags'))
                            // end tags

                            .append($('<div>').addClass('panel-footer')
                                    .append($('<div>').addClass('row').attr('id', 'footer-row')
                                            .append($('<div>').addClass('col-xs-6').append($('<p>')
                                                    .attr('font-size', '8px')
                                                    .text('Posted by: ' + post.author + " on " + post.date)
                                                    )
                                                    )
                                            .append($('<div>').addClass('col-xs-6').css('text-align', 'right').append($('<a>')
                                                    .attr('href', '/MenAtWorkConsulting/admin/editPost/' + post.postId)
                                                    .addClass('edit-button')
                                                    .text('Edit')
                                                    )
                                                    .append($('<a>')
                                                            .attr({
                                                                'onClick': 'deletePost(' + post.postId + ')'
                                                            })
                                                            .addClass('delete-button')
                                                            .text('Delete')
                                                            )
                                                    )
                                            )
                                    ).append($('<div>').addClass('comments').attr('id', post.postId))
                            )
                    .append($('<div>').addClass('row').attr('id', 'footer-row')
                            .append($('<div>')
                                    .append($('<button>')).attr('type', 'button').attr('id', 'add-comment-button').attr({'data-post-id': post.postId}).addClass('btn btn-primary').text('Add Comment')
                                    .attr('data-toggle', 'modal').attr('data-target', '#createModal')
                                    )
                            )
                    )
            );
    $.each(post.tags, function (i) {
        $('#td' + post.postId).append($('<span>').text(post.tags[i]).addClass('tag'));
    });
}
;

function loadComments() {
    clearComments();
    $('.comments').each(function () {
        var id = $(this).attr('id');
        var currentDiv = $(this);

        $.ajax({
            type: 'GET',
            url: 'comments/' + id
        }).success(function (data, status) {
            $.each(data, function (index, comment) {
                currentDiv.append($('<ul>')
                        .addClass('list-group')
                        .append($('<li>').addClass('list-group-item').text(comment.comment + ' - Comment by: ' + comment.user)
//                                            .append($('<a>').addClass('delete-button').css('float', 'right').text('Delete'))
                                .append($('<a>')
                                        .attr({
                                            'onClick': 'deleteComment(' + comment.commentId + ')'
                                        })
                                        .addClass('delete-button')
                                        .css('float', 'right')
                                        .text('Delete')
                                        )
                                )
                        );
            });
        });
    });
}

function clearComments() {
    $('.comments').each(function () {
        $(this).empty();
    });
}