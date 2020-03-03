package dominando.android.hotel.Interfaces

import dominando.android.hotel.model.Hotel

interface HotelListView {

    fun showHotels(hotels: List<Hotel>)


    fun showHotelsDetails(hotel: Hotel)

    fun showDeleteMode()

    fun hideDeleteMode()

    fun showSelectedHotels(hotels: List<Hotel>)

    fun updateSelectionCountText(count: Int)
}