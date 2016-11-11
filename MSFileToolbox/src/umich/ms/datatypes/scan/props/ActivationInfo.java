/*
 * Copyright (c) 2016 Dmitry Avtonomov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package umich.ms.datatypes.scan.props;

/**
 * @author Dmitry Avtonomov
 */
public class ActivationInfo {
    private String activationMethod;
    private Double activationEnergyLo;
    private Double activationEnergyHi;
    private Double activationTime;

    public String getActivationMethod() {
        return activationMethod;
    }

    public void setActivationMethod(String activationMethod) {
        this.activationMethod = activationMethod;
    }

    /** Either the beginning of the collision energy ramp or just the collision energy. In electronvolts. */
    public Double getActivationEnergyLo() {
        return activationEnergyLo;
    }

    /** Either the beginning of the collision energy ramp or just the collision energy. In electronvolts. */
    public void setActivationEnergyLo(Double activationEnergyLo) {
        this.activationEnergyLo = activationEnergyLo;
    }

    /** Either the end of the collision energy ramp or just the collision energy. In electronvolts. */
    public Double getActivationEnergyHi() {
        return activationEnergyHi;
    }

    /** Either the end of the collision energy ramp or just the collision energy. In electronvolts. */
    public void setActivationEnergyHi(Double activationEnergyHi) {
        this.activationEnergyHi = activationEnergyHi;
    }

    /** This would mean different things for different activation methods and might not be present most of the time. */
    public Double getActivationTime() {
        return activationTime;
    }

    public void setActivationTime(Double activationTime) {
        this.activationTime = activationTime;
    }

    @Override
    public String toString() {
        if (activationTime == null)
            return String.format("Activation:%s@[%.2f-%.2f]", activationMethod, activationEnergyLo, activationEnergyHi);
        else
            return String.format("Activation:%s@[%.2f-%.2f] for %.2f", activationMethod, activationEnergyLo, activationEnergyHi, activationTime);
    }
}
