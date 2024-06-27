package com.desafiolatam.surveydonkey.viewmodel

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.desafiolatam.surveydonkey.ui.COLOR
import com.desafiolatam.surveydonkey.ui.COLORS
import com.desafiolatam.surveydonkey.ui.EMAIL
import com.desafiolatam.surveydonkey.ui.MATERIAL
import com.desafiolatam.surveydonkey.ui.MATERIALS
import com.desafiolatam.surveydonkey.ui.SUGERENCIA

class MainViewModel : ViewModel() {

    private var firstAnswer: ArrayList<String> = arrayListOf()
    private var secondAnswer: ArrayList<String> = arrayListOf()
    private var thirdAnswer: ArrayList<String> = arrayListOf()

    private val _userEmail = MutableLiveData<String>()
    val userEmail: LiveData<String> get() = _userEmail

    private val _userComment = MutableLiveData<String>()
    val userComment: LiveData<String> get() = _userComment

    private val separator = ", "



    /**
     * Guarda la primera pregunta y retorna un listado de String,
     * ordenado y sin repetir opciones
     */
    fun addFirstAnswer(value: String): List<String> {
        firstAnswer.add(value)
        return firstAnswer.distinct().sorted().toList()
    }

    /**
     * Si el usuario deseleccion una opcion, entonces la eliminamos de la lista "firstAnswer"
     */
    fun removeFirstAnswer(value: String): List<String> {
        if (firstAnswer.contains(value)) {
            firstAnswer.remove(value)
        }
        return firstAnswer.sorted().toList()
    }

    /**
     * Guarda la segunda pregunta y retorna un listado de String,
     * ordenado y sin repetir opciones
     */
    fun addSecondAnswer(value: String): List<String> {
        secondAnswer.add(value)
        return secondAnswer.distinct().sorted().toList()
    }

    /**
     * Si el usuario deseleccion una opcion, entonces la eliminamos de la lista "secondAnswer"
     */
    fun removeSecondAnswer(value: String): List<String> {
        if (secondAnswer.contains(value)) {
            secondAnswer.remove(value)
        }
        return secondAnswer.sorted().toList()
    }

    /**
     * Guarda la tercera pregunta y retorna un listado de String,
     * ordenado y sin repetir opciones
     */
    fun addThirdAnswer(value: String): List<String> {
        thirdAnswer.add(value)
        return thirdAnswer.distinct().sorted().toList()
    }
    /**
     * Si el usuario deseleccion una opcion, entonces la eliminamos de la lista "thirdAnswer"
     */
    fun removeThirdAnswer(value: String): List<String> {
        if (thirdAnswer.contains(value)) {
            thirdAnswer.remove(value)
        }
        return thirdAnswer.sorted().toList()
    }

    fun setEmail(email: String) {
        _userEmail.value = email
    }

    fun setComment(comment: String) {
        _userComment.value = comment
    }


    /**
     * Muestra el resultado de la primera pregunta, separado por ","
     */
    fun getFirstResult(): String =
        when (firstAnswer.size) {
            1 -> "$COLOR ${firstAnswer.joinToString(separator)}"
            else -> "$COLORS ${firstAnswer.joinToString(separator)}"
        }

    /**
     * Muestra el resultado de la segunda pregunta, separado por ","
     */
    fun getSecondResult(): String =
        when (secondAnswer.size) {
            1 -> "$MATERIAL ${secondAnswer.joinToString(separator)}"
            else -> "$MATERIALS ${secondAnswer.joinToString(separator)}"
        }



    /**
     * Agrega tus funciones aqui:
     */
    fun getThirdResult(): String =
        when (thirdAnswer.size) {
            1 -> "$COLOR ${thirdAnswer.joinToString(separator)}"
            else -> "$COLORS ${thirdAnswer.joinToString(separator)}"
        }

    fun getEmailResult(): String =
        "$EMAIL ${_userEmail.value ?:""}"

    fun getCommentResult(): String =
    "$SUGERENCIA ${_userComment.value ?:""}"

}
