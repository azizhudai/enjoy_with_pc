package com.mindfulness.pc_ticareti

data class ComputerPart(
    var computerPartId: Int,
    var type:Int,
    var title: String,
    var price: Int
) {
    constructor() : this(0,0, "", 0)
}