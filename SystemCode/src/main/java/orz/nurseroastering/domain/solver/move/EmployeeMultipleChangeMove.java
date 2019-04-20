/*
 * Copyright 2010 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package orz.nurseroastering.domain.solver.move;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.optaplanner.core.impl.heuristic.move.AbstractMove;
import org.optaplanner.core.impl.score.director.ScoreDirector;
import orz.nurseroastering.domain.Employee;
import orz.nurseroastering.domain.NurseRoster;
import orz.nurseroastering.domain.ShiftAssignment;

import java.util.*;

public class EmployeeMultipleChangeMove extends AbstractMove<NurseRoster> {

    private Employee fromEmployee;
    private List<ShiftAssignment> shiftAssignmentList;
    private Employee toEmployee;

    public EmployeeMultipleChangeMove(Employee fromEmployee, List<ShiftAssignment> shiftAssignmentList, Employee toEmployee) {
        this.fromEmployee = fromEmployee;
        this.shiftAssignmentList = shiftAssignmentList;
        this.toEmployee = toEmployee;
    }

    @Override
    public boolean isMoveDoable(ScoreDirector<NurseRoster> scoreDirector) {
        return !Objects.equals(fromEmployee, toEmployee);
    }

    @Override
    public EmployeeMultipleChangeMove createUndoMove(ScoreDirector<NurseRoster> scoreDirector) {
        return new EmployeeMultipleChangeMove(toEmployee, shiftAssignmentList, fromEmployee);
    }

    @Override
    protected void doMoveOnGenuineVariables(ScoreDirector<NurseRoster> scoreDirector) {
        for (ShiftAssignment shiftAssignment : shiftAssignmentList) {
            if (!shiftAssignment.getEmployee().equals(fromEmployee)) {
                throw new IllegalStateException("The shiftAssignment (" + shiftAssignment + ") should have the same employee ("
                        + shiftAssignment.getEmployee() + ") as the fromEmployee (" + fromEmployee + ").");
            }
            NurseRosteringMoveHelper.moveEmployee(scoreDirector, shiftAssignment, toEmployee);
        }
    }

    @Override
    public EmployeeMultipleChangeMove rebase(ScoreDirector<NurseRoster> destinationScoreDirector) {
        return new EmployeeMultipleChangeMove(destinationScoreDirector.lookUpWorkingObject(fromEmployee),
                rebaseList(shiftAssignmentList, destinationScoreDirector),
                destinationScoreDirector.lookUpWorkingObject(toEmployee));
    }

    @Override
    public Collection<? extends Object> getPlanningEntities() {
        return Collections.singletonList(shiftAssignmentList);
    }

    @Override
    public Collection<? extends Object> getPlanningValues() {
        return Arrays.asList(fromEmployee, toEmployee);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof EmployeeMultipleChangeMove) {
            EmployeeMultipleChangeMove other = (EmployeeMultipleChangeMove) o;
            return new EqualsBuilder()
                    .append(fromEmployee, other.fromEmployee)
                    .append(shiftAssignmentList, other.shiftAssignmentList)
                    .append(toEmployee, other.toEmployee)
                    .isEquals();
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(fromEmployee)
                .append(shiftAssignmentList)
                .append(toEmployee)
                .toHashCode();
    }

    @Override
    public String toString() {
        return shiftAssignmentList + " {? -> " + toEmployee + "}";
    }

}
