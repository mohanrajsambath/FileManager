package com.droids.tamada.filemanager.fragments;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.droids.tamada.filemanager.adapter.AudiosListAdapter;
import com.droids.tamada.filemanager.adapter.InternalStorageListAdapter;
import com.droids.tamada.filemanager.app.AppController;
import com.droids.tamada.filemanager.helper.DividerItemDecoration;
import com.droids.tamada.filemanager.model.InternalStorageFilesModel;
import com.droids.tamada.filemanager.model.MediaFileListModel;
import com.example.satish.filemanager.R;

import java.io.File;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InternalStorageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InternalStorageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InternalStorageFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private LinearLayout noMediaLayout;
    private OnFragmentInteractionListener mListener;
    private ArrayList<InternalStorageFilesModel> mediaFileListModels;
    private InternalStorageListAdapter internalStorageListAdapter;
    private String rootPath;

    public InternalStorageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InternalStorageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InternalStorageFragment newInstance(String param1, String param2) {
        InternalStorageFragment fragment = new InternalStorageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_internal_storage, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        noMediaLayout = (LinearLayout) view.findViewById(R.id.noMediaLayout);
        mediaFileListModels = new ArrayList<>();
        rootPath = Environment.getExternalStorageDirectory()
                .getAbsolutePath();
        internalStorageListAdapter = new InternalStorageListAdapter(mediaFileListModels);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(AppController.getInstance().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(AppController.getInstance().getApplicationContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(internalStorageListAdapter);
        getFilesList(rootPath);
        recyclerView.addOnItemTouchListener(new AudiosListFragment.RecyclerTouchListener(AppController.getInstance().getApplicationContext(), recyclerView, new AudiosListFragment.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        return view;
    }

    private void getFilesList(String rootPath) {
        File f = new File(rootPath);
        File[] files = f.listFiles();
        if (!rootPath.equals(rootPath) & !rootPath.equals("/sdcard")) {
            InternalStorageFilesModel model = new InternalStorageFilesModel("/", rootPath, true);
            mediaFileListModels.add(model);
        }
        for (File file : files) {
            InternalStorageFilesModel model = new InternalStorageFilesModel(file.getName(), file.getPath(), false);
            mediaFileListModels.add(model);
        }
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
