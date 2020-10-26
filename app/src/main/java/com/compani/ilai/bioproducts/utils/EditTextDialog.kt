package com.compani.ilai.bioproducts.utils

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.InputType
import android.view.*
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.compani.ilai.bioproducts.R
import kotlinx.android.synthetic.main.dialog_edit_text.*
import kotlinx.android.synthetic.main.dialog_edit_text.view.*
import kotlin.coroutines.coroutineContext

@Suppress("UNREACHABLE_CODE")
class EditTextDialog: DialogFragment() {

    companion object {
        private const val TAG = "TAG"
        private const val EXTRA_TITLE = "title"
        private const val EXTRA_HINT = "hint"
        private const val EXTRA_MULTILINE = "multiline"
        private const val EXTRA_TEXT = "text"

        fun newInstance(
            title: String? = null,
            hint: String? = null,
            text: String? = null,
            isMultiline: Boolean = false
        ): EditTextDialog {
            val dialog = EditTextDialog()
            val arg = Bundle().apply {
                putString(EXTRA_TITLE, title)
                putString(EXTRA_HINT, hint)
                putString(EXTRA_TEXT, text)
                putBoolean(EXTRA_MULTILINE, isMultiline)
            }
            dialog.arguments = arg
            return dialog
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = false
        return inflater.inflate(R.layout.dialog_edit_text, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val title = arguments?.getString(EXTRA_TITLE)
        val hint = arguments?.getString(EXTRA_TITLE)
        val isMultiline = arguments?.getBoolean(EXTRA_MULTILINE)

        dialog_title.text = title
        et_dialog.hint = "Enter: $title"
        if (isMultiline!!) {
            et_dialog.maxLines = 3
            et_dialog.inputType = InputType.TYPE_TEXT_FLAG_MULTI_LINE or InputType.TYPE_TEXT_FLAG_CAP_SENTENCES or InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
        }
    }


}