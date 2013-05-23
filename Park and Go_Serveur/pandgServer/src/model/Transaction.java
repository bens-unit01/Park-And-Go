/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.*;

/**
 *class Transaction
 * @author "Messaoud BENSALEM"
 * @version 1.0  2012-12-27 
 */
@Entity
@Table(name="transactions")
public class Transaction implements Serializable{

    // attributs 
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int idTransaction;
    
    @Column(length=10)
    private String codeEmplacement;
    
    @ManyToOne
    @JoinColumn(name="username")
    private User username;
    
    @Temporal(TemporalType.DATE)
    @Column(name="trx_date")
    private Calendar trxDate;  // date de la transaction
    
    @Column(name="heure_debut")
    private Time heureDebut;
    
    @Column(name="heure_fin")
    private Time heureFin;
    
    // constructeurs 

    public Transaction() {
    }
    
    
    // getteurs et setteurs 

    public int getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(int idTransaction) {
        this.idTransaction = idTransaction;
    }

    public String getCodeEmplacement() {
        return codeEmplacement;
    }

    public void setCodeEmplacement(String codeEmplacement) {
        this.codeEmplacement = codeEmplacement;
    }

    public User getUser() {
        return username;
    }

    public void setUser(User user) {
        this.username = user;
    }



    public Calendar getDate() {
        return trxDate;
    }

    public void setDate(Calendar date) {
        this.trxDate = date;
    }

    public Time getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(Time heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Time getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Time heureFin) {
        this.heureFin = heureFin;
    }
    
    
    // toString()

    @Override
    public String toString() {
        return "Transaction{" + "idTransaction=" + idTransaction + ", codeEmplacement=" + codeEmplacement + ", userName=" + username.getUserName() + ", trxDate=" + trxDate + ", heureDebut=" + heureDebut + ", heureFin=" + heureFin + '}';
    }
    
    
    

}
