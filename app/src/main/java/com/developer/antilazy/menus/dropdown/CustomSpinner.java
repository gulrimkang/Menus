package com.developer.antilazy.menus.dropdown;

import android.content.Context;

public class CustomSpinner extends android.support.v7.widget.AppCompatSpinner {


    public CustomSpinner(Context context){
        super(context);
    }

    private boolean mOpenInitiated = false;
    onDropdownListener onDropdownListener;

    public interface onDropdownListener{

        void onOpened();
        void onClosed();

    }

    public void setOnDropdownListener(onDropdownListener onDropdownListener){
        this.onDropdownListener = onDropdownListener;
    }

    private void closeEvent(){

        mOpenInitiated = false;

        if(onDropdownListener!=null)
            onDropdownListener.onClosed();
    }

    @Override
    public boolean performClick() {

        mOpenInitiated = true;

        if(onDropdownListener!=null)
            onDropdownListener.onOpened();

        return super.performClick();
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {

        if(mOpenInitiated && hasWindowFocus)
            closeEvent();

        super.onWindowFocusChanged(hasWindowFocus);
    }
}
