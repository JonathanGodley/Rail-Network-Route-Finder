/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class Station{ 

    private String name; 
    private String line;

    //Overloaded constructor 
    public Station(String sname, String sline){
        this.name = sname; 
        this.line = sline;
    }
 
    public String get_name(){ 
        return this.name; 
    }

 
    public String get_line(){ 
        return this.line; 
    }

}