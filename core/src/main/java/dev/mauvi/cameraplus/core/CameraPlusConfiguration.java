package dev.mauvi.cameraplus.core;

import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.key.Key;
import net.labymod.api.client.gui.screen.widget.widgets.input.KeybindWidget.KeyBindSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SliderWidget.SliderSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.Config;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.property.ConfigProperty;

@ConfigName("settings")
public class CameraPlusConfiguration extends AddonConfig {
    private final GeneralSettings general = new GeneralSettings();
    private final CameraSettings camera = new CameraSettings();
    private final FreecamSettings freecam = new FreecamSettings();
    private final HotkeySettings hotkeys = new HotkeySettings();

    @Override
    public ConfigProperty<Boolean> enabled() {
        return this.general.enabled();
    }

    public ConfigProperty<Boolean> freecamEnabled() {
        return this.freecam.enabled();
    }

    public ConfigProperty<Boolean> thirdPersonEnabled() {
        return this.camera.thirdPersonEnabled();
    }

    public ConfigProperty<Boolean> smoothCamera() {
        return this.general.smoothCamera();
    }

    public ConfigProperty<Boolean> collisionEnabled() {
        return this.general.collisionEnabled();
    }

    public ConfigProperty<Boolean> cinematicEnabled() {
        return this.camera.cinematicEnabled();
    }

    public ConfigProperty<Integer> cameraDistance() {
        return this.camera.cameraDistance();
    }

    public ConfigProperty<Integer> cameraHeight() {
        return this.camera.cameraHeight();
    }

    public ConfigProperty<Integer> cameraPitchOffset() {
        return this.camera.cameraPitchOffset();
    }

    public ConfigProperty<Integer> cameraYawOffset() {
        return this.camera.cameraYawOffset();
    }

    public ConfigProperty<Integer> fov() {
        return this.camera.fov();
    }

    public ConfigProperty<Integer> smoothing() {
        return this.camera.smoothing();
    }

    public ConfigProperty<Integer> freecamSpeed() {
        return this.freecam.speed();
    }

    public ConfigProperty<Integer> freecamSlowSpeed() {
        return this.freecam.slowSpeed();
    }

    public ConfigProperty<Key> freecamHotkey() {
        return this.hotkeys.freecam();
    }

    public ConfigProperty<Key> thirdPersonHotkey() {
        return this.hotkeys.thirdPerson();
    }

    public ConfigProperty<Key> cinematicHotkey() {
        return this.hotkeys.cinematic();
    }

    @ConfigName("general")
    public static class GeneralSettings extends Config {
        @SwitchSetting
        private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

        @SwitchSetting
        private final ConfigProperty<Boolean> smoothCamera = new ConfigProperty<>(true);

        @SwitchSetting
        private final ConfigProperty<Boolean> collisionEnabled = new ConfigProperty<>(true);

        public ConfigProperty<Boolean> enabled() {
            return this.enabled;
        }

        public ConfigProperty<Boolean> smoothCamera() {
            return this.smoothCamera;
        }

        public ConfigProperty<Boolean> collisionEnabled() {
            return this.collisionEnabled;
        }
    }

    @ConfigName("camera")
    public static class CameraSettings extends Config {
        @SwitchSetting
        private final ConfigProperty<Boolean> thirdPersonEnabled = new ConfigProperty<>(true);

        @SwitchSetting
        private final ConfigProperty<Boolean> cinematicEnabled = new ConfigProperty<>(false);

        @SliderSetting(min = 1, max = 20, steps = 1)
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

        public ConfigProperty<Boolean> thirdPersonEnabled() {
            return this.thirdPersonEnabled;
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
    }

    @ConfigName("freecam")
    public static class FreecamSettings extends Config {
        @SwitchSetting
        private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(false);

        @SliderSetting(min = 1, max = 25, steps = 1)
        private final ConfigProperty<Integer> speed = new ConfigProperty<>(5);

        @SliderSetting(min = 0, max = 20, steps = 1)
        private final ConfigProperty<Integer> slowSpeed = new ConfigProperty<>(1);

        public ConfigProperty<Boolean> enabled() {
            return this.enabled;
        }

        public ConfigProperty<Integer> speed() {
            return this.speed;
        }

        public ConfigProperty<Integer> slowSpeed() {
            return this.slowSpeed;
        }
    }

    @ConfigName("hotkeys")
    public static class HotkeySettings extends Config {
        @KeyBindSetting(acceptMouseButtons = false)
        private final ConfigProperty<Key> freecam = new ConfigProperty<>(Key.F6);

        @KeyBindSetting(acceptMouseButtons = false)
        private final ConfigProperty<Key> thirdPerson = new ConfigProperty<>(Key.F7);

        @KeyBindSetting(acceptMouseButtons = false)
        private final ConfigProperty<Key> cinematic = new ConfigProperty<>(Key.F8);

        public ConfigProperty<Key> freecam() {
            return this.freecam;
        }

        public ConfigProperty<Key> thirdPerson() {
            return this.thirdPerson;
        }

        public ConfigProperty<Key> cinematic() {
            return this.cinematic;
        }
    }
}
