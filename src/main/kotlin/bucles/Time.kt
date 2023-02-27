package bucles

class Tempo (private val value: Double = 120.0) {
    fun bpm() : Double = 60.0 / value
}