package az.khayalsharifli.rickandmorty.tools

import android.view.View

interface ClickListener {
    fun onClick(position: Int, view: View)
}