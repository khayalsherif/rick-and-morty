package az.khayalsharifli.rickandmorty.ui.content.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import az.khayalsharifli.rickandmorty.R
import az.khayalsharifli.rickandmorty.databinding.DialogFilterBottomSheetBinding
import az.khayalsharifli.rickandmorty.tools.DialogSelectedClickListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import viewBinding

class FilterBottomSheetDialog(
    private val dialogSelectedClickListener: DialogSelectedClickListener
) : BottomSheetDialogFragment() {

    private val binding by viewBinding(DialogFilterBottomSheetBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.dialog_filter_bottom_sheet, container, false)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.transparentDialogTheme)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply.setOnClickListener {
            dialogSelectedClickListener.onDialogSelectedClick(
                it.id,
                getSelectedStatusText(),
                getSelectedGenderText()
            )
            dialog?.dismiss()
        }
    }

    private fun getSelectedStatusText(): String {
        return if (binding.chipDead.isChecked) binding.chipDead.text.toString()
        else if (binding.chipAlive.isChecked) binding.chipAlive.text.toString()
        else if (binding.chipUnknown.isChecked) binding.chipUnknown.text.toString()
        else ""
    }

    private fun getSelectedGenderText(): String {
        return if (binding.rbFemale.isChecked) binding.rbFemale.text.toString()
        else if (binding.rbMale.isChecked) binding.rbMale.text.toString()
        else if (binding.rbGenderless.isChecked) binding.rbGenderless.text.toString()
        else if (binding.rbUnknown.isChecked) binding.rbUnknown.text.toString()
        else ""
    }


}