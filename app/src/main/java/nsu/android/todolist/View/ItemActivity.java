package nsu.android.todolist.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import nsu.android.todolist.Presenter.PresenterItem;
import nsu.android.todolist.R;

public class ItemActivity extends AppCompatActivity {

    public LinearLayout layout;
    PresenterItem presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_list_item);
        presenter = new PresenterItem(this);

        layout = findViewById(R.id.linear);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.detailsEvent();
            }
        });
    }
}
