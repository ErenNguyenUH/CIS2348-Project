package sample;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Printers extends Asset implements Maintenance,IPConfiguration{
    SimpleStringProperty req;

    Printers(){}
    Printers(String aid, String aname, String acat,String aloc,  String ause, String stat, String r){

    }

    @Override
    public void setReq(String areq){
        req = new SimpleStringProperty(areq);
    }

    @Override
    public String printIPConfig() {
        return "Servers, printers, and projectors need to get IP addresses from a pool of static IP addresses and configured by the Network people.";
    }
    @Override
    public String printMaintenancePlan() {
        return "Printers need driver software to be updated and needs cleaning, cartridges replacements.";
    }
}
