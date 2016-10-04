angular.module("AppMod", [])
  .controller("AppCtrl", ['$scope','$http', function($scope, $http) {
      var self = this;

      $http.get("http://localhost:8080/studentList")
        .then(function (resp) {
          self.student = resp.data;
          console.log(self.student);
        });


      // self.students = [
      //   {"id":100,"firstname":"Eric","lastname":"Ephram","gpa":3.0,"sat":1200,"majorid":1,"vis":true},
      //   {"id":110,"firstname":"Greg","lastname":"Gould","gpa":2.5,"sat":1100,"majorid":null,"vis":true},
      //   {"id":120,"firstname":"Adam","lastname":"Ant","gpa":3.2,"sat":1300,"majorid":null,"vis":true},
      //   {"id":130,"firstname":"Howard","lastname":"Hess","gpa":3.7,"sat":1600,"majorid":4,"vis":true},
      //   {"id":140,"firstname":"Charles","lastname":"Caldwell","gpa":2.1,"sat":900,"majorid":null,"vis":true},
      //   {"id":150,"firstname":"James","lastname":"Joyce","gpa":2.5,"sat":1100,"majorid":null,"vis":true},
      //   {"id":160,"firstname":"Doug","lastname":"Dumas","gpa":3.1,"sat":1350,"majorid":2,"vis":true},
      //   {"id":170,"firstname":"Kevin","lastname":"Kraft","gpa":2.7,"sat":1000,"majorid":null,"vis":true},
      //   {"id":180,"firstname":"Frank","lastname":"Fountain","gpa":2.5,"sat":1000,"majorid":null,"vis":true},
      //   {"id":190,"firstname":"Brian","lastname":"Biggs","gpa":2.3,"sat":950,"majorid":null,"vis":true}
      // ];

       self.refreshClick = function() {
          location.reload();
      };

    //   self.refreshStudents = function() {
    //     for(var i of self.students) {
    //       i.vis = true;
    //       }
     //
    //  };

      self.submitClick = function() {
        var satScore = document.getElementById('sat_box').value

        for(var i of self.student) {

          if(i.sat < satScore) {
            i.vis = false;
          } else {
            i.vis = true;
          }
          }
        }



        self.refreshStudents = function() {
          for(var i of self.student) {
            i.toggle = true;
            console.log(i.toggle)
            }

        };


        $scope.toggleFilter = function() {
          this.toggle = !this.toggle
        }




    }]);
