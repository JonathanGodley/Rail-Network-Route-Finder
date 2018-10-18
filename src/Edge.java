/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class Edge //extends Station
{
    private int source;
    private int destination;
    private int duration;
 
    //Overloaded constructor 
    public Edge(int esource, int edest, int eduration){
        this.source = esource;
        this.destination = edest;
        this.duration = eduration;
    } 

    public int get_source(){
        return this.source;
    }

    public void set_duration(int duration){
        this.duration = duration;}
    
    public int get_destination(){
        return this.destination;
    }

    
    public int get_duration(){
        return this.duration;
    }
}
