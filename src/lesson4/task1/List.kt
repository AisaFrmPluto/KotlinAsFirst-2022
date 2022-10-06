@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import lesson3.task1.isPrime
import kotlin.math.pow
import kotlin.math.sqrt

// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.lowercase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var result = 0
    if (v.isEmpty()) return 0.0
    else for (element in v) {
        result = (result + sqr(element)).toInt()
    }
    return sqrt(result.toDouble())
}

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    var result = 0
    if (list.isEmpty()) return 0.0
    else for (element in list) {
        result = (result + element).toInt()
    }
    return result / list.size.toDouble()
}

/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val mean = mean(list)
    return if (list.isEmpty()) list
    else {
        for (i in list.indices)
            list[i] = (list[i] - mean).toInt().toDouble()
        list
    }
}

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var c = 0
    if ((a.isEmpty()) || (b.isEmpty())) return 0
    else for (i in a.indices) {
        c += a[i] * b[i]
    }
    return c
}

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var result = 0
    if (p.isEmpty()) return 0
    else for (i in p.indices) {
        result += (p[i] * ((x).toDouble()).pow(i)).toInt()
    }
    return result
}

/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    var e = 0
    if (list.isEmpty()) return list
    else for (i in 1 until list.size) {
        list[i] += list[i - 1]
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val list = mutableListOf<Int>()
    var n1 = n
    var i = 2
    return if (isPrime(n1)) list + n
    else {
        while (n1 > 1) {
            while (n1 % i == 0) {
                list.add(i)
                n1 /= i
            }
            i++
            if (n1 % i != 0) i++
        }
        return list
    }
}

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String {
    var result = ""
    var el: String
    val list = factorize(n)
    for (element in list) {
        el = element.toString()
        result = "$result$el*"
    }
    return result.dropLast(1)
}

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val list1 = mutableListOf<Int>()
    val list2 = mutableListOf<Int>()
    var n1 = n
    return if (n < base) list1 + n
    else {
        while (n1 / base > 0) {
            list1.add(n1 % base)
            n1 /= base
        }
        list1.add(n1 % base)
        for (i in list1.size - 1 downTo 0) {
            list2.add(list1[i])
        }
        list2
    }
}

/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String = TODO()

/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var b = base
    var result = digits[digits.size - 1]
    return if (digits.size == 1) digits[0]
    else {
        for (i in digits.size - 2 downTo 0) {
            result += b * digits[i]
            b *= base
        }
        return result
    }
}

/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int = TODO()

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var result = ""
    val th = listOf("", "M", "MM", "MMM", "MMMM", "MMMMM", "MMMMMM", "MMMMMMM")
    val hun = listOf("", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM")
    val te = listOf("", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC")
    val on = listOf("", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX")
    val thousands = th[n / 1000]
    val hundreds = hun[n % 1000 / 100]
    val tens = te[n % 100 / 10]
    val ones = on[n % 10]
    result = result + thousands + hundreds + tens + ones

    return result
}

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val n1 = listOf("", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять")
    val n2 = listOf(
        "", "десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать", "шестнадцать",
        "семнадцать", "восемнадцать", "девятнадцать"
    )
    val n3 = listOf(
        "", "десять", "двадцать", "тридцать", "сорок", "пятьдесят",
        "шестьдесят", "семьдесят", "восемьдесят", "девяносто"
    )
    val n4 =
        listOf("", "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот")
    val n5 = listOf(
        "", "тысяча", "две тысячи", "три тысячи", "четыре тысячи", "пять тысяч",
        "шесть тысяч", "семь тысяч", "восемь тысяч", "девять тысяч"
    )
    val n6 = listOf(
        "", "десять тысяч", "одиннадцать тысяч", "двенадцать тысяч", "тринадцать тысяч", "четырнадцать тысяч",
        "пятнадцать тысяч", "шестнадцать тысяч", "семнадцать тысяч", "восемнадцать тысяч", "девятнадцать тысяч"
    )
    val n7 = listOf(
        "", "десять тысяч", "двадцать тысяч", "тридцать тысяч", "сорок тысяч", "пятьдесят тысяч", "шестьдесят тысяч",
        "семьдесят тысяч", "восемьдесят тысяч", "девяносто тысяч"
    )
    val n8 = listOf(
        "", "сто тысяч", "двести тысяч", "триста тысяч", "четыреста тысяч", "пятьсот тысяч", "шестьсот тысяч",
        "семьсот тысяч", "восемьсот тысяч", "девять сто тысяч"
    )
    val onestensthousands = n6[(n / 1000) - (n / 100000)]
    val hundredthousands = n8[n / 100000]
    val tenthousands = n7[(n / 1000) / 10]
    val thousands = n5[(n / 1000) % 10]
    val hundreds = n4[(n % 1000) / 100]
    val tens = n3[(n % 100) / 10]
    val ones = n1[n % 10]
    val onestens = n2[n % 10]
    return if (n < 9999 && n % 100 !in 11..19)
        "$thousands$hundreds $tens $ones"
    else if (n < 9999 && n % 100 in 11..19)
        "$thousands$hundreds $onestens"
    else if ((n % 100 !in 11..19) && (((n / 1000) - (n / 100000)) !in 11..19))
        "$hundredthousands$tenthousands $thousands $hundreds $tens $ones"
    else if ((n % 100 !in 11..19) && ((n / 1000) - (n / 100000) in 11..19))
        "$hundredthousands$onestensthousands $hundreds $tens $ones"
    else if ((n % 100 in 11..19) && ((n / 1000) - (n / 100000) !in 11..19))
        "$hundredthousands$tenthousands $thousands $hundreds $onestens"
    else "$hundredthousands$onestensthousands $hundreds $onestens"
}