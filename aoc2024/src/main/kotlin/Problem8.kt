package org.example

import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt

class Problem8 {
    fun solve(readLines: List<String>): Any {
        val map = Array(readLines.size) { CharArray(readLines[0].length) }
        val antennas = mutableMapOf<Char, MutableList<Point>>()

        for (i in 0..readLines.lastIndex) {
            for (j in 0..readLines[i].lastIndex) {
                val currentChar = readLines[i][j]
                map[i][j] = currentChar
                if (currentChar == '.') {
                    continue
                }

                if (antennas.containsKey(currentChar)) {
                    antennas[currentChar]!!.add(Point(i, j))
                } else {
                    antennas[currentChar] = mutableListOf(Point(i, j))
                }
            }
        }

        var result = 0

        for (i in 0..map.lastIndex) {
            for (j in 0..map[i].lastIndex) {
                for (char in antennas.keys) {
                    if (Point(i, j).isNode(antennas[char]!!)) {
                        result++
                        break
                    }
                }
            }
        }

        return result
    }

}

data class Point(
    val x: Int,
    val y: Int
) {
    fun isNode(otherPoints: List<Point>): Boolean {
        for (i in 0..<otherPoints.lastIndex) {
            for (j in (i + 1)..otherPoints.lastIndex) {
                if (isAlignedWith(otherPoints[i], otherPoints[j])) {
                    return true
//                    val firstDistance = distance(otherPoints[i])
//                    val secondDistance = distance(otherPoints[j])
//                    val max = max(firstDistance, secondDistance)
//                    val min = min(firstDistance, secondDistance)
//
//                    if (max == 2 * min) {
//                        return true
//                    }
                }
            }
        }

        return false
    }

    fun distance(other: Point): Double =
        sqrt((this.x - other.x).toDouble().pow(2) + (this.y - other.y).toDouble().pow(2))

    fun isAlignedWith(first: Point, second: Point) =
        x * (first.y - second.y) + first.x * (second.y - y) + second.x * (y - first.y) == 0
}