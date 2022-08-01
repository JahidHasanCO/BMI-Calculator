package dev.jahidhasanco.bmicalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.sccomponents.gauges.library.ScArcGauge
import dev.jahidhasanco.bmicalculator.databinding.ActivityMainBinding
import dev.jahidhasanco.bmicalculator.presentation.adapter.WeightPickerAdapter
import travel.ithaka.android.horizontalpickerlib.PickerLayoutManager


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val _binding get() = binding

    lateinit var weightAdapter:WeightPickerAdapter

    private val mAngle = 1f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        _binding.genderMeter.angleStart = mAngle

        val pickerLayoutManager = PickerLayoutManager(this, PickerLayoutManager.HORIZONTAL, false)
        pickerLayoutManager.apply {
            isChangeAlpha = true
            scaleDownBy = 0.99f
            scaleDownDistance = 0.8f
        }


        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(_binding.weightRecyclerBtn)

        weightAdapter = WeightPickerAdapter(this, getData(100), _binding.weightRecyclerBtn)

        _binding.weightRecyclerBtn.apply {
            layoutManager = pickerLayoutManager
            adapter = weightAdapter
        }


        pickerLayoutManager.setOnScrollStopListener {

        }

    }

    fun getData(count: Int): List<String> {
        val data: MutableList<String> = ArrayList()
        for (i in 0 until count) {
            data.add(i.toString())
        }
        return data
    }
}