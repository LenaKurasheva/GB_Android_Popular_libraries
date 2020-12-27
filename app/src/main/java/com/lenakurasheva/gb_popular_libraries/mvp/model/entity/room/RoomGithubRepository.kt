package com.lenakurasheva.gb_popular_libraries.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomGithubUser::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomGithubRepository(
    @PrimaryKey val id: String,
    val name: String,
    val forksCount: String,
    val fullName: String,
    val description: String,
    val htmlUrl: String,
    val userId: String
)