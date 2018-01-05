package com.anandniketanshilaj.skool360.skool360.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.anandniketanshilaj.skool360.R;
import com.anandniketanshilaj.skool360.skool360.Activities.DashBoardActivity;
import com.anandniketanshilaj.skool360.skool360.Adapter.ExpandableListAdapterPayment;
import com.anandniketanshilaj.skool360.skool360.Adapter.payment_list_adapter;
import com.anandniketanshilaj.skool360.skool360.AsyncTasks.FeesDetailsAsyncTask;
import com.anandniketanshilaj.skool360.skool360.AsyncTasks.GetPaymentLedgerAsyncTask;
import com.anandniketanshilaj.skool360.skool360.AsyncTasks.GetPaymentLedgerDetailAsyncTask;
import com.anandniketanshilaj.skool360.skool360.AsyncTasks.PTMTeacherStudentInsertDetailAsyncTask;
import com.anandniketanshilaj.skool360.skool360.AsyncTasks.PaymentDetailsAsyncTask;
import com.anandniketanshilaj.skool360.skool360.Models.FeesModel;
import com.anandniketanshilaj.skool360.skool360.Models.PaymentLedgerModel;
import com.anandniketanshilaj.skool360.skool360.Models.PaymentResponse.GetPaymentLedgerResponse;
import com.anandniketanshilaj.skool360.skool360.Models.PaymentResponse.PaymentMainResponse;
import com.anandniketanshilaj.skool360.skool360.Utility.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class PaymentNewFragment extends Fragment {
    private View rootView;
    private Button btnMenu, btnBackUnitTest;
    private TextView txtNoRecordsUnitTest;
    private Context mContext;
    private FragmentManager fragmentManager = null;
    private ProgressDialog progressDialog = null;
    ListView listview;
    PaymentDetailsAsyncTask paymentDetailsAsyncTask;
    PaymentMainResponse paymentMainResponse;
    GetPaymentLedgerDetailAsyncTask paymentLedgerAsyncTask;
    GetPaymentLedgerResponse paymentLedgerResponse;
    payment_list_adapter adapter;

    public PaymentNewFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_payment, container, false);
        mContext = getActivity();

        initViews();
        setListners();
        getFeesData();


        return rootView;
    }

    public void initViews() {
        btnMenu = (Button) rootView.findViewById(R.id.btnMenu);
        txtNoRecordsUnitTest = (TextView) rootView.findViewById(R.id.txtNoRecordsUnitTest);
        btnBackUnitTest = (Button) rootView.findViewById(R.id.btnBackUnitTest);

        listview = (ListView) rootView.findViewById(R.id.listview);

    }

    public void setListners() {
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DashBoardActivity.onLeft();
            }
        });

        btnBackUnitTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FeesFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
                        .replace(R.id.frame_container, fragment).commit();
            }
        });
    }

    public void getFeesData() {

        if (Utility.isNetworkConnected(mContext)) {

            progressDialog = new ProgressDialog(mContext);
            progressDialog.setMessage("Please Wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("StudentID", Utility.getPref(mContext, "studid"));


                        paymentDetailsAsyncTask = new PaymentDetailsAsyncTask(params);
                        paymentMainResponse = paymentDetailsAsyncTask.execute().get();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                if (paymentMainResponse.getSuccess().equalsIgnoreCase("True")) {

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
        } else {
            Utility.ping(mContext, "Network not available");
        }
    }

    public void getPaymentLedger() {

        if (Utility.isNetworkConnected(mContext)) {

            progressDialog = new ProgressDialog(mContext);
            progressDialog.setMessage("Please Wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("StudentID", Utility.getPref(mContext, "studid"));


                        paymentLedgerAsyncTask = new GetPaymentLedgerDetailAsyncTask(params);
                        paymentLedgerResponse = paymentLedgerAsyncTask.execute().get();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();

                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } else {
            Utility.ping(mContext, "Network not available");
        }
    }

}
