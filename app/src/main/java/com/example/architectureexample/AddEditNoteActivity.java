package com.example.architectureexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String EXTRA_ID="com.example.architectureexample.EXTRA_ID";
    public static final String EXTRA_TITLE="com.example.architectureexample.EXTRA_TITLE";
    public static final String EXTRA_DESC="com.example.architectureexample.EXTRA_DESC";
    public static final String EXTRA_PRIOR="com.example.architectureexample.EXTRA_PRIOR";

    private EditText titleEditText;
    private EditText descriptionEt;
    private NumberPicker priorNumPick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        titleEditText=findViewById(R.id.et_title);
        descriptionEt=findViewById(R.id.et_description);
        priorNumPick=findViewById(R.id.np_priority);

        priorNumPick.setMinValue(1);
        priorNumPick.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            titleEditText.setText(intent.getStringExtra(EXTRA_TITLE));
            descriptionEt.setText(intent.getStringExtra(EXTRA_DESC));
            priorNumPick.setValue(intent.getIntExtra(EXTRA_PRIOR,1));
        } else{
            setTitle("Add Note");
        }
    }

    private void saveNote(){
        String title=titleEditText.getText().toString();
        String desc=descriptionEt.getText().toString();
        int prior=priorNumPick.getValue();

        if(title.trim().isEmpty() || desc.trim().isEmpty()){
            Toast.makeText(this,"Please insert a title and description",Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent=new Intent();
        intent.putExtra(EXTRA_TITLE,title);
        intent.putExtra(EXTRA_DESC,desc);
        intent.putExtra(EXTRA_PRIOR,prior);

        int id=getIntent().getIntExtra(EXTRA_ID,-1);

        if(id!=-1)
            intent.putExtra(EXTRA_ID,id);

        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_note:
                saveNote();
                return true;

                default:
                    return super.onOptionsItemSelected(item);
        }
    }
}
