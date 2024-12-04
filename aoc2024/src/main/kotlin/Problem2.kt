package org.example

import kotlin.math.abs

class Problem2 {
    fun solve(input: List<String>) = input.map { Report(it.split(" ").map(String::toInt)) }.filter { it.isValid() }.size
}

data class Report(
    val values: List<Int>
) {
    fun isValid(): Boolean {
        val (success, index) = isValid(values)
        if (success) {
            return true
        }

        if (index == 2) {
            val headlessCandidate = values.toMutableList()
            headlessCandidate.removeFirst()
            if (isValid(headlessCandidate).first) {
                return true
            }
        }

        val candidate = values.toMutableList()
        candidate.removeAt(index!!)

        if (isValid(candidate).first) {
            return true
        }

        val secondCandidate = values.toMutableList()
        secondCandidate.removeAt(index - 1)
        if (isValid(secondCandidate).first) {
            return true
        }

        return false
    }

    companion object {
        fun isValid(values: List<Int>): Pair<Boolean, Int?> {
            val isAscending = values[0] < values[1]

            for (i in 1..values.lastIndex) {
                val current = values[i]
                val prev = values[i-1]

                val delta = current - prev
                val deltaAbs = abs(delta)
                if (deltaAbs < 1 || deltaAbs > 3) {
                    return Pair(false, i)
                }

                if (isAscending) {
                    if (prev > current) {
                        return Pair(false, i)
                    }
                }
                else {
                    if (prev < current) {
                        return Pair(false, i)
                    }
                }
            }

            return Pair(true, null)
        }
    }
}