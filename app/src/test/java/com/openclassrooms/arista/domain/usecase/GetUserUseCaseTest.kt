package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.repository.UserRepository
import com.openclassrooms.arista.domain.model.User
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class GetUserUseCaseTest{
    @Mock
    private lateinit var userRepository: UserRepository


    private lateinit var getUserUsecase: GetUserUsecase


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getUserUsecase = GetUserUsecase(userRepository)
    }

    @After
    fun tearDown() {
        Mockito.framework().clearInlineMocks()
    }


    @Test
    fun `when repository returns user, use case should return him`() = runBlocking {
        // Arrange
        val fakeUser =
            User(
                "name",
                "email"
            )
        Mockito.`when`(userRepository.getUser()).thenReturn(fakeUser)


        // Act
        val result = getUserUsecase.execute()


        // Assert
        assertEquals(fakeUser, result)
    }

    @Test
    fun `when repository returns empty, use case should return empty`() = runBlocking {
        // Arrange
        Mockito.`when`(userRepository.getUser()).thenReturn(null)


        // Act
        val result = getUserUsecase.execute()


        // Assert
        assertNull(result)
    }
}