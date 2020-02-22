package com.example.deleteitemarraylist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button button;
    ListView listView;
    EditText editText;
    ArrayList<String> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     getSupportActionBar().setDisplayHomeAsUpEnabled(true);
     getSupportActionBar().setDisplayShowHomeEnabled(true);

        button = findViewById(R.id.buttonId);
        button.setClickable(false);

        editText = findViewById(R.id.editTextId);
        listView = findViewById(R.id.listViewId);
        arrayList = new ArrayList<String>();
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);


        editText.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                final String item = editText.getText().toString().trim();

                if (!item.isEmpty()) {
                    button.setEnabled(true);
                    button.setBackgroundColor(getResources().getColor(R.color.color1));
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            arrayList.add(item);
                            editText.setText(null);
                            arrayAdapter.notifyDataSetChanged();
                        }
                    });
                } else {
                    button.setEnabled(false);
                    button.setBackgroundColor(getResources().getColor(R.color.color));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }


        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                final int whice_item = i;

                PopupMenu popupMenu = new PopupMenu(MainActivity.this,listView,Gravity.BOTTOM);
                getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        if (menuItem.getItemId() == R.id.deleteId) {
                            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                            alert.setTitle("Are you sure want to delete?");
                            alert.setMessage("If you delete this item you can't recover it.");
                            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    arrayList.remove(whice_item);
                                    arrayAdapter.notifyDataSetChanged();
                                }
                            });
                            alert.setNegativeButton("No", null);

                            alert.show();


                        }

                        return true;
                    }
                });
                popupMenu.show();


                return true;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

          int id=item.getItemId();
          if(id==android.R.id.home){
              finishAffinity();

          }
        return super.onOptionsItemSelected(item);
    }
}
