package de.byedev.rpgtavern.persistence.entities;

import java.util.*;
import java.util.stream.Collectors;

public class Game {

    public static final transient Game NO_GAME = new Game("No Game", User.ANONYMOUS);

    private static final Comparator<Combatant> combatantSorter = new Comparator<Combatant>() {
        @Override
        public int compare(Combatant o1, Combatant o2) {
            if (o2==null)
            {
                return 1;
            }
            if (o1==null)
            {
                return -1;
            }
            return o1.getInitiative() >= o2.getInitiative() ? -1: 1;
        }
    };

    private String name;
    private User owner;

    private Map<User,Hero> player = new HashMap<>();
    private List<Message> messages = new ArrayList<>();
    private List<Combatant> combatants = new ArrayList<>();

    private List<GameGraphic> graphics = new ArrayList<>();

    private Combatant currentCombatant;
    private boolean inCombat;

    private String masterNotes;

    private CombatMap combatMap = new CombatMap(20,20);

    private Weather weather;

    private Video video;

    private BattleMap battleMap = BattleMap.TOKEN_MAP;

    public Game(String name, User owner) {
        this.name = name;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Map<User,Hero> getPlayer() {
        return player;
    }

    public void setPlayer(Map<User,Hero> player) {
        this.player = player;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public boolean inCombat() {
        return inCombat;
    }

    public void setInCombat(boolean inCombat) {
        this.inCombat = inCombat;
    }

    public List<Combatant> getCombatants() {
        if (combatants == null) {
            combatants = new ArrayList<>();
        }
        for (Combatant c : getPlayer().values()){
            combatants.remove(c);
        }
        return combatants;
    }

    public List<Combatant> getAllCombatants() {
        List<Combatant> allCombatants = new ArrayList();
        allCombatants.addAll(getPlayer().values().stream().filter(c -> !c.getName().equals(Hero.MASTER.getName())).collect(Collectors.toList()));
        allCombatants.addAll(getCombatants());
        sortCombatants(allCombatants);
        return allCombatants;
    }

    public void sortCombatants(List<Combatant> combatants)
    {
        combatants.sort(combatantSorter);
    }

    public Combatant getCurrentCombatant() {
        return currentCombatant;
    }

    public void setCurrentCombatant(Combatant currentCombatant) {
        this.currentCombatant = currentCombatant;
    }

    public void nextCombatant()
    {
        int idx = combatants.indexOf(currentCombatant);
        if (idx < 0 && combatants.size() > 0)
        {
            currentCombatant = combatants.get(0);
        }
        else if (combatants.size() > 0)
        {
            idx ++;
            if (idx >= combatants.size())
                idx = 0;
            currentCombatant = combatants.get(idx);
        }
        else
        {
            currentCombatant = null;
        }
    }

    public CombatMap getCombatMap() {
        if (combatMap == null)
            combatMap = new CombatMap(20,20);
        return combatMap;
    }

    public void setCombatMap(CombatMap combatMap) {
        this.combatMap = combatMap;
    }

    public List<GameGraphic> getGraphics() {
        if (graphics == null)
            graphics = new ArrayList<>();
        return graphics;
    }

    public void setGraphics(List<GameGraphic> graphics) {
        this.graphics = graphics;
    }

    public Weather getWeather() {
        if (weather == null)
            weather = new Weather();
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public String getMasterNotes() {
        return masterNotes;
    }

    public void setMasterNotes(String masterNotes) {
        this.masterNotes = masterNotes;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public BattleMap getBattleMap() {
        if (battleMap == null)
            return BattleMap.NO_MAP;
        return battleMap;
    }

    public void setBattleMap(BattleMap battleMap) {
        this.battleMap = battleMap;
    }
}
