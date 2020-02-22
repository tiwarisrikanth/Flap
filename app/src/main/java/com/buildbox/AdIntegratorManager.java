package com.buildbox;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.preference.PreferenceManager;

import com.buildbox.consent.AdNetwork;
import com.buildbox.consent.ConsentHelper;

import com.apponboard.aob_sessionreporting.AOBReporting;
import com.buildbox.adapter.AdIntegratorInterface;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class AdIntegratorManager {

    private static HashMap<String, AdIntegratorInterface> integrators = new HashMap<>();

    private static WeakReference<Activity> activity;
    private static String TAG = "AdIntegratorManager";

    public static void initBridge(Activity act) {
        activity = new WeakReference<>(act);
        /* ironsource */ /* integrators.put("ironsource", new com.buildbox.adapter.ironsource.AdIntegrator());  */ /* ironsource */
        /* admob */ /* integrators.put("admob", new com.buildbox.adapter.admob.AdIntegrator()); */ /* admob */
        /* custom */ /* integrators.put("custom", new com.buildbox.adapter.custom.AdIntegrator()); */ /* custom */
    }

    public static void initAds(String adNetworkId, final HashMap<String, String> initValues) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity.get());
        final boolean userConsent = sharedPreferences.getBoolean(ConsentHelper.getConsentKey(adNetworkId), false);
        Log.d(TAG, "ad init ads hit with network " + adNetworkId);
        final AdIntegratorInterface integrator = integrators.get(adNetworkId);
        if (integrator != null) {
            Log.d(TAG, "initializing ads for " + adNetworkId);
            Log.d(TAG, "setting user consent: " + userConsent);
            activity.get().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    integrator.setUserConsent(userConsent);
                    integrator.initAds(initValues, activity);
                }
            });
        } else {
            Log.e(TAG, "Ad network " + adNetworkId + " not found in map");
            Log.d(TAG, "failing ad network " + adNetworkId);
            networkFailed(adNetworkId);
        }
    }

    public static void initBanner(String adNetworkId) {
        Log.d(TAG, "initBanner");
        final AdIntegratorInterface integrator = integrators.get(adNetworkId);
        if (integrator != null) {
            Log.d(TAG, "initializing banner for " + adNetworkId);
            activity.get().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    integrator.initBanner();
                }
            });
        } else {
            Log.e(TAG, "Ad network " + adNetworkId + " not found in map");
        }
    }

    public static void initInterstitial(String adNetworkId) {
        Log.d(TAG, "initInterstitial");
        final AdIntegratorInterface integrator = integrators.get(adNetworkId);
        if (integrator != null) {
            Log.d(TAG, "initializing inter for " + adNetworkId);
            activity.get().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    integrator.initInterstitial();
                }
            });
        } else {
            Log.e(TAG, "Ad network " + adNetworkId + " not found in map");
        }
    }

    public static void initRewardedVideo(String adNetworkId) {
        Log.d(TAG, "initrewarded");
        final AdIntegratorInterface integrator = integrators.get(adNetworkId);
        if (integrator != null) {
            Log.d(TAG, "initializing reward for " + adNetworkId);
            activity.get().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    integrator.initRewardedVideo();
                }
            });
        } else {
            Log.e(TAG, "Ad network " + adNetworkId + " not found in map");
        }
    }

    public static void showBanner(String adNetworkId) {
        Log.d(TAG, "showbanner");
        final AdIntegratorInterface integrator = integrators.get(adNetworkId);
        if (integrator != null) {
            Log.d(TAG, "show banner for " + adNetworkId);
            activity.get().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    integrator.showBanner();
                }
            });
        } else {
            Log.e(TAG, "Ad network " + adNetworkId + " not found in map");
        }
    }

    public static void hideBanner(String adNetworkId) {
        Log.d(TAG, "hidebanner");
        final AdIntegratorInterface integrator = integrators.get(adNetworkId);
        if (integrator != null) {
            Log.d(TAG, "hide banner for " + adNetworkId);
            activity.get().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    integrator.hideBanner();
                }
            });
        } else {
            Log.e(TAG, "Ad network " + adNetworkId + " not found in map");
        }
    }

    public static void showInterstitial(String adNetworkId) {
        Log.d(TAG, "showinterstitial");
        final AdIntegratorInterface integrator = integrators.get(adNetworkId);
        if (integrator != null) {
            Log.d(TAG, "show inter for " + adNetworkId);
            activity.get().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    integrator.showInterstitial();
                }
            });
        } else {
            Log.e(TAG, "Ad network " + adNetworkId + " not found in map");
        }
    }

    public static void showRewardedVideo(String adNetworkId) {
        Log.d(TAG, "showrewarded");
        final AdIntegratorInterface integrator = integrators.get(adNetworkId);
        if (integrator != null) {
            Log.d(TAG, "show rewarded for " + adNetworkId);
            activity.get().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    integrator.showRewardedVideo();
                }
            });
        } else {
            Log.e(TAG, "Ad network " + adNetworkId + " not found in map");
        }
    }

    public static boolean isBannerVisible(String adNetworkId) {
        Log.d(TAG, "isbannervisible");
        final AdIntegratorInterface integrator = integrators.get(adNetworkId);
        if (integrator != null) {
            return integrator.isBannerVisible();
        } else {
            Log.e(TAG, "Ad network " + adNetworkId + " not found in map");
        }
        return false;
    }

    public static boolean isRewardedVideoAvailable(String adNetworkId) {
        Log.d(TAG, "isrewardedavailable");
        final AdIntegratorInterface integrator = integrators.get(adNetworkId);
        if (integrator != null) {
            Log.d(TAG, "is rewarded available for " + adNetworkId);
            return integrator.isRewardedVideoAvailable();
        } else {
            Log.e(TAG, "Ad network " + adNetworkId + " not found in map");
        }
        return false;
    }

    public static void setUserConsent(String adNetworkId, final boolean consentGiven) {
        Log.d(TAG, "setuserconsent");
        final AdIntegratorInterface integrator = integrators.get(adNetworkId);
        if (integrator != null) {
            activity.get().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    integrator.setUserConsent(consentGiven);
                }
            });
        } else {
            Log.e(TAG, "Ad network " + adNetworkId + " not found in map");
        }
    }

    public static void revokeAllConsent() {
        Log.d(TAG, "revokeAllConsent");
        activity.get().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = android.preference.PreferenceManager.getDefaultSharedPreferences(activity.get());
                for (AdNetwork adNetwork : ConsentHelper.getAdNetworks()) {
                    Log.d(TAG, "Revoking consent for " + adNetwork.getNetworkId());
                    sharedPreferences
                            .edit()
                            .putBoolean(ConsentHelper.getConsentKey(adNetwork.getNetworkId()), false)
                            .commit();
                    final AdIntegratorInterface integrator = integrators.get(adNetwork.getNetworkId());
                    if (integrator!=null){
                        integrator.setUserConsent(false);
                    } else {
                        Log.e(TAG, "Ad network " + adNetwork.getNetworkId() + " not found in map");
                    }
                }
                Toast toast = Toast.makeText(activity.get(), "Consent revoked for all ad networks", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    public static void bannerImpression(String adNetworkId) {
        AOBReporting.bannerAdAttempt(adNetworkId, true);
    }

    public static void interstitialImpression(String adNetworkId) {
        AOBReporting.interstitialAdAttempt(adNetworkId, true);
    }

    public static void rewardedVideoImpression(String adNetworkId) {
        AOBReporting.rewardedVideoAdAttempt(adNetworkId, true);
    }

    public static void onActivityCreated(Activity activity) {
        for (Map.Entry mapElement : integrators.entrySet()) {
            ((AdIntegratorInterface) mapElement.getValue()).onActivityCreated(activity);
        }
    }

    public static void onActivityStarted(Activity activity) {
        for (Map.Entry mapElement : integrators.entrySet()) {
            ((AdIntegratorInterface) mapElement.getValue()).onActivityStarted(activity);
        }
    }

    public static void onActivityResumed(Activity activity) {
        for (Map.Entry mapElement : integrators.entrySet()) {
            ((AdIntegratorInterface) mapElement.getValue()).onActivityResumed(activity);
        }
    }

    public static void onActivityPaused(Activity activity) {
        for (Map.Entry mapElement : integrators.entrySet()) {
            ((AdIntegratorInterface) mapElement.getValue()).onActivityPaused(activity);
        }
    }

    public static void onActivityStopped(Activity activity) {
        for (Map.Entry mapElement : integrators.entrySet()) {
            ((AdIntegratorInterface) mapElement.getValue()).onActivityStopped(activity);
        }
    }

    public static void onActivityDestroyed(Activity activity) {
        for (Map.Entry mapElement : integrators.entrySet()) {
            ((AdIntegratorInterface) mapElement.getValue()).onActivityDestroyed(activity);
        }
    }

    public static void interstitialClosed(String adNetworkId) {
        interstitialClosedNative(adNetworkId);
    }

    public static void rewardedVideoDidReward(String adNetworkId, boolean value) {
        rewardedVideoDidRewardNative(adNetworkId, value);
    }

    public static void rewardedVideoDidEnd(String adNetworkId, boolean value) {
        rewardedVideoDidEndNative(adNetworkId, value);
    }

    public static void networkLoaded(String adNetworkId) {
        networkLoadedNative(adNetworkId);
    }

    public static void bannerLoaded(String adNetworkId) {
        bannerLoadedNative(adNetworkId);
    }

    public static void interstitialLoaded(String adNetworkId) {
        interstitialLoadedNative(adNetworkId);
    }

    public static void rewardedVideoLoaded(String adNetworkId) {
        rewardedVideoLoadedNative(adNetworkId);
    }

    public static void networkFailed(String adNetworkId) {
        networkFailedNative(adNetworkId);
    }

    public static void bannerFailed(String adNetworkId) {
        bannerFailedNative(adNetworkId);
        AOBReporting.bannerAdAttempt(adNetworkId, false);
    }

    public static void interstitialFailed(String adNetworkId) {
        interstitialFailedNative(adNetworkId);
        AOBReporting.interstitialAdAttempt(adNetworkId, false);
    }

    public static void rewardedVideoFailed(String adNetworkId) {
        rewardedVideoFailedNative(adNetworkId);
        AOBReporting.rewardedVideoAdAttempt(adNetworkId, false);
    }

    public static native void interstitialClosedNative(String adNetworkId);

    public static native void rewardedVideoDidRewardNative(String adNetworkId, boolean value);

    public static native void rewardedVideoDidEndNative(String adNetworkId, boolean value);

    public static native void networkLoadedNative(String adNetworkId);

    public static native void bannerLoadedNative(String adNetworkId);

    public static native void interstitialLoadedNative(String adNetworkId);

    public static native void rewardedVideoLoadedNative(String adNetworkId);

    public static native void networkFailedNative(String adNetworkId);

    public static native void bannerFailedNative(String adNetworkId);

    public static native void interstitialFailedNative(String adNetworkId);

    public static native void rewardedVideoFailedNative(String adNetworkId);
}
