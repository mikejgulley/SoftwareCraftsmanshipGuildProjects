$(document).ready(function () {
//    var elem = $('#pageTitle');
//    $('#text').limiter(15, elem);

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
            numberOfMonths: 1,
            onClose: function (selectedDate) {
                $("#from").datepicker("option", "maxDate", selectedDate);
            }
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

    $('#add-page-button').click(function () {
        event.preventDefault();
        $.ajax({
            type: "POST",
            url: 'addPage',
            data: JSON.stringify({
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

//$(function () {
//    $.fn.extend({
//        limiter: function (limit, elem) {
//            $(this).on("keyup focus", function () {
//                setCount(this, elem);
//            });
//            function setCount(src, elem) {
//                var chars = src.value.length;
//                if (chars > limit) {
//                    src.value = src.value.substr(0, limit);
//                    chars = limit;
//                }
//                elem.html(limit - chars);
//            }
//        }
//    });
//});