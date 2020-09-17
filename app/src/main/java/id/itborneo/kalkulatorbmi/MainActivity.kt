package id.itborneo.kalkulatorbmi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        attachBtn()
    }

    private fun attachBtn() {
        btnCalculate.setOnClickListener {

            val height = etTinggi.text.toString().toDouble()
            val weight = etBerat.text.toString().toDouble()

            val bmiResult = calculateBmi(height, weight)

            updateUi(bmiResult)
        }
    }

    private fun updateUi(bmiResult: Double) {

        val mNumberFormat = NumberFormat.getInstance()
        mNumberFormat.maximumFractionDigits = (2)
        val bmiString = mNumberFormat.format(bmiResult)
        tvBmiResult.text = bmiString
        tvResult.text = category(bmiResult)

    }

    private fun category(bmi: Double): String {

        return when {
            bmi < 18.5 -> "Kurus"
            bmi < 24.9 -> "Normal"
            bmi < 29.9 -> "Gemuk"
            bmi >= 30 -> "Obesitas"
            else -> "Angka tidak valid"
        }
    }

    private fun calculateBmi(height: Double, weight: Double): Double {
        val heightToMeter = height / 100
        return weight / (heightToMeter * heightToMeter)
    }
}