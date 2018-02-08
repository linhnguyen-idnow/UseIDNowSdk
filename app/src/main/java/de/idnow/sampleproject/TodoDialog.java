package de.idnow.sampleproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import de.idnow.R;

/**
 * Created by sean on 1/3/18.
 */

public class TodoDialog extends AppCompatDialogFragment {

    TodoDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View popupView = inflater.inflate(R.layout.popup_todo_creation, null);

        builder.setView(popupView)
                .setTitle("Task Creation ")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String taskName = ((EditText) popupView.findViewById(R.id.todo_name_adding)).getText().toString();
                        String taskDescription =((EditText) popupView.findViewById(R.id.todo_description_adding)).getText().toString();
                        listener.saveTaskData(taskName, taskDescription);
                    }
                });
        return builder.create();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (TodoDialogListener) context;
    }

    public interface TodoDialogListener {
        void saveTaskData(String name, String description);
    }
}