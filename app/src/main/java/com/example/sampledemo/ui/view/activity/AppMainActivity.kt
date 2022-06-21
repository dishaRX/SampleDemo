package com.example.sampledemo.ui.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.sampledemo.databinding.ActAppMainBinding
import com.example.sampledemo.extensions.showToolbar
import com.example.sampledemo.ui.view.fragment.DashboardFragment


class AppMainActivity : AppCompatActivity(){
     lateinit var binding: ActAppMainBinding
    private val fm = supportFragmentManager

    /**
     * Initial onCreate method
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActAppMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        this.showToolbar()
        pushFragment(DashboardFragment(),false)
    }

    /**
     * Replace/add fragment and back stack management
     */
    fun pushFragment(fragment: Fragment, isBackStack: Boolean) {
        val fragmentTransaction = fm.beginTransaction()
        fragmentTransaction.replace(binding.actDashboardFrameMain.id, fragment, fragment.javaClass.getSimpleName())
        if (isBackStack) {
            fragmentTransaction.addToBackStack(fragment.javaClass.simpleName)
        }
        fragmentTransaction.commit()
    }

}
