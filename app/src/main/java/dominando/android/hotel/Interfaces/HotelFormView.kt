package dominando.android.hotel.Interfaces

import dominando.android.hotel.model.Hotel

interface HotelFormView {

    fun showHotel(hotel: Hotel)

    fun errorInvalidHotel()

    fun errorSaveHotel()
}