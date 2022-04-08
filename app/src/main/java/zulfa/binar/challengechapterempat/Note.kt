package zulfa.binar.challengechapterempat

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true)
    var no : Int?,

    @ColumnInfo(name = "judul")
    var judul : String,

    @ColumnInfo(name = "deskripsi")
    var deskripsi : String,

    @ColumnInfo(name = "tenggatwaktu")
    var tenggatwaktu : String
) : Parcelable
