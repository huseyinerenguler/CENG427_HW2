package com.huseyinerenguler.ceng427_hw2.Manager;


import android.os.AsyncTask;

import com.huseyinerenguler.ceng427_hw2.Objects.ObjectAnnouncement;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;


public class ManagerFetchAnnouncement extends AsyncTask<Integer, Void, ArrayList<ObjectAnnouncement>> {

    public interface AsyncResponse {
        void processFinish(ArrayList<ObjectAnnouncement> result);
    }

    private AsyncResponse delegate;

    public void fetchAnnouncements(AsyncResponse delegate) {
        this.delegate = delegate;
        this.execute(0);
    }

    public void fetchNews(AsyncResponse delegate) {
        this.delegate = delegate;
        this.execute(1);
    }

    @Override
    protected ArrayList<ObjectAnnouncement> doInBackground(Integer... integers) {

        ArrayList<ObjectAnnouncement> announcementArrayList = new ArrayList<>();

        try {
            Document document = Jsoup.connect("https://aybu.edu.tr/muhendislik/bilgisayar/").get();
            Elements elements;

            // Fetch Announcements
            if (integers[0] == 0)
                elements = document.select("div[class=contentAnnouncements]").select("div[class=caContent]").select("div[class=cncItem]");

                // Fetch News
            else
                elements = document.select("div[class=contentNews]").select("div[class=cnContent]").select("div[class=cncItem]");


            for (int i = 0; i < elements.size(); i++) {

                String title = elements.get(i).select("a").text();
                String href = elements.get(i).select("a").attr("href");

                announcementArrayList.add(new ObjectAnnouncement(title, href));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return announcementArrayList;
    }

    @Override
    protected void onPostExecute(ArrayList<ObjectAnnouncement> result) {
        delegate.processFinish(result);
    }

}