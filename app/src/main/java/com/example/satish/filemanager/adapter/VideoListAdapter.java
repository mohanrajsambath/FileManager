package com.example.satish.filemanager.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.satish.filemanager.R;
import com.example.satish.filemanager.activity.VideosListActivity;
import com.example.satish.filemanager.model.MediaFileListModel;

import java.util.ArrayList;

/**
 * Created by Satish on 29-12-2015.
 */
public class VideoListAdapter  extends BaseAdapter {
    private ArrayList<MediaFileListModel> mediaFileListModelsArray;
    private Activity activity;
    private LayoutInflater layoutInflater;

    public VideoListAdapter(VideosListActivity audiosListActivity, ArrayList<MediaFileListModel> mediaFileListModelsArray) {
        this.activity = audiosListActivity;
        this.mediaFileListModelsArray = mediaFileListModelsArray;
    }

    @Override
    public int getCount() {
        return mediaFileListModelsArray.size();
    }

    @Override
    public Object getItem(int position) {
        return mediaFileListModelsArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null)
            layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            view = layoutInflater.inflate(R.layout.audio_list_item_view, null);
        TextView lblFileName = (TextView) view.findViewById(R.id.file_name);
        ImageView imgItemIcon = (ImageView) view.findViewById(R.id.icon);
        MediaFileListModel mediaFileListModel = mediaFileListModelsArray.get(position);
        lblFileName.setText(mediaFileListModel.getAudio_name());
        Bitmap bMap = ThumbnailUtils.createVideoThumbnail(mediaFileListModel.getAudio_file_path(), MediaStore.Video.Thumbnails.MICRO_KIND);
        imgItemIcon.setImageBitmap(bMap);
        return view;
    }
}
