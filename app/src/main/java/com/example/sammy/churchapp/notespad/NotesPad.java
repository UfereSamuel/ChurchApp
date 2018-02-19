package com.example.sammy.churchapp.notespad;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.sammy.churchapp.R;
import com.example.sammy.churchapp.WelcomePage;

public class NotesPad extends AppCompatActivity implements AdapterView.OnItemClickListener{

    public String TAG = "notespad";

    private static final int ACTIVITY_CREATE = 0;
    private static final int ACTIVITY_EDIT = 1;

    private static final int DELETE_ID = Menu.FIRST;
    private int mNoteNumber = 1;
    private NotesDbAdapter mDbHelper;

    ListView lisNotePad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);setContentView(R.layout.activity_notes_pad);

        Toolbar toolbar = (Toolbar) findViewById(R.id.homeToolBar);
        ImageView img = (ImageView) findViewById(R.id.img);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NotesPad.this, WelcomePage.class);
                startActivity(i);
                finish();
            }
        });


        //AppCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        lisNotePad = (ListView) findViewById(R.id.listNotePad);
        lisNotePad.setOnItemClickListener(this);
        mDbHelper = new NotesDbAdapter(this);
        mDbHelper.open();
        fillData();
        registerForContextMenu(lisNotePad);
        Button addnote = (Button) findViewById(R.id.addnoteButton);
        addnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNote();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.noteedit_menu, menu);

        Log.i(TAG, "oncreate"+getMenuInflater());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_about:

                AlertDialog.Builder dialog = new AlertDialog.Builder(NotesPad.this);
                dialog.setTitle("About");
                dialog.setMessage("Hello! I'm Heng, the creator of this application"+
                        " Used it on trading or any others activity that is related If there is any bug is found please freely e-mail me."
                );
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                });
                dialog.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void createNote(){
        Intent i = new Intent(this, NoteEdit.class);
        startActivityForResult(i, ACTIVITY_CREATE);
        finish();
    }

//    @Override
//    protected void onListItemClick(ListView l, View v, int position, long id) {
//        super.onListItemClick(l, v, position, id);
//    }

    private void fillData() {
        // Get all of the notes from the database and create the item list
        Cursor notesCursor = mDbHelper.fetchAllNotes();
        startManagingCursor(notesCursor);

        String[] from = new String[] { NotesDbAdapter.KEY_TITLE ,NotesDbAdapter.KEY_DATE};
        int[] to = new int[] { R.id.text1 ,R.id.date_row};

        // Now create an array adapter and set it to display using our row
        SimpleCursorAdapter notes =
                new SimpleCursorAdapter(this, R.layout.list_row, notesCursor, from, to);
        lisNotePad.setAdapter(notes);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, DELETE_ID, 0, R.string.menu_delete);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case DELETE_ID:
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                mDbHelper.deleteNote(info.id);
                fillData();
                return true;
        }
        return super.onContextItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        fillData();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        try{
            Intent i = new Intent(this, NoteEdit.class);
            i.putExtra(NotesDbAdapter.KEY_ROWID, id);
            startActivityForResult(i, ACTIVITY_EDIT);
            finish();

        }   catch (Exception e){
            Log.d(TAG, ""+e.getMessage());
        }


    }
}
