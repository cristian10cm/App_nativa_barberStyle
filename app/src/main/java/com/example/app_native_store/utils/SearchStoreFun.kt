package com.example.app_native_store.utils

import StoreType
import storeList

fun SearchStoreFun(id: String):StoreType{
    val store = storeList.find { x-> x.id.toString() == id }
    return store as  StoreType
}