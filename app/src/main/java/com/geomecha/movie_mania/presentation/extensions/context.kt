package com.geomecha.movie_mania.presentation.extensions

import android.content.Context
import android.content.Intent
import com.geomecha.movie_mania.R
import com.geomecha.movie_mania.presentation.constants.INTENT_TYPE

fun Context.shareLink(link: String) {
    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, link)
        type = INTENT_TYPE
    }
    this.startActivity(Intent.createChooser(shareIntent, this.getString(R.string.share_via)))
}
