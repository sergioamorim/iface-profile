angular.module("iFace").directive("uiPhone", function($filter){
	return {
		require: "ngModel",
		link: function(scope, element, attrs, ctrl){
			var _formatPhone = function(phone){
				if(phone !== undefined){
					phone = phone.replace(/[^0-9]+/g, "");
					if(phone.length > 0){
						phone = "("+phone.substring(0);
					}
					if(phone.length > 3){
						phone = phone.substring(0,3)+") "+phone.substring(3);
					}
					if(phone.length > 9 && phone.length < 15){
						phone = phone.replace("-", "");
						phone = phone.substring(0,9)+"-"+phone.substring(9,14);
					}
					if(phone.length >= 15){
						phone = phone.replace("-", "");
						phone = phone.substring(0,10)+"-"+phone.substring(10,14);
					}
					return phone;
				}
			};
			
			element.bind("keyup", function(){
				ctrl.$setViewValue(_formatPhone(ctrl.$viewValue));
				ctrl.$render();
			});
			
			ctrl.$parsers.push(function(value){
				if(value.length === 14 || value.length === 15)
					return value;
			});
			
			ctrl.$formatters.push(function(value){
				return _formatPhone(value);
			});
		}
	};
});