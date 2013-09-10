package ru.myapps.testapp.listener.dialog;

import android.content.DialogInterface;

/**
 * @author: Dmitry Nazarov
 * @date: 09.09.13
 */
public class DialogOnClickClose implements DialogInterface.OnClickListener {

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }

}
