package myreversi.exception

class ReversiException(message :String = null,
                       cause :Throwable = null) extends RuntimeException(message, cause)
