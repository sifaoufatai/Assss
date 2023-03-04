


package  test;

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
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;
public class Main {


    public static Document getNodesViaOverpassNodeForItineraire( long nodeId) throws IOException, ParserConfigurationException, SAXException
    {
        String hostname = "http://www.overpass-api.de/api/interpreter";
       String queryString = "way\r\n"
                + "   [highway]\r\n"
                + "  [area!=yes]\r\n"
                + " (around:500,  43.9421466 , 4.8047645 , 43.9106016, 4.8905776);\r\n"
                + "(._;>;);\r\n"
                + "out;";

     /*   String queryString = "node(" + nodeId + ")->.center;\n"
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


    public static void searchNodeForItineraire() throws IOException, ParserConfigurationException, SAXException, TransformerException
    {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(getNodesViaOverpassNodeForItineraire(435));
        StreamResult streamResult =  new StreamResult(new File("NodeItineraire.fxml"));
       // StreamResult streamResult =  new StreamResult(new File(c));
        transformer.transform(source, streamResult);
    }

    public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException, SAXException {

        searchNodeForItineraire();


    }
}