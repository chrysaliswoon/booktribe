# BookTribe - bookshelf-app

Note: This is a updated and cleaner version of a previous one: https://github.com/chrysaliswoon/bookshelf-app 

This project is my submission for an assignment where we were tasked to design and develop an application of our choice using what we have learnt in our Server Side Foundation module.

| Fulfilled? | Project Requirements 
| ------------- |:-------------:
| :white_check_mark:| Handle HTTP POST & GET request
| :white_check_mark:| Include parameterized routes (@PathVariable)
| :white_check_mark:| Include both MVC (@Controller) & REST endpoints (@RestController)
| :white_check_mark:| POST must either consume either form-urlencoded or JSON payload 
| :white_check_mark:| Must support more than 1 user
| :white_check_mark:|Include a minimum of 3 views not including REST endpoints
| :white_check_mark:| Make HTTP request to external RESTFUl API 
| :white_check_mark:| REST endpoints must not be those that have been discussed or used in class or assessment 
| :white_check_mark:| Use Bootstrap in your HTML 
| :white_check_mark:|Redis database must be running in the 'cloud'. Can provision an instance from Redis Labs or any other cloud provider


# Breakdown of the Project

### For a detailed breakdown of the project, head over to: https://chrysalis-1.gitbook.io/java-bookshelf-app 



## Login Page

![Login Page](https://github.com/chrysaliswoon/booktribe/blob/master/src/main/resources/images/login.png)

In order to get the values from the Login form, we need to use ```@RequestBody MultiValueMap<String, String> form```. After which, we will get the specific value using ```form.getFirst```. 

As the login form checks for several criterias, we will need to use conditional statements to check with the Redis Database if the user exists. If it doesn't, the user is unable to login. 

To store the login details of the user, ```HttpSession``` was used to store the details in a session. As HTTP is a stateless protocol, all requests and responses are independent. The server cannot distinguish between new visitors and returning visitors. But sometimes we may need to keep track of client's activity across multiple requests. This is achieved using Session Management.


``` java
    //? LOGIN --> HOMEPAGE
    @PostMapping(path = "/login")
    public String postHomePage(Model model, @RequestBody MultiValueMap<String, String> form, HttpSession session) {
        String email = form.getFirst("email");
        String password = form.getFirst("password");

        //? Check with Redis Database
        Boolean loginStatus = userSvc.login(email, password);
        Boolean profileExists = userSvc.checkProfile(email);

        //? If it is wrong, then inform the user that the username and password is incorrect
        if (loginStatus == false) {
            model.addAttribute("loginStatus", loginStatus);

            String errorMessage = "Incorrect email or password";
            model.addAttribute("errorMessage", errorMessage);
            return "login";
        }
        
        //? If it is correct, store in the session and go to the homepage
        User userDetails = userSvc.userDetails(email);
        
        if (profileExists == false) {
            userDetails.setProfile("https://media.istockphoto.com/vectors/default-profile-picture-avatar-photo-placeholder-vector-illustration-vector-id1223671392?k=20&m=1223671392&s=170667a&w=0&h=kEAA35Eaz8k8A3qAGkuY8OZxpfvn9653gDjQwDHZGPE=");
        }
        
        session.setAttribute("userDetails", userDetails);
        model.addAttribute("userDetails", userDetails);
        return "home";
    }
```


## Registration Page

![Registration Page](https://github.com/chrysaliswoon/booktribe/blob/master/src/main/resources/images/register.png)

When a user creates an account, we can use RedisInsight to have a quick view of whether the data was saved in the Redis database running in the 'cloud'.

![RedisInsight](https://github.com/chrysaliswoon/booktribe/blob/master/src/main/resources/images/redisInsight.png)

Similar to the Login Form, we use a POST request to send the information over and store in the Redis Database. To send over all of the information at once, we use a User model to set the specific data to the one indicated by the form submitted. 


``` java
    @PostMapping(path = "/")
    public String postRegisterPage(Model model, @RequestBody MultiValueMap<String, String> form) {

        String name = form.getFirst("name");
        String username = form.getFirst("username");
        String email = form.getFirst("email");
        String password = form.getFirst("password");
        String profile = form.getFirst("profile");

        User user = new User(name, username, email, password, profile);

        //? Store data in Redis Database
        userSvc.createProfile(user);

        return "login";
    } 

```

## Welcome Page


## Profile Page


## Goals Page

## Tribe Page


## Inspire Page


## About Page


## Search Book Function


## Custom Error Pages



# Resources & References

API Used:
- [Google Books](https://developers.google.com/books/docs/v1/using)
- [Random Quote Generator](https://github.com/lukePeavey/quotable)

Styling:
- [Bootstrap](https://getbootstrap.com/)
- [Bootstrap Icons](https://icons.getbootstrap.com/)

Database:
- [Redis](https://redis.com/)

Special thanks to my study buddies and friends, Kai Kein, Weiyang, Delan, Nigel and Neal for being my coding buddies and also giving me loads of valuable coding advice throughout this whole process.