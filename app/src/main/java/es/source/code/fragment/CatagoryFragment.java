package es.source.code.fragment;

/**
 * Created by apple on 2017/10/14.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.source.code.activity.R;

public class CatagoryFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_catagory, container, false);
        return rootView;
    }
}
