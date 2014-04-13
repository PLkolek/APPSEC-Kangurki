package appsec.kangurki.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface LoginService extends RemoteService {

	int getLoginCounter(String name);

	boolean login(String name, String password);

	void init(String name, String password, int iters);
}
