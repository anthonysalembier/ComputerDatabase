$(document).ready(function(){
    
    // Change properties from the locale
    if ($.cookie("cdbLang") === 'en') {
    	$.dateFormat = "yy-mm-dd";
    	// Datepicker
    	$(function() {
    		$("#datepicker-introduced").datepicker({
    		    firstDay: 1,
    			minDate : new Date(1970, 1 - 1, 2),
    			maxDate : new Date(2038, 1 - 1, 19),
    			changeMonth: true,
    			changeYear: true,
    			showAnim : "fadeIn",
    			duration: "normal",
    			dateFormat : $.dateFormat
    		});
    		$("#datepicker-discontinued").datepicker({
    		    firstDay: 1,
    			minDate : new Date(1970, 1 - 1, 2),
    			maxDate : new Date(2038, 1 - 1, 19),
    			changeMonth : true,
    			changeYear : true,
    			showAnim : "fadeIn",
    			duration: "normal",
    			dateFormat : $.dateFormat
    		});
    	});
    } else {
    	$.dateFormat = "dd-mm-yy";
    	// Datepicker
    	$(function() {
    		$("#datepicker-introduced").datepicker({
    		    firstDay: 1,
    			minDate : new Date(1970, 1 - 1, 2),
    			maxDate : new Date(2038, 1 - 1, 19),
    			changeMonth: true,
    			changeYear: true,
    			showAnim : "fadeIn",
    			duration: "normal",
    			dateFormat : $.dateFormat,
    		    closeText: 'Fermer',
    		    prevText: 'Précédent',
    		    nextText: 'Suivant',
    		    currentText: 'Aujourd\'hui',
    		    monthNames: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Décembre'],
    		    monthNamesShort: ['Janv.', 'Févr.', 'Mars', 'Avril', 'Mai', 'Juin', 'Juil.', 'Août', 'Sept.', 'Oct.', 'Nov.', 'Déc.'],
    		    dayNames: ['Dimanche', 'Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi'],
    		    dayNamesShort: ['Dim.', 'Lun.', 'Mar.', 'Mer.', 'Jeu.', 'Ven.', 'Sam.'],
    		    dayNamesMin: ['D', 'L', 'M', 'M', 'J', 'V', 'S'],
    		    weekHeader: 'Sem.',
    		    dateFormat: 'yy-mm-dd'
    		});
    		$("#datepicker-discontinued").datepicker({
    		    firstDay: 1,
    			minDate : new Date(1970, 1 - 1, 2),
    			maxDate : new Date(2038, 1 - 1, 19),
    			changeMonth : true,
    			changeYear : true,
    			showAnim : "fadeIn",
    			duration: "normal",
    			dateFormat : $.dateFormat,
    		    closeText: 'Fermer',
    		    prevText: 'Précédent',
    		    nextText: 'Suivant',
    		    currentText: 'Aujourd\'hui',
    		    monthNames: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Décembre'],
    		    monthNamesShort: ['Janv.', 'Févr.', 'Mars', 'Avril', 'Mai', 'Juin', 'Juil.', 'Août', 'Sept.', 'Oct.', 'Nov.', 'Déc.'],
    		    dayNames: ['Dimanche', 'Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi'],
    		    dayNamesShort: ['Dim.', 'Lun.', 'Mar.', 'Mer.', 'Jeu.', 'Ven.', 'Sam.'],
    		    dayNamesMin: ['D', 'L', 'M', 'M', 'J', 'V', 'S'],
    		    weekHeader: 'Sem.',
    		    dateFormat: 'yy-mm-dd'
    		});
    	});
    }
    
});
