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
import static com.yazapps.mepowerbills.DetailActivity.type;
import static com.yazapps.mepowerbills.DetailActivity.size;
import static com.yazapps.mepowerbills.R.color;
import static com.yazapps.mepowerbills.R.id;
import static com.yazapps.mepowerbills.R.layout;
import static com.yazapps.mepowerbills.R.string;
import static com.yazapps.mepowerbills.StaticData.dateFormat;
import static com.yazapps.mepowerbills.StaticData.discountRate;
import static com.yazapps.mepowerbills.StaticData.isDetailed;
import static com.yazapps.mepowerbills.StaticData.isMyFirst;
import static com.yazapps.mepowerbills.StaticData.levyRate;
import static java.lang.Double.parseDouble;

public class DetailFragment_1 extends Fragment {

    private MyContract myContract;

    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;

    private Date date = new Date();

    private TextView contractTextView;
    private TextView ampLabel;
    private Spinner ampSpinner;
    private Spinner ampWebSpinner;
    private String ampString;
    private TextView kvaLabel;
    private EditText kvaInput;
    private String kvaString;
    private EditText meterInput;
    private String meterString;
    private Button dateButton;
    private EditText discountInput;
    private EditText levyInput;
    private String discountString;
    private double discountDouble;
    private String levyString;
    private double levyDouble;
    private Button discountButton;
    private Button levyButton;
    private WebView helpWebView;
    private boolean isDateSet;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(string.settings_label);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(layout.fragment_detail_1, container, false);

        contractTextView = view.findViewById(id.contract_name);
        contractTextView.setText(contractName);
        ampLabel = view.findViewById(id.amp_label);
        ampSpinner = view.findViewById(id.amp_spinner);
        ampWebSpinner = view.findViewById(id.amp_web_spinner);
        kvaLabel = view.findViewById(id.kva_label);
        kvaInput = view.findViewById(id.kva_input);

        switch (type) {
            case 'B':
                ampLabel.setVisibility(View.VISIBLE);

                // Show Season Plus spinner 30A - 60A for size 'S'
                switch (size) {
                    case 'S':
                        ampSpinner.setVisibility(View.GONE);
                        ampWebSpinner.setVisibility(View.VISIBLE);
                        ampWebSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                ampString = parent.getItemAtPosition(position).toString();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;

                        // Show default spinner 10A - 60A
                        default:
                            ampWebSpinner.setVisibility(View.GONE);
                            ampSpinner.setVisibility(View.VISIBLE);
                            ampSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    ampString = parent.getItemAtPosition(position).toString();
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                }
                break;

            case 'C':
                ampLabel.setVisibility(View.GONE);
                ampSpinner.setVisibility(View.GONE);
                ampWebSpinner.setVisibility(View.GONE);
                kvaLabel.setVisibility(View.VISIBLE);
                kvaInput.setVisibility(View.VISIBLE);
                kvaInput.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        kvaString = kvaInput.getText().toString();

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                break;
        }

        meterInput = view.findViewById(id.meter_input);
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

