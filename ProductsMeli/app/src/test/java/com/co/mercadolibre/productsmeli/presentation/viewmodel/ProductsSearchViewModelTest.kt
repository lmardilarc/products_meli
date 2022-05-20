package com.co.mercadolibre.productsmeli.presentation.viewmodel

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.co.mercadolibre.productsmeli.data.model.ProductsSearchResponse
import com.co.mercadolibre.productsmeli.domain.usecase.GetProductDetailUseCase
import com.co.mercadolibre.productsmeli.domain.usecase.GetProductsUseCase
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.co.mercadolibre.productsmeli.data.util.Resource
import com.google.gson.Gson
import io.mockk.coEvery
import kotlinx.coroutines.test.runBlockingTest


class ProductsSearchViewModelTest {
    @RelaxedMockK
    private lateinit var app: Application

    @RelaxedMockK
    private lateinit var getProductsUseCase: GetProductsUseCase

    @RelaxedMockK
    private lateinit var getProductDetailUseCase: GetProductDetailUseCase

    @RelaxedMockK
    private lateinit var context: Context

    private lateinit var productsSearchViewModel: ProductsSearchViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun before() {
        MockKAnnotations.init(this)
        productsSearchViewModel =
            ProductsSearchViewModel(app, getProductsUseCase, getProductDetailUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }


    @Test
    @ExperimentalCoroutinesApi
    fun `get products when excute succesfully and gets results`() = runTest {
        //Given
        val response = Resource.Success(mockProductsSearchListValue("productsearchresponse.json"))
        coEvery { getProductsUseCase.execute("", 0) } returns response
        //when
        productsSearchViewModel.getProducts("", context)

        //then
        assert(productsSearchViewModel.productsList.value!!.data!!.results == response.data!!.results)

    }

    @After
    fun after() {
        Dispatchers.resetMain()
    }

    private fun mockProductsSearchListValue(
        fileName: String
    ): ProductsSearchResponse {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val data = source.readString(Charsets.UTF_8)

        return Gson().fromJson(
            data,
            ProductsSearchResponse::class.java
        )
    }

}