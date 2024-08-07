package io.github.alanabarbosa.integrationtests.vo.wrappers;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.github.alanabarbosa.integrationtests.vo.BookVO;

public class BookEmbeddedVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("bookVOList")
	private List<BookVO> books;

	public BookEmbeddedVO() {}

	public List<BookVO> getBooks() {
		return books;
	}

	public void setBooks(List<BookVO> books) {
		this.books = books;
	}

	@Override
	public int hashCode() {
		return Objects.hash(books);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookEmbeddedVO other = (BookEmbeddedVO) obj;
		return Objects.equals(books, other.books);
	}
}