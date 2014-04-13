package appsec.kangurki.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SKEY implements EntryPoint {

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final LoginServiceAsync greetingService = GWT
			.create(LoginService.class);

	/**
	 * This is the entry point method.
	 */
	@Override
	public void onModuleLoad() {
		final Button sendNameButton = new Button("Send name");
		final Button loginButton = new Button("Login");
		final Button newUserButton = new Button("New user");
		final TextBox nameField = new TextBox();
		nameField.setText("GWT User");
		final TextBox passwordField = new PasswordTextBox();

		final Label errorLabel = new Label();
		final Label counterLabel = new Label();

		// We can add style names to widgets
		sendNameButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("nameFieldContainer").add(nameField);
		RootPanel.get("nameFieldContainer").add(sendNameButton);
		RootPanel.get("nameFieldContainer").add(counterLabel);
		RootPanel.get("nameFieldContainer").add(passwordField);
		RootPanel.get("nameFieldContainer").add(loginButton);
		RootPanel.get("nameFieldContainer").add(newUserButton);

		RootPanel.get("errorLabelContainer").add(errorLabel);

		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				sendNameButton.setEnabled(true);
				sendNameButton.setFocus(true);
			}
		});

		newUserButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				greetingService.init(nameField.getText(),
						passwordField.getText(), 100,
						new AsyncCallback<Void>() {

							@Override
							public void onFailure(Throwable caught) {
								errorLabel.setText("FAIL");

							}

							@Override
							public void onSuccess(Void result) {
								// TODO Auto-generated method stub
							}
						});

			}
		});

		loginButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				greetingService.login(nameField.getText(),
						passwordField.getText(), new AsyncCallback<Boolean>() {

							@Override
							public void onFailure(Throwable caught) {
								errorLabel.setText("FAIL");
							}

							@Override
							public void onSuccess(Boolean result) {
								if (result)
									errorLabel.setText("LOGIN OK");
								else
									errorLabel.setText("LOGIN NOT OK");
							}
						});

			}
		});

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			@Override
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a
			 * response.
			 */
			private void sendNameToServer() {
				String textToServer = nameField.getText();

				// Then, we send the input to the server.
				greetingService.getLoginCounter(textToServer,
						new AsyncCallback<Integer>() {
							@Override
							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user
								dialogBox
										.setText("Remote Procedure Call - Failure");
								dialogBox.center();
								closeButton.setFocus(true);
							}

							@Override
							public void onSuccess(Integer result) {
								dialogBox.setText("Remote Procedure Call");
								counterLabel.setText("Password number: "
										+ result);
								closeButton.setFocus(true);
							}
						});
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendNameButton.addClickHandler(handler);
		nameField.addKeyUpHandler(handler);
	}
}
