/*
 *  Created by Nguyễn Kim Khánh on 18:22, 09/08/2022
 *     dtako.developer@gmail.com
 *     Last modified 18:22, 09/08/2022
 *     Copyright (c) 2022.
 *     All rights reserved.
 */

package com.bangcodin.alldocumentreader.db.viewmodel

import android.content.ContentResolver
import android.content.ContentUris
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.webkit.MimeTypeMap
import androidx.lifecycle.*
import com.bangcodin.alldocumentreader.db.model.DocumentItem
import com.bangcodin.alldocumentreader.db.repo.DocumentRepository
import com.bangcodin.alldocumentreader.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DocumentViewModel(private val repository: DocumentRepository) : ViewModel() {
    private val _listItemDocument: MutableLiveData<List<DocumentItem>> = MutableLiveData()
    val listItemDocument get() = _listItemDocument

    val allDocuments: LiveData<List<DocumentItem>> = repository.allDocuments.asLiveData()

    val allFilePDF: LiveData<List<DocumentItem>> = repository.allPDFFiles.asLiveData()

    val allFileWord: LiveData<List<DocumentItem>> = repository.allWordFiles.asLiveData()

    val allFileExcel: LiveData<List<DocumentItem>> = repository.allExcelFiles.asLiveData()

    val allFilePPT: LiveData<List<DocumentItem>> = repository.allPPTFiles.asLiveData()

    val allFileText: LiveData<List<DocumentItem>> = repository.allTextFiles.asLiveData()

    val allDocumentsSortByDate: LiveData<List<DocumentItem>> =
        repository.allDocumentsSortByDate.asLiveData()

    val allFilePDFSortByDate: LiveData<List<DocumentItem>> =
        repository.allPDFFilesSortByDate.asLiveData()

    val allFileWordSortByDate: LiveData<List<DocumentItem>> =
        repository.allWordFilesSortByDate.asLiveData()

    val allFileExcelSortByDate: LiveData<List<DocumentItem>> =
        repository.allExcelFilesSortByDate.asLiveData()

    val allFilePPTSortByDate: LiveData<List<DocumentItem>> =
        repository.allPPTFilesSortByDate.asLiveData()

    val allFileTextSortByDate: LiveData<List<DocumentItem>> =
        repository.allTextFilesSortByDate.asLiveData()

    val allDocumentsSortByNameDESC: LiveData<List<DocumentItem>> =
        repository.allDocumentsSortByNameDESC.asLiveData()

    val allFilePDFSortByNameDESC: LiveData<List<DocumentItem>> =
        repository.allPDFFilesSortByNameDESC.asLiveData()

    val allFileWordSortByNameDESC: LiveData<List<DocumentItem>> =
        repository.allWordFilesSortByNameDESC.asLiveData()

    val allFileExcelSortByNameDESC: LiveData<List<DocumentItem>> =
        repository.allExcelFilesSortByNameDESC.asLiveData()

    val allFilePPTSortByNameDESC: LiveData<List<DocumentItem>> =
        repository.allPPTFilesSortByNameDESC.asLiveData()

    val allFileTextSortByNameDESC: LiveData<List<DocumentItem>> =
        repository.allTextFilesSortByNameDESC.asLiveData()

    val allDocumentsSortByNameASC: LiveData<List<DocumentItem>> =
        repository.allDocumentsSortByNameASC.asLiveData()

    val allFilePDFSortByNameASC: LiveData<List<DocumentItem>> =
        repository.allPDFFilesSortByNameASC.asLiveData()

    val allFileWordSortByNameASC: LiveData<List<DocumentItem>> =
        repository.allWordFilesSortByNameASC.asLiveData()

    val allFileExcelSortByNameASC: LiveData<List<DocumentItem>> =
        repository.allExcelFilesSortByNameASC.asLiveData()

    val allFilePPTSortByNameASC: LiveData<List<DocumentItem>> =
        repository.allPPTFilesSortByNameASC.asLiveData()

    val allFileTextSortByNameASC: LiveData<List<DocumentItem>> =
        repository.allTextFilesSortByNameASC.asLiveData()

    fun searchDatabase(searchQuery: String, type: String,type2: String) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                repository.searchDataBase(searchQuery).collect {
                    _listItemDocument.postValue(
                        it.filter { documentItem ->
                            documentItem.type.equals(type) || documentItem.type.equals(type2) || type == Constants.ALL_FILE
                        }

                    )
                }
            }
        }
    }
    fun sortByDateDESC(type: String,type2: String){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.allDocumentsSortByDate.collect{
                    _listItemDocument.postValue(
                        it.filter { documentItem ->
                            documentItem.type.equals(type) || documentItem.type.equals(type2) || type == Constants.ALL_FILE
                        }
                    )
                }
            }
        }
    }
    fun sortByNameDESC(type: String,type2: String){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.allDocumentsSortByNameDESC.collect{
                    _listItemDocument.postValue(
                        it.filter { documentItem ->
                            documentItem.type.equals(type) || documentItem.type.equals(type2) || type == Constants.ALL_FILE
                        }
                    )
                }
            }
        }
    }
    fun sortByNamASC(type: String,type2: String){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.allDocumentsSortByNameASC.collect{
                    _listItemDocument.postValue(
                        it.filter { documentItem ->
                            documentItem.type.equals(type) || documentItem.type.equals(type2) || type == Constants.ALL_FILE
                        }
                    )
                }
            }
        }
    }
    fun sortNormal(type: String,type2: String){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.allDocuments.collect{
                    _listItemDocument.postValue(
                        it.filter { documentItem ->
                            documentItem.type.equals(type) || documentItem.type.equals(type2) || type == Constants.ALL_FILE
                        }
                    )
                }
            }
        }
    }

    private fun insertDocument(documentItem: DocumentItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertAllDocument(documentItem)
    }

    fun deleteDocument(documentItem: DocumentItem) = viewModelScope.launch {
        repository.deleteDocument(documentItem)
    }

    private fun insertAllDocument(vararg documentItem: DocumentItem) = viewModelScope.launch {
        repository.insertAllDocument(*documentItem)
    }

    fun getAllDocuments(contentResolver: ContentResolver) {
        val projection = arrayOf(
            MediaStore.Files.FileColumns._ID,
            MediaStore.Files.FileColumns.SIZE,
            MediaStore.Files.FileColumns.DATE_ADDED,
            MediaStore.Files.FileColumns.MIME_TYPE,
            MediaStore.Files.FileColumns.TITLE
        )
        val selection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            MediaStore.Files.FileColumns.MEDIA_TYPE + "=" + MediaStore.Files.FileColumns.MEDIA_TYPE_DOCUMENT
        } else {
            MediaStore.Files.FileColumns.MEDIA_TYPE + "!=" + MediaStore.Files.FileColumns.MEDIA_TYPE_NONE + " AND " +
                    MediaStore.Files.FileColumns.MEDIA_TYPE + "!=" + MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO + " AND " +
                    MediaStore.Files.FileColumns.MEDIA_TYPE + "!=" + MediaStore.Files.FileColumns.MEDIA_TYPE_AUDIO + " AND " +
                    MediaStore.Files.FileColumns.MEDIA_TYPE + "!=" + MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE
        }
        val queryUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL)
        } else {
            MediaStore.Files.getContentUri("external")
        }
        viewModelScope.launch(Dispatchers.IO) {
            contentResolver.query(
                queryUri!!,
                projection,
                selection,
                null,
                MediaStore.Files.FileColumns.DATE_ADDED + " DESC"
            )?.use { cursor ->
                val documentFiles = arrayListOf<DocumentItem>()
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID)
                val nameColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.TITLE)
                val typeColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MIME_TYPE)
                val dateAddColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_ADDED)
                val sizeColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.SIZE)
                while (cursor.moveToNext()) {
                    val id = cursor.getLong(idColumn)
                    val name = cursor.getString(nameColumn)
                    val size = cursor.getInt(sizeColumn)
                    val type = cursor.getString(typeColumn)
                    val dateAdd = cursor.getLong(dateAddColumn)
                    val contentUri: Uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        ContentUris.withAppendedId(
                            MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL),
                            id
                        )
                    } else {
                        ContentUris.withAppendedId(
                            MediaStore.Files.getContentUri("external"),
                            id
                        )
                    }
                    // Stores column values and the contentUri in a local object
                    // that represents the media file.
                    val mime = MimeTypeMap
                        .getSingleton()
                    val mimeType = mime.getExtensionFromMimeType(type)
                    println("ABC: $dateAdd")
                    println("ABC: $id")
                    if (checkType(mimeType)) {
                        documentFiles.add(
                            DocumentItem(
                                id,
                                name,
                                dateAdd,
                                0,
                                false,
                                size,
                                mimeType,
                                contentUri.toString()
                            )
                        )
                        insertAllDocument(*documentFiles.map { it }.toTypedArray())
                    }
                }
            }
        }

    }

    private fun checkType(type: String?): Boolean {
        return (type == "pdf") or (type == "doc") or (type == "docx") or (type == "xls") or (type == "xlsx") or (type == "ppt") or (type == "pptx") or (type == "txt")
    }
}

class DocumentViewModelFactory(private val repository: DocumentRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DocumentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DocumentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}