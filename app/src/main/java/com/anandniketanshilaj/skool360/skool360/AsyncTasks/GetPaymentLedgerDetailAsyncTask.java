package com.anandniketanshilaj.skool360.skool360.AsyncTasks;

import android.os.AsyncTask;

import com.anandniketanshilaj.skool360.skool360.Models.PaymentResponse.GetPaymentLedgerResponse;
import com.anandniketanshilaj.skool360.skool360.Models.PaymentResponse.PaymentMainResponse;
import com.anandniketanshilaj.skool360.skool360.Utility.AppConfiguration;
import com.anandniketanshilaj.skool360.skool360.WebServicesCall.WebServicesCall;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by admsandroid on 1/5/2018.
 */

public class GetPaymentLedgerDetailAsyncTask  extends AsyncTask<Void, Void, GetPaymentLedgerResponse> {
    HashMap<String, String> param = new HashMap<String, String>();

    public GetPaymentLedgerDetailAsyncTask(HashMap<String, String> param) {
        this.param = param;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected GetPaymentLedgerResponse doInBackground(Void... params) {
        String responseString = null;
        GetPaymentLedgerResponse paymentLedgerResponse = null;
        try {
            responseString = WebServicesCall.RunScript(AppConfiguration.getUrl(AppConfiguration.GetPaymentLedger), param);
            Gson gson = new Gson();
            paymentLedgerResponse = gson.fromJson(responseString, GetPaymentLedgerResponse.class);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return paymentLedgerResponse;
    }

    @Override
    protected void onPostExecute(GetPaymentLedgerResponse result) {
        super.onPostExecute(result);
    }
}



