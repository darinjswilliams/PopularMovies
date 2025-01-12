# POPULAR MOVIE

# OverView

Most of us can relate to kicking back on the couch and enjoying a movie with friends and family. In this project, It allows users to discover the most popular movies playing.  

In this app the user will the user experience will include:

Present the user with a grid arrangement of movie posters upon launch.
Allow your user to change sort order via a setting:
The sort order can be by most popular or by highest-rated
Allow the user to tap on a movie poster and transition to a details screen with additional information such as:
original title
movie poster image thumbnail
A plot synopsis (called overview in the api)
user rating (called vote_average in the api)
release date

# Picasso Library

The application uses the Piscasso a powerful library that will handle image loading and caching on your behalf. If you prefer, you’re welcome to use an alternate library such as Glide.

Using Picasso To Fetch Images and Load Them Into Views

You can use Picasso to easily load album art thumbnails into your views using:
Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(imageView);
Picasso will handle loading the images on a background thread, image decompression and caching the images.

# Implementation

Clone Project to local directory: git clone with following command:

git clone https://github.com/darinjswilliams/PopularMovies.git

Open Project with Android Studio

#Movie Api - Will Required you to request a Unique Movie API Key.  A key is generated after you register with the themoviedb.org site.

# How to user API Key

To fetch popular movies, you will use the API from themoviedb.org.
If you don’t already have an account, you will need to create one in order to request an API Key.
In your request for a key, state that your usage will be for educational/non-commercial use. You will also need to provide some personal information to complete the request. Once you submit your request, you should receive your key via email shortly after.
In order to request popular movies you will want to request data from the /movie/popular and /movie/top_rated endpoints (documentation). An API Key is required.
Once you obtain your key, you append it to your HTTP request as a URL parameter like so:
http://api.themoviedb.org/3/movie/popular?api_key=[YOUR_API_KEY]
You will extract the movie id from this request. You will need this in subsequent requests.


# Location of Move API Key 

 First, create a file apikey.properties in your root directory with the values for different secret keys:

CONSUMER_KEY=XXXXXXXXXXX
CONSUMER_SECRET=XXXXXXX
To avoid these keys showing up in your repository, make sure to exclude the file from being checked in by adding to your .gitignore file:

apikey.properties
Next, add this section to read from this file in your app/build.gradle file. You'll also create compile-time options that will be generated from this file by using the buildConfigField definition:


def apikeyPropertiesFile = rootProject.file("apikey.properties")
def apikeyProperties = new Properties()
apikeyProperties.load(new FileInputStream(apikeyPropertiesFile))
 
android {

  defaultConfig {
     
     // should correspond to key/value pairs inside the file   
    buildConfigField("String", "CONSUMER_KEY", apikeyProperties['CONSUMER_KEY'])
    buildConfigField("String", "CONSUMER_SECRET", apikeyProperties['CONSUMER_SECRET'])
  }
}
You can now access these two fields anywhere within your source code with the BuildConfig object provided by Gradle:

// inside of any of your application's code
String consumerKey = BuildConfig.CONSUMER_KEY;
String consumerSecret = BuildConfig.CONSUMER_SECRET;

Now you have access to as many secret values as you need within your app, but will avoid checking in the actual values into your git repository. To read more about this approach, check out this article or this other article.
