/*
 *  Created by Nguyễn Kim Khánh on 17:36, 09/08/2022
 *     dtako.developer@gmail.com
 *     Last modified 17:36, 09/08/2022
 *     Copyright (c) 2022.
 *     All rights reserved.
 */

package com.bangcodin.alldocumentreader

import android.app.Application
import com.bangcodin.alldocumentreader.db.AppDB
import com.bangcodin.alldocumentreader.db.repo.DocumentRepository
import com.bangcodin.alldocumentreader.ui.base.BaseApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class DocumentApplication : BaseApplication() {
    private val applicationScope = CoroutineScope(SupervisorJob())
    private val database by lazy { AppDB.getDatabase(this, applicationScope) }
    val repository by lazy { DocumentRepository(database.documentDao()) }

}