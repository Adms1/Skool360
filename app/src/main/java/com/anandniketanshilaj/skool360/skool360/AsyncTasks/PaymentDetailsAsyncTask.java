package com.anandniketanshilaj.skool360.skool360.AsyncTasks;

import android.os.AsyncTask;

import com.anandniketanshilaj.skool360.skool360.Models.MainPtmSentMessageResponse;
import com.anandniketanshilaj.skool360.skool360.Models.PaymentResponse.PaymentMainResponse;
import com.anandniketanshilaj.skool360.skool360.Utility.AppConfiguration;
import com.anandniketanshilaj.skool360.skool360.WebServicesCall.WebServicesCall;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by admsandroid on 1/5/2018.
 */

public class PaymentDetailsAsyncTask  extends AsyncTask<Void, Void, PaymentMainResponse> {
    HashMap<String, String> param = new HashMap<String, String>();

    public PaymentDetailsAsyncTask(HashMap<String, String> param) {
        this.param = param;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected PaymentMainResponse doInBackground(Void... params) {
        String responseString = null;
        PaymentMainResponse paymentMainResponse = null;
        try {
            responseString = WebServicesCall.RunScript(AppConfiguration.getUrl(AppConfiguration.GetFeesStatus), param);
            Gson gson = new Gson();
            paymentMainResponse = gson.fromJson(responseString, PaymentMainResponse.class);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return paymentMainResponse;
    }

    @Override
    protected void onPostExecute(PaymentMainResponse result) {
        super.onPostExecute(result);
    }
}



