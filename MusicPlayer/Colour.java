package MusicPlayer;

import java.util.HashMap;

public class Colour
{


    static HashMap<String,String> hm;
    static 
    {
        hm = new HashMap<>();
        hm.put("cr","\u001B[0m");  // reset to normal white colour
        hm.put("bk","\u001B[30m"); // black
        hm.put("r","\u001B[31m");  // red
        hm.put("g","\u001B[32m");  // green
        hm.put("y","\u001B[93m");  // yellow
        hm.put("b","\u001B[34m");  // blue
        hm.put("bo","\u001B[1m");  // bright
        hm.put("i","\u001B[3m");   // Italic
        hm.put("u","\u001B[4m");   // underscore
        hm.put("w","\u001B[37m");  // white
        hm.put("bb","\u001B[40m");  // black background
        hm.put("bw","\u001B[47m"); // white background
        hm.put("gb","\u001B[42m"); // green background
    } 


    static void ChangeColour(String s)
    {
        for (String s1 : s.split(",")) 
        {
            System.out.print(hm.get(s1));    
        }
    }
}