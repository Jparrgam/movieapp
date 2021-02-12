# movieapp


## Tech stack & Open-source libraries
- Minimum SDK level 23
- Compile SDK Version 30
- Android build tools 7.0.0-alpha05
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- JetPack
    - Lifecycle - dispose of observing data when lifecycle state changes.
    - ViewModel - UI related data holder, lifecycle aware.
    - Room Persistence - construct a database using the abstract layer.
- Architecture
    - MVVM Architecture
    - Repository pattern
    - Mavericks framework
    - Use Cases
    - Dependency Injection (Hilt)
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIS.
- [Moshi](https://github.com/square/moshi/) - JSON parser.
- [Coli-kt](https://github.com/coil-kt/coil), - loading images.
- [Material-Components](https://github.com/material-components/material-components-android) - Material design components.
- [Android-Hilt](https://github.com/googlecodelabs/android-hilt) - DI
- [Mavericks](https://github.com/airbnb/mavericks) - State Framework Android
- [Epoxy](https://github.com/airbnb/epoxy) - Building complex screen.

Dependencies file
- [dependencies.gradle](dependencies.gradle)
