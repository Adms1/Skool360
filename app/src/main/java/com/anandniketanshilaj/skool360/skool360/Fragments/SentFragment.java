package com.anandniketanshilaj.skool360.skool360.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anandniketan.skool360teacher.Adapter.ExpandableListAdapterSent;
import com.anandniketan.skool360teacher.AsyncTasks.PTMDeleteMeetingAsyncTask;
import com.anandniketan.skool360teacher.AsyncTasks.PTMTeacherStudentGetDetailAsyncTask;
import com.anandniketan.skool360teacher.Interfacess.onDeleteButton;
import com.anandniketan.skool360teacher.Models.MainPtmSentDeleteResponse;
import com.anandniketan.skool360teacher.Models.PTMInboxResponse.FinalArrayInbox;
import com.anandniketan.skool360teacher.Models.PTMInboxResponse.MainPtmInboxResponse;
import com.anandniketan.skool360teacher.R;
import com.anandniketan.skool360teacher.Utility.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SentFragment extends Fragment {
    private View rootView;
    private TextView txtNoRecordsSent;
    private Context mContext;
    private ProgressDialog progressDialog = null;
    private PTMTeacherStudentGetDetailAsyncTask ptmTeacherStudentGetDetailAsyncTask = null;
    private int lastExpandedPosition = -1;
    private LinearLayout Sent_header;
    private PTMDeleteMeetingAsyncTask ptmDeleteMeetingAsyncTask = null;
    private MainPtmSentDeleteResponse responsedelete;

    ExpandableListAdapterSent expandableListAdapterSent;
    ExpandableListView lvExpSent;
    List<String> listDataHeader = new ArrayList<>();
    HashMap<String, List<FinalArrayInbox>> listDataChild = new HashMap<>();
    MainPtmInboxResponse response;
    String finalMessageIdArray;

    public SentFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_sent, container, false);
        mContext = getActivity();

        initViews();
        setListners();

        return rootView;
    }

    public void initViews() {
        txtNoRecordsSent = (TextView) rootView.findViewById(R.id.txtNoRecordsSent);
        lvExpSent = (ExpandableListView) rootView.findViewById(R.id.lvExpSent);
        Sent_header = (LinearLayout) rootView.findViewById(R.id.Sent_header);
        setUserVisibleHint(true);

    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && rootView != null) {
            getSentData();
        }
        // execute your data loading logic.
    }

    public void setListners() {
        lvExpSent.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1
                        && groupPosition != lastExpandedPosition) {
                    lvExpSent.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });
    }

    public void getSentData() {
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
                        params.put("UserID", Utility.getPref(mContext, "StaffID"));
                        params.put("UserType", "staff");
                        params.put("MessgaeType", "Sent");
                        ptmTeacherStudentGetDetailAsyncTask = new PTMTeacherStudentGetDetailAsyncTask(params);
                        response = ptmTeacherStudentGetDetailAsyncTask.execute().get();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                if (response.getFinalArray().size() > 0) {
                                    txtNoRecordsSent.setVisibility(View.GONE);
                                    Sent_header.setVisibility(View.VISIBLE);
                                    setExpandableListData();
                                    expandableListAdapterSent = new ExpandableListAdapterSent(getActivity(), listDataHeader, listDataChild, new onDeleteButton() {
                                        @Override
                                        public void deleteSentMessage() {
                                            ArrayList<String> id = new ArrayList<>();
                                            String StudentArray = null;
                                            ArrayList<String> array = expandableListAdapterSent.getMessageId();
                                            for (int j = 0; j < array.size(); j++) {
                                                id.add(array.get(j).toString());
                                            }
                                            finalMessageIdArray = String.valueOf(id);
                                            finalMessageIdArray = finalMessageIdArray.substring(1, finalMessageIdArray.length() - 1);
//                                            progressDialog = new ProgressDialog(mContext);
//                                            progressDialog.setMessage("Please Wait...");
//                                            progressDialog.setCancelable(false);
//                                            progressDialog.show();

                                            if (!finalMessageIdArray.equalsIgnoreCase("")) {
                                                new Thread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        try {
                                                            HashMap<String, String> params = new HashMap<String, String>();
                                                            params.put("MessageID", finalMessageIdArray.trim());

                                                            ptmDeleteMeetingAsyncTask = new PTMDeleteMeetingAsyncTask(params);
                                                            responsedelete = ptmDeleteMeetingAsyncTask.execute().get();
                                                            getActivity().runOnUiThread(new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                    progressDialog.dismiss();
                                                                    if (responsedelete.getFinalArray().size() >= 0) {
                                                                        Utility.ping(mContext, "Delete Message.");
                                                                        expandableListAdapterSent.notifyDataSetChanged();
                                                                        getSentData();
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
                                                Utility.ping(mContext, "something went wrong.");
                                            }
                                        }
                                    });
                                    lvExpSent.setAdapter(expandableListAdapterSent);
                                    lvExpSent.deferNotifyDataSetChanged();
                                } else {
                                    progressDialog.dismiss();
                                    txtNoRecordsSent.setVisibility(View.VISIBLE);
                                    Sent_header.setVisibility(View.GONE);
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

    public void setExpandableListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<FinalArrayInbox>>();

        for (int j = 0; j < response.getFinalArray().size(); j++) {
            listDataHeader.add(response.getFinalArray().get(j).getUserName() + "|" +
                    response.getFinalArray().get(j).getMeetingDate() + "|" +
                    response.getFinalArray().get(j).getSubjectLine());

            ArrayList<FinalArrayInbox> rows = new ArrayList<FinalArrayInbox>();
            rows.add(response.getFinalArray().get(j));
            listDataChild.put(listDataHeader.get(j), rows);
        }
    }
}
