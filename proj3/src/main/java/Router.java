import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class provides a shortestPath method for finding routes between two
 * points
 * on the map. Start by using Dijkstra's, and if your code isn't fast enough for
 * your
 * satisfaction (or the autograder), upgrade your implementation by switching it
 * to A*.
 * Your code will probably not be fast enough to pass the autograder unless you
 * use A*.
 * The difference between A* and Dijkstra's is only a couple of lines of code,
 * and boils
 * down to the priority you use to order your vertices.
 */
public class Router {
    private static GraphDB.Node startNode;
    private static GraphDB.Node endNode;
    private static GraphDB graph;

    private static class SearchNode implements Comparable<SearchNode> {
        public Long id;
        public SearchNode parent;
        public double distanceToStart;
        public double priority;

        public SearchNode(Long id, SearchNode parent, double distanceToStart) {
            this.id = id;
            this.parent = parent;
            this.distanceToStart = distanceToStart;
            this.priority = distanceToStart + distanceToEnd(id);
        }

        @Override
        public int compareTo(SearchNode o) {
            if (this.priority < o.priority) {
                return -1;
            } else if (this.priority > o.priority) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    private static double distanceToEnd(Long id) {
        GraphDB.Node node = graph.nodes.get(id);
        return GraphDB.distance(node.lon, node.lat, endNode.lon, endNode.lat);
    }

    /**
     * Return a List of longs representing the shortest path from the node
     * closest to a start location and the node closest to the destination
     * location.
     * 
     * @param g       The graph to use.
     * @param stlon   The longitude of the start location.
     * @param stlat   The latitude of the start location.
     * @param destlon The longitude of the destination location.
     * @param destlat The latitude of the destination location.
     * @return A list of node id's in the order visited on the shortest path.
     */
    public static List<Long> shortestPath(GraphDB g, double stlon, double stlat,
            double destlon, double destlat) {
        graph = g;
        startNode = graph.nodes.get(g.closest(stlon, stlat));
        endNode = graph.nodes.get(g.closest(destlon, destlat));
        Map<Long, Boolean> marked = new HashMap<>();
        PriorityQueue<SearchNode> pq = new PriorityQueue<>();
        pq.add(new SearchNode(startNode.id, null, 0));
        while (!pq.isEmpty() && !isGoal(pq.peek())) {
            SearchNode node = pq.poll();
            marked.put(node.id, true);
            for (Long adj : graph.adjacent(node.id)) {
                if (!marked.containsKey(adj) || marked.get(adj) == false) {
                    pq.add(new SearchNode(adj, node, node.distanceToStart + distance(g, adj, node.id)));
                }
            }
        }
        SearchNode routes = pq.peek();
        List<Long> path = new ArrayList<>();
        while (routes != null) {
            path.add(routes.id);
            routes = routes.parent;
        }
        Collections.reverse(path);
        return path; // FIXME
    }

    private static double distance(GraphDB graph, Long id1, Long id2) {
        GraphDB.Node v1 = graph.nodes.get(id1);
        GraphDB.Node v2 = graph.nodes.get(id2);
        return GraphDB.distance(v1.lon, v1.lat, v2.lon, v2.lat);
    }

    public static boolean isGoal(SearchNode node) {
        return distanceToEnd(node.id) == 0;
    }

    /**
     * Create the list of directions corresponding to a route on the graph.
     * 
     * @param g     The graph to use.
     * @param route The route to translate into directions. Each element
     *              corresponds to a node from the graph in the route.
     * @return A list of NavigatiionDirection objects corresponding to the input
     *         route.
     */
    public static List<NavigationDirection> routeDirections(GraphDB g, List<Long> route) {
        double distance = 0;
        int relativeDirection = NavigationDirection.START;
        ArrayList<NavigationDirection> navigationList = new ArrayList<>();
        ArrayList<GraphDB.Edge> ways = getWays(g, route);
        if (route.size() == 1) {
            navigationList.add(
                    new NavigationDirection(NavigationDirection.START, ways.get(0).getName(), ways.get(0).getWeight()));
            return navigationList;
        }
        for (int i = 1; i < ways.size(); i++) {
            GraphDB.Edge currentEdge = ways.get(i - 1);
            GraphDB.Edge nextEdge = ways.get(i);

            Long preVertex = route.get(i - 1);
            Long currentVertex = route.get(i);
            Long nextVertex = route.get(i + 1);

            String currentWay = currentEdge.getName();
            String nextWay = nextEdge.getName();

            distance += currentEdge.getWeight();
            if (!currentWay.equals(nextWay)) {
                double preBearing = g.bearing(preVertex, currentVertex);
                double nextBearing = g.bearing(currentVertex, nextVertex);
                navigationList.add(new NavigationDirection(relativeDirection, currentWay, distance));

                relativeDirection = relativeDirection(g, preBearing, nextBearing);
                distance = 0;
            }
            if (i == ways.size() - 1) {
                distance += nextEdge.getWeight();
                navigationList.add(new NavigationDirection(relativeDirection, nextWay, distance));
            }
            return navigationList;
        }
        return null; // FIXME
    }

    public static ArrayList<GraphDB.Edge> getWays(GraphDB g, List<Long> route) {
        ArrayList<GraphDB.Edge> ways = new ArrayList<>();
        for (int i = 0; i < route.size(); i++) {
            Long currentVertex = route.get(i - 1);
            Long nextVertex = route.get(i);
            for (GraphDB.Edge e : g.neighbors(currentVertex)) {
                if (e.other(currentVertex).equals(nextVertex)) {
                    ways.add(e);
                }
            }
        }
        return ways;
    }

    public static int relativeDirection(GraphDB g, double preBearing, double nextBearing) {
        double diff = nextBearing - preBearing;
        double absDiff = Math.abs(diff);
        if (absDiff <= 15) {
            return NavigationDirection.STRAIGHT;
        } else if (absDiff <= 30) {
            if (diff > 0) {
                return NavigationDirection.SLIGHT_RIGHT;
            } else {
                return NavigationDirection.SLIGHT_LEFT;
            }
        } else if (absDiff <= 100) {
            if (diff > 0) {
                return NavigationDirection.RIGHT;
            } else {
                return NavigationDirection.LEFT;
            }
        } else {
            if (diff > 0) {
                return NavigationDirection.SHARP_RIGHT;
            } else {
                return NavigationDirection.SHARP_LEFT;
            }
        }
    }

    /**
     * Class to represent a navigation direction, which consists of 3 attributes:
     * a direction to go, a way, and the distance to travel for.
     */
    public static class NavigationDirection {

        /** Integer constants representing directions. */
        public static final int START = 0;
        public static final int STRAIGHT = 1;
        public static final int SLIGHT_LEFT = 2;
        public static final int SLIGHT_RIGHT = 3;
        public static final int RIGHT = 4;
        public static final int LEFT = 5;
        public static final int SHARP_LEFT = 6;
        public static final int SHARP_RIGHT = 7;

        /** Number of directions supported. */
        public static final int NUM_DIRECTIONS = 8;

        /** A mapping of integer values to directions. */
        public static final String[] DIRECTIONS = new String[NUM_DIRECTIONS];

        /** Default name for an unknown way. */
        public static final String UNKNOWN_ROAD = "unknown road";

        /** Static initializer. */
        static {
            DIRECTIONS[START] = "Start";
            DIRECTIONS[STRAIGHT] = "Go straight";
            DIRECTIONS[SLIGHT_LEFT] = "Slight left";
            DIRECTIONS[SLIGHT_RIGHT] = "Slight right";
            DIRECTIONS[LEFT] = "Turn left";
            DIRECTIONS[RIGHT] = "Turn right";
            DIRECTIONS[SHARP_LEFT] = "Sharp left";
            DIRECTIONS[SHARP_RIGHT] = "Sharp right";
        }

        /** The direction a given NavigationDirection represents. */
        int direction;
        /** The name of the way I represent. */
        String way;
        /** The distance along this way I represent. */
        double distance;

        /**
         * Create a default, anonymous NavigationDirection.
         */
        public NavigationDirection() {
            this.direction = STRAIGHT;
            this.way = UNKNOWN_ROAD;
            this.distance = 0.0;
        }

        public NavigationDirection(int direction, String way, double distance) {
            this.direction = direction;
            this.way = way;
            this.distance = distance;
        }

        public String toString() {
            return String.format("%s on %s and continue for %.3f miles.",
                    DIRECTIONS[direction], way, distance);
        }

        /**
         * Takes the string representation of a navigation direction and converts it
         * into
         * a Navigation Direction object.
         * 
         * @param dirAsString The string representation of the NavigationDirection.
         * @return A NavigationDirection object representing the input string.
         */
        public static NavigationDirection fromString(String dirAsString) {
            String regex = "([a-zA-Z\\s]+) on ([\\w\\s]*) and continue for ([0-9\\.]+) miles\\.";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(dirAsString);
            NavigationDirection nd = new NavigationDirection();
            if (m.matches()) {
                String direction = m.group(1);
                if (direction.equals("Start")) {
                    nd.direction = NavigationDirection.START;
                } else if (direction.equals("Go straight")) {
                    nd.direction = NavigationDirection.STRAIGHT;
                } else if (direction.equals("Slight left")) {
                    nd.direction = NavigationDirection.SLIGHT_LEFT;
                } else if (direction.equals("Slight right")) {
                    nd.direction = NavigationDirection.SLIGHT_RIGHT;
                } else if (direction.equals("Turn right")) {
                    nd.direction = NavigationDirection.RIGHT;
                } else if (direction.equals("Turn left")) {
                    nd.direction = NavigationDirection.LEFT;
                } else if (direction.equals("Sharp left")) {
                    nd.direction = NavigationDirection.SHARP_LEFT;
                } else if (direction.equals("Sharp right")) {
                    nd.direction = NavigationDirection.SHARP_RIGHT;
                } else {
                    return null;
                }

                nd.way = m.group(2);
                try {
                    nd.distance = Double.parseDouble(m.group(3));
                } catch (NumberFormatException e) {
                    return null;
                }
                return nd;
            } else {
                // not a valid nd
                return null;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof NavigationDirection) {
                return direction == ((NavigationDirection) o).direction
                        && way.equals(((NavigationDirection) o).way)
                        && distance == ((NavigationDirection) o).distance;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(direction, way, distance);
        }
    }
}
