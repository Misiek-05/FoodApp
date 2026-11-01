# KitchenHelper

KitchenHelper is an Android app for organizing groceries, keeping recipes, and checking food nutrition. It brings these everyday kitchen tools together in one place and stores user-created lists and recipes locally on the device.

## Features

- **Shopping list** - add products with a quantity and unit, combine quantities when the same product is added again, and remove items when they are no longer needed.
- **Recipes** - create recipes with a description, add ingredients and quantities, browse a recipe's ingredients, and transfer those ingredients to the shopping list.
- **Nutrition lookup** - look up calories, fat, protein, fiber, carbohydrates, and sugar for a single food in the bundled food dataset.
- **Nutrition calculator** - add food and a quantity in grams or milliliters to a calculator, see the combined nutrition values, and remove entries.

## Technology

- **Kotlin** for Android application code.
- **Android SDK** with AndroidX Activity and AppCompat.
- **Android Views and XML layouts** for the current screens, with **RecyclerView** for shopping list, recipe, and calculator entries.
- **SQLite**, accessed through `SQLiteOpenHelper`, for shopping list items, recipes, recipe ingredients, and calculator entries.
- **Bundled local nutrition dataset** in `app/src/main/res/raw/food`; nutrition lookup does not require a network request.
- **Gradle Kotlin DSL** and a Gradle version catalog for project configuration and dependency versions.
- **Material Components** are included among the UI dependencies. Jetpack Compose is configured in the project, although the app screens currently use XML layouts and Android Views.

The project also declares OkHttp and Kotlin serialization JSON dependencies; the current app source does not use them.

## Requirements

- Android Studio with Android SDK Platform 35 installed.
- JDK 11 or newer.
- Android 7.0 (API 24) or newer to run the app.

## Build and run

1. Clone or download this repository and open the project in Android Studio.
2. Allow Gradle to sync and install any requested Android SDK components.
3. Run the `app` configuration on an emulator or a connected Android device (API 24+).

To build a debug APK from the command line:

```bash
./gradlew assembleDebug
```

On Windows, use:

```bat
gradlew.bat assembleDebug
```

The APK is generated at `app/build/outputs/apk/debug/app-debug.apk`.

## Project structure

```text
app/src/main/java/com/example/kitchenhelper/
|-- DataBases/    SQLite helpers for app data
|-- Nutrition/    Nutrition lookup and calculator screens
|-- Recipes/      Recipe and ingredient screens
|-- ShoppingList/ Shopping list screens
`-- MainActivity.kt

app/src/main/res/
|-- layout/ XML screen and list-item layouts
|-- raw/    Bundled nutrition data
`-- values/ Strings, colors, and themes
```

## Notes

- Nutrition values are read from the bundled dataset. A food needs to match an entry in that dataset to be found.
- User data is stored in local SQLite databases; the app does not currently provide account sync or cloud storage.
- The manifest requests the Android `INTERNET` permission, but the current nutrition lookup uses the bundled dataset.
