# MadridActivitiesAndroid

## Preview
![alt text](https://imgur.com/a/mPuRb "Initial menu")
![alt text](https://imgur.com/a/QJr0i "Activities Activity")
![alt text](https://imgur.com/a/eO3MO "Activities Detail Activity")


## Configuration
Before running the app you should add a file called api_key_google_maps.xml to app/res/values directory, in order to have full access to maps funcionality.
The file should have this form:

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string name="GOOGLE_MAPS_API_KEY">YOUR_API_KEY_HERE</string>
</resources>
```


## To-Do´s

There´s a few features not implemented yet

* If there's no Internet connection a message will be shown to the user.

* If you tap on a pin in the map a callout will open with the logo +
shop name. Taping the callout takes us to the detail shop screen.

* Test: model, DAOs
