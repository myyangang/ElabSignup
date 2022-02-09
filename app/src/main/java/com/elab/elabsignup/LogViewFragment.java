package com.elab.elabsignup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.elab.elabsignup.event.SignupEvent;
import com.elab.elabsignup.event.SignupEventList;

import java.util.List;

public class LogViewFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private SignupEvent mSignupEvent;
    private LogViewAdapter mLogViewAdapter;

    public static LogViewFragment newInstance(){
        return new LogViewFragment();
    }

    @Override public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_log_view_recycler,container,false);
        mRecyclerView = view.findViewById(R.id.log_view_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    @Override public void onResume(){
        super.onResume();
        updateUI();
    }

    private class LogViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTextViewItem;
        public LogViewHolder(LayoutInflater inflater,ViewGroup parent){
            super(inflater.inflate(R.layout.fragment_log_view_item,parent,false));
            mTextViewItem = itemView.findViewById(R.id.log_view_TextView_Item);
        }
        public void bind(SignupEvent signupEvent){
            mSignupEvent = signupEvent;
            mTextViewItem.setText(mSignupEvent.toString());
        }
        @Override
        public void onClick(View v) {
            // TODO:后续可以增加点击跳转到详情页面的功能
        }
    }

    private class LogViewAdapter extends RecyclerView.Adapter<LogViewHolder> {
        private List<SignupEvent> mSignupEvents;
        public LogViewAdapter(List<SignupEvent> signupEvents){
            mSignupEvents = signupEvents;
        }
        public void setSignupEvents(List<SignupEvent> signupEvents){
            mSignupEvents = signupEvents;
        }
        @Override public LogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new LogViewHolder(layoutInflater,parent);
        }
        @Override public void onBindViewHolder(@NonNull LogViewHolder holder, int position) {
            holder.bind(mSignupEvents.get(position));
        }
        @Override public int getItemCount() {
            return mSignupEvents.size();
        }
    }

    private void updateUI(){
        SignupEventList signupEventList = SignupEventList.get(getActivity());
        List<SignupEvent> signupEvents = signupEventList.getSignupEvents();
        if(mLogViewAdapter == null){
            mLogViewAdapter = new LogViewAdapter(signupEvents);
            mRecyclerView.setAdapter(mLogViewAdapter);
        }else{
            mLogViewAdapter.setSignupEvents(signupEvents);
            mLogViewAdapter.notifyDataSetChanged();
        }
    }


}
