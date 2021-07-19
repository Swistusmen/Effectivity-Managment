package com.example.efectivitymanagment;

public class Step {

    public String desc;
    public Boolean finished;
    private String parent;
    public Step(String description, String goalParent,String IsFinished){
        this.desc=description;
        this.finished=Boolean.parseBoolean(IsFinished);
        this.parent=goalParent;
    }


    public String GetParent(){return parent;}
}
