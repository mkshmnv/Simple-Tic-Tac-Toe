package tictactoe


const val ROWS = 3
const val COLUMNS = 3

const val EMPTY = "_"
const val CELL_X = "X"
const val CELL_O = "O"

const val BORDER_HORIZONTAL = "---------"
const val BORDER_VERTICAL = "|"

fun main() {
    start()
}

// Begin game
fun start() {
    val input = readln()
    printField(input)
    analyzeGame(input)
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