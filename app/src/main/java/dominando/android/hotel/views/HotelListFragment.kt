package dominando.android.hotel.views

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.fragment.app.ListFragment
import dominando.android.hotel.HotelListView
import dominando.android.hotel.model.Hotel
import dominando.android.hotel.presenter.HotelListPresenter
import dominando.android.hotel.utils.MemoryRepository

class HotelListFragment : ListFragment(), HotelListView {
    private val presenter = HotelListPresenter(
        this@HotelListFragment,
        MemoryRepository
    )
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.searchHotels("")
    }

    override fun showHotels(hotels: List<Hotel>) {
        val adapter = ArrayAdapter<Hotel>(
            requireContext(),
            android.R.layout.simple_list_item_1, hotels
        )
        listAdapter = adapter
    }

    override fun showHotelsDetails(hotel: Hotel) {


    }

}