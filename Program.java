import java.util.ArrayList;

public class Program {
	 
		private String nameP = "";
		private String direct = "";
		private ArrayList<String> classList = new ArrayList<>(); 
		private ArrayList<ClasData> classdataList = new ArrayList<>(); 
		private int pID=0;
		 
		Program(String dirS){
			setDirect(dirS);
			setNameP(gNameP(dirS));
			
			Main.getClasA(getDirect(), getClassList(),getClassdataList(),getpID());
			
		}
		
		public static String gNameP(String n) {
			String nameTemp="";
			int end = n.length();
			int tracker = n.length();
//			System.out.println(tracker);
			for(int i = 0; i<tracker; tracker--) {
//				System.out.println(n.charAt(tracker-1));
				
				if(n.charAt(tracker-1)=='\\') {
					nameTemp=n.substring(tracker, end);
					System.out.println(nameTemp);
					return nameTemp;
				}
				
				
			}
			
			return nameTemp;
		}
		
		

		public String getNameP() {
			return nameP;
		}

		public void setNameP(String nameP) {
			this.nameP = nameP;
		}

		public ArrayList<String> getClassList() {
			return classList;
		}

		public void setClassList(ArrayList<String> classList) {
			this.classList = classList;
		}

		public String getDirect() {
			return direct;
		}

		public void setDirect(String direct) {
			this.direct = direct;
		}

		public ArrayList<ClasData> getClassdataList() {
			return classdataList;
		}

		public void setClassdataList(ArrayList<ClasData> classdataList) {
			this.classdataList = classdataList;
		}

		public int getpID() {
			return pID;
		}

		public void setpID(int pID) {
			this.pID = pID;
		}
		
	
}
