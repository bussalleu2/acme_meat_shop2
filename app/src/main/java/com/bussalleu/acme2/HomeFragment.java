package com.bussalleu.acme2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements Callback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


            String url = getString(R.string.baseURLFlickr)+"method=flickr.people.getPublicPhotos&api_key="+getString(R.string.apiKeyFlickr)+"&per_page=4&user_id="+getString(R.string.userFlickr)+"&format=json&nojsoncallback=1";
            new ACMEAsyncTask(getActivity(), this).execute(url);

     if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResult(String result) {
        ArrayList arrayPhotos = new ArrayList();
        try {
            JSONObject jresult = new JSONObject(result);
            JSONObject jObject = jresult.getJSONObject("photos");
            JSONArray jArray = jObject.getJSONArray("photo");

            for (int i=0; i < jArray.length(); i++) {

                JSONObject oneObject = jArray.getJSONObject(i);
                // Pulling items from the array
                String id = oneObject.getString("id");
                String userID = oneObject.getString("owner");
                String secretKey = oneObject.getString("secret");
                String server = oneObject.getString("server");
                String farm = oneObject.getString("farm");
                String title = oneObject.getString("title");
                String url = "https://farm" + farm + ".staticflickr.com/" + server + "/" + id + "_" + secretKey + ".jpg";
                Photo photo = new Photo(id, userID, secretKey, server, farm, url, title);
                arrayPhotos.add(photo);
                URL newurl = null;
            }



                } catch (JSONException e) {
                    e.printStackTrace();
                }



        for(Object obj : arrayPhotos)
        {
            Photo photo = (Photo)obj;
            if (photo.getTitle().equals("meat-01b"))
            {
                new BitmapAsynkTask((ImageView)getActivity().findViewById(R.id.img1))
                        .execute(photo.getUrl());
            }else if (photo.getTitle().equals("7644"))
            {
                new BitmapAsynkTask((ImageView)getActivity().findViewById(R.id.img2))
                        .execute(photo.getUrl());
            }else if (photo.getTitle().equals("restaurant-hotel-barcelo-santo-domingo-237-10481"))
            {
                new BitmapAsynkTask((ImageView)getActivity().findViewById(R.id.img3))
                        .execute(photo.getUrl());
            }else if (photo.getTitle().equals("cache_10484576"))
            {
                new BitmapAsynkTask((ImageView)getActivity().findViewById(R.id.img4))
                        .execute(photo.getUrl());
            }
         }

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
