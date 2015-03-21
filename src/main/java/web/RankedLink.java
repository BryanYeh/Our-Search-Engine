package web;

public class RankedLink {
	private String title;
	private String url;
	private String description;
	
	public RankedLink(String linkURL, String string, String string2) {
		this.url = linkURL;
		this.title = string;
		this.description = string2;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
