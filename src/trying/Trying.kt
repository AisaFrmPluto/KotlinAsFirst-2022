package trying

import kotlinx.html.InputType

fun binarySearch(list: MutableList<Int>, el: Int): Boolean {
    val sorted = list.sorted().toMutableList()
    var check = 0
    while (sorted.size != 1) {
        if (sorted[sorted.size / 2] > el) {
            for (i in sorted.size / 2 until sorted.size)
                sorted -= i
        } else if (sorted[sorted.size / 2] < el) {
            for (i in sorted.size / 2 downTo 0)
                sorted -= i
        }
    }
    if (sorted[0] == el) check = 1
    return check == 1
}

fun rollDice(range: IntRange, times: Int, callback:(result: Int) -> Unit) {
    for (i in 0 until times) {
        val result = range.random()
        callback(result)
    }
}

fun main() {
    rollDice(range = 1..6, times = 4) { result ->
        println(result)
    }
}