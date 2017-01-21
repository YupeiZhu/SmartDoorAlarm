package hackthon.smartdooralarm;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private static ArrayList<Item> ItemList;
    private ArrayList<ItemViewHolder> cards;
    private final Activity context;

    private static final int PLANNED_COLOR = Color.BLUE;
    private static final int CURRENT_COLOR = Color.GREEN;
    private static final int PAST_COLOR = Color.RED;

    // Constructor
    public ItemAdapter(Activity context, ArrayList<Item> contactList) {

        ItemList = contactList;
        this.context = context;
        this.cards = new ArrayList<>();

    }

    @Override
    public int getItemCount() {

        return ItemList.size();

    }

    @Override
    public void onBindViewHolder( final ItemViewHolder contactViewHolder, int i ) {

        Item ci = ItemList.get( i );
        contactViewHolder.setItem( ci );
      
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i ) {
        View itemView = LayoutInflater.
                from( viewGroup.getContext() ).
                inflate( R.layout.item_layout, viewGroup, false );

        return new ItemViewHolder( itemView, ItemList.get(i) );
    }

    public void removeOnSwipe(int position) {
        ItemList.remove(position);
        notifyItemRemoved(position);
    }

    // Inner class ViewHolder
    public static class ItemViewHolder extends RecyclerView.ViewHolder {


        // Item information. Expected to be enriched in the future
        private TextView vUid;
        private ImageView vImg;
        private TextView vDes;

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


        }


    }

}