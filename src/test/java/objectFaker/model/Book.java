package objectFaker.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Book {

	private String title;
	
	private int yearEdition;

	private Map<String, String> translations;

	public Map<String, String> getTranslations() {
		return translations;
	}

	public void setTranslations(Map<String, String> translations) {
		this.translations = translations;
	}

	private Map<String, Map<String, Integer>> otherBooksByAuthors;

	public Map<String, Map<String, Integer>> getOtherBooksByAuthors() {
		return otherBooksByAuthors;
	}

	public void setOtherBooksByAuthors(Map<String, Map<String, Integer>> otherBooksByAuthors) {
		this.otherBooksByAuthors = otherBooksByAuthors;
	}

	private Map<String, List<Integer>> reviewDatesByAuthors;

	public Map<String, List<Integer>> getReviewDatesByAuthors() {
		return reviewDatesByAuthors;
	}

	public void setReviewDatesByAuthors(Map<String, List<Integer>> reviewDatesByAuthors) {
		this.reviewDatesByAuthors = reviewDatesByAuthors;
	}

	private List<String> authors;

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	private Set<String> authorsSet;

	public Set<String> getAuthorsSet() {
		return authorsSet;
	}

	public void setAuthorsSet(Set<String> authorsSet) {
		this.authorsSet = authorsSet;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYearEdition() {
		return yearEdition;
	}

	public void setYearEdition(int yearEdition) {
		this.yearEdition = yearEdition;
	}
	
	
}
