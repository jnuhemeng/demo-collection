package my.spring.demo.domain;

public class NewsBean {

	private String id;

	private String title;


	public NewsBean() {};

	public NewsBean(String id, String title) {
		this.id = id;
		this.title = title;
	}


	public String getId() { return id; }

	public void setId(String id) { this.id = id; }

	public String getTitle() { return title; }

	public void setTitle(String title) { this.title = title; }
	
	public String toString() {
		return "[NewsBean] id=" + id + ", title=" + title;
	}
}