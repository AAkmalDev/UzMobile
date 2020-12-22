package uz.pdp.uzmobile.dp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.pdp.uzmobile.dao.ServiceDao
import uz.pdp.uzmobile.models.TrafficModel
import uz.pdp.uzmobile.models.UssdModel

@Database(entities = [UssdModel::class,TrafficModel::class],version = 1)
abstract class AppDatabase:RoomDatabase()  {

    companion object{
        private var appDatabase: AppDatabase? = null
        fun getDatabase(context: Context):AppDatabase{
            if (appDatabase == null){
                appDatabase = Room.databaseBuilder(context,AppDatabase::class.java,"service")
                    .allowMainThreadQueries()
                    .build()
            }
            return appDatabase as AppDatabase
        }
    }
    abstract fun getDao(): ServiceDao
}