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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.anandniketanshilaj.skool360.R;
import com.anandniketanshilaj.skool360.skool360.Activities.DashBoardActivity;
import com.anandniketanshilaj.skool360.skool360.Adapter.ExpandableListAdapter;
import com.anandniketanshilaj.skool360.skool360.AsyncTasks.ChangePasswordAsyncTask;
import com.anandniketanshilaj.skool360.skool360.AsyncTasks.GetStudClassworkAsyncTask;
import com.anandniketanshilaj.skool360.skool360.AsyncTasks.GetUserProfileAsyncTask;
import com.anandniketanshilaj.skool360.skool360.Models.ClassWorkModel;
import com.anandniketanshilaj.skool360.skool360.Models.StudProfileModel;
import com.anandniketanshilaj.skool360.skool360.Utility.Utility;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Harsh on 04-Aug-16.
 */
public class ClassworkFragment extends Fragment {
    private View rootView;
    private Button btnMenu, btnFilterClasswork, btnBackClasswork;
    private static TextView fromDate, toDate;
    private TextView txtNoRecordsClasswork;
    private static String dateFinal;
    private Context mContext;
    private GetStudClassworkAsyncTask getStudClassworkAsyncTask = null;
    private ProgressDialog progressDialog = null;
    private ArrayList<ClassWorkModel> classWorkModels = new ArrayList<>();
    private static boolean isFromDate = false;
    private int lastExpandedPosition = -1;

    ExpandableListAdapter listAdapter;
    ExpandableListView lvExpClassWork;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    public ClassworkFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.classwork_fragment, container, false);
        mContext = getActivity();

        initViews();
        setListners();

        return rootView;
    }

    public void initViews() {
        btnMenu = (Button) rootView.findViewById(R.id.btnMenu);
        fromDate = (TextView) rootView.findViewById(R.id.fromDate);
        toDate = (TextView) rootView.findViewById(R.id.toDate);
        btnFilterClasswork = (Button) rootView.findViewById(R.id.btnFilterClasswork);
        txtNoRecordsClasswork = (TextView) rootView.findViewById(R.id.txtNoRecordsClasswork);
        btnBackClasswork = (Button) rootView.findViewById(R.id.btnBackClasswork);
        lvExpClassWork = (ExpandableListView) rootView.findViewById(R.id.lvExpClassWork);

        final Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH)+1;
        int dd = calendar.get(Calendar.DAY_OF_MONTH);

        //load today's data first
        fromDate.setText(dd + "/" + mm + "/" + yy);
        toDate.setText(dd + "/" + mm + "/" + yy);
        getClassworkData(fromDate.getText().toString(), toDate.getText().toString());
    }

    public void setListners() {
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DashBoardActivity.onLeft();
            }
        });

        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFromDate = true;
                DialogFragment newFragment = new SelectDateFragment();
                newFragment.show(getFragmentManager(), "DatePicker");
            }
        });

        toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFromDate = false;
                DialogFragment newFragment = new SelectDateFragment();
                newFragment.show(getFragmentManager(), "DatePicker");
            }
        });

        btnFilterClasswork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!fromDate.getText().toString().equalsIgnoreCase("")) {
                    if(!toDate.getText().toString().equalsIgnoreCase("")) {

                        getClassworkData(fromDate.getText().toString(), toDate.getText().toString());

                    }else {
                        Utility.pong(mContext, "You need to select a to date");
                    }
                }else {
                    Utility.pong(mContext, "You need to select a from date");
                }
            }
        });

        btnBackClasswork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new HomeFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .setCustomAnimations(0, 0)
                        .replace(R.id.frame_container, fragment).commit();
            }
        });

        lvExpClassWork.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1
                        && groupPosition != lastExpandedPosition) {
                    lvExpClassWork.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });
    }

    public void getClassworkData(final String fromDate, final String toDate){
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
//                    params.put("StudentID", "1027");
                        params.put("StudentID", Utility.getPref(mContext, "studid"));
                        params.put("ClassWorkFromDate", fromDate);
                        params.put("ClassWorkToDate", toDate);
                        classWorkModels.clear();
                        getStudClassworkAsyncTask = new GetStudClassworkAsyncTask(params);
                        classWorkModels = getStudClassworkAsyncTask.execute().get();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                if (classWorkModels.size() > 0) {
                                    txtNoRecordsClasswork.setVisibility(View.GONE);
                                    prepaareList();
                                    lvExpClassWork.setVisibility(View.VISIBLE);
                                    listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild, true);
                                    lvExpClassWork.setAdapter(listAdapter);

                                } else {
                                    progressDialog.dismiss();
                                    txtNoRecordsClasswork.setVisibility(View.VISIBLE);
                                    lvExpClassWork.setVisibility(View.GONE);
                                }
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }else {
            Utility.ping(mContext,"Network not available");
        }
    }

    public void prepaareList(){
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        for(int i = 0;i < classWorkModels.size();i++){
            listDataHeader.add(classWorkModels.get(i).getClassWorkDate());
            ArrayList<String> rows = new ArrayList<String>();
            for(int j = 0;j < classWorkModels.get(i).getClassWorkDatas().size();j++){
                rows.add(classWorkModels.get(i).getClassWorkDatas().get(j).getSubject()+":"+classWorkModels.get(i).getClassWorkDatas().get(j).getClasswork());
                if(!(classWorkModels.get(i).getClassWorkDatas().get(j).getProxyStatus().equalsIgnoreCase("-") || classWorkModels.get(i).getClassWorkDatas().get(j).getProxyStatus().equalsIgnoreCase("0"))){
                    rows.add(classWorkModels.get(i).getClassWorkDatas().get(j).getProxyStatus());
                }
                listDataChild.put(listDataHeader.get(i), rows);
            }
        }
    }

    public static class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, yy, mm, dd);
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetDate(yy, mm+1, dd);
        }
        public void populateSetDate(int year, int month, int day) {
            dateFinal = day+"/"+month+"/"+year;
            if(isFromDate){
                fromDate.setText(dateFinal);
            }else {
                toDate.setText(dateFinal);
            }
        }
    }
}