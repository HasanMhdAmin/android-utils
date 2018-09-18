package info.interactivesystems.pax.moodtracker.utils;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;

import info.interactivesystems.pax.moodtracker.R;

/**
 * Created by Hasan Mhd Amin on 9/13/2018.
 */

public class FragmentUtils {



    public static void addFragment(FragmentActivity activity, Fragment fragment,
                            FrameLayout fragmentContainer,
                            boolean isAddToBackStack, @Nullable View view) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right,
                R.anim.slide_in_left, R.anim.slide_out_right);

        if (isAddToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.add(fragmentContainer.getId(), fragment);

        transaction.commit();
    }

    public static void replaceFragment(FragmentActivity activity, Fragment fragment,
                                FrameLayout fragmentContainer,
                                boolean isAddToBackStack, @Nullable View view) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right,
                R.anim.slide_in_left, R.anim.slide_out_right);

        if (isAddToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.replace(fragmentContainer.getId(), fragment);
        transaction.commit();
    }

}
