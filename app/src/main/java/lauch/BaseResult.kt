package lauch

data class BaseResult<T>(
    val errorMsg : String,
    val errorCode : Int,
    val data : T) {

}
