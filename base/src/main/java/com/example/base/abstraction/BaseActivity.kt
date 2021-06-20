package com.example.base.abstraction


import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<B : ViewBinding, V : ViewModel> : AppCompatActivity() {

    private var _binding: ViewBinding? = null
    private lateinit var mViewModel: V

    @Suppress("UNCHECKED_CAST")
    protected val binding: B
        get() = _binding as B

    protected val vm: V
        get() = mViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewBinding(layoutInflater)
        setContentView(requireNotNull(_binding).root)

        mViewModel = ViewModelProvider(this).get(getViewModelClass())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    abstract val getViewBinding: (LayoutInflater) -> B
    abstract fun getViewModelClass(): Class<V>
}