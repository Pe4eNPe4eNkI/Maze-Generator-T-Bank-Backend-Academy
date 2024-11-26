package backend.academy.maze.other;

public record Node(Point point, int g, int h, int f, backend.academy.maze.other.Node parent)
    implements Comparable<Node> {

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.f, other.f);
    }
}
