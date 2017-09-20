package com.anandniketanshilaj.skool360.skool360.Utility;

import android.util.Log;

import com.anandniketanshilaj.skool360.skool360.Models.AnnouncementModel;
import com.anandniketanshilaj.skool360.skool360.Models.AttendanceModel;
import com.anandniketanshilaj.skool360.skool360.Models.CanteenModel;
import com.anandniketanshilaj.skool360.skool360.Models.CircularModel;
import com.anandniketanshilaj.skool360.skool360.Models.ClassWorkModel;
import com.anandniketanshilaj.skool360.skool360.Models.EventModel;
import com.anandniketanshilaj.skool360.skool360.Models.FeesModel;
import com.anandniketanshilaj.skool360.skool360.Models.HomeWorkModel;
import com.anandniketanshilaj.skool360.skool360.Models.ImprestDataModel;
import com.anandniketanshilaj.skool360.skool360.Models.PaymentLedgerModel;
import com.anandniketanshilaj.skool360.skool360.Models.PrincipalModel;
import com.anandniketanshilaj.skool360.skool360.Models.ReportCardModel;
import com.anandniketanshilaj.skool360.skool360.Models.StudProfileModel;
import com.anandniketanshilaj.skool360.skool360.Models.TermModel;
import com.anandniketanshilaj.skool360.skool360.Models.TimetableModel;
import com.anandniketanshilaj.skool360.skool360.Models.ResultModel;
import com.anandniketanshilaj.skool360.skool360.Models.UnitTestModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Harsh on 04-Aug-16.
 */
public class ParseJSON {

