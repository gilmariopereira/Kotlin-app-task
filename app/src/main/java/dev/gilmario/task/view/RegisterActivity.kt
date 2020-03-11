package dev.gilmario.task.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.View
import android.widget.Toast
import dev.gilmario.task.R
import dev.gilmario.task.business.UserBusiness
import dev.gilmario.task.util.ValidationException

import kotlinx.android.synthetic.main.activity_register.*
import java.lang.Exception

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var userBusiness : UserBusiness

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        salvar()
    }

    override fun onClick(view: View?) {
        if(view?.id == R.id.btnSave)  {
            insert();
        }
    }

    private fun insert() {
        try {
            val id = 0;
            val name = editName.text.toString()
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()
            userBusiness = UserBusiness(this)
            userBusiness.insert(id, name, email, password)
            Toast.makeText(this, "Dados Cadastrado com sucesso", Toast.LENGTH_LONG).show()
        }catch (e: ValidationException) {
            Toast.makeText(this, "Deu ruim"+e, Toast.LENGTH_LONG).show()

        }catch (e: Exception) {
            Toast.makeText(this, "Algo errado aconteceu!", Toast.LENGTH_LONG).show()
        }
    }

    private fun salvar() {
        btnSave.setOnClickListener(this)
    }


}




