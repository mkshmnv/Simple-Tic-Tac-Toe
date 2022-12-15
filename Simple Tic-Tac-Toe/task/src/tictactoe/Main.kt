package tictactoe

const val ROWS = 3
const val COLUMNS = 2
const val EMPTY = "_"
const val CELL_X = "X"
const val CELL_O = "O"

const val BORDER_HORIZONTAL = "---------"
const val BORDER_VERTICAL = "|"


fun main() {
    printFieldTwo(readln())
}

fun printFieldTwo(input: String) {
    var x = input
    println(BORDER_HORIZONTAL)
    repeat(ROWS) {
        print("$BORDER_VERTICAL ${x.substring(0, COLUMNS + 1).toList().joinToString(" ")} $BORDER_VERTICAL\n")
        x = x.removeRange(0, COLUMNS + 1)
    }
    println(BORDER_HORIZONTAL)
}