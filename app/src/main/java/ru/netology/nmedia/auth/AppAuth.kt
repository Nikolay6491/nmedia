package ru.netology.nmedia.auth

import android.content.Context
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.netology.nmedia.dto.Token
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppAuth @Inject constructor(
    @ApplicationContext
    private val context: Context
) {
    private val prefs = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
    private val idKey = "id"
    private val tokenKey = "token"
    private val _data = MutableStateFlow(Token())
    val data = _data.asStateFlow()
    private val _authStateFlow: MutableStateFlow<AuthState>

    companion object{
        private var INSTANCE: AppAuth? = null

        fun init(context: Context){
            INSTANCE = AppAuth(context)
        }
    }

    init {
        val id = prefs.getLong(idKey, 0)
        val token = prefs.getString(tokenKey, null)

        if (id == 0L || token == null) {
            _authStateFlow = MutableStateFlow(AuthState())
            with(prefs.edit()) {
                clear()
                apply()
            }
        } else {
            _authStateFlow = MutableStateFlow(AuthState(id, token))
        }
    }

    val authStateFlow: StateFlow<AuthState> = _authStateFlow.asStateFlow()

    fun setAuth(token: Token) {
        _data.value = token
        prefs.edit {
            putString(tokenKey, token.token)
            putLong(idKey, token.id)

        }
    }

    fun remove() {
        prefs.edit { clear() }
        _data.value = Token()
    }

    data class AuthState(val id: Long = 0, val token: String? = null)
}