package mymod;

import java.util.ArrayList;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import basemod.BaseMod;
import basemod.interfaces.PostInitializeSubscriber;
import basemod.interfaces.PostUpdateSubscriber;
import utils.*;

/**
 * @author 彼君不触
 * @version 6/4/2020
 * @since 5/24/2020
 */

@SpireInitializer
public class CommanderMod implements PostUpdateSubscriber, PostInitializeSubscriber {
	public static final Logger LOG = LogManager.getLogger(CommanderMod.class.getName());
	public static final HashMap<String, Class<?>> CLASS_MAP = new HashMap<String, Class<?>>();
	public static final ArrayList<String> INVALID_NAME = new ArrayList<String>();
	public static final ArrayList<String> SHORT_NAMES = new ArrayList<String>();
	
	public static void initialize() {
		BaseMod.subscribe(new CommanderMod());
		initName("com.evacipated.cardcrawl.modthespire.steam.SteamWorkshop$Callback");
		initName("com.evacipated.cardcrawl.modthespire.steam.SteamWorkshop");
		initName("javassist.util.HotSwapper");
	}
	
	private static void initName(String s) {
		INVALID_NAME.add(s);
	}
	
	private static void initClassMap() {
		for (String name : PackageUtil.getClassName("")) {
			if (INVALID_NAME.contains(name))
				continue;
			put(shortName(name), getClass(name));
		}
	}
	
	private static void put(String name, Class<?> c) {
		if (c != null)
			CLASS_MAP.put(name, c);
	}
	
	public static String shortName(String fullName) {
		String tmp = fullName.substring(fullName.lastIndexOf(".") + 1);
		if (!SHORT_NAMES.contains(tmp))
			SHORT_NAMES.add(tmp);
		return tmp;
	}
	
	public static Class<?> getClass(String fullName) {
		try {
			return Class.forName(fullName);
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
	

	@Override
	public void receivePostInitialize() {
		LOG.info("啊Started Initing Class Map.");
		initClassMap();
		LOG.info("Finished Initing Class Map With " + CLASS_MAP.size() + " Classes In Total.");
		LOG.info("Num Of Short Names For Classes: " + SHORT_NAMES.size());
	}
	
	@Override
	public void receivePostUpdate() {
	}

}
