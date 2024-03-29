package com.anandniketanshilaj.skool360.skool360.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anandniketanshilaj.skool360.R;
import com.anandniketanshilaj.skool360.skool360.Models.HomeWorkResponse.HomeWorkInfo;

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
    private HashMap<String, ArrayList<HomeWorkInfo>> _listDataChild;
    String FontStyle, splitFont1, splitFont2, splitFont3, splitFont4, compareStr;
    TextView homework_title_txt, subject_title_txt, chapter_title_txt, lblchaptername, objective_title_txt, lblobjective, que_title_txt, lblque;
    Typeface typeface;
//    ImageView imageView;

    public ExpandableListAdapterHomework(Context context, List<String> listDataHeader,
                                         HashMap<String, ArrayList<HomeWorkInfo>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public ArrayList<HomeWorkInfo> getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition));
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             final boolean isLastChild, View convertView, ViewGroup parent) {

        final ArrayList<HomeWorkInfo> childData = getChild(groupPosition, 0);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item_homework, null);
        }
        final LinearLayout chapter_linear = (LinearLayout) convertView.findViewById(R.id.chapter_linear);
        final LinearLayout objective_linear = (LinearLayout) convertView.findViewById(R.id.objective_linear);
        final LinearLayout que_linear = (LinearLayout) convertView.findViewById(R.id.que_linear);
        final LinearLayout homework_linear = (LinearLayout) convertView.findViewById(R.id.homework_linear);

        subject_title_txt = (TextView) convertView.findViewById(R.id.subject_title_txt);
        homework_title_txt = (TextView) convertView.findViewById(R.id.homework_title_txt);
        chapter_title_txt = (TextView) convertView.findViewById(R.id.chapter_title_txt);
        lblchaptername = (TextView) convertView.findViewById(R.id.lblchaptername);
        objective_title_txt = (TextView) convertView.findViewById(R.id.objective_title_txt);
        lblobjective = (TextView) convertView.findViewById(R.id.lblobjective);
        que_title_txt = (TextView) convertView.findViewById(R.id.que_title_txt);
        lblque = (TextView) convertView.findViewById(R.id.lblque);
        final ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);


        chapter_title_txt.setText("Chapter Name");
        objective_title_txt.setText("Objective");
        que_title_txt.setText(Html.fromHtml("Assessment\nQuestion"));

        FontStyle = "";
        splitFont1 = "";
        splitFont2 = "";
        splitFont3 = "";
        splitFont4 = "";
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
            subject_title_txt.setText(Html.fromHtml(childData.get(childPosition).getSubject().replaceAll("\\<.*?\\>", "").replaceAll("\\n", "").trim()));
            homework_title_txt.setText(Html.fromHtml(childData.get(childPosition).getHomeWork().replaceAll("\\<.*?\\>", "").replaceAll("\\n", "").trim()));
            lblchaptername.setText(Html.fromHtml(childData.get(childPosition).getChapterName().replaceAll("\\<.*?\\>", "").replaceAll("\\n", "").trim()));
            lblobjective.setText(childData.get(childPosition).getObjective().replaceAll("\\<.*?\\>", "").replaceAll("\\n", "").trim());
            lblque.setText(Html.fromHtml(childData.get(childPosition).getAssessmentQue().replaceAll("\\<.*?\\>", "").replaceAll("\\n", "").trim()));
        } else {
            typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/arial.ttf");
            homework_title_txt.setTypeface(typeface);
            lblchaptername.setTypeface(typeface);
            lblobjective.setTypeface(typeface);
            lblque.setTypeface(typeface);
            subject_title_txt.setText(Html.fromHtml(childData.get(childPosition).getSubject().replaceAll("\\<.*?\\>", "").replaceAll("\\n", "").trim()));
            homework_title_txt.setText(Html.fromHtml(childData.get(childPosition).getHomeWork().replaceAll("\\<.*?\\>", "").replaceAll("\\n", "").trim()));
            lblchaptername.setText(Html.fromHtml(childData.get(childPosition).getChapterName().replaceAll("\\<.*?\\>", "").replaceAll("\\n", "").trim()));
            lblobjective.setText(Html.fromHtml(childData.get(childPosition).getObjective().replaceAll("\\<.*?\\>", "").replaceAll("\\n", "").trim()));
            lblque.setText(Html.fromHtml(childData.get(childPosition).getAssessmentQue().replaceAll("\\<.*?\\>", "").replaceAll("\\n", "").trim()));
        }

        if (childData.get(childPosition).getVisible()) {
            homework_linear.setVisibility(View.VISIBLE);
            chapter_linear.setVisibility(View.VISIBLE);
            objective_linear.setVisibility(View.VISIBLE);
            que_linear.setVisibility(View.VISIBLE);
        } else {
            homework_linear.setVisibility(View.GONE);
            chapter_linear.setVisibility(View.GONE);
            objective_linear.setVisibility(View.GONE);
            que_linear.setVisibility(View.GONE);
        }
        subject_title_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < childData.size(); i++) {
                    if (i == childPosition) {
                        childData.get(childPosition).setVisible(!childData.get(childPosition).getVisible());
                        imageView.setBackgroundResource(R.drawable.arrow_1_42_down);
                    } else {
                        childData.get(i).setVisible(false);
                        imageView.setBackgroundResource(R.drawable.arrow_1_42);
                    }
                }
                notifyDataSetChanged();
            }
        });
        imageView.setBackgroundResource(R.drawable.arrow_1_42);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < childData.size(); i++) {
                    if (i == childPosition) {
                        childData.get(childPosition).setVisible(!childData.get(childPosition).getVisible());
                        compareStr = String.valueOf(childData.get(childPosition).getVisible());

                    } else {
                        childData.get(i).setVisible(false);
                    }
                }
                notifyDataSetChanged();

            }
        });
//        imageView.setBackgroundResource(R.drawable.arrow_1_42);

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
            convertView.setBackgroundResource(R.color.orange);
        } else {
            convertView.setBackgroundResource(R.color.gray);
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

