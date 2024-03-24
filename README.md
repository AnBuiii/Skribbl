# Skribble 🖌️

**Skribbl** is a SIMPE word guessing game! Through this project, I want to demonstrate how we could use Kotlin Multiplatform technology to build an wonderful app!

## About
- This game got inspired from [Philipp Lackner](https://www.youtube.com/@PhilippLackner), you can look for original game [Doodle Kong](https://www.youtube.com/watch?v=C-DYZopXH68)
- The original game built with old Android technology, so I want it to be updated with modern technology ☺️
- Client and Server communicate through RESTful API and Websocket, you can look for my Skribbl-Server [here](https://github.com/AnBuiii/Skribbl-Server)

## The game 🎲
- It is a guessing word game!
- In each room, each row, there is a player chosen to be a draw player.
- That player have to draw on a canvas about some mistery word so that orther player can guess it.
- The score gained from each guessed is inversely proportional to the time needed to guess.

## Built with 🔨
- [Kotlin](https://kotlinlang.org/) - Official programming language for Android development.
- [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html) - Share code between different platform
- [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/) - Framework to share UI across multiple platform
- [Ktor](https://ktor.io/) - Ktor is a framework for building asynchronous server-side and client-side applications
- [Kotlin-Serialization](https://github.com/Kotlin/kotlinx.serialization) - Kotlin multiplatform / multi-format serialization
- [Voyager](https://github.com/adrielcafe/voyager) - A pragmatic navigation library for Jetpack Compose, Multiplatform supported
- [Settings](https://github.com/russhwolf/multiplatform-settings) - A Kotlin Multiplatform library for saving simple key-value data
- [Koin](https://insert-koin.io) - The pragmatic Kotlin & Kotlin Multiplatform Dependency Injection framework
- [Koin-compose](https://insert-koin.io/docs/quickstart/android-compose/) - Koin Inject in Compose code
- [KotlinX-Datetime](https://github.com/Kotlin/kotlinx-datetime) - KotlinX multiplatform date/time library

## Architecture
This project use [KMP-Wizard](https://kmp.jetbrains.com/) as template generator
This is a Kotlin Multiplatform project targeting Android, iOS.
Here are resouces about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)
* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.
* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

## Project Structure 🏭
    com.anbui.skribbl        # Root Package
    
    .
	├── composeApp                             # Project entry point
	│   ├── src
 	│   │   ├── androidMain                    # Android target module
	│   │   │   ├── di                         # DI for android module
	│   │   │   ├── platform                   # Android Platform actual implementation
	│   │   │ 
 	│   │   ├── iosMain                        # IOS target module
	│   │   │   ├── di                         # DI for IOS module
	│   │   │   ├── platform                   # IOS platform actual implementation
	│   │   │
 	│   │   ├── commonMain                     # common module for shared code, each code is NOT depend on platform, library or stay in common module of library
	│   │   │   ├── composeResource            # UI compose resources
 	│   │   │   │   
	│   │   │   ├── core                       # The core module look very similar to normal android module thanks to Compose Multiplatform 
 	│   │   │   │   ├── data                   # data module 
 	│   │   │   │   │   ├── local              # DI for android module
 	│   │   │   │   │   ├── remote             # DI for android module
 	│   │   │   │   │
 	│   │   │   │   ├── presentation           # UI 
 	│   │   │   │   │   ├── component          # Common UI component
 	│   │   │   │   │   ├── theme              # Comon theme system
 	│   │   │   │   │
 	│   │   │   │   ├── repostory              # Implement for domain repository
 	│   │   │   │   │
 	│   │   │   │   ├── utils                  # Some utilities 
 	│   │   │   │   
	│   │   │   ├── di                         # DI for common module
 	│   │   │   │   
	│   │   │   ├── domain                     # domain for common module
 	│   │   │   │   ├── model               
 	│   │   │   │   ├── repository     
 	│   │   │   │ 
	│   │   │   ├── feature                    # Seperate each screen or use case to feature
 	│   │   │   │   ├── createRoom      
 	│   │   │   │   ├── ...  
 	│   │   │   │  
	│   │   │   ├── main                       # Entry point for common module
 	│   │   │   │ 
	│   │   │   ├── platform                   # Expected Implementation
 	├── config                                 # Detekt configuation
	│   ├── detekt
 	├── iosApp                                 # Entry point for ios app

## TODO
- [ ] Implement game profile
- [ ] Drawing color
- [ ] Enhance UI
- [ ] Circle or Straight line predict?

## Lint
[Detekt](https://github.com/detekt/detekt) is Static code analysis for Kotlin

## Contribute 🪖
Any contributions are welcome! Here are some note 
- Open issue to report a bug and make your suggestion.
- Fork this repo and do your changes. Remember to follow project's code style.
- Open PR against `main` branch with nice description and you are good to go 😊.
Skribbl is an open source project developed by [me](https://github.com/AnBuiii). My purpose is to demonstrate my knowleadge in Android Development, Kotlin Multiplatform, Server Development and share with everyone who interested ☺️. Please do NOT use for commercial purposes.

## License

This application is released under Apache license 2.0 (see [LICENSE](LICENSE)).
Some of the used libraries are released under different licenses.

