package com.example.chapterchallenge7.gamehistory

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.chapterchallenge7.databinding.FragmentDeleteDataValidationDialogBinding
import com.example.chapterchallenge7.gameplay.GameResultDialogFragment


class GameHistoryWarningDialogFragment: DialogFragment() {

    private lateinit var binding: FragmentDeleteDataValidationDialogBinding
    private lateinit var mGameHistoryViewModel: GameHistoryViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDeleteDataValidationDialogBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mGameHistoryViewModel = ViewModelProvider(this)[GameHistoryViewModel::class.java]

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnYesDeleteData.setOnClickListener {
            mGameHistoryViewModel.deleteAllGameHistoryData()
            dismiss()
        }

        binding.btnNoDeleteData.setOnClickListener {
            dismiss()
        }

        binding.ivCloseButton.setOnClickListener {
            dismiss()
        }
    }

}