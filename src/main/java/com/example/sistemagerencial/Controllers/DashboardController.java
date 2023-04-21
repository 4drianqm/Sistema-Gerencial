package com.example.sistemagerencial.Controllers;


import com.example.sistemagerencial.Clients;
import com.example.sistemagerencial.Connection;
import com.example.sistemagerencial.Product;
import com.mongodb.MongoException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.EventObject;
import java.util.Objects;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    // INVENTORY MODULE

    public Button inventory_btn;
    public Button clients_btn;
    public Button providers_btn;
    public AnchorPane Inventory_form;
    public AnchorPane clients_form;
    public TableView<Product> Inventory_tbl;
    public ObservableList<Product> table_inventory;
    public TableColumn<Product, String> Inventory_name;
    public TableColumn<Product, Integer> Inventory_price;
    public TableColumn<Product, Integer> Inventory_store;
    public TableColumn<Product, Integer> Inventory_exist;
    public TableColumn<Product,Button> inventory_delete;
    public TableColumn<Product, Button> inventory_edit;
    public TableColumn<Product, String> Inventory_insert;
    public TableColumn<Product, String> Inventory_delete;
    public TableColumn Inventory_id;
    public Button btn_addProduct;

    public TextField txt_serch_inventary;


    // CLIENTS MODULE
    public TableView<Clients> tbl_clients;
    public ObservableList<Clients> table_clients;
    public TableColumn<Clients,String> client_id;
    public TableColumn<Clients,String> client_name;
    public TableColumn<Clients, String> client_phone;
    public TableColumn<Clients,String> client_address;
    public TableColumn<Clients,String> client_nit;
    public TableColumn<Clients,Button> client_delete;
    public TableColumn<Clients,Button> client_edit;
    public Button btn_cientRefresh;
    public TextField txt_serchClient;
    public Button btn_insertClient;
    public AnchorPane providers_form;
    public AnchorPane sells_form;
    public Button sells_btn;



    public void switchForm(ActionEvent event){
        if(event.getSource() == inventory_btn){
            Inventory_form.setVisible(true);
            clients_form.setVisible(false);
            providers_form.setVisible(false);
            sells_form.setVisible(false);
        }else if(event.getSource() == clients_btn){
            clients_form.setVisible(true);
            Inventory_form.setVisible(false);
            providers_form.setVisible((false));
            sells_form.setVisible(false);
        } else if (event.getSource() == providers_btn) {
            providers_form.setVisible(true);
            clients_form.setVisible(false);
            Inventory_form.setVisible(false);
            sells_form.setVisible(false);
        } else if (event.getSource() == sells_btn) {
            sells_form.setVisible(true);
            clients_form.setVisible(false);
            providers_form.setVisible(false);
            Inventory_form.setVisible(false);

        }

    } // Change page to inventory, clients, providers...etc
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTables();
       loadInventory();
       loadClients();
    }

    // *************  INVENTORY  ************************

    private void initIventory(){
        Inventory_id.setCellValueFactory(new PropertyValueFactory<>("productId"));
        Inventory_name.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        Inventory_price.setCellValueFactory(new PropertyValueFactory<>("product_price"));
        Inventory_store.setCellValueFactory(new PropertyValueFactory<>("product_store"));
        Inventory_exist.setCellValueFactory(new PropertyValueFactory<>("product_stock"));
        inventory_delete.setCellValueFactory(new PropertyValueFactory<>("delete"));
        inventory_edit.setCellValueFactory(new PropertyValueFactory<>("edit"));

    } // Initialize Inventory table

    private void initTables(){
        initIventory();
        initClients();

    } // Initialize tables

    private void  loadInventory(){
        table_inventory = FXCollections.observableArrayList();
        try{
            Connection cn = new Connection();
            cn.CreateConnection();
            cn.Inventory =cn.database.getCollection("Inventario");
            cn.cursor = cn.Inventory.find();

            while (cn.cursor.hasNext()){
            table_inventory.add(new Product(cn.cursor.next().get("_id").toString(),
                    (String) cn.cursor.curr().get("Nombre"),
                    (Integer) cn.cursor.curr().get("Precio"),
                    (Integer) cn.cursor.curr().get("Bodega"),
                    (Integer) cn.cursor.curr().get("Existencias"),
                    new Button("Eliminar"),
                    new Button("Actualizar")));

            }

        }catch (Exception e){
            System.out.println("Error al cargar ventaja Inventario"+e);
        }
        Inventory_tbl.setItems(table_inventory);
    } // charge inventory data

    public void newProduct(){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/insertProduct.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            insertProductController insertProductController = fxmlLoader.getController();
            insertProductController.setDashboardController(this);



        }catch (Exception e){
            System.out.println("Error al cargar la ventana nuevo Prodcuto"+ e);
        }
    }

    public void selectProduct(){
        Product product = Inventory_tbl.getSelectionModel().getSelectedItem();
        int num= Inventory_tbl.getSelectionModel().getSelectedIndex();


        if( (num -1) < -1){return;}
        product.getEdit().setOnAction(e ->{

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/updateProduct.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                updateProductController updateProductController = fxmlLoader.getController();
                updateProductController.setDashboardController(this);

                updateProductController.txt_updateProducto_id.setText(product.getProductId());
                updateProductController.txt_updateProduct_name.setText(product.getProduct_name());
                updateProductController.txt_updateProduct_price.setText(String.valueOf(product.getProduct_price()));
                updateProductController.txt_updateProduct_store.setText(String.valueOf(product.getProduct_store()));
                updateProductController.txt_updateProduct_exis.setText(String.valueOf(product.getProduct_stock()));

            }catch (Exception exception){
                System.out.println("Error al cargar ventana update producto" + exception);
            }

        });

        product.getDelete().setOnAction(e ->{
            Connection connection = new Connection();
            String id = product.getProductId();

            connection.deleteProduct(id);
            clean_load_Inventory();
        });

    }   // Put data into the txt field

    public void clean_load_Inventory(){
        loadInventory();

    }   //Clean txt fields



    // **************  CLIENTS  ***************************

    private void initClients(){
        client_id.setCellValueFactory(new PropertyValueFactory<>("clientId"));
        client_name.setCellValueFactory(new PropertyValueFactory<>("client_name"));
        client_phone.setCellValueFactory(new PropertyValueFactory<>("cellphone"));
        client_address.setCellValueFactory(new PropertyValueFactory<>("client_address"));
        client_nit.setCellValueFactory(new PropertyValueFactory<>("client_nit"));
        client_edit.setCellValueFactory(new PropertyValueFactory<>("add"));
        client_delete.setCellValueFactory(new PropertyValueFactory<>("delete"));

        client_edit.setMinWidth(100);
        client_edit.setMaxWidth(100);

        client_delete.setMinWidth(70);
        client_delete.setMaxWidth(70);


    }

    private void loadClients(){

        table_clients = FXCollections.observableArrayList();

        try {

            Connection cn = new Connection();
            cn.CreateConnection();
            cn.Client = cn.database.getCollection("Clients");
            cn.cursor = cn.Client.find();

            while (cn.cursor.hasNext()){
                table_clients.add(new Clients(cn.cursor.next().get("_id").toString(),
                        (String) cn.cursor.curr().get("Nombre"),
                        (String) cn.cursor.curr().get("Telefono"),
                        (String) cn.cursor.curr().get("Direccion"),
                        (String) cn.cursor.curr().get("NIT"),
                        new Button("Actualizar"),
                        new Button("Eliminar")));


            }

        }catch (Exception e){
            System.out.println("Error al cargar datos"+ e);
        }

        tbl_clients.setItems(table_clients);
    }

    public void selectClient(){

        Clients clients =tbl_clients.getSelectionModel().getSelectedItem();
        int num = tbl_clients.getSelectionModel().getSelectedIndex();

        if((num -1) < -1){return;}
        clients.getAdd().setOnAction(e ->{
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/updateClient.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                updateClientController updateController = fxmlLoader.getController();
                updateController.setDashboardController(this);

                updateController.txt_updateId.setText(clients.getClientId());
                updateController.txt_updateName.setText(clients.getClient_name());
                updateController.txt_updatePhone.setText(clients.getCellphone());
                updateController.txt_updateAddress.setText(clients.getClient_address());
                updateController.txt_updateNit.setText(clients.getClient_nit());




            }catch (Exception ex){
                System.out.println("Error al cargar la venta Update cliente"+ex);
            }
        });

        clients.getDelete().setOnAction(e ->{
            Connection connection = new Connection();
            String id = clients.getClientId();

            connection.deleteClient(id);
            loadClients();
        });

    }

    public void newClient(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/insertClient.fxml"));

            Scene scene = new Scene(fxmlLoader.load());

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();


            insertClientController insertClientController = fxmlLoader.getController();
            insertClientController.setDashboardController(this);

        }catch (Exception e){
            System.out.println("Error al cargar la ventana nuevo cliente" + e);
        }
    }
    public void reloadClients() {
        loadClients();
    }

    public void None(){
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("IMPORTANTE");
        alert.setContentText("ACCION REALIZADA EXITOSAMENTE");
        alert.setGraphic(new ImageView(Objects.requireNonNull(this.getClass().getResource("/Images/comprobado.png")).toString()));
        alert.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        alert.showAndWait();

    }


}
