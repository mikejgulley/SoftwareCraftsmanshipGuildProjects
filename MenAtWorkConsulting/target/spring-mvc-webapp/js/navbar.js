$(document).ready(function() {
	$.ajax({
		url: '/MenAtWorkConsulting/pages'
	}).success(function (data, status) {
		$.each(data, function (index, page) {
			$('#navbar').append($('<li>')
					.append($('<a>').attr('href', '/MenAtWorkConsulting/pages/' + page.pageId)
							.text(page.title))
					);
		});
	});
});