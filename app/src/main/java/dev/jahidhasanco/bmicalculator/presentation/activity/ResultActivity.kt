package dev.jahidhasanco.bmicalculator.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import dev.jahidhasanco.bmicalculator.R
import dev.jahidhasanco.bmicalculator.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    val _binding get() = binding

    var weight : Double = 0.0
    var height : Double = 0.0
    var result : Double = 0.0
    var gender : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_result)

        weight = intent.getDoubleExtra("Weight",50.0)
        height = intent.getDoubleExtra("Height",1.0)
        gender = intent.getIntExtra("Gender",0)

        bmiCal()

        _binding.reloadBtn.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

    }

    fun bmiCal(){
        if(height>0 && weight>0){
            if(gender==0){
                bmiCalMale()
            }
            else if(gender == 1){
                bmiCalFemale()
            }
            showResult()
        }

    }

    private fun showResult() {

        val solution = String.format("%.1f", result)

        _binding.apply {
            resultText.text = solution
            bmiText.text = "BMI = $solution kg/m2"
        }

        // update the status text as per the bmi conditions
//        if (BMI < 18.5) {
//            status.text = "Under Weight"
//        } else if (BMI >= 18.5 && BMI < 24.9) {
//            status.text = "Healthy"
//        } else if (BMI >= 24.9 && BMI < 30) {
//            status.text = "Overweight"
//        } else if (BMI >= 30) {
//            status.text = "Suffering from Obesity"
//        }

    }

    fun bmiCalMale(){
        result = ((weight/(height*height))*10000)
    }
    fun bmiCalFemale(){
        result = ((weight/(height*height))*10000)
    }

}