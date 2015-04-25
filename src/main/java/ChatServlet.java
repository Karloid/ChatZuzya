import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

@SuppressWarnings("serial")
public class ChatServlet extends WebSocketServlet{
	@Override
	public void configure(WebSocketServletFactory factory) {
		factory.register(ChatSocket.class);
	}
}
