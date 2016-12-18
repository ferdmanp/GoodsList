package com.fknt.voltage.goodslist.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ButtonBarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.fknt.voltage.goodslist.R;


/**
 * Created by voltage on 17.12.2016.
 */

public class DialogPrompt extends DialogFragment {

   public interface EditNameDialogListener{
       void onFinishEditDialog(DialogFragment dialog);
   }

    private EditNameDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dlg_input_text,null));

        Button btnOk=(Button) getActivity().findViewById(R.id.btnDlgOk);
        Button btnCancel=(Button) getActivity().findViewById(R.id.btnDlgCancel);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPrompt.this.getDialog().cancel();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tvHeaderName=(TextView) v.findViewById(R.id.dlg_tv_header_name);
                //listener.onFinishEditDialog(tvHeaderName.getText().toString());
                listener.onFinishEditDialog(DialogPrompt.this);
                DialogPrompt.this.getDialog().dismiss();
            }
        });



        return builder.create();


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
                listener=(EditNameDialogListener)context;
        }
        catch (ClassCastException e){
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }

    }
}
