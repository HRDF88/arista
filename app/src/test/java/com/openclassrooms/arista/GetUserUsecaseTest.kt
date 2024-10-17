package com.openclassrooms.arista

import com.openclassrooms.arista.data.repository.UserRepository
import com.openclassrooms.arista.domain.model.User
import com.openclassrooms.arista.domain.usecase.GetUserUsecase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class GetUserUsecaseTest {
    private lateinit var getUserUsecase: GetUserUsecase
    private val userRepository: UserRepository = mock()

    @Before
    fun setup() {
        getUserUsecase = GetUserUsecase(userRepository)
    }

    @Test
    fun `when execute is called and user list is not empty, should return the first user`() =
        runBlocking {
            // Arrange
            val expectedUser = User(
                id = 1,
                name = "Jocelyn Testing",
                email = "jocelyntesting@gmail.com",
                password = "1234ABCD"
            )
            val userList = listOf(
                expectedUser,
                User(
                    id = 2,
                    name = "Jeanette Smith",
                    email = "jeanettesmith@caramail.fr",
                    password = "lapatate"
                )
            )
            Mockito.`when`(userRepository.user).thenReturn(flowOf(userList))

            // Act
            val result = getUserUsecase.execute().first()

            // Assert
            assertEquals(expectedUser, result)
        }
}