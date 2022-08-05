public class Result extends Avalara {
	private String commodityTitle;
	private int commodityID;

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

	public Result(String taxCode, String description, String commodityTitle, int commodityID) {
		super(taxCode, description);
		this.commodityTitle = commodityTitle;
		this.commodityID = commodityID;
	}

	@Override
	public String toString() {
		return commodityTitle + "," + commodityID + "," + getTaxCode() + "," + getDescription();
	}

}
