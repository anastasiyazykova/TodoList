package nsu.android.todolist.View;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import nsu.android.todolist.Presenter.PresenterCreate;
import nsu.android.todolist.Presenter.PresenterList;
import nsu.android.todolist.R;

public class CreateNoteActivity extends AppCompatActivity {

    PresenterCreate presenter;

    FloatingActionButton buttonOk;
    FloatingActionButton buttonBack;

    TextInputEditText name;
    TextInputEditText shortText;
    TextInputEditText fullText;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        presenter = new PresenterCreate(this);

        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        buttonOk = findViewById(R.id.finishcreate);
        buttonBack = findViewById(R.id.back_button);

        name = findViewById(R.id.task_name);
        shortText = findViewById(R.id.short_text);
        fullText = findViewById(R.id.full_text);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.newNoteEvent(name.getText().toString(), shortText.getText().toString(), fullText.getText().toString());
                //обновить главную
                //System.out.println("finish create ivent");
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

