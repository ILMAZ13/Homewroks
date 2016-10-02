package ru.itis.photogallery;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by ilmaz on 02.10.16.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> linksList;
    private Context mContext;

    public MyAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if(linksList != null) {
            String link = linksList.get(position);
            ((MyViewHolder) holder).clearImage();
            if (link != null) {
                ((MyViewHolder) holder).bind(link);
                Glide.with(mContext).load(link).fitCenter().into(((MyViewHolder) holder).imageView);
            } else {
                Glide.clear(((MyViewHolder) holder).imageView);
                ((MyViewHolder) holder).imageView.setImageDrawable(null);
            }
        }
    }

    @Override
    public int getItemCount() {
        return linksList != null ? linksList.size() : 0;
    }

    public void setLinksList(List<String> linksList){
        this.linksList = linksList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img);

        }
        public void bind(final String link){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    GlideBitmapDrawable gbd = (GlideBitmapDrawable) imageView.getDrawable();
//                    if(gbd != null){
//                        Bitmap bitmap = gbd.getBitmap();
//                        if(bitmap != null) {
                    Intent intent = new Intent(mContext, ImageActivity.class);
                    intent.putExtra("image", link);
//                            intent.putExtra("image", bitmap);
                    mContext.startActivity(intent);
//                        }
//                    }
                }
            });
        }

        public void clearImage(){
            imageView.setImageResource(0);
            imageView.setImageDrawable(null);
            imageView.setImageURI(null);
        }

    }


}
