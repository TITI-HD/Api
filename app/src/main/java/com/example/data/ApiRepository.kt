package com.example.data

import kotlinx.coroutines.flow.Flow

class ApiRepository(private val apiDao: ApiDao) {
    val allRequests: Flow<List<RequestEntity>> = apiDao.getAllRequests()
    val allFlows: Flow<List<FlowEntity>> = apiDao.getAllFlows()

    suspend fun saveRequest(req: RequestEntity) = apiDao.insertRequest(req)
    suspend fun deleteRequest(id: Int) = apiDao.deleteRequestById(id)

    suspend fun saveFlow(flow: FlowEntity) = apiDao.insertFlow(flow)
    suspend fun deleteFlow(id: Int) = apiDao.deleteFlowById(id)
}
