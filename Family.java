import java.util.ArrayList;

public class Family {
	private String familyTitle;
	private int familyID;
	private ArrayList<Class> classNodes;

	public Family(String familyTitle, int familyID, ArrayList<Class> classNodes) {

		this.familyTitle = familyTitle;
		this.familyID = familyID;
		this.classNodes = new ArrayList<>();
		this.classNodes = classNodes;
	}

	public Family(String familyTitle, int familyID, Class dataClass) {

		this.familyTitle = familyTitle;
		this.familyID = familyID;
		this.classNodes = new ArrayList<>();
		this.classNodes.add(dataClass);
	}

	public String getFamilyTitle() {
		return familyTitle;
	}

	public void setFamilyTitle(String familyTitle) {
		this.familyTitle = familyTitle;
	}

	public int getFamilyID() {
		return familyID;
	}

	public void setFamilyID(int familyID) {
		this.familyID = familyID;
	}

	public ArrayList<Class> getClassNodes() {
		return classNodes;
	}

	public void setClassNodes(ArrayList<Class> classNodes) {
		this.classNodes = classNodes;
	}

}
