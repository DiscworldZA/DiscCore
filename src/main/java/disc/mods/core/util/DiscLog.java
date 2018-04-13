package disc.mods.core.util;

import disc.mods.core.DiscMod;
import net.minecraftforge.fml.relauncher.FMLLaunchHandler;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import java.util.Locale;

/***
 *
 * @author DiscworldZA
 * @reference Fireball1725/firelib
 */
public class DiscLog {

	private final Logger logger;

	public DiscLog(DiscMod mod) {
		this.logger = LogManager.getLogger(mod.getModId());
		Side side = FMLLaunchHandler.side();
		if (side == null)
			side = Side.CLIENT;
		ThreadContext.put("side", side.name().toLowerCase(Locale.ENGLISH));
	}

	private void log(Level logLevel, Object message) {
		this.logger.log(logLevel, formatMessage(message));
	}

	public void all(Object message) {
		log(Level.ALL, formatMessage(message));
	}

	public void debug(Object message) {
		log(Level.DEBUG, formatMessage(message));
	}

	public void trace(Object message) {
		log(Level.TRACE, formatMessage(message));
	}

	public void fatal(Object message) {
		log(Level.FATAL, formatMessage(message));
	}

	public void error(Object message) {
		log(Level.ERROR, formatMessage(message));
	}

	public void warn(Object message) {
		log(Level.WARN, formatMessage(message));
	}

	public void info(Object message) {
		log(Level.INFO, formatMessage(message));
	}

	public void off(Object message) {
		log(Level.OFF, formatMessage(message));
	}

	private String formatMessage(Object msg) {
		return logger.getName() + " --> " + String.valueOf(msg);
	}
}
