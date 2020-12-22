package uz.pdp.uzmobile.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class TrafficModel:Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    var tname: String? = null

    var tdesc: String? = null

    var tfulldesc: String? = null

    var tcode: String? = null

    var timg: String? = null

    var tyesno: String? = null
}