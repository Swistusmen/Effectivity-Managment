package com.example.efectivitymanagment;

public class Goal {
    public String desc;
    public Boolean finished;
    public String parent;
    public int id;
    public Goal(String description, String goalParent,String IsFinished, String stepID){
        this.desc=description;
        this.finished=Boolean.parseBoolean(IsFinished);
        this.parent=goalParent;
        this.id=Integer.parseInt(stepID);
    }
}
