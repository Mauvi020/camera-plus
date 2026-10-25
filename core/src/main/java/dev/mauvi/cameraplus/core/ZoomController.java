package dev.mauvi.cameraplus.core;

public final class ZoomController {
    private final CameraPlusConfiguration configuration;
    private float currentZoom = 1.0F;
    private float targetZoom = 1.0F;

    public ZoomController(CameraPlusConfiguration configuration) {
        this.configuration = configuration;
    }

    public float currentZoom() {
        return this.currentZoom;
    }

    public void setZoom(float zoomLevel) {
        this.targetZoom = Math.max(0.25F, Math.min(2.5F, zoomLevel));
    }

    public void increase() {
        this.setZoom(this.targetZoom + 0.1F);
    }

    public void decrease() {
        this.setZoom(this.targetZoom - 0.1F);
    }

    public void update() {
        float smoothing = 0.18F + (this.configuration.smoothing().get() / 100.0F);
        this.currentZoom += (this.targetZoom - this.currentZoom) * smoothing;
    }

    public float effectiveFov(float baseFov) {
        float min = Math.max(30.0F, Math.min(140.0F, this.configuration.fov().get()));
        float scaled = baseFov * this.currentZoom;
        return Math.max(min, Math.min(baseFov * 1.5F, scaled));
    }

    public void reset() {
        this.currentZoom = 1.0F;
        this.targetZoom = 1.0F;
    }
}
