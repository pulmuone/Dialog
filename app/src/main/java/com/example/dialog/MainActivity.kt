package com.example.dialog

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import androidx.appcompat.app.AlertDialog
import com.example.dialog.databinding.ActivityMainBinding
import com.example.dialog.databinding.CustomDialogBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            var builder = AlertDialog.Builder(this)
            builder.setTitle("기본 다이얼로그")
            builder.setMessage("기본 다이얼로그 입니다.")
            builder.setIcon(R.mipmap.ic_launcher)

            builder.setPositiveButton("Positive") { dialogInterface: DialogInterface, i: Int ->
                binding.textView.text = "Positive 버튼을 눌렀습니다."
            }
            builder.setNeutralButton("Neutral") { dialogInterface: DialogInterface, i: Int ->
                binding.textView.text = "Neutral 버튼을 눌렀습니다."
            }
            builder.setNegativeButton("Negative") { dialogInterface: DialogInterface, i: Int ->
                binding.textView.text = "Negative 버튼을 눌렀습니다."
            }
            builder.show()
        }

        binding.button2.setOnClickListener {
             val builder = AlertDialog.Builder(this)
            builder.setTitle("커스텀 다이얼로그")
            builder.setIcon(R.mipmap.ic_launcher)

            //val custom_view = layoutInflater.inflate(R.layout.custom_dialog, null)
            var bindingCust : CustomDialogBinding
            bindingCust = CustomDialogBinding.inflate(layoutInflater)

            builder.setView(bindingCust.root)
            builder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
                bindingCust.run {
                    binding.textView.text = "${bindingCust.customEdit1.text}\n"
                    binding.textView.append("${bindingCust.customEdit2.text}\n")
                }
            }
            builder.setNegativeButton("취소", null)
            builder.show()
        }

        binding.button3.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONDAY)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val listener1 =
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    binding.textView.text = "${year}년 ${month+1}월 ${dayOfMonth}일"
                }

            val picker = DatePickerDialog(this, listener1, year, month, day)
            picker.show()
        }

        binding.button4.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR)
            val minute = calendar.get(Calendar.MINUTE)

            val listener2 = TimePickerDialog.OnTimeSetListener {view, hourOfDay, minute ->
                binding.textView.text = "${hourOfDay}시 ${minute }분"
            }

            val picker = TimePickerDialog(this, listener2, hour, minute, true)
            picker.show()
        }
    }
}