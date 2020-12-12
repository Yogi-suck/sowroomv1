package com.sowroom.app.data.local

import android.content.Context
import android.content.SharedPreferences
import com.sowroom.app.data.model.ActionState
import com.sowroom.app.data.model.AuthUser
import com.sowroom.app.util.getObject
import com.sowroom.app.util.putObject

class AuthPref(val context: Context) {
    private val sp: SharedPreferences by lazy { context.getSharedPreferences(AuthPref::class.java.name, Context.MODE_PRIVATE)
    }

    private companion object{
        const val AUTH_USER = "auth_user"
        const val IS_LOGIN = "is_login"
    }

     var authUser: AuthUser?
        get() = sp.getObject(AUTH_USER)
        private set(value) = sp.edit().putObject(AUTH_USER,value).apply()

     var isLogin: Boolean
        get() = sp.getBoolean(IS_LOGIN, false)
        private set(value) = sp.edit().putBoolean(IS_LOGIN,value).apply()

    suspend fun login(email: String, password: String): ActionState<AuthUser>{
        val user = authUser
        return if (user==null){
            ActionState(massage = "Anda Belum Terdaftar", isSuccess = false)
        }else if (email.isBlank()|| password.isBlank()){
            ActionState(massage = "Email dan Password Tidak Boleh Kosong", isSuccess = false)
        }else if (user.email==email&&user.password==password){
            ActionState(authUser, massage = "Anda Berhasil Login")
        }else{
            ActionState(massage = "Email atau Password Anda Salah", isSuccess = false)
        }
    }

    suspend fun register(user: AuthUser): ActionState<AuthUser>{
        return if (user.email.isBlank()|| user.password.isBlank()) {
            ActionState(massage = "Email dan Password Anda Tidak Boleh Kosong", isSuccess = false)
        }else{
            authUser=user
            ActionState(user, massage = "Anda Berhasil Daftar")
        }
    }

    suspend fun login(): ActionState<Boolean>{
        isLogin= false
        return ActionState(true, massage = "Anda Berhasil Logout")
    }

}