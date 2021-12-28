package matrix;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class  {
	
	
    public static int[][] isSafe(int[][] need_Matrix, int[] avaliable_matrix,int[][] allocation_matrix,FileWriter writer ) throws IOException
    {
    	
    	FileWriter myWriter =writer;
    	int counterSuccessfuly =0;
        ArrayList<Integer> safe=new ArrayList<>();
        int[][] chanInAvaileble=new int[need_Matrix.length][need_Matrix[0].length];
        myWriter.write("Safe sequence is :\n");
        System.out.println("Safe sequence is :");
        while(safe.size()!=need_Matrix.length)
        {
        	counterSuccessfuly++;
            for (int i = 0; i < need_Matrix.length; i++) {
                if(safe.contains(i))
                {
                    continue;
                }
                boolean condition=true;
                for (int j = 0; j < need_Matrix[i].length; j++) {
                    if(avaliable_matrix[j]<need_Matrix[i][j])
                    {
                        condition=false;
                        break;
                    }
                }
                if(condition)
                {
                    safe.add(i);
                    int character=65+i;
                    myWriter.write((char)(character)+" ");
                    System.out.print((char)(character)+" ");
                    for (int j = 0; j < allocation_matrix[i].length; j++) {
                        avaliable_matrix[j]+=allocation_matrix[i][j];
                    }
                    for (int j = 0; j < avaliable_matrix.length; j++) {
                        int index=safe.indexOf(i);
                        chanInAvaileble[index][j]=avaliable_matrix[j];
                    }
                }
            }
            
            if(counterSuccessfuly>=need_Matrix.length*5) {
            	
            	return null;
            }
        }
        System.out.println();
        return chanInAvaileble;
    }
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        int processes = 0;
        int resources = 0;
        String available="";
                
        
        String[] resource_matrix ;
        String[]  allocation_matrix;        
        String[] stringtoresource ;
        String[] stringtoallocation ;
        int[] avaliable_matrix;
        
        int counter=0;
        BufferedReader inputStream = null;
        PrintWriter outputStream = null;
 
        try {
            inputStream = new BufferedReader(new FileReader("input.txt"));
                            
            
            
            String satir;
            while ((satir = inputStream.readLine()) != null) {
            if(counter==1) { 
                
                processes=Integer.parseInt(satir);
                
            }    
            if(counter==3) { 
                
                resources=Integer.parseInt(satir);
                counter++;
                break;
                
            }
            counter++;
            }
            
            resource_matrix=new String[processes];
            allocation_matrix=new String[processes]    ;
            stringtoresource=new String[processes]    ;
            stringtoallocation=new String[processes];
            avaliable_matrix=new int[resources];
            while ((satir = inputStream.readLine()) != null) {        
            if(counter==5){
                int j=0;    
                for(counter=5;counter<=(5+processes-1);counter++) {
                
                
                    
                    resource_matrix[j]=satir;
                    j++;
                
                
                    satir = inputStream.readLine();
            }
            }
            if(counter==5+processes-1+2){
                    int u=0;    
                    for(counter=5+processes-1+2;counter<=5+processes-1+2+processes-1;counter++) {
                    
                    
                        
                        allocation_matrix[u]=satir;
                        u++;
                    
                    
                        satir = inputStream.readLine();
                }
                
            }
            if(counter==5+processes-1+2+processes-1+2) {
                
                
                available=satir;
                
            }
                
            counter++;
            
            
            
            
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            
        }
        
        
        
        System.out.println(processes+" process");
        
        System.out.println(resources+" resources");
        
        
        
        
        for(int j=0;j<processes;j++) {
            
            stringtoresource[j]=resource_matrix[j].replaceAll("\\s","");
            
        }
        
        for(int j=0;j<processes;j++) {
            
            stringtoallocation[j]=allocation_matrix[j].replaceAll("\\s","");
            
        }
        
        String stringtoavaliable=available.replaceAll("\\s","");
        
        for(int i=0;i<5;i++) {
            
            avaliable_matrix[i]=Character.getNumericValue(stringtoavaliable.charAt(i));
        }

        
        
        int[][] matrix_resource = new int[processes][resources];
        for(int i=0;i<processes;i++) {
            
            for(int j=0;j<resources;j++) {
                
                
            matrix_resource[i][j]=Character.getNumericValue(stringtoresource[i].charAt(j));
                
                
            }
            
        }
        
        int[][] matrix_allacation = new int[processes][resources];
        for(int i=0;i<processes;i++) {
            
            for(int j=0;j<resources;j++) {
                
                
                matrix_allacation[i][j]=Character.getNumericValue(stringtoallocation[i].charAt(j));
               
            }
            
        }
        
        int[][] need_Matrix = new int[processes][resources];
        for(int i=0;i<processes;i++) {
            
            for(int j=0;j<resources;j++) {
                
                
                need_Matrix[i][j]=matrix_resource[i][j]-matrix_allacation[i][j];
                                
            }
            
        }
        
        
        
        try {
            FileWriter myWriter = new FileWriter("output.txt");
            System.out.println("Need Matrix :");
            myWriter.write("Need Matrix :\n");
			for(int i=0;i<processes;i++) {
			    
	        	for(int j=0;j<resources;j++) {
	        
	        		System.out.print(need_Matrix[i][j]+" ");
	        		myWriter.write(need_Matrix[i][j]+" ");
	                      
	        	}
	        	myWriter.write("\n");
	        	System.out.println("");
	        }
			
			int[][] safeSq=isSafe(need_Matrix, avaliable_matrix, matrix_allacation,myWriter);
			myWriter.write("\nChange in available resource matrix :\n");
			System.out.println("Change in available resource matrix :");
			if(safeSq==null) {
				System.out.println("deadlock ocour");
				myWriter.write("deadlock ocour");
			}
			else {
			for (int i = 0; i < safeSq.length; i++) {
	            for (int j = 0; j < safeSq[i].length; j++) {
	            	myWriter.write(safeSq[i][j]+" ");
	            	System.out.print(safeSq[i][j]+" ");
	            }
	            myWriter.write("\n");
	            System.out.println("");
	            
	        }
			}
			
			
			
			
            myWriter.close();
            
          } catch (IOException e) {           
            e.printStackTrace();
          }
        
        
        
    }

}