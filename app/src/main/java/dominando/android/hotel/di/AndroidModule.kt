package dominando.android.hotel.di

import dominando.android.hotel.auth.Auth
import dominando.android.hotel.auth.AuthManager
import dominando.android.hotel.details.HotelDetailsViewModel
import dominando.android.hotel.form.HotelFormViewModel
import dominando.android.hotel.list.HotelListViewModel
import dominando.android.hotel.repository.HotelRepository
import dominando.android.hotel.repository.room.HotelDatabase
import dominando.android.hotel.repository.room.RoomRepository
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val androidModule = module {
    single { this }
    single {
        RoomRepository(HotelDatabase.getDatabase(context = get()))
                as HotelRepository
    }

    viewModel {
        HotelListViewModel(repository = get())
    }
    viewModel {
        HotelDetailsViewModel(repository = get())
    }
    viewModel {
        HotelFormViewModel(repository = get())
    }
    single {
        val manager: AuthManager = get ()
        manager as Auth
    }

    single {  }
    AuthManager(context =  get())
}