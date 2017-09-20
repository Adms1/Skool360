package com.anandniketanshilaj.skool360.skool360.Fragments;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.anandniketanshilaj.skool360.R;
import com.anandniketanshilaj.skool360.skool360.Activities.DashBoardActivity;
import com.anandniketanshilaj.skool360.skool360.Adapter.HomeImageAdapter;
import com.anandniketanshilaj.skool360.skool360.Adapter.ImageAdapter;
import com.anandniketanshilaj.skool360.skool360.AsyncTasks.AddDeviceDetailAsyncTask;
import com.anandniketanshilaj.skool360.skool360.AsyncTasks.GetUserProfileAsyncTask;
import com.anandniketanshilaj.skool360.skool360.Models.StudProfileModel;
import com.anandniketanshilaj.skool360.skool360.Utility.Utility;
import com.google.firebase.iid.FirebaseInstanceId;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Harsh on 04-Aug-16.
 */
public class HomeFragment extends Fragment {

    private View rootView;
    private Button btnMenu;
    private GridView grid_view;
    private Context mContext;
    private Fragment fragment = null;
    private FragmentManager fragmentManager = null;
    //    Change Megha 04-09-2017
    private CircleImageView profile_image;
    private ViewPager viewPageAndroid;
    int NUM_PAGES;
    private int[] sliderImagesId = new int[]{
            R.drawable.banner1,
            R.drawable.banner2, R.drawable.banner3};
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; //

