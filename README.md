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

The first page which users will see when they enter the app is the login page. If the user enters an incorrect email / password or if it does not exist, a prompt will appear, and they will not be able to access the app. 

![Login Page](https://github.com/chrysaliswoon/booktribe/blob/master/src/main/resources/images/login.png)

In order to get the values from the Login form, we need to use ```@RequestBody MultiValueMap<String, String> form```. After which, we will get the specific value using ```form.getFirst```. 

As the login form checks for several criterias, we will need to use conditional statements to check with the Redis Database if the user exists. If it doesn't, the user is unable to login. 

To store the login details of the user, ```HttpSession``` was used to store the details in a session. As HTTP is a stateless protocol, all requests and responses are independent. The server cannot distinguish between new visitors and returning visitors. But sometimes we may need to keep track of client's activity across multiple requests. This is achieved using Session Management.


``` java
    //? LOGIN --> HOMEPAGE - Controllers
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

If a new user would like to join the BookTribe, they will head over to the Registration page where they will fill up their particulars to create their own personalised booktribe profile and bookshelf.

![Registration Page](https://github.com/chrysaliswoon/booktribe/blob/master/src/main/resources/images/register.png)

When a user creates an account, we can use RedisInsight to have a quick view of whether the data was saved in the Redis database running in the 'cloud'. We can also use the CLI to check if it exists. 

![RedisInsight](https://github.com/chrysaliswoon/booktribe/blob/master/src/main/resources/images/redisInsight.png)

Similar to the Login Form, we use a POST request to send the information over and store in the Redis Database. To send over all of the information at once, we use a User model to set the specific data to the one indicated by the form submitted. 


``` java
    //? REGISTER PAGE - Controllers
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

Upon successful login, users will be welcomed by the system. They will then need to click on the book to head into the profile page.

![Welcome Page](https://github.com/chrysaliswoon/booktribe/blob/master/src/main/resources/images/welcome.png)

To detect which user it is, we use the ```HttpSession``` combined with the ```Model``` attribute for Thymeleaf to show the username. 

``` java
    //? WELCOME PAGE - Controllers
    @GetMapping("/home")
    public String getHomePage(Model model, HttpSession session) {
        User userDetails = (User) session.getAttribute("userDetails");
        String name = userDetails.getName();
        model.addAttribute("userDetails", userDetails);
        model.addAttribute("name", name);
        return "home";
    }
```

## Profile Page

After the user click on the book, their profile page will be loaded with their personal details and bookshelf.

![Profile Page](https://github.com/chrysaliswoon/booktribe/blob/master/src/main/resources/images/profile.png)

As the profile contains quite a number of items, we will need to store the bookIDs and then call it back again in this page using the ```HttpSession```. Based on the Book ID, it will call the [Google Books API](https://developers.google.com/books/docs/v1/using) and load the book relevant details.

``` java
    //? PROFILE PAGE - Controllers
    @GetMapping(path = "/profile")
    public String getProfilePage(Model model, HttpSession session) {
        User userDetails = (User) session.getAttribute("userDetails");
        String bookID = userDetails.getFavourite();
        List<Book> bookDetails = bookSvc.bookDetails(bookID);
        
        model.addAttribute("shelf", bookDetails);
        model.addAttribute("userDetails", userDetails);
        return "profile";
    }
```

When users click on the "Edit Profile" button, they will be directed to a form pre-filled with their existing information which they can then edit. Currently the changes made will only be reflected upon the next sign-in. In the next version update, this will be rectified and when users submit the edit profile button it will reflect the changes immediately.

![Edit Profile](https://github.com/chrysaliswoon/booktribe/blob/master/src/main/resources/images/editProfile.png)

``` java
    //? UPDATE PROFILE - Controllers
    @PostMapping(path = "/editUser") 
    public String editUser(Model model, HttpSession session) {

        User userDetails = (User) session.getAttribute("userDetails");
        model.addAttribute("userDetails", userDetails);
        return "editUser";
    }

    @PostMapping(path = "/update") 
    public String updateProfilePage(Model model, HttpSession session, @RequestBody MultiValueMap<String, String> form) {
        User userDetails = (User) session.getAttribute("userDetails");

        String name = form.getFirst("name");
        String password = form.getFirst("password");
        String profile = form.getFirst("profile");

        String email = userDetails.getEmail();

        userSvc.updateProfile(email, "name", name);
        userSvc.updateProfile(email, "password", password);
        userSvc.updateProfile(email, "profile", profile);

        model.addAttribute("userDetails", userDetails);
        return "profile";
    }
```

If the user wishes to delete their profile, they can click on the "Delete Profile" button. Upon clicking it, a [Bootstrap Toast Alert](https://getbootstrap.com/docs/4.3/components/toasts/) will pop-up warning the user that proceeding with the deletion will result in permanent removal of their data from the Redis Database.

![Delete Profile](https://github.com/chrysaliswoon/booktribe/blob/master/src/main/resources/images/deleteProfile.png)

``` java
    //? DELETE PROFILE - Controllers
    @PostMapping(path = "/deleteUser") 
    public String deleteProfilePage(Model model, HttpSession session) {
        User userDetails = (User) session.getAttribute("userDetails");
        String userEmail = userDetails.getEmail();
        userSvc.deleteProfile(userEmail);
        
        return "login";
    }
```

To enable the Bootstrap Toast pop-up, we need to initialise the Toast using a combination of Javascript and HTML. 

``` html
      <div class="mb-1 h5 px-2">
        <!-- button to initialize toast -->
        <button type="button" class="btn btn-outline-danger" id="toastbtn">Delete Profile</button>
        <!-- Toast -->
        <!-- Flexbox container for aligning the toasts -->
        <div aria-live="polite" aria-atomic="true" class="d-flex justify-content-center align-items-center">

          <!-- Then put toasts within -->
          <div id="toastNotice" class="toast" role="alert">
            <div class="toast-header">
              <i class="bi bi-exclamation-triangle-fill" style="color: red;"></i>

              <strong class="me-auto" style="color: red;">WARNING</strong>
              <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
            <div class="toast-body">
              <strong class="me-auto">DELETE ACCOUNT</strong>
              <p>Are you sure you want to delete your account?</p>
              <p>If you delete your account, you will permanently lose your profile and all data associated with it.</p>

              <form action="/deleteUser" method="POST">
                <div class="mt-2 pt-2 border-top lh-base">
                  <button type="submit" class="btn btn-danger">Delete Account</button>
                  <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="toast">Cancel</button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
```

The toast will trigger upon button click which they will get from the buton id "toastbtn", and show the warning information.

``` javascript
document.getElementById("toastbtn").onclick = function() {
  var myAlert =document.getElementById('toastNotice');//select id of toast
  var bsAlert = new bootstrap.Toast(myAlert);//inizialize it
  bsAlert.show();//show it
}
```

In order for Javascript to work, you will need to import the script and make sure the Javascript file is in the static folder under "resources"

``` javascript
  <script data-th-src="@{/js/profile.js}"></script>
```

## Goals Page

As of now, the Goals page is a new area which is not yet completed as of this update. However, upon completion, users will be able to write reviews for books in their own Bookshelf (which is auto-generated upon favouriting the book).

![Goals Page](https://github.com/chrysaliswoon/booktribe/blob/master/src/main/resources/images/goals.png)

To detect the books in the current user's bookshelf, we need get the information through the ```HttpSession``` and then use ```model.addAttribute``` to show the books using Thymeleaf. 

``` java
    // ? GOALS PAGE  - Controllers
    @GetMapping(path = "/goals")
    public String getGoalsPage(Model model,HttpSession session) {
        User userDetails = (User) session.getAttribute("userDetails");
        String bookID = userDetails.getFavourite();
        List<Book> bookDetails = bookSvc.bookDetails(bookID);
        
        model.addAttribute("shelf", bookDetails);
        model.addAttribute("userDetails", userDetails);
        return "goals";
    }
```

Currently this version is not ideal as it requires us to constantly call the [Google Books API](https://developers.google.com/books/docs/v1/using) and load the book relevant details.

## Tribe Page

As of now, the Goals page is a new area which is not yet completed as of this update. However, upon completion, users will be able to follow other Tribe Members who have the same interest as them, and there will be a community forum for them to chat about their favourite books and topics. 

![Tribe Page](https://github.com/chrysaliswoon/booktribe/blob/master/src/main/resources/images/tribe.png)

Similar to the previous page, in order to get the details of the current user plus the existing users in the Redis Database, we will need to use ```HttpSession``` to get the current user information.

To get all of the existing users, we will call the User Service which will get all of the users from the Redis Database.

``` java
    // ? TRIBE PAGE - Controllers
    @GetMapping(path = "/tribe")
    public String getBookTribe(Model model, HttpSession session) {
        userSvc.getUsers();

        User userDetails = (User) session.getAttribute("userDetails");
        model.addAttribute("users", userSvc.getUsers());
        model.addAttribute("userDetails", userDetails);
        return "tribe";
    }
```

``` java
    // ? TRIBE PAGE - Service
    public Set<String> getUsers() {
        Optional<Set<String>> findUsers = userRepo.findAllUsers();
        Set<String> payload = findUsers.get();

        return payload;
    }
```

``` java
    //? FIND ALL USERS - Repository
    public Optional<Set<String>> findAllUsers() {
        Set<String> allKeys = template.keys("*");
        return Optional.of(allKeys);
    }
```

## Inspire Page

When users go to the Inspiration section, a random quote and poem will be generated. User will also be able to listen to songs on Spotify through the [embedded Spotify iFrame player](https://developer.spotify.com/documentation/embeds/). 

The quote data is generated from the [Random Quote Generator](https://github.com/lukePeavey/quotable). As of this project development, this API is working and there is currently no limit to the number of API calls you can make to it as a free user.

![Inspire - Quote](https://github.com/chrysaliswoon/booktribe/blob/master/src/main/resources/images/inspireQuote.png)

The poem data is generated from the [Random Poem Generator](https://poetrydb.org/index.html). As of this project development, this API is working and there is currently no limit to the number of API calls you can make to it as a free user.

![Inspire - Poem](https://github.com/chrysaliswoon/booktribe/blob/master/src/main/resources/images/inspirePoem.png)

As both of them are on a single page, we will use a single controller to call the API through the Service.

``` java
    //? INSPIRE PAGE - Controller
    @GetMapping("/inspire")
    public String getQuotes(Model model, HttpSession session) {
        List<Quote> poem = quoteSvc.getPoem();
        List<Quote> quote = quoteSvc.getQuote();

        User userDetails = (User) session.getAttribute("userDetails");
        model.addAttribute("userDetails", userDetails);

        model.addAttribute("poem", poem);
        model.addAttribute("quote", quote);
        return "inspire";
    }
```

As the poem API stores the poem lines as an Array, we will need to use a For Loop to loop through the array and then generate it out line by line. 

``` java 
    //? Poem API - Service
        JsonArray lines = poem.getJsonArray("lines");
        
        List<String> poemContent = new ArrayList<String>();

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.getString(i);
            poemContent.add(line);
        }
        
        List<String> poemLines = new ArrayList<>();
        

        for (String content: poemContent) {
            poemLines.add(content);
        }
```


## About Page

This was the easiest portion of the whole project as it doesn't require me to call upon any API nor do any of the CRUD functions. The purpose of this page was the practise my aesthetics and also to have fun exploring the CSS and Bootstrap styling. 

![About Page](https://github.com/chrysaliswoon/booktribe/blob/master/src/main/resources/images/about.png)

However, as the whole app relies on being able to toggle back and forth between the User profile, we will need to include the ```HttpSession``` so we can get the current user details and use it for the navigation bar.

``` java
    //? About Page - Controller
    @GetMapping(path="/about")
    public String getAboutPage(Model model,HttpSession session) { 
        User userDetails = (User) session.getAttribute("userDetails");
        model.addAttribute("userDetails", userDetails);
        return "about";
    }
```

## Search Book Function

This was the trickiest portion in terms of getting data from the API as the [Google Books API](https://developers.google.com/books/docs/v1/using) requires me to drill down to the specific data. 

![Search Book Function](https://github.com/chrysaliswoon/booktribe/blob/master/src/main/resources/images/search.png)

After getting the relevant data, I then realised that not all of the books had all of the data. For instance, some books didn't have subtitles, but I was drilling down to search for that data, which causes a 500 error to appear as the program was trying to find a data which did not exist and just stops there. 

``` java
    //? Search ALL books - Controller
    @GetMapping(path = "/search")
    public String getBookResults(Model model, @RequestParam String book, HttpSession session) { 
        List<Book> bookResults = bookSvc.exploreBooks(book);
        User userDetails = (User) session.getAttribute("userDetails");
        model.addAttribute("userDetails", userDetails);
        model.addAttribute("book", book.toUpperCase());
        model.addAttribute("results", bookResults);

        return "explore";
    }

    //? Search SPECIFIC book by ID - Controller
    @GetMapping( path="/search/{id}")
    public String getBookById(Model model, @PathVariable String id, HttpSession session) {
        List<Book> bookDetails = bookSvc.bookDetails(id);
        User userDetails = (User) session.getAttribute("userDetails");
        model.addAttribute("userDetails", userDetails);
        model.addAttribute("details", bookDetails);
        return "book";
    }
```

To combat this, I included a conditional statement to check if that particular data exists. If the data doesn't exists, we will create an empty string.

``` java
    //? Check if the subtitle exists - Service
    String subtitle;
    if (!volInfo.containsKey("subtitle")) {
        subtitle = "no subtitle available";
    } else {
        subtitle = volInfo.getString("subtitle");
    }
```

## Custom Error Pages

![404 Error Page](https://github.com/chrysaliswoon/booktribe/blob/master/src/main/resources/images/404Error.png)


![500 Error Page](https://github.com/chrysaliswoon/booktribe/blob/master/src/main/resources/images/500Error.png)


# Resources & References

API Used:
- [Google Books](https://developers.google.com/books/docs/v1/using)
- [Random Quote Generator](https://github.com/lukePeavey/quotable)
- [Random Poem Generator](https://poetrydb.org/index.html)

Styling:
- [Bootstrap](https://getbootstrap.com/)
- [Bootstrap Icons](https://icons.getbootstrap.com/)

Database:
- [Redis](https://redis.com/)

Special thanks to my study buddies and friends, Kai Kein, Weiyang, Delan, Nigel and Neal for being my coding buddies and also giving me loads of valuable coding advice throughout this whole process.