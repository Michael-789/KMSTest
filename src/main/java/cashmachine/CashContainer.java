package cashmachine;

import lombok.Data;

import java.util.HashMap;

@Data
public class CashContainer {

 static  CashContainer instance = null;
   private   HashMap<String,Currency> cashMap = new HashMap<>();


   private CashContainer() {
   }

   public static CashContainer getInstance(){

      if(instance == null){
         instance = new CashContainer();
      }
      return instance;
   }


}
