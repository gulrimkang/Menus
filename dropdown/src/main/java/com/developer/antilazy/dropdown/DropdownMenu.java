package com.developer.antilazy.dropdown;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;



/* This Module uses TextInputEditText, TextInputLayout, and customized spinner to implement
dropdown style Menu
 */


public class DropdownMenu extends FrameLayout {

    private TextInputEditText mTextInputEditText;
    private TextInputLayout mTextInputLayout;
    private CustomSpinner mSpinner;

    private ArrayAdapter<CharSequence> mArrayAdapter;

    private CustomSpinner.onDropdownListener mOnDropdownListener;
    private onItemSelectedListener mOnItemSelectedListener;

    private Context mContext;


    public DropdownMenu(Context context) {
        super(context);
        initializeView(context);
    }

    public DropdownMenu(Context context, AttributeSet attrs) {
        super(context,attrs);
        initializeView(context);
    }

    public DropdownMenu(Context context, AttributeSet attrs, int defStyle) {
        super(context,attrs,defStyle);
        initializeView(context);
    }

    /* Implement the basic behavior when Item selected.
    * */

    public interface onItemSelectedListener{

        void onItemSelected();

    }

    private void initializeView(Context context) {

        this.mContext = context;

        mOnDropdownListener = new CustomSpinner.onDropdownListener() {
            @Override
            public void onOpened() {

                mTextInputEditText.setFocusableInTouchMode(true);
                mTextInputEditText.requestFocus();

            }

            @Override
            public void onClosed() {

                mTextInputEditText.setFocusable(false);

            }
        };


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.dropdown_menu,this ,true);

        mTextInputEditText = this.findViewById(R.id.textInputEditForSpinner);
        mTextInputLayout = this.findViewById(R.id.textInputLayoutForSpinner);
        mSpinner = this.findViewById(R.id.customSpinner);
        mSpinner.setOnDropdownListener(mOnDropdownListener);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                changeLabelState(view);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void setAdapter(int resArrayId,int resDropdownId) {

        mArrayAdapter = ArrayAdapter.createFromResource(mContext,resArrayId,R.layout.custom_spinner_item);
        mArrayAdapter.setDropDownViewResource(resDropdownId);

        mSpinner.setAdapter(mArrayAdapter);

    }


    private void changeLabelState(View view){

            mTextInputLayout.setHint(((TextView) view).getText());
            mTextInputEditText.setFocusable(false);

    }

    public void setOnItemSelectedListener(onItemSelectedListener onItemSelectedListener){

        this.mOnItemSelectedListener = onItemSelectedListener;

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                changeLabelState(view);
                mOnItemSelectedListener.onItemSelected();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    protected void onFinishInflate() {


        super.onFinishInflate();

    }

}
