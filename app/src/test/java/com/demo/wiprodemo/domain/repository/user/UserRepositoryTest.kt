package com.demo.wiprodemo.domain.repository.user

import com.demo.wiprodemo.data.api.UserApi
import com.demo.wiprodemo.data.repository.UserRepositoryImpl
import com.demo.wiprodemo.domain.utils.Result
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.assert

class UserRepositoryTest {
    private lateinit var apiService: UserApi
    private lateinit var repository: UserRepositoryImpl

    @Before
    fun setup() {
        apiService = mock(UserApi::class.java)
        repository = UserRepositoryImpl(apiService)
    }

    @Test
    fun `getUsers should emit Loading and Success`() = runTest {
        val users = listOf(dummyUserEntity)
        whenever(apiService.fetchUser()).thenReturn(users)
        val emissions = repository.fetchUser().toList()
        assert(emissions[0] is Result.Loading)
        val success = emissions[1] as Result.Success

        assert(success.data.first().name == "Rahul")
        assert(success.data.first().email == "rahul@test.com")
    }

    @Test
    fun `getUsers should emit Error when api fails`() = runTest {
        whenever(apiService.fetchUser()).thenThrow(RuntimeException("Network Error"))
        val emissions = repository.fetchUser().toList()
        assert(emissions[0] is Result.Loading)
        val error = emissions[1] as Result.Error
        assert(error.error.message == "Network Error")
    }

    @Test
    fun `getUsers should handle empty list`() = runTest {
        whenever(apiService.fetchUser()).thenReturn(emptyList())
        val emissions = repository.fetchUser().toList()
        assert(emissions[0] is Result.Loading)
        val success = emissions[1] as Result.Success
        assert(success.data.isEmpty())
    }

    @Test
    fun `verify api is called once`() = runTest {
        whenever(apiService.fetchUser()).thenReturn(emptyList())
        repository.fetchUser().toList()
        verify(apiService, times(1)).fetchUser()
    }
}