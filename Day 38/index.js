angular.module("AppMod", ["ngRoute"])
	.controller("AppCtrl", ['$http', function($http) {
		var self = this;

		$http.get('http://localhost:8080/studentList')
			.then(function(resp){
				self.students = resp.data;
			},function(err) {

			});

	}])
	.config(['$routeProvider', function($routeProvider) {

		$routeProvider
		.when('/', {
			templateUrl: 'views/home.view.html'

		}).when('/student', {
			templateUrl: 'views/student.view.html',
			controller: 'AppCtrl',
			controllerAs: 'ctrl'

		}).when('/about', {
			templateUrl: 'views/about.view.html'

		})
		.otherwise({redirectTo: '/'});

	}]); // end config

$(document).ready(function() {
	$(document).on('click','.delete-button',function(){
            var sid = $(this).closest('tr').attr('id');
			console.log(sid);
            var conf = confirm("Delete student with ID: " + sid + "?");
            if(conf) {
              $.ajax({
                url: "http://localhost:8080/student/delete/" + sid
              }).then(function() {
                location.reload();
              })
            } <!-- end if -->
			});
			});
