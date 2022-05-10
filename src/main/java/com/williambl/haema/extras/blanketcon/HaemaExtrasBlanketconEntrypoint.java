package com.williambl.haema.extras.blanketcon;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

/**
 * Until QLK becomes a thing
 */
public class HaemaExtrasBlanketconEntrypoint implements ModInitializer {
	@Override
	public void onInitialize(ModContainer mod) {
		HaemaExtrasBlanketcon.INSTANCE.onInitialize(mod);
	}
}
