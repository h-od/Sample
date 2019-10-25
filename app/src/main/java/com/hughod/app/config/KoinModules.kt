package com.hughod.app.config

import android.content.Context
import com.google.gson.Gson
import com.hughod.app.BuildConfig
import com.hughod.app.ui.detail.DetailFragment
import com.hughod.app.ui.detail.DetailViewModelFactory
import com.hughod.app.ui.list.ListFragment
import com.hughod.app.ui.list.ListViewModelFactory
import com.hughod.data.MovieDetailProvider
import com.hughod.data.MovieListProvider
import com.hughod.interaction.interactor.GetAllMoviesInteractor
import com.hughod.interaction.interactor.GetMovieInteractor
import com.hughod.network.Network
import com.hughod.network.data_source.MovieDetailNetworkService
import com.hughod.network.data_source.MovieListNetworkService
import com.hughod.network.mapper.DetailDtoToEntityMapper
import com.hughod.network.mapper.ListDtoToEntityMapper
import com.hughod.storage.GsonSerializer
import com.hughod.storage.data_source.MovieDetailCache
import com.hughod.storage.data_source.MovieListCache
import com.hughod.storage.mapper.DetailDaoToEntityMapper
import com.hughod.storage.mapper.DetailEntityToDaoMapper
import com.hughod.storage.mapper.ListDaoToEntityMapper
import com.hughod.storage.mapper.ListEntityToDaoMapper
import org.koin.android.ext.koin.androidContext
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val listModel = module {
    scope(named<ListFragment>()) {
        scoped { ListViewModelFactory(interactor = get()) }
        scoped { GetAllMoviesInteractor(repository = get<MovieListProvider>()) }
    }
}

val detailModel = module {
    scope(named<DetailFragment>()) {
        scoped { (id: Int) -> DetailViewModelFactory(interactor = get { parametersOf(id) }) }

        scoped { (id: Int) ->
            GetMovieInteractor(repository = get<MovieDetailProvider> {
                parametersOf(
                    id
                )
            })
        }
    }
}

val dataModel = module {
    factory { (id: Int) ->
        MovieDetailProvider(
            movieId = id,
            network = get<MovieDetailNetworkService>(),
            cache = get<MovieDetailCache>()
        )
    }
    single {
        MovieListProvider(
            network = get<MovieListNetworkService>(),
            cache = get<MovieListCache>()
        )
    }
}

val networkModel = module {
    single { Network(url = BuildConfig.BASE_URL, apiKey = BuildConfig.API_KEY) }

    single { MovieDetailNetworkService(network = get(), mapper = DetailDtoToEntityMapper()) }
    single { MovieListNetworkService(network = get(), mapper = ListDtoToEntityMapper()) }
}

val storageModel = module {
    single {
        MovieDetailCache(
            preferences = androidContext().getSharedPreferences(
                "DETAIL_CACHE",
                Context.MODE_PRIVATE
            ),
            serializer = get<GsonSerializer>(),
            toDomainMapper = DetailDaoToEntityMapper(),
            toStorageMapper = DetailEntityToDaoMapper()
        )
    }
    single {
        MovieListCache(
            preferences = androidContext().getSharedPreferences(
                "LIST_CACHE",
                Context.MODE_PRIVATE
            ),
            serializer = get<GsonSerializer>(),
            toDomainMapper = ListDaoToEntityMapper(),
            toStorageMapper = ListEntityToDaoMapper()
        )
    }

    single { GsonSerializer(Gson()) }
}

val modules = listOf(listModel, detailModel, dataModel, networkModel, storageModel)
