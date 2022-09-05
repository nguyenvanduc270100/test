package com.bangcodin.alldocumentreader.ui.adapter

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.graphics.Bitmap
import android.net.Uri
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bangcodin.alldocumentreader.R
import com.bangcodin.alldocumentreader.utils.Common
import com.bangcodin.alldocumentreader.utils.Constants
import com.bumptech.glide.Glide
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@SuppressLint("SetTextI18n")
@BindingAdapter("date_create")
fun setInfoDocument(textView: TextView,  dateCreate: Long) {
    println("TimeCreate: $dateCreate")
    println("TimeNow: ${System.currentTimeMillis()}")

    val date = Common.getDate(dateCreate * 1000, "hh:mm a, dd/MM/yyyy")
    val now = Common.getDate(System.currentTimeMillis(), "dd/MM/yyyy hh:mm:ss")
    println("DateCreate: $date")
    println("DateNow: $now")
//    val cR: ContentResolver = textView.context.contentResolver
//    val mime = MimeTypeMap.getSingleton()
//    val mimeType = mime.getExtensionFromMimeType(uriDoc.let { it1 ->
//        cR.getType(
//            it1
//        )
//    })
//    var sizeText = ""
//    sizeText = if (size > 1024) {
//        if (size / 1024 / 1024 > 1) {
//            sizeText.plus(String.format("%.2f", (size.toDouble() / 1024 / 1024))).plus(" GB")
//        } else {
//            sizeText.plus(String.format("%.2f", (size.toDouble() / 1024))).plus(" MB")
//        }
//    } else {
//        sizeText.plus(size).plus(" KB")
//    }

    textView.text = "$date"
}

@SuppressLint("UseCompatLoadingForDrawables")
@BindingAdapter("type")
fun setImageDoc(imageView: ImageView, type:String) {
    var idIcon  = 0
    when(type){
        Constants.PDF ->{
            idIcon = R.drawable.ic_pdf
        }
        Constants.DOC, Constants.DOCX ->{
            idIcon = R.drawable.ic_docx
        }
        Constants.PPT, Constants.PPTX ->{
            idIcon = R.drawable.ic_ppt
        }
        Constants.XLS, Constants.XLSX ->{
            idIcon = R.drawable.ic_xsl
        }
        Constants.TXT -> {
            idIcon = R.drawable.ic_txt
        }
    }
    imageView.setImageResource(idIcon)
}

@BindingAdapter("src")
fun setImagePreview(imageView: ImageView, image: Bitmap) {
    imageView.setImageBitmap(image)
}
