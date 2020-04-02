package com.foxy.expansion_panel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.expansionpanel.ExpansionLayout;
import com.github.florent37.expansionpanel.viewgroup.ExpansionLayoutCollection;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecyclerViewActivity extends AppCompatActivity {

    private Unbinder unbinder;

    private RecyclerAdapter adapter;

    final List<Object> list = new ArrayList<>();

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        initViews();
        initRecyclerView();
        initData();
        adapter.setItems(list);
    }

    private void initData() {

        for(int i = 0; i < 30; i++) {
            list.add(new Object());
        }
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    private void initViews() {
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private final static class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        private final List<Object> mList = new ArrayList<>();

        private final ExpansionLayoutCollection expansionLayoutCollection = new ExpansionLayoutCollection();

        public RecyclerAdapter() {
            expansionLayoutCollection.openOnlyOne(true);
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return ViewHolder.buildFor(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.bind(mList.get(position));

            expansionLayoutCollection.add(holder.getExpansionLayout());
        }

        @Override
        public int getItemCount() {
            return mList != null ? mList.size() : 0;
        }

        public void setItems(List<Object> items) {
            this.mList.addAll(items);
            notifyDataSetChanged();
        }

        private final static class ViewHolder extends RecyclerView.ViewHolder {

            private ExpansionLayout layout;

            private static ViewHolder buildFor(ViewGroup viewGroup) {
                return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.expansion_panel_recycler_cell, viewGroup, false));
            }

            private ViewHolder(@NonNull View itemView) {
                super(itemView);
                layout = itemView.findViewById(R.id.expansionLayout);
            }

            private void bind(Object object) {
                layout.collapse(false);
            }

            private ExpansionLayout getExpansionLayout() {
                return layout;
            }

        }

    }



}
