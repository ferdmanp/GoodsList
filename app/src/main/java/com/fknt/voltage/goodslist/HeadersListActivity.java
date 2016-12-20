package com.fknt.voltage.goodslist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.fknt.voltage.goodslist.Adapters.GenericListAdapter;
import com.fknt.voltage.goodslist.Classes.ListHeaderItem;
import com.fknt.voltage.goodslist.DAL.ListHeadersDAL;

import java.util.Date;

public class HeadersListActivity extends AppCompatActivity
{

    ListHeadersDAL dal;
    String locNewListName;
    GenericListAdapter<ListHeaderItem> adapter;
    ListView lvHeaders;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headers_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        FloatingActionButton fabDeleteAll = (FloatingActionButton) findViewById(R.id.fabDeleteAll);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(R.string.title_new_header_dialog);
                final EditText input = new EditText(getContext());
                input.setId(R.id.et_prompt_edit_text);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                builder.setPositiveButton(R.string.btn_ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // EditText edit=(EditText) HeadersListActivity.this.findViewById(R.id.et_prompt_edit_text);
                                String newHeaderName=input.getText().toString();
                                dialog.dismiss();
                                CreateNewItem(newHeaderName);
                                RefreshListView();

                            }
                        });

                builder.setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                    }
                });

                builder.show();
            }
        });


        fabDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteAllItems();
                RefreshListView();

            }
        });


        lvHeaders = (ListView) findViewById(R.id.lvHeaders);
        dal = new ListHeadersDAL(getContext());

        adapter =
                new GenericListAdapter<>(getContext(), dal.SelectAll());


        lvHeaders.setAdapter(adapter);
    }

    @NonNull
    private HeadersListActivity getContext() {
        return HeadersListActivity.this;
    }

    @Override
    protected void onResume() {

        if (dal == null)
            dal = new ListHeadersDAL(getContext());

        adapter.updateList(dal.SelectAll());
        super.onResume();
    }

    private void DeleteAllItems() {
        if (dal == null)
            dal = new ListHeadersDAL(getContext());
        dal.DeleteAll();
    }

    private void CreateNewItem(String newHeaderName) {
        ListHeaderItem item= new ListHeaderItem();
        item.Name=newHeaderName;
        item.DateCreate= new Date();
        item.TotalSum=0.0;
        if(dal==null)
            dal = new ListHeadersDAL(getContext());
        try{
            dal.Insert(item);
        }
        catch (Exception e)
        {
            Snackbar
                    .make(new CoordinatorLayout(getContext()), e.getMessage(), Snackbar.LENGTH_LONG)
                    .show();
        }


    }

    private void RefreshListView() {
        if (dal == null)
            dal = new ListHeadersDAL(getContext());
        adapter.updateList(dal.SelectAll());
        lvHeaders.invalidate();
    }


}
