package dominando.android.hotel

import dominando.android.hotel.model.Hotel

interface HotelListView {

    fun showHotels(hotels: List<Hotel>)


    fun showHotelsDetails(hotel: Hotel)


}