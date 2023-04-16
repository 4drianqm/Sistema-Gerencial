package com.example.sistemagerencial.Controllers;


import com.example.sistemagerencial.Connection;
import com.example.sistemagerencial.Product;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.bson.Document;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {


    public Button inventory_btn;
    public Button clients_btn;
    public Button providers_btn;
    public AnchorPane Inventory_form;
    public AnchorPane clients_form;
    public TableView<Product> Inventory_tbl;
    public ObservableList<Product> table_inventory;
    public TableColumn<Product, String> Inventory_name;
    public TableColumn<Product, Integer> Inventory_price;
    public TableColumn<Product, String> Inventory_cost;
    public TableColumn<Product, Integer> Inventory_store;
    public TableColumn<Product, Integer> Inventory_exist;
    public TableColumn<Product, String> Inventory_insert;
    public TableColumn<Product, String> Inventory_delete;
    public TableColumn Inventory_id;
    public Button btn_addProduct;
    public Button btn_deleteProduct;
    public TextField txt_serch_inventary;
    public Button btn_updateProduct;
    public TextField txt_product_inventory;
    public TextField txt_price_inventory;
    public TextField txt_store_inventory;
    public TextField txt_existence_inventory;
    public TextField txt_id_inventory;
    public Button btn_clean;


    public void switchForm(ActionEvent event){
        if(event.getSource() == inventory_btn){
            Inventory_form.setVisible(true);
            clients_form.setVisible(false);
        }else if(event.getSource() == clients_btn){
            Inventory_form.setVisible(false);
            clients_form.setVisible(true);
        }
    } // Cambiar de pagina
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTables();
       loadInventory();
    }



    private void initIventory(){
        Inventory_id.setCellValueFactory(new PropertyValueFactory<>("productId"));
        Inventory_name.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        Inventory_price.setCellValueFactory(new PropertyValueFactory<>("product_price"));
        Inventory_store.setCellValueFactory(new PropertyValueFactory<>("product_store"));
        Inventory_exist.setCellValueFactory(new PropertyValueFactory<>("product_stock"));
    } // Inicializar la tabla de inventario

    private void initTables(){
        initIventory();

    } // inicializar todas las tablas

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
                    (Integer) cn.cursor.curr().get("Existencias")));

            }

        }catch (Exception e){
            System.out.println(e);
        }
        Inventory_tbl.setItems(table_inventory);
    } // Cargar los datos de iventario

    public void insertProduct(){
        Connection connection =  new Connection();
        String name = txt_product_inventory.getText();
        Integer price = Integer.valueOf(txt_price_inventory.getText());
        Integer store = Integer.valueOf(txt_store_inventory.getText());
        Integer stock = Integer.valueOf(txt_existence_inventory.getText());
        connection.insertNewProduct(name, price, store, stock);

        clean_load_Inventory();
    } // Nevo producto

    public void deleteProduct(){
        Connection connection = new Connection();
        String id = txt_id_inventory.getText();
        connection.deleteProduct(id);
        clean_load_Inventory();
    }
    public void selectProduct(){
        Product product = Inventory_tbl.getSelectionModel().getSelectedItem();
        int num= Inventory_tbl.getSelectionModel().getSelectedIndex();

        if( (num -1) < -1){return;}
        txt_id_inventory.setText(product.getProductId());
        txt_product_inventory.setText(product.getProduct_name());
        txt_price_inventory.setText(String.valueOf(product.getProduct_price()));
        txt_store_inventory.setText(String.valueOf(product.getProduct_store()));
        txt_existence_inventory.setText(String.valueOf(product.getProduct_stock()));
    }

    public void updateProduct(){
        Connection connection = new Connection();
        String id = txt_id_inventory.getText();
        String product = txt_product_inventory.getText();
        Integer price = Integer.valueOf(txt_price_inventory.getText());
        Integer store = Integer.valueOf(txt_store_inventory.getText());
        Integer stock = Integer.valueOf(txt_existence_inventory.getText());

        connection.updateProduct(id,product,price,store,stock);
        clean_load_Inventory();
    }
    public void clean_load_Inventory(){
        loadInventory();

        txt_product_inventory.setText("");
        txt_price_inventory.setText("");
        txt_store_inventory.setText("");
        txt_store_inventory.setText("");
        txt_existence_inventory.setText("");
        txt_id_inventory.setText("");
    }

}
