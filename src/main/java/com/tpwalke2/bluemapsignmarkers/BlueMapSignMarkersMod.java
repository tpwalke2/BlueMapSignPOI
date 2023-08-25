package com.tpwalke2.bluemapsignmarkers;

import com.tpwalke2.bluemapsignmarkers.core.SignManager;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerBlockEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;

public class BlueMapSignMarkersMod implements ModInitializer {
	@Override
	public void onInitialize() {
		ServerLifecycleEvents.SERVER_STARTING.register(this::onServerStarting);
		ServerLifecycleEvents.SERVER_STOPPED.register(this::onServerStopped);
		ServerBlockEntityEvents.BLOCK_ENTITY_LOAD.register(this::onBlockEntityLoad);

		// TODO on sign destroy - mixin to override SignBlock.onBreak
	}

	private void onServerStarting(MinecraftServer server) {
		// TODO load markers from file
	}

	private void onServerStopped(MinecraftServer server) {
		// TODO save markers to file
		SignManager.INSTANCE.shutdown();
	}

	private void onBlockEntityLoad(BlockEntity blockEntity, ServerWorld world) {
		if (!(blockEntity instanceof SignBlockEntity castBlockEntity)) return;

		SignManager.addOrUpdate(SignBlockEntityHelper.createSignEntry(castBlockEntity));
	}
}
