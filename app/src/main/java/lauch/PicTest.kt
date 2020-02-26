package lauch

import retrofit2.http.GET

interface PicTest {
    @GET("banner/json")
    suspend fun getBanners() : BaseResult<List<Bannerbean>>
}