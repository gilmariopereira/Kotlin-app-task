package dev.gilmario.task.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns
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
            var emailPattern : String = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

            val id = 0;
            val name = editName.text.toString()
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()


            if(email.isEmpty() || password.isEmpty() ) {
                Toast.makeText(getApplicationContext(), "Digite o email ou a senha", Toast.LENGTH_SHORT)
                    .show();
            }else if(!isEmailValid(email)){
                Toast.makeText(getApplicationContext(), "Email invalido", Toast.LENGTH_SHORT).show();
                }
                else {
                userBusiness = UserBusiness(this)
                userBusiness.insert(id, name, email, password)
                Toast.makeText(this, "Dados Cadastrado com sucesso", Toast.LENGTH_LONG).show()

                val intent = Intent(this, LoginActivity::class.java)
                intent.putExtra("password", password);
                startActivity(Intent(intent))

                finish()
            }

        }catch (e: ValidationException) {
            Toast.makeText(this, "Deu ruim"+e, Toast.LENGTH_LONG).show()

        }catch (e: Exception) {
            Toast.makeText(this, "Algo errado aconteceu!", Toast.LENGTH_LONG).show()
        }
    }

    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.toRegex().matches(email);
    }


    private fun salvar() {
        btnSave.setOnClickListener(this)
    }


}




