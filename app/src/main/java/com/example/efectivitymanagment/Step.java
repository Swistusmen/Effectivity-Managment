package com.example.efectivitymanagment;

public class Step {

    public String desc;
    public Boolean finished;
    private String parent;
    private int id;
    public Step(String description, String goalParent,String IsFinished, String stepID){
        this.desc=description;
        this.finished=Boolean.parseBoolean(IsFinished);
        this.parent=goalParent;
        this.id=Integer.parseInt(stepID);
    }

    public String GetParent(){return parent;}
}
