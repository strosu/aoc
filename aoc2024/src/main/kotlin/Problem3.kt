package org.example

class Problem3 {
    private val regex: Regex = "mul\\(([0-9]+),([0-9]+)\\)".toRegex()
    private val regex2: Regex = "(mul\\(([0-9]+),([0-9]+)\\))|(do\\(\\))|(don't\\(\\))".toRegex()

    fun solve(input: String): Int {
        var result = 0

        val matches = regex.findAll(input)
        matches.forEach {
            val groups = it.groups
            result += groups[1]!!.value.toInt() * groups[2]!!.value.toInt()
        }

        return result
    }

    fun solve2(input: String): Int {
        var result = 0
        var enabled = true
        val matches = regex2.findAll(input)
        for (match in matches) {
            val groups = match.groups
            if (groups[0]!!.value == "do()") {
                enabled = true
                continue
            }

            if (groups[0]!!.value == "don't()") {
                enabled = false
                continue
            }

            if (!enabled) {
                continue
            }

            result += groups[2]!!.value.toInt() * groups[3]!!.value.toInt()
        }

        return result
    }
}