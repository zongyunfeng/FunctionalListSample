fun main(args: Array<String>) {
    val funList: FunctionalList<Int> = listOf(1, 2, 3, 4)
    val list: List<Int> = mutableListOf(1, 2, 3, 4).toList()

    println("fold on functionalList : ${executionTime { funList.fold(0) { acc, i -> acc + i } }}")
    println("fold on list : ${executionTime { list.fold(0) { acc, i -> acc + i } }}")
}


