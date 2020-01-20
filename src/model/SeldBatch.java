package model;

import java.sql.Timestamp;

/**
 * SeldBatch entity. @author MyEclipse Persistence Tools
 */

public class SeldBatch implements java.io.Serializable {

	// Fields

	private Integer oid;
	private String idno;
	private Timestamp sdate;

	// Constructors

	/** default constructor */
	public SeldBatch() {
	}

	/** minimal constructor */
	public SeldBatch(Timestamp sdate) {
		this.sdate = sdate;
	}

	/** full constructor */
	public SeldBatch(String idno, Timestamp sdate) {
		this.idno = idno;
		this.sdate = sdate;
	}

	// Property accessors

	public Integer getOid() {
		return this.oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public String getIdno() {
		return this.idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public Timestamp getSdate() {
		return this.sdate;
	}

	public void setSdate(Timestamp sdate) {
		this.sdate = sdate;
	}

}