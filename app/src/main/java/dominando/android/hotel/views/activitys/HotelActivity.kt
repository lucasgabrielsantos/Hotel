package dominando.android.hotel.views.activitys

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dominando.android.hotel.R
import dominando.android.hotel.model.Hotel
import dominando.android.hotel.views.fragments.HotelListFragment

class HotelActivity : AppCompatActivity(), HotelListFragment.OnHotelClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel)
    }

    override fun onHotelClick(hotel: Hotel) {
        showDetailsActivity(hotel.id)
    }

    private fun showDetailsActivity(hotelId: Long) {
        HotelDetailsActivity.open(this, hotelId)
    }
}
