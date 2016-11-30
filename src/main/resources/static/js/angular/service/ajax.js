app.service('ajax', function($http) {
    this.getUsers = function() {
    	return $http.get("/users/get/all");
    }
});