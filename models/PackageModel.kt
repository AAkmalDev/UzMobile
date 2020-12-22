package uz.pdp.uzmobile.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class PackageModel {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    var scode: String? = null

    var sdesc: String? = null

    var sname: String? = null

}