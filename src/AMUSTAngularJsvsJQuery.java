// http://code.tutsplus.com/tutorials/5-awesome-angularjs-features--net-25651
// https://www.airpair.com/angularjs/posts/jquery-angularjs-comparison-migration-walkthrough
public class AMUSTAngularJsvsJQuery {

}
/*
 •  AngularJs can do everything JQuery can does.
 •  Dependency management is effortless and intutitive.
 Dependency Management
 •  Binding dynamic data to your views is straightforward and powerful.
 Binding data
 •  AngularJS is a solid foundation for building testable web applications that scale.
 Testable
 Scale 
 
   =========================================
 ===========JQuery====================
 =========================================
 
  
  manipulating elements, (DOM manipulation)
  triggering and listening for events,
   selecting elements from the DOM, (selector)
   running animations and effects, 
   getting and setting form input values, 
   traversing the DOM, 
   using deferred promises to manage future events
   
   
 First it finds all the input elements on the page;
Then, it checks if it has a lowercase attribute called catid with the value we are looking for;
Next, it walks up the DOM tree to check that it is a child of the required subcatID;
Finally, it can return a list of elements that satisfy that criteria.
btn = document.getElementById('btn'),
btn.addEventListener('click',
btn.removeEventListener(
// jQuery
$('#btn').one('click', function() {
  $('#msg').append(' World');
});
  =========================================
 ===========AngularJS====================
 =========================================
 
 ===========Unit Testing====================
 =========== End-to-End Testing=============
 ===========Integration=====================
 ===========Template Data-Binding===========
 E.g., input textfield text auto combine with another text when typing
--- ----  jQuery
//look up the input element
var name = $('#name');

//look up the output element
var greeting = $('#greeting');

//listen for keyboard events
name.keyup(function() {

  //update the output element with the new input
  greeting.text('Hello ' + name.val());

});
-- ----  AngularJS
<div ng-app>
    <input type="text" ng-model="name" />
    <span>Hello {{name}}</span>
</div>
 =========== Dependency Management ===========
 // AngularJS
myModule.controller('MyController', function($scope, dep1, dep2) {
  // ...
  $scope.myMethod = function(arg1) {
    return dep1(arg1);
  };
  // ...
});
When MyController is created by AngularJS, the $scope and instances of dep1 and dep2 are automatically passed in. This eliminates the need for hard-coding dependencies with global variables or creating instances manually within the component, which greatly complicates and limits testability.
 
 
 
2.3 From jQuery Plugin to Angular Filter
 2.4 Full Migration
 2.5 Hybrid Migration


 
 * */
 