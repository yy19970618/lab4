package lab4;

public class EdgeElement {
  private String fromvex;  
  private  String endvex;  
  private int weight;  
      
	public EdgeElement(String v1,String v2,int wgt){  
	  fromvex = v1;  
	  endvex = v2;  
	  weight = wgt;  }
	public void setfromvex(String fromv){
	  this.fromvex = fromv;
	}
    public String getfromvex(){
    	return this.fromvex;
    }
    public void setendvex(String endvex){
    	this.fromvex = endvex;
    }
    public String getendvex(){
    	return this.endvex;
    }
    public void setweight(int weight){
    	this.weight = weight;
    }
    public int getweight(){
    	return this.weight;
    }
}
