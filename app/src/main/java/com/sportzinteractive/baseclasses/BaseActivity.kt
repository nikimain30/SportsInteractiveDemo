package com.sportzinteractive.baseclasses

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.sportzinteractive.R

abstract class BaseActivity  : AppCompatActivity(){
    var dialog: Dialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    @LayoutRes
    abstract  fun getLayoutId(): Int

    private fun performDataBinding() {
        var  binding: ViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        init(binding)
        binding.executePendingBindings()
    }

    abstract fun init(binding: ViewDataBinding)

    open fun hideKeyboard() {
        val view: View? = this.currentFocus
        if (view != null) {
            val imm: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
            }
        }
    }

    fun displayLoading( message: String?="", cancelable: Boolean=true) { // function -- context(parent (reference))
        dialog = Dialog(this)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setContentView(R.layout.layout_loading_screen)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCancelable(cancelable)
        var target= dialog?.findViewById<View>(R.id.progressBar) as ImageView
        if (target != null) {
            Glide.with(this).asGif().load(android.R.mipmap.sym_def_app_icon).fitCenter().transition( DrawableTransitionOptions().crossFade()).into(target)
        }
        when {
            message.isNullOrEmpty() -> {
                (dialog?.findViewById<View>(R.id.txtMessage) as TextView?)?.visibility =
                    View.GONE
            }
            else -> {
                (dialog?.findViewById<View>(R.id.txtMessage) as TextView?)?.visibility =
                    View.VISIBLE
                (dialog?.findViewById<View>(R.id.txtMessage) as TextView?)?.text=message

            }
        }
        try {
            dialog?.show()
        } catch (e: Exception) {
        }
    }


    fun hideLoading() {
        try {
            if (dialog != null) {
                dialog?.dismiss()
            }
        } catch (e: Exception) {
        }
    }

}