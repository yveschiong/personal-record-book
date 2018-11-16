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
        private const val DIRECTORY = "signatures"

        const val CACHE = 0
        const val INTERNAL = 1
    }

    private val contextWrapper = ContextWrapper(context)

    fun getUniqueFilename(): String {
        return UUID.randomUUID().toString()
    }

    // Internal Example: /data/data/com.yveschiong.personalrecordbook/app_signatures/2/018759f6-9f58-477b-acee-11f71376a100.png
    // Cache Example: /data/data/com.yveschiong.personalrecordbook/cache/signatures/2/ff028a9e-a1c9-43e0-8a34-5241c41a3447.png
    private fun getDirectory(mode: Int): File {
        return when (mode) {
            CACHE -> contextWrapper.cacheDir
            INTERNAL -> contextWrapper.getDir(DIRECTORY, Context.MODE_PRIVATE)
            else -> contextWrapper.cacheDir
        }
    }

    private fun getImageRelativeFilePath(mode: Int, personId: Int, filename: String): String {
        return when (mode) {
            CACHE -> DIRECTORY + "/" + personId.toString() + "/" + filename + ".png"
            INTERNAL -> personId.toString() + "/" + filename + ".png"
            else -> DIRECTORY + "/" + personId.toString() + "/" + filename + ".png"
        }
    }

    fun getImageAbsoluteFilePath(mode: Int, personId: Int, filename: String): String {
        return getDirectory(mode).absolutePath + "/" + getImageRelativeFilePath(mode, personId, filename)
    }

    fun getImageAbsoluteFilePath(mode: Int, childPath: String): String {
        return getDirectory(mode).absolutePath + "/" + childPath
    }

    fun getLastModifiedTimestamp(path: String): Long {
        return File(path).lastModified()
    }

    fun saveSignature(mode:Int, personId: Int, bitmap: Bitmap, filename: String): String {
        val directory = getDirectory(mode)

        var path = getImageRelativeFilePath(mode, personId, filename)
        val file = File(directory, path)
        if (!file.parentFile.exists()) {
            file.parentFile.mkdirs()
        }

        if (!file.exists()) {
            when (mode) {
                CACHE -> File.createTempFile(filename, ".png", file.parentFile)
                INTERNAL -> {
                    // Do nothing since we don't need to create a file ahead of time
                }
                // Return no path since no valid mode was selected
                else -> return ""
            }
        }

        var output: FileOutputStream? = null
        try {
            output = FileOutputStream(file)

            bitmap.compress(Bitmap.CompressFormat.PNG, 100, output)
        } catch (e: Exception) {
            path = ""
            e.printStackTrace()
        } finally {
            try {
                output?.close()
            } catch (e: IOException) {
                path = ""
                e.printStackTrace()
            }
        }

        return path
    }

    fun copy(fromMode: Int, toMode: Int, personId: Int, fromFilename: String, toFilename: String): Boolean {
        val fromFile = File(getImageAbsoluteFilePath(fromMode, personId, fromFilename))
        val toFile = File(getImageAbsoluteFilePath(toMode, personId, toFilename))

        try {
            fromFile.copyTo(toFile, true)
        } catch (e: Exception) {
            // Failed to copy the file
            return false
        }

        // Successfully copied the file
        return true
    }

    fun delete(path: String): Boolean {
        val file = File(path)

        try {
            file.delete()
        } catch (e: Exception) {
            // Failed to delete the file
            return false
        }

        // Successfully deleted the file
        return true
    }

    fun loadSignature(mode: Int, personId: Int, filename: String): Bitmap? {
        return loadSignature(getImageAbsoluteFilePath(mode, personId, filename))
    }

    fun loadSignature(path: String): Bitmap? {
        try {
            val file = File(path)
            return BitmapFactory.decodeStream(FileInputStream(file))
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

        return null
    }
}