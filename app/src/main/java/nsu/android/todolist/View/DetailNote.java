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
    FloatingActionButton changeButton;
    FloatingActionButton shareButton;

    PresenterDetail presenter;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        name.setText(data.getStringExtra("name"));
        shortText.setText(data.getStringExtra("shortText"));
        fullText.setText(data.getStringExtra("fullText"));
        changeDate.setText(data.getStringExtra("changeDate"));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_note);
        presenter = new PresenterDetail(this);

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            presenter.setData(intent.getExtras());
        }

        changeButton = findViewById(R.id.detail_change_button);
        deleteButton = findViewById(R.id.detail_del_button);
        shareButton = findViewById(R.id.detail_share_button);

        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onChangeEvent();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAlertDialog();
            }
        });
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendEvent();
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
                        presenter.deleteEvent(name.getText().toString());
                        DetailNote.this.finish();
                    }
                });
        builder.show();
    }
}
