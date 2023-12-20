public class WiseSaying {
	private int id;
	private String content;
	private String author;

	public WiseSaying(int id, String content, String author) {
		this.id = id;
		this.content = content;
		this.author = author;
	}

	@Override
	public String toString() {
		return "번호: " + id + ", 명언: " + content + ", 작가: " + author + "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}