package com.anbui.skribbl.core.data.network

import com.anbui.skribbl.domain.model.Employee
import com.anbui.skribbl.domain.model.EmployeeRespond
import com.anbui.skribbl.domain.repository.TestRepository
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.serialization.JsonConvertException

class TestRepositoryImpl(
    private val client: HttpClient
) : TestRepository {
    override suspend fun getEmployee(): List<Employee> {
        try {
            val a = client.get("https://dummy.restapiexample.com/api/v1/employees")
                .body<EmployeeRespond>()
            return a.data
        } catch (e: JsonConvertException) {
            Napier.d("too many request")
        }
        return emptyList()

    }
}