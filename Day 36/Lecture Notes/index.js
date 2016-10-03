angular.module("AppMod", [])
  .controller("AppCtrl", [function() {
      var self = this;
      self.about = "About AngularJS";
      self.test = "Testing";

      self.changeAbout = function(parm1) {
        self.about = "Changed it to this new String - To the right is our added parameter" + parm1;
      };

      //var self.localVar = 1; // This is not part of the model due to the var keyword on the left


      self.loopStudents = function() {
        for(var std of self.students) {
          console.log(std.id);
        }
      }



      self.students = [
        {id: 1, name: 'Student 1', sat: 1200, gpa: 1.5},
        {id: 2, name: 'Student 2', sat: 1300, gpa: 2.5},
        {id: 3, name: 'Student 3', sat: 1400, gpa: 3.5},
        {id: 4, name: 'Student 4', sat: 1500, gpa: 4.0}
      ];

      self.visstudents = [
        {id: 1, name: 'Student 1', sat: 1200, gpa: 1.5, vis: true},
        {id: 2, name: 'Student 2', sat: 1300, gpa: 2.5, vis: false},
        {id: 3, name: 'Student 3', sat: 1400, gpa: 3.5, vis: true},
        {id: 4, name: 'Student 4', sat: 1500, gpa: 4.0, vis: false}
      ];

    }])
