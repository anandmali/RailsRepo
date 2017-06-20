package com.anand.loktratask.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anand.loktratask.R;
import com.anand.loktratask.model.pojo.CommitObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommitsListAdapter extends RecyclerView.Adapter<CommitsListAdapter.RepoViewHolder> {

    private List<CommitObject> commitObject;
    public CommitsListAdapter() {
        this.commitObject = new ArrayList<>();
    }

    public void addPosts(List<CommitObject> newCommit) {
        commitObject.addAll(newCommit);
        notifyDataSetChanged();
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_commit_row_item, parent, false);
        return new RepoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {
        String email = "Email : "+ commitObject.get(position).getCommit().getAuthor().getEmail();
        String message = "Commit : "+ commitObject.get(position).getCommit().getMessage();
        holder._txtName.setText(commitObject.get(position).getCommit().getAuthor().getName());
        holder._txtEmail.setText(email);
        holder._txtMessage.setText(message);
    }

    @Override
    public int getItemCount() {
        return commitObject.size();
    }

    class RepoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textName) TextView _txtName;
        @BindView(R.id.textEmail) TextView _txtEmail;
        @BindView(R.id.textMessage) TextView _txtMessage;
        RepoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
