package com.zenika.resanet.model;

/**
 * A standard businessObject
 */
public abstract class AbstractBusinessObject {
	private Long id;

	/**
	 * Gets the technical identifier of this business object
	 * 
	 * @return unique identifier of the object
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
