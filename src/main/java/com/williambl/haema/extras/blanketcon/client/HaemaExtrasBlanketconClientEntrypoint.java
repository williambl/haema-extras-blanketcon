package com.williambl.haema.extras.blanketcon.client;

import com.williambl.haema.extras.blanketcon.HaemaExtrasBlanketcon;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

/**
 * Until QLK becomes a thing
 */
public class HaemaExtrasBlanketconClientEntrypoint implements ClientModInitializer {
	@Override
	public void onInitializeClient(ModContainer mod) {
		HaemaExtrasBlanketconClient.INSTANCE.onInitializeClient(mod);
	}
}
