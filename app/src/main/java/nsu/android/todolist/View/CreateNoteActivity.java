package nsu.android.todolist.View;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import nsu.android.todolist.R;

public class CreateNoteActivity extends AppCompatActivity {

    FloatingActionButton buttonOk;
    FloatingActionButton buttonBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_note);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buttonOk = findViewById(R.id.finishcreate_fab);
        buttonBack = findViewById(R.id.back_fab);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAlertDialog();
            }
        });
    }

    private void createAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(CreateNoteActivity.this);
        builder.setTitle("Отмена");
        builder.setMessage("Вы уверены, что хотите выйти? Ваши изменения не сохранятся");
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
                        CreateNoteActivity.this.finish();
                    }
                });
        builder.show();
    }
}

