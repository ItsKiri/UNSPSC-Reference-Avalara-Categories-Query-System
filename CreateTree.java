import java.util.ArrayList;

public class CreateTree {

	public CreateTree() {

	}

	public UNSPSC insert(ArrayList<Data> dataList) {
		UNSPSC tree = new UNSPSC();
		for0: for (Data data : dataList) {
			Commodity dataCommodity = new Commodity(data.getCommodityTitle(), data.getCommodityID());
			Class dataClass = new Class(data.getClassTitle(), data.getClassID(), dataCommodity);
			Family dataFamily = new Family(data.getFamilyTitle(), data.getFamilyID(), dataClass);
			Segment dataSegment = new Segment(data.getSegmentTitle(), data.getSegmentID(), dataFamily);
			if (tree.getSegmentNodes() == null) {
				ArrayList<Segment> segmentList = new ArrayList<Segment>();
				segmentList.add(dataSegment);
				tree.setSegmentNodes(segmentList);
				continue for0;
			}
			for (Segment treeSegment : tree.getSegmentNodes()) {
				if (treeSegment.getSegmentID() == dataSegment.getSegmentID()) {
					if (treeSegment.getFamilyNodes() == null) {
						ArrayList<Family> familyList = new ArrayList<Family>();
						familyList.add(dataFamily);
						treeSegment.setFamilyNodes(familyList);
						continue for0;
					}
					for (Family treeFamily : treeSegment.getFamilyNodes()) {
						if (treeFamily.getFamilyID() == dataFamily.getFamilyID()) {
							if (treeFamily.getClassNodes() == null) {
								ArrayList<Class> classList = new ArrayList<>();
								classList.add(dataClass);
								treeFamily.setClassNodes(classList);
								continue for0;
							}
							for (Class treeClass : treeFamily.getClassNodes()) {
								if (treeClass.getClassID() == dataClass.getClassID()) {
									if (treeClass.getCommodityNodes() == null) {
										ArrayList<Commodity> commodityList = new ArrayList<>();
										commodityList.add(dataCommodity);
										treeClass.setCommodityNodes(commodityList);
										continue for0;
									}
									ArrayList<Commodity> commodityList = new ArrayList<>();
									commodityList = treeClass.getCommodityNodes();
									commodityList.add(dataCommodity);
									treeClass.setCommodityNodes(commodityList);
									continue for0;

								}

							}
							ArrayList<Class> classList = new ArrayList<>();
							classList = treeFamily.getClassNodes();
							classList.add(dataClass);
							treeFamily.setClassNodes(classList);
							continue for0;

						}
					}
					ArrayList<Family> familyList = new ArrayList<>();
					familyList = treeSegment.getFamilyNodes();
					familyList.add(dataFamily);
					treeSegment.setFamilyNodes(familyList);
					continue for0;

				}
			}
			ArrayList<Segment> segmentList = new ArrayList<>();
			segmentList = tree.getSegmentNodes();
			segmentList.add(dataSegment);
			tree.setSegmentNodes(segmentList);
			continue for0;
		}
		return tree;
	}
}
