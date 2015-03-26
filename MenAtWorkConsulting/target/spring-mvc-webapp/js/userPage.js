$(document).ready(function() {
	var id = $('#pageId').text();
//	$('#edit-delete-page-button-area')
//        .append($('<div>').addClass('col-xs-12').css('text-align', 'right')
//                    .append($('<a>')
//                        .addClass('edit-button')
//			.attr('href', 'editPage/' + id).text('Edit'))
//                    .append($('<a>')
//                        .addClass('delete-button')
//			.attr('id', 'deleteButton').text('Delete')))
//                    ;
        
        
	$('#deleteButton').click(function() {
		var answer = confirm("Do you really want to delete this post?");
		if (answer === true) {
			$.ajax({
				type: 'POST',
				url: 'pages/' + id
			}).success(function () {
				window.location.replace("/MenAtWorkConsulting/home");
			});
		}
	});
});