package com.buildbox.consent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.companyname.gamename.R;

import java.util.List;

public class ConsentAdapter extends RecyclerView.Adapter<ConsentAdapter.MyViewHolder> {

    public ConsentAdapter(List<AdNetwork> adNetworks, Listener listener){
        this.adNetworks = adNetworks;
        this.listener = listener;
    }

    interface Listener {
        void toggleConsent(int adapterPosition, boolean consent);
        void clickPrivacyPolicy(AdNetwork adNetwork);
    }

    private List<AdNetwork> adNetworks;
    private Listener listener;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View consentItem = inflater.inflate(R.layout.consent_item, parent, false);
        return new MyViewHolder(consentItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(adNetworks.get(position));
    }

    @Override
    public int getItemCount() {
        return adNetworks.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView displayName;
        TextView privacyPolicy;
        Switch toggle;
        MyViewHolder(View itemView) {
            super(itemView);
            privacyPolicy = itemView.findViewById(R.id.linkPrivacyPolicy);
            displayName = itemView.findViewById(R.id.textNetworkName);
            toggle = itemView.findViewById(R.id.switchToggle);
        }

        void bind(final AdNetwork adNetwork) {
            displayName.setText(adNetwork.getNetworkDisplayName());
            toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    listener.toggleConsent(getAdapterPosition(), isChecked);
                }
            });
            privacyPolicy.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    listener.clickPrivacyPolicy(adNetwork);
                }
            });
        }
    }
}
