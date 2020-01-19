package com.killkinto.motivation.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.killkinto.motivation.R
import com.killkinto.motivation.util.MotivationConstants
import com.killkinto.motivation.util.SecurityPreferences
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private lateinit var mSecurity: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mSecurity = SecurityPreferences(this)

        btnSave.setOnClickListener {
            if (it.id == R.id.btnSave) {
                handleSave()
            }
        }

        verifyUserName()
    }

    private fun verifyUserName() {
        val userName = mSecurity.getSotredString(MotivationConstants.KEY.PERSON_NAME)
        if (userName != "") {
            Intent(this, MainActivity::class.java).also { intent -> startActivity(intent) }
        }
        edtName.setText(userName)
    }

    private fun handleSave() {

        if (edtName.text.toString().isBlank()) {
            Toast.makeText(this, getString(R.string.infome_nome), Toast.LENGTH_LONG).show()
        } else {
            mSecurity.storeString(MotivationConstants.KEY.PERSON_NAME, edtName.text.toString())
            Intent(this, MainActivity::class.java).also { intent -> startActivity(intent) }

            finish()
        }

    }
}
