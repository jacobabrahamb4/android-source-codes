/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.slidingtabscolors;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchFragment extends BaseFragment implements SearchView.OnQueryTextListener, BaseFragment.CallbackTask.QuerryCallback {

    private SearchView mSearchView;
    private RecyclerView mListView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MyAdapter mAdapter;

    /**
     * @return a new instance of {@link ContentFragment}, adding the parameters into a bundle and
     * setting them as arguments.
     */
    public static SearchFragment newInstance(CharSequence title, int indicatorColor,
                                             int dividerColor) {
        Bundle bundle = new Bundle();
        bundle.putCharSequence(KEY_TITLE, title);
        bundle.putInt(KEY_INDICATOR_COLOR, indicatorColor);
        bundle.putInt(KEY_DIVIDER_COLOR, dividerColor);

        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSearchView = (SearchView) view.findViewById(R.id.search_box);
        mSearchView.setOnQueryTextListener(this);
        mListView = (RecyclerView) view.findViewById(R.id.search_result);
        mListView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mListView.setLayoutManager(mLayoutManager);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if(query.chars().allMatch(Character::isLetter)) {
            new CallbackTask(this).execute(dictionaryEntries(query));
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return true;
    }

    @Override
    public void publishDefinitions(ArrayList<Definition> definitions) {
        if(mAdapter == null) {
            mAdapter = new MyAdapter(definitions);
            mListView.setAdapter(mAdapter);
        } else {
            mAdapter.setDefinitions(definitions);
            mAdapter.notifyDataSetChanged();
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private ArrayList<Definition> mDataset;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder

        public class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView mDefinition;
            public TextView mExample;

            public ViewHolder(View v) {
                super(v);
                mDefinition = v.findViewById(R.id.definition_text);
                mExample = v.findViewById(R.id.example_text);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public MyAdapter(ArrayList<Definition> myDataset) {
            mDataset = myDataset;
        }

        public void setDefinitions(ArrayList<Definition> definitions) {
            mDataset = definitions;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.definition_layout, parent, false);

            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.mDefinition.setText(mDataset.get(position).definitions[0]);
            holder.mExample.setText(mDataset.get(position).examples[0]);

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }


}
