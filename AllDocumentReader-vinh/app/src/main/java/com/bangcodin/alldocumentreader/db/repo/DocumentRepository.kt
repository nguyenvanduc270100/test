/*
 *  Created by Nguyễn Kim Khánh on 15:49, 08/08/2022
 *     dtako.developer@gmail.com
 *     Last modified 15:49, 08/08/2022
 *     Copyright (c) 2022.
 *     All rights reserved.
 */

package com.bangcodin.alldocumentreader.db.repo

import androidx.annotation.WorkerThread
import com.bangcodin.alldocumentreader.db.dao.DocumentDao
import com.bangcodin.alldocumentreader.db.model.DocumentItem
import com.bangcodin.alldocumentreader.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

class DocumentRepository(private val documentDao: DocumentDao) {
    val allDocuments: Flow<List<DocumentItem>> = documentDao.getAll()

    val allPDFFiles: Flow<List<DocumentItem>> = documentDao.getAllFileByTYPE("pdf")

    val allWordFiles: Flow<List<DocumentItem>> = documentDao.getAllFileByTYPE2("doc", "docx")

    val allExcelFiles: Flow<List<DocumentItem>> = documentDao.getAllFileByTYPE2("xls", "xlsx")

    val allPPTFiles: Flow<List<DocumentItem>> = documentDao.getAllFileByTYPE2("ppt", "pptx")

    val allTextFiles: Flow<List<DocumentItem>> = documentDao.getAllFileByTYPE("txt")

    val allDocumentsSortByDate: Flow<List<DocumentItem>> = documentDao.getAllByDateCreatedDESC()

    val allPDFFilesSortByDate: Flow<List<DocumentItem>> =
        documentDao.getAllByTypeAndDateCreatedDESC("pdf")

    val allWordFilesSortByDate: Flow<List<DocumentItem>> =
        documentDao.getAllByTypeAndDateCreatedDESC2("doc", "docx")

    val allExcelFilesSortByDate: Flow<List<DocumentItem>> =
        documentDao.getAllByTypeAndDateCreatedDESC2("xls", "xlsx")

    val allPPTFilesSortByDate: Flow<List<DocumentItem>> =
        documentDao.getAllByTypeAndDateCreatedDESC2("ppt", "pptx")

    val allTextFilesSortByDate: Flow<List<DocumentItem>> =
        documentDao.getAllByTypeAndDateCreatedDESC("txt")

    val allDocumentsSortByNameDESC: Flow<List<DocumentItem>> = documentDao.getAllByNameDESC()

    val allPDFFilesSortByNameDESC: Flow<List<DocumentItem>> =
        documentDao.getAllByTypeAndNameDESC("pdf")

    val allWordFilesSortByNameDESC: Flow<List<DocumentItem>> =
        documentDao.getAllByTypeAndNameDESC2("doc", "docx")

    val allExcelFilesSortByNameDESC: Flow<List<DocumentItem>> =
        documentDao.getAllByTypeAndNameDESC2("xls", "xlsx")

    val allPPTFilesSortByNameDESC: Flow<List<DocumentItem>> =
        documentDao.getAllByTypeAndNameDESC2("ppt", "pptx")

    val allTextFilesSortByNameDESC: Flow<List<DocumentItem>> =
        documentDao.getAllByTypeAndNameDESC("txt")

    val allDocumentsSortByNameASC: Flow<List<DocumentItem>> = documentDao.getAllByNameASC()

    val allPDFFilesSortByNameASC: Flow<List<DocumentItem>> =
        documentDao.getAllByTypeAndNameASC("pdf")

    val allWordFilesSortByNameASC: Flow<List<DocumentItem>> =
        documentDao.getAllByTypeAndNameASC2("doc", "docx")

    val allExcelFilesSortByNameASC: Flow<List<DocumentItem>> =
        documentDao.getAllByTypeAndNameASC2("xls", "xlsx")

    val allPPTFilesSortByNameASC: Flow<List<DocumentItem>> =
        documentDao.getAllByTypeAndNameASC2("ppt", "pptx")

    val allTextFilesSortByNameASC: Flow<List<DocumentItem>> =
        documentDao.getAllByTypeAndNameASC("txt")

    fun searchDataBase(query: String): Flow<List<DocumentItem>> {
        return documentDao.getAllFileByName(query)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertDocument(documentItem: DocumentItem) {
        documentDao.insertDocument(documentItem)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteDocument(documentItem: DocumentItem) {
        documentDao.delete(documentItem)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertAllDocument(vararg documentItem: DocumentItem) {
        documentDao.insertAllDocument(*documentItem)
    }
}