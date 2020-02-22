

package com.secrethq.utils;

public class PTJniHelper {
	public static String password() {
		return "Giv/IJqvt1gWd/kuyqa9DBd8/S+c/eYMHn6sJ8ipvFlIKq10mv60W0sqrSefqbINFnbwdZz6sVodfK1zyKa3XA==";
	}
	public static native boolean isAdNetworkActive( String name ); 
    public static native String jsSettingsString();
    
    public static native void setSettingsValue(String path, String value);
    public static native String getSettingsValue(String path);
}
