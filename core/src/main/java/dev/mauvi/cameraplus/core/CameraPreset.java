package dev.mauvi.cameraplus.core;

public record CameraPreset(
    String name,
    double xOffset,
    double yOffset,
    double zOffset,
    float yawOffset,
    float pitchOffset,
    float fov
) {
}
