package com.lenakurasheva.gb_popular_libraries.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomCachedImage (
    @PrimaryKey
    var url: String,
    var path: String)