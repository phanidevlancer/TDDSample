package com.phani.tddsample.presentation.userdetail

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.phani.tddsample.domain.model.Address
import com.phani.tddsample.domain.model.Company
import com.phani.tddsample.domain.model.User
import com.phani.tddsample.domain.usecase.GetUserDetailUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
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
 * Test class for UserDetailViewModel
 *
 * Testing Strategy:
 * 1. Test userId extraction from SavedStateHandle
 * 2. Test successful user detail loading
 * 3. Test error scenarios
 * 4. Test state transitions
 * 5. Test with invalid/missing userId
 *
 * Important Concepts:
 * - Mock SavedStateHandle to provide navigation arguments
 * - Test ViewModel initialization with different userId values
 * - Verify correct userId is passed to use case
 */
@OptIn(ExperimentalCoroutinesApi::class)
class UserDetailViewModelTest {

    // TODO: Declare dependencies
    // HINT: GetUserDetailUseCase (mock), SavedStateHandle (mock), and UserDetailViewModel (SUT)

    // TODO: Declare test dispatcher

    @BeforeEach
    fun setup() {
        // TODO: Set main dispatcher
        // TODO: Initialize mocks
    }

    @AfterEach
    fun tearDown() {
        // TODO: Reset main dispatcher
    }

    @Test
    fun `when userId is valid, should load user detail successfully`() = runTest {
        // TODO: Create test user
        val testUser = User(
            id = 1,
            name = "John Doe",
            email = "john@example.com",
            phone = "123-456",
            website = "john.com",
            company = Company("Tech Corp", "Innovation"),
            address = Address("123 Main St", "NY", "10001")
        )

        // TODO: Mock SavedStateHandle to return userId = "1"
        // HINT: val savedStateHandle = mockk<SavedStateHandle>()
        // HINT: every { savedStateHandle.get<String>("userId") } returns "1"

        // TODO: Mock use case to return user
        // HINT: coEvery { getUserDetailUseCase(1) } returns Result.success(testUser)

        // TODO: Create ViewModel with mocked SavedStateHandle
        // HINT: val viewModel = UserDetailViewModel(getUserDetailUseCase, savedStateHandle)

        // TODO: Test state flow emissions
        // HINT: Use turbine to verify Loading -> Success

        // TODO: Verify user detail in state
        // HINT: assertEquals(testUser, (state as UserDetailUiState.Success).user)

        // TODO: Verify use case was called with userId = 1
        // HINT: coVerify { getUserDetailUseCase(1) }
    }

    @Test
    fun `when use case returns error, state should be Error`() = runTest {
        // TODO: Mock SavedStateHandle with valid userId

        // TODO: Create test exception
        // HINT: val exception = Exception("User not found")

        // TODO: Mock use case to return failure

        // TODO: Create ViewModel

        // TODO: Test state emissions
        // HINT: Verify Loading -> Error transition

        // TODO: Verify error message
    }

    @Test
    fun `when userId is null, should not load user detail`() = runTest {
        // TODO: Mock SavedStateHandle to return null
        // HINT: every { savedStateHandle.get<String>("userId") } returns null

        // TODO: Create ViewModel

        // TODO: Verify state remains Loading (no API call made)
        // HINT: advanceUntilIdle()

        // TODO: Verify use case was never called
        // HINT: coVerify(exactly = 0) { getUserDetailUseCase(any()) }
    }

    @Test
    fun `when userId is invalid string, should not load user detail`() = runTest {
        // TODO: Mock SavedStateHandle to return invalid userId
        // HINT: every { savedStateHandle.get<String>("userId") } returns "invalid"

        // TODO: Create ViewModel

        // TODO: Verify state remains Loading

        // TODO: Verify use case was not called
    }

    @Test
    fun `should extract correct userId from SavedStateHandle`() = runTest {
        // TODO: Test with different userId values (1, 5, 100, etc.)
        val testUserId = 5
        val testUser = User(
            id = testUserId,
            name = "Test User",
            email = "test@example.com",
            phone = "555-555",
            website = "test.com",
            company = Company("Test Co", "Testing"),
            address = Address("Test St", "Test City", "12345")
        )

        // TODO: Mock SavedStateHandle with testUserId

        // TODO: Mock use case for this userId

        // TODO: Create ViewModel

        // TODO: Verify use case was called with exact userId
        // HINT: coVerify { getUserDetailUseCase(testUserId) }
    }

    @Test
    fun `state flow should emit correct sequence of states`() = runTest {
        // TODO: Setup test data
        val testUser = User(
            id = 1,
            name = "John Doe",
            email = "john@example.com",
            phone = "123-456",
            website = "john.com",
            company = Company("Tech Corp", "Innovation"),
            address = Address("123 Main St", "NY", "10001")
        )

        // TODO: Mock dependencies

        // TODO: Create ViewModel

        // TODO: Use Turbine to test all emissions
        // HINT: viewModel.uiState.test {
        //     1. awaitItem() should be Loading
        //     2. advanceUntilIdle()
        //     3. awaitItem() should be Success with testUser
        // }
    }
}
