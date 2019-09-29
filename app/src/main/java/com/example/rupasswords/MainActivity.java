package com.example.rupasswords;


import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText sourceEditText;
    private TextView resultTextView;
    private View copyButton;

    private CompoundButton checkCaps;

    private Button generateButton;

    private String[] russians;
    private String[] latin;
    private PasswordsHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sourceEditText = findViewById(R.id.edti_source);
        resultTextView = findViewById(R.id.text_result);
        copyButton = findViewById(R.id.buttonCopyPassword);
        checkCaps = findViewById(R.id.big_letters);
        generateButton = findViewById(R.id.generate_password_button);

        russians = getResources().getStringArray(R.array.russians);
        latin = getResources().getStringArray(R.array.latin);

        helper = new PasswordsHelper(russians, latin);

        sourceEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                resultTextView.setText(helper.convert(s));

                copyButton.setEnabled(s.length() > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager manager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                assert manager != null;
                manager.setPrimaryClip(ClipData.newPlainText(getString(R.string.clipboard_title),resultTextView.getText().toString()));
                Toast.makeText(MainActivity.this, R.string.copied, Toast.LENGTH_SHORT).show();
            }
        });

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean useCaps = checkCaps.isChecked();
            }
        });


    }
}
