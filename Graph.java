package lab4;

public class Graph {
	int G[][];
	String unitword[];
	int Edge;
	
	public Graph(int Gr[][],String uw[],int ed) {
		G=Gr;
		unitword=uw;
		Edge=ed;
	}
	public void setEdge(int Edge){
    	this.Edge = Edge;
    }
    public int getEdge(){
    	return this.Edge;
    }
    public void setG(int G[][]){
    	this.G = G;
    }
    public int[][] getG(){
    	return this.G;
    }
    public void setunitword(String unitword[]){
    	this.unitword = unitword;
    }
    public String[] getunitword(){
    	return this.unitword;
    }
}
