package hw4.puzzle;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.MinPQ;

public class Solver {

    private class SearchNode implements Comparable<SearchNode> {
        private WorldState state;
        private int moves;
        private SearchNode pre;

        public SearchNode(WorldState s, int m, SearchNode p) {
            state = s;
            moves = m;
            pre = p;
        }

        public WorldState getState() {
            return state;
        }

        public int getMoves() {
            return moves;
        }

        public SearchNode getPre() {
            return pre;
        }

        @Override
        public int compareTo(SearchNode o) {
            return this.moves + this.state.estimatedDistanceToGoal() - o.moves - o.state.estimatedDistanceToGoal();
        }

    }

    private List<WorldState> bestSolution;
    private int totMoves;

    private void getAnswer(SearchNode goal) {
        totMoves = goal.moves;
        bestSolution = new ArrayList<>();
        SearchNode p = goal;
        while (p != null) {
            bestSolution.add(p.state);
            p = p.pre;
        }
    }

    private MinPQ<SearchNode> openNodes = new MinPQ<>();

    public Solver(WorldState initial) {
        openNodes.insert(new SearchNode(initial, 0, null));
        while (true) {
            SearchNode min = openNodes.delMin();
            if (min.getState().isGoal()) {
                getAnswer(min);
                break;
            } else {
                for (WorldState neighbour : min.state.neighbors()) {
                    if (min.pre == null || !neighbour.equals(min.pre.state)) {
                        openNodes.insert(new SearchNode(neighbour, min.moves + 1, min));
                    }
                }
            }
        }
    }

    public int moves() {
        return totMoves;

    }

    public Iterable<WorldState> solution() {
        return bestSolution;
    }
}
