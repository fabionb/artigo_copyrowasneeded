package br.com.fnbrandao.artigo_copyrowasneeded;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Test2Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TESTE2_SEQ")
	@SequenceGenerator(sequenceName = "TESTE2_SEQ", allocationSize = 1, name = "TESTE2_SEQ")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Test1Entity test1Entity;

	@OneToMany(mappedBy = "test2Entity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Test5Entity> test5Entities = new HashSet<>();

	@Column
	private String column1;

	@Column
	private String column2;

	@Column
	private String column3;

	@Column
	private String column4;

	@Column
	private String column5;

	@Column
	private String column6;

	@Column
	private String column7;

	@Column
	private String column8;

	@Column
	private String column9;

	@Column
	private String column10;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Test1Entity getTest1Entity() {
		return test1Entity;
	}

	public void setTest1Entity(Test1Entity test1Entity) {
		this.test1Entity = test1Entity;
	}

	public Set<Test5Entity> getTest5Entities() {
		return test5Entities;
	}

	public void setTest5Entities(Set<Test5Entity> test5Entities) {
		this.test5Entities = test5Entities;
	}

	public String getColumn1() {
		return column1;
	}

	public void setColumn1(String column1) {
		this.column1 = column1;
	}

	public String getColumn2() {
		return column2;
	}

	public void setColumn2(String column2) {
		this.column2 = column2;
	}

	public String getColumn3() {
		return column3;
	}

	public void setColumn3(String column3) {
		this.column3 = column3;
	}

	public String getColumn4() {
		return column4;
	}

	public void setColumn4(String column4) {
		this.column4 = column4;
	}

	public String getColumn5() {
		return column5;
	}

	public void setColumn5(String column5) {
		this.column5 = column5;
	}

	public String getColumn6() {
		return column6;
	}

	public void setColumn6(String column6) {
		this.column6 = column6;
	}

	public String getColumn7() {
		return column7;
	}

	public void setColumn7(String column7) {
		this.column7 = column7;
	}

	public String getColumn8() {
		return column8;
	}

	public void setColumn8(String column8) {
		this.column8 = column8;
	}

	public String getColumn9() {
		return column9;
	}

	public void setColumn9(String column9) {
		this.column9 = column9;
	}

	public String getColumn10() {
		return column10;
	}

	public void setColumn10(String column10) {
		this.column10 = column10;
	}

}
