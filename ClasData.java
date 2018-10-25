import java.util.ArrayList;

public class ClasData {
	private ArrayList<String> cHTMList = new ArrayList<>(); 
	private String nameC ="";
	private int pIDc=0;
	private String code = "";
	
	ClasData(String a){
		 setNameC(makeName(a));
//		 cHTMList.add(Main.dataFix(a));
//		 System.out.println(cHTMList.size());
		 setCode(Main.dataFix(a));
//		 System.out.println(nameC);
//		 System.out.println(code);
	}
	
	
	public static String makeName(String n) {
		String nameTemp="";
		int end = n.length();
		int tracker = n.length();
//		System.out.println(tracker);
		for(int i = 0; i<tracker; tracker--) {
//			System.out.println(n.charAt(tracker-1));
			
			if(n.charAt(tracker-1)=='\\') {
				nameTemp=n.substring(tracker);
				int nTrack = nameTemp.length();
				for(int j = 0; j<nameTemp.length(); nTrack--) {
					if(nameTemp.charAt(nTrack-1)=='.') {
						String finName= nameTemp.substring(0, nTrack-1);
//						System.out.println(finName);
						return finName;
					}
					
				}
				
				System.out.println(nameTemp);
				
				
			}
			
			
		}
		
		return nameTemp;
	}
	
	
	public ArrayList<String> getcHTMList() {
		return cHTMList;
	}
	public void setcHTMList(ArrayList<String> cHTMList) {
		this.cHTMList = cHTMList;
	}
	public String getNameC() {
		return nameC;
	}
	public void setNameC(String nameC) {
		this.nameC = nameC;
	}


	public int getpIDc() {
		return pIDc;
	}


	public void setpIDc(int pID) {
		this.pIDc = pID;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}
	

}
