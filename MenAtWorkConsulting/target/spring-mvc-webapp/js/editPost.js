$(document).ready(function () {
    $(function () {
        $("#from").datepicker({
            defaultDate: new Date($('#from').val()),
            changeMonth: true,
            numberOfMonths: 1,
//            onClose: function (selectedDate) {
//                $("#to").datepicker("option", "minDate", selectedDate);
//            }
        });
        $("#to").datepicker({
            defaultDate: $('#to').val(),
            changeMonth: true,
            numberOfMonths: 1
//            onClose: function (selectedDate) {
//                $("#from").datepicker("option", "maxDate", selectedDate);
//            }
        });
    });

    $('#edit-post-button').click(function () {
        event.preventDefault();
        var date = $('#from').datepicker('getDate');
        var startDate = (date.getMonth() + 1) + "/" + date.getDate() + "/" + date.getFullYear();
        var date2 = $('#to').datepicker('getDate');
        var expirationDate = (date2.getMonth() + 1) + "/" + date2.getDate() + "/" + date2.getFullYear();

        if ($('#tags').val() === "") {
            var tagsList = [];
        } else {
            var tagsList = $('#tags').val().split(",");

            for (var i = 0; i < tagsList.length; i++) {
                // prints string at that index
                // console.log(tagsList[i.valueOf()]);
				if (tagsList[i].length > 1) {
					if (tagsList[i].charAt(0) === " ") {
						tagsList[i] = tagsList[i.valueOf()].substring(1);
					}
					tagsList[i] = tagsList[i].toLowerCase();
				} else {
					tagsList.splice(i);
				}
            }
        }

        $.ajax({
            type: 'POST',
            url: 'editPost',
            data: JSON.stringify({
                postId: $('#postId').text(),
                title: $('#postTitle').val(),
                body: tinyMCE.get('content').getContent(),
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