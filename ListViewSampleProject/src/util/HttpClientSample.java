package util;

import android.os.Build;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

public class HttpClientSample {
	private static final int GINGERBREAD = 9;

	public static HttpTransport newCompatibleTransport() {
		return isGingerbreadOrHigher() ? new NetHttpTransport() : new ApacheHttpTransport();
	}

	public static boolean isGingerbreadOrHigher() {
		return Build.VERSION.SDK_INT >= GINGERBREAD;
	}


}
