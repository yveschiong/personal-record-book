package com.yveschiong.data.storage

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.*
import java.util.*
import javax.inject.Inject


class InternalStorageManager @Inject constructor(
    context: Context
) {

    companion object {
        private val DIRECTORY = "signatures"
    }

    private val contextWrapper = ContextWrapper(context)

    fun getUniqueFilename(): String {
        return UUID.randomUUID().toString()
    }

    fun getImageRelativeFilePath(personId: Int, filename: String): String {
        return personId.toString() + "/" + filename + ".png"
    }

    fun getImageParentFilePath(): String {
        return contextWrapper.getDir(DIRECTORY, Context.MODE_PRIVATE).absolutePath
    }

    fun getImageAbsoluteFilePath(personId: Int, filename: String): String {
        return getImageParentFilePath() + "/" + getImageRelativeFilePath(personId, filename)
    }

    fun getImageAbsoluteFilePath(childPath: String): String {
        return getImageParentFilePath() + "/" + childPath
    }

    fun getLastModifiedTimestamp(path: String): Long {
        return File(path).lastModified()
    }

    fun saveSignature(personId: Int, bitmap: Bitmap, filename: String): String {
        val directory = contextWrapper.getDir(DIRECTORY, Context.MODE_PRIVATE)

        val path = getImageRelativeFilePath(personId, filename)
        val file = File(directory, path)
        if (!file.parentFile.exists()) {
            file.parentFile.mkdirs()
        }

        var output: FileOutputStream? = null
        try {
            output = FileOutputStream(file)

            bitmap.compress(Bitmap.CompressFormat.PNG, 100, output)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                output?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        return path
    }

    fun loadSignature(path: String): Bitmap? {
        try {
            val file = File(contextWrapper.getDir(DIRECTORY, Context.MODE_PRIVATE), path)
            return BitmapFactory.decodeStream(FileInputStream(file))
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

        return null
    }
}