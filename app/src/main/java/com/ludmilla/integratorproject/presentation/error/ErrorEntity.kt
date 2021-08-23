package com.ludmilla.integratorproject.presentation.error

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import com.ludmilla.integratorproject.presentation.UnknownErrorActivity

sealed class ErrorEntity{
    object NotFoundError : ErrorEntity()
    object UnknownError : ErrorEntity()

    companion object{
        fun<T: View> doOnNotFoundError (showError:T, hideLayout:T){
            showError.visibility = View.VISIBLE
            hideLayout.visibility = View.GONE
        }

        fun doOnUnknownError(frag: Fragment, context: Context){
            val intent = Intent(context, UnknownErrorActivity::class.java)
            frag.startActivity(intent)
        }
    }
}
