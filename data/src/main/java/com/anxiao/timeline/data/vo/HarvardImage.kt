package com.anxiao.timeline.data.vo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anxiao.core.extension.empty
import com.google.gson.annotations.SerializedName

@Entity
data class HarvardImage(
    @PrimaryKey
    var id: Int = 0,

    @SerializedName("imageid")
    @ColumnInfo(name = "image_id")
    var imageId: Int = 0,

    @SerializedName("fileid")
    @ColumnInfo(name = "file_id")
    var fileId: Int = 0,

    @SerializedName("idsid")
    @ColumnInfo(name = "ids_id")
    var idsId: Int = 0,

    @ColumnInfo
    var caption: String = String.empty(),

    @SerializedName("alttext")
    @ColumnInfo(name = "alt_text")
    var altText: String = String.empty(),

    @ColumnInfo
    var description: String = String.empty(),

    @ColumnInfo
    var copyright: String = String.empty(),

    @SerializedName("iiifbaseuri")
    @ColumnInfo(name = "iiif_base_url")
    var iiifBaseUri: String = String.empty(),

    @SerializedName("baseimageurl")
    @ColumnInfo(name = "base_image_url")
    var baseImageUrl: String = String.empty(),

    @SerializedName("renditionnumber")
    @ColumnInfo(name = "rendition_number")
    var renditionNumber: String = String.empty(),

    @ColumnInfo
    var date: String = String.empty(),

    @ColumnInfo
    var height: Int = 0,

    @ColumnInfo
    var width: Int = 0,

    @SerializedName("accesslevel")
    @ColumnInfo(name = "access_level")
    var accessLevel: Int = 0,


    @ColumnInfo
    var format: String = String.empty(),

    @ColumnInfo
    var technique: String = String.empty(),

    @SerializedName("lastupdate")
    @ColumnInfo(name = "last_update")
    var lastUpdate: String = String.empty()

) {
    companion object {
        fun empaty() = HarvardImage()
    }
}

