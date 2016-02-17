package walk.persistence;

import java.util.ArrayList;
import java.util.List;

import walk.domain.Machine;
import walk.domain.Order;
import walk.domain.Scheduling;
import walk.main.OptimizationSettings;

public class DatabaseFake implements IDatabase {

	@Override
	public List<Machine> getMachines() {
		List<Machine> machines = new ArrayList<>();
		for (int i = 0; i < OptimizationSettings.MACHINE_COUNT; i++) {
			machines.add(new Machine(String.valueOf(i)));
		}
		return machines;
	}

	@Override
	public List<Order> getOrders() {
		List<Order> orders = new ArrayList<>();
		for (int i = 0; i < OptimizationSettings.ORDER_COUNT; i++) {
			int duration = (int) Math.round(Math.random() * 10) + 1;
			String name = String.valueOf(i);

			orders.add(new Order(duration, name));
		}
		return orders;
	}

	@Override
	public List<Integer> getAllowedStartTimes() {
		List<Integer> allowedStartTimes = new ArrayList<>();
		for (int i = 0; i < OptimizationSettings.MAX_TIME; i++) {
			allowedStartTimes.add(i);
		}
		return allowedStartTimes;
	}

	@Override
	public List<Scheduling> getDefaultScheduling() {
		List<Scheduling> returnList = new ArrayList<>();
		for (int i = 0; i < OptimizationSettings.ORDER_COUNT; i++) {
			returnList.add(new Scheduling());
		}
		return returnList;
	}
}
