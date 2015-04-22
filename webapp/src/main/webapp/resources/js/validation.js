// When the document is ready
$(document).ready(function () {
	
	$.validator.addMethod(
	        "regex",
	        function(value, element, regexp) {
	            var re = new RegExp(regexp);
	            return this.optional(element) || re.test(value);
	        },
	        "Invalid format."
	);
	
	$("#addForm").validate({
		onkeyup : false,
        onfocusout : function(element) { $(element).valid(); },
        errorElement : "div",
        errorPlacement : function(error, element) {
			if (element.attr("name") === "computerName") {
				error.appendTo("div#error-name");
			} else if (element.attr("name") === "introduced") {
				error.appendTo("div#error-introduced");
			} else if (element.attr("name") === "discontinued") {
				error.appendTo("div#error-discontinued");
			}
		},
		rules : {
			"computerName" : {
				required : true,
				minlength : 2,
				regex : "^[a-zA-Z]"
			}
		},
		
		messages : {
			"computerName" : {
				required : "The name is required.",
				minlength : "Name minimum length is 2.",
				regex : "Wrong name format."
			}
		}
	});
	
	$("#editForm").validate({
		onkeyup : false,
        onfocusout : function(element) { $(element).valid(); },
        errorElement : "div",
        errorPlacement : function(error, element) {
			if (element.attr("name") === "computerName") {
				error.appendTo("div#error-name");
			} else if (element.attr("name") === "introduced") {
				error.appendTo("div#error-introduced");
			} else if (element.attr("name") === "discontinued") {
				error.appendTo("div#error-discontinued");
			}
		}, 
		rules : {
			"computerName" : {
				required : true,
				minlength : 2,
				regex : "^[a-zA-Z]"
			}
		},
		
		messages : {
			"computerName" : {
				required : "The name is required.",
				minlength : "Name minimum length is 2.",
				regex : "Wrong name format."
			}
		}
	});
	
})