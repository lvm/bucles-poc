package bucles

import kotlin.collections.forEachIndexed

enum class DrumPart {
    KICK("bd", 36),
    SNARE("sn", 38),
    HIHAT("ch", 42),
    OPENHAT("oh", 46);

    var slug : String? = null
    var midiNote : Int? = null

    constructor(
        slug: String,
        midiNote: Int
    ) {
        this.slug = slug
        this.midiNote = midiNote
    }

    companion object {
        fun getBySlug(slug: String) : DrumPart? =
            values().firstOrNull { part -> part.slug == slug }
    }

}

open class DrumLoop {
    var name: String = "Unknown"
    var patterns: MutableMap<DrumPart, List<Event>> = mutableMapOf()
    var tempo: Tempo = Tempo()

    private fun calculateDuration() : Double = tempo.bpm() / 4

    private fun patternFromEvents(part: DrumPart, events: List<Event>) {
        patterns[part] = events
    }

    private fun patternFromInt(part: String, events: List<Int>) {
        val eventList : MutableList<Event> = mutableListOf()
        val drumPart : DrumPart? = DrumPart.getBySlug(part)
        events.forEach { evt ->
            var symbol = if (evt == 1) drumPart!!.midiNote?.let { Note(it, 0) } else Rest
            eventList.add(Event(symbol as MusicSymbol, 120, this.calculateDuration()))
        }

        this.patternFromEvents(drumPart as DrumPart, eventList)
    }

    override fun toString(): String {
        var printable : MutableList<String> = mutableListOf()
        patterns.forEach { (part, events) ->
            events.forEach { evt ->
                printable.add( if (evt.symbol == Rest) "00" else evt.symbol.midiNote().toString() )
            }
            printable.add ("\n")
        }
        return printable.joinToString(" ")
    }

    fun toEventList() : List<Event> {
        val eventsSize = patterns.values.first().size
        var eventList : MutableList<Event> = MutableList(eventsSize) {
            Event(Rest, 0, this.calculateDuration())
        }
        patterns.forEach { (part, events) ->
            events.forEachIndexed { idx, evt ->
                if (evt.symbol != Rest) {
                    eventList[idx] = evt
                }
            }
        }
        return eventList
    }

    fun pattern(part: DrumPart, events: List<Event>) {
        this.patternFromEvents(part, events)
    }

    fun pattern(part: String, events: List<Int>) {
        this.patternFromInt(part, events)
    }
}

fun loop(pattern: DrumLoop.() -> Unit) : DrumLoop {
    val drumLoop = DrumLoop()
    drumLoop.pattern()
    return drumLoop
}
