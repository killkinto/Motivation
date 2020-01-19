package com.killkinto.motivation.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.killkinto.motivation.R
import com.killkinto.motivation.mock.Mock
import com.killkinto.motivation.util.MotivationConstants
import com.killkinto.motivation.util.SecurityPreferences
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var mFilter: Int = MotivationConstants.PHRASE_FILTER.ALL
    private lateinit var mSecurityPreferences: SecurityPreferences
    private val mMock = Mock()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSecurityPreferences = SecurityPreferences(this)

        setListeners()
        handleFilter(imgAll.id)
        refreshPhrase()
        verifyUserName()
    }

    private fun verifyUserName() {
        txtUserName.text = mSecurityPreferences.getSotredString(MotivationConstants.KEY.PERSON_NAME)
    }

    override fun onClick(view: View) {
        when (view.id) {
            btnNewPhrase.id -> refreshPhrase()
            else -> handleFilter(view.id)
        }
    }

    private fun handleFilter(id: Int) {
        imgAll.setImageResource(R.drawable.ic_all_unselected)
        imgSun.setImageResource(R.drawable.ic_sun_unselected)
        imgHappy.setImageResource(R.drawable.ic_happy_unselected)

        when (id) {
            imgAll.id -> {
                mFilter = MotivationConstants.PHRASE_FILTER.ALL
                imgAll.setImageResource(R.drawable.ic_all_selected)
            }
            imgSun.id -> {
                mFilter = MotivationConstants.PHRASE_FILTER.SUN
                imgSun.setImageResource(R.drawable.ic_sun_selected)
            }
            else -> {
                mFilter = MotivationConstants.PHRASE_FILTER.HAPPY
                imgHappy.setImageResource(R.drawable.ic_happy_selected)
            }
        }
    }

    private fun refreshPhrase() {
        txtPhrase.text = mMock.getPhrase(mFilter)
    }

    private fun setListeners() {
        imgAll.setOnClickListener(this)
        imgSun.setOnClickListener(this)
        imgHappy.setOnClickListener(this)
        btnNewPhrase.setOnClickListener(this)
    }
}
