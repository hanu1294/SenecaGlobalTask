package com.senecaglobal.weatherapp.repositories

import com.senecaglobal.weatherapp.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {
    suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<T> = apiToBeCalled()
                if (response.isSuccessful) {
                    Resource.success(data = response.body()!!, "")
                } else {
                    var errorMessage = "Something went wrong"
                    try {
                        val jObjError = JSONObject(response.errorBody()!!.string())
                        errorMessage = jObjError.getString("message")
                        Resource.error(
                            null,
                            errorMessage
                        )
                    } catch (e: Exception) {
                        Resource.error(null, errorMessage)
                    }
                }
            } catch (e: HttpException) {
                Resource.error(null, msg = e.message ?: "Something went wrong")
            } catch (e: IOException) {
                Resource.error(null, "Please check your network connection")
            } catch (e: Exception) {
                Resource.error(null, "Something went wrong")
            }
        }
    }

}