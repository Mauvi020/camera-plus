package dev.mauvi.cameraplus.core;

import java.util.ArrayList;
import java.util.List;

public final class CinematicTimeline {
    private final List<CameraPreset> frames = new ArrayList<>();
    private final double durationSeconds;
    private double elapsedSeconds;
    private boolean running;

    public CinematicTimeline(double durationSeconds) {
        this.durationSeconds = Math.max(0.1D, durationSeconds);
    }

    public void addFrame(CameraPreset frame) {
        this.frames.add(frame);
    }

    public void start() {
        this.running = true;
        this.elapsedSeconds = 0.0D;
    }

    public void pause() {
        this.running = false;
    }

    public void resume() {
        this.running = true;
    }

    public void stop() {
        this.running = false;
        this.elapsedSeconds = 0.0D;
    }

    public boolean isRunning() {
        return this.running;
    }

    public CameraPreset sample(double deltaSeconds) {
        if (!this.running) {
            return this.frames.isEmpty() ? new CameraPreset("Idle", 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, 90.0F) : this.frames.getLast();
        }

        this.elapsedSeconds += deltaSeconds;
        double normalized = Math.min(1.0D, this.elapsedSeconds / this.durationSeconds);
        int index = Math.min(this.frames.size() - 1, Math.max(0, (int) (normalized * Math.max(1, this.frames.size() - 1))));
        return this.frames.get(index);
    }

    public double durationSeconds() {
        return this.durationSeconds;
    }
}
