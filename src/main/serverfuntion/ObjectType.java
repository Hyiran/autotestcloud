package main.serverfuntion;

import java.util.ArrayList;

public class ObjectType {  
	  
    private String type;  
    private ArrayList<SubObjectType> subObjects;  
    public String getType() {  
        return type;  
    }  
    public void setType(String type) {  
        this.type = type;  
    }  
    public ArrayList<SubObjectType> getSubObjects() {  
        return subObjects;  
    }  
    public void setSubObjects(ArrayList<SubObjectType> subObjects) {  
        this.subObjects = subObjects;  
    }   
}  