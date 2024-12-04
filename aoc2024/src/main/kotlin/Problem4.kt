package org.example

class Problem4 {
    private val letters = listOf('X', 'M', 'A', 'S')
    private val directions = listOf(
        Direction(-1, -1),
        Direction(-1, 0),
        Direction(-1, 1),
        Direction(0, -1),
        Direction(0, 1),
        Direction(1, -1),
        Direction(1, 0),
        Direction(1, 1))

    fun solve2(lines: List<String>): Int {
        val map = Array(lines.size) { CharArray(lines[0].length) }
        for (i in lines.indices) {
            map[i] = lines[i].toCharArray()
        }

        var result = 0

        for (i in 0..lines.lastIndex) {
            for (j in 0..lines[0].lastIndex) {
                if (isCenter(map, Point(i, j))) {
                    result++
                }
            }
        }

        return result
    }

    private fun isCenter(map: Array<CharArray>, point: Point): Boolean {
        val current = map[point.x][point.y]
        if (current != 'A') {
            return false
        }

        val firstDiag = listOf(Point(point.x - 1, point.y - 1), Point(point.x + 1, point.y + 1))
        val secondDiag = listOf(Point(point.x - 1, point.y + 1), Point(point.x + 1, point.y - 1))

        return checkValid(firstDiag, map) && checkValid(secondDiag, map)
    }

    private fun checkValid(pointList: List<Point>, map: Array<CharArray>): Boolean {
        if (!pointList.all { it.isValid(map) }) {
            return false
        }

        val points = listOf(map[pointList[0].x][pointList[0].y], map[pointList[1].x][pointList[1].y]).sorted()

        return points[0] == 'M' && points[1] == 'S'
    }

    fun solve(lines: List<String>): Int {
        val map = Array(lines.size) { CharArray(lines[0].length) }
        for (i in lines.indices) {
            map[i] = lines[i].toCharArray()
        }

        var result = 0

        for (i in 0..lines.lastIndex) {
            for (j in 0..lines[i].lastIndex) {
                if (map[i][j] == 'X') {
                    for (direction in directions) {
                        if (tryParse(map, Point(i, j), direction, 0)) {
                            result++
                        }
                    }
                }
            }
        }

        return result
    }

    fun tryParse(map: Array<CharArray>, startPoint: Point, direction: Direction, index: Int): Boolean {
        if (index == 4) {
            return true
        }

        if (!startPoint.isValid(map)) {
            return false
        }

        if (map[startPoint.x][startPoint.y] != letters[index]) {
            return false
        }

        return tryParse(map, startPoint.apply(direction), direction, index + 1)
    }

    data class Point(
        val x: Int,
        val y: Int
    ) {
        fun isValid(map: Array<CharArray>): Boolean {
            return x >= 0 && x <= map[0].lastIndex
                    && y >= 0 && y <= map.lastIndex
        }

        fun apply(direction: Direction) = Point(x + direction.addX, y + direction.addY)
    }

    data class Direction(
        val addX: Int,
        val addY: Int
    )
}