package hackthon.smartdooralarm;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

public class ListFragment extends Fragment implements AdapterView.OnItemClickListener {
    RecyclerView recList;
    boolean ready;

    private static String TAG = "ListFragment";

    /**
     * Default constructor
     */
    public ListFragment() {

        ready = false;

    }

    /**
     * Callback function; create and setup view
     * @param inflater inflater
     * @param container container
     * @param savedInstanceState current state
     * @return View created
     */
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.v( TAG, "MYLISTFRAGMENT ONCREATE Called" );
        // Get the view of the fragment
        View view = inflater.inflate(R.layout.activity_main, container, false);

        // Create new RecyclerView
        recList = (RecyclerView) view.findViewById(R.id.picList);
        // Improve performance
        recList.setHasFixedSize(true);

        // Layour manager for RecyclerView
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        /* Setup adapter */
        ItemAdapter adapter = new ItemAdapter(getActivity(), MainActivity.itemList);
        recList.setAdapter(adapter);
        recList.setLayoutManager(llm);

        /* Swipe and dismiss configuarations */
        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(adapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recList);

        ready = true;
        return view;
    }

    /**
     * Callback for the activity created
     * @param savedInstanceState current state
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * Callback when an item is clicked
     * @param parent parent activity/view
     * @param view view
     * @param position position of item clicked
     * @param id item id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        Log.d("LIST", "Item: " + position + "selected");
    }

    /**
     * Callback for starting fragment activity
     */
    @Override
    public void onStart()
    {
        super.onStart();
    }

    /**
     * Callback for fragment activity stop
     */
    @Override
    public void onStop() {
        super.onStop();
        ItemAdapter adapter = (ItemAdapter) recList.getAdapter();
        //adapter.destroyCards();
    }


    /**
     * Callback for fragment activity resume
     */
    @Override
    public void onResume()
    {
        super.onResume();
        Log.v(TAG, "ON resume Called");
    }
    
}

