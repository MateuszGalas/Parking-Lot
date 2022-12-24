package parking

const val parkingRows = 1
const val parkingPlaces = 2

data class Car(val registrationNumber: String, val color: String)

class Parking() {
    private val parkingLot = MutableList(parkingRows) {
        MutableList<Car>(parkingPlaces) { Car("", "") }
    }

    init {
        parkingLot[0][0] = Car("KA-01-HH-1234", "Red")
    }

    fun park(registrationNumber: String, color: String) {
        parkingLot[0].forEach {
            if (it == Car("", "")) {
                val spot = parkingLot[0].indexOf(it)
                parkingLot[0][spot] = Car(registrationNumber, color)
                println("$color car parked in spot ${spot + 1}.")
            }
        }

    }

    fun leave(spot: Int) {
        if (parkingLot[0][spot - 1] == Car("", "")) {
            println("There is no car in spot 2.")
        } else {
            parkingLot[0][spot - 1] = Car("", "")
            println("Spot $spot is free.")
        }
    }
}

fun main() {
    val  parking = Parking()
    val command = readln().split(" ")

    when (command.first()) {
        "park" -> parking.park(command[1], command[2])
        "leave" -> parking.leave(command[1].toInt())
    }
}
