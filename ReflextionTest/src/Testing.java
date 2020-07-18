public class Testing {
	private String string1;
	private boolean boolean1;
	private String privateDude;
	private String protectedDude;

	public Testing(String string1, boolean boolean1, String privateDude, String protectedDude) {
		this.string1 = string1;
		this.boolean1 = boolean1;
		this.privateDude = privateDude;
		this.protectedDude = protectedDude;
	}
	public Testing() {
	}

	public Testing(String string1, boolean boolean1) {
		this.string1 = string1;
		this.boolean1 = boolean1;
	}
	public String getString1() {
		return string1;
	}

	public void setString1(String string1) {
		this.string1 = string1;
	}

	public boolean isBoolean1() {
		return boolean1;
	}

	public void setBoolean1(boolean boolean1) {
		this.boolean1 = boolean1;
	}

	public String getPrivateDude() {
		return privateDude;
	}

	public void setPrivateDude(String privateDude) {
		this.privateDude = privateDude;
	}

	public String getProtectedDude() {
		return protectedDude;
	}

	public void setProtectedDude(String protectedDude) {
		this.protectedDude = protectedDude;
	}
}
