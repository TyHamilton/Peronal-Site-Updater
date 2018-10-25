import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Main {
	

	
	// loads data from a text file provided by a string 
	public static String dataFix(String a) {
		String logFile= a;
		String row = null;
		int count = 1;
		String html="";
		
		 try {
			 FileReader file = new FileReader(logFile);
	         BufferedReader buffered = new BufferedReader(file);
	         
	         while((row = buffered.readLine()) != null) {
//	        	
	        	 String nRow = "\n\"<tr> \" +\n "+ "\" <td>"+count +" </td>"+" " + " <td> " + row + "</td> \" + " ;
	        	 count ++;
	        	 
	        	 html += nRow;
//	        	 System.out.println(nRow);
	         }
	         
	         String htm = "\n\"<table>\"+ \n ";
	         String htm2 = "\n\"</table>\"\n";
	         
	         htm += html + htm2; 
//	         System.out.println(htm);
	         return htm;
	         
		 }catch(Exception e ) {
			 System.out.println("der");
		 }
		return "none";
	}
	
	public static void getPrograms() {
		String dirName = "D:\\DropBox\\Dropbox\\Newsite\\CodeSamples";

        try {
			Files.list(new File(dirName).toPath())
			        .limit(10)
			        .forEach(path -> {
//			            System.out.println(path);
			            String convert = path.toString();
			            
			            dirList.add(convert);
			            
			        });
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
	} 
	
	
	
	
	
	
	//Gets classes from directory provided by getPrograms creates class objects and loads html data 
	public static void getClasA(String check, ArrayList<String> list, ArrayList<ClasData> cList, int p) {
				
			File folder = new File(check);
			File[] listOfFiles = folder.listFiles();

			for (int i = 0; i < listOfFiles.length; i++) {
			  if (listOfFiles[i].isFile()) {
//			    System.out.println(check+"\\"+ listOfFiles[i].getName());
			    String clasPath = check+"\\"+ listOfFiles[i].getName();
			    ClasData clasN = new ClasData(clasPath);
			    clasN.setpIDc(p);
			    cList.add(clasN);
			    
			    list.add(clasPath);
			  } else if (listOfFiles[i].isDirectory()) {
//			    System.out.println("Directory " + listOfFiles[i].getName());
			  }
			}
			
		
		
		
	}
	
	
	// prototype method for getting class data 
	public static void getClas() {
		
		for(String check :dirList) {
			File folder = new File(check);
			File[] listOfFiles = folder.listFiles();

			for (int i = 0; i < listOfFiles.length; i++) {
			  if (listOfFiles[i].isFile()) {
//			    System.out.println(check+"\\"+ listOfFiles[i].getName());
			    String clasPath = check+"\\"+ listOfFiles[i].getName();
			    classList.add(clasPath);
			  } else if (listOfFiles[i].isDirectory()) {
//			    System.out.println("Directory " + listOfFiles[i].getName());
			  }
			}
			
		}
		
		
	}
	
	public static void dataPrimer() {
		
		getPrograms();
		int pIDmaker= 1;
		for(String tar: dirList) {
		Program a = new Program(tar);
		a.setpID(pIDmaker);
		System.out.println(pIDmaker);
		pIDmaker++;
		programList.add(a);
//		System.out.println(a.getClassdataList().size());
		}
		
		
	}
	
	public static void insertSQL() {
		for(Program loader: programList) {
			String nameProgram = loader.getNameP();
			int iDProg = loader.getpID();
			
			
			String insertP = "INSERT INTO  `programs` (  `_pID` ,  `name` ,  `img` ) VALUES (NULL , '"+ nameProgram+"', NULL);";
//			Statement statement = connect.createStatement();
//			System.out.println(insertP);
			
			SQLcommandsList.add(insertP);
			
			
			for(ClasData cLoader:loader.getClassdataList()) {
				int pIDc = cLoader.getpIDc();
				String nameC= cLoader.getNameC();
				String codeC= cLoader.getCode();
				
				String insertC = "INSERT INTO  `class` (  `_cID` ,  `pID` ,  `name` ,  `code` ) VALUES (NULL ,  '"+pIDc+"',  '"+nameC+"',  '"+codeC+"');";
				SQLcommandsList.add(insertC);
//				System.out.println(insertC);
//				st.executeUpdate(insertC);
				
			}
			}
	}
	



	public static  ArrayList<String> dirList = new ArrayList<>(); 
	public static  ArrayList<String> classList = new ArrayList<>(); 
	public static  ArrayList<Program> programList = new ArrayList<>();

	public static  ArrayList<String> SQLcommandsList = new ArrayList<>(); 
	
	public static void main(String[] args) throws ClassNotFoundException, FileNotFoundException, UnsupportedEncodingException   {
	
		dataPrimer();
		insertSQL();
		String megaCom="";
		
		for(String targ: SQLcommandsList) {
			megaCom+="+\n"+targ;
		}
		
		System.out.println(megaCom);
		
		PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
		for(String a: SQLcommandsList) {
			
			writer.println(a);
			
		}
		writer.close();
		
		
		
//		Connection connect = null;
//		Statement st = null;
//		ResultSet rs;
//		
//		String driver = "com.mysql.cj.jdbc.Driver";
//		String db = "tyhamilton";
//		String url = "jdbc:mysql://vrheadaoc74910.ipagemysql.com, "+db;
//		String user = "ty";
//		String pass = "WalaWala2020!";
//		
//		
//	
//		
//			
//			try{
//			try{
//				
//				Class.forName(driver);
//				
//				connect = DriverManager.getConnection(url,user,pass);
//				st= connect.createStatement();
//		
//				
//
//					
//				
//				
//			}catch(SQLException e){
//				System.out.println("SQL error"+ e.getMessage());
//				System.out.println("SQLState: "+e.getSQLState());
//				System.out.println("VendorError: "+e.getErrorCode());
//				
//			}
//			}catch(NullPointerException e){
//				
//			}
//		
//
//		try {
//		connect.close();
//		}catch(SQLException e){
//			System.out.println("SQL error"+ e.getMessage());
//			System.out.println("SQLState: "+e.getSQLState());
//			System.out.println("VendorError: "+e.getErrorCode());
//			
//		}

	}
}
