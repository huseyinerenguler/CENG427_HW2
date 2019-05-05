package com.huseyinerenguler.ceng427_hw2.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.huseyinerenguler.ceng427_hw2.Manager.ManagerFetchAnnouncement;
import com.huseyinerenguler.ceng427_hw2.Objects.ObjectAnnouncement;
import com.huseyinerenguler.ceng427_hw2.R;

import java.util.ArrayList;

public class FragmentAnnouncements extends Fragment {

    private View inf;

    private ListView listView_announcements;
    private ArrayAdapter<ObjectAnnouncement> arrayAdapter;
    private ArrayList<ObjectAnnouncement> announcementsList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        inf = inflater.inflate(R.layout.fragment_announcements, container, false);

        initListView();
        initAdapter();
        fetchAnnouncements();

        return inf;
    }

    private void initListView() {

        listView_announcements = inf.findViewById(R.id.listView_announcements);
        listView_announcements.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String URL = announcementsList.get(position).getHref();
                openBrowser(URL);
            }
        });
    }


    private void initAdapter() {

        arrayAdapter = new ArrayAdapter<ObjectAnnouncement>(getContext(), android.R.layout.simple_list_item_1, announcementsList) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                convertView = inf.inflate(getContext(), R.layout.custom_announcement_view, null);
                ((TextView) convertView.findViewById(R.id.textView_title)).setText(announcementsList.get(position).getTitle());
                return convertView;
            }
        };

        listView_announcements.setAdapter(arrayAdapter);
    }


    private void fetchAnnouncements() {

        new ManagerFetchAnnouncement().fetchAnnouncements(new ManagerFetchAnnouncement.AsyncResponse() {
            @Override
            public void processFinish(ArrayList<ObjectAnnouncement> result) {

                announcementsList.addAll(result);
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }


    private void openBrowser(String URL) {
        String baseURL = "https://aybu.edu.tr/muhendislik/bilgisayar/";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(baseURL + URL));
        startActivity(intent);
    }

}