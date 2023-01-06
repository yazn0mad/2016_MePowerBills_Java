package com.yazapps.mepowerbills;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Date;

import static com.yazapps.mepowerbills.DetailActivity.contractName;
import static com.yazapps.mepowerbills.DetailActivity.sMyContract;
import static com.yazapps.mepowerbills.StaticData.discountRate;
import static com.yazapps.mepowerbills.StaticData.isDetailed;
import static com.yazapps.mepowerbills.StaticData.isMyFirst;
import static com.yazapps.mepowerbills.StaticData.levyRate;
import static java.lang.Double.parseDouble;

public class DetailFragment_2 extends Fragment {

    private MyContract myContract;

    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;

    private Date date = new Date();


    private TextView contractTextView;
    private Spinner minSpinner;
    private Spinner powerSpinner;
    private EditText kWInput;
    private String minUsageString;
    static int minMonth;
    private String powerString;
    private EditText meterInput;
    private Button dateButton;
    private EditText discountInput;
    private EditText levyInput;
    private String kWString;
    private String meterString;
    private String discountString;
    private double discountDouble;
    private String levyString;
    private double levyDouble;
    private Button discountButton;
    private Button levyButton;
    private WebView helpWebView;
    private boolean isDateSet;

    static int powerPosition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.settings_label);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail_2, container, false);

        contractTextView = view.findViewById(R.id.contract_name);
        contractTextView.setText(contractName);

        kWInput = view.findViewById(R.id.kw_input);
        kWInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                kWString = kWInput.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        minSpinner = view.findViewById(R.id.min_usage_spinner);
        minSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                minUsageString = parent.getItemAtPosition(position).toString();
                minMonth = position + 8; // position 0 == September(month index = 8)

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        powerSpinner = view.findViewById(R.id.power_spinner);
        powerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                powerString = parent.getItemAtPosition(position).toString();
                powerPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        meterInput = view.findViewById(R.id.meter_input);
        meterInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                meterString = meterInput.getText().toString();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        dateButton = view.findViewById(R.id.start_date_button);
        dateButton.setText(R.string.select_date);
        dateButton.setBackgroundColor(getResources().getColor(R.color.blue_core));
        isDateSet = false;
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment
                        .newInstance(date);
                dialog.setTargetFragment(DetailFragment_2.this, REQUEST_DATE);
                dialog.show(fm, DIALOG_DATE);
            }
        });

        discountInput = view.findViewById(R.id.discount_input);
        discountInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                discountString = discountInput.getText().toString();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        discountButton = view.findViewById(R.id.discount_button);
        discountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebActivity.searchChar = 'D';
                Intent intent = new Intent(getActivity(), WebActivity.class);
                startActivity(intent);
            }
        });

        levyInput = view.findViewById(R.id.levy_input);
        levyInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                levyString = levyInput.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        levyButton = view.findViewById(R.id.levy_button);
        levyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebActivity.searchChar = 'L';
                Intent intent = new Intent(getActivity(), WebActivity.class);
                startActivity(intent);
            }
        });

        helpWebView = view.findViewById(R.id.help_web_view);
        helpWebView.loadUrl("file:///android_res/drawable/bill_example.png");

        return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE) {
            Date selectedDate = (Date) data.getSerializableExtra(DatePickerFragment.SELECTED_DATE);
            date = selectedDate;
            isDateSet = true;
            updateDate();
        }

    }

    private void updateDate() {
        dateButton.setText(StaticData.dateFormat.format(date));
        dateButton.setBackgroundColor(getResources().getColor(R.color.blue_light));
        dateButton.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_detail, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean isAllGood = false;
        boolean isKwGood = false;
        switch (item.getItemId()) {
            case R.id.check_button:

                myContract = sMyContract;

                if (kWString != null) {
                    int kwInt = Integer.parseInt(kWString);
                    if (kwInt > 0 && kwInt < 50) {
                        myContract.setMyUnit(kWString);
                        isKwGood = true;
                    } else {
                        Snackbar.make(getActivity().findViewById(android.R.id.content),
                                R.string.range_1_49, Snackbar.LENGTH_LONG).show();
                    }
                }

                if (meterString == null || meterString.isEmpty()) {
                    Snackbar.make(getActivity().findViewById(android.R.id.content),
                            R.string.missing_meter, Snackbar.LENGTH_LONG).show();

                } else if (!isDateSet) {
                    Snackbar.make(getActivity().findViewById(android.R.id.content),
                            R.string.missing_date, Snackbar.LENGTH_LONG).show();

                } else if (discountString == null || discountString.isEmpty()) {
                    Snackbar.make(getActivity().findViewById(android.R.id.content),
                            R.string.missing_discount, Snackbar.LENGTH_LONG).show();

                } else if (levyString == null || levyString.isEmpty()) {
                    Snackbar.make(getActivity().findViewById(android.R.id.content),
                            R.string.missing_levy, Snackbar.LENGTH_LONG).show();

                } else {
                    myContract.setMyStartMeter(meterString);
                    myContract.setMyDate(date);
                    discountDouble = parseDouble(discountString);
                    discountRate = discountDouble;
                    levyDouble = parseDouble(levyString);
                    levyRate = levyDouble;
                    isAllGood = true;
                }

                if (isKwGood && isAllGood) {
                    Intent intent = new Intent(getActivity(), MyListActivity.class);
                    startActivity(intent);
                    isMyFirst = false;
                    isDetailed = true;
                    return true;
                }

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
