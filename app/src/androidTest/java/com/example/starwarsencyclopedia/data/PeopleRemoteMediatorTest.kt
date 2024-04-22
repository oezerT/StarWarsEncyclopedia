package com.example.starwarsencyclopedia.data

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.starwarsencyclopedia.FakeStarWarsApi
import com.example.starwarsencyclopedia.data.local.StarWarsDatabase
import com.example.starwarsencyclopedia.data.local.people.PersonDao
import com.example.starwarsencyclopedia.data.local.people.PersonEntity
import com.example.starwarsencyclopedia.mockPeople
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@ExperimentalPagingApi
@RunWith(AndroidJUnit4::class)
class PeopleRemoteMediatorTest {
    private lateinit var categoryDao: PersonDao
    private lateinit var mockDb: StarWarsDatabase
    private val mockApi = FakeStarWarsApi()

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        mockDb = Room.inMemoryDatabaseBuilder(
            context, StarWarsDatabase::class.java
        ).build()
        categoryDao = mockDb.personDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        mockDb.close()
    }

    @After
    fun tearDown() {
        mockDb.clearAllTables()
        // Clear out failure message to default to the successful response.
        mockApi.failureMsg = null
        // Clear out categories after each test run.
        mockApi.clearCategories()
    }

    @Test
    fun refreshLoadReturnsSuccessResultWhenMoreDataIsPresent() = runTest {
        // Add mock results for the API to return.
        mockApi.people = mockPeople()

        val remoteMediator = PeopleRemoteMediator(
            starWarsDb = mockDb,
            starWarsApi = mockApi,
        )

        val pagingState = PagingState<Int, PersonEntity>(
            pages = listOf(),
            anchorPosition = null,
            config = PagingConfig(10),
            leadingPlaceholderCount = 10
        )

        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertTrue(result is RemoteMediator.MediatorResult.Success)
        assertFalse((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
    }

    @Test
    fun refreshLoadSuccessAndEndOfPaginationWhenNoMoreData() = runTest {
        // To test endOfPaginationReached, don't set up the mockApi to return category data here.
        val remoteMediator = PeopleRemoteMediator(
            starWarsDb = mockDb,
            starWarsApi = mockApi,
        )

        val pagingState = PagingState<Int, PersonEntity>(
            pages = listOf(),
            anchorPosition = null,
            config = PagingConfig(10),
            leadingPlaceholderCount = 10
        )

        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertTrue(result is RemoteMediator.MediatorResult.Success)
        assertTrue((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
    }

    @Test
    fun refreshLoadReturnsErrorResultWhenErrorOccurs() = runTest {
        // Set up failure message to throw exception from the mock API.
        mockApi.failureMsg = "Throw test failure"

        val remoteMediator = PeopleRemoteMediator(
            starWarsDb = mockDb,
            starWarsApi = mockApi,
        )

        val pagingState = PagingState<Int, PersonEntity>(
            pages = listOf(),
            anchorPosition = null,
            config = PagingConfig(10),
            leadingPlaceholderCount = 10
        )

        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertTrue(result is RemoteMediator.MediatorResult.Error)
    }
}