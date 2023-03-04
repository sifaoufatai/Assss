package test;

import java.util.*;

 class AStra {
   /** public static List<Node> findPath(Node startNode, Node endNode) {
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Set<Node> closedSet = new HashSet<>();
        Map<Node, Node> cameFrom = new HashMap<>();
        Map<Node, Integer> gScore = new HashMap<>();
        Map<Node, Integer> fScore = new HashMap<>();

        gScore.put(startNode, 0);
        fScore.put(startNode, heuristic(startNode, endNode));
        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            Node currentNode = openSet.poll();

            if (currentNode == endNode) {
                return reconstructPath(cameFrom, currentNode);
            }

            closedSet.add(currentNode);

            for (Node neighbor : currentNode.getNeighbors()) {
                if (closedSet.contains(neighbor)) {
                    continue;
                }

                int tentativeGScore = gScore.get(currentNode) + currentNode.getCost(neighbor);

                if (!openSet.contains(neighbor) || tentativeGScore < gScore.get(neighbor)) {
                    cameFrom.put(neighbor, currentNode);
                    gScore.put(neighbor, tentativeGScore);
                    fScore.put(neighbor, tentativeGScore + heuristic(neighbor, endNode));

                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }

        return null; // Si le chemin n'a pas été trouvé
    }

 /*   private static int heuristic(Node startNode, Node endNode) {
        int dx = Math.abs(startNode.getX() - endNode.getX());
        int dy = Math.abs(startNode.getY() - endNode.getY());
        return dx + dy; // Distance de Manhattan
    }**/

    /*private static List<Node> reconstructPath(Map<Node, Node> cameFrom, Node currentNode) {
        List<Node> path = new ArrayList<>();
        path.add(currentNode);

        while (cameFrom.containsKey(currentNode)) {
            currentNode = cameFrom.get(currentNode);
            path.add(0, currentNode);
        }

        return path;
    }*/
}

