import java.util.ArrayList;

public class Class {
	private String classTitle;
	private int classID;
	private ArrayList<Commodity> commodityNodes;

	public Class(String classTitle, int classID, ArrayList<Commodity> commodityNodes) {
		this.classTitle = classTitle;
		this.classID = classID;
		this.commodityNodes = new ArrayList<>();
		this.commodityNodes = commodityNodes;
	}

	public Class(String classTitle, int classID, Commodity commodity) {
		this.classTitle = classTitle;
		this.classID = classID;
		this.commodityNodes = new ArrayList<>();
		this.commodityNodes.add(commodity);
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

	public ArrayList<Commodity> getCommodityNodes() {
		return commodityNodes;
	}

	public void setCommodityNodes(ArrayList<Commodity> commodityNodes) {
		this.commodityNodes = commodityNodes;
	}

}
