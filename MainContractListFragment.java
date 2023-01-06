package com.yazapps.mepowerbills;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import static com.yazapps.mepowerbills.StaticData.isMyFirst;

public class MainContractListFragment extends Fragment {

    private RecyclerView firstRecyclerView;
    private FirstListAdaptor adaptor;
    private int numOfContract = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contract_list, container, false);
        firstRecyclerView = view.findViewById(R.id.contract_list_recycler_view);
        firstRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_contract_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.done_button:
                if (numOfContract > 1) {
                    Snackbar.make(getActivity().findViewById(android.R.id.content),
                            R.string.pick_one, Snackbar.LENGTH_LONG).show();
                } else if (numOfContract < 1) {
                    Snackbar.make(getActivity().findViewById(android.R.id.content),
                            R.string.select_contract, Snackbar.LENGTH_LONG).show();

                } else {
                    isMyFirst = true;
                    Intent intent = new Intent(getActivity(), MyListActivity.class);
                    startActivity(intent);
                    return true;
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateUI() {

        // List items from string-array xml resource for localisation purpose
        String newArr[] = getResources().getStringArray(R.array.contract_list);
        List<String> contractList = Arrays.asList(newArr);
        adaptor = new FirstListAdaptor(contractList);
        firstRecyclerView.setAdapter(adaptor);
    }

    private class FirstListViewHolder extends RecyclerView.ViewHolder implements View
            .OnClickListener {

        private TextView nameTextView;
        private CheckBox checkBox;
        private Contract contract;
        private MyContract myContract;
        private String contractName;

        FirstListViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.contract_list_item, parent, false));
            itemView.setOnClickListener(this);

            checkBox = itemView.findViewById(R.id.checkBox);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (isChecked) {

                        // Add selected contract to my contract list, allow only one selection
                        int pos = getAdapterPosition();
                        String name = findName(pos);
                        myContract = MyContract.makeMine(pos, name);
                        MyList.mySelections.add(myContract);
                        numOfContract++;
                    }

                    if (!isChecked) {

                        // Delete selected contract from my list and reset counter
                        if (MyList.mySelections.contains(myContract)) {
                            MyList.mySelections.remove(myContract);
                            numOfContract--;
                        }
                    }
                }
            });
            nameTextView = itemView.findViewById(R.id.contract_name);
        }

        // Match names with contracts
        String findName(int pos) {
            switch (pos) {
                case 0:
                    return getString(R.string.pos0);
                case 1:
                    return getString(R.string.pos1);
                case 2:
                    return getString(R.string.pos2);
                case 3:
                    return getString(R.string.pos3);
                case 4:
                    return getString(R.string.pos4);
                case 5:
                    return getString(R.string.pos5);
                case 6:
                    return getString(R.string.pos6);
                case 7:
                    return getString(R.string.pos7);
                case 8:
                    return getString(R.string.pos8);
                case 9:
                    return getString(R.string.pos9);
                case 10:
                    return getString(R.string.pos10);
                default:
                    return "Name not found";
            }
        }

        void bind(String contract) {
             this.contractName = contract;
             nameTextView.setText(contract);
        }

        @Override
        public void onClick(View view) {

        }
    }

    private class FirstListAdaptor extends RecyclerView.Adapter<MainContractListFragment
            .FirstListViewHolder> {

        private List<String> contractList;

        FirstListAdaptor(List<String> contracts) {
            contractList = contracts;
        }

        @Override
        public MainContractListFragment.FirstListViewHolder onCreateViewHolder(ViewGroup parent, int
                viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new MainContractListFragment.FirstListViewHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(MainContractListFragment.FirstListViewHolder holder, int position) {

            // KEEP THIS SIMPLE FOR SMOOTH SCROLLING!
            String contract = contractList.get(position);
            holder.bind(contract);
        }

        @Override
        public int getItemCount() {
            return contractList.size();
        }
    }

}
