# Lab #5: Background tasks

## Readings & concepts 
Android offers different ways to run background tasks and the available support has change along the APIs. The [guide on background tasks](https://developer.android.com/training/best-background) offers some contextualization.
While you used to rely onIntentServicebase class to create Services that run in the background, that approach is now generally seen as inefficientwhen target recent API, and you canfavour better battery-savvy options, like [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager).

  - Supporting concepts (readings):
        * ADF [Lessons 7.3, 7.4](https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/index.html)
        * [Meiers’ PA4](https://www.wiley.com/en-pt/Professional+Android%2C+4th+Edition-p-9781118949528): Chap. 6, 11.
        * Developer.android.com’s [guide on background tasks](https://developer.android.com/training/best-background)
  - [Slides](https://drive.google.com/drive/folders/1eu-LXxiHocSktGYpG04PfE9Xmr_pBY5P) from Google’s Android Developer Fundamentals: [7.3](https://docs.google.com/presentation/d/1qF9Yeau7uHIP7_aOHWgPU_RnfxACZzGyAZIzcJWz0R0/edit#slide=id.g116d7d9d49_3_13), [7.4](https://docs.google.com/presentation/d/1SdawmBYLrDKcLxwtvB09B5ffK7Bd9cj-677qI5Unekg/edit#slide=id.g116d7d9d49_3_13), [8.3](https://docs.google.com/presentation/d/1jWZ10wsLZTPhTl2jcPpa-PpkIbfJAeoVKfUv-eQoSCk/edit#slide=id.g116d7d9d49_3_13)

WorkManager provides a convenient API on topof existing, but different, approaches.


## Lab
In this lab:

  1. Codelab: Android fundamentals [7.3: Broadcast receivers](Codelab: Android fundamentals 7.3: Broadcast receivers)

  2. Codelab: Android fundamentals [8.3 JobScheduler](https://developer.android.com/courses/fundamentals-training/toc-v2)

  3. Codelab: [WorkManager](https://codelabs.developers.google.com/codelabs/android-workmanager-java/index.html#0) (a more sophisticated job scheduler, with backward compatibility)

  4. Optional: Use a bound Service: sample from Vogel, [step 7 only](https://www.vogella.com/tutorials/AndroidServices/article.html#exercise_bindlocalservice).
        * Note: if you follow the examplecode, in thesecond LinearLayout (just before Button tag), the android:layout_height="match_parent"should be “wrap_content”
