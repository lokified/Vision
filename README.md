# Vision
Vision is an android app that business person can use to manage their business. One can add expenses incured in a particular month and insurance policies, loans and investments taken for the business.

[Download Apk](https://drive.google.com/file/d/1PC3P0inSWDCii4GUttJUKFBfNjrnXDFn/view?usp=drive_link)

#### The app utilizes firebase features.
* Firebase authentication - this includes login and signup with an email.
* Firebase firestore - all the data is stored in the database.
* Firebase Cloud Messaging - App uses push notification from the firebase sdk that can be used for advertisement and passing information about the app
* Firebase perfomance - one can monitor the perfomance of the app where it has traces attached to main functions of the app
* Firebase crashlytics - one can view crash logs of the app

#### Features
* User can create an account and login.
* User can create a new company which will have default empty data.
* User can add and edit their expenses for a particlar month.
* User can logout.
  
## Screenshots

### Auth
<div display="flex"> 
  <img src="https://github.com/lokified/Vision/assets/87479198/cb2729e4-1618-45e4-bc57-2b6166c90e4f" width=20% height=30% >
  <img src="https://github.com/lokified/Vision/assets/87479198/2fcdc08f-2aa8-4fe2-85de-06341833ec70" width=20% height=30% >
</div>

### Home
<div display="flex"> 
  <img src="https://github.com/lokified/Vision/assets/87479198/0ed581a6-30e7-43c5-a104-1e14d6707751" width=20% height=30% >
  <img src="https://github.com/lokified/Vision/assets/87479198/7a1725a7-e734-4a88-ac53-fa532fadb995" width=20% height=30% >
  <img src="https://github.com/lokified/Vision/assets/87479198/925bcc6f-3995-40f0-a410-e0470725b960" width=20% height=30% >
  <img src="https://github.com/lokified/Vision/assets/87479198/6dc775d5-40eb-4faa-861d-d01afd65e3e8" width=20% height=30% >
</div>


## Technologies Used
- Kotlin - App is built with the language.
- Firebase - for storing data and authentication.
- Jetpack compose - Ui uses compose
- Hilt - For dependency injection.
- Jetpack components;
  - navigation component - Navigating through different screens in the app.
- Coroutines - Used to make asynchronous calls.
- Coil - for loading images.
- Splash screen Api - For creating a splash screen on app starting.
- Datastore - for data persistence in the app
