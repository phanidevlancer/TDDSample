# Test Patterns Cheat Sheet

Quick reference for writing tests in this project.

## 🔧 Basic Setup

### Test Class Structure
```kotlin
@OptIn(ExperimentalCoroutinesApi::class)  // For ViewModel tests
class MyClassTest {

    // Mocks
    private lateinit var dependency: Dependency

    // System Under Test
    private lateinit var sut: MyClass

    // Test Dispatcher (for ViewModel tests)
    private val testDispatcher = StandardTestDispatcher()

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(testDispatcher)  // ViewModel tests only
        dependency = mockk()
        sut = MyClass(dependency)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()  // ViewModel tests only
    }
}
```

## 📦 MockK Patterns

### Creating Mocks
```kotlin
val mock = mockk<MyInterface>()
```

### Mocking Suspend Functions (with coEvery)
```kotlin
// Return success
coEvery { mock.getUsers() } returns Result.success(listOf(user1, user2))

// Return failure
coEvery { mock.getUsers() } returns Result.failure(Exception("Error"))

// Throw exception
coEvery { mock.getUsers() } throws Exception("Network error")

// Return different values on consecutive calls
coEvery { mock.getUsers() } returns Result.failure(exception) andThen Result.success(users)
```

### Mocking Regular Functions (with every)
```kotlin
// For SavedStateHandle
val savedStateHandle = mockk<SavedStateHandle>()
every { savedStateHandle.get<String>("userId") } returns "1"
```

### Verifying Calls
```kotlin
// Verify function was called
coVerify { mock.getUsers() }

// Verify exact number of calls
coVerify(exactly = 1) { mock.getUsers() }
coVerify(exactly = 2) { mock.getUsers() }

// Verify with specific parameters
coVerify { mock.getUserById(1) }

// Verify never called
coVerify(exactly = 0) { mock.getUsers() }
```

## 🌊 Flow Testing with Turbine

### Basic Pattern
```kotlin
viewModel.uiState.test {
    // Get first emission
    val firstState = awaitItem()
    assertTrue(firstState is LoadingState)

    // Process all pending coroutines
    advanceUntilIdle()

    // Get second emission
    val secondState = awaitItem()
    assertTrue(secondState is SuccessState)
    assertEquals(expectedData, (secondState as SuccessState).data)
}
```

### Testing State Transitions
```kotlin
viewModel.uiState.test {
    // 1. Initial state
    assertEquals(UserListUiState.Loading, awaitItem())

    // 2. Process coroutines
    advanceUntilIdle()

    // 3. Final state
    val finalState = awaitItem()
    assertTrue(finalState is UserListUiState.Success)
    assertEquals(testUsers, (finalState as UserListUiState.Success).users)
}
```

## ✅ Common Assertions

### Result Type
```kotlin
// Success
assertTrue(result.isSuccess)
assertEquals(expectedValue, result.getOrNull())

// Failure
assertTrue(result.isFailure)
assertEquals("Error message", result.exceptionOrNull()?.message)
```

### UI State
```kotlin
// Loading
assertTrue(state is UserListUiState.Loading)

// Success
assertTrue(state is UserListUiState.Success)
assertEquals(testUsers, (state as UserListUiState.Success).users)

// Error
assertTrue(state is UserListUiState.Error)
assertEquals("Network error", (state as UserListUiState.Error).message)
```

### Collections
```kotlin
assertTrue(list.isEmpty())
assertTrue(list.isNotEmpty())
assertEquals(3, list.size)
assertEquals(expectedList, actualList)
```

## 🧪 Complete Test Examples

### Use Case Test
```kotlin
@Test
fun `when repository returns success, use case should return success`() = runTest {
    // Arrange
    val testUsers = listOf(createTestUser())
    coEvery { repository.getUsers() } returns Result.success(testUsers)

    // Act
    val result = useCase()

    // Assert
    assertTrue(result.isSuccess)
    assertEquals(testUsers, result.getOrNull())
    coVerify(exactly = 1) { repository.getUsers() }
}
```

### Repository Test
```kotlin
@Test
fun `when api returns data, repository should map to domain model`() = runTest {
    // Arrange
    val dto = UserDto(id = 1, name = "John", ...)
    coEvery { apiService.getUsers() } returns listOf(dto)

    // Act
    val result = repository.getUsers()

    // Assert
    assertTrue(result.isSuccess)
    assertEquals(1, result.getOrNull()?.first()?.id)
    assertEquals("John", result.getOrNull()?.first()?.name)
}
```

### ViewModel Test
```kotlin
@Test
fun `initial state should be loading then load data`() = runTest {
    // Arrange
    val testUsers = listOf(createTestUser())
    coEvery { useCase() } returns Result.success(testUsers)

    // Act
    val viewModel = UserListViewModel(useCase)

    // Assert
    viewModel.uiState.test {
        assertEquals(UserListUiState.Loading, awaitItem())
        advanceUntilIdle()
        val successState = awaitItem()
        assertTrue(successState is UserListUiState.Success)
        assertEquals(testUsers, (successState as UserListUiState.Success).users)
    }

    coVerify { useCase() }
}
```

## 🎨 Test Data Creation

### Simple User
```kotlin
val testUser = User(
    id = 1,
    name = "John Doe",
    email = "john@example.com",
    phone = "123-456",
    website = "john.com",
    company = Company("Tech Corp", "Innovation"),
    address = Address("123 Main St", "NY", "10001")
)
```

### UserDto
```kotlin
val testUserDto = UserDto(
    id = 1,
    name = "John Doe",
    email = "john@example.com",
    phone = "123-456",
    website = "john.com",
    company = CompanyDto("Tech Corp", "Innovation"),
    address = AddressDto("123 Main St", "NY", "10001")
)
```

### Multiple Users
```kotlin
val testUsers = listOf(
    createUser(id = 1, name = "User 1"),
    createUser(id = 2, name = "User 2")
)
```

## 🚦 Test Naming Convention

Use descriptive names that explain the scenario:

```kotlin
// ✅ Good
@Test
fun `when repository returns error, use case should return failure`()

@Test
fun `when api throws exception, repository should return failure`()

@Test
fun `initial state should be loading and then load users`()

// ❌ Bad
@Test
fun testError()

@Test
fun test1()
```

## 💡 Tips

1. **Always use `runTest` for suspend functions**
   ```kotlin
   @Test
   fun testName() = runTest {
       // test code
   }
   ```

2. **Use `advanceUntilIdle()` when testing StateFlow in ViewModels**
   ```kotlin
   advanceUntilIdle()  // Processes all pending coroutines
   ```

3. **Verify both positive and negative scenarios**
   - Success cases
   - Error cases
   - Edge cases (null, empty, etc.)

4. **Mock dependencies, test behavior**
   - Mock external dependencies (repository, API)
   - Test the logic of your class
   - Verify interactions with dependencies

5. **One assertion focus per test**
   - Each test should verify one specific behavior
   - Use descriptive test names

## 🐛 Debugging Tests

### Test fails with timeout
```kotlin
// Add advanceUntilIdle() before assertions
advanceUntilIdle()
```

### Mock not returning expected value
```kotlin
// Check your coEvery/every setup
coEvery { mock.method() } returns expectedValue  // For suspend
every { mock.method() } returns expectedValue    // For regular
```

### Verification fails
```kotlin
// Check exact parameter matching
coVerify { mock.getUserById(1) }  // Must be exactly 1, not any Int
```

Happy Testing! 🎯
