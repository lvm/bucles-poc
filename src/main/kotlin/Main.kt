
import bucles.drumPlayer
import bucles.Tempo
import bucles.loop

fun main(args: Array<String>) {
    val hh = loop {
        name = "hip hop"
        tempo = Tempo(90.0)

        pattern("bd", listOf(1,0,0,0,0,0,0,1,0,0,1,0,0,1,0,1))
        pattern("sn", listOf(0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0))
        pattern("ch", listOf(0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0))
        pattern("oh", listOf(0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0))
    }
    val hhShuffle = loop {
        name = "hip hop shuffle"
        tempo = Tempo(90.0)

        pattern("bd", listOf(1,0,0,0,0,0,0,1,0,0,1,0,0,1,0,1).shuffled())
        pattern("sn", listOf(0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0).shuffled())
        pattern("ch", listOf(0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0).shuffled())
        pattern("oh", listOf(0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0).shuffled())
    }

    drumPlayer {
        play(hh)
        play(hh)
        play(hh)
        play(hhShuffle)
        play(hh)
        play(hh)
        play(hhShuffle)
        play(hh)
    }

}
