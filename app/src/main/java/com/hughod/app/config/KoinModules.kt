package com.hughod.app.config

import android.content.Context
import com.google.gson.Gson
import com.hughod.app.BuildConfig
import com.hughod.app.ui.detail.DetailFragment
import com.hughod.app.ui.detail.DetailViewModelFactory
import com.hughod.app.ui.list.ListFragment
import com.hughod.app.ui.list.ListViewModelFactory
import com.hughod.data.MovieDetailRepository
import com.hughod.data.MovieListRepository
import com.hughod.interaction.detail.GetMovieInteractor
import com.hughod.interaction.list.GetAllMoviesInteractor
import com.hughod.network.Network
import com.hughod.storage.Cache
import com.hughod.storage.GsonSerializer
import org.koin.android.ext.koin.androidContext
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val listModel = module {
    scope(named<ListFragment>()) {
        scoped { ListViewModelFactory(interactor = get()) }
        scoped { GetAllMoviesInteractor(repository = get<MovieListRepository>()) }
    }
}

val detailModel = module {
    scope(named<DetailFragment>()) {
        scoped { (id: Int) -> DetailViewModelFactory(interactor = get { parametersOf(id) }) }

        scoped { (id: Int) ->
            GetMovieInteractor(repository = get<MovieDetailRepository>{ parametersOf(id) })
        }
    }
}

val dataModel = module {
    factory { (id: Int) -> MovieDetailRepository(
        movieId = id,
        network = get(),
        cache = get()
    ) }
    single { MovieListRepository(get(), get()) }
}

val networkModel = module {
    single { Network(url = BuildConfig.BASE_URL, apiKey = BuildConfig.API_KEY) }
    single { get<Network>().movieDetailService }
    single { get<Network>().movieListService }
}

val storageModel = module {
    single {
        Cache.Detail(
            preferences = androidContext().getSharedPreferences(
                "DETAIL_CACHE",
                Context.MODE_PRIVATE
            ),
            serializer = get<GsonSerializer>()
        )
    }
    single {
        Cache.List(
            preferences = androidContext().getSharedPreferences(
                "LIST_CACHE",
                Context.MODE_PRIVATE
            ),
            serializer = get<GsonSerializer>()
        )
    }

    single { GsonSerializer(Gson()) }
}

val modules = listOf(listModel, detailModel, dataModel, networkModel, storageModel)
