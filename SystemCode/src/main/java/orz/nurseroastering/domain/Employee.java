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

package orz.nurseroastering.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import orz.nurseroastering.common.domain.AbstractPersistable;
import orz.nurseroastering.domain.contract.Contract;
import orz.nurseroastering.domain.request.DayOffRequest;
import orz.nurseroastering.domain.request.DayOnRequest;
import orz.nurseroastering.domain.request.ShiftOffRequest;
import orz.nurseroastering.domain.request.ShiftOnRequest;

import java.util.Map;

@XStreamAlias("Employee")
public class Employee extends AbstractPersistable {

    private String code;
    private String name;
    private Contract contract;

    private Map<ShiftDate, DayOffRequest> dayOffRequestMap;
    private Map<ShiftDate, DayOnRequest> dayOnRequestMap;
    private Map<Shift, ShiftOffRequest> shiftOffRequestMap;
    private Map<Shift, ShiftOnRequest> shiftOnRequestMap;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public int getWeekendLength() {
        return getContract().getWeekendLength();
    }

    @JsonIgnore
    public Map<ShiftDate, DayOffRequest> getDayOffRequestMap() {
        return dayOffRequestMap;
    }

    public void setDayOffRequestMap(Map<ShiftDate, DayOffRequest> dayOffRequestMap) {
        this.dayOffRequestMap = dayOffRequestMap;
    }
    @JsonIgnore
    public Map<ShiftDate, DayOnRequest> getDayOnRequestMap() {
        return dayOnRequestMap;
    }

    public void setDayOnRequestMap(Map<ShiftDate, DayOnRequest> dayOnRequestMap) {
        this.dayOnRequestMap = dayOnRequestMap;
    }
    @JsonIgnore
    public Map<Shift, ShiftOffRequest> getShiftOffRequestMap() {
        return shiftOffRequestMap;
    }

    public void setShiftOffRequestMap(Map<Shift, ShiftOffRequest> shiftOffRequestMap) {
        this.shiftOffRequestMap = shiftOffRequestMap;
    }
    @JsonIgnore
    public Map<Shift, ShiftOnRequest> getShiftOnRequestMap() {
        return shiftOnRequestMap;
    }

    public void setShiftOnRequestMap(Map<Shift, ShiftOnRequest> shiftOnRequestMap) {
        this.shiftOnRequestMap = shiftOnRequestMap;
    }

    public String getLabel() {
        //return "Employee " + name;
    	return name;
    }

    @Override
    public String toString() {
        if (name == null) {
            return super.toString();
        }
        return name;
    }

}
