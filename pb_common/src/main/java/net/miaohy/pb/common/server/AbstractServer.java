
package net.miaohy.pb.common.server;

import net.miaohy.pb.common.constant.IConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Abstract server instance.
 * 
 * @author Jerry Cheng 
 * @date Dec 13, 2019   
 * @version
 */
public abstract class AbstractServer implements IConstants {

	// The static logger
	protected static Logger logger = LoggerFactory.getLogger(AbstractServer.class);

	// Server name
	protected String name = null;

	// Server join
	public abstract void join() throws InterruptedException;

	// Server start
	public abstract void start();

	// Server restart
	public abstract void restart();

	// Server shutdown
	public abstract void shutdown();

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
