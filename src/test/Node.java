package test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.util.*;

import static test.Query.getNodes;

public class Node  implements  Comparable<Node> {
    private Double x;
    private Double y;
    private long id ;
    private List<Node> neighbors;
    private Map<Node, Integer> costs;
    private int gScore;

    public Node(Double x, Double y, long id, List<Node> neighbors, Map<Node, Integer> costs, int gScore) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.neighbors = neighbors;
        this.costs = costs;
        this.gScore = 0;
    }
public Node(){


}
    public Node(Double x, Double y, long id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }


    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Node> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<Node> neighbors) {
        this.neighbors = neighbors;
    }

    public Map<Node, Integer> getCosts() {
        return costs;
    }

    public void setCosts(Map<Node, Integer> costs) {
        this.costs = costs;
    }

    public int getgScore() {
        return gScore;
    }

    public void setgScore(int gScore) {
        this.gScore = gScore;
    }

    public void addNeighbor(Node neighbor, int cost) {
        neighbors.add(neighbor);
        costs.put(neighbor, cost);
    }

   /** public List<Node> getNeighbors() throws IOException, ParserConfigurationException, TransformerException, SAXException {


        ArrayList<Node> list1 = new ArrayList<>();
        ArrayList<Node> list2 = new ArrayList<>();
        ArrayList list3 = new ArrayList();
        getNodes(list1, new File("NodeItineraire.fxml"));

        for(Node n : list1) {


            list2 = n.searchNeigbor();

            list2.retainAll(list2);


        }

        return neighbors;
    }*/

    public int getCost(Node neighbor) {
        return costs.get(neighbor);
    }



    public int getGScore() {
        return gScore;
    }

    public void setGScore(int gScore) {
        this.gScore = gScore;
    }



    @Override
    public String toString() {
        String s = String.valueOf((this.getId() +    this.getX()   +  this.getY()));
        String s1 = s;
        return s1;
    }

    public ArrayList<Node> searchNeigbor() throws IOException, ParserConfigurationException, TransformerException, SAXException {
        ArrayList<Node> list = new ArrayList<>();

        Query q = new Query();
        String c ="Listevoisin.fxml";

        q.searchNodeForItineraire(this.id,"Listeneighbor.fxml");

        File file = new File("Listeneighbor.fxml");
        return getNodes(list, file);

    }


 /*   public boolean equals(Object a) {
        if(a.getClass()!=getClass() || a==null) return false ;

        Object  other =(Node) a;
        return a.id ==other.id;
    }*/


    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Node other = (Node) obj;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Node o) {
        return 0;
    }
}

