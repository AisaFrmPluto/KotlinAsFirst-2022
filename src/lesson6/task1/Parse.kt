@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import lesson2.task2.daysInMonth

// Урок 6: разбор строк, исключения
// Максимальное количество баллов = 13
// Рекомендуемое количество баллов = 11
// Вместе с предыдущими уроками (пять лучших, 2-6) = 40/54

/**
 * Пример  d
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main() {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя (4 балла)
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun conditionForDate(str: String): Boolean {
    var parts = str.split(" ")
    if ("." in str) parts = str.split(".")
    val jj = parts[0]
    val mm = parts[1]
    val yyyy = parts[2]
    val days: Int
    if (mm.toInt() in 1..12) days = daysInMonth(parts[1].toInt(), yyyy.toInt())
    else {
        val months = mapOf<String, String>(
            "января" to "01", "февраля" to "02", "марта" to "03", "апреля" to "04", "мая" to "05", "июня" to "06",
            "июля" to "07", "августа" to "08", "сентября" to "09", "октября" to "10", "ноября" to "11",
            "декабря" to "12"
        )
        days = months[mm]?.let { daysInMonth(it.toInt(), yyyy.toInt()) }!!
    }
    return (jj.toInt() > days) || (jj.toInt() < 1) || (yyyy.toInt() < 1)
}

/**
 * Средняя (4 балла)
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun dateStrToDigit(str: String): String {
    if (str.matches(Regex("""\d{1,2} [а-я]{3,8} \d+"""))) {
        val parts = str.split(" ")
        var jj = parts[0]
        var mm: String = parts[1]
        val y = parts[2]
        val months = mapOf<String, String>(
            "января" to "01", "февраля" to "02", "марта" to "03", "апреля" to "04", "мая" to "05", "июня" to "06",
            "июля" to "07", "августа" to "08", "сентября" to "09", "октября" to "10", "ноября" to "11",
            "декабря" to "12"
        )
        if ((jj.toInt() < 10) && jj.length == 1)
            jj = "0$jj"
        if (mm in months)
            mm = months[mm].toString()
        else return ""
        val days = daysInMonth(mm.toInt(), y.toInt())
        return if ((jj.toInt() > days) || (jj.toInt() < 1) || (y.toInt() < 1))
            ""
        else String.format("%s.%s.%s", jj, mm, y)
    } else return ""
}

/**
 * Средняя (4 балла)
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    if (digital.matches(Regex("""\d+\.\d+\.\d+"""))) {
        val parts = digital.split(".")
        var jj = parts[0]
        var mm: String = parts[1]
        val yyyy = parts[2]
        val months = listOf<String>(
            "января", "февраля", "марта", "апреля",
            "мая", "июня", "июля", "августа", "сентября",
            "октября", "ноября", "декабря"
        )
        if (jj.toInt() < 10)
            jj = jj.toInt().toString()
        if (mm.toInt() in 1..12) mm = months[mm.toInt() - 1]
        else return ""
        val days = daysInMonth(parts[1].toInt(), yyyy.toInt())
        return if (conditionForDate(digital) || (mm in digital))
            ""
        else String.format("%s %s %s", jj, mm, yyyy)
    } else return ""
}

/**
 * Средняя (4 балла)
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку.
 *
 * PS: Дополнительные примеры работы функции можно посмотреть в соответствующих тестах.
 */
fun flattenPhoneNumber(phone: String): String {
    if (phone.matches(Regex("""[^\d\s\-+\d()]"""))) return ""
    if (!phone.matches(Regex("""(\+?[\d\- ])*(\([\d\- ]+\))?[\d\- ]*"""))) return ""
    return phone.filter { it !in " " && it !in "(" && it !in ")" && it !in "-" }
}

