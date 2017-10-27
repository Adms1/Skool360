package com.anandniketanshilaj.skool360.skool360.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.anandniketanshilaj.skool360.R;
import com.anandniketanshilaj.skool360.skool360.AsyncTasks.PTMTeacherStudentInsertDetailAsyncTask;
import com.anandniketanshilaj.skool360.skool360.Interfacess.onInboxRead;
import com.anandniketanshilaj.skool360.skool360.Models.MainPtmSentMessageResponse;
import com.anandniketanshilaj.skool360.skool360.Models.PTMInboxResponse.FinalArrayInbox;
import com.anandniketanshilaj.skool360.skool360.Utility.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by admsandroid on 10/25/2017.
 */

public class ExpandableListAdapterInbox extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<FinalArrayInbox>> listChildData;
    private onInboxRead onInboxRead;
    String messageId, FromId, Toid, messageDate, messageSubject, messageMessageLine;
    private PTMTeacherStudentInsertDetailAsyncTask getPTMTeacherStudentInsertDetailAsyncTask = null;
    MainPtmSentMessageResponse mainPtmSentMessageResponse;
    private ArrayList<FinalArrayInbox> staffattendaceModel = new ArrayList<>();


    public ExpandableListAdapterInbox(Context context, List<String> listDataHeader,
                                      HashMap<String, List<FinalArrayInbox>> listChildData, onInboxRead onInboxRead) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this.listChildData = listChildData;
        this.onInboxRead = onInboxRead;
    }

    public ExpandableListAdapterInbox(FragmentActivity activity, List<String> listDataHeader, HashMap<String, List<FinalArrayInbox>> listDataChild, onInboxRead onInboxRead) {
    }

    @Override
    public List<FinalArrayInbox> getChild(int groupPosition, int childPosititon) {
        return this.listChildData.get(this._listDataHeader.get(groupPosition));
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        List<FinalArrayInbox> childData = getChild(groupPosition, 0);


        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item_inbox, null);
        }


        TextView txtSubject;
        txtSubject = (TextView) convertView.findViewById(R.id.txtSubject);
        messageId = childData.get(childPosition).getMessageID();
        FromId = childData.get(childPosition).getFromID();
        Toid = childData.get(childPosition).getToID();
        messageDate = childData.get(childPosition).getMeetingDate();
        messageSubject = childData.get(childPosition).getSubjectLine();
        messageMessageLine = childData.get(childPosition).getDescription();

        if (childData.get(childPosition).getReadStatus().equalsIgnoreCase("UnRead")) {
            txtSubject.setTypeface(null, Typeface.BOLD);
            onInboxRead.readMessageStatus();
            if (Utility.isNetworkConnected(_context)) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            HashMap<String, String> params = new HashMap<String, String>();
                            params.put("MessageID", messageId);
                            params.put("FromID", Utility.getPref(_context, "StaffID"));
                            params.put("ToID", Toid);
                            params.put("MeetingDate", messageDate);
                            params.put("SubjectLine", messageSubject);
                            params.put("Description", messageMessageLine);

                            getPTMTeacherStudentInsertDetailAsyncTask = new PTMTeacherStudentInsertDetailAsyncTask(params);
                            mainPtmSentMessageResponse = getPTMTeacherStudentInsertDetailAsyncTask.execute().get();
                            this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    if (mainPtmSentMessageResponse.getFinalArray().size() > 0) {
                                        Utility.ping(_context, "Send Sucessfully");
                                        staffattendaceModel.get(childPosition).setReadStatus("Read");
                                    } else {


                                    }
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    private void runOnUiThread(Runnable runnable) {
                    }
                }).start();
            } else {
                Utility.ping(_context, "Network not available");
            }
        } else

        {
            txtSubject.setTypeface(null, Typeface.NORMAL);
        }

        txtSubject.setText(childData.get(childPosition).

                getDescription());
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listChildData.get(this._listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String[] headerTitle = getGroup(groupPosition).toString().split("\\|");

        String headerTitle1 = headerTitle[0];
        String headerTitle2 = headerTitle[1];
        String headerTitle3 = headerTitle[2];

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_inbox, null);
        }
        TextView Student_name_inbox_txt, date_inbox_txt, subject_inbox_txt, view_inbox_txt;
        Student_name_inbox_txt = (TextView) convertView.findViewById(R.id.Student_name_inbox_txt);
        date_inbox_txt = (TextView) convertView.findViewById(R.id.date_inbox_txt);
        subject_inbox_txt = (TextView) convertView.findViewById(R.id.subject_inbox_txt);
        view_inbox_txt = (TextView) convertView.findViewById(R.id.view_inbox_txt);

        Student_name_inbox_txt.setText(headerTitle1);
        date_inbox_txt.setText(headerTitle2);
        subject_inbox_txt.setText(headerTitle3);

        if (isExpanded) {
            view_inbox_txt.setTextColor(_context.getResources().getColor(R.color.present_header));
        } else {
            view_inbox_txt.setTextColor(_context.getResources().getColor(R.color.absent_header));
        }


        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public ArrayList<FinalArrayInbox> getData() {
        return staffattendaceModel;
    }

}


