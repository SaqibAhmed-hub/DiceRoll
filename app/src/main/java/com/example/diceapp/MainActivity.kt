package com.example.diceapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.diceapp.databinding.ActivityMainBinding

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: DiceViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //View Model Initialisation
        viewModel =  ViewModelProvider(this)[DiceViewModel::class.java]
        val random: MutableLiveData<Int> = viewModel.getRolls()

        //Observer
        random.observe(this,Observer{
            binding.txtView.text = "Dice :${it}"
            val imgDice = when (it.toString().toInt()) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                6 -> R.drawable.dice_6
                else -> 0
            }
            binding.imgDice.setImageResource(imgDice)

            //animation
            binding.imgDice.animate().apply {
                duration = 500
                rotationXBy(3600f)
                scaleX(2f)
                translationY(-50f)
            }.withEndAction {
                binding.imgDice.animate().apply {
                    duration = 500
                    rotationYBy(360f)
                    scaleX(1f)
                    translationY(50f)
                }
            }.start()
        })

        binding.btnRoll.setOnClickListener {
            viewModel.getRandomRolls()
            Log.i(TAG, viewModel.getRandomRolls().toString())
        }
    }

    companion object {
        private const val TAG: String = "Main Activity"
    }
}