package dominando.android.hotel.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import dominando.android.hotel.Interfaces.HotelFormView
import dominando.android.hotel.R
import dominando.android.hotel.model.Hotel
import dominando.android.hotel.presenter.HotelFormPresenter
import dominando.android.hotel.repository.MemoryRepository
import kotlinx.android.synthetic.main.fragment_hotel_form.*

class HotelFormFragment : DialogFragment(), HotelFormView {
    private val presenter = HotelFormPresenter(this, MemoryRepository)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hotel_form, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val hotelId = arguments?.getLong(EXTRA_HOTEL_ID, 0) ?: 0
        presenter.loadHotel(hotelId)
        edtAddress.setOnEditorActionListener { _, i, _ ->
            handleKeyBoardEvent(i)
        }
        dialog?.window?.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
        )
    }


    override fun showHotel(hotel: Hotel) {
        edtName.setText(hotel.name)
        edtAddress.setText(hotel.address)
        rtbRating.rating = hotel.rating
    }

    override fun errorSaveHotel() {
        Toast.makeText(
            requireContext(), R.string.error_hotel_not_found,
            Toast.LENGTH_LONG
        ).show()
    }

    override fun errorInvalidHotel() {
        Toast.makeText(
            requireContext(), R.string.error_invalid_hotel,
            Toast.LENGTH_LONG
        ).show()
    }

    private fun handleKeyBoardEvent(actionId: Int): Boolean {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            val hotel = saveHotel()
            if (hotel != null) {
                if (activity is HotelListFragment.OnHotelClickListener) {
                    val listener = activity as OnHotelSavedListener
                    listener.onHotelSaved(hotel)
                }
                //Feche Dialog
                dialog?.dismiss()
                return true
            }
        }
        return false
    }

    private fun saveHotel(): Hotel? {
        val hotel = Hotel()
        val hotelId = arguments?.getLong(EXTRA_HOTEL_ID, 0) ?: 0
        hotel.id = hotelId
        hotel.apply {
            name = edtName.text.toString()
            address = edtAddress.text.toString()
            rating = rtbRating.rating
        }
        if (presenter.saveHotel(hotel)) {
            return hotel
        } else {
            return null
        }
    }

     fun open(fm: FragmentManager) {
        if (fm.findFragmentByTag(DIALOG_TAG) == null) {
            show(fm, DIALOG_TAG)
        }
    }

    interface OnHotelSavedListener {
        fun onHotelSaved(hotel: Hotel)
    }

    companion object {
        const val DIALOG_TAG = "editDialog"
        const val EXTRA_HOTEL_ID = "hotel_id"
    }

     fun newInstance(hotelId: Long = 0) = HotelFormFragment().apply {
        arguments = Bundle().apply {
            putLong(EXTRA_HOTEL_ID, hotelId)
        }
    }
}