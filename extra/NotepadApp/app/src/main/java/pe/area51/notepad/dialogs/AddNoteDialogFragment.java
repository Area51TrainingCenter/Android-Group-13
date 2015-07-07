package pe.area51.notepad.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import pe.area51.notepad.R;
import pe.area51.notepad.models.Note;

public class AddNoteDialogFragment extends DialogFragment {

    private OnNoteCreatedListener onNoteCreatedListener;

    public void setOnNoteCreatedListener(final OnNoteCreatedListener onNoteCreatedListener) {
        this.onNoteCreatedListener = onNoteCreatedListener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        final LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        final View contentView = layoutInflater.inflate(R.layout.dialog_add_item, null);
        return alertDialogBuilder.setView(contentView)
                .setTitle(R.string.insert_note)
                .setPositiveButton(R.string.insert_note, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String title = ((TextView) contentView.findViewById(R.id.dialog_add_item_edittext_title)).getText().toString();
                        final String content = ((TextView) contentView.findViewById(R.id.dialog_add_item_edittext_content)).getText().toString();
                        final Note note = new Note(0, title, content, System.currentTimeMillis(), System.currentTimeMillis());
                        if (onNoteCreatedListener != null) {
                            onNoteCreatedListener.onNoteCreated(note);
                        }
                    }
                })
                .create();
    }

    public interface OnNoteCreatedListener {

        public void onNoteCreated(Note note);

    }

}
