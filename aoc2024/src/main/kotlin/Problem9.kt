package org.example

class Problem9 {
    fun solve(readText: String): Long {
        val blockList = mutableListOf<Block>()
        val files = mutableListOf<File>()
        val spaces = mutableListOf<Space>()

        var currentId = -1

        for (i in 0..readText.lastIndex) {
            val blockCount = readText[i] - '0'

            val isEmpty = i % 2 == 1
            val block = if (isEmpty) {
                if (blockCount > 0) {
                    spaces.add(Space(blockList.size, blockCount))
                }

                Block.Empty
            }
            else {
                currentId++
                files.add(File(currentId, blockList.size, blockCount))
                Block(currentId, false, blockCount)
            }

            repeat((0..<blockCount).count()) {
                blockList.add(block)
            }
        }


        for (i in files.lastIndex downTo 0) {
            val currentFile = files[i]
            val matchingSpace = spaces.firstOrNull { it.size >= currentFile.size && it.start < currentFile.start }

            if (matchingSpace != null) {
                swap2(currentFile, matchingSpace, blockList)
            }
        }

        var result: Long = 0
        for (i in 0..blockList.lastIndex) {
            if (blockList[i].isEmpty) {
                continue
            }

            result += i * blockList[i].id!!
        }

        return result
    }

    private fun swap2(currentFile: File, matchingSpace: Space, blockList: MutableList<Block>) {
        for (i in 0..<currentFile.size) {
            swapSegment(blockList, currentFile.start + i, matchingSpace.start + i)
        }

        matchingSpace.size -= currentFile.size
        matchingSpace.start += currentFile.size
    }

    private fun swapSegment(blockList: MutableList<Block>, first: Int, second: Int) {
        val tmp = blockList[first]
        blockList[first] = blockList[second]
        blockList[second] = tmp
    }

    data class Block(
        val id: Int?,
        val isEmpty: Boolean = false,
        val count: Int?
    ) {
        companion object {
            val Empty = Block(null, true, null)
        }
    }

    data class File(
        val id: Int, // used for debugging only
        val start: Int,
        val size: Int
    )

    data class Space(
        var start: Int,
        var size: Int
    )
}