package com.phani.tddsample.domain.usecase

import com.phani.tddsample.domain.model.Address
import com.phani.tddsample.domain.model.Company
import com.phani.tddsample.domain.model.User
import com.phani.tddsample.domain.repository.UserRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Test class for GetUserDetailUseCase
 *
 * Testing Strategy:
 * 1. Test successful user retrieval
 * 2. Test error scenarios
 * 3. Verify correct userId is passed to repository
 */
class GetUserDetailUseCaseTest {

    // TODO: Declare dependencies
    // HINT: UserRepository (mock) and GetUserDetailUseCase (SUT)

    @BeforeEach
    fun setup() {
        // TODO: Initialize mocks and use case
    }

    @Test
    fun `when repository returns user successfully, use case should return success with user`() = runTest {
        // TODO: Create test data
        // HINT: Create a single User object with id = 1
        val testUser = User(
            id = 1,
            name = "John Doe",
            email = "john@example.com",
            phone = "123-456",
            website = "john.com",
            company = Company("Tech Corp", "Innovation"),
            address = Address("123 Main St", "NY", "10001")
        )

        // TODO: Mock repository to return user for userId = 1
        // HINT: coEvery { userRepository.getUserById(1) } returns Result.success(testUser)

        // TODO: Call the use case with userId = 1
        // HINT: val result = getUserDetailUseCase(1)

        // TODO: Verify result is success

        // TODO: Verify the returned user matches test data

        // TODO: Verify repository was called with correct userId
        // HINT: coVerify { userRepository.getUserById(1) }
    }

    @Test
    fun `when repository returns error, use case should return failure`() = runTest {
        // TODO: Create test exception
        // HINT: val testException = Exception("User not found")

        // TODO: Mock repository to return failure

        // TODO: Call use case

        // TODO: Verify result is failure

        // TODO: Verify exception message
    }

    @Test
    fun `use case should pass correct userId to repository`() = runTest {
        // TODO: Create test user
        val testUser = User(
            id = 5,
            name = "Test User",
            email = "test@example.com",
            phone = "555-555",
            website = "test.com",
            company = Company("Test Co", "Testing"),
            address = Address("Test St", "Test City", "12345")
        )

        // TODO: Mock repository for userId = 5
        // HINT: coEvery { userRepository.getUserById(5) } returns Result.success(testUser)

        // TODO: Call use case with userId = 5
        // HINT: getUserDetailUseCase(5)

        // TODO: Verify repository was called with exactly userId = 5
        // HINT: coVerify(exactly = 1) { userRepository.getUserById(5) }
    }
}
