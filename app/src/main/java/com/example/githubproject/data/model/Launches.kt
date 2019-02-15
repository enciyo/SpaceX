package com.example.githubproject.data.model

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import androidx.room.*
import com.bumptech.glide.load.resource.bitmap.BitmapDrawableEncoder
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

@Entity(tableName = "Launches")
class Launches() : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var mId: Int = 0
    @SerializedName("flight_number")
    var flightNumber: Int = 0
    @SerializedName("mission_name")
    var missionName: String = ""
    @Ignore
    @SerializedName("mission_id")
    var missionİd: List<Any> = listOf()
    @SerializedName("upcoming")
    var upcoming: Boolean = false
    @SerializedName("launch_year")
    var launchYear: String = ""
    @SerializedName("launch_date_unix")
    var launchDateUnix: Int = 0
    @SerializedName("launch_date_utc")
    var launchDateUtc: String = ""
    @SerializedName("launch_date_local")
    var launchDateLocal: String = ""
    @SerializedName("is_tentative")
    var isTentative: Boolean = false
    @SerializedName("tentative_max_precision")
    var tentativeMaxPrecision: String = ""
    @SerializedName("tbd")
    var tbd: Boolean = false
    @Ignore
    @SerializedName("launch_window")
    var launchWindow: Any? = Any()
    @Ignore
    @SerializedName("rocket")
    var rocket: Rocket = Rocket()
    @Ignore
    @SerializedName("ships")
    var ships: List<Any> = listOf()
    @Ignore
    @SerializedName("telemetry")
    var telemetry: Telemetry = Telemetry()
    @Ignore
    @SerializedName("launch_site")
    var launchSite: LaunchSite = LaunchSite()
    @Ignore
    @SerializedName("launch_success")
    var launchSuccess: Any? = Any()
    @Ignore
    @SerializedName("launch_failure_details")
    var launchFailureDetails: LaunchFailureDetails = LaunchFailureDetails()
    @Ignore
    @SerializedName("links")
    var links: Links = Links()
    @Ignore
    @SerializedName("details")
    var details: String? = ""
    @Ignore
    @SerializedName("static_fire_date_utc")
    var staticFireDateUtc: Any? = Any()
    @Ignore
    @SerializedName("static_fire_date_unix")
    var staticFireDateUnix: Any? = Any()
    @Ignore
    @SerializedName("timeline")
    var timeline: Any? = Any()
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var bitmap: ByteArray? = null

    constructor(parcel: Parcel) : this() {
        mId = parcel.readInt()
        flightNumber = parcel.readInt()
        missionName = parcel.readString()
        upcoming = parcel.readByte() != 0.toByte()
        launchYear = parcel.readString()
        launchDateUnix = parcel.readInt()
        launchDateUtc = parcel.readString()
        launchDateLocal = parcel.readString()
        isTentative = parcel.readByte() != 0.toByte()
        tentativeMaxPrecision = parcel.readString()
        tbd = parcel.readByte() != 0.toByte()
        details = parcel.readString()
        bitmap = parcel.createByteArray()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(mId)
        parcel.writeInt(flightNumber)
        parcel.writeString(missionName)
        parcel.writeByte(if (upcoming) 1 else 0)
        parcel.writeString(launchYear)
        parcel.writeInt(launchDateUnix)
        parcel.writeString(launchDateUtc)
        parcel.writeString(launchDateLocal)
        parcel.writeByte(if (isTentative) 1 else 0)
        parcel.writeString(tentativeMaxPrecision)
        parcel.writeByte(if (tbd) 1 else 0)
        parcel.writeString(details)
        parcel.writeByteArray(bitmap)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Launches> {
        override fun createFromParcel(parcel: Parcel): Launches {
            return Launches(parcel)
        }

        override fun newArray(size: Int): Array<Launches?> {
            return arrayOfNulls(size)
        }
    }

}
