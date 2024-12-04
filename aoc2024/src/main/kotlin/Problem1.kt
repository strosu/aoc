package org.example

import kotlin.math.abs

class Problem1 {
    fun solve2(lines: List<String>): Int {
        var (first, second) = extract(lines)
        val map = getMap(first)

        var result = 0
        second.forEach { value ->
            map[value]?.let {
                result += value * it
            }
        }

        return result
    }

    fun getMap(input: List<Int>): MutableMap<Int, Int> {
        val result = mutableMapOf<Int, Int>()
        input.forEach {
            if (result.containsKey(it)) {
                result[it] = result[it]!! + 1
            }
            else {
                result[it] = 1
            }
        }

        return result
    }

    fun solve(lines: List<String>): Int {
        var (first, second) = extract(lines)

        var result = 0
        val firstSorted = first.sorted()
        val secondSorted = second.sorted()

        for (i in 0..firstSorted.lastIndex) {
            result += abs(firstSorted[i] - secondSorted[i])
        }

        return result
    }

    private fun extract(lines: List<String>): Pair<List<Int>, List<Int>> {
        var first = mutableListOf<Int>()
        var second = mutableListOf<Int>()

        lines.forEach { line ->
            val tmp = line.split(" ").filter { it.isNotEmpty() }
            first.add(tmp[0].toInt())
            second.add(tmp[1].toInt())
        }
        return Pair(first, second)
    }
}