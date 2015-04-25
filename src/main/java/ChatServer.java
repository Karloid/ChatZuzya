import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class ChatServer {
	private static int port = 8081;

	public static void main(String[] args) {
		System.out.println("Lol");
		Server server = new Server();
		ServerConnector connector = new ServerConnector(server);
		connector.setPort(getPort());
		server.addConnector(connector);

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);

		ServletHolder holderEvents = new ServletHolder("ws-events", ChatServlet.class);
		context.addServlet(holderEvents, getChatPath() +"*");

		try {
			server.start();
			server.dump(System.err);
			server.join();
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}

	}

	public static String getChatPath() {
		return "/chat/";
	}

	public static int getPort() {
		return port;
	}

	public static void setPort(int port) {
		ChatServer.port = port;
	}
}
