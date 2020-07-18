package DTO;

import java.util.TreeSet;

public class TamCheTree extends TreeSet<TamChe> {
	public TamCheTree Menu(){
		System.out.println("---------------------------------");
		System.out.println("1. Them tam che moi");
		System.out.println("2. Tim tam che theo id");
		System.out.println("3. Xoa tam che theo id");
		System.out.println("4. Sua du lieu tam che theo id");
		System.out.println("5. Xuat tat ca tam che");
		System.out.println("0. Thoat");
		System.out.println("---------------------------------");
		int option = InputValidation.getAnInteger("nhap lua chon: ", " lua chon khong hop le", 0, 5);
		String id;
		switch (option){
			case 1:
				if(this.add(new TamChe().input(true)))
					System.out.println("them vao thanh cong");
				else
					System.out.println("them vao that bai");
				break;
			case 2:
				id = InputValidation.getString("Nhap id cua tam che", "Id khong the trong");
				for (TamChe tamChe : this) {
					if(tamChe.getId().equalsIgnoreCase(id))
						System.out.println(tamChe);
				}
				break;
			case 3:
				id = InputValidation.getString("Nhap id cua tam che", "Id khong the trong");
				this.removeIf(tamChe -> tamChe.getId().equalsIgnoreCase(id));
				break;
			case 4:
				id = InputValidation.getString("Nhap id cua tam che", "Id khong the trong");
				for (TamChe tamChe : this)
					if (tamChe.getId().equalsIgnoreCase(id))
						tamChe.input(false);
				break;
			case 5:
				if(this.isEmpty()) System.out.println("danh sach trong");
				else for (TamChe tamChe : this) System.out.println(tamChe);
				break;
			case 0:
				System.out.println("bye!!!");
				System.exit(0);
				break;
			default:
				System.out.println("how did you get here?");
		}
		return this.Menu();
	}

	public static void main(String[] args) {
		new TamCheTree().Menu();
//		System.out.println("mathematics".matches("physical|mathematic|chemistry"));
	}
}
