package com.droids.tamada.filemanager.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.droids.tamada.filemanager.model.InternalStorageFilesModel;
import com.droids.tamada.filemanager.model.MediaFileListModel;
import com.example.satish.filemanager.R;

import java.util.List;

/**
 * Created by satish on 17/10/16.
 */

public class InternalStorageListAdapter extends RecyclerView.Adapter<InternalStorageListAdapter.MyViewHolder> {
    private List<InternalStorageFilesModel> mediaFileListModels;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView lblFileName;
        public ImageView imgItemIcon;

        public MyViewHolder(View view) {
            super(view);
            lblFileName = (TextView) view.findViewById(R.id.file_name);
            imgItemIcon = (ImageView) view.findViewById(R.id.icon);
        }
    }

    public InternalStorageListAdapter(List<InternalStorageFilesModel> mediaFileListModels) {
        this.mediaFileListModels = mediaFileListModels;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.media_list_item_view, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        InternalStorageFilesModel mediaFileListModel = mediaFileListModels.get(position);
        holder.lblFileName.setText(mediaFileListModel.getFileName());
        holder.imgItemIcon.setImageResource(R.drawable.ic_folder);
    }

    @Override
    public int getItemCount() {
        return mediaFileListModels.size();
    }
}