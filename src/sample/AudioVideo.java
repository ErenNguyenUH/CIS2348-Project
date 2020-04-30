package sample;
import javafx.beans.property.SimpleStringProperty;

public class AudioVideo extends Asset implements Maintenance {
    SimpleStringProperty req;
    AudioVideo(){}
    AudioVideo(String aid, String aname, String acat,String aloc,  String ause, String stat, String r){

    }
    @Override
    public void setReq(String areq){
        req = new SimpleStringProperty(areq);
    }

    @Override
    public String printMaintenancePlan() {
        return "Audio/Video devides need clean, part replacement.";
    }
}
