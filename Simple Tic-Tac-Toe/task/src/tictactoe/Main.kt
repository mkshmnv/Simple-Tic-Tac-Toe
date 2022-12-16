package tictactoe

// Enum with game marks
enum class Cells(private val type: Char) {
    EMPTY('_'),
    CELL_X('X'),
    CELL_O('O');

    fun getCell() = type
}

// Global const
var FIELD = "_________" // Keep field in string
var PLAYER = Cells.CELL_X.getCell() // Player in game
var END_GAME = false // Status of game

fun main() {
    while (!END_GAME) {
        printField()
        makeMove()
    }
}

// Switch currently player - X or O, start X
fun nextPlayer() {
    PLAYER = if (PLAYER == Cells.CELL_X.getCell()) {
        Cells.CELL_O.getCell()
    } else {
        Cells.CELL_X.getCell()
    }
}

fun makeMove() {
    // Check game over?
    if (END_GAME) return

    // Input player turn
    val move = readln()
    var x = 0
    var y = 0

    fun turnMove(cell: Char) {
        // Check turn in range
        fun coordinates() {
            println("Coordinates should be from 1 to 3!")
            makeMove()
        }

        // Change currently cell, pick X or O
        fun changeString(index: Int) {
            if (FIELD[index] == Cells.EMPTY.getCell()) {
                FIELD = StringBuilder(FIELD).also { it.setCharAt(index, cell) }.toString()
                printField()
            } else {
                println("This cell is occupied! Choose another one!")
                if (!END_GAME) {
                    makeMove()
                }
            }
        }

        // Implement changes cell in field
        when {
            x !in 1..3 || y !in 1..3 -> coordinates()
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
        turnMove(PLAYER)
    } else {
        println("You should enter numbers!")
        makeMove()
    }

    analyzeGame()
    nextPlayer()
}

fun analyzeGame() {

    // Checking winner X or O
    fun win(c: Char): Boolean {
        return when ("$c$c$c") {
            // check winner first row
            FIELD.substring(0, 3) -> true
            // check winner second row
            FIELD.substring(3, 6) -> true
            // check winner third row
            FIELD.substring(6, 9) -> true
            // X12X45X78 - check winner first column
            "${FIELD[0]}${FIELD[3]}${FIELD[6]}" -> true
            // 01X34X67X - check winner second column
            "${FIELD[1]}${FIELD[4]}${FIELD[7]}" -> true
            // 01X34X67X - check winner third column
            "${FIELD[2]}${FIELD[5]}${FIELD[8]}" -> true
            // 01X3X5X78 - check winner first diagonal row
            "${FIELD[2]}${FIELD[4]}${FIELD[6]}" -> true
            // X123X567X - check winner second diagonal row
            "${FIELD[0]}${FIELD[4]}${FIELD[8]}" -> true
            else -> false
        }
    }

    // Switch state ending game
    fun endGame(result: String) {
        println(result)
        END_GAME = true
    }

    // Check result game
    when {
        win(Cells.CELL_X.getCell()) -> endGame("X wins")
        win(Cells.CELL_O.getCell()) -> endGame("O wins")
        !FIELD.contains(Cells.EMPTY.getCell()) -> endGame("Draw")
    }

    // Repeat if game go on
    makeMove()
}

// Print currently field
fun printField() {
    var tempField = FIELD
    println("---------")
    repeat(3) {
        println("| ${tempField.substring(0, 3).toList().joinToString(" ")} |")
        tempField = tempField.removeRange(0, 3)
    }
    println("---------")
}