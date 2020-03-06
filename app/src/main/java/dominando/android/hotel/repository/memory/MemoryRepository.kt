package dominando.android.hotel.repository.memory

import dominando.android.hotel.model.Hotel
import dominando.android.hotel.repository.HotelRepository

object MemoryRepository : HotelRepository {

    private var nextId = 1L
    private val hotelList = mutableListOf<Hotel>()

    init {
        save(
            Hotel(
                0,
                "New Beach Hotel",
                "Av. Boa Viagem",
                4.5f
            )
        )
        save(
            Hotel(
                0,
                "Recife Hotel",
                "Av. Boa Viagem",
                4.0f
            )
        )
        save(
            Hotel(
                0,
                "Canario Hotel",
                "Rua dos Navegantes",
                3.0f
            )
        )
        save(
            Hotel(
                0,
                "Byanca Beach Hotel",
                "Rua Reseda",
                4.0f
            )
        )
        save(
            Hotel(
                0,
                "Grand Hotel",
                "Av. Bernardo",
                3.5f
            )
        )
        save(
            Hotel(
                0,
                "Hotel Cool",
                "Av. Lucas",
                3.5f
            )
        )
        save(
            Hotel(
                0,
                "Hotel Infinity",
                "Rua Gabriel Dos Santos",
                2.5f
            )
        )
        save(
            Hotel(
                0,
                "Hotel Tulipy",
                "Av. Boa Viagem",
                5.0f
            )
        )
    }

    override fun save(hotel: Hotel) {
        if (hotel.id == 0L) {
            hotel.id = nextId++
            hotelList.add(hotel)
        } else {
            val index = hotelList.indexOfFirst { it.id == hotel.id }
            if (index > -1) {
                hotelList[index] = hotel
            } else {
                hotelList.add(hotel)
            }
        }
    }

    override fun remove(vararg hotels: Hotel) {
        hotelList.removeAll(hotels)

    }

    override fun hotelById(id: Long, callback: (Hotel?) -> Unit) {
        callback(hotelList.find { it.id == id })

    }

    override fun search(term: String, callback: (List<Hotel>?) -> Unit) {
        val resultList =
            if (term.isEmpty()) hotelList
            else hotelList.filter {
                it.name.toUpperCase().contains(term.toUpperCase())
            }
        callback(resultList.sortedBy { it.name })
    }
}