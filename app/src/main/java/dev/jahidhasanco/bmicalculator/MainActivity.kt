package dev.jahidhasanco.bmicalculator

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.cncoderx.wheelview.OnWheelChangedListener
import dev.jahidhasanco.bmicalculator.databinding.ActivityMainBinding
import dev.jahidhasanco.bmicalculator.presentation.adapter.WeightPickerAdapter
import dev.jahidhasanco.bmicalculator.utils.displayToast
import travel.ithaka.android.horizontalpickerlib.PickerLayoutManager


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val _binding get() = binding

    lateinit var weightAdapter: WeightPickerAdapter
    var gender = 'M'
    var height = 1
    var weight = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

//        Gender
        val titlesofGender: List<String> = listOf("F", "O", "M")

        _binding.genderWheelView.apply {
            titles = titlesofGender
            elevation = 0f
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                isFocusedByDefault = true
            }
            isSelected = true
            focusedIndex = 2
        }
        _binding.genderWheelView.selectListener = {
           gender = titlesofGender[it][0]
        }


//        Weight
        val pickerLayoutManager = PickerLayoutManager(this, PickerLayoutManager.HORIZONTAL, false)
        pickerLayoutManager.apply {
            isChangeAlpha = true
            scaleDownBy = 0.99f
            scaleDownDistance = 0.8f
            initialPrefetchItemCount = 3
            isSmoothScrollbarEnabled = true
            scrollToPosition(50)
        }


        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(_binding.weightRecyclerBtn)

        weightAdapter = WeightPickerAdapter(this, getData(101), _binding.weightRecyclerBtn)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            _binding.weightRecyclerBtn.defaultFocusHighlightEnabled = true
        }
        _binding.weightRecyclerBtn.apply {
            layoutManager = pickerLayoutManager
            adapter = weightAdapter
            isSelected = true
            requestFocus()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                isFocusedByDefault = true
            }


        }


        pickerLayoutManager.setOnScrollStopListener { view ->
            weight = Integer.parseInt((view as TextView).text.toString())
        }


//        Height
        _binding.heightWheel.onWheelChangedListener =
            OnWheelChangedListener { view, oldIndex, newIndex ->
                val text = view.getItem(newIndex)
                height = Integer.parseInt(text.toString())
//                Toast.makeText(this,""+ text,Toast.LENGTH_SHORT).show()

            }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            _binding.heightWheel.apply {
                defaultFocusHighlightEnabled = true
            }
        }

        _binding.startButton.setOnClickListener {
            displayToast("Height: $height Weight: $weight Gender: $gender")
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