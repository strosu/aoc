package org.example

import java.io.File

fun main() {
    println(Problem5().solve(File("./build/resources/main/5/sample").readLines()))
    println(Problem5().solve(File("./build/resources/main/5/in").readLines()))
}

fun solve1() {
    println(Problem1().solve(File("./build/resources/main/1/sample-in.txt").readLines()))
    println(Problem1().solve(File("./build/resources/main/1/in1").readLines()))

    println(Problem1().solve2(File("./build/resources/main/1/sample-in.txt").readLines()))
    println(Problem1().solve2(File("./build/resources/main/1/in1").readLines()))

    println(Problem2().solve(File("./build/resources/main/2/sample").readLines()))
    println(Problem2().solve(File("./build/resources/main/2/in").readLines()))

    println(Problem3().solve(File("./build/resources/main/3/sample").readText()))
    println(Problem3().solve(File("./build/resources/main/3/in").readText()))

    println(Problem3().solve2(File("./build/resources/main/3/sample2").readText()))
    println(Problem3().solve2(File("./build/resources/main/3/in").readText()))

    println(Problem4().solve2(File("./build/resources/main/4/sample").readLines()))
    println(Problem4().solve2(File("./build/resources/main/4/in").readLines()))
}