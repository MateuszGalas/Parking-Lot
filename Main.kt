package parking

const val parkingRows = 1
const val parkingPlaces = 20

data class Car(val registrationNumber: String, val color: String)

class Parking(private val size: Int) {
    private var parkingLot = MutableList(parkingRows) {
        MutableList<Car>(size) { Car("", "") }
    }

    fun create(size: Int) {
        println("Created a parking lot with $size spots.")
        parkingLot = MutableList(parkingRows) {
            MutableList<Car>(size) { Car("", "") }
        }
    }

    fun status() {
        var parking = false
        parkingLot[0].forEach {
            if (it != Car("", "")) {
                parking = true
                println("${parkingLot[0].indexOf(it) + 1} ${it.registrationNumber} ${it.color}")
            }
        }
        if (!parking) println("Parking lot is empty.")
    }

    fun park(registrationNumber: String, color: String) {
        parkingLot[0].forEach {
            if (it == Car("", "")) {
                val spot = parkingLot[0].indexOf(it)
                parkingLot[0][spot] = Car(registrationNumber, color)
                println("$color car parked in spot ${spot + 1}.")
                return
            }
        }
        println("Sorry, the parking lot is full.")
    }

    fun leave(spot: Int) {
        if (parkingLot[0][spot - 1] == Car("", "")) {
            println("There is no car in spot $spot.")
        } else {
            parkingLot[0][spot - 1] = Car("", "")
            println("Spot $spot is free.")
        }
    }


}

fun main() {
    val parking: Parking

    while (true) {
        val command = readln().split(" ")
        if (command.first() == "create" && command.last().matches("[1-9][0-9]?".toRegex())) {
            println("Created a parking lot with ${command.last()} spots.")
            parking = Parking(command.last().toInt())
            break
        } else if (command.first() == "exit") {
            return
        } else {
            println("Sorry, a parking lot has not been created.")
        }
    }

    while (true) {
        val command = readln().split(" ")
        when (command.first()) {
            "park" -> parking.park(command[1], command[2])
            "leave" -> parking.leave(command[1].toInt())
            "status" -> parking.status()
            "create" -> parking.create(command.last().toInt())
            "exit" -> break
        }
    }
}