    private GetUserProfileAsyncTask getUserProfileAsyncTask = null;
    private ArrayList<StudProfileModel> studDetailList = new ArrayList<>();
    private ProgressDialog progressDialog = null;
    private ImageLoader imageLoader;
    private TextView student_name_txt, student_classname_txt, teacher_name_txt, teacher_name1_txt,
            vehicle_name_txt, vehicle_picktime_txt, vehicle_droptime_txt, admission_txt, attendance_txt;

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.home_fragment, container, false);
        mContext = getActivity().getApplicationContext();
        initViews();
        setListners();
        if (Utility.isNetworkConnected(mContext)) {
            getRegistrationID();
            getUserProfile();
        } else {
            Utility.ping(mContext, "Network not available");
        }
        return rootView;
    }

    public void initViews() {

        btnMenu = (Button) rootView.findViewById(R.id.btnMenu);
        grid_view = (GridView) rootView.findViewById(R.id.grid_view);
        grid_view.setAdapter(new ImageAdapter(mContext));
        viewPageAndroid = (ViewPager) rootView.findViewById(R.id.viewPageAndroid);
        student_name_txt = (TextView) rootView.findViewById(R.id.student_name_txt);
        student_classname_txt = (TextView) rootView.findViewById(R.id.student_classname_txt);
        teacher_name_txt = (TextView) rootView.findViewById(R.id.teacher_name_txt);
        teacher_name1_txt = (TextView) rootView.findViewById(R.id.teacher_name1_txt);
        vehicle_name_txt = (TextView) rootView.findViewById(R.id.vehicle_name_txt);
        vehicle_picktime_txt = (TextView) rootView.findViewById(R.id.vehicle_picktime_txt);
        vehicle_droptime_txt = (TextView) rootView.findViewById(R.id.vehicle_droptime_txt);
        admission_txt = (TextView) rootView.findViewById(R.id.admission_txt);
        attendance_txt = (TextView) rootView.findViewById(R.id.attendance_txt);

        HomeImageAdapter adapterView = new HomeImageAdapter(getActivity(), sliderImagesId);
        viewPageAndroid.setAdapter(adapterView);
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES - 1) {
                    currentPage = 0;
                }
                viewPageAndroid.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);


        profile_image = (CircleImageView) rootView.findViewById(R.id.profile_image);
        imageLoader = ImageLoader.getInstance();
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300))
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                mContext)
                .threadPriority(Thread.MAX_PRIORITY)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .denyCacheImageMultipleSizesInMemory()
                .tasksProcessingOrder(QueueProcessingType.LIFO)// .enableLogging()
                .build();
        imageLoader.init(config.createDefault(mContext));
    }

    public void getRegistrationID() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        //getting old saved token
        String old_token = Utility.getPref(getActivity(), "registration_id");
        //check if called token is new
        if (refreshedToken != null && !old_token.equalsIgnoreCase(refreshedToken)) {
            Utility.setPref(getActivity(), "registration_id", refreshedToken);
            sendRegistrationToServer(refreshedToken, Settings.Secure.getString(getActivity().getContentResolver(),
                    Settings.Secure.ANDROID_ID));
        } else {
            sendRegistrationToServer(old_token, Settings.Secure.getString(getActivity().getContentResolver(),
                    Settings.Secure.ANDROID_ID));
        }
    }
    private void sendRegistrationToServer(String token, String uniqueID) {
        try {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("StudentID", Utility.getPref(getActivity(), "studid"));//Utility.getPref(getActivity(), "studid")
            hashMap.put("DeviceId", uniqueID);
            hashMap.put("TokenId", token);
            AddDeviceDetailAsyncTask addDeviceDetailAsyncTask = new AddDeviceDetailAsyncTask(hashMap);
            boolean result = addDeviceDetailAsyncTask.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //change Megha 04-09-2017

    public void setListners() {
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DashBoardActivity.onLeft();
            }
        });
        grid_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                /*                else if (position == 2) {
//                    fragment = new ClassworkFragment();
//                    fragmentManager = getFragmentManager();
//                    fragmentManager.beginTransaction()
//                            .setCustomAnimations(0, 0)
//                            .replace(R.id.frame_container, fragment).commit();
//                }
                     else if (position == 4) {
                    fragment = new AnnouncementFragment();
                    fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .setCustomAnimations(0, 0)
                            .replace(R.id.frame_container, fragment).commit();
                }
                    else if (position == 5) {
                    fragment = new CircularFragment();
                    fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .setCustomAnimations(0, 0)
                            .replace(R.id.frame_container, fragment).commit();
                }
                     else if (position == 5) {
                    fragment = new ExamFragment();
                    fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .setCustomAnimations(0, 0)
                            .replace(R.id.frame_container, fragment).commit();
                }else if (position == 10) {
                    fragment = new CourseFragment();
                    fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .setCustomAnimations(0, 0)
                            .replace(R.id.frame_container, fragment).commit();
                }
                else if (position == 8) {
                    fragment = new EventFragment();
                    fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .setCustomAnimations(0, 0)
                            .replace(R.id.frame_container, fragment).commit();
                }
                */

                if (position == 0) {
                    fragment = new AttendanceFragment();
                    fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .setCustomAnimations(R.anim.zoom_in, R.anim.zoom_out)
                            .replace(R.id.frame_container, fragment).commit();
                } else if (position == 1) {
                    fragment = new HomeworkFragment();
                    fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .setCustomAnimations(R.anim.zoom_in, R.anim.zoom_out)
                            .replace(R.id.frame_container, fragment).commit();
                } else if (position == 2) {
                    fragment = new TimeTableFragment();
                    fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .setCustomAnimations(R.anim.zoom_in, R.anim.zoom_out)
                            .replace(R.id.frame_container, fragment).commit();
//                    "Attendance", "Homework", "Timetable","Unit Test",  "Results", "Report Card",
//                    "Appointment Request", "Imprest", "Event", "Canteen",  "Course"};
                } else if (position == 3) {
                    fragment = new UnitTestFragment();
                    fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .setCustomAnimations(R.anim.zoom_in, R.anim.zoom_out)
                            .replace(R.id.frame_container, fragment).commit();
                } else if (position == 4) {
                    fragment = new ResultFragment();
                    fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .setCustomAnimations(R.anim.zoom_in, R.anim.zoom_out)
                            .replace(R.id.frame_container, fragment).commit();
                } else if (position == 5) {
                    fragment = new ReportCardFragment();
                    fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .setCustomAnimations(R.anim.zoom_in, R.anim.zoom_out)
                            .replace(R.id.frame_container, fragment).commit();
                } else if (position == 6) {
                    fragment = new FeesFragment();
                    fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .setCustomAnimations(R.anim.zoom_in, R.anim.zoom_out)
                            .replace(R.id.frame_container, fragment).commit();
                } else if (position == 7) {
                    fragment = new ImprestFragment();
                    fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .setCustomAnimations(R.anim.zoom_in, R.anim.zoom_out)
                            .replace(R.id.frame_container, fragment).commit();
                } else if (position == 8) {
                    fragment = new CanteenFragment();
                    fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .setCustomAnimations(R.anim.zoom_in, R.anim.zoom_out)
                            .replace(R.id.frame_container, fragment).commit();
                } else if (position == 9) {
                    fragment = new AppointmentFragment();
                    fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .setCustomAnimations(R.anim.zoom_in, R.anim.zoom_out)
                            .replace(R.id.frame_container, fragment).commit();
                } else if (position == 10) {
                    fragment = new PrincipalMessageFragment();
                    fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .setCustomAnimations(R.anim.zoom_in, R.anim.zoom_out)
                            .replace(R.id.frame_container, fragment).commit();
                }
            }
        });
    }

    public void getUserProfile() {
//        progressDialog = new ProgressDialog(getActivity());
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage("Please wait...");
//        progressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HashMap<String, String> params = new HashMap<String, String>();
                    params.put("StudentID", Utility.getPref(mContext, "studid"));
                    getUserProfileAsyncTask = new GetUserProfileAsyncTask(params);
                    studDetailList = getUserProfileAsyncTask.execute().get();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            progressDialog.dismiss();
                            student_name_txt.setText(studDetailList.get(0).getStudentName());
                            imageLoader.displayImage(studDetailList.get(0).getStudentImage(), profile_image);
                            vehicle_picktime_txt.setText("Pick Up :" + studDetailList.get(0).getTransport_PicupTime());
                            vehicle_droptime_txt.setText("Drop off :" + studDetailList.get(0).getTransport_DropTime());
                            student_classname_txt.setText("Class :" + " " + studDetailList.get(0).getStandard() + " " + "Standard -" + studDetailList.get(0).getStudClass());
                            teacher_name1_txt.setText(studDetailList.get(0).getTeacherName());
                            admission_txt.setText("Admission No :" + " " + studDetailList.get(0).getGRNO());
                            if (studDetailList.get(0).getTodayAttendance().equalsIgnoreCase("")) {
                                attendance_txt.setText("Attendance :" + " " + "N/A Today");
                            } else {
                                attendance_txt.setText("Attendance :" + " " + studDetailList.get(0).getTodayAttendance());
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}