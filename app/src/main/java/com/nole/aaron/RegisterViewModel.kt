import android.content.Context
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel(
    private val context: Context
) : ViewModel() {

    val inputsError = MutableLiveData<Boolean>()
    val emailFormatError = MutableLiveData<Boolean>()
    val passwordMismatchError = MutableLiveData<Boolean>()
    val passwordLengthError = MutableLiveData<Boolean>()
    val registrationSuccess = MutableLiveData<Boolean>()
    val registrationError = MutableLiveData<Boolean>()

    private val sharedPreferencesRepository: SharedPreferencesRepository =
        SharedPreferencesRepository().also {
            it.setSharedPreference(context)
        }

    fun register(email: String, password: String, confirmPassword: String) {
        if (isEmptyInputs(email, password, confirmPassword)) {
            inputsError.postValue(true)
            return
        }

        if (!isEmailValid(email)) {
            emailFormatError.postValue(true)
            return
        }

        if (password.length < 8) {
            passwordLengthError.postValue(true)
            return
        }

        if (password != confirmPassword) {
            passwordMismatchError.postValue(true)
            return
        }

        val isRegistered = saveUserCredentials(email, password)
        if (isRegistered) {
            registrationSuccess.postValue(true)
        } else {
            registrationError.postValue(true)
        }
    }

    private fun isEmptyInputs(email: String, password: String, confirmPassword: String) =
        email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun saveUserCredentials(email: String, password: String): Boolean {
        return try {
            sharedPreferencesRepository.saveUserEmail(email)
            sharedPreferencesRepository.saveUserPassword(password)
            true
        } catch (e: Exception) {
            false
        }
    }
}
