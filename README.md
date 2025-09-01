# Kmp-Architecture Project Architecture

This document outlines the architecture of the Kmp-Architecture project. The project follows a modular, layered architecture designed for scalability and maintainability, leveraging Kotlin Multiplatform (KMP) to share code across different platforms like Android and iOS.

## Core Principles

*   **Clean Architecture:** The project aims to separate concerns by dividing the software into layers: Presentation (UI), Domain, and Data.
*   **Modularization:** Code is organized into modules, typically by feature or layer, to improve build times, encapsulation, and team scalability.
*   **Kotlin Multiplatform:** Common business logic, domain layers, and even data layers are written in `commonMain` to be shared across platforms. Platform-specific implementations are provided in their respective source sets (e.g., `androidMain`, `iosMain`).
*   **Dependency Injection:** Used to manage dependencies between different parts of the application, promoting loose coupling and testability.

## Module Structure

The project is broadly divided into several types of modules:

### 1. Core Modules (`core/*`)

These modules provide foundational functionalities and utilities shared across the entire application.

*   `core-data`: Contains core data-handling logic, data source interfaces, and repository interfaces that are not specific to any feature.
*   `core-di`: Sets up the core dependency injection graph and provides common dependencies.
*   `core-domain`: Includes core domain models, base use cases, and utility functions for the domain layer.
*   `core-ui`: (If present) Might contain shared UI components, theming, or base UI logic.

### 2. Data Modules (`data/*`)

These modules are responsible for data persistence and retrieval.

*   `data-database`: Implements database interactions (e.g., using SQLDelight).
*   `data-preferences`: Manages local data storage like user preferences (e.g., using DataStore).
*   `data-network`: (If present) Would handle API communications.

### 3. Feature Modules (`feature/*`)

Each feature of the application is encapsulated within its own set of modules. For example, an `auth` feature would have:

*   `feature/auth/auth-domain`: Contains the business logic specific to the authentication feature. This includes use cases (interactors), domain models, and repository interfaces for this feature. Resides primarily in `commonMain`.
*   `feature/auth/auth-data`: Implements the repository interfaces defined in `auth-domain`. It interacts with data sources (local or remote) to fetch and store authentication-related data. Includes platform-specific implementations if needed.
*   `feature/auth/auth-ui`: Contains the presentation logic and UI components for the authentication feature (e.g., Composable functions for Jetpack Compose, ViewModels/Presenters). This layer is platform-specific (`androidMain`, `iosMain`).
*   `feature/auth/auth-di`: Handles dependency injection specific to the authentication feature.

### 4. Application Modules (`app` or `composeApp`)

*   `composeApp`: This is likely the main application module that brings together all the features and core modules. It would contain the platform-specific entry points for Android, iOS, and other targets. It initializes DI, navigation, and sets up the overall application structure.

## Kotlin Multiplatform (KMP) Strategy

*   **`commonMain`:** The majority of the business logic (domain layer), data layer interfaces, and sometimes data layer implementations (if platform-agnostic, like network DTOs or shared database logic with SQLDelight) are placed here.
*   **`androidMain`, `iosMain`, etc.:** Platform-specific implementations are provided in these source sets. This includes UI, platform-specific APIs (e.g., file system access, device hardware), and DI wiring for platform-specific dependencies.
*   **`*Test` source sets (e.g., `commonTest`, `androidTest`, `iosTest`):** Unit and integration tests for their respective source sets.

## Dependencies and Build System

*   **Gradle:** Used as the build system.
*   **Gradle Kotlin DSL (`build.gradle.kts`):** Build scripts are written in Kotlin.
*   **Version Catalogs (e.g., `libs.versions.toml`):** Likely used for managing dependency versions centrally.

## Data Flow (Typical)

1.  **UI (Platform-Specific):** User interacts with the UI (e.g., a Composable in `auth-ui`).
2.  **ViewModel/Presenter (Platform-Specific):** UI event triggers a function in the ViewModel (e.g., in `auth-ui`).
3.  **Use Case (commonMain):** ViewModel calls a use case (e.g., in `auth-domain`).
4.  **Repository Interface (commonMain):** Use case interacts with a repository interface (e.g., `UserRepository` in `auth-domain`).
5.  **Repository Implementation (commonMain or Platform-Specific):** The DI framework provides the implementation of the repository (e.g., in `auth-data`).
6.  **Data Source (commonMain or Platform-Specific):** Repository implementation fetches/stores data from/to a local database, network API, or other data sources.
7.  Data flows back up the chain to the UI for display.

## Conceptual Module Dependency Scheme

This diagram illustrates the typical dependencies between the different layers and module types. Note that this is a conceptual representation; actual dependencies are defined in the `build.gradle.kts` files.

'''mermaid
graph TD;
App_composeApp["composeApp (Application)"]

    subgraph "Feature Modules (e.g., Auth)"
        direction LR
        F_UI["Feature UI (auth-ui)"]
        F_Domain["Feature Domain (auth-domain)"]
        F_Data["Feature Data (auth-data)"]
        F_DI["Feature DI (auth-di)"]
    end

    subgraph "Core Modules"
        direction LR
        C_UI["core-ui"]
        C_Domain["core-domain"]
        C_Data["core-data"]
        C_DI["core-di"]
    end

    subgraph "Data Sources"
        direction LR
        DS_DB["Database (data-database)"]
        DS_Prefs["Preferences (data-preferences)"]
        DS_Network["Network (data-network, if present)"]
    end

    App_composeApp --> F_UI;
    App_composeApp --> F_DI;
    App_composeApp --> C_DI;

    F_UI --> F_Domain;

F_UI --> C_UI; // Optional: if feature UI uses core UI components

    F_DI --> F_Domain;
    F_DI --> F_Data;
    F_DI --> C_DI; // For shared dependencies

    F_Domain --> C_Domain; // For base classes/models

    F_Data --> F_Domain; // Implements interfaces from Feature Domain
    F_Data --> C_Data;   // For base data logic/utils
    F_Data --> DS_DB;
    F_Data --> DS_Prefs;
    F_Data --> DS_Network;

'''
This architecture promotes a decoupled, testable, and maintainable codebase suitable for KMP development.
