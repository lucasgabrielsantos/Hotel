package dominando.android.hotel.Interfaces

import dominando.android.hotel.model.Hotel

interface HotelDetailsView {

    fun showHotelDetails(hotel: Hotel)

    fun errorHotelNotFound()
}