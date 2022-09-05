/*
 *  Created by Nguyễn Thành Vinh on 8/17/22, 1:52 PM
 *     ntvtht1204@gmail.com
 *     Last modified 8/17/22, 1:52 PM
 *     Copyright (c) 2022.
 *     All rights reserved.
 */

package com.bangcodin.alldocumentreader.ui.`interface`

import com.bangcodin.alldocumentreader.db.model.DocumentItem

interface MenuClickListener {
    fun onClick(document: DocumentItem)
}