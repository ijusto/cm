# Lab #3: Local data management

## Readings & concepts 

  * Concepts for [Lesson 7.1(AsyncTask)](https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/unit-3-working-in-the-background/lesson-7-background-tasks/7-1-c-asynctask-and-asynctaskloader/7-1-c-asynctask-and-asynctaskloader.html), [Lesson 9: Preferences and settings and Lesson 10: Storing data with Room](https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/index.html) 
  * See also theDocumentation guide“[Save data in a local database using Room](https://developer.android.com/training/data-storage/room)”
  * Slides:[9.0, 9.1 (SharedPreferences)](https://drive.google.com/drive/folders/1eu-LXxiHocSktGYpG04PfE9Xmr_pBY5P),[7.1-AsyncTask](https://docs.google.com/presentation/d/1A7anDTS8NQCunbxoJnqJqkSHNFCvRIn9moE7Zww-gds/edit#slide=id.g116d7d9d49_3_13),  10.1-Room databases.
  * Suggested reading on [getting the user location](https://developer.android.com/training/location/) (you may be using it in your project)

The SharedPreferences mechanism is very practical to store simple key-value entries. Given the value can be any string, you may even use it to store JSON data structures.
But, for most cases, you need a more advanced support to store and update structured data. Android has built-in support for relational databases (sqlite). Working with relational data in a object-oriented language can be very tedious and error prone... usually, you will use some Object-to-relational mapping (ORM) framework. That is the role of Room, which provides a convenient, high-level API to store and retrieve local data.
There are several reasons to keep data locally, one of them being the possibility to keep “caches”, so the user can benefit fromthe app even if there is no internet connection.


## Lab
The use of the Room database is best understood with respect to the overall strategy to structure an Android application discussed in “[Guide to app architecture](https://developer.android.com/jetpack/docs/guide)”. We will look in more detail into this in next class; for now, an overall understanding of the “new” architecture will be sufficient to understand the role of Room.

In this lab, we will follow the exercise in [Android Developer Fundamentals](https://developer.android.com/courses/fundamentals-training/toc-v2).
  - [Code lab 10.1:](https://codelabs.developers.google.com/android-training/) Storing data with Room framework.
        * Task 1: Create the RoomWordsSample app
        * Task 2: Create the Word entity
        * Task 3: Create the DAO
        * Task 4: Use LiveData
        * Task 5: Add a Room database
        * Task 6: Create the Repository
        * Task 7: Create the ViewModel
        * Task 8: Add XML layouts for the UI
        * Task 9: Create an Adapter and adding the RecyclerView
        * Task 10: Populate the database
        * Task 11: Connect the UI with the data
        * Task 12: Create an Activity for adding words

Notes:
  → adding [Room dependency](https://developer.android.com/jetpack/androidx/releases/room) and [Lifecycle components dependency](https://developer.android.com/jetpack/androidx/releases/room) to the project
