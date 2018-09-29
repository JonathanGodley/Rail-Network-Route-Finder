// assign1.java

// import XML packages
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class assign1
{

    public static void main(String[] args)
    {
        //TODO - add args verification (JG: I'll do this one, i've got it pre-written from another assignment pretty much)
	    //TODO - Grab filepath from args (JG: I'll do this one too)
        loadStations("RailNetwork.xml");
    }

    public static void loadStations(String path)
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

            for (int temp = 0; temp < nList.getLength(); temp++)
            {
                Node nNode = nList.item(temp);
                // TODO - add verification that the current element is called Station, otherwise formatting error
                //System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) nNode;
                    //TODO - Add Formatting Verification, output the rest of the elements - including loops where necessary
                    System.out.println("Station Name: " + eElement.getElementsByTagName("Name").item(0).getTextContent());
                }
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}
