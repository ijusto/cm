# Lab #1-Android application components

## Android learning resources
Our Android module will cover the basics. To further learn Android development, consider these selected resources:

  * Google offers an [extensive set of tutorial/learning resources](https://developer.android.com/courses), including complete courses. 
  * There are [self-paced learning tracks](https://developer.android.com/courses/kotlin-android-fundamentals/overview) for Kotlin (newer) and Java. Online courses are also available (“nano degree”) for [beginner](https://www.udacity.com/course/android-basics-nanodegree-by-google--nd803) and [intermediate](https://www.udacity.com/course/android-developer-nanodegree-by-google--nd801) levels.
  * If you enjoya book, [Meiers’ PA4](https://www.wiley.com/en-pt/Professional+Android%2C+4th+Edition-p-9781118949528) is a good option. 


## Preparing the developer environment

  * You need **Android Studio** and at least one emulator or real device for deployment. If needed, check the “[Development tools setup](https://uapt33090-my.sharepoint.com/personal/ico_ua_pt/_layouts/15/onedrive.aspx?id=%2Fpersonal%2Fico_ua_pt%2FDocuments%2FDETI-Classes%2FCM-201920-2S%2FeLearning-Android&originalPath=aHR0cHM6Ly91YXB0MzMwOTAtbXkuc2hhcmVwb2ludC5jb20vOmY6L2cvcGVyc29uYWwvaWNvX3VhX3B0L0VyN3JPUG9oX0psTmhQOGp4Um4zU0swQkJUNlczTGdYdFJXTFlkZmlKY2JxMkE_cnRpbWU9ZzZNVkZSdjUxMGc)” instructions.
  * Set up a **git repository** for CM. It will be requiredto submit the homework assignments;you may alsoinclude all the lab activities.


## Readings & concepts 
These references provide the “theoretical” support for the labs. Mostcontent is available from the Android Developer Fundamental (ADFun) and Android Developer Advanced (ADAdv) [courses](https://developer.android.com/courses/kotlin-android-fundamentals/overview).

  * Supporting concepts (readings):
   * ADFun [Lessons 1 & 2](https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/index.html)
   * [Meiers’ PA4](https://www.wiley.com/en-pt/Professional+Android%2C+4th+Edition-p-9781118949528): Chap. 2, 3 & 5.
  * [Slides](https://docs.google.com/presentation/d/1aVIE8p73jWMv-3_nvx9HufCK_kgqvwNpzFNSCTQXnZ0/edit) (adapted from Google’s Android Developer Fundamentals course)


##Lab

  1. [Code labs for “Lesson 2.x –Activities and intents”](https://developer.android.com/courses/fundamentals-training/toc-v2) → no fragments (empty template)
    Note: in this series, in thenew project creation wizard, select “use **legacy android.support** libraries”.
        * Task 1: Create the TwoActivities project
        * Task 2: Create and launch the second Activity
            Each new activity you add to your project has its own layout and Java files, separate from those of the main activity. They also have their own <activity> elements in the AndroidManifest.xml file. As with the main activity, new activity implementations that you create in Android Studio also extend from the AppCompatActivity class.
            Each activity in your app is only loosely connected with other activities. However, you can define an activity as a parent of another activity in the AndroidManifest.xml file. This parent-child relationship enables Android to add navigation hints such as left-facing arrows in the title bar for each activity.
            An activity communicates with other activities (in the same app and across different apps) with an intent. An Intent can be explicit or implicit:
                * An explicit intent is one in which you know the target of that intent; that is, you already know the fully qualified class name of that specific activity.
                * An implicit intent is one in which you do not have the name of the target component, but have a general action to perform.
            In this task you add a second activity to our app, with its own layout. You modify the AndroidManifest.xml file to define the main activity as the parent of the second activity. Then you modify the launchSecondActivity() method in MainActivity to include an intent that launches the second activity when you click the button.
        * Task 3: Send data from the main Activity to the second Activity
            In the last task, you added an explicit intent to MainActivity that launched SecondActivity. You can also use an intent to send data from one activity to another while launching it.
            Your intent object can pass data to the target activity in two ways: in the data field, or in the intent extras. The intent data is a URI indicating the specific data to be acted on. If the information you want to pass to an activity through an intent is not a URI, or you have more than one piece of information you want to send, you can put that additional information into the extras instead.
            The intent extras are key/value pairs in a [Bundle](https://developer.android.com/reference/android/os/Bundle.html). A Bundle is a collection of data, stored as key/value pairs. To pass information from one activity to another, you put keys and values into the intent extra Bundle from the sending activity, and then get them back out again in the receiving activity.
            In this task, you modify the explicit intent in MainActivity to include additional data (in this case, a user-entered string) in the intent extra Bundle. You then modify SecondActivity to get that data back out of the intent extra Bundle and display it on the screen.
        * Task 4: Return data back to the main Activity
  2. “[Build yourfirst app](https://codelabs.developers.google.com/codelabs/build-your-first-android-app/index.html#2)” (in Java) → modern approach, with fragments.
