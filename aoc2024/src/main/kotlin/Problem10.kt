package org.example

class Problem10 {
    fun solve(lines: List<String>): Int {
        val map = Array(lines.size) { Array(lines[0].length) { PointAndValue(Point(0, 0), 0)} }
        val candidates = mutableListOf<PointAndValue>()

        for (i in 0..lines.lastIndex) {
            for (j in 0..lines[i].lastIndex) {
                map[i][j] = PointAndValue(Point(i, j), lines[i][j] - '0')
                if (map[i][j].value == 0) {
                    candidates.add(map[i][j])
                }
            }
        }

        return candidates.sumOf { countRecursively(it, hashSetOf(), map) }
    }

    fun countRecursively(current: PointAndValue, seen: HashSet<Point>, map: Array<Array<PointAndValue>>): Int {
        if (current.value == 9) {
//            if (seen.contains(current.point)) {
//                return 0
//            }
//
//            seen.add(current.point)
            return 1
        }

        val nextCandidates = current.point.getNeighbors(map)
            .filter { map[it.x][it.y].value == current.value + 1 }

        var result = 0
        for (nextPoint in nextCandidates) {
            result += countRecursively(map[nextPoint.x][nextPoint.y], seen, map)
        }

        return result
    }

    data class PointAndValue(
        val point: Point,
        val value: Int
    )

    data class Point(
        val x: Int,
        val y: Int
    ) {
        fun isValid(map: Array<Array<PointAndValue>>): Boolean {
            return x >= 0 && x < map.size && y >= 0 && y < map[0].size
        }

        fun getNeighbors(map: Array<Array<PointAndValue>>): List<Point> {
            return listOf(
                Point(x - 1, y),
                Point(x + 1, y),
                Point(x, y - 1),
                Point(x, y + 1)
            ).filter { it.isValid(map) }
        }
    }
}