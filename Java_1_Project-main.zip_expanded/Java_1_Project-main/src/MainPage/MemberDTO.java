package MainPage;

public class MemberDTO {
	   
	   private static MemberDTO instance = new MemberDTO();
	   
	   
	    public static MemberDTO getInstance() {
	        return instance;
	    } 
	   
	   private String id;
	   private String pw;
	   private String name;
	   private String bir;
	   private String email;
	   private String number;
	   public String getId() {
	      return id;
	   }
	   public void setId(String id) {
	      this.id = id;
	   }
	   public String getPw() {
	      return pw;
	   }
	   public void setPw(String pw) {
	      this.pw = pw;
	   }
	   public String getName() {
	      return name;
	   }
	   public void setName(String name) {
	      this.name = name;
	   }
	   public String getBir() {
	      return bir;
	   }
	   public void setBir(String bir) {
	      this.bir = bir;
	   }
	   public String getEmail() {
	      return email;
	   }
	   public void setEmail(String email) {
	      this.email = email;
	   }
	   public String getNumber() {
	      return number;
	   }
	   public void setNumber(String number) {
	      this.number = number;
	   }
	   
	   @Override
	   public String toString() {
	      return "MemberDTO [id=" + id + ", pw=" + pw + ", name=" + name + ", bir=" + bir + ", email=" + email
	            + ", number=" + number + "]";
	   }

	}