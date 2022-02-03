package com.example.retrofit.data

data class CategoriesModel(
    val creatures: CreatureModel,
    val equipment: List<EntryModel>,
    val materials: List<EntryModel>,
    val monsters: List<EntryModel>,
    val treasure: List<EntryModel>
)
