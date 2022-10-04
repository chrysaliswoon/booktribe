# Reflections

During the course of the project, there were a couple of takeaways and thoughts which might help you when you are embarking on your own project**.**

<figure><img src="https://images.unsplash.com/photo-1518241353330-0f7941c2d9b5?crop=entropy&#x26;cs=tinysrgb&#x26;fm=jpg&#x26;ixid=MnwxOTcwMjR8MHwxfHNlYXJjaHw3fHxyZWZsZWN0aW9ufGVufDB8fHx8MTY2NDg1OTk1Mw&#x26;ixlib=rb-1.2.1&#x26;q=80" alt=""><figcaption></figcaption></figure>

**It helps to work backwards and identify what type of data you will be storing as that will determine which Redis data type to use.**&#x20;

* In my case, I had used Redis ValueOperations to store my data. Halfway through the project, I realised that the data type wasn't enough to do what I wanted to do.&#x20;
* Upon further research, I found out that there was RedisJSON which allows us to store, update and retrieve JSON values in the Redis Database which would have suited my use case better.&#x20;

{% embed url="https://www.youtube.com/watch?v=V0wmD_y03iM" %}
Explanation of RedisJSON
{% endembed %}

**Try not to focus and fixate too much on the design and visual aspects especially at the initial project stage. Make it work first before working on the aesthetics.**

* As someone who is very visual and artistic, there is a tendency for me to focus on the aesthetics of my project, which then takes away time for me to do up the logic.&#x20;
* To ensure that I don't spend too much time working on Bootstrap and CSS, I time-box myself and only allow myself x amount of time or days to work on the visual aspect.&#x20;

#### **Be ok with the fact that your project will never be completed**

* There will always be features or functions that you will want to implement in your project as you start creating it.&#x20;
* In my case, I started to add other areas such as the Inspiration area and the Community area, but with the time-frame that was given, I had to focus and prioritise on what I could do and complete to show a MVP of my project.
