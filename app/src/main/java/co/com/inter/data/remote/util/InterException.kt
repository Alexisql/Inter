package co.com.inter.data.remote.util

sealed class InterException() : Exception() {
    class NetworkException() : InterException() {
        override val message: String =
            "You don't have an internet connection, please check your connection and try again."
    }

    class ServiceException() : InterException() {
        override val message: String = "We're having server issues, please try again later."
    }

    class UnknownException() : InterException() {
        override val message: String = "Unknown error, please contact support"
    }
}