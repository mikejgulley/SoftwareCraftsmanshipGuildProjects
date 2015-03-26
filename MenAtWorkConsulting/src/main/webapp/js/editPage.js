$(document).ready(function () {
    $('#edit-page-button').click(function () {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: 'editPage',
            data: JSON.stringify({
                pageId: $('#pageId').text(),
                title: $('#pageTitle').val(),
                body: tinyMCE.get('content').getContent()
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