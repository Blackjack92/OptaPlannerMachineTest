package walk.persistence;

import java.util.List;

import walk.domain.Machine;
import walk.domain.Order;
import walk.domain.Scheduling;

public interface IDatabase {

	public List<Machine> getMachines();

	public List<Order> getOrders();

	public List<Integer> getAllowedStartTimes();

	public List<Scheduling> getDefaultScheduling();
}
