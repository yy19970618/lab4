package hello2;
import java.io.*;
import java.util.Scanner;

class EdgeElement {  
    String fromvex;  
    String endvex;  
    int weight;  
      
    public EdgeElement(String v1,String v2,int wgt){  
        fromvex=v1;  
        endvex=v2;  
        weight=wgt;  
    }     
}
class Graph{
	int G[][];
	String unitword[];
	int Edge;
	
	public Graph(int Gr[][],String uw[],int ed) {
		G=Gr;
		unitword=uw;
		Edge=ed;
	}
}
public class Main {
	public static Graph graph;
	public static EdgeElement Ed[];
	public static void main(String args[]){
	         Scanner sc = new Scanner(System.in);
             String filepath;
             System.out.println("please input your filepath:");
             filepath = sc.nextLine();
		     String[] W = readFileByLines(filepath);
		     CreatGraph(W);
		     showDirectedGraph(graph.G);
		     System.out.println("please input two words:");
		     String word1;
		     word1 = sc.nextLine();
		     String word2;
		     word2 = sc.nextLine();
		     calcShortestPath(word1,word2);
		     sc.close();
	  }
	  
	  public static String[] readFileByLines(String filename) {
             char[] inputCharArray =new char[1000];
	         int count = 0;
	         try{
	    	      InputStream reader = new FileInputStream(filename);
			      int size = reader.available();		   
			      count=size;
			      
			      char B;
			      for(int i=0; i< size; i++){
			    	 B=(char)reader.read();
			    	 if(!(Character.isLetter(B))) {
			    		    inputCharArray[i]=' ';
			    	  }else {
			    	        inputCharArray[i] = (char) ((B<97) ? B+32:B);
			    	  }
			      }
			        reader.close();
			      }catch(IOException e){
			        System.out.print("Exception");
	         }
	         
	         int flag = 0;
	         int wordcount = 0;
	         int wordflag  = 0;
	         char[] word = new char[20];
             String[] wordlist  = new String[100];
	         for(int j=0;j<count;j++) {
	        	 if(inputCharArray[j] != ' ') {
	        		word[wordflag] = inputCharArray[j];
	        		wordflag++;
	        		flag = 0;
	        	 }else {
	        		 flag++;
		        	 if(flag==1) {
		        		 wordlist[wordcount] = new String(word);
		        		 wordcount++;
		        		 wordflag=0;
		        		 for(int k=0;k<20;k++) {
		        			 word[k]=' ';
		        		 }
		        	 }else {
		        		    continue;
		        	 }
	        	 }
	         }
	         return wordlist;
	         
	 }

	 public static void CreatGraph(String wordlist[]){
		     int wordcount = 0;
	         EdgeElement D[] = new EdgeElement[100];
	         for(int i=0;i<99;i++) {
	        	 if(wordlist[i+1]!=null) {
		        	 String V1=wordlist[i];
		        	 String V2=wordlist[i+1];
		        	 D[i] = new EdgeElement(V1,V2,1);
		        	 wordcount++;
	        	 }
	         }
	         Ed = D;
	         for(int i=1;i<wordcount;i++) {
	        	 for(int j=0;j<i;j++) {
	        		 if(wordlist[i].equals(wordlist[j])) {
	        			 wordlist[i]="false";
	        			 break;
	        		 }
	        	 }
	         }
	         
	         int unitwordcount=0;
	         String unitword[] = new String[100];
	         for(int i=0;i<wordcount;i++) {
                if(!wordlist[i].equals("false")) {
                	unitword[unitwordcount]= wordlist[i];
                	unitwordcount++;
                }
	         }
	         int G[][] = new int[unitwordcount][unitwordcount];
	         for(int i=0;i<wordcount-1;i++) {
	        	 String V1,V2;
	        	 V1 = D[i].fromvex;
	        	 V2 = D[i].endvex;
	        	 int m=0;
	        	 int n=0;
	        	 for(int j=0;j<unitwordcount;j++) {
	        		 if(V1.equals(unitword[j])) {
	        			 m=j;
	        		 }
	        		 if(V2.equals(unitword[j])) {
	        			 n=j;
	        		 }
	        	 }
	        	 if(G[m][n]>0) {
	        		 G[m][n] += 1;
	        	 }else {
	        		 G[m][n] = D[i].weight;
	        	 }
	         }
	         
	         graph = new Graph(G,unitword,wordcount-1);
	  }

  public static void showDirectedGraph(int G[][]) {
    	 for(int i =0;i<graph.G.length;i++) {
    		 for(int j=0;j<graph.G.length;j++) {
	        	 if(G[i][j]>0) {
	        		 System.out.println(graph.unitword[i]+"-->"+graph.unitword[j]+G[i][j]);
    		 }
    	  }
      }
            
  }
	  
	  public static void calcShortestPath(String word1,String word2){
        	 int m=0;
        	 int n=0;
        	 for(int w=0;w<graph.G.length;w++) {
        		 if(word1.equals(graph.unitword[w])) {
        			 m=w;
        		 }
        		 if(word2.equals(graph.unitword[w])) {
        			 n=w;
        		 }
        	 }
        	 
	     	 System.out.println(m+" "+n);
	         int h = graph.G.length;  
	         int[][] D = new int[h][h]; 
	         int[][] p = new int[h][h];
	         for (int i = 0; i < h; i++) {  
	             for (int j = 0; j < h; j++) {  
	                 if (graph.G[i][j] !=0) {  	      
	                     p[i][j] = j;  
	                 } else {  
	                     p[i][j] = -1;  
	                 }
	                 if(D[i][j]==0) {
	                	 D[i][j] =100000;
	                 }else {
	                	 D[i][j] = graph.G[i][j]; 
	                 } 
	             }  
	         }  
	      
	         for (int x = 0; x < h; x++) {
	             for (int i = 0; i < h; i++) {  
	                 for (int j = 0; j < h; j++) {  
	                     if (D[i][j] > D[i][x] + D[x][j]) {  
	                         D[i][j] = D[i][x] + D[x][j];  
	                         p[i][j] = p[i][x]; 
	                     }  
	                 }  
	             }  
	         } 
	         
             int k = p[3][4];  
             if (k == -1) {  
                 System.out.println("没有最短路径");  
             } else {  
                 System.out.print(" " + k);  
                 while (k != m) {  
                     k = p[m][k];  
                     System.out.print(" " + k);  
                 }  
                 System.out.println(" "+k);  
                 System.out.println();  
             }  
	  }
}
