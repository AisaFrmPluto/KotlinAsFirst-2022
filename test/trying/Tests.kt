package trying

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

class Tests {
    @Test
    fun binarySearch() {
        assertTrue(binarySearch(mutableListOf(1, 2, 3, 7, 8), 7))
    }
}