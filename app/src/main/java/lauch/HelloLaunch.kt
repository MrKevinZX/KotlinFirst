package lauch

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class BaseViewModel(application: Application) : AndroidViewModel(application),LifecycleObserver{

    val mBanners = MutableLiveData<List<Bannerbean>>()
    private val reposity by lazy {
        val retrofit = Retrofit.Builder().client(getOkhttpClient()).baseUrl("").addConverterFactory(GsonConverterFactory.create()).build()
        retrofit.create(PicTest::class.java)
    }

    private fun getOkhttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder().connectTimeout(20L, TimeUnit.SECONDS).build()
    }


    fun getBanner() {
        viewModelScope.launch{
            val result = withContext(Dispatchers.IO){reposity.getBanners()}
            mBanners.value = result.data
        }
    }

}