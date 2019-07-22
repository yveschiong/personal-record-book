package com.yveschiong.data.storage

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.*
import java.util.*
import javax.inject.Inject


class InternalStorageManager @Inject constructor(context: Context) {

    companion object {
        private const val SUBDIRECTORY_SIGNATURE = "signatures"

        const val MODE_CACHE = 0
        const val MODE_INTERNAL = 1

        const val TYPE_SIGNATURE = 0
    }

    private val contextWrapper = ContextWrapper(context)

    fun getUniqueFilename(): String {
        return UUID.randomUUID().toString()
    }

    private fun getSubDirectory(type: Int): String {
        return when (type) {
            TYPE_SIGNATURE -> SUBDIRECTORY_SIGNATURE
            else -> SUBDIRECTORY_SIGNATURE
        }
    }

    // Internal Example: /data/data/com.yveschiong.personalrecordbook/app_signatures/2/018759f6-9f58-477b-acee-11f71376a100.png
    // Cache Example: /data/data/com.yveschiong.personalrecordbook/cache/signatures/2/ff028a9e-a1c9-43e0-8a34-5241c41a3447.png
    private fun getDirectory(mode: Int, type: Int): File {
        return when (mode) {
            MODE_CACHE -> contextWrapper.cacheDir
            MODE_INTERNAL -> contextWrapper.getDir(getSubDirectory(type), Context.MODE_PRIVATE)
            else -> contextWrapper.cacheDir
        }
    }

    fun getPersonIdRelativeDirectoryPath(mode: Int, personId: Int): String {
        return when (mode) {
            MODE_CACHE -> SUBDIRECTORY_SIGNATURE + "/" + personId.toString()
            MODE_INTERNAL -> personId.toString()
            else -> SUBDIRECTORY_SIGNATURE + "/" + personId.toString()
        }
    }

    fun getImageRelativeFilePath(mode: Int, personId: Int, filename: String): String {
        return getPersonIdRelativeDirectoryPath(mode, personId) + "/" + filename + ".png"
    }

    fun getImageAbsoluteFilePath(mode: Int, personId: Int, filename: String): String {
        return getDirectory(
            mode,
            TYPE_SIGNATURE
        ).absolutePath + "/" + getImageRelativeFilePath(mode, personId, filename)
    }

    fun getAbsoluteFilePath(mode: Int, type: Int, childPath: String): String {
        return getDirectory(mode, type).absolutePath + "/" + childPath
    }

    fun getLastModifiedTimestamp(path: String): Long {
        return File(path).lastModified()
    }

    fun saveSignature(mode: Int, personId: Int, bitmap: Bitmap, filename: String): String {
        val directory = getDirectory(mode, TYPE_SIGNATURE)

        var path = getImageRelativeFilePath(mode, personId, filename)
        val file = File(directory, path)
        if (!file.parentFile.exists()) {
            file.parentFile.mkdirs()
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

    fun getFiles(path: String): Array<File>? {
        return File(path).listFiles()
    }

    fun copy(
        fromMode: Int,
        toMode: Int,
        personId: Int,
        fromFilename: String,
        toFilename: String
    ): Boolean {
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

    fun delete(mode: Int, type: Int, personId: Int): Boolean {
        return delete(
            File(
                getAbsoluteFilePath(
                    mode,
                    type,
                    getPersonIdRelativeDirectoryPath(mode, personId)
                )
            ).absolutePath
        )
    }

    fun delete(path: String): Boolean {
        if (getFiles(path)?.size ?: 0 == 0) {
            // There is nothing within this file path to delete
            return true
        }

        val file = File(path)

        try {
            file.deleteRecursively()
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