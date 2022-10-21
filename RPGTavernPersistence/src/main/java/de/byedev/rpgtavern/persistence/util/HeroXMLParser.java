package de.byedev.rpgtavern.persistence.util;

import de.byedev.rpgtavern.persistence.entities.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HeroXMLParser {

    public static Hero fromXmlData(String rawData) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(rawData.getBytes()));
        doc.getDocumentElement().normalize();
        Node heroRoot = doc.getDocumentElement().getElementsByTagName("held").item(0);
        Element hero = null;
        if (heroRoot.getNodeType() == Node.ELEMENT_NODE)
        {
            hero = (Element) heroRoot;
        }
        String name = hero.getAttribute("name");
        Element basis = null;
        Element property = null;
        Element talent = null;
        Element spell = null;
        Element combat = null;
        Element vt = null;
        Element sf = null;
        NodeList heroNodes = hero.getChildNodes();
        for (int i = 0; i < heroNodes.getLength(); i++)
        {
            if (basis == null && heroNodes.item(i).getNodeName().equals("basis"))
            {
                basis = (Element) heroNodes.item(i);
            }
            if (property == null && heroNodes.item(i).getNodeName().equals("eigenschaften"))
            {
                property = (Element) heroNodes.item(i);
            }
            if (talent == null && heroNodes.item(i).getNodeName().equals("talentliste"))
            {
                talent = (Element) heroNodes.item(i);
            }
            if (spell == null && heroNodes.item(i).getNodeName().equals("zauberliste"))
            {
                spell = (Element) heroNodes.item(i);
            }
            if (combat == null && heroNodes.item(i).getNodeName().equals("kampf"))
            {
                combat = (Element) heroNodes.item(i);
            }
            if (vt == null && heroNodes.item(i).getNodeName().equals("vt"))
            {
                vt = (Element) heroNodes.item(i);
            }
            if (sf == null && heroNodes.item(i).getNodeName().equals("sf"))
            {
                sf = (Element) heroNodes.item(i);
            }
        }
        Hero.Builder hBuilder = new Hero.Builder(rawData,name);
        hBuilder.appendCulture(((Element) basis.getElementsByTagName("kultur").item(0)).getAttribute("string"));
        hBuilder.appendRace(((Element) basis.getElementsByTagName("rasse").item(0)).getAttribute("string"));
        hBuilder.appendGender(((Element) basis.getElementsByTagName("geschlecht").item(0)).getAttribute("name"));
        hBuilder.appendXp(Integer.parseInt(((Element) basis.getElementsByTagName("abenteuerpunkte").item(0)).getAttribute("value")));
        NodeList professions = basis.getElementsByTagName("ausbildungen").item(0).getChildNodes();
        for (int i = 0; i<professions.getLength(); i++) {
            Node n = professions.item(i);
            if (n instanceof Element) {
                hBuilder.appendProfession(((Element) n).getAttribute("string"));
            }
        }

        NodeList propNodes = property.getChildNodes();
        List<HeroProperty> properties = new ArrayList<>();
        for (int i = 0; i < propNodes.getLength(); i++)
        {
            Node n = propNodes.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE && n.getNodeName().equals("eigenschaft"))
            {
                Element prop = (Element) n;
                Optional<PropertyNames> propName = PropertyNames.getByName(prop.getAttribute("name"));
                if (propName.isPresent())
                {
                    properties.add(new HeroProperty(propName.get(),Integer.parseInt(prop.getAttribute("value")) + Integer.parseInt(prop.getAttribute("mod"))));
                }
            }
        }
        hBuilder.appendProperties(properties);

        NodeList talentList = talent.getChildNodes();
        List<Talent> talents = new ArrayList<>();
        for (int i = 0; i < talentList.getLength(); i++)
        {
            Node n = talentList.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE && n.getNodeName().equals("talent"))
            {
                Element prop = (Element) n;
                talents.add(new Talent(prop.getAttribute("name"),prop.getAttribute("probe"),Integer.parseInt(prop.getAttribute("value"))));
            }
        }
        hBuilder.appendTalents(talents);

        NodeList spellList = spell.getChildNodes();
        List<Spell> spells = new ArrayList<>();
        for (int i = 0; i < spellList.getLength(); i++)
        {
            Node n = spellList.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE && n.getNodeName().equals("zauber"))
            {
                Element prop = (Element) n;
                spells.add(new Spell(prop.getAttribute("name"),prop.getAttribute("probe"),Integer.parseInt(prop.getAttribute("value"))));
            }
        }
        hBuilder.appendSpells(spells);

        NodeList combatTalentList = combat.getChildNodes();
        List<CombatTalent> combats = new ArrayList<>();
        for (int i = 0; i < combatTalentList.getLength(); i++)
        {
            Node n = combatTalentList.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE && n.getNodeName().equals("kampfwerte"))
            {
                Element prop = (Element) n;

                combats.add(new CombatTalent(Integer.parseInt(((Element) prop.getChildNodes().item(0)).getAttribute("value")),
                        Integer.parseInt(((Element) prop.getChildNodes().item(1)).getAttribute("value")),
                        prop.getAttribute("name")));
            }
        }
        hBuilder.appendCombatTalents(combats);

        NodeList advantageList = vt.getChildNodes();
        List<Advantage> advantages = new ArrayList<>();
        for (int i = 0; i < advantageList.getLength(); i++)
        {
            Node n = advantageList.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE && n.getNodeName().equals("vorteil"))
            {
                Element prop = (Element) n;
                Advantage advantage = new Advantage(prop.getAttribute("name"), prop.getAttribute("value"));
                NodeList childNodes = prop.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++){
                    if (Element.class.isAssignableFrom(childNodes.item(j).getClass())) {
                        Element child = (Element) childNodes.item(j);
                        advantage.getAdditionalText().add(child.getAttribute("value"));
                    }
                }
                advantages.add(advantage);
            }
        }
        hBuilder.appendAdvantages(advantages);

        NodeList specialityList = sf.getChildNodes();
        List<Speciality> specialities = new ArrayList<>();
        for (int i = 0; i < specialityList.getLength(); i++)
        {
            Node n = specialityList.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE && n.getNodeName().equals("sonderfertigkeit"))
            {
                Element prop = (Element) n;
                String vtname = prop.getAttribute("name");
                specialities.add(new Speciality(vtname));
            }
        }
        hBuilder.appendSpecialities(specialities);

        return hBuilder.build();
    }
}
