package android.example.midterm;

import android.app.Dialog;
import android.content.Context;
import android.example.midterm.db.DatabaseOpenHelper;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Activity_List_DB extends AppCompatActivity {
    int id_clicked;
    String table_name;

    TextView textView;

    RecyclerView recyclerView2;

    List<RawInfo> raws2;

    DatabaseOpenHelper access;

    Dialog dialog;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__list__d_b);
        context = this;

        id_clicked = getIntent().getIntExtra("Session_ID", 0);
        table_name = getIntent().getStringExtra("Table_name");

        access = new DatabaseOpenHelper(this);

        raws2 = access.retrieveTableCurWithDates(table_name);

        access.close();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(table_name);

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_import);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        recyclerView2 = (RecyclerView) findViewById(R.id.recycle_view_2);

        RecyclerViewAdapter2 recyclerViewAdapter2 = new RecyclerViewAdapter2(this, raws2, table_name);

        recyclerView2.setLayoutManager(new LinearLayoutManager(this));

        recyclerView2.setAdapter(recyclerViewAdapter2);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_db);
        fab.bringToFront();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText_title= (EditText) dialog.findViewById(R.id.title_import);
                EditText editText_amount = (EditText) dialog.findViewById(R.id.amount_import);
                ImageButton imageButton = (ImageButton) dialog.findViewById(R.id.button_import);

                editText_title.setText("test");
                editText_amount.setText("test_amount");

                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseOpenHelper access = new DatabaseOpenHelper(dialog.getContext());
                        String title = editText_title.getText().toString();
                        int amount = Integer.parseInt(editText_amount.getText().toString());

                        access.insert(table_name, title, amount);
                        Toast.makeText(v.getContext(), "Created!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        ((Activity_List_DB) context).onResume();
                    }
                });

                dialog.show();
//              dialog.dismiss();
//                ((Activity_List_DB) context).onResume();
            }
        });
    }



    @Override
    protected void onResume(){
        super.onResume();

        access = new DatabaseOpenHelper(this);

        raws2 = access.retrieveTableCurWithDates(table_name);

        Toast.makeText(this, "Resume", Toast.LENGTH_SHORT);

        recyclerView2 = (RecyclerView) findViewById(R.id.recycle_view_2);

        RecyclerViewAdapter2 recyclerViewAdapter2 = new RecyclerViewAdapter2(this, raws2, table_name);

        recyclerView2.setLayoutManager(new LinearLayoutManager(this));

        recyclerView2.setAdapter(recyclerViewAdapter2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}