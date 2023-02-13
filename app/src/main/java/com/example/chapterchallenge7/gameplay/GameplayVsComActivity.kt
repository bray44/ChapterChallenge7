package com.example.chapterchallenge7.gameplay

import android.annotation.SuppressLint
import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.forEach
import androidx.lifecycle.ViewModelProvider
import com.example.chapterchallenge7.R
import com.example.chapterchallenge7.databinding.ActivityGameplayVsPlayerBinding
import com.example.chapterchallenge7.mainmenu.MainMenuActivity
import com.example.chapterchallenge7.playermode.PlayerModeActivity
import kotlin.random.Random

class GameplayVsComActivity : AppCompatActivity(), GameResultDialogFragment.ResultDialogListener {

    private lateinit var soundPool: SoundPool
    private val MAX_STREAM = 1
    private var loaded = false

    private lateinit var binding: ActivityGameplayVsPlayerBinding
    private lateinit var mGameplayViewModel: GameplayViewModel
    private lateinit var playerOne: GameplayData
    private lateinit var playerTwo: GameplayData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameplayVsPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mGameplayViewModel = ViewModelProvider(this)[GameplayViewModel::class.java]

        playerOne = mGameplayViewModel.createGameplayData("player_one")
        playerTwo = mGameplayViewModel.createGameplayData("player_two")

        mGameplayViewModel.getName(playerOne, "Player 1")
        mGameplayViewModel.getName(playerTwo, "COM")


        binding.tvPlayerOneName.text = mGameplayViewModel.getName(playerOne, "Player 1")
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
            mGameplayViewModel.resetScore(playerOne)
            mGameplayViewModel.resetScore(playerTwo)
        }

        binding.ivPlayerModeButton.setOnClickListener {
            startActivity(Intent(this, PlayerModeActivity::class.java))
            mGameplayViewModel.resetScore(playerOne)
            mGameplayViewModel.resetScore(playerTwo)
        }

        binding.ivRestartGameButton.setOnClickListener {
            resetGameText()
            resetAllItems()
        }
    }

    private fun showWinnerSoundEffect() {
        val audioManager = getSystemService(AUDIO_SERVICE) as AudioManager
        val actualVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC).toFloat()
        val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC).toFloat()
        val volume = actualVolume / maxVolume



        val audioAttribute = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        val builder = SoundPool.Builder()
        builder.setAudioAttributes(audioAttribute).setMaxStreams(MAX_STREAM)
        soundPool = builder.build()



        soundPool.setOnLoadCompleteListener { _, _, _ ->
            loaded = true
        }

        val soundWin = soundPool.load(this, R.raw.win, 1)
        val soundLose = soundPool.load(this, R.raw.lose, 1)

        if (mGameplayViewModel.getPlayerOneResult()) {
            // code to show win sound effect
            soundPool.play(soundWin, volume, volume, 1, 0, 1F)
        } else {
            // code to show lose sound effect
            soundPool.play(soundLose, volume, volume, 1, 0, 1F)
        }
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

    private fun setChosenItemTo(player: GameplayData) {
        when (player) {
            playerOne -> when {
                binding.ivPlayerOneGunting.isSelected -> mGameplayViewModel.setItem(
                    playerOne,
                    "GUNTING"
                )
                binding.ivPlayerOneBatu.isSelected -> mGameplayViewModel.setItem(playerOne, "BATU")
                binding.ivPlayerOneKertas.isSelected -> mGameplayViewModel.setItem(
                    playerOne,
                    "KERTAS"
                )
            }

            playerTwo -> when {
                binding.ivPlayerTwoGunting.isSelected -> mGameplayViewModel.setItem(
                    playerTwo,
                    "GUNTING"
                )
                binding.ivPlayerTwoBatu.isSelected -> mGameplayViewModel.setItem(playerTwo, "BATU")
                binding.ivPlayerTwoKertas.isSelected -> mGameplayViewModel.setItem(
                    playerTwo,
                    "KERTAS"
                )
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
            "${
                mGameplayViewModel.getName(
                    playerOne,
                    "Player 1"
                )
            }\n memilih ${mGameplayViewModel.getItem(playerOne)}."
        binding.tvPlayerTwoMessage.text =
            "${
                mGameplayViewModel.getName(
                    playerTwo,
                    "Player 2"
                )
            }\n memilih ${mGameplayViewModel.getItem(playerTwo)}."
    }

    private fun showGameResultDialog() {
        val fragmentManager = supportFragmentManager
        val winnerDialogFragment = GameResultDialogFragment()
        winnerDialogFragment.show(fragmentManager, "fragment_winner_dialog")
    }

    private fun showGameResult() {
        showGameResultDialog()
        showWinnerSoundEffect()
        showTextOfPlayerChosenItem()
    }

    private fun allPlayersItemsIsEnabled(boolean: Boolean) {
        binding.clPlayerOneItemList.forEach { it.isEnabled = boolean }
        binding.clPlayerTwoItemList.forEach { it.isEnabled = boolean }
    }

    @SuppressLint("SetTextI18n")
    private fun resetGameText() {
        binding.tvPlayerOneMessage.text =
            "${mGameplayViewModel.getName(playerOne, "Player 1")},\n silahkan pilih item mu"
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