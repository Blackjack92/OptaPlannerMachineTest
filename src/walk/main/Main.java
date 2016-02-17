package walk.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.optaplanner.core.api.domain.solution.Solution;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.api.solver.event.BestSolutionChangedEvent;
import org.optaplanner.core.api.solver.event.SolverEventListener;

import walk.domain.OrderScheduling;
import walk.score.ScoreCalculator;
import walk.utils.VisualizationHelper;

public class Main {

	public static final String SOLVER_CONFIG = "src/walk/score/SolverConfig.xml";

	public static Solver createSolver() {
		SolverFactory solverFactory = SolverFactory.createFromXmlReader(new BufferedReader(new InputStreamReader(ScoreCalculator.class.getResourceAsStream("SolverConfig.xml"))));
		return solverFactory.buildSolver();
	}

	public static void main(String[] args) {

		if (args.length > 0) {
			// TODO: Try to parse the first part as solver path
		}

		// Solve with OptaPlanner
		Solver solver = createSolver();
		solver.addEventListener(new SolverEventListener<Solution>() {

			@Override
			public void bestSolutionChanged(BestSolutionChangedEvent<Solution> arg) {
				System.out.println("\r\n");
				System.out.println("Best solution [" + arg.getNewBestSolution().getScore() + "]");
				System.out.println("");
				VisualizationHelper.printSolutionAsGraphic((OrderScheduling) arg.getNewBestSolution());
				System.out.println("");
				VisualizationHelper.printSolutionAsTable((OrderScheduling) arg.getNewBestSolution());
			}
		});

		solver.solve(new OrderScheduling());
		solver.getBestSolution();
	}

}
