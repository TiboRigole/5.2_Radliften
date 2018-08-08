import java.util.*;

public class DijkstraAlgoritme {

    private final ArrayList<Verdiep> nodes;
    private final ArrayList<Pijl> edges;
    private HashSet<Verdiep> settledNodes;
    private HashSet<Verdiep> unsettledNodes;
    private HashMap<Verdiep, Verdiep> predecessors;
    private HashMap<Verdiep,Integer> distance;

    public DijkstraAlgoritme(Graaf graaf){
        this.nodes = new ArrayList<Verdiep>(graaf.getVerdiepen());
        this.edges = new ArrayList<Pijl>(graaf.getPijlen());
    }

    public void execute(Verdiep bronVerdiep){
        settledNodes = new HashSet<Verdiep>();
        unsettledNodes = new HashSet<Verdiep>();
        distance = new HashMap<Verdiep, Integer>();
        predecessors = new HashMap<Verdiep, Verdiep>();

        distance.put(bronVerdiep,0);
        unsettledNodes.add(bronVerdiep);

        while(unsettledNodes.size() >0){
            Verdiep node = getMinimum(unsettledNodes);
            settledNodes.add(node);
            unsettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(Verdiep node) {
        ArrayList<Verdiep> adjacentNodes = getNeighbors(node);
        for (Verdiep target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unsettledNodes.add(target);
            }
        }

    }

    private int getDistance(Verdiep node, Verdiep target) {
        for (Pijl edge : edges) {
            if (edge.getVan().equals(node)
                    && edge.getNaar().equals(target)) {
                return 1; //gewicht is altijd 1
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private ArrayList<Verdiep> getNeighbors(Verdiep node) {
        ArrayList<Verdiep> neighbors = new ArrayList<Verdiep>();
        for (Pijl edge : edges) {
            if (edge.getVan().equals(node)
                    && !isSettled(edge.getNaar())) {
                neighbors.add(edge.getNaar());
            }
        }
        return neighbors;
    }

    private boolean isSettled(Verdiep vertex) {
        return settledNodes.contains(vertex);
    }

    private Verdiep getMinimum(HashSet<Verdiep> vertexes) {
        Verdiep minimum = null;
        for (Verdiep vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    private int getShortestDistance(Verdiep destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }


    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    public LinkedList<Verdiep> getPath(Verdiep target) {
        LinkedList<Verdiep> path = new LinkedList<Verdiep>();
        Verdiep step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order

        Collections.reverse(path);
        return path;
    }




}
