package sample;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;

public class Controller implements Initializable{

    public Button load;
    public Button add;
    public Button sedit;
    public Button delete;
    public TableView<Asset> mTable;
    public TextField idB;
    public TextField nameB;
    public TextField locB;
    public TextField useB;
    public TextField statusB;
    public TextField typeB;
    public TextField requirement;
    public Label cnum;
    public Label pnum;
    public Label avnum;
    public int compnum=0, printernum=0, audiovideonum=0;
    public Button maintenance;
    public Button ipconfig;
    public Label l1;
    public Label l2;
    public Label l3;

    private ObservableList<Asset> assetlist;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        assetlist = FXCollections.observableArrayList();

        //Set table

        TableColumn idCol = new TableColumn("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<Asset, String>("id"));
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<Asset, String>("name"));
        TableColumn typeCol = new TableColumn("Category");
        typeCol.setCellValueFactory(new PropertyValueFactory<Asset, String>("type"));
        TableColumn locCol = new TableColumn("Location");
        locCol.setCellValueFactory(new PropertyValueFactory<Asset, String>("loc"));
        TableColumn usebyCol = new TableColumn("Use By");
        usebyCol.setCellValueFactory(new PropertyValueFactory<Asset, String>("useby"));
        TableColumn statusCol = new TableColumn("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<Asset, String>("status"));

        TableColumn reqCol = new TableColumn("Device Requirement");
        reqCol.setCellValueFactory(new PropertyValueFactory<Asset, String>("req"));

        mTable.getColumns().addAll(idCol, nameCol, typeCol, locCol,usebyCol,statusCol, reqCol);
        mTable.setItems(assetlist);
        mTable.setEditable(true);

        //add new asset to file

        add.setOnAction(ActionEvent->{
            String id = idB.getText();
            String name = nameB.getText();
            String type = typeB.getText();
            String loc = locB.getText();
            String useby = useB.getText();
            String status = statusB.getText();
            String req = requirement.getText();
            if(type.equalsIgnoreCase("Computer")||type.equalsIgnoreCase("Laptop")||type.equalsIgnoreCase("Desktop")||type.equalsIgnoreCase("Server")){
                compnum++;
                cnum.setText(""+compnum);
                Computers temp = new Computers(id, name, type, loc, useby, status, req);
                assetlist.add(temp);
                WritetoFile.writetoFile(temp.line());
            }
            else if(type.equalsIgnoreCase("Printer")){
                printernum++;
                pnum.setText(""+printernum);
                Printers temp = new Printers(id, name, type, loc, useby, status, req);
                assetlist.add(temp);
                WritetoFile.writetoFile(temp.line());
            }
            else if(type.equalsIgnoreCase("Audio/video")||type.equalsIgnoreCase("Projector")){
                audiovideonum++;
                avnum.setText(""+audiovideonum);
                AudioVideo temp = new AudioVideo(id, name, type, loc, useby, status, req);
                assetlist.add(temp);
                WritetoFile.writetoFile(temp.line());
            }
            else{
                Asset temp = new Asset (id, name, type, loc, useby, status,req);
                assetlist.add(temp);
                WritetoFile.writetoFile(temp.line());
            }


            idB.clear();
            nameB.clear();
            typeB.clear();
            locB.clear();
            useB.clear();
            statusB.clear();
            requirement.clear();
        });

        // load assets from file

        load.setOnAction(ActionEvent->{
            assetlist.clear();
            mTable.refresh();
            compnum=0;
            printernum=0;
            audiovideonum=0;
            String detect =",";
            BufferedReader read;
            try {
                read = new BufferedReader(new FileReader("assets.csv"));
                String temp;
                try{
                while( (temp = read.readLine())!=null ){
                    String[] assets = temp.split(detect,-1);
                    Asset t = new Asset(assets[0], assets[1],assets[2],assets[3],assets[4],assets[5],assets[6]);
                    assetlist.add(t);
                }
                read.close();
                } catch(FileNotFoundException e){
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (Asset t:assetlist){
                String temp = t.getType();
                if(temp.equalsIgnoreCase("Computer")||temp.equalsIgnoreCase("Laptop")||temp.equalsIgnoreCase("Desktop")||temp.equalsIgnoreCase("Server")){
                    compnum++;
                }
                else if(temp.equalsIgnoreCase("Printer")){
                    printernum++;
                }
                else if(temp.equalsIgnoreCase("Audio/video")||temp.equalsIgnoreCase("Projector")){
                    audiovideonum++;
                }
            }
            cnum.setText(""+compnum);
            pnum.setText(""+printernum);
            avnum.setText(""+audiovideonum);
        });


        //Set table elements to editable

        idCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        typeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        locCol.setCellFactory(TextFieldTableCell.forTableColumn());
        usebyCol.setCellFactory(TextFieldTableCell.forTableColumn());
        statusCol.setCellFactory(TextFieldTableCell.forTableColumn());

        idCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Asset, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Asset, String> ce) {
                ((Asset) ce.getTableView().getItems().get(ce.getTablePosition().getRow())).setId(ce.getNewValue());
                mTable.refresh();
            }
        });
        nameCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Asset, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Asset, String> ce) {
                ((Asset) ce.getTableView().getItems().get(ce.getTablePosition().getRow())).setName(ce.getNewValue());
                mTable.refresh();
            }
        });
        typeCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Asset, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Asset, String> ce) {
                ((Asset) ce.getTableView().getItems().get(ce.getTablePosition().getRow())).setType(ce.getNewValue());
                mTable.refresh();
            }
        });
        locCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Asset, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Asset, String> ce) {
                ((Asset) ce.getTableView().getItems().get(ce.getTablePosition().getRow())).setLoc(ce.getNewValue());
                mTable.refresh();
            }
        });
        usebyCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Asset, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Asset, String> ce) {
                ((Asset) ce.getTableView().getItems().get(ce.getTablePosition().getRow())).setUseby(ce.getNewValue());
                mTable.refresh();
            }
        });
        statusCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Asset, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Asset, String> ce) {
                ((Asset) ce.getTableView().getItems().get(ce.getTablePosition().getRow())).setStatus(ce.getNewValue());
                mTable.refresh();
            }
        });

        //Save Edit
        sedit.setOnAction(ActionEvent-> UpdateFile.updateFile(assetlist));

        //Delete Asset
        delete.setOnAction(ActionEvent->{
            assetlist.remove(mTable.getSelectionModel().getSelectedIndex());
            compnum=0;
            printernum=0;
            audiovideonum=0;
            for (Asset t:assetlist){
                String temp = t.getType();
                if(temp.equalsIgnoreCase("Computer")||temp.equalsIgnoreCase("Laptop")||temp.equalsIgnoreCase("Desktop")||temp.equalsIgnoreCase("Server")){
                    compnum--;
                }
                else if(temp.equalsIgnoreCase("Printer")){
                    printernum--;
                }
                else if(temp.equalsIgnoreCase("Audio/video")||temp.equalsIgnoreCase("Projector")){
                    audiovideonum--;
                }
            }
            cnum.setText(""+compnum);
            pnum.setText(""+printernum);
            avnum.setText(""+audiovideonum);
        });

        // print maintenance plan
        maintenance.setOnAction(ActionEvent-> {
            Computers temp = new Computers();
            Printers tempt = new Printers();
            AudioVideo t= new AudioVideo();
            l1.setText(temp.printMaintenancePlan());
            l2.setText(tempt.printMaintenancePlan());
            l3.setText(t.printMaintenancePlan());
        });

        //print IP Config detail
        ipconfig.setOnAction(ActionEvent-> {
            Computers temp = new Computers();
            Printers tempt = new Printers();
            l1.setText(temp.printIPConfig());
            l2.setText(tempt.printIPConfig());
            l3.setText("");
        });
    }
}
