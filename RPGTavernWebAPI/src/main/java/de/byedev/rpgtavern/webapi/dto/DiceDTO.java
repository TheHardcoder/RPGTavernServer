package de.byedev.rpgtavern.webapi.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.groupingBy;

@XmlRootElement(name="Dice")
public class DiceDTO {


    private RPGSystem system = RPGSystem.DSA;
    private boolean isAbilityCheck;
    private int mod;
    private int bonus;
    private List<DieDTO> dice = new ArrayList<>();

    public RPGSystem getSystem() {
        return system;
    }

    public void setSystem(RPGSystem system) {
        this.system = system;
    }

    public boolean isAbilityCheck() {
        return isAbilityCheck;
    }

    public void setAbilityCheck(boolean abilityCheck) {
        isAbilityCheck = abilityCheck;
    }

    public int getMod() {
        return mod;
    }

    public void setMod(int mod) {
        this.mod = mod;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public List<DieDTO> getDice() {
        return dice;
    }

    public void setDice(List<DieDTO> dice) {
        this.dice = dice;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        dice.stream().collect(groupingBy(DieDTO::getSize)).forEach((k,e) -> builder.append(e.size()+"d"+k.intValue()+"+"));
        builder.deleteCharAt(builder.length()-1);
        return builder.toString();
    }

    public void roll() {
        dice.stream().forEach(d -> d.roll());
    }
}
