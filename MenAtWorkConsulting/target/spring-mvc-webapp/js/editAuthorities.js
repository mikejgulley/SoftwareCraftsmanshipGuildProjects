$(document).ready(function() {
	$.ajax({
		url: "authority"
	}).success(function(data) {
		$.each(data, function(index, user) {
			$('#username-dropdown').append($('<option>').text(user.userName));
		});
	});
});