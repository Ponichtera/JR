package gameArkanoid;

public abstract class BaseObject {
    double x;
    double y;
    double radius;

    BaseObject(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    abstract void draw(Canvas canvas);
    abstract void move();

    double getY() {
        return y;
    }

    void checkBorders(double minX, double maxX, double minY, double maxY) {
        if (x < minX) x = minX;
        if (x > maxX) x = maxX;
        if (y < minY) y = minY;
        if (y > maxY) y = maxY;
    }

    boolean isIntersec(BaseObject o) {
        double dx = x - o.x;
        double dy = y - o.y;
        double destination = Math.sqrt(dx * dx + dy * dy);
        double destination2 = Math.max(radius, o.radius);
        return destination <= destination2;
    }
}
