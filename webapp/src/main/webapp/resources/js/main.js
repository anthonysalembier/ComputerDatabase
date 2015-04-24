// Popover getting ready to use
$(document).ready(function(){
    
	// Display popover
    $('#language-picker').popover({ 
        html : true,
        content: function() {
        	return $('#language-picker-content').html();
        }
    });
    
    // Dismiss the popover when clicking away
    $('body').on('click', function (e) {
        $('[data-toggle="popover"]').each(function () {
            //the 'is' for buttons that trigger popups
            //the 'has' for icons within a button that triggers a popup
            if (!$(this).is(e.target)
            		&& $(this).has(e.target).length === 0
            		&& $('.popover').has(e.target).length === 0) {
                $(this).popover('hide');
            }
        });
    });
    
    // Change properties from the locale
    $.dateFormat;
    if ($.cookie("cdbLang") === 'en') {
    	$("#language-picker").css("background-image", "url(resources/img/flags/en.png)")
    	$.dateFormat = "yy-mm-dd";
//    	$("#datepicker").datepicker("option", $.datepicker.regional[""]);
//    	$("#datepicker").datepicker($.datepicker.regional[""]);
//    	$.datepicker.setDefaults($.datepicker.regional[""]);
    } else {
    	$("#language-picker").css("background-image", "url(resources/img/flags/fr.png)")
    	$.dateFormat = "dd-mm-yy";
//    	$("#datepicker").datepicker("option", $.datepicker.regional["fr"]);
//    	$("#datepicker").datepicker($.datepicker.regional["fr"]);
//    	$.datepicker.setDefaults($.datepicker.regional["fr"]);
    }
    
});

// Datepicker
$(function() {
	$("#datepicker-introduced").datepicker({
		minDate : new Date(1970, 1 - 1, 2),
		maxDate : new Date(2038, 1 - 1, 19),
		changeMonth: true,
		changeYear: true,
		showAnim : "fadeIn",
		duration: "normal",
		dateFormat : $.dateFormat
	});
	$("#datepicker-discontinued").datepicker({
		minDate : new Date(1970, 1 - 1, 2),
		maxDate : new Date(2038, 1 - 1, 19),
		changeMonth : true,
		changeYear : true,
		showAnim : "fadeIn",
		duration: "normal",
		dateFormat : $.dateFormat
	});
});

// Select language button
var changeLanguage = function(lang) {
	$("#lang").val(lang);
	$("#language-picker-form").submit();
};

// Retrieve a parameter from URL
function getUrlParameter(sParam) {
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++) {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam) {
            return sParameterName[1];
        }
    }
}


