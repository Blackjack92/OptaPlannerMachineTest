package walk.domain;

import java.io.Serializable;

public class Machine implements Serializable {

	/**
	 * Generated serial version 
	 */
	private static final long serialVersionUID = -2051415021435689149L;

	private String name;

	public Machine(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}
