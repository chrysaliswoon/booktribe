# APIs Used

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
