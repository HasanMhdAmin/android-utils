package info.interactivesystems.pax.mood.utils;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.FrameLayout;

import info.interactivesystems.pax.mood.R;

/**
 * Created by Hasan Mhd Amin on 9/13/2018.
 */

public class FragmentUtils {



    public static void addFragment(FragmentActivity activity, Fragment fragment,
                                   FrameLayout fragmentContainer,
                                   boolean isAddToBackStack,
                                   int enterAnimation, int exitAnimation,
                                   @Nullable View transitionView) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(enterAnimation, exitAnimation,
                enterAnimation, exitAnimation);

        if (isAddToBackStack) {
            if (transitionView != null)
                transaction.addSharedElement(transitionView, ViewCompat.getTransitionName(transitionView));
            transaction.addToBackStack(null);
        }
        transaction.add(fragmentContainer.getId(), fragment);

        transaction.commit();
    }

    public static void addFragment(FragmentActivity activity, Fragment fragment,
                                   FrameLayout fragmentContainer,
                                   boolean isAddToBackStack,
                                   int enterAnimation, int exitAnimation,
                                   String tag,
                                   @Nullable View transitionView) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(enterAnimation, exitAnimation,
                enterAnimation, exitAnimation);

        if (isAddToBackStack) {
            if (transitionView != null)
                transaction.addSharedElement(transitionView, ViewCompat.getTransitionName(transitionView));
            transaction.addToBackStack(tag);
        }
        transaction.add(fragmentContainer.getId(), fragment, tag);

        transaction.commit();
    }


    public static void addFragment(FragmentActivity activity, Fragment fragment,
                            FrameLayout fragmentContainer,
                            boolean isAddToBackStack, @Nullable View transitionView) {

        addFragment(activity, fragment,
                fragmentContainer, isAddToBackStack,
                R.anim.slide_in_left, R.anim.slide_out_right, transitionView);

    }

    public static void addFragment(FragmentActivity activity, Fragment fragment,
                                   FrameLayout fragmentContainer,
                                   boolean isAddToBackStack,
                                   String tag,
                                   @Nullable View transitionView) {

        addFragment(activity, fragment,
                fragmentContainer, isAddToBackStack,
                R.anim.slide_in_left, R.anim.slide_out_right, tag, transitionView);

    }

    public static void replaceFragment(FragmentActivity activity, Fragment fragment,
                                       FrameLayout fragmentContainer,
                                       boolean isAddToBackStack,
                                       String tag,
                                       @Nullable View view) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right,
                R.anim.slide_in_left, R.anim.slide_out_right);

        if (isAddToBackStack) {
            if (view != null)
                transaction.addSharedElement(view, ViewCompat.getTransitionName(view));
            transaction.addToBackStack(null);
        }
        transaction.replace(fragmentContainer.getId(), fragment, tag);
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
            if (view != null)
                transaction.addSharedElement(view, ViewCompat.getTransitionName(view));
            transaction.addToBackStack(null);
        }
        transaction.replace(fragmentContainer.getId(), fragment);
        transaction.commit();
    }

}
