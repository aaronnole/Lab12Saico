package com.nole.aaron

import SharedPreferencesRepository
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.nole.aaron.databinding.ActivitySplashBinding
import com.rommansabbir.animationx.Attention
import com.rommansabbir.animationx.animationXAttention

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var sharedPreferencesRepository: SharedPreferencesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        // Inicializamos el repositorio de SharedPreferences
        sharedPreferencesRepository = SharedPreferencesRepository().also {
            it.setSharedPreference(this)
        }

        showAnimationLogo()
        runPostDelayed()
    }

    private fun showAnimationLogo() {
        Handler(Looper.getMainLooper()).postDelayed({
            binding.imvLogo.animationXAttention(Attention.ATTENTION_RUBERBAND)
        }, 1000)
    }

    private fun runPostDelayed() {
        Handler(Looper.getMainLooper()).postDelayed({
            checkUserStatusAndNavigate()
        }, 4000)
    }

    private fun checkUserStatusAndNavigate() {
        // Comprobamos si hay un usuario registrado en SharedPreferences
        val email = sharedPreferencesRepository.getUserEmail()
        val password = sharedPreferencesRepository.getUserPassword()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            // Si el usuario ya está registrado, navegamos a MainActivity
            goToMainActivity()
        } else {
            // Si no está registrado, lo enviamos a LoginActivity
            goToLoginActivity()
        }
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun goToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
