package myreversi.exception

class UnknownException(message :String = null,
                       cause :Throwable = null) extends ReversiException(message, cause)
