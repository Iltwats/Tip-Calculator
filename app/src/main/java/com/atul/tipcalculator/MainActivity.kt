package com.atul.tipcalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.atul.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.*
import kotlin.math.roundToInt
import kotlin.math.roundToLong

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var billAmount: Int = 0
    private var percentTip: Float = 0.0f
    private var totalBill: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculate.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
            // Bill Amount entered by the user
            if (binding.textInputEditText.text.toString().isNotEmpty()) {
                billAmount = binding.textInputEditText.text.toString().toIntOrNull()!!
            }

            // Percentage tip calculator
            percentTip = when (binding.radioGroup.checkedRadioButtonId) {
                R.id.amazing -> 0.2F
                R.id.good -> 0.15F
                R.id.ok ->  0.1F
                else -> 0.0F
            }

            // Tip
            var tip = billAmount*percentTip

            // Calculate total bill with tip percentage
            totalBill = (billAmount + billAmount * percentTip).toDouble()

            // Show only upto 4 decimal places after amount
            totalBill = (totalBill * 10000.0).roundToLong() / 10000.0

            // Round off the number to nearest Integer
            if (binding.switchButton.isChecked) {
                totalBill = totalBill.roundToInt().toDouble()
                tip = kotlin.math.ceil(tip)
            }

            // Show the amount to user
            /*
                val local = ConfigurationCompat.getLocales(Resources.getSystem().configuration).get(0);
                Toast.makeText(this,local.toString(),Toast.LENGTH_SHORT).show()
             */
            val formattedTip = NumberFormat.getCurrencyInstance(Locale("en","in")).format(tip)
            binding.finalAmount.text = "Tip Amount: $formattedTip"
    }


}