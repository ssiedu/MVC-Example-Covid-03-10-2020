package mypkg;

//write the CRUD operations for medicine table.

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class MedicineDAO {

    private Connection con;

    public MedicineDAO(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/coviddata", "root","root");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    //we will pass the values for a record and save method should enter those value to DB
    //code, name, type, price
    public void saveMedicine(Medicine medicine){
        //this method will save the details of this object to DB
        try{
        PreparedStatement ps=con.prepareStatement("INSERT INTO medicines values(?,?,?,?)");
        ps.setInt(1, medicine.getCode());
        ps.setString(2, medicine.getName());
        ps.setString(3, medicine.getType());
        ps.setInt(4, medicine.getPrice());
        ps.executeUpdate();
        System.out.println("Data Stored....");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
        
    
    public  Medicine searchMedicine(int code){
        //this method will get the record on code and return all details
        Medicine medicine=new Medicine();
        try{
        PreparedStatement ps=con.prepareStatement("SELECT * FROM medicines WHERE code=?");
        ps.setInt(1, code);
        ResultSet rs=ps.executeQuery();
        //we will store the data of resultset into a model and will return the object
        rs.next();
        
        medicine.setCode(rs.getInt(1));
        medicine.setName(rs.getString(2));
        medicine.setType(rs.getString(3));
        medicine.setPrice(rs.getInt(4));
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        return medicine;
    }
    
    public void deleteMedicine(int code){
        try{
            PreparedStatement ps=con.prepareStatement("DELETE FROM medicines WHERE code=?");
            ps.setInt(1, code);
            ps.executeUpdate();
            System.out.println("Record Removed...");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void updateMedicine(){
        
    }
/*
    public static void main(String args[]){
        
        MedicineDAO dao=new MedicineDAO();
        dao.deleteMedicine(1111);
        Medicine medicine=dao.searchMedicine(1111);
        System.out.println(medicine.getCode());
        System.out.println(medicine.getName());
        System.out.println(medicine.getType());
        System.out.println(medicine.getPrice());
        //testing the save methods.
        Medicine ob=new Medicine();
        ob.setCode(1111);
        ob.setName("Xyz");
        ob.setType("Vitamin");
        ob.setPrice(50);
        
        MedicineDAO dao=new MedicineDAO();
        dao.saveMedicine(ob);
        System.out.println("Completed");
    }
    */
}
