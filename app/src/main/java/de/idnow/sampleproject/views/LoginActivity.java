package de.idnow.sampleproject.views;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import de.idnow.R;
import de.idnow.sampleproject.application.IDNowApplication;
import de.idnow.sampleproject.bases.BaseActivity;
import de.idnow.sampleproject.utils.FontUtils;
import de.idnow.sdk.IDnowSDK;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.login_id_input)
    protected EditText idInput;
    @BindView(R.id.buttonStartVideoIdent)
    protected Button buttonStartVideoIdent;

    @BindView(R.id.login_logo)
    protected ImageView logoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setOnClick();
    }

    private void setOnClick() {
        buttonStartVideoIdent.setOnClickListener(this);
        //It use for testing only
        logoLogin.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                idInput.setText("DEV-TVSAV");
                return true;
            }
        });
    }

    private void init() {
        buttonStartVideoIdent.setTypeface(FontUtils.setSofiaRegular(IDNowApplication.getAppContext()));
        idInput.setTypeface(FontUtils.setSofiaRegular(IDNowApplication.getAppContext()));

        InputFilter[] filterArray = new InputFilter[2];
        filterArray[0] = new InputFilter.AllCaps();
        filterArray[1] = new InputFilter.LengthFilter(9);
        idInput.setFilters(filterArray);

        idInput.addTextChangedListener(new TextWatcher() {
            int length = 0;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String str = idInput.getText().toString();
                length = str.length();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String str = idInput.getText().toString();
                if (length == 2 && length < str.length()) idInput.append("-");
            }
        });
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View view) {
        String identId = idInput.getText().toString();
        if (identId != null && identId.length() > 0) {
            //"DEV-TVSAV"
            switch (view.getId()) {
                case R.id.buttonStartVideoIdent:
                    try {
                        IDnowSDK.getInstance().initialize(this, "");
                        IDnowSDK.setTransactionToken(identId, this);
                        IDnowSDK.setShowVideoOverviewCheck(true, this);
                        IDnowSDK.setShowErrorSuccessScreen(false, this);

                        IDnowSDK.getInstance().start(IDnowSDK.getTransactionToken(this));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        } else {
            Toast.makeText(this, "Please input Ident-ID..", Toast.LENGTH_LONG).show();
        }

    }

    /**
     * Callback from the SDK
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IDnowSDK.REQUEST_ID_NOW_SDK) {
            StringBuilder toastText;
            if (resultCode == IDnowSDK.RESULT_CODE_SUCCESS) {
                toastText = new StringBuilder("Identification performed. ");
                if (null != data) {
                    toastText.append(data.getStringExtra(IDnowSDK.RESULT_DATA_TRANSACTION_TOKEN));
                }
                //Toast.makeText(this, toastText.toString(), Toast.LENGTH_LONG).show();
            } else if (resultCode == IDnowSDK.RESULT_CODE_CANCEL) {
                toastText = new StringBuilder("Identification canceled. ");
                if (null != data) {
                    toastText.append(data.getStringExtra(IDnowSDK.RESULT_DATA_ERROR));
                }
                //Toast.makeText(this, toastText.toString(), Toast.LENGTH_LONG).show();
            } else if (resultCode == IDnowSDK.RESULT_CODE_FAILED) {
                toastText = new StringBuilder("Identification failed. ");
                if (null != data) {
                    toastText.append(data.getStringExtra(IDnowSDK.RESULT_DATA_ERROR));
                }
                //Toast.makeText(this, toastText.toString(), Toast.LENGTH_LONG).show();
            } else {
                toastText = new StringBuilder("Result Code: ");
                toastText.append(resultCode);
                //Toast.makeText(this, toastText.toString(), Toast.LENGTH_LONG).show();
            }

            showBaseAlert(toastText.toString());
        }
    }
}
