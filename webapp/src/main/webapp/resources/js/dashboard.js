//On load
$(function() {
    // Default: hide edit mode
    $(".editMode").hide();
    
    // Click on "selectall" box
    $("#selectall").click(function () {
        $('.cb').prop('checked', this.checked);
    });

    // Click on a checkbox
    $(".cb").click(function() {
        if ($(".cb").length == $(".cb:checked").length) {
            $("#selectall").prop("checked", true);
        } else {
            $("#selectall").prop("checked", false);
        }
        if ($(".cb:checked").length != 0) {
            $("#deleteSelected").enable();
        } else {
            $("#deleteSelected").disable();
        }
    });

});

// Tablesorter
$(document).ready(function() {
	$("#dashboard").tablesorter();
});

// Language picker
$(document).ready(function() {
	if (window.location.href.search("lang=fr") !== -1) {
		$("#lang-fr").attr("selected", "selected")
	} else if (window.location.href.search("lang=en") !== -1) {
		$("#lang-en").attr("selected", "selected")
	}
			
	
//    $('#polyglotLanguageSwitcher').polyglotLanguageSwitcher({
//		effect: 'fade',
//        testMode: true,
//        onChange: function(evt){
//            alert("The selected language is: ");
//        }
////        ,afterLoad: function(evt){
////            alert("The selected language has been loaded");
////        },
////        beforeOpen: function(evt){
////            alert("before open");
////        },
////        afterOpen: function(evt){
////            alert("after open");
////        },
////        beforeClose: function(evt){
////            alert("before close");
////        },
////        afterClose: function(evt){
////            alert("after close");
////        }
//	});
});


// Function setCheckboxValues
(function ( $ ) {

    $.fn.setCheckboxValues = function(formFieldName, checkboxFieldName) {

        var str = $('.' + checkboxFieldName + ':checked').map(function() {
            return this.value;
        }).get().join();
        
        $(this).attr('value',str);
        
        return this;
    };

}( jQuery ));

// Function toggleEditMode
(function ( $ ) {

    $.fn.toggleEditMode = function() {
        if($(".editMode").is(":visible")) {
            $(".editMode").hide();
            if ($.cookie("cdbLang") === 'en') {
            	$("#editComputer").text("Delete");
            } else {
            	$("#editComputer").text("Supprimer");
            }
        }
        else {
            $(".editMode").show();
            if ($.cookie("cdbLang") === 'en') {
            	$("#editComputer").text("View");
            } else {
            	$("#editComputer").text("Vue");
            }
        }
        return this;
    };

}( jQuery ));


// Function delete selected: Asks for confirmation to delete selected computers, then submits it to the deleteForm
(function ( $ ) {
    $.fn.deleteSelected = function() {
        if (confirm("Are you sure you want to delete the selected computers?")) { 
            $('#deleteForm input[name=selection]').setCheckboxValues('selection','cb');
            $('#deleteForm').submit();
        }
    };
}( jQuery ));



//Event handling
//Onkeydown
$(document).keydown(function(e) {

    switch (e.keyCode) {
        //DEL key
        case 46:
            if($(".editMode").is(":visible") && $(".cb:checked").length != 0) {
                $.fn.deleteSelected();
            }   
            break;
        //E key (CTRL+E will switch to edit mode)
        case 69:
            if(e.ctrlKey) {
                $.fn.toggleEditMode();
            }
            break;
    }
});

