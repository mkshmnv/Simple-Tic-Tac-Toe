package tictactoe


const val ROWS = 3
const val COLUMNS = 3

enum class Cells(private val type: String) {
    EMPTY("_"),
    CELL_X("X"),
    CELL_O("O");
    fun getCell() = type
}

//const val EMPTY = "_"
//const val CELL_X = "X"
//const val CELL_O = "O"

var FIELD = ""
var MOVE = ""

fun main() {
    FIELD = readln()
    start(FIELD)
}

// Begin game
fun start(field: String) {
    while (FIELD.contains(Cells.EMPTY.getCell())) {
        printField(field)
//        analyzeGame(field)
        makeMove(readln())
    }
}

fun makeMove(move: String) {

    val x = move[0].digitToInt()
    val y = move[2].digitToInt()

    val error = "This cell is occupied! Choose another one!"

    val field = FIELD.split(" ").toMutableList()

    fun turnMove(type: String) {
        when {
            (move.toIntOrNull() == null) -> println("You should enter numbers!")
            (x == 1 && y == 1) -> if (field[0] != Cells.EMPTY.getCell()) field[0] = type else println(error)
            (x == 1 && y == 2) -> if (field[1] != Cells.EMPTY.getCell()) field[1] = type else println(error)
            (x == 1 && y == 3) -> if (field[2] != Cells.EMPTY.getCell()) field[2] = type else println(error)
            (x == 2 && y == 1) -> if (field[3] != Cells.EMPTY.getCell()) field[3] = type else println(error)
            (x == 2 && y == 2) -> if (field[4] != Cells.EMPTY.getCell()) field[4] = type else println(error)
            (x == 2 && y == 3) -> if (field[5] != Cells.EMPTY.getCell()) field[5] = type else println(error)
            (x == 3 && y == 1) -> if (field[6] != Cells.EMPTY.getCell()) field[6] = type else println(error)
            (x == 3 && y == 2) -> if (field[7] != Cells.EMPTY.getCell()) field[7] = type else println(error)
            (x == 3 && y == 3) -> if (field[8] != Cells.EMPTY.getCell()) field[8] = type else println(error)
        }
    }
//    (1, 1) (1, 2) (1, 3)
//    (2, 1) (2, 2) (2, 3)
//    (3, 1) (3, 2) (3, 3)
}

// Analyze game state and print it
fun analyzeGame(input: String) {

    // Checking winner X or O
    fun win(c: String): Boolean {
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
        val qtyX = input.filter { it.toString() == Cells.CELL_X.getCell() }.length
        val qtyO = input.filter { it.toString() == Cells.CELL_O.getCell() }.length
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

fun printField(input: String) {
    var x = input
    println("---------")
    repeat(ROWS) {
        print("| ${x.substring(0, COLUMNS).toList().joinToString(" ")} |")
        x = x.removeRange(0, COLUMNS)
    }
    println("---------")
}