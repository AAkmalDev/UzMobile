package uz.pdp.uzmobile.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class UssdModel {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    var desc: String? = null

    var code: String? = null


}