package org.example

class Problem6 {
    fun solve2(lines: List<String>): Int {
        val map = Array(lines.size) { CharArray(lines[0].length) }
        var startPoint: Point? = null

        for (i in 0..lines.lastIndex) {
            val current = lines[i].toCharArray()
            for (j in 0..current.lastIndex) {
                map[i][j] = current[j]
                if (map[i][j] == '^') {
                    startPoint = Point(i, j)
                }
            }
        }

        val tested = hashSetOf<Point>()
        val (_, candidates) = leavesMap(map, startPoint!!)
        var result = 0
        for (i in 1..candidates.lastIndex) {
            val current = candidates[i]
            if (current == startPoint || tested.contains(current)) {
                continue
            }

            tested.add(current)

            map[current.x][current.y] = 'O'
            if (!leavesMap(map, startPoint).first) {
                result++
//                map.forEach {
//                    println(it.joinToString(""))
//                }
//
//                println()
            }

            map[current.x][current.y] = '.'
        }

        return result
    }

    fun leavesMap(map: Array<CharArray>, start: Point): Pair<Boolean, List<Point>> {
        var current: Position? = Position(start, Direction(-1, 0))
        val blockPositions = HashSet<Position>()
        val points = mutableListOf<Point>()

        while (current != null && !blockPositions.contains(current)) {
            blockPositions.add(current)
            points.add(current.point)
            current = current.moveNext(map)
        }

        return Pair(current == null, points)
    }

//
//    fun solve(lines: List<String>): Int {
//        val map = Array(lines.size) { CharArray(lines[0].length) }
//        var startPoint: Point? = null
//
//        for (i in 0..lines.lastIndex) {
//            val current = lines[i].toCharArray()
//            for (j in 0..current.lastIndex) {
//                map[i][j] = current[j]
//                if (map[i][j] == '^') {
//                    startPoint = Point(i, j)
//                }
//            }
//        }
//
//        var direction = Direction(-1, 0)
//        var position: Position? = Position(startPoint!!, direction)
//
//        val seen = HashSet<Position>()
//        val blockPositions = HashSet<Point>()
//
//        while (position != null) {
//            val candidate = position.moveNext(map)
//
//            if (blockAndSolve(position, candidate?.point, seen, map)) {
//                blockPositions.add(candidate!!.point)
//            }
//
//            seen.add(position)
//            position = candidate
//        }
//
//        return blockPositions.count()
//    }
//
//    fun blockAndSolve(start: Position, toBlock: Point?, seen: HashSet<Position>, map: Array<CharArray>): Boolean {
//        if (toBlock == null) {
//            return false
//        }
//        map[toBlock.x][toBlock.y] = '#'
//
//        var current: Position? = start
//        val prevSeen = seen.toHashSet()
//        val visitList = mutableListOf<Position>()
//
//        while (current != null && !prevSeen.contains(current)) {
//            prevSeen.add(current)
//            visitList.add(current)
//            current = current.moveNext(map)
//        }
//
//        map[toBlock.x][toBlock.y] = '.'
//        return current != null
//    }

    data class Point(
        val x: Int,
        val y: Int
    ) {
        fun isValid(map: Array<CharArray>): Boolean {
            return x >= 0 && x <= map.lastIndex
                    && y >= 0 && y <= map[0].lastIndex
        }
    }

    data class Direction(
        val addX: Int,
        val addY: Int
    ) {
        fun rotate(): Direction {
            if (addX == -1) {
                return Direction(0, 1)
            }

            if (addY == 1) {
                return Direction(1, 0)
            }

            if (addX == 1) {
                return Direction(0, -1)
            }

            return Direction(-1, 0)
        }
    }

    data class Position(
        val point: Point,
        val direction: Direction
    ) {
        fun moveNext(map: Array<CharArray>): Position? {
            val candidate = Point(point.x + direction.addX, point.y + direction.addY)
            if (!candidate.isValid(map)) {
                return null // we've left the map
            }

            if (map[candidate.x][candidate.y] in listOf('#', 'O')) {
                val newDirection = direction.rotate()
                return Position(point, newDirection).moveNext(map)
            }

            return Position(candidate, direction)
        }
    }
}