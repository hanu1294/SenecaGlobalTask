# SenecaGlobalTask
Weather App using Public APIs

This Weather app made using the OpenWeatherMap API. Used some background images and color codes.

Followed MVVM architecture
Application developed in Kotlin Language.
Used Kotlin Coroutines Library for asynchronous API calls
Used Retrofit for API calls integration
Used Dagger HILT dependency Injection for code reusability and loose coupling
Used common Resource Data parsing for SUCCESS/FAILURE/LOADING states for API calls
Used Data Binding Library for UI

=================================================================================================

Requirements

Android studio lastest version or above 4.0
JDK 8
Android SDK 33
Supports Minimum API Level 21
Material Components 1.8.0
=================================================================================================

Dependencies Used in Application
Dagger HILT -> For dependency Injection
Maps platform secrets gradle plugin -> For Weather API key Security purpose
Retrofit
Kotlin Coroutines
ViewModel
LiveData

=================================================================================================

Application Features Include:
1. Main screen with List of Cities from India.
2. Intentionally added No Data and Invalid API Locations in Cities List of Main screen.
3. Fetch and Show City Weather Information on Click of City Item in Main screen.
4. Handled No Data for Invalid city.
5. Handled API failure for Invalid API key.
6. Showing Loading Dialog for better user interaction while fetching data

=================================================================================================
