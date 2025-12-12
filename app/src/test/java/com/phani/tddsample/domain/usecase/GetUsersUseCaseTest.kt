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
 * Test class for GetUsersUseCase
 *
 * Testing Strategy:
 * 1. Test success scenarios
 * 2. Test failure/error scenarios
 * 3. Verify repository interactions
 */
class GetUsersUseCaseTest {

    // TODO: Declare dependencies as lateinit var
    // HINT: You need UserRepository (mock) and GetUsersUseCase (system under test)
    private lateinit var userRepository: UserRepository
    private lateinit var getUsersUseCase: GetUsersUseCase

    @BeforeEach
    fun setup() {
        // TODO: Initialize mocks using mockk()
        // HINT: userRepository = mockk()
        userRepository = mockk()

        // TODO: Initialize the use case with mocked repository
        // HINT: getUsersUseCase = GetUsersUseCase(userRepository)
        getUsersUseCase = GetUsersUseCase(userRepository)
    }

    @Test
    fun `when repository returns users successfully, use case should return success with users list`() = runTest {
        // TODO: Create test data (list of users)
        // HINT: Create a list with at least 2 sample users
        val testUsers = listOf(
            User(
                id = 1,
                name = "John Doe",
                email = "john@example.com",
                phone = "123-456",
                website = "john.com",
                company = Company("Tech Corp", "Innovation"),
                address = Address("123 Main St", "NY", "10001")
            ),
            User(
                id = 2,
                name = "Jane Smith",
                email = "jane@example.com",
                phone = "789-012",
                website = "jane.com",
                company = Company("Design Inc", "Creativity"),
                address = Address("456 Oak Ave", "LA", "90001")
            )
        )

        // TODO: Mock the repository behavior
        // HINT: Use coEvery { userRepository.getUsers() } returns Result.success(testUsers)

        // TODO: Call the use case
        // HINT: val result = getUsersUseCase()

        // TODO: Verify the result is success
        // HINT: assertTrue(result.isSuccess)

        // TODO: Verify the result contains the expected users
        // HINT: assertEquals(testUsers, result.getOrNull())

        // TODO: Verify repository was called exactly once
        // HINT: Use coVerify(exactly = 1) { userRepository.getUsers() }
    }

    @Test
    fun `when repository returns error, use case should return failure`() = runTest {
        // TODO: Create a test exception
        // HINT: val testException = Exception("Network error")

        // TODO: Mock the repository to return failure
        // HINT: Use coEvery { userRepository.getUsers() } returns Result.failure(testException)

        // TODO: Call the use case
        // HINT: val result = getUsersUseCase()

        // TODO: Verify the result is a failure
        // HINT: assertTrue(result.isFailure)

        // TODO: Verify the exception message
        // HINT: assertEquals("Network error", result.exceptionOrNull()?.message)

        // TODO: Verify repository was called
        // HINT: Use coVerify { userRepository.getUsers() }
    }

    @Test
    fun `when repository returns empty list, use case should return success with empty list`() = runTest {
        // TODO: Mock repository to return empty list
        // HINT: coEvery { userRepository.getUsers() } returns Result.success(emptyList())

        // TODO: Call the use case

        // TODO: Verify result is success

        // TODO: Verify the list is empty
        // HINT: assertTrue(result.getOrNull()?.isEmpty() == true)
    }
}
