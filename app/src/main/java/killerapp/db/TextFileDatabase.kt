package killerapp.db

import TextFileDao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context;
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope


@Database(entities = [TextFile::class], version = 1, exportSchema = false)
abstract class TextFileDatabase : RoomDatabase() {
K
    abstract fun fileDao(): TextFileDao

    companion object {
        @Volatile
        private var INSTANCE: TextFileDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): TextFileDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TextFileDatabase::class.java,
                    "textFile_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .addCallback(TextFileDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class TextFileDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onOpen method to populate the database.
             * For this sample, we clear the database every time it is created or opened.
             */
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
//                INSTANCE?.let { database ->
//                    scope.launch(Dispatchers.IO) {
//                        populateDatabase(database.wordDao())
            }
        }
    }
}

//@Database(entities = [TextFile::class], version = 1, exportSchema = false)
//abstract class TextFileDatabase : RoomDatabase() {
//
//    abstract val fileDao: TextFileDao
//
//    companion object {
//
//        @Volatile
//        private var INSTANCE: TextFileDatabase? = null
//
//        fun getInstance(context: Context): TextFileDatabase {
//            synchronized(this) {
//                var instance = INSTANCE
//
//                if (instance == null) {
//                    instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        TextFileDatabase::class.java,
//                        "file_database"
//                    )
//                        .fallbackToDestructiveMigration()
//                        .build()
//                    INSTANCE = instance
//                }
//                return instance
//            }
//        }
//    }
//}