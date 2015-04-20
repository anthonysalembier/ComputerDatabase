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
    
    // Display the flag of current language
    if ($.cookie("cdbLang") === 'en') {
    	$("#language-picker").css("background-image", "url(resources/img/flags/en.png)")
    } else {
    	$("#language-picker").css("background-image", "url(resources/img/flags/fr.png)")
    }
    
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


