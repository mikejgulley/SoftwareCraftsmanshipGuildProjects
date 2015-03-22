$('document').ready(function () {
    loadMovieLibrary();

    $('#add-button').click(function (event) {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: 'movie',
            data: JSON.stringify({
                title: $('#add-title').val(),
                releaseDate: $('#add-release-date').val(),
                mpaaRating: $('#add-mpaa-rating').val(),
                director: $('#add-director').val(),
                studio: $('#add-studio').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
//            'data-type': 'json'
            'dataType': 'json'
        }).success(function () {
            $('#add-title').val('');
            $('#add-release-date').val('');
            $('#add-mpaa-rating').val('');
            $('#add-director').val('');
            $('#add-studio').val('');
            loadMovieLibrary();
        });
    });

    $('#edit-button').click(function (event) {
        event.preventDefault();
        $.ajax({
            type: 'PUT',
            url: 'movie/' + $('#edit-movie-id').val(),
            data: JSON.stringify({
                movieId: $('#edit-movie-id').val(),
                title: $('#edit-title').val(),
                releaseDate: $('#edit-release-date').val(),
                mpaaRating: $('#edit-mpaa-rating').val(),
                director: $('#edit-director').val(),
                studio: $('#edit-studio').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
//            'data-type': 'json'
            'dataType': 'json'
        }).success(function () {
            loadMovieLibrary();
        });
    });

});

// ============================
// FUNCTIONS
// ============================

function loadMovieLibrary() {
    // clear table so we don't repeat info
    clearMovieLibrary();

    // grab element to write data into
    var movieTable = $('#contentRows');

    var movieCounter = 0;
    // iterate through objects
    $.ajax({
        url: 'movies'
    }).success(function (data, status) {
        $.each(data, function (index, movie) {
            movieTable.append($('<tr>')
                    .append($('<td>')
                            .append($('<a>')
                                    .attr({'data-movie-id': movie.movieId, 'data-toggle': 'modal', 'data-target': '#detailsModal'})
                                    .text(movie.title)
                                    )
                            )
                    .append($('<td>').text(movie.releaseDate))
//                    .append($('<td>').text(movie.mpaaRating))
//                    .append($('<td>').text(movie.director))
//                    .append($('<td>').text(movie.studio))
                    .append($('<td>')
                            .append($('<a>')
                                    .attr({'data-movie-id': movie.movieId, 'data-toggle': 'modal', 'data-target': '#editModal'})
                                    .text('Edit')
                                    )
                            )
                    .append($('<td>')
                            .append($('<a>')
                                    .attr({'onClick': 'deleteMovie(' + movie.movieId + ')'})
                                    .text('Delete')
                                    )
                            )
                    );
            movieCounter++;
        });
    });
    $.ajax({
        url: 'movies'
    }).success(function (data, status) {
        movieTable.append($('<tr>')
                .append($('<td>')
                        .append($('<b>')
                                .text('Total Movies: ' + movieCounter)
                                )
                        )
                .append($('<td>')
                        )
                .append($('<td>')
                        )
                .append($('<td>')
                        )
                );
    });
}

function deleteMovie(id) {
    var answer = confirm('Do you really want to delete this movie?');
    if (answer === true) {
        $.ajax({
            type: 'DELETE',
            url: 'movie/' + id
        }).success(function () {
            loadMovieLibrary();
        });
    }
}

function clearMovieLibrary() {
    $('#contentRows').empty();
}

// runs in response to the show.bs.modal event
$('#createModal').on('show.bs.modal', function (event) {
    var element = $(event.relatedTarget);
    var movieId = element.data('movie-id');

    var modal = $(this);

});

$('#detailsModal').on('show.bs.modal', function (event) {
    var element = $(event.relatedTarget);
    var movieId = element.data('movie-id');

    var modal = $(this);

    $.ajax({
        type: 'GET',
        url: 'movie/' + movieId
    }).success(function (movie) {
        modal.find('#movie-id').text(movie.movieId);
        modal.find('#movie-title').text(movie.title);
        modal.find('#movie-releaseDate').text(movie.releaseDate);
        modal.find('#movie-mpaaRating').text(movie.mpaaRating);
        modal.find('#movie-director').text(movie.director);
        modal.find('#movie-studio').text(movie.studio);
    });
});

$('#editModal').on('show.bs.modal', function (event) {
    var element = $(event.relatedTarget);
    var movieId = element.data('movie-id');

    var modal = $(this);

    $.ajax({
        type: 'GET',
        url: 'movie/' + movieId
    }).success(function (movie) {
        modal.find('#movie-id').text(movie.movieId);
        modal.find('#edit-movie-id').val(movie.movieId);
        modal.find('#edit-title').val(movie.title);
        modal.find('#edit-release-date').val(movie.releaseDate);
        modal.find('#edit-mpaa-rating').val(movie.mpaaRating);
        modal.find('#edit-director').val(movie.director);
        modal.find('#edit-studio').val(movie.studio);
    });
});