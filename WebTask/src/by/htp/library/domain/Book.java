package by.htp.library.domain;

public class Book {
	private int id;
	private String nazvanie;
	private String avtor;
	private String image;

	public Book() {

	}

	public Book(int id,  String avtor, String nazvanie, String image) {
		super();
		this.id = id;
		this.avtor =avtor;
		this.nazvanie =nazvanie;
		this.setImage(image);
		
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNazvanie() {
		return nazvanie;
	}

	public void setNazvanie(String nazvanie) {
		this.nazvanie = nazvanie;
	}

	public String getAvtor() {
		return avtor;
	}

	public void setAvtor(String avtor) {
		this.avtor = avtor;
	}

	public String toString() {
		return this.avtor + " " + this.nazvanie;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
