// When the document is ready
$(document).ready(function () {
	
	// Prepare a JSON with needed string to be translate
	var strings = {
		fr : {
			error : {
				name : {
					required : "Entrez un nom.",
					length : "Entrez un nom d'au moins 2 caractères.",
					format : "Entrez un nom correct (lettres et/ou chiffres)."
				},
				date : {
					format : "Entrez une date correcte (jj-mm-aaaa).",
					interval : {
						less : "Entrez une date inférieure ouégaleà 19-01-2038. ",
						greater : "Entrez une date supérieure ou égale à 02-01-1970."
					}
				}
			}
		},
		en : {
			error : {
				name : {
					required : "Enter a name.",
					length : "Enter a name with minimum 2 characters.",
					format : "Enter a correct name (containing letters and/or numbers)."
				},
				date : {
					format : "Enter a correct date (yyyy-mm-dd).",
					interval : {
						less : "Enter a value less than or equal to 2038-01-19.",
						greater : "Enter a value greater than or equal to 1970-01-02."
					}
				}
			}
		}
	};
	
	$("#datepicker-introduced").prop("readonly", true);
	$("#datepicker-discontinued").prop("readonly", true);
	
	$.validator.addMethod(
	        "regex",
	        function(value, element, regexp) {
	            var re = new RegExp(regexp);
	            return this.optional(element) || re.test(value);
	        },
	        "Invalid format."
	);
	
	$("#addForm").validate({
		onkeyup : function(element) { $(element).valid(); },
        onfocusout : function(element) { $(element).valid(); },
        errorElement : "div",
        errorPlacement : function(error, element) {
			if (element.attr("name") === "name") {
				error.appendTo("div#error-name");
			} else if (element.attr("name") === "introduced") {
				error.appendTo("div#error-introduced");
			} else if (element.attr("name") === "discontinued") {
				error.appendTo("div#error-discontinued");
			}
		},
		rules : {
			"name" : {
				required : true,
				minlength : 2,
				regex : "^[a-zA-Z]"
			},
			"introduced" : {
				required : false
			},
			"discontinued" : {
				required : false
			}
		},
		
		messages : {
			"name" : {
				required : function() {
					if ($.cookie("cdbLang") === 'en') {
						return strings.en.error.name.required;
					} else {
						return strings.fr.error.name.required;
					}
				},
				minlength : function() {
					if ($.cookie("cdbLang") === 'en') {
						return strings.en.error.name.length;
					} else {
						return strings.fr.error.name.length;
					}
				},
				regex : function() {
					if ($.cookie("cdbLang") === 'en') {
						return strings.en.error.name.length;
					} else {
						return strings.fr.error.name.length;
					}
				}
			}
		}
	});
	
	$("#editForm").validate({
		onkeyup : false,
        onfocusout : function(element) { $(element).valid(); },
        errorElement : "div",
        errorPlacement : function(error, element) {
			if (element.attr("name") === "name") {
				error.appendTo("div#error-name");
			} else if (element.attr("name") === "introduced") {
				error.appendTo("div#error-introduced");
			} else if (element.attr("name") === "discontinued") {
				error.appendTo("div#error-discontinued");
			}
		}, 
		rules : {
			"name" : {
				required : true,
				minlength : 2,
				regex : "^[a-zA-Z]"
			},
			"introduced" : {
				required : false
			},
			"discontinued" : {
				required : false
			}
		},
		
		messages : {
			"name" : {
				required : function() {
					if ($.cookie("cdbLang") === 'en') {
						return strings.en.error.name.required;
					} else {
						return strings.fr.error.name.required;
					}
				},
				minlength : function() {
					if ($.cookie("cdbLang") === 'en') {
						return strings.en.error.name.length;
					} else {
						return strings.fr.error.name.length;
					}
				},
				regex : function() {
					if ($.cookie("cdbLang") === 'en') {
						return strings.en.error.name.length;
					} else {
						return strings.fr.error.name.length;
					}
				}
			}
		}
	});
	
})