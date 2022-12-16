package tictactoe


const val ROWS = 3
const val COLUMNS = 3

const val EMPTY = "_"
const val CELL_X = "X"
const val CELL_O = "O"

const val BORDER_HORIZONTAL = "---------"
const val BORDER_VERTICAL = "|"

var FIELD = ""
var MOVE = ""

fun main() {
    FIELD = readln()
    start(FIELD)
}

// Begin game
fun start(field: String) {
    while (FIELD.contains(EMPTY)) {
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
            (x == 1 && y == 1) -> if (field[0] != EMPTY) field[0] = type else println(error)
            (x == 1 && y == 2) -> if (field[1] != EMPTY) field[1] = type else println(error)
            (x == 1 && y == 3) -> if (field[2] != EMPTY) field[2] = type else println(error)
            (x == 2 && y == 1) -> if (field[3] != EMPTY) field[3] = type else println(error)
            (x == 2 && y == 2) -> if (field[4] != EMPTY) field[4] = type else println(error)
            (x == 2 && y == 3) -> if (field[5] != EMPTY) field[5] = type else println(error)
            (x == 3 && y == 1) -> if (field[6] != EMPTY) field[6] = type else println(error)
            (x == 3 && y == 2) -> if (field[7] != EMPTY) field[7] = type else println(error)
            (x == 3 && y == 3) -> if (field[8] != EMPTY) field[8] = type else println(error)
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
        val qtyX = input.filter { it.toString() == CELL_X }.length
        val qtyO = input.filter { it.toString() == CELL_O }.length
        return qtyX - qtyO in 0..1 || qtyO - qtyX in 0..1
    }

    println(
        when {
//            Grid has three X’s in a row as well as three O’s in a row
//            Or there are a lot more X's than O's or vice versa
//            (the difference should be 1 or 0; if the difference is 2 or more, then the game state is impossible).
            win(CELL_X) && win(CELL_O) || !checkQty() -> "Impossible"

//            Grid has three X’s in a row (including diagonals)
            win(CELL_X) -> "X wins"

//            Grid has three O’s in a row (including diagonals)
            win(CELL_O) -> "O wins"

//            Side has a three in a row and the grid has no empty cells
            !input.contains(EMPTY) -> "Draw"

//            Side has three in a row but the grid still has empty cells
            input.contains(EMPTY) -> "Game not finished"
            else -> "Error"
        }
    )
}

fun printField(input: String) {
    var x = input
    println(BORDER_HORIZONTAL)
    repeat(ROWS) {
        print("$BORDER_VERTICAL ${x.substring(0, COLUMNS).toList().joinToString(" ")} $BORDER_VERTICAL\n")
        x = x.removeRange(0, COLUMNS)
    }
    println(BORDER_HORIZONTAL)
}