package nsu.android.todolist.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.TextView;

import nsu.android.todolist.Presenter.PresenterDetail;
import nsu.android.todolist.R;

public class DetailNote extends AppCompatActivity {

    public TextView name;
    public TextView shortText;
    public TextView fullText;
    public TextView createDate;
    public TextView changeDate;
    public AppCompatCheckBox checkBox;
    FloatingActionButton deleteButton;

    PresenterDetail presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_note);

        presenter.setData(getIntent());

        deleteButton = findViewById(R.id.detail_del_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAlertDialog();
            }
        });

    }

    private void createAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailNote.this);
        builder.setTitle("Отмена");
        builder.setMessage("Вы уверены, что хотите удалить заметку?");
        builder.setNegativeButton("Нет",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        builder.setPositiveButton("Да",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.deleteEvent(name.toString());
                    }
                });
        builder.show();
    }
}