        dateButton = view.findViewById(id.start_date_button);
        dateButton.setText(string.select_date);
        dateButton.setBackgroundColor(getResources().getColor(color.blue_core));
        isDateSet = false;
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment
                        .newInstance(date);
                dialog.setTargetFragment(DetailFragment_1.this, REQUEST_DATE);
                dialog.show(fm, DIALOG_DATE);
            }
        });

        discountInput = view.findViewById(id.discount_input);
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

        discountButton = view.findViewById(id.discount_button);
        discountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebActivity.searchChar = 'D';
                Intent intent = new Intent(getActivity(), WebActivity.class);
                startActivity(intent);
            }
        });

        levyInput = view.findViewById(id.levy_input);
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

        levyButton = view.findViewById(id.levy_button);
        levyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebActivity.searchChar = 'L';
                Intent intent = new Intent(getActivity(), WebActivity.class);
                startActivity(intent);
            }
        });

        helpWebView = view.findViewById(id.help_web_view);
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
        dateButton.setText(dateFormat.format(date));
        dateButton.setBackgroundColor(getResources().getColor(color.blue_light));
        dateButton.setTextColor(getResources().getColor(color.colorPrimaryDark));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_detail, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        boolean isAllGood = false;
        boolean isKvaGood = false;

        switch (item.getItemId()) {

            case id.check_button:

                myContract = sMyContract;

                switch (type) {

                    case 'B':

                        if (ampSpinner != null || ampWebSpinner != null) {
                            myContract.setMyUnit(ampString);
                        }

                        if (meterString == null || meterString.isEmpty()) {
                            Snackbar.make(getActivity().findViewById(android.R.id.content),
                                    string.missing_meter, Snackbar.LENGTH_LONG).show();

                        } else if (!isDateSet) {
                            Snackbar.make(getActivity().findViewById(android.R.id.content),
                                    string.missing_date, Snackbar.LENGTH_LONG).show();

                        } else if (discountString == null || discountString.isEmpty()) {
                            Snackbar.make(getActivity().findViewById(android.R.id.content),
                                    string.missing_discount, Snackbar.LENGTH_LONG).show();

                        } else if (levyString == null || levyString.isEmpty()) {
                            Snackbar.make(getActivity().findViewById(android.R.id.content),
                                    string.missing_levy, Snackbar.LENGTH_LONG).show();

                        } else {
                            myContract.setMyStartMeter(meterString);
                            myContract.setMyDate(date);
                            discountDouble = parseDouble(discountString);
                            discountRate = discountDouble;
                            levyDouble = parseDouble(levyString);
                            levyRate = levyDouble;
                            isAllGood = true;
                        }

                        if (isAllGood) {
                            Intent intent = new Intent(getActivity(), MyListActivity.class);
                            startActivity(intent);
                            isMyFirst = false;
                            isDetailed = true;
                            return true;
                        }
                        break;

                    case 'C':

                        if (kvaString != null) {
                            int kva = Integer.parseInt(kvaString);

                            switch (size) {
                                case 'S':
                                    // kVA = 7 - 10
                                    if (kva >= 7 && kva <= 10) {
                                        myContract.setMyUnit(kvaString);
                                        isKvaGood = true;
                                    } else {
                                        Snackbar.make(getActivity().findViewById(android.R.id.content),
                                                string.range_7_10, Snackbar.LENGTH_LONG).show();
                                    }
                                    break;

                                default:
                                    // kVA = 6 - 49
                                    if (kva >= 6 && kva <= 49) {
                                        myContract.setMyUnit(kvaString);
                                        isKvaGood = true;
                                    } else {
                                        Snackbar.make(getActivity().findViewById(android.R.id.content),
                                                string.range_6_49, Snackbar.LENGTH_LONG).show();
                                    }
                            }
                        }

                        if (meterString == null || meterString.isEmpty()) {
                            Snackbar.make(getActivity().findViewById(android.R.id.content),
                                    string.missing_meter, Snackbar.LENGTH_LONG).show();

                        } else if (!isDateSet) {
                            Snackbar.make(getActivity().findViewById(android.R.id.content),
                                    string.missing_date, Snackbar.LENGTH_LONG).show();

                        } else if (discountString == null || discountString.isEmpty()) {
                            Snackbar.make(getActivity().findViewById(android.R.id.content),
                                    string.missing_discount, Snackbar.LENGTH_LONG).show();

                        } else if (levyString == null || levyString.isEmpty()) {
                            Snackbar.make(getActivity().findViewById(android.R.id.content),
                                    string.missing_levy, Snackbar.LENGTH_LONG).show();

                        } else {
                            myContract.setMyStartMeter(meterString);
                            myContract.setMyDate(date);
                            discountDouble = parseDouble(discountString);
                            discountRate = discountDouble;
                            levyDouble = parseDouble(levyString);
                            levyRate = levyDouble;
                            isAllGood = true;
                        }

                        if (isKvaGood && isAllGood) {
                            Intent intent = new Intent(getActivity(), MyListActivity.class);
                            startActivity(intent);
                            isMyFirst = false;
                            isDetailed = true;
                            return true;
                        }
                        break;
                }

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
