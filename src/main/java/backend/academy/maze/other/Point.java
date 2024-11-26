package backend.academy.maze.other;

import java.util.Objects;

public record Point(int x, int y) {

    // Метод для добавления направлений к текущей точке
    public Point move(Point direction) {
        return new Point(this.x + direction.x, this.y + direction.y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Point other)) {
            return false;
        }
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}
