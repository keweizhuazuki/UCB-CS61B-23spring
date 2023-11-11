package lab11.graphs;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        int vx = maze.toX(v); // Convert 1D to 2D (x-coordinate)
        int vy = maze.toY(v); // Convert 1D to 2D (y-coordinate)
        int tx = maze.toX(t); // Target x-coordinate
        int ty = maze.toY(t); // Target y-coordinate

        return Math.abs(vx - tx) + Math.abs(vy - ty); // Manhattan distance
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(
                Comparator.comparingInt(v -> distTo[v] + h(v)));

        pq.add(s);
        marked[s] = true;

        while (!pq.isEmpty() && !targetFound) {
            int v = pq.poll();

            if (v == t) {
                targetFound = true;
                return;
            }

            for (int w : maze.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    pq.add(w);
                    announce();
                }
            }
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}
