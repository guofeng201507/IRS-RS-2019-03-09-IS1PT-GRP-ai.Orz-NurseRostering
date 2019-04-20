package orz.nurseroastering.service;

import lombok.extern.slf4j.Slf4j;
import org.jdom.JDOMException;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import orz.nurseroastering.domain.NurseRoster;

import java.io.IOException;

@Slf4j
@Service
public class ShiftService {

    @Autowired
    NurseRosteringImporter nurseRosteringImporter;

    private static final Logger log = LoggerFactory.getLogger(NurseRosteringImporter.class);

    public NurseRoster plan(String fileName) throws JDOMException, IOException {

        SolverFactory<NurseRoster> solverFactory =
                SolverFactory.createFromXmlResource("config/nurseRosteringSolverConfig.xml");

        Solver solver = solverFactory.buildSolver();

        NurseRoster nurseRoster = nurseRosteringImporter.readSolution(fileName);

        nurseRoster = (NurseRoster) solver.solve(nurseRoster);

        final HardSoftScore score = nurseRoster.getScore();

        log.info(String.format("'%s' score ", score.toString()));

        return nurseRoster;
    }

}
