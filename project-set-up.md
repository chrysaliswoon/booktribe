---
description: The initial Java project initialisation and set-up.
---

# 💻 Project Set-up

For this project, the database that was used is known as Redis **(REmote DIctionary Server)**.&#x20;

<figure><img src=".gitbook/assets/image.png" alt=""><figcaption><p>Overview of what is Redis</p></figcaption></figure>

#### **Install Redis**

Follow the steps given [here to install Redis Client/Server](https://www.techgeeknext.com/spring-boot/install-redis-on-windows) to test this application.

#### **Create Spring Boot application**

Create Spring Boot application from [Spring Initializr](https://start.spring.io/).

<figure><img src=".gitbook/assets/Screenshot 2022-10-06 at 8.52.52 AM.png" alt=""><figcaption></figcaption></figure>

Add these additional dependencies to the `pom.xml` file once the Spring Boot application is initialised.

```
	<dependency>
		<groupId>org.glassfish</groupId>
		<artifactId>jakarta.json</artifactId>
		<version>2.0.1</version>
	</dependency>
```

```
	<dependency>
		<groupId>redis.clients</groupId>
		<artifactId>jedis</artifactId>
		<version>3.9.0</version>
	</dependency>
```

#### Project Structure

![](<.gitbook/assets/Screenshot 2022-10-09 at 10.29.44 AM (1).png>)

![](<.gitbook/assets/Screenshot 2022-10-09 at 10.29.54 AM (1).png>)

#### ![](<.gitbook/assets/Screenshot 2022-10-09 at 10.30.06 AM.png>)

#### ![](<.gitbook/assets/Screenshot 2022-10-09 at 10.30.14 AM (1).png>)

#### Redis Configuration

<figure><img src=".gitbook/assets/Screenshot 2022-10-06 at 2.45.46 PM.png" alt=""><figcaption></figcaption></figure>
