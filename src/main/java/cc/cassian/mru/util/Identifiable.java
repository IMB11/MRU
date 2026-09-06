package cc.cassian.mru.util;

import net.minecraft.resources.Identifier;

public interface Identifiable {
	default Identifier mru$identifier() {
		throw new AssertionError("not implemented!");
	}
}
