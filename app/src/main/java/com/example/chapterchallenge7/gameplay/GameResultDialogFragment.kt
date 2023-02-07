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
import com.example.chapterchallenge7.databinding.FragmentGameResultDialogBinding
import com.example.chapterchallenge7.mainmenu.MainMenuActivity
import com.example.chapterchallenge7.playermode.PlayerModeActivity

class GameResultDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentGameResultDialogBinding
    private lateinit var listener: ResultDialogListener
    private lateinit var playerOne: SharedPreferences
    private lateinit var playerTwo: SharedPreferences
    private lateinit var mViewModel: ViewModel



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

        mViewModel = ViewModelProvider(this)[ViewModel::class.java]

        playerOne = mViewModel.createSharedPreferences("player_one")
        playerTwo = mViewModel.createSharedPreferences("player_two")

    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.tvGameResultWinnerDialog.text = calculateResult()
        binding.tvScoreResult.text = "${mViewModel.getScore(playerOne)}:${mViewModel.getScore(playerTwo)}"
        binding.tvPlayerOneNameOnScore.text = mViewModel.getName(playerOne, "Player 1")
        binding.tvPlayerTwoNameOnScore.text = mViewModel.getName(playerTwo, "Player 2")



        binding.ivCloseDialogButton.setOnClickListener {
            dismiss()
        }

        binding.btnPlayAgainDialog.setOnClickListener {
            listener.resetAllTextAndItems()
            dismiss()
        }

        binding.btnNewGameDialog.setOnClickListener {
            listener.resetAllTextAndItems()
            mViewModel.resetScore(playerOne)
            mViewModel.resetScore(playerTwo)
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

    fun calculateResult(): String {
        if (mViewModel.getItem(playerOne) == mViewModel.getItem(playerTwo)) {
            return "DRAW!"
        } else if (mViewModel.getItem(playerOne) == "KERTAS" && mViewModel.getItem(playerTwo) == "BATU") {
            mViewModel.addScore(playerOne)
            return "${mViewModel.getName(playerOne, "Player 1")}\n MENANG!"
        } else if (mViewModel.getItem(playerOne) == "GUNTING" && mViewModel.getItem(playerTwo) == "KERTAS") {
            mViewModel.addScore(playerOne)
            return "${mViewModel.getName(playerOne, "Player 1")}\n MENANG!"
        } else if (mViewModel.getItem(playerOne) == "BATU" && mViewModel.getItem(playerTwo) == "GUNTING") {
            mViewModel.addScore(playerOne)
            return "${mViewModel.getName(playerOne, "Player 1")}\n MENANG!"
        } else {
            mViewModel.addScore(playerTwo)
            return "${mViewModel.getName(playerTwo, "Player 2")}\n MENANG!"
        }
    }

}
