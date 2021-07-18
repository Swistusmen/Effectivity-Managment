package com.example.efectivitymanagment;

public class Step {
    private Integer id;
    public String desc;
    public Boolean finished;
    private String parent;
    public Step(String stepId,String description, String goalParent, String IsFinished){
        this.id=Integer.parseInt(stepId);
        this.desc=description;
        this.finished=Boolean.parseBoolean(IsFinished);
        this.parent=goalParent;
    }

    public Integer GetId(){return id;}
    public String GetParent(){return parent;}
}
