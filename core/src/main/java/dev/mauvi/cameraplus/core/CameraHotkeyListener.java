package dev.mauvi.cameraplus.core;

import net.labymod.api.client.gui.screen.key.Key;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.input.KeyEvent;
import net.labymod.api.event.client.input.KeyEvent.State;

public final class CameraHotkeyListener {
    private final CameraPlusConfiguration configuration;
    private final CameraController cameraController;

    public CameraHotkeyListener(CameraPlusConfiguration configuration, CameraController cameraController) {
        this.configuration = configuration;
        this.cameraController = cameraController;
    }

    @Subscribe
    public void onKey(KeyEvent event) {
        if (event.isCancelled() || event.state() != State.PRESS || !this.configuration.enabled().get()) {
            return;
        }

        CameraMode targetMode = this.targetMode(event.key());
        if (targetMode == null) {
            return;
        }

        CameraMode nextMode = this.cameraController.mode() == targetMode ? CameraMode.NORMAL : targetMode;
        this.cameraController.setMode(nextMode);
        this.configuration.freecamEnabled().set(nextMode == CameraMode.FREECAM);
        this.configuration.thirdPersonEnabled().set(nextMode == CameraMode.THIRD_PERSON);
        this.configuration.cinematicEnabled().set(nextMode == CameraMode.CINEMATIC);
    }

    private CameraMode targetMode(Key pressedKey) {
        if (this.matches(pressedKey, this.configuration.freecamHotkey().get())) {
            return CameraMode.FREECAM;
        }
        if (this.matches(pressedKey, this.configuration.thirdPersonHotkey().get())) {
            return CameraMode.THIRD_PERSON;
        }
        if (this.matches(pressedKey, this.configuration.cinematicHotkey().get())) {
            return CameraMode.CINEMATIC;
        }
        return null;
    }

    private boolean matches(Key pressedKey, Key configuredKey) {
        return configuredKey != null && !Key.NONE.equals(configuredKey) && configuredKey.equals(pressedKey);
    }
}
