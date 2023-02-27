package bucles

import javax.sound.midi.MidiSystem
import javax.sound.midi.Synthesizer

abstract class Player {
    private var synth : Synthesizer? = null

    init {
        this.synth = MidiSystem.getSynthesizer()
    }

    internal fun wait(duration: Double) = Thread.sleep((duration * 1000).toLong())

    internal fun getChannels() =
        this.synth!!.channels

    fun open() = this.synth!!.open()
    fun close() = this.synth!!.close()

    abstract fun play(drumLoop: DrumLoop)
}

class DrumPlayer : Player() {
    private val instrument : Int = 9

    override fun play(drumLoop: DrumLoop) {
        val channels = this.getChannels()
        val eventList = drumLoop.toEventList()
        eventList.forEach { evt ->
            val midiNote = evt.symbol.midiNote()
            if (midiNote > 0) {
                channels[instrument].noteOn(midiNote, evt.velocity)
                this.wait(evt.duration)
                channels[instrument].noteOff(midiNote)
            } else {
                this.wait(evt.duration)
            }
        }
    }
}

fun drumPlayer(player: DrumPlayer.() -> Unit) : DrumPlayer {
    val d = DrumPlayer()
    d.open()
    d.player()
    d.close()
    return d
}
