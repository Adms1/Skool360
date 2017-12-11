package com.anandniketanshilaj.skool360.skool360.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anandniketanshilaj.skool360.R;
import com.anandniketanshilaj.skool360.skool360.Models.HomeWorkModel;
import com.anandniketanshilaj.skool360.skool360.Models.UnitTestModel;
import com.anandniketanshilaj.skool360.skool360.Utility.Utility;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by admsandroid on 9/5/2017.
 */

public class ExpandableListAdapterHomework extends BaseExpandableListAdapter {

    private Context _context;
    boolean visible = true;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, ArrayList<HomeWorkModel.HomeWorkData>> _listDataChild;
    String FontStyle, splitFont1, splitFont2, splitFont3, splitFont4;
    TextView subject_title_txt, homework_title_txt, chapter_title_txt, lblchaptername, objective_title_txt, lblobjective, que_title_txt, lblque;
   ImageView imgRightSign;
     LinearLayout chapter_linear, objective_linear, que_linear;
    Typeface typeface;

    public ExpandableListAdapterHomework(Context context, List<String> listDataHeader,
                                         HashMap<String, ArrayList<HomeWorkModel.HomeWorkData>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public ArrayList<HomeWorkModel.HomeWorkData> getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition));
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             final boolean isLastChild, View convertView, ViewGroup parent) {

        final ArrayList<HomeWorkModel.HomeWorkData> childData = getChild(groupPosition, 0);




        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item_home_work, null);
        }

        subject_title_txt = (TextView) convertView.findViewById(R.id.subject_title_txt);
        homework_title_txt = (TextView) convertView.findViewById(R.id.homework_title_txt);
        imgRightSign = (ImageView) convertView.findViewById(R.id.imgRightSign);
        chapter_title_txt = (TextView) convertView.findViewById(R.id.chapter_title_txt);
        lblchaptername = (TextView) convertView.findViewById(R.id.lblchaptername);
        objective_title_txt = (TextView) convertView.findViewById(R.id.objective_title_txt);
        lblobjective = (TextView) convertView.findViewById(R.id.lblobjective);
        que_title_txt = (TextView) convertView.findViewById(R.id.que_title_txt);
        lblque = (TextView) convertView.findViewById(R.id.lblque);
        chapter_linear = (LinearLayout) convertView.findViewById(R.id.chapter_linear);
        objective_linear = (LinearLayout) convertView.findViewById(R.id.objective_linear);
        que_linear = (LinearLayout) convertView.findViewById(R.id.que_linear);

        subject_title_txt.setText(Html.fromHtml(childData.get(childPosition).getSubject() + ":"));
        chapter_title_txt.setText("Chapter Name :");
        objective_title_txt.setText("Objective :");
        que_title_txt.setText(Html.fromHtml("Assessment\nQuestion :"));
        FontStyle = "";
        splitFont1 = "";
        splitFont2 = "";
        splitFont3 = "";
        FontStyle = childData.get(childPosition).getFont();

        if (!FontStyle.equalsIgnoreCase("-|-|-|-")) {
            String[] splitFontStyle = FontStyle.split("\\|");
            Log.d("SplitFOnt", splitFontStyle[0]);
            splitFont1 = splitFontStyle[0].toString();
            splitFont2 = splitFontStyle[1].toString();
            splitFont3 = splitFontStyle[2].toString();
            splitFont4 = splitFontStyle[3].toString();

            SetLanguageHomework(splitFont1);
            SetLanguageChapterName(splitFont2);
            SetLanguageObjective(splitFont3);
            SetLanguageAssessmentQue(splitFont4);

            homework_title_txt.setText(Html.fromHtml(childData.get(childPosition).getHomework()));
            lblchaptername.setText(Html.fromHtml(childData.get(childPosition).getChapterName()));
            lblobjective.setText(Html.fromHtml(childData.get(childPosition).getObjective()));
            lblque.setText(Html.fromHtml(childData.get(childPosition).getAssessmentQue()));
        } else {
            typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/arial.ttf");
            homework_title_txt.setTypeface(typeface);
            lblchaptername.setTypeface(typeface);
            lblobjective.setTypeface(typeface);
            lblque.setTypeface(typeface);
            homework_title_txt.setText(Html.fromHtml(childData.get(childPosition).getHomework()));
            lblchaptername.setText(Html.fromHtml(childData.get(childPosition).getChapterName()));
            lblobjective.setText(Html.fromHtml(childData.get(childPosition).getObjective()));
            lblque.setText(Html.fromHtml(childData.get(childPosition).getAssessmentQue()));
        }
        homework_title_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (visible == true) {
                    homework_title_txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_1_42_down, 0);
                    chapter_linear.setVisibility(View.VISIBLE);
                    objective_linear.setVisibility(View.VISIBLE);
                    que_linear.setVisibility(View.VISIBLE);
                    chapter_title_txt.setVisibility(View.VISIBLE);
                    lblchaptername.setVisibility(View.VISIBLE);
                    objective_title_txt.setVisibility(View.VISIBLE);
                    lblobjective.setVisibility(View.VISIBLE);
                    que_title_txt.setVisibility(View.VISIBLE);
                    lblque.setVisibility(View.VISIBLE);
                    visible = false;
                } else {
                    homework_title_txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_1_42, 0);
                    chapter_linear.setVisibility(View.GONE);
                    objective_linear.setVisibility(View.GONE);
                    que_linear.setVisibility(View.GONE);
                    chapter_title_txt.setVisibility(View.GONE);
                    lblchaptername.setVisibility(View.GONE);
                    objective_title_txt.setVisibility(View.GONE);
                    lblobjective.setVisibility(View.GONE);
                    que_title_txt.setVisibility(View.GONE);
                    lblque.setVisibility(View.GONE);
                    visible = true;
                }
            }
        });
        homework_title_txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_1_42, 0);
        chapter_linear.setVisibility(View.GONE);
        objective_linear.setVisibility(View.GONE);
        que_linear.setVisibility(View.GONE);
        chapter_title_txt.setVisibility(View.GONE);
        lblchaptername.setVisibility(View.GONE);
        objective_title_txt.setVisibility(View.GONE);
        lblobjective.setVisibility(View.GONE);
        que_title_txt.setVisibility(View.GONE);
        lblque.setVisibility(View.GONE);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
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
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        if (isExpanded) {
            convertView.setBackgroundResource(R.drawable.homework_selected_bg);
        } else {
            convertView.setBackgroundResource(R.drawable.homework_subject_bg);
        }


        TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

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

    public void SetLanguageHomework(String type) {
        switch (type) {
            case "ArivNdr POMt":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fotns/Arvinder.ttf");
                homework_title_txt.setTypeface(typeface);
                break;
            case "Gujrati Saral-1":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/Gujrati-Saral-1.ttf");
                homework_title_txt.setTypeface(typeface);
                break;
            case "Gujrati Saral-2":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/G-SARAL2.TTF");
                homework_title_txt.setTypeface(typeface);
                break;
            case "Gujrati Saral-3":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/G-SARAL3.TTF");
                homework_title_txt.setTypeface(typeface);
                break;
            case "Gujrati Saral-4":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/G-SARAL4.TTF");
                homework_title_txt.setTypeface(typeface);
                break;
            case "Hindi Saral-4":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/H-SARAL0.TTF");
                homework_title_txt.setTypeface(typeface);
                break;
            case "Hindi Saral-1":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/h-saral1.TTF");
                homework_title_txt.setTypeface(typeface);
                break;
            case "Hindi Saral-2":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/h-saral2.TTF");
                homework_title_txt.setTypeface(typeface);
                break;
            case "Hindi Saral-3":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/h-saral3.TTF");
                homework_title_txt.setTypeface(typeface);
                break;
            case "Shivaji05":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/Shivaji05.ttf");
                homework_title_txt.setTypeface(typeface);
                break;
            case "Shruti":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/Shruti.ttf");
                homework_title_txt.setTypeface(typeface);
                break;
            default:
        }
    }

    public void SetLanguageChapterName(String type) {
        switch (type) {
            case "ArivNdr POMt":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fotns/Arvinder.ttf");
                lblchaptername.setTypeface(typeface);
                break;
            case "Gujrati Saral-1":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/Gujrati-Saral-1.ttf");
                lblchaptername.setTypeface(typeface);
                break;
            case "Gujrati Saral-2":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/G-SARAL2.TTF");
                lblchaptername.setTypeface(typeface);
                break;
            case "Gujrati Saral-3":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/G-SARAL3.TTF");
                lblchaptername.setTypeface(typeface);
                break;
            case "Gujrati Saral-4":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/G-SARAL4.TTF");
                lblchaptername.setTypeface(typeface);
                break;
            case "Hindi Saral-4":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/H-SARAL0.TTF");
                lblchaptername.setTypeface(typeface);
                break;
            case "Hindi Saral-1":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/h-saral1.TTF");
                lblchaptername.setTypeface(typeface);
                break;
            case "Hindi Saral-2":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/h-saral2.TTF");
                lblchaptername.setTypeface(typeface);
                break;
            case "Hindi Saral-3":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/h-saral3.TTF");
                lblchaptername.setTypeface(typeface);
                break;
            case "Shivaji05":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/Shivaji05.ttf");
                lblchaptername.setTypeface(typeface);
                break;
            case "Shruti":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/Shruti.ttf");
                lblchaptername.setTypeface(typeface);
                break;
            default:
        }
    }

    public void SetLanguageObjective(String type) {
        switch (type) {
            case "ArivNdr POMt":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fotns/Arvinder.ttf");
                lblobjective.setTypeface(typeface);
                break;
            case "Gujrati Saral-1":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/Gujrati-Saral-1.ttf");
                lblobjective.setTypeface(typeface);
                break;
            case "Gujrati Saral-2":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/G-SARAL2.TTF");
                lblobjective.setTypeface(typeface);
                break;
            case "Gujrati Saral-3":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/G-SARAL3.TTF");
                lblobjective.setTypeface(typeface);
                break;
            case "Gujrati Saral-4":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/G-SARAL4.TTF");
                lblobjective.setTypeface(typeface);
                break;
            case "Hindi Saral-4":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/H-SARAL0.TTF");
                lblobjective.setTypeface(typeface);
                break;
            case "Hindi Saral-1":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/h-saral1.TTF");
                lblobjective.setTypeface(typeface);
                break;
            case "Hindi Saral-2":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/h-saral2.TTF");
                lblobjective.setTypeface(typeface);
                break;
            case "Hindi Saral-3":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/h-saral3.TTF");
                lblobjective.setTypeface(typeface);
                break;
            case "Shivaji05":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/Shivaji05.ttf");
                lblobjective.setTypeface(typeface);
                break;
            case "Shruti":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/Shruti.ttf");
                lblobjective.setTypeface(typeface);
                break;
            default:
        }
    }

    public void SetLanguageAssessmentQue(String type) {
        switch (type) {
            case "ArivNdr POMt":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fotns/Arvinder.ttf");
                lblque.setTypeface(typeface);
                break;
            case "Gujrati Saral-1":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/Gujrati-Saral-1.ttf");
                lblque.setTypeface(typeface);
                break;
            case "Gujrati Saral-2":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/G-SARAL2.TTF");
                lblque.setTypeface(typeface);
                break;
            case "Gujrati Saral-3":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/G-SARAL3.TTF");
                lblque.setTypeface(typeface);
                break;
            case "Gujrati Saral-4":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/G-SARAL4.TTF");
                lblque.setTypeface(typeface);
                break;
            case "Hindi Saral-4":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/H-SARAL0.TTF");
                lblque.setTypeface(typeface);
                break;
            case "Hindi Saral-1":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/h-saral1.TTF");
                lblque.setTypeface(typeface);
                break;
            case "Hindi Saral-2":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/h-saral2.TTF");
                lblque.setTypeface(typeface);
                break;
            case "Hindi Saral-3":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/h-saral3.TTF");
                lblque.setTypeface(typeface);
                break;
            case "Shivaji05":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/Shivaji05.ttf");
                lblque.setTypeface(typeface);
                break;
            case "Shruti":
                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/Shruti.ttf");
                lblque.setTypeface(typeface);
                break;
            default:
        }
    }
}

