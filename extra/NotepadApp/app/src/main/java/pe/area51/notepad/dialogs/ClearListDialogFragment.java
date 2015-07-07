package pe.area51.notepad.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import pe.area51.notepad.R;

public class ClearListDialogFragment extends DialogFragment {

    public interface OnClearListListener {

        public void onClearList();

    }

    private OnClearListListener onClearListListener;

    public void setOnClearListListener(OnClearListListener onClearListListener) {
        this.onClearListListener = onClearListListener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        return alertDialogBuilder.setTitle(R.string.warning)
                .setMessage(R.string.clear_list_warning)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (onClearListListener != null) {
                            onClearListListener.onClearList();
                        }
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .create();
    }
}
