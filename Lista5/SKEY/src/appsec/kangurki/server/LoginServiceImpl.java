package appsec.kangurki.server;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import appsec.kangurki.client.LoginService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements
		LoginService {

	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean login(String name, String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			byte[] passBytes = Base64.getDecoder().decode(password);
			passBytes = md.digest(passBytes);
			String password2 = Base64.getEncoder().encodeToString(passBytes);
			Connection db = DriverManager
					.getConnection("jdbc:postgresql://localhost:5432/appsec",
							"appsec", "test");
			PreparedStatement stmt = db
					.prepareStatement("SELECT * FROM users where name=? and password=?");
			stmt.setString(1, name);
			stmt.setString(2, password2);
			ResultSet rs = stmt.executeQuery();
			boolean success = rs.next();
			if (success) {
				int counter = rs.getInt("counter") - 1;
				stmt = db
						.prepareStatement("UPDATE users SET counter =?, password=? where name=?");
				stmt.setInt(1, counter);
				stmt.setString(2, password);
				stmt.setString(3, name);
				stmt.executeUpdate();
			}
			return success;
		} catch (SQLException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void init(String name, String password, int iters) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			byte[] passBytes = Base64.getDecoder().decode(password);
			for (int i = 0; i < iters; i++) {
				passBytes = md.digest(passBytes);
			}

			Connection db = DriverManager
					.getConnection("jdbc:postgresql://localhost:5432/appsec",
							"appsec", "test");
			PreparedStatement stmt = db
					.prepareStatement("INSERT INTO users (name, password, counter) VALUES (?,?,?)");
			stmt.setString(1, name);
			stmt.setString(2, Base64.getEncoder().encodeToString(passBytes));
			stmt.setInt(3, iters - 1);
			stmt.executeUpdate();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public int getLoginCounter(String name) {
		try {

			Connection db = DriverManager
					.getConnection("jdbc:postgresql://localhost:5432/appsec",
							"appsec", "test");
			PreparedStatement stmt = db
					.prepareStatement("SELECT * FROM users where name=?");
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			return rs.getInt("counter");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
