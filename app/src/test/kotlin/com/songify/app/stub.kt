package com.songify.app

import java.lang.reflect.Proxy

inline fun <reified T : Any> stub(): T =
    Proxy.newProxyInstance(
        T::class.java.classLoader,
        arrayOf<Class<*>>(T::class.java)
    ) { _, _, _ -> throw NotImplementedError("Stub!") } as T
