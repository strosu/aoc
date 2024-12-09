package org.example

class Problem7 {
    fun solve(readLines: List<String>): Any {
        var result: Long = 0
        readLines.forEach {
            val equation = Equation.fromLine(it)
            if (equation.canMatch(1, equation.factors[0].toLong())) {
                result += equation.result
            }
        }

        return result
    }

    data class Equation(
        val result: Long,
        val factors: List<Int>
    ) {
        fun canMatch(currentIndex: Int, currentValue: Long): Boolean {
            if (currentIndex == factors.size) {
                return currentValue == result
            }

            return canMatch(currentIndex + 1, currentValue + factors[currentIndex])
                    || canMatch(currentIndex + 1, currentValue * factors[currentIndex])
                    || canMatch(currentIndex + 1, combine(currentValue, factors[currentIndex]))
        }

        private fun combine(currentValue: Long, factorValue: Int) = (currentValue.toString() + factorValue.toString()).toLong()

        companion object {
            fun fromLine(line: String): Equation {
                val pieces = line.split(":")
                return Equation(pieces[0].toLong(), pieces[1].split(" ").filter { it != "" }.map { it.toInt() })
            }
        }
    }
}