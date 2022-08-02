package dev.jahidhasanco.bmicalculator

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.cncoderx.wheelview.OnWheelChangedListener
import dev.jahidhasanco.bmicalculator.databinding.ActivityMainBinding

import dev.jahidhasanco.bmicalculator.presentation.adapter.WeightPickerAdapter
import travel.ithaka.android.horizontalpickerlib.PickerLayoutManager
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val _binding get() = binding

    lateinit var weightAdapter:WeightPickerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val titlesofGender: List<String> = listOf("M","O", "F")

        _binding.genderWheelView.apply {
            titles = titlesofGender
            elevation = 0f
        }
        _binding.genderWheelView.selectListener =  {
            Toast.makeText(this@MainActivity,"Click on WheelView" + titlesofGender[it] ,Toast.LENGTH_SHORT).show()
        }




        val pickerLayoutManager = PickerLayoutManager(this, PickerLayoutManager.HORIZONTAL, false)
        pickerLayoutManager.apply {
            isChangeAlpha = true
            scaleDownBy = 0.99f
            scaleDownDistance = 0.8f
        }


        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(_binding.weightRecyclerBtn)

        weightAdapter = WeightPickerAdapter(this, getData(102), _binding.weightRecyclerBtn)

        _binding.weightRecyclerBtn.apply {
            layoutManager = pickerLayoutManager
            adapter = weightAdapter
        }


        pickerLayoutManager.setOnScrollStopListener {

        }

        _binding.heightWheel.onWheelChangedListener =
            OnWheelChangedListener { view, oldIndex, newIndex ->
                val text = view.getItem(newIndex)
                Log.i("WheelView", String.format("index: %d, text: %s", newIndex, text))
            }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            _binding.heightWheel.defaultFocusHighlightEnabled = true
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