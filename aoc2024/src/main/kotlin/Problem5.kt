package org.example

class Problem5 {
    fun solve(lines: List<String>): Int {
        val container = DependencyContainer()

        var result = 0

        for (i in 0..lines.lastIndex) {
            if (lines[i].contains('|')) {
                container.add(lines[i])
            }

            if (lines[i].contains(',')) {
                val values = lines[i].split(',').map { it.toInt() }.toIntArray()
                result += Sequence(values).getSumComponent(container)
            }
        }

        return result
    }

    data class DependencyContainer(
        val dependencyMap: MutableMap<Int, HashSet<Int>> = mutableMapOf()
    ) {
        fun add(line: String) {
            val chunks = line.split('|')
            val before = chunks[0].toInt()
            val after = chunks[1].toInt()

            if (dependencyMap.containsKey(after)) {
                dependencyMap[after]!!.add(before)
            }
            else {
                dependencyMap[after] = HashSet()
                dependencyMap[after]!!.add(before)
            }
        }

        fun isValid(map: MutableMap<Int, List<Int>>): Pair<Int, Int>? {
            for (i in map.keys) {
                if (!dependencyMap.containsKey(i)) {
                    continue
                }

                val followUpValues = map[i]
                val dependencies = dependencyMap[i]!!
                val firstBad = followUpValues!!.firstOrNull { dependencies.contains(it) }

                if (firstBad != null) {
                    return Pair(i, firstBad)
                }
            }

            return null
        }
    }

    data class Sequence(
        val values: IntArray,
        private var map: MutableMap<Int, List<Int>> = mutableMapOf()
    ) {
        init {
            buildMap()
        }

        fun buildMap() {
            val currentlyAfter = mutableListOf<Int>()
            for (i in values.lastIndex downTo 0) {
                map[values[i]] = currentlyAfter.toList()
                currentlyAfter.add(values[i])
            }
        }

        fun getSumComponent(container: DependencyContainer): Int {
            var swapPair: Pair<Int, Int>?
            var isBad = false

            do {
                swapPair = container.isValid(map)
                if (swapPair != null) {
                    isBad = true
                    val first = values.indexOfFirst { it == swapPair.first }
                    val second = values.indexOfFirst { it == swapPair.second }

                    val tmp = values[first]
                    values[first] = values[second]
                    values[second] = tmp

                    buildMap()
                }
            } while(swapPair != null)

            if (isBad) {
                return values[(values.lastIndex + 1) / 2]
            }

            return 0
        }
    }
}