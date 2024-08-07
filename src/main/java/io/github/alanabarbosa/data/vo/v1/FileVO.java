package io.github.alanabarbosa.data.vo.v1;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "files")
public class FileVO extends RepresentationModel<FileVO> implements Serializable {

	private static final long serialVersionUID = 1L;	
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;

    @Lob
    private byte[] data;   
    

	public FileVO() {
		super();
	}

	public FileVO(Long id, String filename, byte[] data) {
		super();
		this.id = id;
		this.filename = filename;
		this.data = data;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(data);
		result = prime * result + Objects.hash(filename, id);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileVO other = (FileVO) obj;
		return Arrays.equals(data, other.data) && Objects.equals(filename, other.filename)
				&& Objects.equals(id, other.id);
	}

    
}
