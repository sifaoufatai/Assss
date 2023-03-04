package test;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Dikstra {
    private Double INFINIADouble = Double.MAX_VALUE;

    public Dikstra() {
    }

   public ArrayList<Node> getNeighbor(Node current) throws IOException, ParserConfigurationException, TransformerException, SAXException {
        ArrayList<Node> nodes = new ArrayList();

                nodes =current.searchNeigbor();
       return nodes;
   }

  /*  public Node getClosestNode(Map<Node, Double> distances, Set<Node> invisited) {
        Node closest = null;
        Double min = this.INFINIADouble;
        Iterator var5 = invisited.iterator();

        while(var5.hasNext()) {
            Node node = (Node)var5.next();

            if ((Double)distances.get(node) < min) {
                min = (Double)distances.get(node);
                closest = node;
            }
        }
        System.out.println(closest.getId());
        System.out.println(min);
        return closest;
    }**/


    public Node getClosestNode(Set<Node> unvisited, Map<Node, Double> distances) {
        Node closest = null;
        Double minDistance = Double.POSITIVE_INFINITY;

        for (Node node : unvisited) {
            Double distance = distances.get(node);
            if (distance < minDistance) {
                minDistance = distance;
                closest = node;
            }
        }

        return closest;
    }


    public double distance(Node start, Node end) {
      //  start.toString();
     //   end.toString();
        double xDistance = Math.abs(start.getX() - end.getX());
        double yDistance = Math.abs(start.getY() - end.getY());
       // double euclideanDistance = Math.sqrt(xDistance * xDistance + yDistance * yDistance);
        double euclideanDistance = xDistance  + yDistance;
        return euclideanDistance;
    }
    public Map<Node, Double> getInitialiseDistances(ArrayList <Node> nodes, Node start){
        Map<Node, Double> distances = new HashMap<>();
        for (Node node : nodes) {
            if (node != start) {
                distances.put(node,Double.POSITIVE_INFINITY);
            }
        }
        distances.put(start, 0.0);
        return distances;

    }
    public Map<Node, Node> shortestpath(ArrayList<Node> nodes, Node start, Node end) throws IOException, ParserConfigurationException, TransformerException, SAXException {
       // Map<Node, Double> distances = new HashMap();
        Map<Node, Double> distances = getInitialiseDistances(nodes, start);
        Map<Node, Node> predecessor = new HashMap();
        Set<Node> invisited = new HashSet(nodes);




        while(!invisited.isEmpty() ) {
            Node current = getClosestNode(invisited, distances);
            invisited.remove(current);
            System.out.println(current.getId()+" voici mes voisin");
            ArrayList<Node> listneigbor= new ArrayList<>();
            listneigbor= getNeighbor(current);
             listneigbor.retainAll(nodes);


            Iterator  var13 = listneigbor.iterator();

            while(var13.hasNext()) {
                Node neighbor = (Node)var13.next();
                System.out.println(neighbor.getId());

                Double alt;
              /** if (distances.get(current) == Double.POSITIVE_INFINITY) {
                    alt = distance(neighbor, current);
                } else {
                    alt = distances.get(current) + distance(neighbor, current);
                }**/
                alt = distances.get(current) + distance(neighbor, current);
               // System.out.println(this.distance(neighbor, current));
               // System.out.println(distances.get(current));

                if (alt < distances.get(neighbor)) {
                    distances.put(neighbor, alt);
                    System.out.println("je suis "+neighbor.getId()+ "mon parrent est" +current.getId()+ "je suis a ce distancee du sommet"+ alt );
                    predecessor.put(neighbor, current);
                }
            }
        }

        return predecessor;
    }

    public ArrayList<Node> reconstructPath(Map<Node, Node> predecessor, Node start) {
        ArrayList path;
        for(path = new ArrayList(); predecessor.containsKey(start); start = (Node)predecessor.get(start)) {
            path.add(start);
        }

        return path;
    }


}
