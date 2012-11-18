package ai.aw;

import java.awt.Desktop;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URI;
import java.util.Dictionary;
import java.util.Hashtable;

import org.eclipse.equinox.http.jetty.JettyConfigurator;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    private static final String HTTP_PORT_KEY = "http.port";
    private static final int HTTP_PORT = 8080;
    private static final String SERVER_NAME = "demojetty";

    @Override
    public void start(BundleContext context) throws Exception {
    	
        try {  
             ServerSocket serverSocket = new java.net.ServerSocket(50382);  
        } catch (IOException ex) {  
             javax.swing.JOptionPane.showMessageDialog(null, "aw уже запущен.  http://localhost:8080/ ");
             this.stop(context);
             context.getBundle(0).stop();
             System.exit(0);
             return;
        }  

        
        Dictionary<String, Object> properties = new Hashtable<String, Object>();
        properties.put(HTTP_PORT_KEY, HTTP_PORT);
        JettyConfigurator.startServer(SERVER_NAME, properties);
        //System.out.println("Server " + SERVER_NAME + " has been started");
        URI url = new URI("http://localhost:8080/welcome?name=");
        Desktop.getDesktop().browse(url);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        JettyConfigurator.stopServer(SERVER_NAME);
        //System.out.println("Server " + SERVER_NAME + " has been stoped");
    }

}
