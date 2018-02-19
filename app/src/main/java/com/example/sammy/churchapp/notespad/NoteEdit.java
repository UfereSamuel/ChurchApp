package com.example.sammy.churchapp.notespad;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sammy.churchapp.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteEdit extends Activity {
    public static int numTitle = 1;
    public static String curDate = "";
    public static String curText = "";
    private EditText mTitleText;
    private EditText mBodyText;
    private TextView mDateText;
    private Long mRowId;

    private Cursor note;

    private NotesDbAdapter mDbHelper;

    public static class LineEditText extends android.support.v7.widget.AppCompatEditText{
        //we need this constructor for layout inflater

        public LineEditText(Context context, AttributeSet attrs){
            super(context, attrs);
            mRect = new Rect();
            mpaint = new Paint();
            mpaint.setStyle(Paint.Style.FILL_AND_STROKE);
            mpaint.setColor(Color.BLUE);

        }

        private Rect mRect;
        private Paint mpaint;

        @Override
        protected void onDraw(Canvas canvas){
            int height = getHeight();
            int line_height = getLineHeight();

            int count = height / line_height;

            if (getLineCount() > count)
                count = getLineCount();

            Rect r = mRect;
            Paint paint = mpaint;
            int baseline = getLineBounds(0, r);

            for (int i = 0; i <count; i++){
                canvas.drawLine(r.left, baseline + 1, r.right, baseline + 1, paint);
                baseline += getLineHeight();

                super.onDraw(canvas);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);
        setTitle(R.string.app_name);

        mDbHelper = new NotesDbAdapter(this);
        mDbHelper.open();

        mTitleText = (EditText) findViewById(R.id.title);
        mBodyText = (EditText) findViewById(R.id.body);
        mDateText = (TextView) findViewById(R.id.notelist_date);

        long msTime = System.currentTimeMillis();
        Date curDateTime = new Date(msTime);

        SimpleDateFormat formatter = new SimpleDateFormat("d'/'m'/'y");
        curDate = formatter.format(curDateTime);

        mDateText.setText(""+curDate);

        mRowId = (savedInstanceState == null) ? null:
                (long) savedInstanceState.getSerializable(NotesDbAdapter.KEY_ROWID);
        if (mRowId == null){
            Bundle extras = getIntent().getExtras();
            mRowId = extras != null ? extras.getLong(NotesDbAdapter.KEY_ROWID) : null;
        }

        populateFields();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(NoteEdit.this, NotesPad.class);
                startActivity(intent);
                finish();
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

    }

   // @Override
    protected void onSavedInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        saveState();
        outState.putSerializable(NotesDbAdapter.KEY_ROWID, mRowId);

    }


    @Override
    protected void onPause() {
        super.onPause();
        saveState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateFields();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.noteedit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
          case R.id.menu_about:

      /* Here is the introduce about myself */
                AlertDialog.Builder dialog = new AlertDialog.Builder(NoteEdit.this);
                dialog.setTitle("About");
                dialog.setMessage("Hello! I'm Heng, the creator of this application.");
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                });
                dialog.show();
                return true;
            case R.id.menu_delete:
                if(note != null){
                    note.close();
                    note = null;
                }
                if(mRowId != null){
                    mDbHelper.deleteNote(mRowId);
                }
                finish();

                return true;
            case R.id.menu_save:
                saveState();
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void saveState() {
        String title = mTitleText.getText().toString();
        String body = mBodyText.getText().toString();

        if(mRowId == null){
            long id = mDbHelper.createNote(title, body, curDate);
            if(id > 0){ mRowId = id; }
        }else{
            mDbHelper.updateNote(mRowId, title, body, curDate);
        }
    }

    private void populateFields() {
        if (mRowId != null) {
            note = mDbHelper.fetchNote(mRowId);
            startManagingCursor(note);
            mTitleText.setText(note.getString(
                    note.getColumnIndexOrThrow(NotesDbAdapter.KEY_TITLE)));
            mBodyText.setText(note.getString(
                    note.getColumnIndexOrThrow(NotesDbAdapter.KEY_BODY)));
            curText = note.getString(
                    note.getColumnIndexOrThrow(NotesDbAdapter.KEY_BODY));
        }
    }



}
