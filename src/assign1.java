/*
 * assign1.java
 * COMP2230 - Assignment 1
 * Jonathan Godley - c3188072
 * Tamara Wold - c3088810
 * Last Modified 18/10/2018
 *
 * Description:
 *
 * Input: java assign1 <xml_file> "station 1" "station 2" <time|changes>
 * Output: the quickest route between station 1 and station 2, according to the criteria the user selects
 *
 * TODO: cover sheet
 */

import org.w3c.dom.*;

import javax.xml.parsers.*;
import java.io.*;

public class assign1
{
    /**
     * Expected args - java assign1 "xml_file" "station 1" "station 2" criterion
     * @param args 
     */
    public static void main(String[] args)
    {

        String mode = "";

        // check if args[] is valid
        if (args.length != 4)
        {
            System.err.println("Usage: java assign1 <xml_file> \"station 1\" \"station 2\" <time|changes>");
            System.exit(1);
        }
        else if (args[3].equals("time") || args[3].equals("changes"))
        {
            mode = args[3];
        }
        else
        {
            System.err.println("Usage: java assign1 <xml_file> <station 1> <station 2> <time|changes>");
            System.exit(1);
        }

        // load station and edge data from xml file
        Graph graph = loadStations(args[0]);

        // now we check that both of our stations exist in the graph, and find our source station index
        int source      = -1;
        int destination = -1;

        if ((source = graph.findIndex(args[1])) == -1)
        {
            System.err.println("Specified source does not exist");
            System.exit(1);
        }
        else if ((destination = graph.findIndex(args[2])) == -1)
            {
                System.err.println("Specified destination does not exist");
                System.exit(1);
            }

        
        if (mode.equals("time"))
        {
            graph.getShortestTime(source, destination);
        }
        else if (mode.equals("changes"))
        {
            graph.getLeastChanges(source, destination);
        }

        System.exit(0);
    }

    /**
     * Extracts stations and edges from the input xml file and makes them objects
     * @param path
     * @return 
     */
    public static Graph loadStations(String path)
    {
        try
        {
            File inputFile = new File(path);

            // create a document builder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder        builder = factory.newDocumentBuilder();
            Document               doc     = builder.parse(inputFile);
            doc.getDocumentElement().normalize();

            if (!doc.getDocumentElement().getNodeName().toLowerCase().equals("Stations".toLowerCase()))
            {
                System.err.println("Error! Malformed Input File!");
                System.exit(1);
                return null;
            }

            NodeList nList = doc.getElementsByTagName("Station");

            Station[] Stations = new Station[nList.getLength()];

            // create a list of the stations
            for (int temp = 0; temp < nList.getLength(); temp++)
            {
                Node nNode = nList.item(temp);
                
                if (!nNode.getNodeName().toLowerCase().equals("Station".toLowerCase()))
                {
                    System.err.println("Error! Malformed Input File!");
                    System.exit(1);
                    return null;
                }

                if (nNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) nNode;

                    if (eElement.getElementsByTagName("Name").item(0).getTextContent() == null ||
                        eElement.getElementsByTagName("Line").item(0).getTextContent() == null)
                    {
                        System.err.println("Error! Malformed Input File!");
                        System.exit(1);
                        return null;
                    }

                    {
                        Stations[temp] = new Station(eElement.getElementsByTagName("Name").item(0).getTextContent(),
                                                     eElement.getElementsByTagName("Line").item(0).getTextContent());
                    }
                }
            }

            Graph graph = new Graph(Stations);

            // second pass to get the edges
            // a second pass is required due to how we're assigning indexes to all the stations.
            // we need a full list of stations compiled before we can start resolving stations into station ID's
            for (int temp = 0; temp < nList.getLength(); temp++)
            {
                Node nNode = nList.item(temp);
                if (!nNode.getNodeName().toLowerCase().equals("Station".toLowerCase()))
                {
                    System.err.println("Error! Malformed Input File!");
                    System.exit(1);
                    return null;
                }

                if (nNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) nNode;


                    NodeList tnList = eElement.getElementsByTagName("StationEdge");

                    for (int x = 0; x < eElement.getElementsByTagName("StationEdge").getLength(); x++)
                    {

                        Node tNode = tnList.item(x);

                        if (!tNode.getNodeName().toLowerCase().equals("StationEdge".toLowerCase()))
                        {
                            System.err.println("Error! Malformed Input File!");
                            System.exit(1);
                            return null;
                        }

                        if (nNode.getNodeType() == Node.ELEMENT_NODE)
                        {
                            Element tElement = (Element) tNode;

                            if (tElement.getElementsByTagName("Name").item(0).getTextContent() == null ||
                                tElement.getElementsByTagName("Line").item(0).getTextContent() == null ||
                                tElement.getElementsByTagName("Duration").item(0).getTextContent() == null)
                            {
                                System.err.println("Error! Malformed Input File!");
                                System.exit(1);
                                return null;
                            }

                            String destination        = tElement.getElementsByTagName("Name").item(0).getTextContent();
                            int    destinationStation = -1;
                            String line               = tElement.getElementsByTagName("Line").item(0).getTextContent();

                            for (int found = 0, i = 0; i < nList.getLength() && found < 1; i++)
                            {


                                if (destination.equals(Stations[i].get_name()) && line.equals(Stations[i].get_line()))
                                {
                                    destinationStation = i;
                                    found++;
                                }

                            }

                            graph.addEdge(temp, destinationStation, Integer.parseInt(
                                    tElement.getElementsByTagName("Duration").item(0).getTextContent()));
                        }
                    }
                }
            }
            return graph;

        }
        catch (FileNotFoundException e)
        {
            System.err.println("Error! Input File not Found!");
            System.err.println("Usage: java assign1 <xml_file> \"station 1\" \"station 2\" <time|changes>");
            System.exit(1);
            return null;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }
}