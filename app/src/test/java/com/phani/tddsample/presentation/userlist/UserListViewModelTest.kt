package com.phani.tddsample.presentation.userlist

import app.cash.turbine.test
import com.phani.tddsample.domain.model.Address
import com.phani.tddsample.domain.model.Company
import com.phani.tddsample.domain.model.User
import com.phani.tddsample.domain.usecase.GetUsersUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Test class for UserListViewModel
 *
 * Testing Strategy:
 * 1. Test initial state is Loading
 * 2. Test successful data loading (state transitions)
 * 3. Test error scenarios
 * 4. Test retry functionality
 * 5. Test StateFlow emissions using Turbine
 *
 * Important Concepts:
 * - Use StandardTestDispatcher for coroutine testing
 * - Use Turbine for testing Flow emissions
 * - Test state transitions: Loading -> Success/Error
 */
@OptIn(ExperimentalCoroutinesApi::class)
class UserListViewModelTest {

    // TODO: Declare dependencies
    // HINT: GetUsersUseCase (mock) and UserListViewModel (SUT)

    // TODO: Declare test dispatcher
    // HINT: private val testDispatcher = StandardTestDispatcher()

    @BeforeEach
    fun setup() {
        // TODO: Set main dispatcher for testing
        // HINT: Dispatchers.setMain(testDispatcher)

        // TODO: Initialize mocks
    }

    @AfterEach
    fun tearDown() {
        // TODO: Reset main dispatcher
        // HINT: Dispatchers.resetMain()
    }

    @Test
    fun `initial state should be loading and then load users automatically`() = runTest {
        // TODO: Create test users list
        val testUsers = listOf(
            User(
                id = 1,
                name = "John Doe",
                email = "john@example.com",
                phone = "123-456",
                website = "john.com",
                company = Company("Tech Corp", "Innovation"),
                address = Address("123 Main St", "NY", "10001")
            )
        )

        // TODO: Mock use case to return success
        // HINT: coEvery { getUsersUseCase() } returns Result.success(testUsers)

        // TODO: Create ViewModel
        // HINT: val viewModel = UserListViewModel(getUsersUseCase)

        // TODO: Test state flow emissions using Turbine
        // HINT: viewModel.uiState.test {
        //     - First emission should be Loading
        //     - Call advanceUntilIdle() to process coroutines
        //     - Second emission should be Success with testUsers
        // }

        // TODO: Verify use case was called
    }

    @Test
    fun `when use case returns error, state should be Error with message`() = runTest {
        // TODO: Create test exception
        // HINT: val exception = Exception("Network error")

        // TODO: Mock use case to return failure

        // TODO: Create ViewModel

        // TODO: Test state emissions
        // HINT: Use turbine to verify Loading -> Error transition

        // TODO: Verify error message in state
        // HINT: assertTrue(state is UserListUiState.Error)
        // HINT: assertEquals("Network error", (state as UserListUiState.Error).message)
    }

    @Test
    fun `when loadUsers is called, should emit Loading then Success`() = runTest {
        // TODO: Create test data
        val testUsers = listOf(
            User(
                id = 1,
                name = "Jane Smith",
                email = "jane@example.com",
                phone = "789-012",
                website = "jane.com",
                company = Company("Design Inc", "Creativity"),
                address = Address("456 Oak Ave", "LA", "90001")
            )
        )

        // TODO: Mock use case

        // TODO: Create ViewModel (will call loadUsers in init)

        // TODO: Manually call loadUsers() again

        // TODO: Test state flow emissions
        // HINT: Should see Loading -> Success -> Loading -> Success

        // TODO: Verify use case was called twice
        // HINT: coVerify(exactly = 2) { getUsersUseCase() }
    }

    @Test
    fun `when use case returns empty list, state should be Success with empty list`() = runTest {
        // TODO: Mock use case to return empty list
        // HINT: coEvery { getUsersUseCase() } returns Result.success(emptyList())

        // TODO: Create ViewModel

        // TODO: Test state
        // HINT: Verify state is Success with empty list

        // TODO: Verify the users list is empty
        // HINT: assertTrue((state as UserListUiState.Success).users.isEmpty())
    }

    @Test
    fun `retry functionality should call use case again`() = runTest {
        // TODO: First mock to return error, then success
        // HINT: Use coEvery with andThen
        // coEvery { getUsersUseCase() } returns Result.failure(Exception("First error")) andThen Result.success(testUsers)

        // TODO: Create ViewModel (first call will fail)

        // TODO: Call loadUsers() to retry

        // TODO: Verify state is now Success

        // TODO: Verify use case was called twice
    }
}
