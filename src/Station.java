/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.LinkedList;

public class Station{ 

    private String name; 
    private String line;
    private int index;


    //Overloaded constructor 
    public Station(String sname, String sline, int index){
        this.name = sname; 
        this.line = sline;
        this.index = index;
    }
 
    public String get_name(){ 
        return this.name; 
    }

    public int getIndex()
    {
        return index;
    }

    public void setIndex(int index)
    {
        this.index = index;
    }
 
    public String get_line(){ 
        return this.line; 
    }

    @Override public String toString()
    {


        return "Station: "+name+" on line: "+line+" with index: "+index;
    }

}