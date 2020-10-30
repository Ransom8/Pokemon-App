package com.olamachia.Pokemon_week_8

import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.olamachia.Pokemon_week_8.R

/**
 * Base activity that extends AppCompact Activity.
 * Inflates base activity fragment that holds pokemon views
 */


abstract class BaseActivity : AppCompatActivity() {

    lateinit var mProgressBar: ProgressBar


    override fun setContentView(layoutResID: Int) {

        val constraintLayout =
            layoutInflater.inflate(R.layout.activity_base, null) as ConstraintLayout
        val frameLayout: FrameLayout = constraintLayout.findViewById(R.id.activity_content)

        mProgressBar = constraintLayout.findViewById(R.id.progress_bar)


        layoutInflater.inflate(layoutResID, frameLayout, true)

        super.setContentView(constraintLayout)

    }

}