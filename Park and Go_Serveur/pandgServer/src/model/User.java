/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

/**
 *class User
 * @author "Messaoud BENSALEM"
 * @version 1.0  2012-12-27 
 */
@Entity
@Table(name="users")
public class User implements Serializable{
    
    // attributs 
    @Id
    @Column(name="username")
    private String username; 
    
    @Column(name="password", length=32)
    private String password;
    
    @Column(length=32)
    private String nom;
    
    @Column(length=32)
    private String prenom;
    
    @Column(length=6)
    private double solde;
    
    @Column(length=15)
    private String numTelephone;
    
    @OneToMany(mappedBy="username", cascade=CascadeType.ALL)
    private Set<Transaction> historique;
    
    // constructeur 

    public User() {
    }
    
    // getteurs et setteurs 

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public String getNumTelephone() {
        return numTelephone;
    }

    public void setNumTelephone(String numTelephone) {
        this.numTelephone = numTelephone;
    }

    public Set<Transaction> getHistorique() {
        return historique;
    }

    public void setHistorique(Set<Transaction> historique) {
        this.historique = historique;
    }
    
    
    // toString()

    @Override
    public String toString() {
        return "User{" + "userName=" + username + ", password=" + password + ", nom=" + nom + ", prenom=" + prenom + ", solde=" + solde + ", numTelephone=" + numTelephone + '}';
    }
    
    
    

}
