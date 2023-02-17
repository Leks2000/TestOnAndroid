package com.example.testunit

import android.graphics.PorterDuff
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val StartButton: Button = findViewById(R.id.ButtonStart)
        val CheckButton: Button = findViewById(R.id.ButtonCheck)
        val mEdit: EditText  = findViewById(R.id.Result)
        var count = 0
        var CountCorrect = 0
        var CountInCorrect = 0
        var result = ""
        StartButton.setOnClickListener {
            val FirstText: TextView = findViewById(R.id.TextFirstOperand)
            val SecondText: TextView = findViewById(R.id.TextSecondOperand)
            val OperationText: TextView = findViewById(R.id.Operation)
            mEdit.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.black), PorterDuff.Mode.SRC_ATOP)
            val FirstNum = (10..99).random()
            var SecNum = (10..99).random()
            val randSign = (1..4).random()
            when(randSign){
                1 -> OperationText.text = "*"
                2 -> OperationText.text = "/"
                3 -> OperationText.text = "+"
                4 -> OperationText.text = "-"
            }
            if(randSign == 1){
                result = (FirstNum * SecNum).toString()
            }
            if(randSign == 2){
                while (FirstNum % SecNum !== 0){
                    SecNum = (10..99).random()
                }
                result = (FirstNum  / SecNum).toString()
            }
            if(randSign == 3){
                result = (FirstNum + SecNum).toString()

            }
            if(randSign == 4){
                result = (FirstNum - SecNum).toString()
            }
            SecondText.text = SecNum.toString()
            FirstText.text = FirstNum.toString()
            CheckButton.isClickable = true
            StartButton.isClickable = false
            mEdit.isEnabled = true
            mEdit.isClickable = true
        }

        CheckButton.setOnClickListener {
            val AllTest: TextView = findViewById(R.id.ressultOfAllEx)
            val Percent: TextView = findViewById(R.id.ResultInPercent)
            val Correct: TextView = findViewById(R.id.Correct)
            val InCorrect: TextView = findViewById(R.id.Incorrect)
            count++
            AllTest.text = count.toString()
            if (result == mEdit.text.toString()){
                CountCorrect++
                Correct.text = CountCorrect.toString()
                mEdit.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_green_light), PorterDuff.Mode.SRC_ATOP)
            }else{
                CountInCorrect++
                InCorrect.text = CountInCorrect.toString()
                mEdit.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP)
            }
            Percent.text = (CountCorrect.toBigDecimal().divide(count.toBigDecimal(), 2, RoundingMode.HALF_UP)).toString() + "%"
            Toast.makeText(this, "Правильный ответ был:  ${result} Вы ввели ${mEdit.text}", Toast.LENGTH_SHORT).show()
            CheckButton.isClickable = false
            StartButton.isClickable = true
            mEdit.isEnabled = false
            mEdit.isClickable = false
        }
    }
}