package nsu.android.todolist.View;

import android.content.DialogInterface;
import android.content.Intent;
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

    public TextInputEditText name;
    public TextInputEditText shortText;
    public TextInputEditText fullText;

    int flag;
    public String oldName;
    public String oldDate;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        presenter = new PresenterCreate(this);

        buttonOk = findViewById(R.id.finishcreate);
        buttonBack = findViewById(R.id.back_button);
        name = findViewById(R.id.task_name);
        shortText = findViewById(R.id.short_text);
        fullText = findViewById(R.id.full_text);

        final Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if (b != null) {
            flag = 2;
            presenter.setData(b);

        } else {
            flag = 1;
        }

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 1) {
                    presenter.newNoteEvent(name.getText().toString(), shortText.getText().toString(), fullText.getText().toString());
                }
                if (flag == 2) {
                    presenter.changeEvent(name.getText().toString(), shortText.getText().toString(), fullText.getText().toString());
                }
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

