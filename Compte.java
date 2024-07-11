/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gbancaire;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.*;
import java.sql.DriverManager;

public class CompteBancaire {
    private int numeroCompte;
    private String titulaireCompte;
    private int solde;
    public CompteBancaire(int nCompte,String tcmpte,int soldeDeVotreCompte)
    {
        this.titulaireCompte=tcmpte;
        this.numeroCompte=nCompte;
        this.solde=soldeDeVotreCompte;
    }
    public int getNumeroCompte()
    {
        return numeroCompte;
    }
     public String gettitulaire()
    {
        return titulaireCompte;
    }
     public int getSolde()
    {
        return solde;
    }
    public void deposer(double montant)
    {
    if(montant>0)
    {
        solde+=montant;
        System.out.println("Depot reussi. /n"+"Nouveau solde "+solde);
    }else
        System.out.println("Le montant doit etre plus de 500Ar . /n");
    }
    public void retirer(double montant)
    {
        if(montant > 0 && montant <=solde)
        {
        solde-=montant;
        System.out.println("Retrait bien succes.\n"+"Nouveau solde : "+solde);
        
        }else if(montant>solde)
        {
            System.out.println("Votre solde est insuffisant");
        }else
            System.out.println("Le montant de retrait > 0");
    }
    public  static void sauverDansBase(CompteBancaire b)
    {
        Connection c=null;
        Statement st=null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c= DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_banquaire", "root", "");
            st=c.createStatement();
            String sqlInsert="insert into banque values("+b.getNumeroCompte()+",'"+b.gettitulaire()+"',"+b.getSolde()+")";
            st.executeUpdate(sqlInsert);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public String toString()
    {
        return "[Numero de compte] : "+numeroCompte+"\n"+"[Titulaire] : "+titulaireCompte+"[Solde] : "+solde;
    }
    
}
