package dominando.android.hotel.views.activitys

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import dominando.android.hotel.R
import dominando.android.hotel.model.Hotel
import dominando.android.hotel.views.fragments.HotelDetailsFragment
import dominando.android.hotel.views.fragments.HotelListFragment

class HotelActivity : AppCompatActivity(),
    HotelListFragment.OnHotelClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel)
    }

    override fun onHotelClick(hotel: Hotel) {

        if (isTablet()) {
            showDetailsFragment(hotel.id)
        } else {
            showDetailsActivity(hotel.id)
        }
    }

    private fun showDetailsActivity(hotelId: Long) {
        HotelDetailsActivity.open(this, hotelId)
    }

    private fun showDetailsFragment(hotelId: Long) {
        val fragment = HotelDetailsFragment.newInstance(hotelId)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.details, fragment, HotelDetailsFragment.TAG_DETAILS)
            .commit()
    }

    private fun isTablet() = resources.getBoolean(R.bool.tablet)
    private fun isSmartphone() = resources.getBoolean(R.bool.smartphone)


}
