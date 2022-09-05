/*
 *  Created by Nguyễn Kim Khánh on 15:47, 08/08/2022
 *     dtako.developer@gmail.com
 *     Last modified 15:47, 08/08/2022
 *     Copyright (c) 2022.
 *     All rights reserved.
 */

package com.bangcodin.alldocumentreader.db.dao

import androidx.annotation.Keep
import androidx.room.*
import com.bangcodin.alldocumentreader.db.model.DocumentItem
import kotlinx.coroutines.flow.Flow

@Keep
@Dao
interface DocumentDao {

    @Query("SELECT COUNT(*) FROM document")
    fun count(): Long

    @Query("SELECT * FROM document ORDER BY id ASC")
    fun getAll(): Flow<List<DocumentItem>>

    @Query("SELECT * FROM document WHERE type LIKE :type2")
    fun getAllFileByTYPE(type2:String):Flow<List<DocumentItem>>

    @Query("SELECT * FROM document WHERE type LIKE :type2 OR type LIKE :type3")
    fun getAllFileByTYPE2(type2: String,type3:String): Flow<List<DocumentItem>>

    @Query("SELECT * FROM document ORDER BY dateCreated DESC")
    fun getAllByDateCreatedDESC(): Flow<List<DocumentItem>>

    @Query("SELECT * FROM document WHERE type LIKE :type2 ORDER BY dateCreated DESC")
    fun getAllByTypeAndDateCreatedDESC(type2:String): Flow<List<DocumentItem>>

    @Query("SELECT * FROM document WHERE type LIKE :type2 OR type LIKE :type3 ORDER BY dateCreated DESC")
    fun getAllByTypeAndDateCreatedDESC2(type2:String,type3: String): Flow<List<DocumentItem>>

    @Query("SELECT * FROM document ORDER BY title ASC")
    fun getAllByNameASC(): Flow<List<DocumentItem>>

    @Query("SELECT * FROM document ORDER BY title DESC")
    fun getAllByNameDESC(): Flow<List<DocumentItem>>

    @Query("SELECT * FROM document WHERE type LIKE :type2 ORDER BY title DESC")
    fun getAllByTypeAndNameDESC(type2:String): Flow<List<DocumentItem>>

    @Query("SELECT * FROM document WHERE type LIKE :type2 OR type LIKE :type3 ORDER BY title DESC")
    fun getAllByTypeAndNameDESC2(type2:String,type3: String): Flow<List<DocumentItem>>

    @Query("SELECT * FROM document WHERE type LIKE :type2 ORDER BY title ASC")
    fun getAllByTypeAndNameASC(type2:String): Flow<List<DocumentItem>>

    @Query("SELECT * FROM document WHERE type LIKE :type2 OR type LIKE :type3 ORDER BY title ASC")
    fun getAllByTypeAndNameASC2(type2:String,type3: String): Flow<List<DocumentItem>>

    @Query("SELECT * FROM document WHERE title LIKE :name")
    fun getAllFileByName(name: String): Flow<List<DocumentItem>>

    @Query("SELECT * FROM document WHERE title LIKE :name AND type LIKE :type")
    fun getAllFileByNameAndType(name: String,type:String): Flow<List<DocumentItem>>

    @Query("SELECT * FROM document WHERE title LIKE :name AND type LIKE :type OR title LIKE :name AND type LIKE :type2")
    fun getAllFileByNameAndType2(name: String,type:String,type2:String): Flow<List<DocumentItem>>

    @Query("SELECT * FROM document ORDER BY lastOpen DESC")
    fun getAllFileRecent(): Flow<List<DocumentItem>>

    @Query("SELECT * FROM document WHERE favorite ='true'")
    fun getAllFileFavorite(): Flow<List<DocumentItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDocument(documentItem: DocumentItem)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllDocument(vararg documentItem: DocumentItem)

    @Delete
    fun delete(documentItem: DocumentItem)

}