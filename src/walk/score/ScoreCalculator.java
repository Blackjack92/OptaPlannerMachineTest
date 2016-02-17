package walk.score;

import java.util.List;

import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;

import walk.domain.OrderScheduling;
import walk.domain.Scheduling;
import walk.main.OptimizationSettings;

public class ScoreCalculator implements EasyScoreCalculator<OrderScheduling> {

	@Override
	public Score calculateScore(OrderScheduling orderScheduling) {

		int hardscore = 0;
		int softscore = 0;
		List<Scheduling> scheduling = orderScheduling.getScheduling();

		for (Scheduling s1 : scheduling) {
			// No machine or order is used
			if (s1.getMachine() == null || s1.getOrder() == null) {
				hardscore--;
				continue;
			}

			// The max time is reached
			if (s1.getStartTime() + s1.getOrder().getDuration() > OptimizationSettings.MAX_TIME) {
				hardscore--;
			}

			for (Scheduling s2 : scheduling) {
				if (s1 == s2) {
					continue;
				}

				// A order is used twice
				if (s1.getOrder() == s2.getOrder()) {
					hardscore--;
				}

				if (s1.getMachine() == s2.getMachine()) {
					// A machine has two jobs at the same time
					if (isInTime(s1, s2)) {
						hardscore--;
					}
				}
			}
		}

		return HardSoftScore.valueOf(hardscore, softscore);
	}

	private boolean isInTime(Scheduling scheduling1, Scheduling scheduling2) {
		int scheduling1Start = scheduling1.getStartTime();
		int scheduling1End = scheduling1.getStartTime() + scheduling1.getOrder().getDuration();
		int scheduling2Start = scheduling2.getStartTime();

		return scheduling1Start <= scheduling2Start && scheduling1End >= scheduling2Start;
	}

}
