package orz.nurseroastering.domain.solver;

import lombok.Data;
import orz.nurseroastering.common.domain.AbstractPersistable;
import orz.nurseroastering.domain.ShiftDate;

@Data
public class EmployeeRosterParametrization extends AbstractPersistable {

    private ShiftDate firstShiftDate;
    private ShiftDate lastShiftDate;
    private ShiftDate planningWindowStart;

    public int getFirstShiftDateDayIndex() {
        return firstShiftDate.getDayIndex();
    }

    public int getLastShiftDateDayIndex() {
        return lastShiftDate.getDayIndex();
    }

    public void setPlanningWindowStart(ShiftDate planningWindowStart) {
        this.planningWindowStart = planningWindowStart;
    }

    // ************************************************************************
    // Worker methods
    // ************************************************************************

    public boolean isInPlanningWindow(ShiftDate shiftDate) {
        return planningWindowStart.getDayIndex() <= shiftDate.getDayIndex();
    }

    @Override
    public String toString() {
        return firstShiftDate + " - " + lastShiftDate;
    }

}
