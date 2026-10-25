package dev.mauvi.cameraplus.core;

import java.util.ArrayList;
import java.util.List;

public final class CameraController {
    private final CameraPlusConfiguration configuration;
    private final CollisionResolver collisionResolver;
    private final ZoomController zoomController;
    private final CinematicTimeline cinematicTimeline;
    private final List<CameraPreset> presets = new ArrayList<>();
    private final CameraState current = new CameraState();
    private final CameraState original = new CameraState();
    private CameraMode mode = CameraMode.NORMAL;

    public CameraController(CameraPlusConfiguration configuration, CollisionResolver collisionResolver) {
        this.configuration = configuration;
        this.collisionResolver = collisionResolver;
        this.zoomController = new ZoomController(configuration);
        this.cinematicTimeline = new CinematicTimeline(6.0D);
        this.presets.add(new CameraPreset("Default", 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, 90.0F));
        this.presets.add(new CameraPreset("Third Person", 6.0D, 0.0D, 0.0D, 0.0F, 0.0F, 90.0F));
        this.presets.add(new CameraPreset("Cinematic", 10.0D, 2.0D, 0.0D, 0.0F, -10.0F, 80.0F));
        this.cinematicTimeline.addDefaultFrames();
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

    public void setMode(CameraMode nextMode) {
        this.mode = nextMode == null ? CameraMode.NORMAL : nextMode;
        this.current.copyFrom(this.original);

        if (this.mode == CameraMode.CINEMATIC) {
            this.cinematicTimeline.start();
        } else {
            this.cinematicTimeline.stop();
        }
    }

    public void activateFreecam() {
        this.setMode(CameraMode.FREECAM);
    }

    public void activateThirdPerson() {
        this.setMode(CameraMode.THIRD_PERSON);
    }

    public void activateCinematic() {
        this.setMode(CameraMode.CINEMATIC);
    }

    public void reset() {
        this.mode = CameraMode.NORMAL;
        this.cinematicTimeline.stop();
        this.zoomController.reset();
        this.current.copyFrom(this.original);
    }

    public void tick() {
        this.zoomController.update();

        switch (this.mode) {
            case FREECAM -> this.updateFreecam();
            case THIRD_PERSON -> this.updateThirdPerson();
            case CINEMATIC -> this.updateCinematic();
            default -> {
            }
        }
    }

    private void updateFreecam() {
        double speed = this.configuration.freecamSpeed().get();
        double slowSpeed = this.configuration.freecamSlowSpeed().get();
        double step = Math.max(0.1D, speed * 0.2D + slowSpeed * 0.05D);

        this.current.x(this.current.x() + step * 0.5D);
        this.current.z(this.current.z() + step * 0.25D);
        this.current.y(this.current.y() + 0.05D);

        CameraState resolved = this.collisionResolver.resolve(this.current, this.original);
        this.current.copyFrom(resolved);
    }

    private void updateThirdPerson() {
        double distance = this.configuration.cameraDistance().get();
        double height = this.configuration.cameraHeight().get();
        double yawOffset = this.configuration.cameraYawOffset().get();
        double pitchOffset = this.configuration.cameraPitchOffset().get();

        this.current.x(this.original.x() + distance * 0.25D);
        this.current.y(this.original.y() + height);
        this.current.z(this.original.z() - distance);
        this.current.yaw((float) (this.original.yaw() + yawOffset));
        this.current.pitch((float) (this.original.pitch() + pitchOffset));
        this.current.fov((float) this.zoomController.effectiveFov(this.configuration.fov().get()));
    }

    private void updateCinematic() {
        CameraPreset preset = this.cinematicTimeline.sample(0.016D);
        double eased = 0.5D + Math.sin(System.currentTimeMillis() * 0.0008D) * 0.5D;

        this.current.x(this.original.x() + preset.xOffset() * eased);
        this.current.y(this.original.y() + preset.yOffset());
        this.current.z(this.original.z() + preset.zOffset() * eased);
        this.current.pitch(this.original.pitch() + preset.pitchOffset());
        this.current.fov(preset.fov());
    }

    public void applyPreset(String name) {
        if (name == null || name.isBlank()) {
            return;
        }

        this.presets.stream()
            .filter(preset -> preset.name().equalsIgnoreCase(name))
            .findFirst()
            .ifPresent(preset -> {
                this.current.x(this.original.x() + preset.xOffset());
                this.current.y(this.original.y() + preset.yOffset());
                this.current.z(this.original.z() + preset.zOffset());
                this.current.yaw(this.original.yaw() + preset.yawOffset());
                this.current.pitch(this.original.pitch() + preset.pitchOffset());
                this.current.fov(preset.fov());
            });
    }

    public void setZoomLevel(float zoomLevel) {
        this.zoomController.setZoom(zoomLevel);
    }

    public void increaseZoom() {
        this.zoomController.increase();
    }

    public void decreaseZoom() {
        this.zoomController.decrease();
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

    public ZoomController zoomController() {
        return this.zoomController;
    }

    public CinematicTimeline cinematicTimeline() {
        return this.cinematicTimeline;
    }
}
