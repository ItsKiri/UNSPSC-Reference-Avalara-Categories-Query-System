
public class Data {
	private String segmentTitle;
	private int segmentID;
	private String familyTitle;
	private int familyID;
	private String classTitle;
	private int classID;
	private String commodityTitle;
	private int commodityID;

	public Data(String segmentTitle, int segmentID, String familyTitle, int familyID, String classTitle, int classID,
			String commodityTitle, int commodityID) {

		this.segmentTitle = segmentTitle;
		this.segmentID = segmentID;
		this.familyTitle = familyTitle;
		this.familyID = familyID;
		this.classTitle = classTitle;
		this.classID = classID;
		this.commodityTitle = commodityTitle;
		this.commodityID = commodityID;
	}

	public String getSegmentTitle() {
		return segmentTitle;
	}

	public void setSegmentTitle(String segmentTitle) {
		this.segmentTitle = segmentTitle;
	}

	public int getSegmentID() {
		return segmentID;
	}

	public void setSegmentID(int segmentID) {
		this.segmentID = segmentID;
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

	public String getClassTitle() {
		return classTitle;
	}

	public void setClassTitle(String classTitle) {
		this.classTitle = classTitle;
	}

	public int getClassID() {
		return classID;
	}

	public void setClassID(int classID) {
		this.classID = classID;
	}

	public String getCommodityTitle() {
		return commodityTitle;
	}

	public void setCommodityTitle(String commodityTitle) {
		this.commodityTitle = commodityTitle;
	}

	public int getCommodityID() {
		return commodityID;
	}

	public void setCommodityID(int commodityID) {
		this.commodityID = commodityID;
	}

	@Override
	public String toString() {
		return segmentTitle + " " + segmentID + " " + familyTitle + " " + familyID + " " + classTitle + " " + classID
				+ " " + commodityTitle + " " + commodityID;
	}

}
