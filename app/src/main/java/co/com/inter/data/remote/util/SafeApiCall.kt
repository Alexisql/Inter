package co.com.inter.data.remote.util

import android.util.Log
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(
    className: String,
    call: suspend () -> T
): Result<T> {
    return try {
        Result.success(call())
    } catch (e: IOException) {
        Log.e(className, "Network error: ${e.message}")
        Result.failure(InterException.NetworkException())
    } catch (e: HttpException) {
        Log.e(className, "Api error: ${e.message}")
        Result.failure(InterException.ServiceException())
    } catch (e: Exception) {
        Log.e(className, "Unknown error: ${e.message}")
        Result.failure(InterException.UnknownException())
    }
}