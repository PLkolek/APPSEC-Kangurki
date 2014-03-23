package pl.kangurki.lista3.csrf.client;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.rpc.XsrfProtectedService;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends XsrfProtectedService  {
	String greetServer(String name) throws IllegalArgumentException;
}
