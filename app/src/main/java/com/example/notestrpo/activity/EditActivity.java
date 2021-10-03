package com.example.notestrpo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notestrpo.R;
import com.example.notestrpo.adapter.RecyclerViewColorAdapter;
import com.example.notestrpo.database.NoteDbHandler;
import com.example.notestrpo.model.ColorModel;
import com.example.notestrpo.model.NoteModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EditActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private BottomSheetDialog bottomSheetDialog;
    private List<ColorModel> colorList;
    View bottomSheetView;

    private boolean editMode = false;
    private NoteModel editNote;

    private NoteDbHandler database;

    private EditText title, subtitle, desc;
    private TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        init();
        setupToolbar();
        setupBottomSheetDialog();
        setupRecyclerView();

    }

    private void init(){
        title = findViewById(R.id.title);
        subtitle = findViewById(R.id.subtitle);
        desc = findViewById(R.id.description);
        time = findViewById(R.id.time_text_view);



        toolbar = findViewById(R.id.toolbar);

        colorList = new ArrayList<>();
        colorList.add(new ColorModel(R.color.colorRed));
        colorList.add(new ColorModel(R.color.colorPink));
        colorList.add(new ColorModel(R.color.colorYellow));
        colorList.add(new ColorModel(R.color.colorGreen));
        colorList.add(new ColorModel(R.color.colorDarkBlue));
        colorList.add(new ColorModel(R.color.colorPurple));
        colorList.add(new ColorModel(R.color.colorOrange));
        colorList.add(new ColorModel(R.color.colorWhite));

        if(getIntent().getExtras() != null) {
            editMode = true;
            editNote = (NoteModel) getIntent().getSerializableExtra("EditNote");

            assert editNote != null;
            title.setText(editNote.getTitle());
            subtitle.setText(editNote.getSubtitle());
            desc.setText(editNote.getDescription());
        }
        //database = new NoteDbHandler(this);
    }

    private void setupToolbar() {
        //toolbar.setTitle(getResources().getString(R.string.new_notes));
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
                if(editMode)
                    NoteDbHandler.getsInstance(getApplicationContext()).delete(editNote.getId());
                finish();
                break;
            case R.id.send:
                Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void pickColor(View view) {
        bottomSheetDialog.show();
    }

    public void setupBottomSheetDialog() {
        bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetView = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.layout_bottom_sheet,
                        (LinearLayout) findViewById(R.id.bottom_sheet_container),
                        false
                );
        bottomSheetDialog.setContentView(bottomSheetView);
    }

    public void setupRecyclerView() {
        RecyclerView recyclerView = bottomSheetView.findViewById(R.id.color_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        RecyclerViewColorAdapter recyclerViewColorAdapter = new RecyclerViewColorAdapter(this, colorList);
        recyclerView.setAdapter(recyclerViewColorAdapter);

        recyclerView.hasFixedSize();
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        Log.d("mylog", "pause");
//        NoteModel noteModel = new NoteModel(title.getText().toString(), subtitle.getText().toString(), desc.getText().toString(), time.getText().toString(), null, 0);
//        database.add(noteModel);
//    }

    public void saveNote(View view) {
        if(!editMode) {
            NoteModel noteModel = new NoteModel(title.getText().toString(), subtitle.getText().toString(), desc.getText().toString(), time.getText().toString(), null, 0);
            NoteDbHandler.getsInstance(this).add(noteModel);
//            Date currentDate = new Date();
//            DateFormat dateFormat = new SimpleDateFormat("d MMM yyyy HH:mm ", Locale.getDefault());
//            String dateText = dateFormat.format(currentDate);
//            Log.d("mylog", dateText);
            finish();
        } else {
            NoteModel noteModel = new NoteModel(title.getText().toString(), subtitle.getText().toString(), desc.getText().toString(), time.getText().toString(), null, 0);
            NoteDbHandler.getsInstance(this).update(editNote.getId(),noteModel);
            finish();
        }
    }
}
