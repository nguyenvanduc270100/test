package com.bangcodin.alldocumentreader.db.model


import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "document")
data class DocumentItem(
    @Keep @PrimaryKey(autoGenerate = true) val id: Long?,
    @Keep @ColumnInfo val title: String,
    @Keep @ColumnInfo val dateCreated: Long,
    @Keep @ColumnInfo val lastOpen:Long,
    @Keep @ColumnInfo val favorite:Boolean,
    @Keep @ColumnInfo val size: Int,
    @Keep @ColumnInfo val type: String?,
    @Keep @ColumnInfo val uriDoc: String
) {
    constructor() : this(null, "", 0,0,false, 0, "", "")
}

