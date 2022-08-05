
public class Avalara {
	private String taxCode;
	private String description;

	public Avalara(String taxCode, String description) {

		this.taxCode = taxCode;
		this.description = description;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toString() {
		return taxCode + "," + description;
	}

}
