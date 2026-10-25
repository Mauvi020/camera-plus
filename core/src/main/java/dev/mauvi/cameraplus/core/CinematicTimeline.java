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

    public void addDefaultFrames() {
        this.frames.clear();
        this.frames.add(new CameraPreset("Intro", 0.0D, 0.5D, 0.0D, 0.0F, -8.0F, 80.0F));
        this.frames.add(new CameraPreset("Orbit", 4.0D, 1.5D, -2.0D, 18.0F, -12.0F, 74.0F));
        this.frames.add(new CameraPreset("Reveal", 8.0D, 2.5D, -5.0D, 32.0F, -20.0F, 70.0F));
        this.frames.add(new CameraPreset("End", 10.0D, 3.0D, -7.0D, 45.0F, -26.0F, 68.0F));
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
        if (this.frames.isEmpty()) {
            return new CameraPreset("Idle", 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, 90.0F);
        }

        if (!this.running) {
            return this.frames.get(this.frames.size() - 1);
        }

        this.elapsedSeconds += deltaSeconds;
        double normalized = Math.min(1.0D, this.elapsedSeconds / this.durationSeconds);
        if (this.frames.size() == 1) {
            return this.frames.getFirst();
        }

        int index = Math.min(this.frames.size() - 1, Math.max(0, (int) (normalized * (this.frames.size() - 1))));
        return this.frames.get(index);
    }

    public double durationSeconds() {
        return this.durationSeconds;
    }
}
