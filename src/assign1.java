// assign1.java
// TODO - insert student numbers

// import XML packages
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class assign1
{

    // expected args - java assign1 "xml_file" "station 1" "station 2" criterion
    public static void main(String[] args)
    {

        if (args.length != 4) {
            System.err.println("Usage: java assign1 <xml_file> <station 1> <station 2> <time|changes>");
            System.exit(1);
        }
        else if (args[3].equals("time") || args[3].equals("changes"))
        {
            String mode = args[3];
        }
        else
        {
            System.err.println("Usage: java assign1 <xml_file> <station 1> <station 2> <time|changes>");
            System.exit(1);
        }


	    //TODO - Grab filepath from args (JG: I'll do this one too)
        Station[] Stations = loadStations("RailNetwork.xml");

        System.exit(0);
    }

    public static Station[] loadStations(String path)
    {

        try
        {

            File inputFile = new File(path);

            // create a document builder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputFile);
            doc.getDocumentElement().normalize();

            //TODO - Make it check that the root element is Stations, otherwise Formatting Error
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("Station");
            System.out.println("----------------------------");

            Station[] Stations = new Station[nList.getLength()];

            for (int temp = 0; temp < nList.getLength(); temp++)
            {
                Node nNode = nList.item(temp);
                // TODO - add verification that the current element is called Station, otherwise formatting error
                //System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) nNode;
                    //TODO - Add Formatting Verification, output the rest of the elements - including loops where necessary
                    // TODO - on that note, these two are REQUIRED, station edges aren't necessary as long as formatting is kept.
                    System.out.println("Station Name: " + eElement.getElementsByTagName("Name").item(0).getTextContent());
                    System.out.println("Line Name: " + eElement.getElementsByTagName("Line").item(0).getTextContent());
                }
            }

            return Stations;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }

}
