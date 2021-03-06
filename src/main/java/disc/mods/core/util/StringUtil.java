package disc.mods.core.util;

import net.minecraft.block.properties.IProperty;

import java.util.Map;

/***
 * @credit Fireball1725/firelib
 * @author Pieter.VanLill
 *
 */
public class StringUtil {
	public static String getPropertyString(Map<IProperty<?>, Comparable<?>> values, String... extrasArgs) {
		StringBuilder stringbuilder = new StringBuilder();

		for (Map.Entry<IProperty<?>, Comparable<?>> entry : values.entrySet()) {
			if (stringbuilder.length() != 0) {
				stringbuilder.append(",");
			}

			IProperty<?> iproperty = entry.getKey();
			stringbuilder.append(iproperty.getName());
			stringbuilder.append("=");
			stringbuilder.append(getPropertyName(iproperty, entry.getValue()));
		}

		if (stringbuilder.length() == 0) {
			stringbuilder.append("inventory");
		}

		for (String args : extrasArgs) {
			if (stringbuilder.length() != 0)
				stringbuilder.append(",");
			stringbuilder.append(args);
		}

		return stringbuilder.toString();
	}

	private static <T extends Comparable<T>> String getPropertyName(IProperty<T> p_187489_1_,
	                                                                Comparable<?> p_187489_2_) {
		return p_187489_1_.getName((T) p_187489_2_);
	}
}