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
