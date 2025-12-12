# TDD Sample Project - User List App

A sample Android app built with **MVVM Clean Architecture** to help you learn Test-Driven Development (TDD).

## 📱 Features

- **Screen 1**: List of users fetched from JSONPlaceholder API
- **Screen 2**: Detailed user information
- **Clean Architecture**: Separation of concerns across Domain, Data, and Presentation layers
- **Dependency Injection**: Using Hilt
- **Navigation**: Jetpack Compose Navigation
- **Coroutines**: For asynchronous operations

## 🏗️ Project Structure

```
app/
├── domain/                          # Business logic layer
│   ├── model/                      # Domain models (User, Company, Address)
│   ├── repository/                 # Repository interfaces
│   └── usecase/                    # Use cases (GetUsersUseCase, GetUserDetailUseCase)
│
├── data/                           # Data layer
│   ├── remote/
│   │   ├── api/                   # Retrofit API service
│   │   └── dto/                   # Data Transfer Objects + Mappers
│   └── repository/                # Repository implementations
│
├── presentation/                   # UI layer
│   ├── userlist/                  # User list feature
│   │   ├── UserListViewModel.kt
│   │   ├── UserListScreen.kt
│   │   └── UserListUiState.kt
│   └── userdetail/                # User detail feature
│       ├── UserDetailViewModel.kt
│       ├── UserDetailScreen.kt
│       └── UserDetailUiState.kt
│
├── di/                            # Dependency Injection modules
│   ├── NetworkModule.kt
│   └── RepositoryModule.kt
│
└── navigation/                     # Navigation setup
    ├── Route.kt
    └── NavGraph.kt
```

## 🧪 Test Structure

All test classes are located in `app/src/test/java/` with **method stubs and hints**:

### Domain Layer Tests
- `GetUsersUseCaseTest.kt` - Tests for fetching users list
- `GetUserDetailUseCaseTest.kt` - Tests for fetching single user

### Data Layer Tests
- `UserRepositoryImplTest.kt` - Tests for repository implementation and DTO mapping

### Presentation Layer Tests
- `UserListViewModelTest.kt` - Tests for user list screen ViewModel
- `UserDetailViewModelTest.kt` - Tests for user detail screen ViewModel

## 📚 Learning Path

### Step 1: Complete Use Case Tests
Start with domain layer as it has the simplest logic:

1. Open `GetUsersUseCaseTest.kt`
2. Read the hints in each `TODO` comment
3. Implement the test by filling in the TODOs
4. Run the test: Right-click on the test class → Run
5. Repeat for `GetUserDetailUseCaseTest.kt`

**Key Concepts:**
- Mocking with MockK: `mockk()`, `coEvery`, `coVerify`
- Coroutine testing: `runTest`
- Result handling: `Result.success()`, `Result.failure()`

### Step 2: Repository Tests
Move to the data layer:

1. Open `UserRepositoryImplTest.kt`
2. Learn about DTO to Domain mapping
3. Implement tests following TODOs

**Key Concepts:**
- Testing data transformation
- Exception handling
- API service mocking

### Step 3: ViewModel Tests
Finally, tackle the presentation layer:

1. Open `UserListViewModelTest.kt`
2. Learn about StateFlow testing with Turbine
3. Implement tests

**Key Concepts:**
- `StandardTestDispatcher` for coroutines
- Turbine for Flow testing
- State transitions
- `SavedStateHandle` mocking (for UserDetailViewModel)

## 🔧 Testing Tools Used

| Tool | Purpose |
|------|---------|
| **JUnit 5** | Testing framework |
| **MockK** | Mocking library for Kotlin |
| **Turbine** | Testing Kotlin Flows |
| **Coroutines Test** | Testing coroutines with `runTest` |

## 🚀 Running the App

1. Sync Gradle: File → Sync Project with Gradle Files
2. Run the app: Click the green play button or `Shift + F10`
3. The app will fetch users from: `https://jsonplaceholder.typicode.com/users`

## ✅ Running Tests

### Run All Tests
```bash
./gradlew test
```

### Run Single Test Class
```bash
./gradlew test --tests GetUsersUseCaseTest
```

### From Android Studio
- Right-click on test class/method → Run
- View results in the Run window

## 📖 Test Writing Tips

### 1. **AAA Pattern**
```kotlin
@Test
fun testName() = runTest {
    // Arrange - Set up test data and mocks
    val testData = createTestData()
    coEvery { mock.method() } returns testData

    // Act - Execute the code under test
    val result = systemUnderTest.doSomething()

    // Assert - Verify the results
    assertTrue(result.isSuccess)
    coVerify { mock.method() }
}
```

### 2. **MockK Basics**
```kotlin
// Create mock
val mock = mockk<UserRepository>()

// Mock suspend function
coEvery { mock.getUsers() } returns Result.success(users)

// Mock to throw exception
coEvery { mock.getUsers() } throws Exception("Error")

// Verify function was called
coVerify { mock.getUsers() }
coVerify(exactly = 2) { mock.getUsers() }
```

### 3. **Testing StateFlow with Turbine**
```kotlin
viewModel.uiState.test {
    // First emission
    val item1 = awaitItem()
    assertTrue(item1 is LoadingState)

    // Process coroutines
    advanceUntilIdle()

    // Second emission
    val item2 = awaitItem()
    assertTrue(item2 is SuccessState)
}
```

### 4. **Coroutine Testing Setup**
```kotlin
@OptIn(ExperimentalCoroutinesApi::class)
class MyViewModelTest {
    private val testDispatcher = StandardTestDispatcher()

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
```

## 🎯 Test Coverage Goals

Try to achieve:
- ✅ All success scenarios
- ✅ All error/failure scenarios
- ✅ Edge cases (empty lists, null values, etc.)
- ✅ Verify all interactions with dependencies

## 📝 Common Issues & Solutions

### Issue: Test fails with "Job has not completed yet"
**Solution:** Add `advanceUntilIdle()` to process all pending coroutines

### Issue: StateFlow doesn't emit expected values
**Solution:** Make sure you set up `StandardTestDispatcher` and called `advanceUntilIdle()`

### Issue: "No matching calls" when using `coVerify`
**Solution:** Check that your mock setup matches the actual call (parameter types, values)

## 🎓 Learning Resources

- [MockK Documentation](https://mockk.io/)
- [Kotlin Coroutines Testing](https://developer.android.com/kotlin/coroutines/test)
- [Turbine - Flow Testing](https://github.com/cashapp/turbine)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)

## 🏆 Challenge Yourself

After completing the test stubs:
1. Run tests and ensure they all pass ✅
2. Try breaking the implementation code to see tests fail 🔴
3. Add more test cases for edge scenarios 📈
4. Achieve 100% code coverage 🎯

Good luck with your TDD journey! 🚀
