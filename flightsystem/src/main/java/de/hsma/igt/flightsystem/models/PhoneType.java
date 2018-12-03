package de.hsma.igt.flightsystem.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PHONE_TYPE")
public class PhoneType implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2670447124815906665L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer phoneTypeID;
	
	@Column
	private String phoneTypeName;
	
	public PhoneType() {}
	
	public PhoneType(String phoneTypeName) 
	{
		this.phoneTypeName = phoneTypeName;
	}

	public Integer getPhoneTypeID() {
		return phoneTypeID;
	}

	public String getPhoneTypeName() {
		return phoneTypeName;
	}

	public void setPhoneTypeID(Integer phoneTypeID) {
		this.phoneTypeID = phoneTypeID;
	}

	public void setPhoneTypeName(String phoneTypeName) {
		this.phoneTypeName = phoneTypeName;
	}
}
