var app = angular.module("AppMod", ["ngRoute"])

	app.controller("AppCtrl", ['$http', '$routeParams', '$scope', function($http, $routeParams, $scope) {
		var self = this;
		//console.log($routeParams);
		self.id = $routeParams.studentId;
		self.fn = $routeParams.studentFn;
		self.ln = $routeParams.studentLn;
		self.sat = $routeParams.studentSat;
		self.gpa = $routeParams.studentGpa;

		$http.get('http://localhost:8080/student')
			.then(function(resp){
				self.students = resp.data;
			},function(err) {

			});

			if(self.id != undefined) {
			$http.get('http://localhost:8080/student/'+self.id)
				.then(function(resp){
					self.student = resp.data;
				},function(err) {

				});
			};

			if(self.id != undefined && self.fn != undefined) {
			$http.get('http://localhost:8080/updatestudent/'+self.id+ '/' + self.fn + '/' + self.ln + '/' + self.sat + '/' + self.gpa)
				.then(function(resp){
					self.student = resp.data;
				},function(err) {

				});
			};

	}]); // end controller


app.controller("DetailsController",['$scope', function($scope) {
	$scope.details = function() {
		console.log(1);
	}

}]);

app.controller("EditController",['$scope', function($scope) {
	$scope.edit = function() {
		$scope.fn = "dfgdfgdfg";
		$scope.ln = "sdfgdsfg";
		$scope.sat = 0;
		$scope.gpa = 0;
	}

}]);


	app.config(['$routeProvider', function($routeProvider) {

		$routeProvider
		.when('/', {
			templateUrl: 'views/home.view.html'

		}).when('/student', {
			templateUrl: 'views/student.view.html',
			controller: 'AppCtrl',
			controllerAs: 'ctrl'

		}).when('/student/:studentId', {
			templateUrl: 'views/detail.view.html',
			controller: 'AppCtrl',
			controllerAs: 'ctrl'

		}).when('/about', {
			templateUrl: 'views/about.view.html'

		}).when('/student/update/:studentId/:studentFn/:studentLn/:studentSat/:studentGpa', {
			templateUrl: 'views/submitted.view.html',
			controller: 'AppCtrl',
			controllerAs: 'ctrl'

		}).when('/student/edit/:studentId', {
			templateUrl: 'views/edit.view.html',
			controller: 'AppCtrl',
			controllerAs: 'ctrl'
		})

		.otherwise({redirectTo: '/'});

	}]); // end config
