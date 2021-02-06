# Food Fit

This is a personal project that demonstrates understanding of the <b>eMobilis Mobile Development Bootcamp</b> that I have just completed.
This intensive bootcamp took 4 hours a day for 20 weekdays, totalling to 80 hours, including a 5 day incubation session.

> ## Food Fit is an android app that gives you access to 5,000+ recipes each with nutrition information.

<p float="left">
    <img src="app_pictures\Food Fit banner Screen 1.png" width="40%" />
    <img src="app_pictures\Food Fit banner Screen 2.png" width="40%" />
</p>
The app renders data in JSON (from Spoonacular API) into card views placed in a recycler view.
<br/>
## Food Fit App Development Cycle
- [x] Strategy
- [x] Analysis and Planning
- [x] UI / UX Design
- [x] App Development
- [ ] <b>Testing</b>
- [ ] Deployment

## I created this app to demonstrate skills in:
* Firebase Authentication
* Working with third-party libraries
* Layout
* Mobile App Development Life cycle
<br/>

## Tech Stack
*   Language: Java
*   Authentication : Firebase : Email & Password
*   Libraries:
    * Sweet Alert Dialog - nice looking progress and alert dialogs
    * Gson - To and Fro Json to Java Conversion
    * Picasso - Draw images from web links
    * Volley - HTTP library for networking 
*   API : Spoonacular Food API

## About Food Fit
Users are authenticated via firebase, once logged in, the following actions can be performed:
*	Look for special recipes via key words such as vegetarian, gluten-free, etc.
*   Search for recipe suggestion for breakfast, lunch and dinner
*   Random Recipes

The JSON response is parsed into card views in a scrollable recycler view showing cards of recipes
*	Each card shows the name and picture of the recipe
*	 a new activity shows a little bit about the recipe: the ingredients(pictures) needed, rating and preparation time.
*	On clicking on a recipe in the recipe card, a new activity displays the actual recipe: ingredients(with pictures),  cooking steps and nutrition info.
 
The Home screen contains 3 bottom navigation buttons:
*	Home
*	Search Recipe
*	Account - signing out purposes and credits
<br/>

## Screenshots
<p float="left">
<img src="app_pictures\Splash Screen.png" width="150" />
<img src="app_pictures\What the app is all about screen 1.png" width="150" />
<img src="app_pictures\What the app is all about screen 2.png" width="150" />
<img src="app_pictures\What the app is all about screen 3.png" width="150" />
<img src="app_pictures\create account.png" width="150" />
<img src="app_pictures\Login Screen.png" width="150" />
<img src="app_pictures\reset password- fragment.png" width="150" />
<img src="app_pictures\Home Screen.png" width="150" />
<img src="app_pictures\account.png" width="150" />
<img src="app_pictures\Search Recipe.png" width="150" />
<img src="app_pictures\Recipe view.png" width="150" />
</p>


