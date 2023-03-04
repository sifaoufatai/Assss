package test;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;


public class Query {







        public  Document getNodesViaOverpassNodeForItineraire( Double nodeId) throws IOException, ParserConfigurationException, SAXException
        {
            String hostname = "http://www.overpass-api.de/api/interpreter";
           /* String queryString = "way\r\n"
                    + "   [highway]\r\n"
                    + "  [area!=yes]\r\n"
                    + " (around:500,  43.9421466 , 4.8047645 , 43.9106016, 4.8905776);\r\n"
                    + "(._;>;);\r\n"
                    + "out;";*/

        String queryString = "node(" + nodeId + ")->.center;\n"
                + "way(bn.center)->.ways;\n"
                + "(\n"
                + "  node(w.ways);\n"
                + "  <;\n"
                + ");\n"
                + "out;";
       /* String queryString = "node(id)->.center;\n"
                + "way(bn.center)->.ways;\n"
                + "(\n"
                + "  node(w.ways);\n"
                + "  <;\n"
                + ");\n"
                + "out;";**/

            URL osm = new URL(hostname);
            HttpURLConnection connection = (HttpURLConnection) osm.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            DataOutputStream printout = new DataOutputStream(connection.getOutputStream());
            printout.writeBytes("data=" + URLEncoder.encode(queryString, "utf-8"));
            printout.flush();
            printout.close();

            DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
            return docBuilder.parse(connection.getInputStream());
        }


        public  void searchNodeForItineraire(Double nodeid) throws IOException, ParserConfigurationException, SAXException, TransformerException
        {
            String fic = String.valueOf(nodeid);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(getNodesViaOverpassNodeForItineraire(nodeid));
            StreamResult streamResult =  new StreamResult(new File("Listenoeud"+fic+".xml"));
            // StreamResult streamResult =  new StreamResult(new File(c));
            transformer.transform(source, streamResult);
        }
        public ArrayList<Node> getNodes(String fichier) throws IOException {
            ArrayList<Node> list = new ArrayList<>();
            File file = new File(fichier);
            InputStreamReader input;
            try (FileInputStream fileStream = new FileInputStream(file)) {
                input = new InputStreamReader(fileStream);

                BufferedReader reader = new BufferedReader(input);

                String line;
                String id = null;
                String maLat = null;
                String maLon = null;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("<node")) {
                        String[] pass2 = line.split("id=\"");
                        String idNode1 = pass2[1];
                        String[] idNode = idNode1.split("\" lat=\"");
                        String[] latitude = idNode[1].split("\" lon=\"");

                        String[] longitude = latitude[1].split("\"");


                        id = idNode[0];
                        maLat = latitude[0];
                        maLon = longitude[0];
                        Node node = new Node(Double.parseDouble(maLon), Double.parseDouble(maLat), Double.parseDouble(id));
                        list.add(node);
                    }


                }
                reader.close();
            }
            return list;

            }
    // public ArrayList<Node> getNodes(ArrayList<Node>list, String file){return 0;}











        public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException, SAXException {
   Query q= new Query();
     ArrayList<Node> listt;ArrayList<Node> list;

        listt=q.getNodes("NodeItineraire.fxml");
        Node n1 = new Node(19124327);
     list=   n1.searchNeigbor();
           /* //for(Node n:list ) System.out.println(n.getId());
            for(Node n:listt ) System.out.println(n.getId());
            System.out.println(listt.contains(n1));
            Node start = new Node(122655.0);
            Node end = new Node(10700393998.0);
**/

        }



    }


