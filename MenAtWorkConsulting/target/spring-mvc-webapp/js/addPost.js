$(document).ready(function () {
    $(function () {
        $("#from").datepicker({
            defaultDate: "+1w",
            changeMonth: true,
            numberOfMonths: 1,
            onClose: function (selectedDate) {
                $("#to").datepicker("option", "minDate", selectedDate);
            }
        });
        $("#to").datepicker({
            defaultDate: "+1w",
            changeMonth: true,
            numberOfMonths: 1
//            onClose: function (selectedDate) {
//                $("#from").datepicker("option", "maxDate", selectedDate);
//            }
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

    $('#add-post-button').click(function () {
        $(event.preventDefault());

//        $.ajax({
//           type: 'POST',
//           url: 'https://www.google.com/recaptcha/api/siteverify',
//           data: 
//        }).success({
//            
//        });

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
            url: 'addPost',
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
