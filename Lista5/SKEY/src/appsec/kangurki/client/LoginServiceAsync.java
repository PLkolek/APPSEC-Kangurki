package appsec.kangurki.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface LoginServiceAsync {
	void login(String name, String password, AsyncCallback<Boolean> callback);

	void init(String name, String password, int iters,
			AsyncCallback<Void> callback);

	void getLoginCounter(String name, AsyncCallback<Integer> callback);
}
