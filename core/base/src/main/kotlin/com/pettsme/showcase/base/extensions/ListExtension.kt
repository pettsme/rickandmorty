package com.pettsme.showcase.base.extensions

// This utility function can be used to update a certain subtype of generic list
inline fun <reified T1, reified T2> List<T1>.firstSubtype(): T2 where T2 : T1 {
    return first { it is T2 } as T2
}

inline fun <reified T> List<T>.updateItem(predicate: (T) -> Boolean, transform: (T) -> T) =
    map { if (predicate(it)) transform(it) else it }
