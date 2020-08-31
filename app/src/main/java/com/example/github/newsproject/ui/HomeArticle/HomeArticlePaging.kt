package com.example.github.newsproject.ui.HomeArticle


import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.github.newsproject.MyApplication
import com.example.github.newsproject.MyApplication.Companion.instance
import com.example.github.newsproject.base.*
import com.example.github.newsproject.data.MyDataBaseUtils
import com.example.github.newsproject.data.PreferencesHelper
import com.example.github.newsproject.data.db.HomeArticleDetail
import com.example.github.newsproject.network.RetrofitManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.withContext

/**
 *   Created by zhangziyi on 8/25/20 14:45
 */

class HomeArticleRepository{

    suspend fun loadPageData(page : Int) :List<HomeArticleDetail>?  = withContext(Dispatchers.IO){
        RetrofitManager.apiService.homeArticles(page).data.datas
    }
    suspend fun loadTops(): List<HomeArticleDetail>? = withContext(Dispatchers.IO) {
        RetrofitManager.apiService.topArticle(PreferencesHelper.fetchCookie(MyApplication.instance)).data
    }

}
class HomeArticleDataSource( private val repository : HomeArticleRepository) :
    PageKeyedDataSource<Int, HomeArticleDetail>(), CoroutineScope by IOScope() {

    val initState = MutableLiveData<NetworkState>()
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, HomeArticleDetail>
    ) {
        safeLaunch{
            block ={
                initState.postValue(NetworkState.LOADING)
                val tops = repository.loadTops()
                val data = repository.loadPageData(0)

                callback.onResult(arrayListOf<HomeArticleDetail>().apply {
                    addAll(tops ?: arrayListOf())
                    addAll(data ?: arrayListOf())
                },null,1)

                initState.postValue(NetworkState.LOADED)

                withContext(Dispatchers.IO){
                    MyDataBaseUtils.homeArticleCacheDao.clearHomeCache()
                    tops?.let{ MyDataBaseUtils.homeArticleCacheDao.cacheHomeArticles(it)}
                    data?.let{ MyDataBaseUtils.homeArticleCacheDao.cacheHomeArticles(it)}
                }
            }
            onError = {
                initState.postValue(NetworkState.error(it.message, ERROR_CODE_INIT))
            }
        }
    }

    override fun invalidate() {
        super.invalidate()
        cancel()
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, HomeArticleDetail>
    ) {
        TODO("Not yet implemented")
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, HomeArticleDetail>
    ) {
        safeLaunch{
            block={
                repository.loadPageData(params.key)?.let {
                    withContext(Dispatchers.IO){
                        MyDataBaseUtils.homeArticleCacheDao.cacheHomeArticles(it)
                    }
                    callback.onResult(it, params.key+1)
                }
            }
            onError ={
                initState.postValue(NetworkState.error(it.message,ERROR_CODE_MORE))
            }
        }
    }
}

class HomeArticleDataSourceFactory(private val repository : HomeArticleRepository):
    DataSource.Factory<Int, HomeArticleDetail>(){
    val sourceLiveData = MutableLiveData<HomeArticleDataSource>()
    override fun create(): DataSource<Int, HomeArticleDetail> = HomeArticleDataSource(repository).apply {
        sourceLiveData.postValue(this)
    }
}

class HomeArticleAdapter : BasePagedListAdapter<>