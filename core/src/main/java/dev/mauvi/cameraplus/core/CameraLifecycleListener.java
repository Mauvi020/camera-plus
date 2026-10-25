package dev.mauvi.cameraplus.core;

public class CameraLifecycleListener {
    private final CameraController cameraController;

    public CameraLifecycleListener(CameraController cameraController) {
        this.cameraController = cameraController;
    }

    public void onShutdown() {
        if (this.cameraController != null) {
            this.cameraController.reset();
        }
    }
}
