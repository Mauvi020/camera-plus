package dev.mauvi.cameraplus.core;

import net.labymod.api.addon.LabyAddon;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.lifecycle.ShutdownEvent;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class CameraPlusAddon extends LabyAddon<CameraPlusConfiguration> {
    private CameraController cameraController;

    @Override
    protected void enable() {
        this.registerSettingCategory();
        this.cameraController = new CameraController(this.configuration(), new CollisionResolver(this.configuration()));
        this.registerListener(new CameraLifecycleListener(this.cameraController));
        this.logger().info("Camera+ enabled");
    }

    @Override
    protected Class<CameraPlusConfiguration> configurationClass() {
        return CameraPlusConfiguration.class;
    }

    @Subscribe
    public void onShutdown(ShutdownEvent event) {
        if (this.cameraController != null) {
            this.cameraController.reset();
        }
    }
}
