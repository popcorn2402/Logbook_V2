package com.ar.logbookv2.view.essaifragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.ar.logbookv2.R;

public class DailyLogInfoFragment extends Fragment {

    public DailyLogInfoFragment() {
        // Required empty public constructor
    }

    public static class ScreenSlidePageFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return (ViewGroup) inflater.inflate(
                    R.layout.content_holder, container, false);
        }
    }
}
