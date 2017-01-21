package hackthon.smartdooralarm;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.Firebase;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private ArrayList<Item> ItemList;
    private Context mContext;
    private Firebase fb;

    // Constructor
    public ItemAdapter(Context ct, ArrayList<Item> list, Firebase fb) {

        this.ItemList = list;
        this.mContext = ct;
        this.fb = fb;
    }

    public String getItemUid(ItemViewHolder viewHolder){ return viewHolder.uid; }

    public ArrayList<Item> getItemList(){ return ItemList; }

    public Firebase getFirebaseInstance(){ return fb; }

    @Override
    public int getItemCount() {
        return ItemList.size();
    }

    @Override
    public void onBindViewHolder( final ItemViewHolder contactViewHolder, int position ) {

        Item ci = ItemList.get( position );
        contactViewHolder.setItem( ci );
      
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i ) {
        View itemView = LayoutInflater.
                from( viewGroup.getContext() ).
                inflate( R.layout.item_layout, viewGroup, false );

        return new ItemViewHolder( itemView, ItemList.get(i) );
    }



    // Inner class ViewHolder
    public static class ItemViewHolder extends RecyclerView.ViewHolder {


        // Item information. Expected to be enriched in the future
        private TextView vUid;
        private ImageView vImg;
        private TextView vDes;
        public String uid;

        ItemViewHolder( View v, Item data) {

            super(v);
            vUid =  (TextView) v.findViewById( R.id.uid );
            vImg = (ImageView) v.findViewById( R.id.img );
            vDes = (TextView) v.findViewById( R.id.des );
            setItem(data);
        }

        public void setItem(Item data){
            this.vUid.setText(data.getUid());
            this.vImg.setImageBitmap(data.getPic());
            this.vDes.setText(data.getDes());
            uid = data.getUid();
        }

    }

}