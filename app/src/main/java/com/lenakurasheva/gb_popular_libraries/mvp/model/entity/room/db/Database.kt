package com.lenakurasheva.gb_popular_libraries.mvp.model.entity.room.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.room.RoomCachedImage
import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.room.RoomGithubRepository
import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.room.RoomGithubUser
import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.room.dao.CachedImageDao
import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.room.dao.RepositoryDao
import com.lenakurasheva.gb_popular_libraries.mvp.model.entity.room.dao.UserDao
import java.lang.RuntimeException

@androidx.room.Database(entities = [RoomGithubUser::class, RoomGithubRepository::class, RoomCachedImage::class], version = 4)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao
    abstract val cachedImageDao: CachedImageDao

    companion object {
        private const val DB_NAME = "database.db"
        private var instance: Database? = null
        fun getInstance() = instance ?: throw RuntimeException("Database has not been created")
        fun create(context: Context) {
            instance ?: let {
                instance = Room.databaseBuilder(context, Database::class.java, DB_NAME)
                    .addMigrations(MIGRATION_2_3, MIGRATION_3_4)
                    .build()
            }
        }
    }
}