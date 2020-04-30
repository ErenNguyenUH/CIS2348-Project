package sample;

import javafx.beans.property.SimpleStringProperty;

public class Asset {

    public SimpleStringProperty  id, name, type, loc, useby, status, req;

    Asset(String aid, String aname, String acat,String aloc,  String ause, String stat, String re){
        id =  new SimpleStringProperty(aid);
        name= new SimpleStringProperty(aname);
        type= new SimpleStringProperty(acat);
        loc =new SimpleStringProperty(aloc);
        useby= new SimpleStringProperty(ause);
        status=new SimpleStringProperty(stat);
        req = new SimpleStringProperty(re);
    }
    Asset(){}
    public void setId(String aid){
        id = new SimpleStringProperty(aid);
    }
    public void setName(String aname){
        name= new SimpleStringProperty(aname);
    }
    public void setType(String acat){
        type= new SimpleStringProperty(acat);
    }
    public void setLoc(String aloc){
        loc =new SimpleStringProperty(aloc);
    }
    public void setUseby(String ause){
        useby= new SimpleStringProperty(ause);
    }
    public void setStatus(String stat){
        status=new SimpleStringProperty(stat);
    }
    public void setReq(String re){
        req = new SimpleStringProperty(re);
    }

    public String getId(){
        return id.get();
    }
    public String getName(){
        return name.get();
    }
    public String getType (){
        return type.get();
    }
    public String getLoc (){
        return loc.get();
    }
    public String getUseby (){
        return useby.get();
    }
    public String getStatus (){
        return status.get();
    }
    public String getReq(){
            return req.get();
    }

    public String line(){
        return getId() +","+getName()+","+getType()+","+getLoc()+","+getUseby()+","+getStatus()+","+getReq();
    }

}
