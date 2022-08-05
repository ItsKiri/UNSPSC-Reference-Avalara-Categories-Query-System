
public class Commodity {
	private String commodityTitle;
	private int commodityID;

	public Commodity(String commodityTitle, int commodityID) {

		this.commodityTitle = commodityTitle;
		this.commodityID = commodityID;
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

}
