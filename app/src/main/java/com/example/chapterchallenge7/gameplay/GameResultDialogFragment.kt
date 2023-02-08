package com.example.chapterchallenge7.gameplay

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.chapterchallenge7.R
import com.example.chapterchallenge7.databinding.FragmentGameResultDialogBinding
import com.example.chapterchallenge7.gamehistory.GameHistoryViewModel
import com.example.chapterchallenge7.mainmenu.MainMenuActivity
import com.example.chapterchallenge7.playermode.PlayerModeActivity

class GameResultDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentGameResultDialogBinding
    private lateinit var listener: ResultDialogListener
    private lateinit var playerOne: GameplayData
    private lateinit var playerTwo: GameplayData
    private lateinit var mGameplayViewModel: GameplayViewModel
    private lateinit var mGameHistoryViewModel: GameHistoryViewModel


    interface ResultDialogListener {
        fun resetAllTextAndItems()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = activity as ResultDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + "must implemented ResultDialogListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameResultDialogBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mGameplayViewModel = ViewModelProvider(this)[GameplayViewModel::class.java]
        mGameHistoryViewModel = ViewModelProvider(this)[GameHistoryViewModel::class.java]

        playerOne = mGameplayViewModel.createGameplayData("player_one")
        playerTwo = mGameplayViewModel.createGameplayData("player_two")
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Nama diberi toString agar tidak null
        val playerOneName = mGameplayViewModel.getName(playerOne, "Player 1").toString()
        val playerTwoName = mGameplayViewModel.getName(playerTwo, "Player 2").toString()


        val playerOneScore = mGameplayViewModel.getScore(playerOne)
        val playerTwoScore = mGameplayViewModel.getScore(playerTwo)

        binding.tvGameResultWinnerDialog.text = calculateResult()
        binding.tvScoreResult.text = "$playerOneScore:$playerTwoScore"
        binding.tvPlayerOneNameOnScore.text = playerOneName
        binding.tvPlayerTwoNameOnScore.text = playerTwoName
        saveGameDataToHistory()



        binding.ivCloseDialogButton.setOnClickListener {
            dismiss()
        }

        binding.btnPlayAgainDialog.setOnClickListener {
            listener.resetAllTextAndItems()
            dismiss()
        }

        binding.btnNewGameDialog.setOnClickListener {
            listener.resetAllTextAndItems()
            mGameplayViewModel.resetScore(playerOne)
            mGameplayViewModel.resetScore(playerTwo)
            val intent = Intent(activity, PlayerModeActivity::class.java)
            startActivity(intent)
            dismiss()
        }

        binding.btnBackToMenuDialog.setOnClickListener {
            val intent = Intent(activity, MainMenuActivity::class.java)
            startActivity(intent)
            dismiss()
        }
    }

    private fun calculateResult(): String {

        val playerOneItem = mGameplayViewModel.getItem(playerOne)
        val playerTwoItem = mGameplayViewModel.getItem(playerTwo)
        val playerOneWinText = "${mGameplayViewModel.getName(playerOne, "Player 1")}\n MENANG!"
        val playerTwoWinText = "${mGameplayViewModel.getName(playerTwo, "Player 2")}\n MENANG!"

        return if (playerOneItem == playerTwoItem) {
            "DRAW!"
        } else if (playerOneItem == "KERTAS" && playerTwoItem == "BATU") {
            mGameplayViewModel.addScore(playerOne)
            playerOneWinText
        } else if (playerOneItem == "GUNTING" && playerTwoItem == "KERTAS") {
            mGameplayViewModel.addScore(playerOne)
            playerOneWinText
        } else if (playerOneItem == "BATU" && playerTwoItem == "GUNTING") {
            mGameplayViewModel.addScore(playerOne)
            playerOneWinText
        } else {
            mGameplayViewModel.addScore(playerTwo)
            playerTwoWinText
        }
    }

    private fun saveGameDataToHistory() {
        mGameHistoryViewModel.addGameHistory(
            playerOneName = mGameplayViewModel.getName(playerOne, "Player 1").toString(),
            playerOneItem = changeItemToImage(mGameplayViewModel.getItem(playerOne)),
            playerOneScore = mGameplayViewModel.getScore(playerOne).toString(),
            playerTwoName = mGameplayViewModel.getName(playerTwo, "Player 2").toString(),
            playerTwoItem = changeItemToImage(mGameplayViewModel.getItem(playerTwo)),
            playerTwoScore = mGameplayViewModel.getScore(playerTwo).toString(),
            gameResult = calculateResult()

        )
    }

    // Function untuk mengubah string menjadi Int supaya item pilihan player bisa disimpan dalam Room database
    private fun changeItemToImage(item: String?): Int {
        return when (item) {
            "BATU" -> R.drawable.ic_batu
            "KERTAS" -> R.drawable.ic_kertas
            "GUNTING" -> R.drawable.ic_gunting
            else -> {
                0
            }
        }
    }
}





