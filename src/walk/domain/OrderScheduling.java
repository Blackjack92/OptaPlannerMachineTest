package walk.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.Solution;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import walk.persistence.DatabaseFake;
import walk.persistence.IDatabase;

@PlanningSolution
public class OrderScheduling implements Solution<HardSoftScore> {

	// This fake database is used for generating the machines, startTimes and orders
	// No constructor injection because a default constructor is needed from OptaPlanner
	private static IDatabase database = new DatabaseFake();

	@ValueRangeProvider(id = "machineRange")
	private List<Machine> machines;
	@ValueRangeProvider(id = "startTimeRange")
	private List<Integer> startTimes;
	@ValueRangeProvider(id = "orderRange")
	private List<Order> orders;

	// Scheduling is needed for returning the PlanningEntityCollectionProperty
	private List<Scheduling> scheduling;
	private HardSoftScore score;

	public OrderScheduling() {
		// Init machines
		machines = database.getMachines();
		// Init times
		startTimes = database.getAllowedStartTimes();
		// Init orders
		orders = database.getOrders();
		// Init default scheduling list as empty list
		scheduling = database.getDefaultScheduling();
	}

	// OptaPlanner methods
	@PlanningEntityCollectionProperty
	public List<Scheduling> getScheduling() {
		return scheduling;
	}

	@Override
	public Collection<? extends Object> getProblemFacts() {
		List<Object> facts = new ArrayList<Object>();
		facts.add(machines);
		facts.add(orders);
		return facts;
	}

	@Override
	public HardSoftScore getScore() {
		return score;
	}

	@Override
	public void setScore(HardSoftScore score) {
		this.score = score;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public List<Machine> getMachines() {
		return machines;
	}

	public List<Scheduling> getSchedulingFromMachine(Machine machine) {
		List<Scheduling> returnList = new ArrayList<>();
		for (Scheduling s : scheduling) {
			if (s.getMachine() == machine) {
				returnList.add(s);
			}
		}

		// Sort collection by start time
		Collections.sort(returnList, new Comparator<Scheduling>() {
			@Override
			public int compare(Scheduling s1, Scheduling s2) {
				return s1.getStartTime() - s2.getStartTime();
			}
		});

		return returnList;
	}
}
