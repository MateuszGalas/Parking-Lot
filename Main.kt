package parking

data class Car(val registrationNumber: String, val color: String)

class Parking(size: Int) {
    private var parkingLot: MutableList<Car> = MutableList(size) { Car("", "") }

    init {
        println("Created a parking lot with $size spots.")
    }

    fun status() {
        val list = parkingLot.filter { it != Car("", "") }
        if (list.isEmpty()) {
            println("Parking lot is empty.")
            return
        }
        list.forEach { println("${parkingLot.indexOf(it) + 1} ${it.registrationNumber} ${it.color}") }
    }

    fun park(registrationNumber: String, color: String) {
        parkingLot.forEach {
            if (it == Car("", "")) {
                val spot = parkingLot.indexOf(it)
                parkingLot[spot] = Car(registrationNumber, color)
                println("$color car parked in spot ${spot + 1}.")
                return
            }
        }
        println("Sorry, the parking lot is full.")
    }

    fun leave(spot: Int) {
        if (parkingLot[spot - 1] == Car("", "")) {
            println("There is no car in spot $spot.")
        } else {
            parkingLot[spot - 1] = Car("", "")
            println("Spot $spot is free.")
        }
    }

    fun regByColor(color: String) {
        val list = filterCarsBy(color, "color")
        println(list!!.joinToString { it.registrationNumber })
    }

    fun spotByColor(color: String) {
        val list = filterCarsBy(color, "color")
        println(list!!.joinToString { "${parkingLot.indexOf(it) + 1}" })
    }

    fun spotByReg(reg: String) {
        val list = filterCarsBy(reg, "registrationNumber")
        println(list!!.joinToString { "${parkingLot.indexOf(it) + 1}" })
    }
    private fun filterCarsBy(filter: String, filterBase: String): List<Car>? {
        var list: List<Car>? = null
        when (filterBase) {
            "color" -> {
                list = parkingLot.filter { it.color.equals(filter, true) }
                if (list.isEmpty()) throw IllegalStateException("No cars with color $filter were found.")
            }
            "registrationNumber" -> {
                list = parkingLot.filter { it.registrationNumber.equals(filter, true) }
                if (list.isEmpty()) throw IllegalStateException("No cars with registration number $filter were found.")
            }
        }
        return list
    }
}

fun main() {
    var parking: Parking? = null

    while (true) {
        val command = readln().split(" ")
        try {
            when (command.first()) {
                "park" -> parking!!.park(command[1], command[2])
                "leave" -> parking!!.leave(command[1].toInt())
                "status" -> parking!!.status()
                "create" -> parking = Parking(command.last().toInt())
                "reg_by_color" -> parking!!.regByColor(command.last())
                "spot_by_color" -> parking!!.spotByColor(command.last())
                "spot_by_reg" -> parking!!.spotByReg(command.last())
                "exit" -> break
            }
        } catch (e: IllegalStateException) {
            println(e.message)
        } catch (e: Exception) {
            println("Sorry, a parking lot has not been created.")
        }
    }
}
