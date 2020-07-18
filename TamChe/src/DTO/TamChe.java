package DTO;

public class TamChe implements Comparable<TamChe>{
	String id;
	int height;
	int width;
	double thick;
	int insurance;

	public TamChe(){
	}
	public TamChe(String id, int height, int width, double thick, int insurance) {
		this.id = id;
		this.height = height;
		this.width = width;
		this.thick = thick;
		this.insurance = insurance;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public double getThick() {
		return thick;
	}

	public void setThick(double thick) {
		this.thick = thick;
	}

	public int getInsurance() {
		return insurance;
	}

	public void setInsurance(int insurance) {
		this.insurance = insurance;
	}
	public TamChe input(boolean isNew){
		if(isNew)
			this.setId(InputValidation.getString("Nhap id cua tam che", "Id khong the trong"));
		this.setHeight(InputValidation.getAnInteger("Nhap chieu cao cua tam che", " chieu cao khong hop le", 0, Integer.MAX_VALUE));
		this.setWidth(InputValidation.getAnInteger("Nhap chieu rong cua tam che", " chieu rong khong hop le", 0, Integer.MAX_VALUE));
		this.setThick(InputValidation.getADouble("Nhap do day cua tam che", "Do day khong hop le", 0, Double.MAX_VALUE));
		this.setInsurance(InputValidation.getAnInteger("Nhap gia tri bao hanh", " gia tri bao hanh khong hop le", 0, Integer.MAX_VALUE));
		return this;
	}

	@Override
	public String toString() {
		return  "|id: " + id +
				"|chieu cao: " + height +
				"|chieu rong: " + width +
				"|do day: " + thick +
				"|bao hanh: " + insurance +
				'|';
	}

	@Override
	public int compareTo(TamChe o) {
		return this.getId().compareTo(o.getId());
	}
}
