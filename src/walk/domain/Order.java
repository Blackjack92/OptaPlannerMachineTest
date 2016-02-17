package walk.domain;

public class Order {

	private int duration;
	private String name;

	public Order(int duration, String name) {
		this.duration = duration;
		this.name = name;
	}

	public int getDuration() {
		return duration;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}
