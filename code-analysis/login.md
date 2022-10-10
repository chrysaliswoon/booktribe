---
cover: >-
  https://images.unsplash.com/photo-1481627834876-b7833e8f5570?crop=entropy&cs=tinysrgb&fm=jpg&ixid=MnwxOTcwMjR8MHwxfHNlYXJjaHw2fHxib29rfGVufDB8fHx8MTY2NDgxMTgxNw&ixlib=rb-1.2.1&q=80
coverY: 0
---

# Login

When users first enter the project, they will see the Login Page. If the user enters an incorrect email / password or if it does not exist, a prompt will appear, and they will not be able to access the app.

<figure><img src="../.gitbook/assets/login.png" alt=""><figcaption><p>BookTribe Login Page</p></figcaption></figure>

### Login Controller

In order to get the values from the Login form, we need to use `@RequestBody MultiValueMap<String, String> form`. After which, we will get the specific value using `form.getFirst`.

As the login form checks for several criterias, we will need to use conditional statements to check with the Redis Database if the user exists. If it doesn't, the user is unable to login.

```java
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

To store the login details of the user, `HttpSession` was used to store the details in a session. As HTTP is a stateless protocol, all requests and responses are independent. The server cannot distinguish between new visitors and returning visitors. But sometimes we may need to keep track of client's activity across multiple requests. This is achieved using Session Management.

<figure><img src="../.gitbook/assets/image (1).png" alt=""><figcaption><p><a href="https://www.studytonight.com/servlet/httpsession.php">How HttpSession Works</a></p></figcaption></figure>

1. On client's first request, the **Web Container** generates a unique session ID and gives it back to the client with response. This is a temporary session created by web container.
2. The client sends back the session ID with each request. Making it easier for the web container to identify where the request is coming from.
3. The **Web Container** uses this ID, finds the matching session with the ID and associates the session with the request.

### User Service

#### Login Status

To check if the person is able to log in successfully, we create a boolean value which will be returned back to us.&#x20;

If the login is successful, it will return true. But if the login is unsuccessful due to either incorrect password or there isn't such email found in the database, it will return false.

```java
    public boolean login(String email, String password) {

        //? Get Redis Value from the Database
        Optional<User> redisValue = userRepo.findUserByEmail(email);

        if (redisValue.isEmpty()) {
            return false;
        } 
        
        //? Check if password is correct
        String redisPassword = redisValue.get().getPassword();
        if (password.equals(redisPassword)) {
            return true;
        }

        return false;
    }
```

If the boolean returns false, a span text will appear saying that it is an incorrect email or password.

<figure><img src="../.gitbook/assets/Screenshot 2022-10-10 at 8.17.43 AM.png" alt=""><figcaption></figcaption></figure>

#### Check Profile

Similar to the Login status, we use a boolean to check if the user has uploaded a profile picture. If the user has uploaded a profile picture at registration, it will show the image upon login. But if there is no profile picture uploaded, a placeholder image will be given.

```java
    public boolean checkProfile(String email) {

        //? Get Redis Value from the Database
        Optional<User> redisValue = userRepo.findUserByEmail(email);

        if (redisValue.isEmpty()) {
            return false;
        } 


        //? Check if the profile exists in Redist
        String redis_profile = redisValue.get().getProfile();
        
        //? Check if user has uploaded a profile pic
        if (redis_profile.isBlank()) {
            return false;
        }

        return true;
    }
```

****

**Example of a user with no profile picture uploaded at the Registration stage:**

<figure><img src="../.gitbook/assets/Screenshot 2022-10-10 at 8.27.49 AM.png" alt=""><figcaption><p>Profile Page - No profile image</p></figcaption></figure>

####

**Example of a user with profile picture uploaded at the Registration stage:**

<figure><img src="../.gitbook/assets/Screenshot 2022-10-10 at 8.29.07 AM (1).png" alt=""><figcaption><p>Profile Page - With profile image</p></figcaption></figure>



#### User Details

```java
    public User userDetails(String email) {
        Optional<User> userOpt = userRepo.findUserByEmail(email);
        return userOpt.get();
    }
```

### User Repository

```java
    //? FIND SPECIFIC USER
    public Optional<User> findUserByEmail(String email) {
        HashOperations<String, String, String> hashOp = template.opsForHash();
        String redisName = hashOp.get(email, "name");
        String redisUserName = hashOp.get(email, "username");
        String redisEmail = hashOp.get(email, "email");
        String redisPassword = hashOp.get(email, "password");
        String redisProfile = hashOp.get(email, "profile");
        String redisBook = hashOp.get(email, "favourite");


        User user = new User(redisName, redisUserName, redisEmail, redisPassword, redisProfile, redisBook);
        
        if (null == redisEmail)
            return Optional.empty();
        return Optional.of(user);
    }
```
