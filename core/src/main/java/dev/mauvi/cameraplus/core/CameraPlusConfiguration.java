package dev.mauvi.cameraplus.core;

import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.widget.widgets.input.SliderWidget.SliderSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.property.ConfigProperty;

@ConfigName("settings")
public class CameraPlusConfiguration extends AddonConfig {

    @SwitchSetting
    private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

    @SwitchSetting
    private final ConfigProperty<Boolean> freecamEnabled = new ConfigProperty<>(false);

    @SwitchSetting
    private final ConfigProperty<Boolean> thirdPersonEnabled = new ConfigProperty<>(true);

    @SwitchSetting
    private final ConfigProperty<Boolean> smoothCamera = new ConfigProperty<>(true);

    @SwitchSetting
    private final ConfigProperty<Boolean> collisionEnabled = new ConfigProperty<>(true);

    @SwitchSetting
    private final ConfigProperty<Boolean> cinematicEnabled = new ConfigProperty<>(false);

    @SliderSetting(min = 0, max = 100, steps = 1)
    private final ConfigProperty<Integer> cameraDistance = new ConfigProperty<>(5);

    @SliderSetting(min = -10, max = 10, steps = 1)
    private final ConfigProperty<Integer> cameraHeight = new ConfigProperty<>(0);

    @SliderSetting(min = -90, max = 90, steps = 1)
    private final ConfigProperty<Integer> cameraPitchOffset = new ConfigProperty<>(0);

    @SliderSetting(min = -180, max = 180, steps = 1)
    private final ConfigProperty<Integer> cameraYawOffset = new ConfigProperty<>(0);

    @SliderSetting(min = 30, max = 140, steps = 1)
    private final ConfigProperty<Integer> fov = new ConfigProperty<>(90);

    @SliderSetting(min = 0, max = 50, steps = 1)
    private final ConfigProperty<Integer> smoothing = new ConfigProperty<>(12);

    @SliderSetting(min = 0, max = 25, steps = 1)
    private final ConfigProperty<Integer> freecamSpeed = new ConfigProperty<>(5);

    @SliderSetting(min = 0, max = 20, steps = 1)
    private final ConfigProperty<Integer> freecamSlowSpeed = new ConfigProperty<>(1);

    @Override
    public ConfigProperty<Boolean> enabled() {
        return this.enabled;
    }

    public ConfigProperty<Boolean> freecamEnabled() {
        return this.freecamEnabled;
    }

    public ConfigProperty<Boolean> thirdPersonEnabled() {
        return this.thirdPersonEnabled;
    }

    public ConfigProperty<Boolean> smoothCamera() {
        return this.smoothCamera;
    }

    public ConfigProperty<Boolean> collisionEnabled() {
        return this.collisionEnabled;
    }

    public ConfigProperty<Boolean> cinematicEnabled() {
        return this.cinematicEnabled;
    }

    public ConfigProperty<Integer> cameraDistance() {
        return this.cameraDistance;
    }

    public ConfigProperty<Integer> cameraHeight() {
        return this.cameraHeight;
    }

    public ConfigProperty<Integer> cameraPitchOffset() {
        return this.cameraPitchOffset;
    }

    public ConfigProperty<Integer> cameraYawOffset() {
        return this.cameraYawOffset;
    }

    public ConfigProperty<Integer> fov() {
        return this.fov;
    }

    public ConfigProperty<Integer> smoothing() {
        return this.smoothing;
    }

    public ConfigProperty<Integer> freecamSpeed() {
        return this.freecamSpeed;
    }

    public ConfigProperty<Integer> freecamSlowSpeed() {
        return this.freecamSlowSpeed;
    }
}
