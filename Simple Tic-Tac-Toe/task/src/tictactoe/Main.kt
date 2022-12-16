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

fun analyzeGame(input: String) {

    // Checking winner X or O
    fun win(c: Char): Boolean {
        return when ("$c$c$c") {
            // check winner first row
            input.substring(0, 3) -> true
            // check winner second row
            input.substring(3, 6) -> true
            // check winner third row
            input.substring(6, 9) -> true
            // X12X45X78 - check winner first column
            "${input[0]}${input[3]}${input[6]}" -> true
            // 01X34X67X - check winner second column
            "${input[1]}${input[4]}${input[7]}" -> true
            // 01X34X67X - check winner third column
            "${input[2]}${input[5]}${input[8]}" -> true
            // 01X3X5X78 - check winner first diagonal row
            "${input[2]}${input[4]}${input[6]}" -> true
            // X123X567X - check winner second diagonal row
            "${input[0]}${input[4]}${input[8]}" -> true
            else -> false
        }
    }

    // Checking quantity chars on input string
    fun checkQty(): Boolean {
        val qtyX = input.filter { it == Cells.CELL_X.getCell() }.length
        val qtyO = input.filter { it == Cells.CELL_O.getCell() }.length
        return qtyX - qtyO in 0..1 || qtyO - qtyX in 0..1
    }

    println(
        when {
//            Grid has three X’s in a row as well as three O’s in a row
//            Or there are a lot more X's than O's or vice versa
//            (the difference should be 1 or 0; if the difference is 2 or more, then the game state is impossible).
            win(Cells.CELL_X.getCell()) && win(Cells.CELL_O.getCell()) || !checkQty() -> "Impossible"

//            Grid has three X’s in a row (including diagonals)
            win(Cells.CELL_X.getCell()) -> "X wins"

//            Grid has three O’s in a row (including diagonals)
            win(Cells.CELL_O.getCell()) -> "O wins"

//            Side has a three in a row and the grid has no empty cells
            !input.contains(Cells.EMPTY.getCell()) -> "Draw"

//            Side has three in a row but the grid still has empty cells
            input.contains(Cells.EMPTY.getCell()) -> "Game not finished"
            else -> "Error"
        }
    )
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