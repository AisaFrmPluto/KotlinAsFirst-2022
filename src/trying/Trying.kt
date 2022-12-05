package trying

import kotlinx.html.InputType
import lesson4.task1.squares
import kotlin.time.Duration.Companion.seconds

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

//Быстрый поиск в телефонном справочнике
/**
 * Имеется старый добрый телефон с клавиатурой вида:
 *                  ABC(2)   DEF(3)
 *       GHI(4)     JKL(5)   MNO(6)
 *       PQRS(7)    TUV(8)   WXYZ(9)
 *
 * Дан список имен в телефонном справочнике.
 * Для быстрого доступа к необходимому контакту в строке поиска
 * телефонного справочника можно вводить цифры, соответствующие
 * буквам искомого имени. Например, для поиска контакта с именем
 * Maxim необходимо ввести последовательность “62946”. Для списка
 * имен и цифровой последовательности необходимо вывести список
 * контактов, имена которых можно получить, введя в телефонный
 * справочник данную последовательность.
 *
 * Имя функции и тип результата функции предложить самостоятельно;
 * в задании указан тип Collection<Any>,
 * то есть коллекция объектов произвольного типа,
 * можно (и нужно) изменить как вид коллекции,
 * так и тип её элементов.
 *
 * Кроме функции, следует написать тесты,
 * подтверждающие её работоспособность.
 */
fun myFun(names: List<String>, digits: String): List<String> = TODO()
fun companiesByTaxes(table: Map<String, Int>, taxes: String): List<String> {
    val lines = taxes.lines()
    val map = mutableMapOf<String, Int>()
    var tax = 0
    val result = mutableListOf<String>()
    for (line in lines) {
        if (line.matches(Regex("""[\dА-Яа-я\s]+ - [А-Яа-я\s]+ - \d+"""))) {
            val parts = line.split(" - ")
            val percentage = parts[2].toInt()
            for ((name, percentageInTable) in table) {
                if (parts[1] == name) tax = percentage * percentageInTable / 100
                else continue
            }
            if (tax == 0) tax = percentage * 13 / 100
            map[parts[0]] = tax
        } else throw IllegalArgumentException()
    }
    val sorted = map.toSortedMap()
    for ((company, taxValue) in sorted)
        result += "$company - $taxValue"
    return result
}

fun taxAmount(taxes: String, money: Int): Double {
    var result = 0.0
    val string = "$taxes; "
    val oneTaxString = string.split("; ")
    val listOfValuePer = mutableListOf(Pair(0.0, 0.0))
    for (oneTax in oneTaxString) {
        val valuePer = oneTax.split(" - ")
        listOfValuePer += if (valuePer[0] == "else")
            Pair(-1.0, valuePer[1].dropLast(1).toDouble())
        else Pair(valuePer[0].dropLast(5).toDouble(), valuePer[1].dropLast(1).toDouble())
        for (i in 1 until listOfValuePer.size) {
            result += listOfValuePer[i - 1].first * listOfValuePer[i - 1].second
            +(money.toDouble() - listOfValuePer[i].first) * listOfValuePer[i].second
        }
    }
    return result
}
