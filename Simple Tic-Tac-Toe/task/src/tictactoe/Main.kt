package tictactoe

enum class Cells(private val type: Char) {
    EMPTY('_'),
    CELL_X('X'),
    CELL_O('O');
    fun getCell() = type
}

// Keep field in string
var FIELD = "_________"

fun main() {
    FIELD = readln()
    printField(FIELD)
    makeMove(readln())
}

fun makeMove(move: String) {
    var x = 0
    var y = 0

    fun turnMove(cell: Char) {

        // Check in ranges
        fun coordinates() {
            println("Coordinates should be from 1 to 3!")
            makeMove(readln())
        }

        // Change currently cell
        fun changeString(index: Int) {
            if (FIELD[index] == Cells.EMPTY.getCell()) {
                FIELD = StringBuilder(FIELD).also { it.setCharAt(index, cell) }.toString()
                printField(FIELD)
            } else {
                println("This cell is occupied! Choose another one!")
                makeMove(readln())
            }
        }

        // Implement changes cell in field
        when {
            (x !in 1..3 || y !in 1..3) -> coordinates()
            x == 1 && y == 1 -> changeString(0)
            x == 1 && y == 2 -> changeString(1)
            x == 1 && y == 3 -> changeString(2)
            x == 2 && y == 1 -> changeString(3)
            x == 2 && y == 2 -> changeString(4)
            x == 2 && y == 3 -> changeString(5)
            x == 3 && y == 1 -> changeString(6)
            x == 3 && y == 2 -> changeString(7)
            x == 3 && y == 3 -> changeString(8)
        }
    }

    // Check for digits
    if (move[0].isDigit() && move[2].isDigit()) {
        x = move[0].digitToInt()
        y = move[2].digitToInt()
        turnMove(Cells.CELL_X.getCell())
    } else {
        println("You should enter numbers!")
        makeMove(readln())
    }
}

// Print currently field
fun printField(input: String) {
    var x = input
    println("---------")
    repeat(3) {
        println("| ${x.substring(0, 3).toList().joinToString(" ")} |")
        x = x.removeRange(0, 3)
    }
    println("---------")
}