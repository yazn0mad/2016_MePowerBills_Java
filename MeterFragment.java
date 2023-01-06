package com.yazapps.mepowerbills;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.GONE;
import static com.yazapps.mepowerbills.MeterActivity.sMyContract;
import static com.yazapps.mepowerbills.StaticData.isMetered;
import static java.lang.Double.parseDouble;

public class MeterFragment extends Fragment {

    private MyContract myContract;
    private TextView title;
    private TextView startMeterLabel;
    private TextView startMeterStringValue;
    private TextView lastMeterLabel;
    private TextView lastMeterStringValue;
    private EditText meterReadingInput;
    private double meterInputDouble;
    private String meterInputString;
    private Button enterButton;
    private String balanceString;
    private String kwhString;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_meter, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.my_meter);

        myContract = sMyContract;

        title = view.findViewById(R.id.contract_name);
        title.setText(myContract.getName());

        startMeterLabel = view.findViewById(R.id.start_meter_label);
        startMeterStringValue = view.findViewById(R.id.start_meter_value);
        startMeterStringValue.setText(myContract.getMyStartMeter());

        // Not implemented at the moment
        lastMeterLabel = view.findViewById(R.id.last_meter_label);
        lastMeterStringValue = view.findViewById(R.id.last_meter_value);
        lastMeterStringValue.setText("not yet");
        lastMeterStringValue.setVisibility(GONE);
        lastMeterLabel.setVisibility(GONE);

        meterReadingInput = view.findViewById(R.id.meter_input);
        meterReadingInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                meterInputString = meterReadingInput.getText().toString();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        enterButton = view.findViewById(R.id.enter_button);
        enterButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(meterInputString)) {
                    meterReadingInput.setError(getString(R.string.enter_value));
                    return;
                }

                if (!meterInputString.isEmpty()) {
                    meterInputDouble = parseDouble(meterInputString);
                    Double start = parseDouble(myContract.getMyStartMeter());
                    if (meterInputDouble <= start) {
                        Toast.makeText(getActivity(),
                                R.string.correct_value,
                                Toast.LENGTH_LONG).show();
                    } else {
                        myContract.setMyCurrentMeter(meterInputString);

                        // CALCULATE & SHOW TOTAL KWH, BALANCE
                        kwhString = String.valueOf((int)RateCalculator.getTotalKwh(myContract));
                        myContract.setMyKwh(kwhString);
                        balanceString = String.valueOf(RateCalculator.computeBalance(myContract));
                        myContract.setMyBalance(balanceString);

                        // SET isMetered = TRUE.
                        isMetered = true;

                        Intent intent = new Intent(getActivity(), MyListActivity.class);
                        startActivity(intent);

                    }
                }
            }
        });



        return view;
    }
}
