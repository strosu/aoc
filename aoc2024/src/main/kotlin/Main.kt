package org.example

import java.io.File
import kotlin.system.measureTimeMillis

fun main() {
    println(Problem9().solve(File("./build/resources/main/9/sample").readText()))
    println(Problem9().solve(File("./build/resources/main/9/sample2").readText()))
    val time = measureTimeMillis {
        println(Problem9().solve(File("./build/resources/main/9/in").readText()))
        // call your function here
    }

    println("Took $time ms")
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

    println(Problem5().solve(File("./build/resources/main/5/sample").readLines()))
    println(Problem5().solve(File("./build/resources/main/5/in").readLines()))

    println(Problem6().solve2(File("./build/resources/main/6/sample").readLines()))
    println(Problem6().solve2(File("./build/resources/main/6/in").readLines()))

    println(Problem7().solve(File("./build/resources/main/7/sample").readLines()))
    println(Problem7().solve(File("./build/resources/main/7/in").readLines()))

    println(Problem8().solve(File("./build/resources/main/8/sample").readLines()))
    println(Problem8().solve(File("./build/resources/main/8/in").readLines()))
}