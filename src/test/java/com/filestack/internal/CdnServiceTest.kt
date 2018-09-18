package com.filestack.internal

import com.filestack.internal.responses.StoreResponse
import com.google.gson.JsonObject
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import okhttp3.HttpUrl
import okhttp3.Request
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.ArgumentCaptor

class CdnServiceTest {

    val networkClient: NetworkClient = mock()

    val cdnService = CdnService(networkClient)


    @Test
    fun get() {
        cdnService.get("my_handle", "my_policy", "my_signature")

        val argumentCaptor = ArgumentCaptor.forClass(Request::class.java)
        verify(networkClient).call(argumentCaptor.capture())

        val request = argumentCaptor.value
        assertEquals(
                HttpUrl.get("https://cdn.filestackcontent.com/my_handle?policy=my_policy&signature=my_signature"),
                request.url()
        )
        assertEquals("GET", request.method())
    }

    @Test
    fun transform() {
        cdnService.transform("my_tasks", "my_handle")

        val argumentCaptor = ArgumentCaptor.forClass(Request::class.java)
        verify(networkClient).call(argumentCaptor.capture())

        val request = argumentCaptor.value
        assertEquals(
                HttpUrl.get("https://cdn.filestackcontent.com/my_tasks/my_handle"),
                request.url()
        )
        assertEquals("GET", request.method())
    }

    @Test
    fun transformDebug() {
        cdnService.transformDebug("my_tasks", "my_handle")

        val argumentCaptor = ArgumentCaptor.forClass(Request::class.java)
        verify(networkClient).call(argumentCaptor.capture(), eq(JsonObject::class.java))

        val request = argumentCaptor.value
        assertEquals(
                HttpUrl.get("https://cdn.filestackcontent.com/debug/my_tasks/my_handle"),
                request.url()
        )
        assertEquals("GET", request.method())
    }

    @Test
    fun transformStore() {
        cdnService.transformStore("my_tasks", "my_handle")

        val argumentCaptor = ArgumentCaptor.forClass(Request::class.java)
        verify(networkClient).call(argumentCaptor.capture(), eq(StoreResponse::class.java))

        val request = argumentCaptor.value
        assertEquals(
                HttpUrl.get("https://cdn.filestackcontent.com/my_tasks/my_handle"),
                request.url()
        )
        assertEquals("POST", request.method())
    }

    @Test
    fun transformExt() {
        cdnService.transformExt("my_key", "my_tasks", "my_handle")

        val argumentCaptor = ArgumentCaptor.forClass(Request::class.java)
        verify(networkClient).call(argumentCaptor.capture())

        val request = argumentCaptor.value
        assertEquals(
                HttpUrl.get("https://cdn.filestackcontent.com/my_key/my_tasks/my_handle"),
                request.url()
        )
        assertEquals("GET", request.method())
    }

    @Test
    fun transformDebugExt() {
        cdnService.transformDebugExt("my_key", "my_tasks", "my_url")

        val argumentCaptor = ArgumentCaptor.forClass(Request::class.java)
        verify(networkClient).call(argumentCaptor.capture(), eq(JsonObject::class.java))

        val request = argumentCaptor.value
        assertEquals(
                HttpUrl.get("https://cdn.filestackcontent.com/my_key/debug/my_tasks/my_url"),
                request.url()
        )
        assertEquals("GET", request.method())
    }

    @Test
    fun transformStoreExt() {
        cdnService.transformStoreExt("my_key", "my_tasks", "my_url")

        val argumentCaptor = ArgumentCaptor.forClass(Request::class.java)
        verify(networkClient).call(argumentCaptor.capture(), eq(StoreResponse::class.java))

        val request = argumentCaptor.value
        assertEquals(
                HttpUrl.get("https://cdn.filestackcontent.com/my_key/my_tasks/my_url"),
                request.url()
        )
        assertEquals("POST", request.method())
    }
}