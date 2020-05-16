# Lab #2 - Flexible UI

## Readings & concepts 
These references provide the “theoretical” support for the labs. 

  * Supporting concepts (readings):
   * Codepath’s Guide: [Creating and Using Fragments](https://guides.codepath.com/android/creating-and-using-fragments)
   * ADFun [Lessons 5.3, 4.5](https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/index.html) & ADAdv [Lesson 1: Fragments](https://google-developer-training.github.io/android-developer-advanced-course-concepts/index-book.html)
   * [Meiers’ PA4](https://www.wiley.com/en-pt/Professional+Android%2C+4th+Edition-p-9781118949528): Chap. 2, 3, 4& 5.
  * Slides (adapted from ADFun + ADAdv): [5.3-Adaptative layouts](https://docs.google.com/presentation/d/15VwOB_6BupSUmzryT0YypAtIKUMC9mH67cPVYvyKqNA/edit#slide=id.p); [4.5-Recycler view](https://docs.google.com/presentation/d/1tLLYBSGl9d8nHc_88007kTOZvXdSY0oqIRF3APIgm34/edit#slide=id.p); [1.1-Fragments](https://docs.google.com/presentation/d/1VrlgPyf2IvpEXSFIWXLsz0LqEUjxWu6akwTlRTWX6Ng/edit#slide=id.g2dde0bc238_3_0); [1.2 Fragments communication](https://docs.google.com/presentation/d/11zMbRezuCIJVmPtVtkRBeSBm_FmONv14pNoNIagaCNQ/edit#slide=id.g2f5deb3c7a_1_216).

## Lab
  1. **Code lab 4.5** ([RecyclerView](https://developer.android.com/courses/fundamentals-training/toc-v2#unit_2_user_experience)), from ADF course.
   Suggested adaptation to the codelab, taking into consideration the new the wizard creates the elements:
    @Step 3: in task 1.1, when you select the Basic Activity template, the wizard will include two fragments. You can remove the fragments ( fragment_first, fragment_second) and the layouts created for those fragments. Then, adapt the content_main to define the layout of the main activity.
    @Step 3: in task 1.3, instead of creating a new image resource, you can just pick an iconfrom the drop down list (for attribute srcCompat).
    @Step4: in task 2.1, be sure to add the androidx.recyclerview.widget.RecyclerView(and not the support library v7). You can easily do it by using the designer(instead of editing the xml).
        * Task 1: Create a new project and dataset
        * Task 2: Create a RecyclerView
            * Data to display: Use the mWordList.
            * A RecyclerView for the scrolling list that contains the list items.
            * Layout for one item of data. All list items look the same.
            * A layout manager. RecyclerView.LayoutManager handles the hierarchy and layout of View elements. RecyclerView requires an explicit layout manager to manage the arrangement of list items contained within it. This layout could be vertical, horizontal, or a grid. You will use a vertical LinearLayoutManager.
            * An adapter. RecyclerView.Adapter connects your data to the RecyclerView. It prepares the data in a RecyclerView.ViewHolder. You will create an adapter that inserts into and updates your generated words in your views.
            * A ViewHolder. Inside your adapter, you will create a ViewHolder that contains the View information for displaying one item from the item's layout.
        * Task 3: Make the list interactive
            * Looking at lists of items is interesting, but it's a lot more fun and useful if your user can interact with them. To see how the RecyclerView can respond to user input, you will attach a click handler to each item. When the item is tapped, the click handler is executed, and that item's text will change.
            * The list of items that a RecyclerView displays can also be modified dynamically—it doesn't have to be a static list. There are several ways to add additional behaviors. One is to use the floating action button (FAB). For example, in Gmail, the FAB is used to compose a new email. For this practical, you will generate a new word to insert into the list. For a more useful app, you would get data from your users. 

  2. Code lab for [Fragments](https://codelabs.developers.google.com/advanced-android-training/) (**code lab 01.1+ codelab 01.2**) from Android Developer Advanced
        * Task 1. Include a Fragment for the entire Activity lifecycle
            * In this task you modify a starter app to add a Fragment statically as a part of the layout of the Activity, which means that the Fragment is shown during the entire lifecycle of the Activity. This is a useful technique for consolidating a set of UI elements (such as radio buttons and text) and user interaction behavior that you can reuse in layouts for other activities.
        * Task 2. Add a Fragment to an Activity dynamically
            * In this task you learn how to add the same Fragment to an Activity dynamically. The Fragment is added only if the user performs an interaction in the Activity—in this case, tapping a button.
            * You will change the FragmentExample1 app to manage the Fragment using FragmentManager and FragmentTransaction statements that can add, remove, and replace a Fragment.

        * Task 1. Communicating with a fragment
            * Define a listener interface in the Fragment with a callback method to get the user's choice.
            * Implement the interface and callback in the host Activity to retrieve the user's choice.
            * Use the newInstance() factory method to provide the user's choice back to the Fragment when creating the next Fragment instance.
        * Task 2. Changing an app to a master/detail layout
            * This task demonstrates how you can use a Fragment to implement a two-pane master/detail layout for a horizontal tablet display. It also shows how to take code from an Activity and encapsulate it within a Fragment, thereby simplifying the Activity.

  3. Code **lab 5.3** ([adaptative layouts](https://developer.android.com/courses/fundamentals-training/toc-v2#unit_2_user_experience)), from ADF course. Suggesting: instead of implementing thecode lab, download the solution project and compare, step by step, the instructions with thesolution. The main objective here is to learn how android makes use of **alternative resources**. 
        * Task 1: Support landscape orientation
            * You may recall that when the user changes the orientation of the device, the Android framework destroys and recreates the current activity. The new orientation often has different layout requirements than the original one. For example, the MaterialMe app looks good in portrait mode, but does not make optimal use of the screen in landscape mode. With the larger width in landscape mode, the image in each list item overwhelms the text providing a poor user experience.
            * In this task, you create an alternative resource file that will change the appearance of the app when it is used in landscape orientation.
        * Task 2 : Support tablets
            * Although you have modified the app to look better in landscape mode, running it on a tablet with physically larger dimensions results in all the text appearing too small. Also when the device is in landscape orientation, the screen is not used efficiently; three columns would be more appropriate for a tablet-sized screen in landscape mode.
            * In this task, you add additional resource qualifiers to change the appearance of the app when used on tablets.
        * Task 3: Localize your app
            * A "locale" represents a specific geographic, political or cultural region of the world. Resource qualifiers can be used to provide alternate resources based on the users' locale. Just as for orientation and screen width, Android provides the ability to include separate resource files for different locales. In this step, you modify your strings.xml file to be a little more international.


From [CODEPATH](https://guides.codepath.com/android/creating-and-using-fragments):
“Within a fragment-heavy app, we need to remember to organize our code according to architectural best practices.
**Activities are navigation controllers** primarily responsible for:

  * Navigation to other activities through intents.
  * Presenting navigational components such as the [navigation drawer](https://guides.codepath.com/android/Fragment-Navigation-Drawer) or the [viewpager](https://guides.codepath.com/android/ViewPager-with-FragmentPagerAdapter).
  * Hiding and showing relevant fragments using the fragment manager.
  * Receiving data from intents and passing data between fragments.

**Fragments are content controllers** and contain most views, layouts, and event logic including:

  * Layouts and views displaying relevant app content.
  * Event handling logic associated with relevant views.
  * View state management logic such as visibility or error handling.
  * Triggering of network request through a client object.
  * Retrieval and storage of data from persistence through model objects.

To reiterate, in a fragment-based architecture, the **activities are for navigation** and the **fragments are for views and logic**.”

##Explore
The (new) Android JetPack selection of components includes support for a new [Navigation Component](https://developer.android.com/guide/navigation), which makes it easier to handle the navigation between fragments and activities.There is also a related [codelab available](https://codelabs.developers.google.com/codelabs/android-navigation/index.html?index=..%2F..index#0).
