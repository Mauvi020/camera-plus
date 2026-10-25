package dev.mauvi.cameraplus.core;

import java.util.ArrayList;
import java.util.List;

public final class CameraController {
    private final CameraPlusConfiguration configuration;
    private final CollisionResolver collisionResolver;
    private final List<CameraPreset> presets = new ArrayList<>();
    private final CameraState current = new CameraState();
    private final CameraState original = new CameraState();
    private CameraMode mode = CameraMode.NORMAL;

    public CameraController(CameraPlusConfiguration configuration, CollisionResolver collisionResolver) {
        this.configuration = configuration;
        this.collisionResolver = collisionResolver;
        this.presets.add(new CameraPreset("Default", 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, 90.0F));
        this.presets.add(new CameraPreset("Third Person", 6.0D, 0.0D, 0.0D, 0.0F, 0.0F, 90.0F));
        this.presets.add(new CameraPreset("Cinematic", 10.0D, 2.0D, 0.0D, 0.0F, -10.0F, 80.0F));
    }

    public void saveOriginalState(double x, double y, double z, float yaw, float pitch, float fov) {
        this.original.x(x);
        this.original.y(y);
        this.original.z(z);
        this.original.yaw(yaw);
        this.original.pitch(pitch);
        this.original.fov(fov);
        this.current.copyFrom(this.original);
    }

    public void activateFreecam() {
        this.mode = CameraMode.FREECAM;
        this.current.copyFrom(this.original);
    }

    public void activateThirdPerson() {
        this.mode = CameraMode.THIRD_PERSON;
        this.current.copyFrom(this.original);
    }

    public void activateCinematic() {
        this.mode = CameraMode.CINEMATIC;
        this.current.copyFrom(this.original);
    }

    public void reset() {
        this.mode = CameraMode.NORMAL;
        this.current.copyFrom(this.original);
    }

    public void tick() {
        switch (this.mode) {
            case FREECAM -> this.updateFreecam();
            case THIRD_PERSON -> this.updateThirdPerson();
            case CINEMATIC -> this.updateCinematic();
            default -> {
            }
        }
    }

    private void updateFreecam() {
        double step = Math.max(0.1D, this.configuration.freecamSpeed().get() * 0.2D);
        this.current.x(this.current.x() + step);
        this.current.z(this.current.z() + step * 0.25D);
        this.current.y(this.current.y() + 0.05D);

        CameraState resolved = this.collisionResolver.resolve(this.current, this.original);
        this.current.copyFrom(resolved);
    }

    private void updateThirdPerson() {
        double distance = this.configuration.cameraDistance().get();
        double height = this.configuration.cameraHeight().get();
        this.current.x(this.original.x() + distance * 0.25D);
        this.current.y(this.original.y() + height);
        this.current.z(this.original.z() - distance);
        this.current.yaw(this.original.yaw() + this.configuration.cameraYawOffset().get());
        this.current.pitch(this.original.pitch() + this.configuration.cameraPitchOffset().get());
        this.current.fov(this.configuration.fov().get());
    }

    private void updateCinematic() {
        CameraPreset preset = this.presets.stream()
            .filter(item -> "Cinematic".equals(item.name()))
            .findFirst()
            .orElse(this.presets.getFirst());

        double eased = 0.5D + Math.sin(System.currentTimeMillis() * 0.001D) * 0.5D;
        this.current.x(this.original.x() + preset.xOffset() * eased);
        this.current.y(this.original.y() + preset.yOffset());
        this.current.z(this.original.z() + preset.zOffset() * eased);
        this.current.pitch(this.original.pitch() + preset.pitchOffset());
        this.current.fov(preset.fov());
    }

    public CameraMode mode() {
        return this.mode;
    }

    public CameraState current() {
        return this.current;
    }

    public CameraState original() {
        return this.original;
    }

    public List<CameraPreset> presets() {
        return this.presets;
    }
}
