# Lab #4: Mobile backend services

## Readings & concepts 
[Firebase](https://firebase.google.com) is the Google’s mobile backend platform (MBaaS -Mobile Backend as a Service) that [plays very well with Android](https://developer.android.com/distribute/best-practices/develop/build-with-firebase). It offers a convenient way to: 

  - Gain insights on user behaviour (with Analytics).
  - Set up user **authentication** (with Authentication).
  - Store datawith Cloud **Realtime** / [Firestore](https://firebase.google.com/docs/firestore) or blob data with **Cloud Storage**.
  - Send notifications to users (with **Cloud Messaging**).
  - Find out when and why your app may be crashing (with Crash Reporting).

The topics in bold are used in the first code lab.

[Firestore](https://firebase.google.com/docs/firestore) is a redesignedclouddatabase, that works with Firebase, and offers better scalability.


## Lab
In this lab:

  1. Follow the [Android Firebase Codelab](https://codelabs.developers.google.com/codelabs/firebase-android/#0) to develop a simple chat client (“Friendly Chat”) and get started with Firebase.
    Note 1: the keytool utility is available in the bindirectory of yourJava installation.You may confirm the Java location: File Menu > Project Structure > SDK location > JDK Location.
    Note 2: if you get errors related to AndroidX not being enabled, in gradle.properties, set android.useAndroidX=**true**

  2. Then, continue to the [step 7 of “Grow Friendly Chat”](https://codelabs.developers.google.com/codelabs/growfirebase-android/index.html#0) (Notifications); just step 7.

  3. [Read and Write data into cloud Firestore](https://codelabs.developers.google.com/codelabs/firestore-web/index.html#0) (you should, first, have used Firebase).
    Note: the project makes use of FirestoreAdapter, a RecyclerView adapter to get data drom Firestore.
