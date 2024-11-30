package com.nole.aaron.ui.view

import com.nole.aaron.ui.viewmodel.RegisterViewModel
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nole.aaron.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerViewModel = RegisterViewModel(this)
        observeValues()

        binding.btnRegister.setOnClickListener {
            registerViewModel.register(
                email = binding.edtEmail.text.toString(),
                password = binding.edtPassword.text.toString(),
                confirmPassword = binding.edtPassword2.text.toString()
            )
        }

        // Configuramos el clic del botón de retroceso
        binding.btnBackClose.setOnClickListener {
            finish()
        }

        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun observeValues() {
        registerViewModel.inputsError.observe(this) {
            Toast.makeText(this, "Ingrese todos los datos correctamente", Toast.LENGTH_SHORT).show()
        }

        registerViewModel.passwordMismatchError.observe(this) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
        }

        registerViewModel.registrationSuccess.observe(this) {
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        registerViewModel.registrationError.observe(this) {
            Toast.makeText(this, "Error en el registro, intente nuevamente", Toast.LENGTH_SHORT).show()
        }
        registerViewModel.passwordLengthError.observe(this) {
            Toast.makeText(this, "La contraseña debe tener más de 8 caracteres", Toast.LENGTH_SHORT).show()
        }

        registerViewModel.emailFormatError.observe(this) {
            Toast.makeText(this, "Ingrese un correo electrónico válido", Toast.LENGTH_SHORT).show()
        }
    }
}