/**
 * Средняя (5 баллов)
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    val parts = jumps.split(" ")
    val list = mutableListOf<Int>()
    return if (jumps.contains(Regex("""[^\d\s\-%]""")) ||
        jumps.contains(Regex("""([%\-])(%|-|\d)|(%|-|\d)([%\-])""")) || !jumps.contains(Regex("""\d"""))
    )
        -1
    else {
        for (part in parts) {
            if (part != "-" && part != "%")
                list += part.toInt()
        }
        list.max()
    }
}

/**
 * Сложная (6 баллов)
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки, а также в случае отсутствия удачных попыток,
 * вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    var result = -1
    val parts = jumps.split(" ") as MutableList<String>
    val list = mutableListOf<String>()
    return if (
        jumps.contains(Regex("""[^\d\s\-%+]""")) ||
        jumps.contains(Regex("""(([%\-+])(\d))""")) ||
        jumps.contains(Regex("""((\d)([%\-+]))""")) ||
        !jumps.matches(Regex("""\d+ [-%+]+( \d+ [-%+]+)*"""))
    ) -1
    else {
        for (i in 0 until parts.size - 1 step 2) {
            if (parts[i + 1] == "+")
                list += parts[i]
        }
        if (list.size == 1) {
            result = list[0].toInt()
        } else {
            for (i in 0 until list.size - 1)
                result = if (list[i + 1].toInt() > list[i].toInt())
                    list[i + 1].toInt()
                else list[i].toInt()
        }
        result
    }
}

/**
 * Сложная (6 баллов)
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    require("$expression + ".matches(Regex("""(\d+ [+-] )+""")))
    val parts = expression.split(" ") as MutableList<String>
    val numbers = mutableListOf<Int>()
    val signs = mutableListOf<String>()
    for (i in 0 until parts.size step 2)
        numbers += parts[i].toInt()
    for (i in 1 until parts.size step 2)
        signs += parts[i]
    var result = numbers[0]
    for (i in 0 until signs.size) {
        if (signs[i] == "+") result += numbers[i + 1]
        else result -= numbers[i + 1]
    }
    return result
}

/**
 * Сложная (6 баллов)
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    val parts = str.split(" ")
    var result = 0
    for (i in parts.indices) {
        if (i + 1 < parts.size && parts[i].equals(parts[i + 1], ignoreCase = true)) {
            return result
        }
        result += parts[i].length + 1
    }
    return -1
}

/**
 * Сложная (6 баллов)
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше нуля либо равны нулю.
 */
fun mostExpensive(description: String): String {
    val des = "$description;"
    val parts = des.split(" ") as MutableList<String>
    var result = ""
    return if (!description.matches(Regex("""(\S+ \d+\.?\d*; )*\S+ \d+\.\d+""")) &&
        !description.matches(Regex("""(\S+ \d+\.?\d*; )*\S+ \d+"""))
    ) ""
    else if (parts.size == 2) parts[0]
    else {
        val p1 = parts[1].dropLast(1).toDouble()
        for (i in 1 until parts.size - 2 step 2) {
            result = if ((parts[i + 2].dropLast(1)).toDouble() > (parts[i].dropLast(1)).toDouble()) parts[i + 1]
            else if ((parts[i + 2].dropLast(1)).toDouble() == (parts[i].dropLast(1)).toDouble()) "Any good with price $p1"
            else parts[i - 1]
        }
        return result
    }
}

/**
 * Сложная (6 баллов)
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int = TODO()

/**
 * Очень сложная (7 баллов)
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> = TODO()

/**
 * тест на автомат
 */
fun freePlaces(
    places: MutableList<MutableList<Boolean>>,
    requests: Map<String, Pair<Int, Int>>
): MutableMap<String, Array<Int>> {
    val changedPlaces = mutableListOf<MutableList<Boolean>>()
    val peopleAndPlaces = mutableMapOf<String, Array<Int>>()
    for ((key, value) in requests) {
        if (key.matches(Regex("""\w+"""))) {
            var reservedPlaces = arrayOf<Int>()
            var check = 0
            for (j in 0 until places[value.first].size) {
                if (!places[value.first][j]) check++
            }
            if (value.second < check) {
                for (i in 0 until places[value.first].size) {
                    var neededPlaces = value.second
                    while (neededPlaces > 0) {
                        if (places[value.first][i]) {
                            changedPlaces[i] += (true)
                        } else {
                            changedPlaces[i] += (true)
                            reservedPlaces += i
                            neededPlaces--
                        }
                    }
                }
                peopleAndPlaces[key] = reservedPlaces
            } else {
                throw IllegalStateException()
            }
        } else {
            throw IllegalStateException()
        }
    }
    return peopleAndPlaces
}




