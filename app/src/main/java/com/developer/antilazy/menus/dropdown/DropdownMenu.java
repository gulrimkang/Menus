package com.developer.antilazy.menus.dropdown;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.developer.antilazy.menus.R;


/* This Module uses TextInputEditText, TextInputLayout, and customized spinner to implement
dropdown style Menu
 */


public class DropdownMenu extends FrameLayout {

    private TextInputEditText mTextInputEditText;
    private TextInputLayout mTextInputLayout;
    private CustomSpinner mSpinner;

    private CustomSpinner.onDropdownListener mOnDropdownListener;
    private onItemSelectedListener mOnItemSelectedListener;

    private ArrayAdapter mAdapter;
    private Context mContext;

    public DropdownMenu(Context context) {
        super(context);
        initializeView(context);
    }

    public DropdownMenu(Context context, AttributeSet attrs) {
        super(context);
        initializeView(context);
    }

    public DropdownMenu(Context context, AttributeSet attrs, int defStyle) {
        super(context);
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

                mTextInputEditText.requestFocus();

            }

            @Override
            public void onClosed() {

                mTextInputEditText.setFocusable(false);

            }
        };


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_spinner, this);

    }

    public void setAdapter(int textArrResId, int textViewResId) {

        mAdapter = ArrayAdapter.createFromResource(mContext, textArrResId, textViewResId);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(mAdapter);

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

        mTextInputEditText = findViewById(R.id.textInputEditForSpinner);
        mTextInputLayout = findViewById(R.id.textInputLayoutForSpinner);
        mSpinner = findViewById(R.id.spinner);

        super.onFinishInflate();

    }

}
