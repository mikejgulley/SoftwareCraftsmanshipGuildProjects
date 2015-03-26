$(document).ready(function () {
    loadGuestPosts();

    $('#guestPostContainer').on('click', '.post-title', function (event) {
        getSingleGuestPost($(this).data('post-id'));
    });

    $('#add-guest-post-button').click(function () {
        $(event.preventDefault());

        var date = new Date();
        var currentDate = (date.getMonth() + 1) + "/" + date.getDate() + "/" + date.getFullYear();

        if ($('#from').val() === "") {
            date = new Date();
        } else {
            date = $('#from').datepicker('getDate');
        }
        var startDate = (date.getMonth() + 1) + "/" + date.getDate() + "/" + date.getFullYear();

        var expirationDate;
        if ($('#to').val() === "") {
            expirationDate = "1/1/2099";
        } else {
            date = $('#to').datepicker('getDate');
            expirationDate = (date.getMonth() + 1) + "/" + date.getDate() + "/" + date.getFullYear();
        }

        if ($('#tags').val() === "") {
            var tagsList = [];
        } else {
            var tagsList = $('#tags').val().split(",");

            for (var i = 0; i < tagsList.length; i++) {
                // prints string at that index
                // console.log(tagsList[i.valueOf()]);
                if (tagsList[i.valueOf()].length > 1) {
                    if (tagsList[i.valueOf()].charAt(0) === " ") {
                        tagsList[i.valueOf()] = tagsList[i.valueOf()].substring(1);
                    }
                    tagsList[i] = tagsList[i].toLowerCase();
                }
            }
        }
        $.ajax({
            type: "POST",
            url: 'submitGuestPost',
            data: JSON.stringify({
                title: $('#postTitle').val(),
                authorId: 3,
                body: tinyMCE.get('content').getContent(),
                date: currentDate,
                startDate: startDate,
                expirationDate: expirationDate,
                tags: tagsList
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).success(function () {
            window.location.replace("/MenAtWorkConsulting/home");
        });
    });
});

function loadGuestPosts() {
    clearGuestPosts();
    $.ajax({
        url: 'allGuestPosts'
    }).success(function (data, status) {
        $.each(data, function (index, post) {
            displayGuestPost(post);
        });
//		readMore();
    });
}

//clear posts from display before redisplaying with changed content
function clearGuestPosts() {
    $('#guestPostContainer').empty();
}

function approveGuestPost(id) {
    //confirmation before approval
    var answer = confirm("Do you really want to approve this post?");
    if (answer === true) {
        $.ajax({
            type: 'POST',
            url: 'approveGuestPost/' + id
        }).success(function () {
            loadGuestPosts();
        });
    }
}

function deleteGuestPost(id) {
    //confirmation before deletion
    var answer = confirm("Do you really want to delete this post?");
    if (answer === true) {
        $.ajax({
            type: 'DELETE',
            url: 'guestPosts/' + id
        }).success(function () {
            loadGuestPosts();
        });
    }
}

function getSingleGuestPost(id) {
    clearPosts();
    $.ajax({
        url: 'guestPost/' + id
    }).success(function (data, status) {
        displayGuestPost(data);
//		loadComments();
//		readMore();
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
};

function displayGuestPost(post) {
    $('#guestPostContainer').prepend($('<div>').attr('class', 'row')
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
                                            )
                                    ).append($('<div>').addClass('comments').attr('id', post.postId))
                            )
                    .append($('<div>').addClass('row').attr('id', 'footer-row')
                            .append($('<div>')
                                        .append($('<button>'))
                                                .attr('type', 'button')
                                                .attr('id', 'approve-guest-post-button')
                                                .attr({'data-post-id': post.postId})
                                                .attr({'onClick': 'approveGuestPost(' + post.postId + ')'})
                                                .addClass('btn btn-primary')
                                                .text('Approve')
                                    )
                                    .append($('<div>')
                                            .append($('<button>'))
                                                    .attr('type', 'button')
                                                    .attr('id', 'delete-guest-post-button')
                                                    .attr({'data-post-id': post.postId})
                                                    .attr({'onClick': 'deleteGuestPost(' + post.postId + ')'})
                                                    .addClass('btn btn-danger')
                                                    .css('margin-left', '10px')
                                                    .text('Delete')
                                    )
                            )
                    )
            );
    $.each(post.tags, function (i) {
        $('#td' + post.postId).append($('<span>').text(post.tags[i]).addClass('tag'));
    });
}
;