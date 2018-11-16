package com.yveschiong.personalrecordbook.common.extensions

import android.graphics.Bitmap
import android.graphics.Color

/** Code taken from
 * @see com.github.gcacace.signaturepad.views.SignaturePad.getTransparentSignatureBitmap
 * with blank spaces trimmed/cropped
 **/
fun Bitmap?.cropped(): Bitmap? {
    if (this == null) {
        return null
    }

    val imgHeight = height
    val imgWidth = width

    val backgroundColor = Color.TRANSPARENT

    var xMin = Integer.MAX_VALUE
    var xMax = Integer.MIN_VALUE
    var yMin = Integer.MAX_VALUE
    var yMax = Integer.MIN_VALUE

    var foundPixel = false

    // Find xMin
    for (x in 0 until imgWidth) {
        var stop = false
        for (y in 0 until imgHeight) {
            if (getPixel(x, y) != backgroundColor) {
                xMin = x
                stop = true
                foundPixel = true
                break
            }
        }
        if (stop)
            break
    }

    // Image is empty...
    if (!foundPixel)
        return null

    // Find yMin
    for (y in 0 until imgHeight) {
        var stop = false
        for (x in xMin until imgWidth) {
            if (getPixel(x, y) != backgroundColor) {
                yMin = y
                stop = true
                break
            }
        }
        if (stop)
            break
    }

    // Find xMax
    for (x in imgWidth - 1 downTo xMin) {
        var stop = false
        for (y in yMin until imgHeight) {
            if (getPixel(x, y) != backgroundColor) {
                xMax = x
                stop = true
                break
            }
        }
        if (stop)
            break
    }

    // Find yMax
    for (y in imgHeight - 1 downTo yMin) {
        var stop = false
        for (x in xMin..xMax) {
            if (getPixel(x, y) != backgroundColor) {
                yMax = y
                stop = true
                break
            }
        }
        if (stop)
            break
    }

    return Bitmap.createBitmap(this, xMin, yMin, xMax - xMin, yMax - yMin)
}