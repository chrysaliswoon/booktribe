---
description: An overview of the APIs and methods used for the project
cover: >-
  https://images.unsplash.com/photo-1481627834876-b7833e8f5570?crop=entropy&cs=tinysrgb&fm=jpg&ixid=MnwxOTcwMjR8MHwxfHNlYXJjaHw2fHxib29rfGVufDB8fHx8MTY2NDgxMTgxNw&ixlib=rb-1.2.1&q=80
coverY: 0
---

# ⚙ APIs Used

## [Google Book API](apis-used.md#undefined)

Requests to the Books API for public data must be accompanied by an identifier, which can be an [API key](https://developers.google.com/console/help/generating-dev-keys) or an [access token](https://developers.google.com/accounts/docs/OAuth2).

To acquire an API key:

1. Open the [Credentials page](https://console.developers.google.com/apis/credentials) in the API Console.
2. This API supports two types of credentials. Create whichever credentials are appropriate for your project:
   *   **OAuth 2.0:** Whenever your application requests private user data, it must send an OAuth 2.0 token along with the request. Your application first sends a client ID and, possibly, a client secret to obtain a token. You can generate OAuth 2.0 credentials for web applications, service accounts, or installed applications.

       For more information, see the [OAuth 2.0 documentation](https://developers.google.com/identity/protocols/OAuth2).
   *   **API keys:** A request that does not provide an OAuth 2.0 token must send an API key. The key identifies your project and provides API access, quota, and reports.

       The API supports several types of restrictions on API keys. If the API key that you need doesn't already exist, then create an API key in the Console by clicking [**Create credentials** ](https://console.cloud.google.com/apis/credentials) **> API key**. You can restrict the key before using it in production by clicking **Restrict key** and selecting one of the **Restrictions**.

{% swagger method="get" path="" baseUrl="https://www.googleapis.com/books/v1/volumes?q=search+terms" summary="" %}
{% swagger-description %}
You can perform a volumes search by sending an HTTP `GET` request to the following URI:

This request has a single required parameter:



[`q`](https://developers.google.com/books/docs/v1/using#q) - Search for volumes that contain this text string. There are special keywords you can specify in the search terms to search in particular fields, such as:
{% endswagger-description %}

{% swagger-parameter in="query" name="q" required="true" %}

{% endswagger-parameter %}

{% swagger-response status="200: OK" description="If the request succeeds, the server responds with a 200 OK HTTP status code and the volume results:" %}
```javascript
200 OK

{
 "kind": "books#volumes",
 "items": [
  {
   "kind": "books#volume",
   "id": "_ojXNuzgHRcC",
   "etag": "OTD2tB19qn4",
   "selfLink": "https://www.googleapis.com/books/v1/volumes/_ojXNuzgHRcC",
   "volumeInfo": {
    "title": "Flowers",
    "authors": [
     "Vijaya Khisty Bodach"
    ],
   ...
  },
  {
   "kind": "books#volume",
   "id": "RJxWIQOvoZUC",
   "etag": "NsxMT6kCCVs",
   "selfLink": "https://www.googleapis.com/books/v1/volumes/RJxWIQOvoZUC",
   "volumeInfo": {
    "title": "Flowers",
    "authors": [
     "Gail Saunders-Smith"
    ],
    ...
  },
  {
   "kind": "books#volume",
   "id": "zaRoX10_UsMC",
   "etag": "pm1sLMgKfMA",
   "selfLink": "https://www.googleapis.com/books/v1/volumes/zaRoX10_UsMC",
   "volumeInfo": {
    "title": "Flowers",
    "authors": [
     "Paul McEvoy"
    ],
    ...
  },
  "totalItems": 3
}
```
{% endswagger-response %}
{% endswagger %}

### API Limit & Restrictions

By default, it is set to 100 requests per 100 seconds per user and can be adjusted to a maximum value of 1,000.&#x20;

But the number of requests to the API is restricted to a maximum of **10 requests per second per user**.

## [Random Poem Generator](https://poetrydb.org/index.html)

The API is written in Ruby and uses Sinatra to resolve API routes. The poetry data is stored in a MongoDB database. The Ruby code is provided here as Open Source. The PoetryDB database is not directly accessible, in order to preserve its integrity.

<figure><img src="https://github.com/thundercomb/poetrydb/raw/master/Architecture_Diagram.jpg" alt=""><figcaption><p>Poem API Database Architecture</p></figcaption></figure>

{% swagger method="get" path="" baseUrl="https://poetrydb.org/random" summary="" %}
{% swagger-description %}
The random count search field is always exact, and therefore the match type :abs has no effect



Random and poemcount cannot be used together, as both specify the number of poems to return\

{% endswagger-description %}

{% swagger-response status="200: OK" description="" %}
```javascript
[
  {
    "title": "Sonnet XLIV: Press'd by the Moon",
    "author": "Charlotte Smith",
    "lines": [
      "Press'd by the Moon, mute arbitress of tides,",
      "While the loud equinox its power combines,",
      "The sea no more its swelling surge confines,",
      "But o'er the shrinking land sublimely rides.",
      "The wild blast, rising from the Western cave,",
      "Drives the huge billows from their heaving bed;",
      "Tears from their grassy tombs the village dead,",
      "And breaks the silent sabbath of the grave!",
      "With shells and sea-weed mingled, on the shore",
      "Lo! their bones whiten in the frequent wave;",
      "But vain to them the winds and waters rave;",
      "They hear the warring elements no more:",
      "While I am doom'd—by life's long storm opprest,",
      "To gaze with envy on their gloomy rest."
    ],
    "linecount": "14"
  }
]
```
{% endswagger-response %}
{% endswagger %}

### API Limit & Restrictions

There does not seem to be any limit on the number of calls you can get from this API. Repeated attempts to call the API multiple times throughout the day did not result in a block.&#x20;

## [Random Motivational Quote Generator](https://github.com/lukePeavey/quotable)

Quotable is a free, open source quotations API. It was originally built as part of a [FreeCodeCamp](https://www.freecodecamp.org/) project.

{% swagger method="get" path="" baseUrl="https://api.quotable.io/random?tags=technology,famous-quotes" summary="" %}
{% swagger-description %}
This method returns a single random quote from the database.
{% endswagger-description %}

{% swagger-parameter in="query" name="maxLength" type="Int" %}
The maximum Length in characters (can be combined with minLength)
{% endswagger-parameter %}

{% swagger-parameter in="query" name="minLength" type="Int" %}
The minimum Length in characters (can be combined with maxLength)
{% endswagger-parameter %}

{% swagger-parameter in="query" name="tags" type="String" %}
Get a random quote with specific tag(s).&#x20;



This takes a list of one or more tag names, separated by a comma (meaning AND) or a pipe (meaning OR).&#x20;



A comma separated list will match quotes that have all of the given tags.&#x20;

\
While a pipe ( | ) separated list will match quotes that have any one of the provided tags.&#x20;
{% endswagger-parameter %}

{% swagger-parameter in="query" name="author" type="String" %}
Get a random quote by one or more authors.&#x20;



The value can be an author name or slug.&#x20;



To include quotes by multiple authors, provide a pipe-separated list of author names / slugs.
{% endswagger-parameter %}

{% swagger-parameter in="query" name="authorId" type="String" %}
deprecated



Same as author param, except it uses author \_id instead of slug
{% endswagger-parameter %}

{% swagger-response status="200: OK" description="" %}
```javascript
{
"_id": "WJ5c36Guag1",
"content": "I will give you a definition of a proud man: he is a man who has neither vanity nor wisdom one filled with hatreds cannot be vain, neither can he be wise.",
"author": "John Keats",
"tags": [
"wisdom"
],
"authorSlug": "john-keats",
"length": 154,
"dateAdded": "2020-01-31",
"dateModified": "2020-01-31"
}
```
{% endswagger-response %}
{% endswagger %}

### API Limit & Restrictions

There does not seem to be any limit on the number of calls you can get from this API. Repeated attempts to call the API multiple times throughout the day did not result in a block.&#x20;
