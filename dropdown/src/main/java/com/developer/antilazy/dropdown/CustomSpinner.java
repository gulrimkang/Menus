package com.developer.antilazy.dropdown;

import android.content.Context;
import android.util.AttributeSet;


class CustomSpinner extends android.support.v7.widget.AppCompatSpinner {

    public CustomSpinner(Context context){
        super(context);
    }

    public CustomSpinner(Context context, AttributeSet attrs) {
        super(context,attrs);
    }

    public CustomSpinner(Context context, AttributeSet attrs, int defStyle) {
        super(context,attrs,defStyle);
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
