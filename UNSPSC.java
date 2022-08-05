import java.util.ArrayList;

public class UNSPSC {
	private ArrayList<Segment> segmentNodes;

	public UNSPSC() {
		segmentNodes = new ArrayList<>();

	}

	public UNSPSC(ArrayList<Segment> segmentNodes) {
		this.segmentNodes = segmentNodes;
	}

	public ArrayList<Segment> getSegmentNodes() {
		return segmentNodes;
	}

	public void setSegmentNodes(ArrayList<Segment> segmentNodes) {
		this.segmentNodes = segmentNodes;
	}

}
