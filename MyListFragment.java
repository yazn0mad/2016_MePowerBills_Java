package com.yazapps.mepowerbills;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import static com.yazapps.mepowerbills.StaticData.isDetailed;
import static com.yazapps.mepowerbills.StaticData.isFirstTime;
import static com.yazapps.mepowerbills.StaticData.isMetered;
import static com.yazapps.mepowerbills.StaticData.isMyFirst;

public class MyListFragment extends Fragment {

    private RecyclerView myListRecyclerView;
    private MyListAdaptor myAdaptor;
    private MyContract myContract;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_list, container, false);

        myListRecyclerView = view.findViewById(R.id.my_list_recycler_view);
        myListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        setHasOptionsMenu(true);


        updateUI();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_my_list, menu);
    }

    // Show delete option on menu bar, if delete yes, back to MainContractList
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_button:

                new AlertDialog.Builder(super.getContext()).setTitle(R.string.delete_label)
                        .setMessage(R.string.delete_msg)
                        .setPositiveButton(R.string.answer_yes,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        isMyFirst = true;
                                        isFirstTime = true;
                                        isDetailed = false;
                                        MyList.mySelections.removeAll(MyList.mySelections);
                                        getActivity().finish();
                                        Intent intent = new Intent(getActivity(), MainActivity.class);
                                        startActivity(intent);
                                        dialog.dismiss();
                                    }
                                })
                        .setNegativeButton(R.string.answer_no,
                                new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Do nothing
                                dialog.dismiss();
                            }
                        })
                        .create()
                        .show();
                return true;

            case R.id.edit_button:
                new AlertDialog.Builder(super.getContext()).setTitle(R.string.edit_label)
                        .setMessage(R.string.edit_msg)
                        .setPositiveButton(R.string.answer_yes,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(getActivity(), DetailActivity.class);
                                        startActivity(intent);
                                        dialog.dismiss();
                                    }
                                })
                        .setNegativeButton(R.string.answer_no,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Do nothing
                                        dialog.dismiss();
                                    }
                                })
                        .create()
                        .show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void updateUI() {
        MyList myList = MyList.get(getActivity());
        List<MyContract> myContractList = myList.getContracts();
        myAdaptor = new MyListAdaptor(myContractList);
        myListRecyclerView.setAdapter(myAdaptor);
    }

    private class MyListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private MyContract myContract;
        private TextView nameLabelView;
        private TextView unitLabelView;
        private TextView dateLabelView;
        private TextView meterLabelView;
        private TextView balanceLabelView;
        private TextView kwhLabelView;
        private TextView unitValueView;
        private TextView dateValueView;
        private TextView meterValueView;
        private TextView balanceValueView;
        private TextView kwhValueView;

        private Button detailButton;
        private Button meterButton;

        public MyListViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.card_view, parent, false));

            nameLabelView = itemView.findViewById(R.id.name_label);

            unitLabelView = itemView.findViewById(R.id.unit_label);
            dateLabelView = itemView.findViewById(R.id.date_label);
            meterLabelView = itemView.findViewById(R.id.meter_label);
            balanceLabelView = itemView.findViewById(R.id.balance_label);
            kwhLabelView = itemView.findViewById(R.id.kwh_label);

            unitValueView = itemView.findViewById(R.id.unit_value);
            dateValueView = itemView.findViewById(R.id.date_value);
            meterValueView = itemView.findViewById(R.id.meter_value);
            balanceValueView = itemView.findViewById(R.id.balance_value);
            kwhValueView = itemView.findViewById(R.id.kwh_value);

            detailButton = itemView.findViewById(R.id.detail_button);
            detailButton.setOnClickListener(this);

            meterButton = itemView.findViewById(R.id.meter_button);
            meterButton.setOnClickListener(this);

            if (!isMyFirst) {
                // detailButton.setVisibility(View.GONE);
                //unitValueView.setText(Double.toString(DetailFragment_1.ampValue));
                //dateValueView.setText(DetailFragment_1.startDate);
                //meterValueView.setVisibility(View.GONE);
            }

            if (isMyFirst) {
                unitLabelView.setVisibility(View.GONE);
                unitValueView.setVisibility(View.GONE);
                dateLabelView.setVisibility(View.GONE);
                dateValueView.setVisibility(View.GONE);
                meterLabelView.setVisibility(View.GONE);
                meterValueView.setVisibility(View.GONE);
                balanceLabelView.setVisibility(View.GONE);
                balanceValueView.setVisibility(View.GONE);
                kwhLabelView.setVisibility(View.GONE);
                kwhValueView.setVisibility(View.GONE);
                meterButton.setVisibility(View.GONE);
                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.my_contract);
            }


            if (isDetailed) {
                balanceLabelView.setVisibility(View.GONE);
                balanceValueView.setVisibility(View.GONE);
                kwhLabelView.setVisibility(View.GONE);
                kwhValueView.setVisibility(View.GONE);
                detailButton.setVisibility(View.GONE);
                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.my_details);
            }

            if (isMetered) {
                balanceLabelView.setVisibility(View.VISIBLE);
                balanceValueView.setVisibility(View.VISIBLE);
                kwhLabelView.setVisibility(View.VISIBLE);
                kwhValueView.setVisibility(View.VISIBLE);
                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.my_summary);

            }

        }

        public void bind(MyContract contract){
            myContract = contract;
            nameLabelView.setText(myContract.getName());
            unitValueView.setText(myContract.getMyUnit());
            if (myContract.getMyDate() != null) {
                dateValueView.setText(StaticData.dateFormat.format(myContract.getMyDate()));
            }
            meterValueView.setText(myContract.getMyStartMeter());
            kwhValueView.setText(myContract.getMyKwh() + " kWh");
            balanceValueView.setText("¥ " + myContract.getMyBalance());


        }

        @Override
        public void onClick(View view) {

            DetailActivity.type = myContract.getType();
            DetailActivity.size = myContract.getSize();
            DetailActivity.contractName = myContract.getName();
            DetailActivity.sMyContract = myContract;
            MeterActivity.sMyContract = myContract;

            switch (view.getId()) {
                case R.id.detail_button:
                    Intent intent1 = new Intent(getActivity(), DetailActivity.class);
                    startActivity(intent1);
                    break;

                case R.id.meter_button:
                    Intent intent2 = new Intent(getActivity(), MeterActivity.class);
                    startActivity(intent2);
                    break;

                default:
                    break;
            }
        }
    }

    private class MyListAdaptor extends RecyclerView.Adapter<MyListViewHolder> {

        //private List<Contract> myContractList;
        private List<MyContract> myContractList;

        //public MyListAdaptor(List<Contract> contracts) {
        //    myContractList = contracts;
        //}
        public MyListAdaptor(List<MyContract> contracts) {
            myContractList = contracts;
        }

        @Override
        public MyListViewHolder onCreateViewHolder(ViewGroup parent, int viewTpye) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new MyListViewHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(MyListViewHolder holder, int position) {

            // KEEP THIS SIMPLE FOR SMOOTH SCROLLING!
            //Contract contract = myContractList.get(position);
            //holder.bind(contract);
            MyContract contract = myContractList.get(position);
            holder.bind(contract);
        }

        @Override
        public int getItemCount() {
            return myContractList.size();
        }
    }


}
// ADD EDIT BUTTON ON MENU > CHANGE METER BUTTON OR ADD DELETE BUTTON TO DELETE && DETAIL BTN
// VISIBLE
// IF NO LIST, GO TO MAIN CONTRACT LIST