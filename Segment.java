import java.util.ArrayList;

public class Segment {
	private String segmentTitle;
	private int segmentID;
	private ArrayList<Family> familyNodes;

	public Segment(String segmentTitle, int segmentID, ArrayList<Family> familyNodes) {

		this.segmentTitle = segmentTitle;
		this.segmentID = segmentID;
		this.familyNodes = new ArrayList<>();
		this.familyNodes = familyNodes;
	}

	public Segment(String segmentTitle, int segmentID, Family family) {

		this.segmentTitle = segmentTitle;
		this.segmentID = segmentID;
		this.familyNodes = new ArrayList<>();
		this.familyNodes.add(family);
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

	public ArrayList<Family> getFamilyNodes() {
		return familyNodes;
	}

	public void setFamilyNodes(ArrayList<Family> familyNodes) {
		this.familyNodes = familyNodes;
	}

}
