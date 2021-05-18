## HungryEats
Is an Android application that provides a platform for online delivery of food at your doorstep. Pandemic had increased the demand for food delivery service and many restaurants are now offering home delivery. 
Due to pandemic food delivery applications has proven to be more important for both customers as well as business owners. For customers it’s one of the easiest way to satisfy the food cravings while staying safe at home and for many business owners it helps the business to survive by providing online delivery option.


                           

## Demo Video
(https://www.youtube.com/watch?v=Z59qJvDN-Yk&ab_channel=PriyankaDevendran)


## HungryEats Icon


<div>
<img src='screenshots/icon.png' width = 100 height = 100>&nbsp; &nbsp;
</div>
         



## Overview Architecture diagram

![](screenshots/Adiagram.png)




## Functionalities implemented

![](screenshots/Funcblock.png)


## Details of the Functionality included:

### The application consists of the below:

### -> The LaunchActivity

The LaucnhActivity is the home page for HungryEats application and is the first screen that is loaded once the user launches the application, the user can click on order button to continue with the app.

### -> LoginAndRegisterActivity: 

The LoginAndRegisterActivity helps user to register the application if not already registered and login for registered users. The user details are stored in the google firebase to authorize and authenticate the users.


### ->	MainActivity:

The MainAcitvity has a tool bar with cart icon and a menu bar for the users to logout, also the back navigation is enabled for the users.

MainActivity has 3 fragments:

#### * Restaurant Fragment

To fetch the list of restaurants from the zipCode provided by the user. The backend API call excepts only latitude and longitude as parameters , therefore google API call is done to convert the user provided zipCode to latitude and longitude to display the list of Restaurants present. The user can view all the list of Restaurants and select them to view the restaurant details.

#### *	MenuFragment
 
The Menufragment is used to fetch the details of the restaurant that is selected. The backend API is used to fetch the menu lists by passing the selected restaurant id as the parameter. 
The user can view the restaurant details such as the ratings and also view the menu offered by the Restaurant and add any of the selected items to the cart.

#### * CartFragment

The CartFragment are stored using the google firebase, once the user selected the cart icon present on the toolbar the user can view all the list of items added in the cart. The user can remove any of the items added , can clear the entire cart or proceed to checkout to the place the order.


Android Architecture Diagram


<img src='screenshots/AndroidDiagram'




## Features Implemented
   
     
![](screenshots/FeaBlock2.png')


## Details of the features implemented
                              
-	The application is built using **Model-view-viewmodel**  architectural pattern as the overall flow depends on the data.

-	The application is built using **Activity and fragments**, using of fragment helped the application to be more light weight and simpler to handle.

-	The layouts were built using **Constraint layout and linear layout**, both the layouts helped to design the UI with ease.

-	**Recycler View** is used in Restaurant fragment, Menu fragment and Cart fragment enables the application to be more efficient in terms of time and space, as it recycles the existing structure instead of creating new ones.

-	**View Binding** is implemented to access the view objects as it is enables both type safety and null safety.

-	**LiveData** makes sure the application is up to date and prevents the application from crashing.

-	Application uses **retrofit** to easily connect to REST backend APIs to retrieve and upload the JSON data using GSON converter factory.

-	The application is enabled with the **back button** used to move backward from the previously visited screen by the user, enhancing the user experience. 

-	The user details are store in **firebase** Authentication and the cart details are stored in the firestore Database.


## App Screenshot

## Welcome
Is the home screen displayed for the users launching the app.
<div>
<img src='screenshots/welcome.png' width = 150 height = 300>&nbsp; &nbsp;
</div>
                                  
 ## Login/SignUp
Is the first page displayed for the users logging in.
<div>
<img src='screenshots/login.png' width = 150 height = 300>&nbsp; &nbsp;
</div>


## List of Restaurants
Will display the list of nearby restaurants from the pincode provided. 
<div>
<img src='screenshots/restaurant1.png' width = 150 height = 300>&nbsp; &nbsp;
</div>


## Menu List
Will display the list of menu for the restaurant selected.

<div>
<img src='screenshots/menu1.png' width = 150 height = 300>&nbsp; &nbsp;
</div>
                          
                                
## Cart List
Will display the list of cart items added by the user.

<div>
<img src='screenshots/cart.png' width = 150 height = 300>&nbsp; &nbsp;
</div>
                                
                                                             
## Screenshot of Firebase Configuration
                               
### For storing User details
                             
 ![](screenshots/Authentication.png)
 
 ### For storing cart details 
 ![](screenshots/items.png)
                               

