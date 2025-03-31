package com.dagteam.ideatest

fun main() {
    val list = intArrayOf(1,3,-1,-3,5,3,6,7)
    println(maxSlidingWindow(list, 3))
}

fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
    val result = arrayListOf<Int>()
    val queue = ArrayDeque(nums.take(k))
    result.add(queue.max())
    queue.removeFirst()
    for(r in k until nums.size) {
        println(nums[r])
        queue.add(nums[r])
        result.add(queue.max())
        queue.removeFirst()
    }
    return result.toIntArray()
}
