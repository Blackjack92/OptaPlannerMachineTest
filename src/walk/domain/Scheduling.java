package walk.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class Scheduling {

	// Planning variables
	@PlanningVariable(valueRangeProviderRefs = { "machineRange" }, nullable = true)
	private Machine machine;
	@PlanningVariable(valueRangeProviderRefs = { "startTimeRange" })
	private int startTime;
	@PlanningVariable(valueRangeProviderRefs = { "orderRange" }, nullable = true)
	private Order order;

	public Machine getMachine() {
		return machine;
	}

	public int getStartTime() {
		return startTime;
	}

	public Order getOrder() {
		return order;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("M[");
		builder.append(machine == null ? "-" : machine);
		builder.append("] O[");
		builder.append(order == null ? "-" : order);
		builder.append("] Start[");
		builder.append(String.format("%02d", startTime));
		builder.append("] End[");
		builder.append(order == null ? String.format("%02d", startTime) : String.format("%02d", startTime + order.getDuration() - 1));
		builder.append("]");
		return builder.toString();
	}
}
