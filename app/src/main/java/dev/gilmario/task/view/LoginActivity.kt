package dev.gilmario.task.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.View
import android.widget.Toast
import dev.gilmario.task.R
import dev.gilmario.task.constants.TaskConstants
import dev.gilmario.task.business.UserBusiness
import dev.gilmario.task.util.SecurityPreferences

import kotlinx.android.synthetic.main.activity_login.*
import kotlin.math.log

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var userBusiness : UserBusiness
    private lateinit var mSecurityPreferences : SecurityPreferences
    private var mPassword = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val bundle :Bundle ?=intent.extras
        if (bundle!=null){
            mPassword = bundle.getString("password")
        }

        userBusiness = UserBusiness(this)
        mSecurityPreferences = SecurityPreferences(this)
        userBusiness
        verifyLoggerUser()
        setListeners()
    }

    private fun setListeners() {
        btnLogin.setOnClickListener(this)
        btnRegister.setOnClickListener(this)

    }
    override fun onClick(view: View?) {
        if(view?.id == R.id.btnLogin)  {
            validarlogin();
        }else if (view?.id ==R.id.btnRegister) {
            registerLogin();
        }
    }

    private fun verifyLoggerUser() {
       var id = mSecurityPreferences.getStoredString(TaskConstants.KEY.USER_ID)
       val name = mSecurityPreferences.getStoredString(TaskConstants.KEY.USER_NAME)
       val email =  mSecurityPreferences.getStoredString(TaskConstants.KEY.USER_EMAIL)
        if(name != "" && email != "" && mPassword !="") {
                if(userBusiness.validUser(email, mPassword)) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }

        }
    }

    private fun validarlogin() {
        val email = editEmailLogin.text.toString()
        val senha = editPasswordLogin.text.toString()
        if(userBusiness.validUser(email,senha)) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }else {
            Toast.makeText(this, "Usuario ou senha invalido", Toast.LENGTH_LONG).show()
        }
    }

    fun registerLogin() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

}
