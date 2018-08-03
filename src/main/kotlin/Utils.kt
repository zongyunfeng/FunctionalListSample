import FunctionalList.Construct
import FunctionalList.Empty

fun executionTime(f: () -> Unit): Long {
    val startTime = System.currentTimeMillis()
    f()
    return System.currentTimeMillis() - startTime
}

fun <T> listOf(vararg items: T): FunctionalList<T> {
    return if (items.isEmpty()) {
        Empty
    } else {
        Construct(items.first(), listOf(*items.copyOfRange(1, items.size)))
    }
}