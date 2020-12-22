package uz.pdp.uzmobile.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import uz.pdp.uzmobile.models.TrafficModel
import uz.pdp.uzmobile.models.UssdModel

@Dao
interface ServiceDao {

    @Insert
    fun addUssd(ussdModel: UssdModel)

    @Query("select * from ussdmodel")
    fun allUssd(): List<UssdModel>

    @Insert
    fun addTraffic(trafficModel: TrafficModel)

    @Query("select * from trafficmodel")
    fun allTraffic(): List<TrafficModel>
}