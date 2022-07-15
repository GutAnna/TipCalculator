package org.hyperskill.calculator.tip

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.google.android.material.slider.Slider
import java.math.RoundingMode
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val edit = findViewById<EditText>(R.id.edit_text)
        val slider = findViewById<Slider>(R.id.slider)

        edit.doOnTextChanged { _, _, _, _ ->
                updateTextView(edit,slider)
        }

        slider.addOnChangeListener(Slider.OnChangeListener { _, _, _ ->
            updateTextView(edit,slider)
        })
    }

    fun updateTextView(edit: EditText, slider: Slider) {
        val tipResult = findViewById<TextView>(R.id.text_view)

        if (edit.text.isEmpty()) tipResult.text = "" else {
            val decimalFormat = DecimalFormat("0.00")
            decimalFormat.roundingMode = RoundingMode.HALF_UP

            val billValue = edit.text.toString().toDouble()
            val tipPercent = slider.value.toDouble()

            val result: Double = billValue * tipPercent / 100.0
            val tipValue: String = decimalFormat.format(result)

            tipResult.text = "Tip amount: $tipValue"
        }
    }
}