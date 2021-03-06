/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.farmcontrol.controller.animal;

import br.com.farmcontrol.model.dao.MamiferoDAO;
import br.com.farmcontrol.model.vo.Mamifero;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


public class AtualizarMamiferoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private Button atualizarAnimal;
    
    @FXML
    private ListView<Mamifero> listAnimais;
    
    private ObservableList<Mamifero> obsAnimais;
    
    private List<Mamifero> animais = new ArrayList<>();
    
    @FXML
    private TextField valorVenda;

    @FXML
    private TextField dataVenda;

    @FXML
    private TextField dataAquisicao;

    @FXML
    private TextField raca;

    @FXML
    private TextField id;

    @FXML
    private Pane panelMamifero;

    @FXML
    private TextField sexoMamifero;

    @FXML
    private TextField valorArroba;

    @FXML
    private TextField peso;

    @FXML
    private TextField tipoMamifero;

    @FXML
    private TextField dataAbate;

    @FXML
    private Pane panelLote;

    @FXML
    private TextField tipoLote;

    @FXML
    private TextField quantidadeLote;
    
    @FXML
    private Button deletar;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarAnimais();
    }
    
    public void pegarInforAnimais(){
        Mamifero m = listAnimais.getSelectionModel().getSelectedItem();
        id.setText(String.valueOf(m.getId_animal()));
        raca.setText(m.getRaca());
        dataAquisicao.setText(String.valueOf(m.getData_nasc_aquisicao()));
        dataVenda.setText(String.valueOf(m.getData_venda()));
        valorVenda.setText(String.valueOf(m.getValor_venda()));
        sexoMamifero.setText(m.getSexo_mamifero());
        valorArroba.setText(String.valueOf(m.getValor_arroba()));
        peso.setText(String.valueOf(m.getPeso()));
        tipoMamifero.setText(m.getTipo_mamifero());
        dataAbate.setText(String.valueOf(m.getData_abate()));    
    }
    
    public void atualizarAnimal() throws NullPointerException{

        
        Mamifero m = new Mamifero();
        
        m.setId_animal(Integer.parseInt(id.getText()));
        m.setRaca(raca.getText());
        m.setData_nasc_aquisicao(dataPadrao(dataAquisicao.getText()));
        m.setData_venda(dataPadrao(String.valueOf(dataVenda.getText())));
        m.setValor_venda(Float.parseFloat(valorVenda.getText()));
        m.setSexo_mamifero(sexoMamifero.getText());
        m.setValor_arroba(Float.parseFloat(valorArroba.getText()));
        m.setPeso(Float.parseFloat(peso.getText()));
        m.setTipo_mamifero(tipoMamifero.getText());
        m.setData_abate((Date)dataPadrao(dataAbate.getText()));
        
        MamiferoDAO.update(m);
        
        id.setText("");
        raca.setText("");
        dataAquisicao.setText("");
        dataVenda.setText("");
        valorVenda.setText("");
        sexoMamifero.setText("");
        valorArroba.setText("");
        peso.setText("");
        tipoMamifero.setText("");
        dataAbate.setText("");
        
        carregarAnimais();
      
    }
   
    public void deletarAnimal(){
        
        Mamifero m = new Mamifero();
        m.setId_animal(Integer.parseInt(id.getText()));
        MamiferoDAO.delete(m);
        
        id.setText("");
        raca.setText("");
        dataAquisicao.setText("");
        dataVenda.setText("");
        valorVenda.setText("");
        sexoMamifero.setText("");
        valorArroba.setText("");
        peso.setText("");
        tipoMamifero.setText("");
        dataAbate.setText("");
        
        carregarAnimais();
        
    }
 
    public Date dataPadrao(String s){
        if(s==null || s.equals("null") || s.equals("")){
            return null;
        }
        int ano = Integer.parseInt(s.substring(0,4));
        int mes = Integer.parseInt(s.substring(5,7));
        int dia = Integer.parseInt(s.substring(8,10));   
        return new Date(ano-1900, mes-1, dia);
    } 
    
    public void carregarAnimais(){
        
        animais = MamiferoDAO.read();
        
        obsAnimais = FXCollections.observableArrayList(animais);

        listAnimais.setItems(obsAnimais);
        
    }
    
}
