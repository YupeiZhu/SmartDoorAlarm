package hackthon.smartdooralarm;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;


public class ItemTouchHelperCallback extends ItemTouchHelper.SimpleCallback{
    private final ItemAdapter itemAdapter;


    /**
     * Constructor
     * @param itemAdapter Adapter to perform callback function
     */
    public ItemTouchHelperCallback(ItemAdapter itemAdapter) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT |
                ItemTouchHelper.RIGHT); //Allow swipe in left and right direction
        this.itemAdapter = itemAdapter;
    }

    /**
     * Disable long press and drop
     * @return false
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    /**
     * Disable swipe and dismiss on all item view fragment
     * @return true if swipe and drop is enabled in current fragment; otherwise false
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    /**
     * Not implemented
     * @param recyclerView
     * @param viewHolder
     * @param target
     * @return false
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                          RecyclerView.ViewHolder target) {
        return false;
    }

    /**
     * Detect swipe activity and update database
     * @param viewHolder Listening viewHolder
     * @param direction Swipe direction
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        System.out.println("Item touch helper: item swiped at pos "+ viewHolder.getAdapterPosition());
    }
}