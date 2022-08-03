package dev.jahidhasanco.bmicalculator.presentation.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setPadding
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.cncoderx.wheelview.OnWheelChangedListener
import dev.jahidhasanco.bmicalculator.R
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
    var weight = 50

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        animationView()
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
            scrollToPosition(49)
        }


        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(_binding.weightRecyclerBtn)

        weightAdapter = WeightPickerAdapter(this, getData(151), _binding.weightRecyclerBtn)


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
//            displayToast("Height: $height Weight: $weight Gender: $gender")
            val intent = Intent(this,ResultActivity::class.java)
            intent.putExtra("Height",height.toDouble())
            intent.putExtra("Weight",weight.toDouble())
            if(gender == 'M'){
                intent.putExtra("Gender",0)
            }
            else{
                intent.putExtra("Gender",1)
            }
            startActivity(intent)
        }

    }


    fun getData(count: Int): List<String> {
        val data: MutableList<String> = ArrayList()
        for (i in 0 until count) {
            data.add(i.toString())
        }
        return data
    }

    private fun animationView() {

        _binding.apply {

            bodyContainer.translationY = 50f
            footerContainer.translationY = 50f
            heightWheel.translationY = 50f
            weightRecyclerBtn.translationX = 50f
//            deleteBtn.translationY = 70f
//            reloadCardView.translationY = 70f
//            shareBtn.translationY = 70f

            bodyContainer.alpha = 0f
            footerContainer.alpha = 0f
            heightWheel.alpha = 0f
            weightRecyclerBtn.alpha = 0f
//            deleteBtn.alpha = 0f
//            reloadCardView.alpha = 0f
//            shareBtn.alpha = 0f


            bodyContainer.animate().translationY(0f).alpha(1f).setDuration(500).setStartDelay(300).start()
            footerContainer.animate().translationY(0f).alpha(1f).setDuration(500).setStartDelay(400).start()
            heightWheel.animate().translationY(0f).alpha(1f).setDuration(500).setStartDelay(450).start()
            weightRecyclerBtn.animate().translationX(0f).alpha(1f).setDuration(500).setStartDelay(500).start()
//            deleteBtn.animate().translationY(0f).alpha(1f).setDuration(500).setStartDelay(600).start()
//            reloadCardView.animate().translationY(0f).alpha(1f).setDuration(500).setStartDelay(750).start()
//            shareBtn.animate().translationY(0f).alpha(1f).setDuration(500).setStartDelay(900).start()


        }
    }


}