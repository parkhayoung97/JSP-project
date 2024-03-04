package AdminPage;


public class AirDTO {
   private String code,name,nation;

   public String getCode() {
      return code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getNation() {
      return nation;
   }

   public void setNation(String nation) {
      this.nation = nation;
   }

   @Override
   public String toString() {
      return "AirDTO [code=" + code + ", name=" + name + ", nation=" + nation + ", getCode()=" + getCode()
            + ", getName()=" + getName() + ", getNation()=" + getNation() + ", getClass()=" + getClass()
            + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
   }

   

   
   
   

}