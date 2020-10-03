
import mypkg.Medicine;
import mypkg.MedicineDAO;

public class MedicineService {
    private MedicineDAO dao;
    public MedicineService(){
        dao=new MedicineDAO();
    }
    public int getDiscountedPrice(int price){
        if(price>=500){
            return price-(price*10/100);
        }else{
            return price-(price*5/100);
        }
    }
    
    public void save(Medicine medicine){
        dao.saveMedicine(medicine);
    }
    public Medicine search(int code){
        Medicine medicine =dao.searchMedicine(code);
        return medicine;
    }
}
