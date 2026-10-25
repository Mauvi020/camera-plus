package dev.mauvi.cameraplus.core;

public final class CollisionResolver {

    private final CameraPlusConfiguration configuration;

    public CollisionResolver(CameraPlusConfiguration configuration) {
        this.configuration = configuration;
    }

    public CameraState resolve(CameraState target, CameraState current) {
        if (current == null) {
            return target;
        }

        if (!this.configuration.collisionEnabled().get()) {
            return target;
        }

        CameraState resolved = new CameraState();
        resolved.copyFrom(target);

        double dx = target.x() - current.x();
        double dy = target.y() - current.y();
        double dz = target.z() - current.z();

        if (dx * dx + dy * dy + dz * dz < 0.0001) {
            return resolved;
        }

        final double minimumDistance = 0.1D;
        double length = Math.sqrt(dx * dx + dy * dy + dz * dz);
        if (length < minimumDistance) {
            resolved.x(current.x());
            resolved.y(current.y());
            resolved.z(current.z());
            return resolved;
        }

        double ratio = minimumDistance / length;
        resolved.x(current.x() + dx * ratio);
        resolved.y(current.y() + dy * ratio);
        resolved.z(current.z() + dz * ratio);
        return resolved;
    }
}
