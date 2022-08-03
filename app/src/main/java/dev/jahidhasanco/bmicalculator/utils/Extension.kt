package dev.jahidhasanco.bmicalculator.utils

import android.content.Context
import android.view.View
import android.widget.Toast

fun Context.displayToast(s: String?){
    Toast.makeText(this,"$s",Toast.LENGTH_SHORT).show()
}

fun View.show(){
    this.visibility = View.VISIBLE
}

fun View.hide(){
    this.visibility = View.GONE
}

fun View.invisible(){
    this.visibility = View.INVISIBLE
}