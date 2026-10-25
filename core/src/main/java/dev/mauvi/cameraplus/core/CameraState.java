package dev.mauvi.cameraplus.core;

public final class CameraState {
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;
    private float fov;

    public CameraState() {
    }

    public CameraState(double x, double y, double z, float yaw, float pitch, float fov) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.fov = fov;
    }

    public double x() {
        return x;
    }

    public void x(double x) {
        this.x = x;
    }

    public double y() {
        return y;
    }

    public void y(double y) {
        this.y = y;
    }

    public double z() {
        return z;
    }

    public void z(double z) {
        this.z = z;
    }

    public float yaw() {
        return yaw;
    }

    public void yaw(float yaw) {
        this.yaw = yaw;
    }

    public float pitch() {
        return pitch;
    }

    public void pitch(float pitch) {
        this.pitch = pitch;
    }

    public float fov() {
        return fov;
    }

    public void fov(float fov) {
        this.fov = fov;
    }

    public void copyFrom(CameraState other) {
        if (other == null) {
            return;
        }
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
        this.yaw = other.yaw;
        this.pitch = other.pitch;
        this.fov = other.fov;
    }
}
