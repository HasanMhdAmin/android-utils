package info.interactivesystems.pax.moodtracker.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import info.interactivesystems.pax.moodtracker.R;

/**
 * Created by Hasan Mhd Amin on 10/6/2016.
 */

public class PopupsUtils {

    private static AlertDialog alertDialog;

    public static void showAlertDialog(final Context context, boolean cancelable,
                                       @Nullable String title, String message,
                                       String positiveButtonText, @Nullable DialogInterface.OnClickListener positiveButtonListener,
                                       String negativeButtonText, @Nullable DialogInterface.OnClickListener negativeButtonListener) {
        if (alertDialog == null || !alertDialog.isShowing()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            if (title != null)
                builder.setTitle(title);
            builder.setMessage(message);
            if (positiveButtonText != null) {
                if (positiveButtonListener != null) {
                    builder.setPositiveButton(positiveButtonText, positiveButtonListener);
                } else {
                    builder.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            alertDialog.dismiss();
                        }
                    });
                }
            }
            if (negativeButtonText != null) {
                if (negativeButtonListener != null) {
                    builder.setNegativeButton(negativeButtonText, negativeButtonListener);
                } else {
                    builder.setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            alertDialog.dismiss();
                        }
                    });
                }
            }
            alertDialog = builder.create();
            alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialogInterface) {
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
                }
            });

            alertDialog.setCancelable(cancelable);
            alertDialog.show();

        }
    }

    public static void hideAlertDialog() {
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }


}