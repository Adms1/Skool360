package com.anandniketanshilaj.skool360.skool360.Fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

import com.anandniketanshilaj.skool360.R;
import com.anandniketanshilaj.skool360.skool360.Activities.DashBoardActivity;
import com.anandniketanshilaj.skool360.skool360.Adapter.ExpandableListAdapter;
import com.anandniketanshilaj.skool360.skool360.Adapter.ImprestListAdapter;
import com.anandniketanshilaj.skool360.skool360.AsyncTasks.GetImprestDataAsyncTask;
import com.anandniketanshilaj.skool360.skool360.AsyncTasks.GetStudHomeworkAsyncTask;
import com.anandniketanshilaj.skool360.skool360.AsyncTasks.GetTermAsyncTask;
import com.anandniketanshilaj.skool360.skool360.Models.HomeWorkModel;
import com.anandniketanshilaj.skool360.skool360.Models.ImprestDataModel;
import com.anandniketanshilaj.skool360.skool360.Models.TermModel;
import com.anandniketanshilaj.skool360.skool360.Utility.Utility;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Harsh on 04-Aug-16.
 */
public class ImprestFragment extends Fragment {
    private View rootView;
    private Button btnMenu, btnBackImprest;
    private TextView txtMyBalance, txtOpeningBalaceTop, txtNoRecordsHomework;
    private Spinner spinYear;
    private TableRow tblRowBalance, tblRowOpeningBalance;
    private ListView listImprestData;
//    private LinearLayout llListTitle;
    private Context mContext;
    private GetTermAsyncTask getTermAsyncTask = null;
    private GetImprestDataAsyncTask getImprestDataAsyncTask = null;
    private ArrayList<TermModel> termModels = new ArrayList<>();
    private ArrayList<ImprestDataModel> imprestModels = new ArrayList<>();
    private ImprestListAdapter imprestListAdapter = null;
    private ProgressDialog progressDialog = null;

    public ImprestFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.imprest_fragment, container, false);
        mContext = getActivity();

        initViews();
        setListners();
        fillspinYear();

        return rootView;
    }

    public void initViews() {
        btnMenu = (Button) rootView.findViewById(R.id.btnMenu);
        btnBackImprest = (Button) rootView.findViewById(R.id.btnBackImprest);
        spinYear = (Spinner) rootView.findViewById(R.id.spinYear);
        tblRowBalance = (TableRow) rootView.findViewById(R.id.tblRowBalance);
        tblRowOpeningBalance = (TableRow) rootView.findViewById(R.id.tblRowOpeningBalance);
        txtMyBalance = (TextView) rootView.findViewById(R.id.txtMyBalance);
        txtOpeningBalaceTop = (TextView) rootView.findViewById(R.id.txtOpeningBalaceTop);
        txtNoRecordsHomework = (TextView) rootView.findViewById(R.id.txtNoRecordsHomework);
        listImprestData = (ListView) rootView.findViewById(R.id.listImprestData);
//        llListTitle = (LinearLayout) rootView.findViewById(R.id.llListTitle);
    }

    public void setListners() {
        spinYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               getImprestData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DashBoardActivity.onLeft();
            }
        });

        btnBackImprest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new HomeFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
                        .replace(R.id.frame_container, fragment).commit();
            }
        });
    }

    public void fillspinYear(){
            progressDialog = new ProgressDialog(mContext);
            progressDialog.setMessage("Please Wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        HashMap<String, String> params = new HashMap<String, String>();
                        getTermAsyncTask = new GetTermAsyncTask(params);
                        termModels = getTermAsyncTask.execute().get();

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                if (termModels.size() > 0) {
                                    ArrayList<String> termText = new ArrayList<String>();
                                    for (int i = 0; i < termModels.size(); i++) {
                                        termText.add(termModels.get(i).getTerm());
                                    }
                                    ArrayAdapter<String> adapterSpinYear = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_dropdown_item, termText);
                                    spinYear.setAdapter(adapterSpinYear);
                                } else {
                                    progressDialog.dismiss();
                                }
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

    }

    public void getImprestData(){
        if(Utility.isNetworkConnected(mContext)) {
            progressDialog = new ProgressDialog(mContext);
            progressDialog.setMessage("Please Wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        HashMap<String, String> params = new HashMap<String, String>();
                        String text = spinYear.getSelectedItem().toString();
                        String id = "";
                        for (int i = 0; i < termModels.size(); i++) {
                            if (text.equals(termModels.get(i).getTerm())) {
                                id = termModels.get(i).getTermId();
                            }
                        }

                        params.put("Term", id);
                        params.put("StudentID", Utility.getPref(mContext, "studid"));
                        getImprestDataAsyncTask = new GetImprestDataAsyncTask(params);
                        imprestModels = getImprestDataAsyncTask.execute().get();

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                if (imprestModels.size() > 0) {
                                    txtNoRecordsHomework.setVisibility(View.GONE);
                                    tblRowBalance.setVisibility(View.VISIBLE);
                                    tblRowOpeningBalance.setVisibility(View.VISIBLE);
                                    txtMyBalance.setText(imprestModels.get(0).getMyBalance());
                                    txtOpeningBalaceTop.setText(imprestModels.get(0).getOpeningBalanceTop());
//                                llListTitle.setVisibility(View.VISIBLE);
                                    listImprestData.setVisibility(View.VISIBLE);
                                    if (imprestModels.size() > 0 && imprestModels.get(0).getBalance() != null) {
                                        imprestListAdapter = new ImprestListAdapter(mContext, imprestModels);
                                        listImprestData.setAdapter(imprestListAdapter);
                                    }
                                } else {
                                    progressDialog.dismiss();
                                    tblRowBalance.setVisibility(View.GONE);
                                    tblRowOpeningBalance.setVisibility(View.GONE);
                                    txtNoRecordsHomework.setVisibility(View.VISIBLE);
                                    listImprestData.setVisibility(View.GONE);
//                                llListTitle.setVisibility(View.GONE);
                                }

                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }else{
            Utility.ping(mContext,"Network not available");
        }
    }
}