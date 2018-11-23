/**
 * 
 */
var app=angular.module("app",['ngRoute','ngCookies'])

app.config(function($routeProvider){
	$routeProvider
	.when('/registration',{controller:'UserCtrl',templateUrl:'Views/Registration.html'})
	.when('/login',{controller:'UserCtrl',templateUrl:'Views/login.html'})
	.when('/home',{templateUrl:'Views/home.html'})
	.when('/addjob',{controller:'JobCtrl',templateUrl:'Views/jobform.html'})
	.when('/getalljobs',{controller:'JobCtrl',templateUrl:'Views/jobs.html'})//$scope.jobs
	.when('/addblog',{controller:'BlogCtrl',templateUrl:'Views/blogform.html'})
	
	.when('/blogsWaitingForApproval/:id',{controller:'BlogCtrl',templateUrl:'Views/blogsWaitingForApproval.html'})
	.when('/blogsApproved/:id',{controller:'BlogCtrl',templateUrl:'Views/blogsApproved.html'})
	.when('/getBlogApprovalForm/:id',{controller:'BlogInDetailCtrl',templateUrl:'Views/blogApprovalForm.html'})
	.when('/getBlogApproved/:id',{controller:'BlogInDetailCtrl',templateUrl:'Views/blogdetails.html'})
	.otherwise({templateUrl:'Views/home.html'})
})

app.run(function($cookieStore,$rootScope,UserService,$location){
	if($rootScope.user==undefined)
		$rootScope.user=$cookieStore.get('loggedInUser')//add user variable to the newly created $rootScope object
		
		$rootScope.logout=function(){//function is called from index.html
		  alert('logout function is called')
		  UserService.logout().then(function(response){
			  delete $rootScope.user
			  $cookieStore.remove('loggedInUser')
			  $location.path('/login')
		  },function(response){
			  delete $rootScope.user
			  $cookieStore.remove('loggedInUser')
			  if(response.status==401)//UNAUTHORIZED
				  $location.path('/login')
		  })
		}
	
			
		
})
