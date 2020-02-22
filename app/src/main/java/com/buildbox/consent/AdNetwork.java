package com.buildbox.consent;

public class AdNetwork {
    private String networkId;
    private String networkDisplayName;
    private String privacyPolicyUrl;
    private Boolean consent = false;

    public AdNetwork(String networkId, String networkDisplayName, String privacyPolicyUrl){
        this.networkId = networkId;
        this.networkDisplayName = networkDisplayName;
        this.privacyPolicyUrl = privacyPolicyUrl;
    }

    public String getNetworkId() {
        return networkId;
    }


    String getNetworkDisplayName() {
        return networkDisplayName;
    }


    String getPrivacyPolicyUrl() {
        return privacyPolicyUrl;
    }

    public Boolean getConsent() {
        return consent;
    }

    public void setConsent(Boolean consent) {
        this.consent = consent;
    }
}
