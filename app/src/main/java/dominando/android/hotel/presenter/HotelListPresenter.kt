package dominando.android.hotel.presenter

import dominando.android.hotel.Interfaces.HotelListView
import dominando.android.hotel.model.Hotel
import dominando.android.hotel.repository.HotelRepository

class HotelListPresenter(
    private val view: HotelListView,
    private val repository: HotelRepository
) {

    fun searchHotels(term:String){
        repository.search(term){hotels ->
            view.showHotels(hotels!!)
        }
    }

    fun showHotelDetails(hotel: Hotel){
        view.showHotelsDetails(hotel)
    }
}