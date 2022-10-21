package de.byedev.rpgtavern.persistence.entities;

import de.byedev.rpgtavern.persistence.util.HeroXMLParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Hero implements Combatant {

    public static final Logger LOG = LoggerFactory.getLogger(Hero.class);

    public static final transient Hero MASTER = new Hero("","Master","","","","", 0,new ArrayList<>(),new ArrayList<>(),new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static final transient Hero DUMMY = new Hero("","Dummy","","","","", 0,new ArrayList<>(),new ArrayList<>(),new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

    private String rawData;

    private String name;
    private String race;
    private String profession;
    private String culture;
    private String gender;

    private int xp;

    private int currentLife;
    private int currentAsp;
    private int currentKarma;
    private int ini;

    private List<HeroProperty> properties = new ArrayList<>();
    private List<Talent> talents = new ArrayList<>();
    private List<Spell> spells = new ArrayList<>();
    private List<CombatTalent> combatTalents = new ArrayList<>();
    private List<Weapon> weapons = new ArrayList<>();
    private List<Advantage> advantages = new ArrayList<>();
    private List<Speciality> specialities = new ArrayList<>();

    private String notes;

    private String heroIcon = "img/char.svg";

    public Hero(String rawData, String name, String race, String profession, String culture, String gender, int xp, List<HeroProperty> properties, List<Talent> talents, List<Spell> spells, List<CombatTalent> combatTalents, List<Advantage> advantages, List<Speciality> specialities) {
        this.rawData = rawData;
        this.name = name;
        this.race = race;
        this.profession = profession;
        this.culture = culture;
        this.gender = gender;
        this.xp = xp;
        this.properties = properties;
        this.talents = talents;
        this.spells = spells;
        this.combatTalents = combatTalents;
        this.advantages = advantages;
        this.specialities = specialities;
        if (this.talents != null)
        {
            int selbstb = this.talents.stream().filter(t -> t.getName().equals("Selbstbeherrschung")).findAny().map(Ability::getValue).orElse(0);
            int sinnens = this.talents.stream().filter(t -> t.getName().equals("Sinnenschärfe")).findAny().map(Ability::getValue).orElse(0);
            this.talents.add(new Talent("Wache halten", "(MU/IN/FF)",(selbstb+sinnens+sinnens)/3));
        }
        this.currentLife = Integer.MAX_VALUE;
        updateCalculated();
        if (this.talents != null)
        {
            int wild = this.talents.stream().filter(t -> t.getName().equals("Wildnisleben")).findAny().map(Ability::getValue).orElse(0);
            int tierk = this.talents.stream().filter(t -> t.getName().equals("Tierkunde")).findAny().map(Ability::getValue).orElse(0);
            int faehrt = this.talents.stream().filter(t -> t.getName().equals("Fährtensuchen")).findAny().map(Ability::getValue).orElse(0);
            int schlei = this.talents.stream().filter(t -> t.getName().equals("Schleichen")).findAny().map(Ability::getValue).orElse( 0);
            int fernk = this.combatTalents.stream().filter(t -> t.getName().equals("Bogen")).findAny().map(CombatTalent::getAttack).orElse( 0);
            int max = 2 * Math.min(wild, Math.min(tierk, Math.min(faehrt, Math.min(schlei, fernk))));
            int val = (wild+tierk+faehrt+schlei+fernk)/5;
            this.talents.add(new Talent("Pirschjagd", "(MU/IN/GE)", Math.min(val, max)));
        }
    }

    public void updateHero(Hero hero) {
        this.rawData = hero.rawData;
        this.name = hero.name;
        this.race = hero.race;
        this.profession = hero.profession;
        this.culture = hero.culture;
        this.gender = hero.gender;
        this.xp = hero.xp;
        this.properties = hero.properties;
        this.talents = hero.talents;
        this.spells = hero.spells;
        this.combatTalents = hero.combatTalents;
        this.advantages = hero.advantages;
        this.specialities = hero.specialities;
        updateCalculated();
    }

    private void updateCalculated()
    {
        if (properties.stream().noneMatch(p -> p.getNames().equals(PropertyNames.DODGE)))
            properties.add(new HeroProperty(PropertyNames.DODGE,0));
        int bonus = 0;
        if (hasSpeciality("Ausweichen I"))
            bonus += 3;
        if (hasSpeciality("Ausweichen II"))
            bonus += 3;
        if (hasSpeciality("Ausweichen III"))
            bonus += 3;
        Talent ath = talents.stream().filter(t -> t.getName().equalsIgnoreCase("Athletik")).findAny().orElse(null);
        if (ath != null && ath.getValue() > 9)
            bonus += (ath.getValue()-9)/3;
        getProperty(PropertyNames.DODGE).get().setValue(getPropertyValue(PropertyNames.BASE_DEFENCE)+bonus);
        if (combatTalents.stream().filter(ct -> ct.getName().equals(CombatTalent.BASE_TALENT_NAME)).findAny().isEmpty())
            combatTalents.add( new CombatTalent(getAT(),getPA(), CombatTalent.BASE_TALENT_NAME));
        talents.stream().filter(t -> t.getName().equalsIgnoreCase("bogen")).findAny()
                .ifPresent(bogen -> combatTalents.add(new CombatTalent(getPropertyValue(PropertyNames.BASE_RANGED_AT) + bogen.getValue(), getPA(), "Bogen")));
        talents.stream().filter(t -> t.getName().equalsIgnoreCase("armbrust")).findAny()
                .ifPresent(armbrust -> combatTalents.add(new CombatTalent(getPropertyValue(PropertyNames.BASE_RANGED_AT) + armbrust.getValue(), getPA(), "Armbrust")));
    }

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public List<HeroProperty> getProperties() {
        return properties;
    }

    public Optional<HeroProperty> getProperty(PropertyNames name)
    {
        return getProperties().stream().filter(p -> p.getNames().equals(name)).findAny();
    }

    public int getPropertyValue(PropertyNames name)
    {
        return getProperty(name).orElse(HeroProperty.NONE).getValue();
    }

    public int getTotalLife(){
        return  getPropertyValue(PropertyNames.LIFE) + Math.round((getPropertyValue(PropertyNames.CONSTITUTION) * 2 + getPropertyValue(PropertyNames.STRENGTH))/2.0f);
    }

    public int getMagicResistance()
    {
        return getPropertyValue(PropertyNames.MAGIC_RESISTENZ) + Math.round((getPropertyValue(PropertyNames.COURAGE) + getPropertyValue(PropertyNames.WISDOM) + getPropertyValue(PropertyNames.CONSTITUTION))/5.0f);
    }

    public int getMagicEnergy()
    {
        if (getProperty(PropertyNames.MAGIC_ENERGY).isEmpty() || getProperty(PropertyNames.MAGIC_ENERGY).get().getValue() == 0)
        {
            return 0;
        }
        if (specialities != null && specialities.contains(new Speciality("Gefäß der Sterne")))
        {
            return getPropertyValue(PropertyNames.MAGIC_ENERGY) + Math.round((getPropertyValue(PropertyNames.COURAGE) + getPropertyValue(PropertyNames.INTUITION) + 2*getPropertyValue(PropertyNames.CHARISMA))/2.0f);
        }
        else {
            return getPropertyValue(PropertyNames.MAGIC_ENERGY) + Math.round((getPropertyValue(PropertyNames.COURAGE) + getPropertyValue(PropertyNames.INTUITION) + getPropertyValue(PropertyNames.CHARISMA))/2.0f);
        }
    }

    public PropertyNames getPrimaryPropertyName()
    {
        if (race.toLowerCase().contains("elf"))
            return PropertyNames.INTUITION;
        if (profession.toLowerCase().contains("hex"))
            return PropertyNames.CHARISMA;
        return PropertyNames.WISDOM;
    }

    public int getEndurance()
    {
        return getPropertyValue(PropertyNames.ENDURANCE) + Math.round((getPropertyValue(PropertyNames.COURAGE) + getPropertyValue(PropertyNames.CONSTITUTION) + getPropertyValue(PropertyNames.AGILITY))/2.0f);
    }

    public void setProperties(List<HeroProperty> properties) {
        this.properties = properties;
    }

    public List<Talent> getTalents() {
        return talents;
    }

    public void setTalents(List<Talent> talents) {
        this.talents = talents;
    }

    public List<Spell> getSpells() {
        return spells;
    }

    public void setSpells(List<Spell> spells) {
        this.spells = spells;
    }

    public List<CombatTalent> getCombatTalents() {
        return combatTalents;
    }

    public void setCombatTalents(List<CombatTalent> combatTalents) {
        this.combatTalents = combatTalents;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    public List<Advantage> getAdvantages() {
        return advantages;
    }

    public void setAdvantages(List<Advantage> advantages) {
        this.advantages = advantages;
    }

    public List<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<Speciality> specialities) {
        this.specialities = specialities;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int getInitiative() {
        return ini;
    }

    public void setIni(int ini) {
        this.ini = ini;
    }

    @Override
    public int getMaxLife() {
        return getTotalLife();
    }

    @Override
    public void setCurrentLife(int life) {
        if (life > getMaxLife())
            currentLife = getMaxLife();
        else
            currentLife = life;
    }

    @Override
    public int getCurrentLife() {
        if (currentLife > getMaxLife())
            currentLife = getMaxLife();
        return currentLife;
    }

    public void setCurrentAsp(int asp) {
        currentAsp = Math.min(asp, getMagicEnergy());
    }

    public int getCurrentAsp() {
        if (currentAsp > getMagicEnergy())
            currentAsp = getMagicEnergy();
        return currentAsp;
    }

    public void setCurrentKarma(int karma) {
        currentKarma = Math.min(karma, getPropertyValue(PropertyNames.KARMA));
    }

    public int getCurrentKarma() {
        if (currentKarma > getPropertyValue(PropertyNames.KARMA))
            currentKarma = getPropertyValue(PropertyNames.KARMA);
        return currentKarma;
    }

    @Override
    public int getAT() {
        return getPropertyValue(PropertyNames.BASE_ATTACK);
    }

    @Override
    public int getPA() {
        return getPropertyValue(PropertyNames.BASE_DEFENCE);
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean hasSpeciality(String name)
    {
        return specialities.stream().anyMatch(s -> s.getName().equals(name));
    }

    public void reparse()
    {
        if (rawData == null)
            return;
        try {
            updateHero(HeroXMLParser.fromXmlData(getRawData()));
        } catch (Exception e) {
            LOG.warn("Error while reparsing the hero: " + e.getMessage());
        }
    }

    public int getLifeRegen() {
        int reg = new Random().nextInt(6)+1;
        Optional<Advantage> adv = advantages.stream().filter(a -> a.getName().equals("Schnelle Heilung")).findAny();
        if (adv.isPresent())
            reg += adv.get().getTextAsInt();
        Optional<Advantage> dis = advantages.stream().filter(a -> a.getName().equals("Schlechte Regeneration")).findAny();
        if (dis.isPresent())
            reg += dis.get().getTextAsInt();
        if (new Random().nextInt(20) +1 <= getPropertyValue(PropertyNames.CONSTITUTION))
            reg ++;
        return reg;
    }

    public int getAspRegen() {
        if (getMagicEnergy() == 0)
            return 0;
        int reg = new Random().nextInt(6)+1;
        if (hasSpeciality("Meisterliche Regeneration"))
            reg = Math.round(getPropertyValue(getPrimaryPropertyName())/3.0f)+1;
        Optional<Advantage> adv = advantages.stream().filter(a -> a.getName().equals("Astrale Regeneration")).findAny();
        if (adv.isPresent())
            reg += adv.get().getTextAsInt();
        if (hasSpeciality("Regeneration I"))
            reg ++;
        if (hasSpeciality("Regeneration II"))
            reg ++;
        if (new Random().nextInt(20) +1 <= getPropertyValue(PropertyNames.INTUITION))
            reg ++;
        return reg;
    }

    public String getHeroIcon() {
        if (heroIcon == null)
            heroIcon = "img/char.svg";
        return heroIcon;
    }

    public void setHeroIcon(String heroIcon) {
        this.heroIcon = heroIcon;
    }

    public static class Builder {
        private final String rawData;

        private final String name;
        private String race;
        private String profession;
        private String culture;
        private String gender;

        private int xp;

        private List<HeroProperty> properties = new ArrayList<>();
        private List<Talent> talents = new ArrayList<>();
        private List<Spell> spells = new ArrayList<>();
        private List<CombatTalent> combatTalents = new ArrayList<>();
        private List<Advantage> advantages = new ArrayList<>();
        private List<Speciality> specialities = new ArrayList<>();

        public Builder(String rawData, String name) {
            this.rawData = rawData;
            this.name = name;
        }

        public Builder appendRace(String race) {
            this.race = race;
            return this;
        }

        public Builder appendProfession(String profession) {
            this.profession = profession;
            return this;
        }

        public Builder appendCulture(String culture) {
            this.culture = culture;
            return this;
        }

        public Builder appendGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder appendProperties(List<HeroProperty> properties) {
            if(properties != null)
                this.properties = properties;
            return this;
        }

        public Builder appendTalents(List<Talent> talents) {
            if(talents != null)
                this.talents = talents;
            return this;
        }

        public Builder appendSpells(List<Spell> spells) {
            if(spells != null)
                this.spells = spells;
            return this;
        }

        public Builder appendAdvantages(List<Advantage> advantages) {
            if(advantages != null)
                this.advantages = advantages;
            return this;
        }

        public Builder appendSpecialities(List<Speciality> specialities) {
            if(specialities != null)
                this.specialities = specialities;
            return this;
        }

        public Builder appendXp(int xp) {
            this.xp = xp;
            return this;
        }

        public Builder appendCombatTalents(List<CombatTalent> combatTalents) {
            if(combatTalents != null)
                this.combatTalents = combatTalents;
            return this;
        }

        public Hero build() {
            return new Hero(rawData,name,race,profession,culture,gender, xp,properties,talents,spells,combatTalents,advantages,specialities);
        }
    }
}
