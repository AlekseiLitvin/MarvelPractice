package by.litvin.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import by.litvin.R;
import by.litvin.model.RelatedItem;
import by.litvin.model.Image;

//TODO refactor for series?
public class RelatedItemRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //TODO change name and type
    private List<RelatedItem> relatedItems = new ArrayList<>();
    private Context context;

    public RelatedItemRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void addRelatedItems(List<RelatedItem> comics) {
        this.relatedItems.addAll(comics);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.related_entities_recycler_item, viewGroup, false);
        return new RelatedItemViewHolder(frameLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof RelatedItemViewHolder) {
            RelatedItemViewHolder relatedItemViewHolder = (RelatedItemViewHolder) viewHolder;
            TextView relatedItemText = relatedItemViewHolder.frameLayout.findViewById(R.id.related_item_text);
            ImageView relatedItemImage = relatedItemViewHolder.frameLayout.findViewById(R.id.related_item_image);

            RelatedItem relatedItem = relatedItems.get(position);
            relatedItemText.setText(relatedItem.getTitle());

            Image thumbnail = relatedItem.getThumbnail();
            String imageUrl = String.format("%s.%s", thumbnail.getPath(), thumbnail.getExtension());
            Glide.with(context)
                    .load(imageUrl)
                    .apply(new RequestOptions().fitCenter())
                    .into(relatedItemImage);
        }
    }

    @Override
    public int getItemCount() {
        return relatedItems.size();
    }

    private class RelatedItemViewHolder extends RecyclerView.ViewHolder {
        private FrameLayout frameLayout;

        public RelatedItemViewHolder(@NonNull View itemView) {
            super(itemView);
            frameLayout = itemView.findViewById(R.id.related_entity_recycler_item);
        }
    }
}
