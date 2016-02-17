package walk.utils;

import java.util.List;

import walk.domain.Machine;
import walk.domain.OrderScheduling;
import walk.domain.Scheduling;
import walk.main.OptimizationSettings;

public class VisualizationHelper {

	public static void printSolutionAsGraphic(OrderScheduling solution) {
		for (Machine m : solution.getMachines()) {
			System.out.print(m.getName() + "[");

			List<Scheduling> schedulingFromMachine = solution.getSchedulingFromMachine(m);
			for (int index = 0; index <= OptimizationSettings.MAX_TIME; index++) {
				boolean schedulingFound = false;

				for (Scheduling scheduling : schedulingFromMachine) {

					if (scheduling.getOrder() != null && isTimeBetweenScheduling(scheduling, index)) {
						schedulingFound = true;
						int startTime = scheduling.getStartTime();
						int endTime = scheduling.getStartTime() + scheduling.getOrder().getDuration() - 1;

						if (startTime == index) {
							System.out.print(scheduling.getOrder().getName());
						} else if (endTime == index) {
							System.out.print("|");
						} else {
							System.out.print("-");
						}

						break;
					}
				}

				if (!schedulingFound) {
					System.out.print(".");
				}
			}

			System.out.print("]\n");
		}
	}

	private static boolean isTimeBetweenScheduling(Scheduling scheduling, int time) {
		int startTime = scheduling.getStartTime();
		int endTime = scheduling.getStartTime() + scheduling.getOrder().getDuration() - 1;
		return time >= startTime && time <= endTime;
	}

	public static void printSolutionAsTable(OrderScheduling solution) {
		for (Machine m : solution.getMachines()) {
			List<Scheduling> schedulingFromMachine = solution.getSchedulingFromMachine(m);
			for (Scheduling s : schedulingFromMachine) {
				System.out.println(s);
			}
		}
	}
}
