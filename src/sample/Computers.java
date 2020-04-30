package sample;

import javafx.beans.property.SimpleStringProperty;

public class Computers extends Asset implements IPConfiguration,Maintenance{

    SimpleStringProperty req;

    Computers(){}
    Computers(String aid, String aname, String acat,String aloc,  String ause, String stat, String r){}

    @Override
    public void setReq(String areq){
        req = new SimpleStringProperty(areq);
    }

    @Override
    public String printIPConfig() {
        return "Computers get their IP address from DHCP servers automatically and are dynamic addresses.";
    }
    @Override
    public String printMaintenancePlan() {
            return "Computers need security updates and Software upgrades.";
    }
}