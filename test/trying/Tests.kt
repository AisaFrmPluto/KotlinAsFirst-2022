package trying

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Tests {
    @Test
    fun binarySearch() {
        assertTrue(binarySearch(mutableListOf(1, 2, 3, 7, 8), 7))
    }

    @Test
    fun companiesByTaxes() {
        assertEquals(
            mutableListOf("Политек Ведра - 1170000", "Вбербанк - 17100", "ООО Горняк - 12000"),
            companiesByTaxes(
                table =
                mapOf(
                    "производство напитков" to 4,
                    "горнодобывающая промышленность" to 12,
                    "банковские операции" to 9
                ),
                "ООО Горняк - горнодобывающая промышленность - 100000\nВбербанк - банковские операции - 190000\nПолитек Ведра - образование - 9000000"
            )
        )
    }

    @Test
    fun taxAmount() {
        assertEquals(
            13000.0,
            taxAmount(
                taxes =
                "20000 y.e. - 0%; 40000 y.e. - 5%; 60000 y.e. - 10%; else - 25%", 100000
            )
        )
    }
}

