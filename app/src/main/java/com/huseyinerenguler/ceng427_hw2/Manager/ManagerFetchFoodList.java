package com.huseyinerenguler.ceng427_hw2.Manager;


import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;


public class ManagerFetchFoodList extends AsyncTask<Void, Void, ArrayList<String>> {

    public interface AsyncResponse {
        void processFinish(ArrayList<String> result);
    }

    private AsyncResponse delegate;

    public void fetchFoodList(AsyncResponse delegate) {
        this.delegate = delegate;
        this.execute();
    }

    @Override
    protected ArrayList<String> doInBackground(Void... aVoid) {

        ArrayList<String> foodList = new ArrayList<>();

        try {
            Document document = Jsoup.connect("https://aybu.edu.tr/sks/").get();
            Elements elements = document.select("div[id=main_wrapper]").select("table").select("td").select("table").select("tr");

            for (int i = 1; i < elements.size(); i++)
                foodList.add(elements.get(i).text());


        } catch (Exception e) {
            e.printStackTrace();
        }

        return foodList;
    }

    @Override
    protected void onPostExecute(ArrayList<String> result) {
        delegate.processFinish(result);
    }

}