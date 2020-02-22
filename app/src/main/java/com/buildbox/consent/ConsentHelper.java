package com.buildbox.consent;

import java.util.ArrayList;
import java.util.List;

public class ConsentHelper {

    public static List<AdNetwork> getAdNetworks() {
        ArrayList<AdNetwork> adNetworks = new ArrayList<>();
        /* admob */ /*
        adNetworks.add( new AdNetwork("admob", "Admob", "https://policies.google.com/technologies/partner-sites"));
        */ /* admob */
        //adNetworks.add( new Adnetwork("{networkname}", "{DisplayName}", "{PrivacyPolicyUrl}"));
        /* ironsource */ /*
        adNetworks.add( new AdNetwork("ironsource", "ironSource", "https://developers.ironsrc.com/ironsource-mobile/android/ironsource-mobile-privacy-policy/"));
        */ /* ironsource */
        return adNetworks;
    }

    public static String getConsentKey(String networkId) {
        return networkId + "_CONSENT_KEY";
    }
}
