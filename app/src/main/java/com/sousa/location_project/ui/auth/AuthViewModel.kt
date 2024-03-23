package com.sousa.location_project.ui.auth

import android.app.Application
import android.database.Cursor
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rethinkdb.RethinkDB
import com.rethinkdb.net.Connection
import com.sousa.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class AuthViewModel (
    private val authRepository: AuthRepository,
    private val application: Application
) : AndroidViewModel(application) {

    private val r = RethinkDB.r

    val goNext:MutableLiveData<Boolean> = MutableLiveData(false)

    val photoUrl:MutableLiveData<String> = MutableLiveData("")

    var termsOfUse:String = ""

    init {
        getTermsOfUse()
    }

    fun getTermsOfUse(){
        viewModelScope.launch(IO) {
            authRepository
                .getTermsOfUse()
                .catch {  }
                .collect{
                    termsOfUse = it
                }
        }
    }

    fun registration(fio:String,age:String,city:String,address:String,registrationCode:String){
        viewModelScope.launch(IO) {
            authRepository
                .registration(fio, age, city, address, photoUrl.value?:"", registrationCode)
                .catch {  }
                .collect{
                    goNext.postValue(it)
                }
        }
    }
    fun login(registrationCode:String){
        viewModelScope.launch(IO) {
            authRepository
                .login(registrationCode)
                .catch {  }
                .collect{
                    goNext.postValue(it)
                }
        }
    }

    fun connectToTable(){
        viewModelScope.launch(IO) {
            try {
                Log.d("mylog","2")
                r.connection()
                val conn: Connection = r.connection().port(32770).hostname("193.124.93.2").timeout(60000).connect()

                Log.d("mylog","3")
                val result = r.db("test").run(conn)

            }catch (e:Exception){
                Log.d("mylog",e.message.toString())

            }
        }
    }
}