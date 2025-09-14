package com.example.crewlyx.Activitys

import android.graphics.Color
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.example.crewlyx.R
import com.example.crewlyx.databinding.ActivitySwapingBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import eightbitlab.com.blurview.BlurView
import eightbitlab.com.blurview.RenderScriptBlur

class SwapingActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySwapingBinding.inflate(layoutInflater)
    }
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupBottomSheet()
        setupBlurView()
    }

    private fun setupBottomSheet() {
        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet)

        bottomSheetBehavior.apply {
            state = BottomSheetBehavior.STATE_COLLAPSED
            isHideable = false
            addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    when (newState) {
                        BottomSheetBehavior.STATE_EXPANDED -> {
                            binding.expandedContent1.visibility = View.VISIBLE
                            binding.expandedContent2.visibility = View.VISIBLE
                            binding.actionButton.visibility = View.VISIBLE
                        }

                        BottomSheetBehavior.STATE_COLLAPSED -> {
                            binding.expandedContent1.visibility = View.GONE
                            binding.expandedContent2.visibility = View.GONE
                            binding.actionButton.visibility = View.GONE
                        }
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    // animation ka code yaha aa sakta hai
                }
            })
        }
    }

    private fun setupBlurView() {
        val rootView = window.decorView.findViewById<ViewGroup>(android.R.id.content)
        binding.blurView.setupWith(rootView)
            .setBlurRadius(15f)
            .setBlurAutoUpdate(true)

    }
}