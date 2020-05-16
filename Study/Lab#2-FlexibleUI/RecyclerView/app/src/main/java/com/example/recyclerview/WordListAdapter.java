package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class WordListAdapter extends
        RecyclerView.Adapter<WordListAdapter.WordViewHolder>  {


    /* Android uses adapters (from the Adapter class) to connect data with View items in a list.
       There are many different kinds of adapters available, and you can also write custom adapters.
       In this task you will create an adapter that associates your list of words with word list
       View items.
       To connect data with View items, the adapter needs to know about the View items. The adapter
       uses a ViewHolder that describes a View item and its position within the RecyclerView.

       You need to hold your data in the adapter, and WordListAdapter needs a constructor that
       initializes the word list from the data.
     */

    private final LinkedList<String> mWordList;
    private LayoutInflater mInflater;

    public WordListAdapter(Context context, LinkedList<String> mWordList) {
        /* WordListAdapter needs a constructor that initializes the word list from the data.
           To create a View for a list item, the WordListAdapter needs to inflate the XML for a list
           item. You use a layout inflator for that job. LayoutInflator reads a layout XML
           description and converts it into the corresponding View items. Start by creating a member
           variable for the inflater in WordListAdapter. */
        mInflater = LayoutInflater.from(context);
        this.mWordList = mWordList;
    }

    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView wordItemView;
        final WordListAdapter mAdapter;

        public WordViewHolder(@NonNull View itemView, WordListAdapter mAdapter) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.word);
            this.mAdapter = mAdapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // Get the position of the item that was clicked.
            int mPosition = getLayoutPosition();
            // Use that to access the affected item in mWordList.
            String element = mWordList.get(mPosition);
            // Change the word in the mWordList.
            mWordList.set(mPosition, "Clicked! " + element);
            // Notify the adapter, that the data has changed so it can
            // update the RecyclerView to display the data.
            mAdapter.notifyDataSetChanged();
        }
    }

    /* The onCreateViewHolder() method is similar to the onCreate() method. It inflates the item
       layout, and returns a ViewHolder with the layout and the adapter. */
    @NonNull
    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                             int viewType) {
        View mItemView = mInflater.inflate(R.layout.wordlist_item, parent, false);
        return new WordViewHolder(mItemView, this);
    }

    /* The onBindViewHolder() method connects your data to the view holder. */
    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder holder, int position) {
        String mCurrent = mWordList.get(position);
        holder.wordItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }
}
