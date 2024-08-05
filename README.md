# News Application

This application serves as a comprehensive platform for news management, including features for user management, news articles, comments, and statistics. It is structured into three main components: the Android application, the backend service, and the database.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java Development Kit (JDK) 8 or newer
- Android Studio for the Android application development
- .NET Core 3.1 SDK for the backend service
- SQL Server for the database

### Installing

#### Android Application

1. Open Android Studio and import the project from the `application/` directory.
2. Configure the `gradle.properties` file according to your environment specifics. A sample configuration is provided in [application/gradle.properties](application/gradle.properties).
3. Build the project using Android Studio's build tools.

#### Backend Service

1. Navigate to the `backend/` directory.
2. Restore the dependencies by running `dotnet restore`.
3. Start the service by running `dotnet run`.

#### Database

1. Open SQL Server Management Studio (SSMS).
2. Execute the script found in [database/CreateDatabase.sql](database/CreateDatabase.sql) to create the database and its schema.
3. (Optional) Populate the database with sample data by executing [database/SampleData.sql](database/SampleData.sql).

## Running the tests

### Android Application

Run the automated tests for this system through Android Studio's integrated test runner.

### Backend Service

Execute the test suite using the `dotnet test` command in the `backend/` directory.

## Deployment

Add additional notes about how to deploy this on a live system.

## Built With

- [Java](https://kotlinlang.org/) - The language used for the Android application
- [.NET Core](https://dotnet.microsoft.com/download/dotnet-core/3.1) - The framework used for the backend service
- [SQL Server](https://www.microsoft.com/en-us/sql-server/sql-server-downloads) - Used for the database


