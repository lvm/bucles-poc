package bucles

interface MusicSymbol {
    fun midiNote() : Int
}

object Rest : MusicSymbol {
    override fun midiNote() : Int = -1
}

open class Note(note: Int = 0, octave: Int = 5) : MusicSymbol {
    var pitch : Int = 60

    init {
        pitch = if (octave > 1) note * octave else note
    }

    override fun midiNote(): Int = pitch
}

open class Chord(val notes: List<Note>)


// drum parts
object kick : Note(36, 0)
object snare : Note(38, 0)
object hihat : Note(42, 0)
object openhat : Note(46,0)

// notes
object C5 : Note(0)
object Cs5 : Note(1)
object Df5 : Note(1)
object D5 : Note(2)
object Ds5 : Note(3)
object Ef5 : Note(3)
object E5 : Note(4)
object Es5 : Note(5)
object F5 : Note(5)
object Fs5 : Note(6)
object G5 : Note(7)
object Gs5 : Note(8)
object A5 : Note(9)
object As5 : Note(10)
object B5 : Note(11)
object Bs5 : Note(12)
// an octave up
object C6 : Note(0, 6)
object Cs6 : Note(1, 6)
object Df6 : Note(1, 6)
object D6 : Note(2, 6)
object Ds6 : Note(3, 6)
object Ef6 : Note(3, 6)
object E6 : Note(4, 6)
object Es6 : Note(5, 6)
object F6 : Note(5, 6)
object Fs6 : Note(6, 6)
object G6 : Note(7, 6)
object Gs6 : Note(8, 6)
object A6 : Note(9, 6)
object As6 : Note(10, 6)
object B6 : Note(11, 6)
object Bs6 : Note(12, 6)

// chords
object Cmajor : Chord(listOf(C5, E5, G5))
object Dmajor : Chord(listOf(D5, Fs5, A5))
object Emajor : Chord(listOf(E5, A5, C6))
object Gmajor : Chord(listOf(G5, B5, D6))
object Amajor : Chord(listOf(A5, Cs6, E6))
object Bmajor : Chord(listOf(B5, Ds6, Fs6))