    public static HashMap<String, String> parseLoginJson(String responseString) {
        HashMap<String, String> result = new HashMap<>();

        try {
            JSONObject reader = new JSONObject(responseString);
            String data_load_basket = reader.getString("Success");
            if (data_load_basket.toString().equals("True")) {
                JSONArray jsonMainNode = reader.optJSONArray("FinalArray");

                for (int i = 0; i < jsonMainNode.length(); i++) {
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                    result.put("StudentID", jsonChildNode.getString("StudentID"));
                    result.put("FamilyID", jsonChildNode.getString("FamilyID"));
                    result.put("StandardID", jsonChildNode.getString("StandardID"));
                    result.put("ClassID", jsonChildNode.getString("ClassID"));
                }
            } else {
                //invalid login
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static Boolean parseChangePwdJson(String responseString) {
        Boolean result = false;

        try {
            JSONObject reader = new JSONObject(responseString);
            String data_load_basket = reader.getString("Success");
            if (data_load_basket.toString().equals("True")) {
                result = true;
            } else {
                //invalid login
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static Boolean parseAppointmentJson(String responseString) {
        Boolean result = false;

        try {
            JSONObject reader = new JSONObject(responseString);
            String data_load_basket = reader.getString("Success");
            if (data_load_basket.toString().equals("True")) {
                result = true;
            } else {
                result = false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static ArrayList<StudProfileModel> parseUserProfileJson(String responseString) {
        ArrayList<StudProfileModel> result = new ArrayList<>();

        try {
            JSONObject reader = new JSONObject(responseString);
            String data_load_basket = reader.getString("Success");
            if (data_load_basket.toString().equals("True")) {
                JSONArray jsonMainNode = reader.optJSONArray("FinalArray");

                StudProfileModel studProfileModel = null;
                for (int i = 0; i < jsonMainNode.length(); i++) {
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                    studProfileModel = new StudProfileModel();
                    studProfileModel.setStudentName(jsonChildNode.getString("StudentName"));
                    studProfileModel.setStudentDOB(jsonChildNode.getString("StudentDOB"));
                    studProfileModel.setStudentAge(jsonChildNode.getString("StudentAge"));
                    studProfileModel.setStudentGender(jsonChildNode.getString("StudentGender"));
                    studProfileModel.setBloodGroup(jsonChildNode.getString("BloodGroup"));
                    studProfileModel.setBirthPlace(jsonChildNode.getString("BirthPlace"));
                    studProfileModel.setCaste(jsonChildNode.getString("Caste"));
                    studProfileModel.setHouse(jsonChildNode.getString("House"));
                    studProfileModel.setStudentImage(jsonChildNode.getString("StudentImage"));
                    studProfileModel.setFatherName(jsonChildNode.getString("FatherName"));
                    studProfileModel.setFatherPhone(jsonChildNode.getString("FatherPhone"));
                    studProfileModel.setFatherEmail(jsonChildNode.getString("FatherEmail"));
                    studProfileModel.setMotherName(jsonChildNode.getString("MotherName"));
                    studProfileModel.setMotherMobile(jsonChildNode.getString("MotherMobile"));
                    studProfileModel.setMotherEmail(jsonChildNode.getString("MotherEmail"));
                    studProfileModel.setAddress(jsonChildNode.getString("Address"));
                    studProfileModel.setCity(jsonChildNode.getString("City"));
                    studProfileModel.setSMSNumber(jsonChildNode.getString("SMSNumber"));
                    studProfileModel.setTransport_KM(jsonChildNode.getString("Transport_KM"));
                    studProfileModel.setTransport_PicupTime(jsonChildNode.getString("Transport_PicupTime"));
                    studProfileModel.setTransport_DropTime(jsonChildNode.getString("Transport_DropTime"));
                    studProfileModel.setGRNO(jsonChildNode.getString("GRNO"));
                    studProfileModel.setStandard(jsonChildNode.getString("Standard"));
                    studProfileModel.setStudClass(jsonChildNode.getString("Class"));
                    studProfileModel.setAddmissionDate(jsonChildNode.getString("AddmissionDate"));
                    studProfileModel.setUserName(jsonChildNode.getString("UserName"));
                    studProfileModel.setPassword(jsonChildNode.getString("Password"));
                    studProfileModel.setTeacherName(jsonChildNode.getString("ClassTeacher"));
                    studProfileModel.setTodayAttendance(jsonChildNode.getString("TodayAttendance"));
                    result.add(studProfileModel);
                }
            } else {
                //invalid login
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static ArrayList<ClassWorkModel> parseStudClassworkJson(String responseString) {
        ArrayList<ClassWorkModel> result = new ArrayList<>();

        try {
            JSONObject reader = new JSONObject(responseString);
            String data_load_basket = reader.getString("Success");
            if (data_load_basket.toString().equals("True")) {
                JSONArray jsonMainNode = reader.optJSONArray("FinalArray");
                ClassWorkModel classWorkModel = null;

                for (int i = 0; i < jsonMainNode.length(); i++) {
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                    classWorkModel = new ClassWorkModel();
                    classWorkModel.setClassWorkDate(jsonChildNode.getString("ClassWorkDate"));

                    ClassWorkModel.ClassWorkData classWorkData = null;
                    ArrayList<ClassWorkModel.ClassWorkData> classWorkDatas = new ArrayList<>();
                    JSONArray jsonMainNode1 = jsonChildNode.optJSONArray("Data");
                    for (int j = 0; j < jsonMainNode1.length(); j++) {
                        JSONObject jsonChildNode1 = jsonMainNode1.getJSONObject(j);
                        classWorkData = classWorkModel.new ClassWorkData();
                        classWorkData.setSubject(jsonChildNode1.getString("Subject"));
                        classWorkData.setClasswork(jsonChildNode1.getString("Classwork"));
                        classWorkData.setProxyStatus(jsonChildNode1.getString("ProxyStatus"));

                        classWorkDatas.add(classWorkData);
                    }
                    classWorkModel.setClassWorkDatas(classWorkDatas);
                    result.add(classWorkModel);
                }

            } else {
                //invalid login
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static ArrayList<ResultModel> parseUnitTestJson(String responseString) {
        ArrayList<ResultModel> result = new ArrayList<>();

        try {
            JSONObject reader = new JSONObject(responseString);
            String data_load_basket = reader.getString("Success");
            ResultModel resultModel = null;

            if (data_load_basket.toString().equals("True")) {


                JSONArray jsonMainNode = reader.optJSONArray("FinalArray");
                for (int a = 0; a < jsonMainNode.length(); a++) {
                    resultModel = new ResultModel();
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(a);
                    resultModel.setTestName(jsonChildNode.getString("TestName"));
                    resultModel.setTotal_Marks(jsonChildNode.getString("Total Marks"));
                    resultModel.setTotalMarksGained(jsonChildNode.getString("Total MarksGained"));
                    resultModel.setTotalPercentage(jsonChildNode.getString("Total Percentage"));

                    ResultModel.Data data = null;
                    ArrayList<ResultModel.Data> dataArrayList = new ArrayList<>();
                    JSONArray jsonChildMainNode = jsonChildNode.optJSONArray("Data");
                    for (int i = 0; i < jsonChildMainNode.length(); i++) {
                        data = resultModel.new Data();
                        JSONObject jsonChildNode1 = jsonChildMainNode.getJSONObject(i);
                        data.setSubjectName(jsonChildNode1.getString("SubjectName"));
                        data.setTestMark(jsonChildNode1.getString("TestMark"));
                        data.setMarkGained(jsonChildNode1.getString("MarkGained"));
                        data.setPercentage(jsonChildNode1.getString("Percentage"));
                        dataArrayList.add(data);
                    }
                    resultModel.setDataArrayList(dataArrayList);
                    result.add(resultModel);
                }
            } else {
                //invalid login
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static ArrayList<PaymentLedgerModel> parsePaymentLedgerJson(String responseString) {
        ArrayList<PaymentLedgerModel> result = new ArrayList<>();

        try {
            JSONObject reader = new JSONObject(responseString);
            String data_load_basket = reader.getString("Success");
            PaymentLedgerModel paymentLedgerModel = null;

            if (data_load_basket.toString().equals("True")) {


                JSONArray jsonMainNode = reader.optJSONArray("FinalArray");
                for (int a = 0; a < jsonMainNode.length(); a++) {
                    paymentLedgerModel = new PaymentLedgerModel();
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(a);
                    paymentLedgerModel.setPayDate(jsonChildNode.getString("PayDate"));
                    paymentLedgerModel.setPaid(jsonChildNode.getString("Paid"));

                    PaymentLedgerModel.Data data = null;
                    ArrayList<PaymentLedgerModel.Data> dataArrayList = new ArrayList<>();
                    JSONArray jsonChildMainNode = jsonChildNode.optJSONArray("Data");
                    for (int i = 0; i < jsonChildMainNode.length(); i++) {
                        data = paymentLedgerModel.new Data();
                        JSONObject jsonChildNode1 = jsonChildMainNode.getJSONObject(i);
                        data.setTerm(jsonChildNode1.getString("Term"));
                        data.setTermDetail(jsonChildNode1.getString("TermDetail"));
                        data.setGRNO(jsonChildNode1.getString("GRNO"));
                        data.setPayMode(jsonChildNode1.getString("PayMode"));
                        data.setPaidFee(jsonChildNode1.getString("PaidFee"));
                        data.setReceiptNo(jsonChildNode1.getString("ReceiptNo"));
                        data.setAdmissionFee(jsonChildNode1.getString("AdmissionFee"));
                        data.setCautionFee(jsonChildNode1.getString("CautionFee"));
                        data.setPreviousFees(jsonChildNode1.getString("PreviousFees"));
                        data.setTuitionFee(jsonChildNode1.getString("TuitionFee"));
                        data.setTransport(jsonChildNode1.getString("Transport"));
                        data.setImprestFee(jsonChildNode1.getString("ImprestFee"));
                        data.setLatesFee(jsonChildNode1.getString("LatesFee"));
                        data.setDiscountFee(jsonChildNode1.getString("DiscountFee"));
                        data.setPaidFee(jsonChildNode1.getString("PayPaidFees"));
                        data.setCurrentOutstandingFees(jsonChildNode1.getString("CurrentOutstandingFees"));
                        dataArrayList.add(data);
                    }
                    paymentLedgerModel.setDataArrayList(dataArrayList);
                    result.add(paymentLedgerModel);
                }
            } else {
                //invalid login
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static ArrayList<FeesModel> parseFeesDetailsJson(String responseString) {
        ArrayList<FeesModel> result = new ArrayList<>();

        try {
            JSONObject reader = new JSONObject(responseString);
            String data_load_basket = reader.getString("Success");
            FeesModel feesModel = null;

            if (data_load_basket.toString().equals("True")) {


                JSONArray jsonMainNode = reader.optJSONArray("FinalArray");
                for (int a = 0; a < jsonMainNode.length(); a++) {
                    feesModel = new FeesModel();
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(a);
                    feesModel.setTerm(jsonChildNode.getString("Term"));

                    FeesModel.Data data = null;
                    ArrayList<FeesModel.Data> dataArrayList = new ArrayList<>();
                    JSONArray jsonChildMainNode = jsonChildNode.optJSONArray("Data");
                    for (int i = 0; i < jsonChildMainNode.length(); i++) {
                        data = feesModel.new Data();

                        JSONObject jsonChildNode1 = jsonChildMainNode.getJSONObject(i);
                        data.setPreviousBalance(jsonChildNode1.getString("PreviousBalance"));
                        data.setAdmissionFees(jsonChildNode1.getString("AdmissionFees"));
                        data.setCautionFees(jsonChildNode1.getString("CautionFees"));
                        data.setTutionFees(jsonChildNode1.getString("TutionFees"));
                        data.setTransportFees(jsonChildNode1.getString("TransportFees"));
                        data.setImprest(jsonChildNode1.getString("Imprest"));
                        data.setLateFees(jsonChildNode1.getString("LateFees"));
                        data.setDiscount(jsonChildNode1.getString("Discount"));
                        data.setTotalFees(jsonChildNode1.getString("Balance"));
                        data.setPaidFees(jsonChildNode1.getString("PaidFees"));
                        data.setTotalPayableFees(jsonChildNode1.getString("TotalPayableFees"));
                        data.setURL(jsonChildNode1.getString("URL"));
                        data.setButtonVisiblity(jsonChildNode1.getString("ButtonVisiblity"));
                        dataArrayList.add(data);
                    }
                    feesModel.setDataArrayList(dataArrayList);
                    result.add(feesModel);
                }
            } else {
                //invalid login
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("megha", result.get(0).getDataArrayList().get(0).getImprest());
        return result;
    }

    public static ArrayList<HomeWorkModel> parseStudHomeworkJson(String responseString) {
        ArrayList<HomeWorkModel> result = new ArrayList<>();

        try {
            JSONObject reader = new JSONObject(responseString);
            String data_load_basket = reader.getString("Success");
            if (data_load_basket.toString().equals("True")) {
                JSONArray jsonMainNode = reader.optJSONArray("FinalArray");
                HomeWorkModel homeWorkModel = null;

                for (int i = 0; i < jsonMainNode.length(); i++) {
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                    homeWorkModel = new HomeWorkModel();
                    homeWorkModel.setHomeWorkDate(jsonChildNode.getString("HomeWorkDate"));

                    HomeWorkModel.HomeWorkData homeWorkData = null;
                    ArrayList<HomeWorkModel.HomeWorkData> homeWorkDatas = new ArrayList<>();
                    JSONArray jsonMainNode1 = jsonChildNode.optJSONArray("Data");
                    for (int j = 0; j < jsonMainNode1.length(); j++) {
                        JSONObject jsonChildNode1 = jsonMainNode1.getJSONObject(j);
                        homeWorkData = homeWorkModel.new HomeWorkData();
                        homeWorkData.setSubject(jsonChildNode1.getString("Subject"));
                        homeWorkData.setHomework(jsonChildNode1.getString("HomeWork"));
                        homeWorkData.setChapterName(jsonChildNode1.getString("ChapterName"));
                        homeWorkData.setObjective(jsonChildNode1.getString("Objective"));
                        homeWorkData.setAssessmentQue(jsonChildNode1.getString("AssessmentQue"));
                        homeWorkData.setFont(jsonChildNode1.getString("Font"));
                        homeWorkDatas.add(homeWorkData);
                    }
                    homeWorkModel.setHomeWorkDatas(homeWorkDatas);
                    result.add(homeWorkModel);
                }

            } else {
                //invalid login
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static ArrayList<FeesModel> parseFeesJson(String responseString) {
        ArrayList<FeesModel> result = new ArrayList<>();

        try {
            JSONObject reader = new JSONObject(responseString);
            String data_load_basket = reader.getString("Success");
            FeesModel feesModel = null;

            if (data_load_basket.toString().equals("True")) {
                feesModel = new FeesModel();
                feesModel.setTermTotal(reader.getString("TermTotal"));
                feesModel.setTermPaid(reader.getString("TermPaid"));
                feesModel.setTermDuePay(reader.getString("TermDuePay"));
                feesModel.setTermDiscount(reader.getString("TermDiscount"));
                feesModel.setTermLateFee(reader.getString("TermLateFee"));

                result.add(feesModel);
            } else {
                //invalid login
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    public static ArrayList<AttendanceModel> parseAttendanceJson(String responseString) {
        ArrayList<AttendanceModel> result = new ArrayList<>();

        try {
            JSONObject reader = new JSONObject(responseString);
            String data_load_basket = reader.getString("Success");
            AttendanceModel attendanceModel = new AttendanceModel();
            attendanceModel.setTotalAbsent(reader.getString("TotalAbsent"));
            attendanceModel.setTotalPresent(reader.getString("TotalPresent"));

            if (data_load_basket.toString().equals("True")) {
                JSONArray jsonMainNode = reader.optJSONArray("FinalArray");
                AttendanceModel.Attendance attendance = null;
                ArrayList<AttendanceModel.Attendance> attendances = new ArrayList<>();
                for (int i = 0; i < jsonMainNode.length(); i++) {
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                    attendance = attendanceModel.new Attendance();
                    attendance.setAttendanceDate(jsonChildNode.getString("AttendanceDate"));
                    attendance.setComment(jsonChildNode.getString("Comment"));
                    attendance.setAttendenceStatus(jsonChildNode.getString("AttendenceStatus"));

                    attendances.add(attendance);
                }
                attendanceModel.setEventsList(attendances);
                result.add(attendanceModel);
            } else {
                //invalid login
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static ArrayList<TimetableModel> parseTimeTableJson(String responseString) {
        ArrayList<TimetableModel> result = new ArrayList<>();

        try {
            JSONObject reader = new JSONObject(responseString);
            String data_load_basket = reader.getString("Success");
            TimetableModel timetableModel = new TimetableModel();

            if (data_load_basket.toString().equals("True")) {
                JSONArray jsonMainNode = reader.optJSONArray("FinalArray");
                TimetableModel.Timetable timetable = null;
                ArrayList<TimetableModel.Timetable> timetables = new ArrayList<>();
                for (int i = 0; i < jsonMainNode.length(); i++) {
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                    timetable = timetableModel.new Timetable();
                    timetable.setDay(jsonChildNode.getString("Day"));

                    JSONArray jsonMainNode1 = jsonChildNode.optJSONArray("Data");
                    TimetableModel.Timetable.TimetableData timetableData = null;
                    ArrayList<TimetableModel.Timetable.TimetableData> timetablesData = new ArrayList<>();
                    for (int j = 0; j < jsonMainNode1.length(); j++) {
                        JSONObject jsonChildNode1 = jsonMainNode1.getJSONObject(j);
                        timetableData = timetable.new TimetableData();
                        timetableData.setLecture(jsonChildNode1.getString("Lecture"));
                        timetableData.setSubject(jsonChildNode1.getString("Subject"));
                        timetableData.setTeacher(jsonChildNode1.getString("Teacher"));

                        timetablesData.add(timetableData);
                    }
                    timetable.setTimetableDatas(timetablesData);
                    timetables.add(timetable);
                }
                timetableModel.setTimetables(timetables);
                result.add(timetableModel);
            } else {
                //invalid login
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static ArrayList<TermModel> getTermData(String responseString) {
        ArrayList<TermModel> result = new ArrayList<>();

        try {
            JSONObject reader = new JSONObject(responseString);
            String data_load_basket = reader.getString("Success");
            if (data_load_basket.toString().equals("True")) {
                JSONArray jsonMainNode = reader.optJSONArray("FinalArray");

                TermModel termModel = null;
                for (int i = 0; i < jsonMainNode.length(); i++) {
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                    termModel = new TermModel();
                    termModel.setTermId(jsonChildNode.getString("TermId"));
                    termModel.setTerm(jsonChildNode.getString("Term"));

                    result.add(termModel);
                }
            } else {
                //invalid login
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static ArrayList<PrincipalModel> getPrincipalMessageJson(String responseString) {
        ArrayList<PrincipalModel> result = new ArrayList<>();

        try {
            JSONObject reader = new JSONObject(responseString);
            String data_load_basket = reader.getString("Success");
            if (data_load_basket.toString().equals("True")) {
                JSONArray jsonMainNode = reader.optJSONArray("FinalArray");

                PrincipalModel principalModel = null;
                for (int i = 0; i < jsonMainNode.length(); i++) {
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                    principalModel = new PrincipalModel();
                    principalModel.setImage(jsonChildNode.getString("Image"));
                    principalModel.setName(jsonChildNode.getString("Name"));
                    principalModel.setType(jsonChildNode.getString("Type"));
                    principalModel.setDiscription(jsonChildNode.getString("Discription"));
                    principalModel.setOrderNo(jsonChildNode.getString("OrderNo"));

                    result.add(principalModel);
                }
            } else {
                //invalid login
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static ArrayList<ImprestDataModel> getImprestData(String responseString) {
        ArrayList<ImprestDataModel> result = new ArrayList<>();

        try {
            JSONObject reader = new JSONObject(responseString);
            String data_load_basket = reader.getString("Success");
            String MyBalance = reader.getString("MyBalance");
            String OpeningBalanceTop = reader.getString("OpeningBalance");
            ImprestDataModel imprestDataModel = null;
            if (data_load_basket.toString().equals("True")) {
                JSONArray jsonMainNode = reader.optJSONArray("FinalArray");
                if (jsonMainNode.length() > 0) {
                    for (int i = 0; i < jsonMainNode.length(); i++) {
                        JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                        imprestDataModel = new ImprestDataModel();
                        imprestDataModel.setMyBalance(MyBalance);
                        imprestDataModel.setOpeningBalanceTop(OpeningBalanceTop);
                        imprestDataModel.setMessage(jsonChildNode.getString("Message"));
                        imprestDataModel.setDate(jsonChildNode.getString("Date"));
                        imprestDataModel.setOpeningBalance(jsonChildNode.getString("OpeningBalance"));
                        imprestDataModel.setDeductAmount(jsonChildNode.getString("DeductAmount"));
                        imprestDataModel.setBalance(jsonChildNode.getString("Balance"));

                        result.add(imprestDataModel);
                    }
                } else {
                    imprestDataModel = new ImprestDataModel();
                    imprestDataModel.setMyBalance(MyBalance);
                    imprestDataModel.setOpeningBalanceTop(OpeningBalanceTop);
                    result.add(imprestDataModel);
                }
            } else {
                //invalid login
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static ArrayList<EventModel> parseEventJson(String responseString) {
        ArrayList<EventModel> result = new ArrayList<>();

        try {
            JSONObject reader = new JSONObject(responseString);
            String data_load_basket = reader.getString("Success");


            if (data_load_basket.toString().equals("True")) {
                JSONArray jsonMainNode = reader.optJSONArray("FinalArray");
                EventModel eventModel = null;
                for (int i = 0; i < jsonMainNode.length(); i++) {
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                    eventModel = new EventModel();
                    eventModel.setCreateDate(jsonChildNode.getString("CreateDate"));
                    eventModel.setDescription(jsonChildNode.getString("Description"));

                    JSONArray jsonMainNode1 = jsonChildNode.optJSONArray("EventImage");
                    EventModel.EventImage eventImage = null;
                    ArrayList<EventModel.EventImage> eventImages = new ArrayList<>();
                    for (int j = 0; j < jsonMainNode1.length(); j++) {
                        JSONObject jsonChildNode1 = jsonMainNode1.getJSONObject(j);
                        eventImage = eventModel.new EventImage();
                        eventImage.setImagePath(jsonChildNode1.getString("ImagePath"));

                        eventImages.add(eventImage);
                    }
                    eventModel.setEventImages(eventImages);
                    result.add(eventModel);
                }

            } else {
                //invalid login
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static ArrayList<AnnouncementModel> parseAnnouncementJson(String responseString) {
        ArrayList<AnnouncementModel> result = new ArrayList<>();

        try {
            JSONObject reader = new JSONObject(responseString);
            String data_load_basket = reader.getString("Success");
            if (data_load_basket.toString().equals("True")) {
                JSONArray jsonMainNode = reader.optJSONArray("FinalArray");

                AnnouncementModel announcementModel = null;
                for (int i = 0; i < jsonMainNode.length(); i++) {
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                    announcementModel = new AnnouncementModel();
                    announcementModel.setCreateDate(jsonChildNode.getString("CreateDate"));
                    announcementModel.setAnnoucementDescription(jsonChildNode.getString("AnnoucementDescription"));
                    announcementModel.setAnnoucementPDF(jsonChildNode.getString("AnnoucementPDF"));

                    result.add(announcementModel);
                }
            } else {
                //invalid login
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static ArrayList<CanteenModel> parseCanteenJson(String responseString) {
        ArrayList<CanteenModel> result = new ArrayList<>();
        CanteenModel canteenModel = null;
        try {
            JSONObject reader = new JSONObject(responseString);
            String data_load_basket = reader.getString("Success");
            if (data_load_basket.toString().equals("True")) {

                canteenModel = new CanteenModel();
                canteenModel.setFromDate(reader.getString("FromDate"));
                canteenModel.setToDate(reader.getString("ToDate"));

                JSONArray jsonMainNode = reader.optJSONArray("FinalArray");
                ArrayList<HashMap<String, String>> hashMapArrayList = new ArrayList<>();
                HashMap<String, String> hashMap = null;
                for (int i = 0; i < jsonMainNode.length(); i++) {
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                    hashMap = new HashMap<>();
                    hashMap.put("MenuDay", jsonChildNode.getString("MenuDay"));
                    hashMap.put("MenuDate", jsonChildNode.getString("MenuDate"));
                    hashMap.put("Breakfast", jsonChildNode.getString("Breakfast"));
                    hashMap.put("Lunch", jsonChildNode.getString("Lunch"));
                    hashMap.put("FlvrMilk", jsonChildNode.getString("FlvrMilk"));

                    hashMapArrayList.add(hashMap);
                }
                canteenModel.setCanteenData(hashMapArrayList);
                result.add(canteenModel);
            } else {
                //invalid login
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static ArrayList<CircularModel> parseCircularJson(String responseString) {
        ArrayList<CircularModel> result = new ArrayList<>();

        try {
            JSONObject reader = new JSONObject(responseString);
            String data_load_basket = reader.getString("Success");
            if (data_load_basket.toString().equals("True")) {
                JSONArray jsonMainNode = reader.optJSONArray("FinalArray");

                CircularModel circularModel = null;
                for (int i = 0; i < jsonMainNode.length(); i++) {
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                    circularModel = new CircularModel();
                    circularModel.setCreateDate(jsonChildNode.getString("CreateDate"));
                    circularModel.setCircularHeading(jsonChildNode.getString("CircularHeading"));
                    circularModel.setCircularFile(jsonChildNode.getString("CircularFile"));

                    result.add(circularModel);
                }
            } else {
                //invalid login
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static ArrayList<UnitTestModel> parseTestDetailJson(String responseString) {
        ArrayList<UnitTestModel> result = new ArrayList<>();

        try {
            JSONObject reader = new JSONObject(responseString);
            String data_load_basket = reader.getString("Success");
            UnitTestModel unitTestModel = null;

            if (data_load_basket.toString().equals("True")) {
                JSONArray jsonMainNode = reader.optJSONArray("FinalArray");
                for (int a = 0; a < jsonMainNode.length(); a++) {
                    unitTestModel = new UnitTestModel();
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(a);
                    unitTestModel.setTestDate(jsonChildNode.getString("TestDate"));

                    UnitTestModel.Data data = null;
                    ArrayList<UnitTestModel.Data> dataArrayList = new ArrayList<>();
                    JSONArray jsonChildMainNode = jsonChildNode.optJSONArray("Data");
                    for (int i = 0; i < jsonChildMainNode.length(); i++) {
                        data = unitTestModel.new Data();
                        JSONObject jsonChildNode1 = jsonChildMainNode.getJSONObject(i);
                        data.setSubject(jsonChildNode1.getString("Subject"));
                        data.setDetail(jsonChildNode1.getString("Detail"));
                        dataArrayList.add(data);
                    }

                    unitTestModel.setDataArrayList(dataArrayList);
                    result.add(unitTestModel);
                }
            } else {
                //invalid login
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    public static ArrayList<ReportCardModel> parseReportCardJson(String responseString) {
        ArrayList<ReportCardModel> result = new ArrayList<>();

        try {
            JSONObject reader = new JSONObject(responseString);
            String data_load_basket = reader.getString("Success");
            ReportCardModel reportCardModel = null;

            if (data_load_basket.toString().equals("True")) {
                JSONArray jsonMainNode = reader.optJSONArray("FinalArray");
                for (int a = 0; a < jsonMainNode.length(); a++) {
                    reportCardModel = new ReportCardModel();
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(a);
                    reportCardModel.setURL(jsonChildNode.getString("URL"));
                    result.add(reportCardModel);
                }
            } else {
                //invalid login
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}