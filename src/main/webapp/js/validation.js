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
            error.appendTo("div#errors");
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