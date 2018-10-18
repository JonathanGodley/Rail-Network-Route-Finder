/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * TODO: Your program should contain a header with information about what it does and what the input and output are.
 * TODO: Your program should also contain inline comments and be easy to follow.
 * TODO: cover sheet
 */

// assign1.java
// TODO - insert student numbers
// TODO - finalise readme

// import XML packages

import org.w3c.dom.*;

import javax.xml.parsers.*;
import java.io.*;

public class assign1
{

    //TODO force lower case checks everywhere
    // expected args - java assign1 "xml_file" "station 1" "station 2" criterion
    public static void main(String[] args)
    {

        String mode = "";

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

        // load our data from the file
        Graph graph = loadStations(args[0]);

        // now we check that both of our stations exist in the graph, and find our source station index
        int source      = -1;
        int destination = -1;

        //TODO: make case not matter
        if ((source = graph.findIndex(args[1])) == -1)
        {
            System.err.println("Specified source does not exist");
            System.exit(1);
        }
        else
        {

            if ((destination = graph.findIndex(args[2])) == -1)
            {
                System.err.println("Specified destination does not exist");
                System.exit(1);
            }
        }

        //is this if statement necessary as have already covered both cases above
        if (source == -1 || destination == -1)
        {
            System.err.println("Specified destination or source does not exist");
            System.exit(1);
        }


        // test it

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

            //TODO - Make it check that the root element is Stations, otherwise Formatting Error
            //System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("Station");

            Station[] Stations = new Station[nList.getLength()];

            // create a list of the stations
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

                    Stations[temp] = new Station(eElement.getElementsByTagName("Name").item(0).getTextContent(),
                                                 eElement.getElementsByTagName("Line").item(0).getTextContent());

                }
            }

            Graph graph = new Graph(Stations);

            // second pass to get the edges
            // a second pass is required due to how we're assigning indexes to all the stations.
            // we need a full list of stations with their indexes before we can start resolving edge stations into edge stationID's
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

                    NodeList tnList = eElement.getElementsByTagName("StationEdge");

                    for (int x = 0; x < eElement.getElementsByTagName("StationEdge").getLength(); x++)
                    {

                        Node tNode = tnList.item(x);

                        if (nNode.getNodeType() == Node.ELEMENT_NODE)
                        {
                            Element tElement = (Element) tNode;

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



