sealed class FunctionalList<out T> {
    object Empty : FunctionalList<Nothing>()
    data class Construct<out T>(val head: T, val tail: FunctionalList<T>) : FunctionalList<T>()

    fun forEach(f: (T) -> Unit) {
        tailrec fun go(list: FunctionalList<T>, f: (T) -> Unit) {
            when (list) {
                is Construct -> {
                    f(list.head)
                    go(list.tail, f)
                }
                is Empty -> Unit
            }
        }

        go(this, f)
    }

    fun <R> fold(init: R, f: (R, T) -> R): R {
        tailrec fun go(list: FunctionalList<T>, init: R, f: (R, T) -> R): R = when (list) {
            is Construct -> go(list.tail, f(init, list.head), f)
            is Empty -> init
        }

        return go(this, init, f)
    }

    fun reverse(): FunctionalList<T> = fold(Empty as FunctionalList<T>) { acc, i -> Construct(i, acc) }

    fun <R> foldRight(init: R, f: (R, T) -> R): R {
        return this.reverse().fold(init, f)
    }

    fun <R> map(f: (T) -> R): FunctionalList<R> {
        return foldRight(Empty as FunctionalList<R>) { tail, head -> Construct(f(head), tail) }
    }
}

