package com.example.chapterchallenge7.gameplay

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.forEach
import androidx.lifecycle.ViewModelProvider
import com.example.chapterchallenge7.R
import com.example.chapterchallenge7.databinding.ActivityGameplayVsPlayerBinding
import com.example.chapterchallenge7.mainmenu.MainMenuActivity
import kotlin.random.Random

class GameplayVsComActivity : AppCompatActivity(), GameResultDialogFragment.ResultDialogListener {

    private lateinit var binding: ActivityGameplayVsPlayerBinding
    private lateinit var mViewModel: ViewModel
    private lateinit var playerOne: SharedPreferences
    private lateinit var playerTwo: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameplayVsPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mViewModel = ViewModelProvider(this)[ViewModel::class.java]

        playerOne = mViewModel.createSharedPreferences("player_one")
        playerTwo = mViewModel.createSharedPreferences("player_two")

        mViewModel.getName(playerOne, "Player 1")
        mViewModel.getName(playerTwo, "COM")


        binding.tvPlayerOneName.text = mViewModel.getName(playerOne, "Player 1")
        resetGameText()

        val listenerForPlayerOne = View.OnClickListener { view ->

            view.isSelected = true
            setChosenItemTo(playerOne)
            playerTwoChoosingItem()
            showGameResult()
            allPlayersItemsIsEnabled(false)
        }

        val listenerForPlayerTwo = View.OnClickListener {
            val toast = Toast.makeText(this, "Klik item di bagian kiri", Toast.LENGTH_SHORT)
            toast.show()
        }



        binding.ivPlayerOneBatu.setOnClickListener(listenerForPlayerOne)
        binding.ivPlayerOneKertas.setOnClickListener(listenerForPlayerOne)
        binding.ivPlayerOneGunting.setOnClickListener(listenerForPlayerOne)




        binding.ivPlayerTwoBatu.setOnClickListener(listenerForPlayerTwo)
        binding.ivPlayerTwoKertas.setOnClickListener(listenerForPlayerTwo)
        binding.ivPlayerTwoGunting.setOnClickListener(listenerForPlayerTwo)

        binding.ivHomeButton.setOnClickListener {
            startActivity(Intent(this, MainMenuActivity::class.java))
        }

        binding.ivRestartGameButton.setOnClickListener {
            resetGameText()
            resetAllItems()
        }
    }

    private fun playerOneItemsIsEnabled(boolean: Boolean) {
        binding.clPlayerOneItemList.forEach { it.isEnabled = boolean }
    }

    private fun chooseRandomItem(): View {

        val listOfItems = listOf<View>(
            binding.ivPlayerTwoGunting,
            binding.ivPlayerTwoBatu,
            binding.ivPlayerTwoKertas
        )
        val index = Random.nextInt(0, 3)

        return listOfItems[index]
    }

    private fun setChosenItemTo(player: SharedPreferences) {
        when (player) {
            playerOne -> when {
                binding.ivPlayerOneGunting.isSelected -> mViewModel.setItem(playerOne, "GUNTING")
                binding.ivPlayerOneBatu.isSelected -> mViewModel.setItem(playerOne, "BATU")
                binding.ivPlayerOneKertas.isSelected -> mViewModel.setItem(playerOne, "KERTAS")
            }

            playerTwo -> when {
                binding.ivPlayerTwoGunting.isSelected -> mViewModel.setItem(playerTwo, "GUNTING")
                binding.ivPlayerTwoBatu.isSelected -> mViewModel.setItem(playerTwo, "BATU")
                binding.ivPlayerTwoKertas.isSelected -> mViewModel.setItem(playerTwo, "KERTAS")
            }
        }
    }

    private fun playerTwoChoosingItem() {
        chooseRandomItem().isSelected = true
        setChosenItemTo(playerTwo)
    }

    @SuppressLint("SetTextI18n")
    private fun showTextOfPlayerChosenItem() {
        binding.tvPlayerOneMessage.text =
            "${mViewModel.getName(playerOne, "Player 1")}\n memilih ${mViewModel.getItem(playerOne)}."
        binding.tvPlayerTwoMessage.text =
            "${mViewModel.getName(playerTwo, "Player 2")}\n memilih ${mViewModel.getItem(playerTwo)}."
    }

    private fun showGameResultDialog() {
        val fragmentManager = supportFragmentManager
        val winnerDialogFragment = GameResultDialogFragment()
        winnerDialogFragment.show(fragmentManager, "fragment_winner_dialog")
    }

    private fun showGameResult() {
        showGameResultDialog()
        showTextOfPlayerChosenItem()
    }

    private fun allPlayersItemsIsEnabled(boolean: Boolean) {
        binding.clPlayerOneItemList.forEach { it.isEnabled = boolean }
        binding.clPlayerTwoItemList.forEach { it.isEnabled = boolean }
    }

    @SuppressLint("SetTextI18n")
    private fun resetGameText() {
        binding.tvPlayerOneMessage.text =
            "${mViewModel.getName(playerOne, "Player 1")},\n silahkan pilih item mu"
        binding.tvPlayerTwoMessage.text = ""
    }

    override fun resetAllTextAndItems() {
        resetGameText()
        resetAllItems()
    }

    private fun resetAllItems() {
        allPlayersItemsIsEnabled(true)
        allPlayersItemsIsUnselected()
    }

    private fun allPlayersItemsIsUnselected() {
        binding.clPlayerOneItemList.forEach { it.isSelected = false }
        binding.clPlayerTwoItemList.forEach { it.isSelected = false }
    }

}