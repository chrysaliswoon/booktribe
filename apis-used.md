---
description: An overview of the APIs and methods used for the project
---

# APIs Used

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

## [Random Poem Generator](https://poetrydb.org/index.html)



## [Random Motivational Quote Generator](https://github.com/lukePeavey/quotable)

