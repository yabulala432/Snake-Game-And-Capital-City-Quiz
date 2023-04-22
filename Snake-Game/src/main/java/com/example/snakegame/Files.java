package com.example.snakegame;

import java.util.ArrayList;

public class Files {
    private String allCountry = "Afghanistan:Albania:Algeria:Angola:Argentina:Australia:Austria:Bahamas:Bahrain:Bangladesh:" +
            "Belgium:Bolivia:Botswana:Brazil:Bulgaria:Burkina Faso:Burundi:Cambodia:Cameroon:Canada:Central Africa:" +
            "Chad:Chile:China:Colombia:Comoros:Congo Republic:Costa Rica:Cote d'Ivorie:Croatia:Cuba:Cyprus:" +
            "Czech Republic:Denmark:Djibouti:Ecuador:Egypt:El Salvador:Eritrea:Estonia:Ethiopia:Finland:France:Gabon:" +
            "Gambia:Georgia:Germany:Ghana:Greece:Guatemala:Guinea:Haiti:Honduras:Hungary:India:Indonesia:Iran:Iraq:" +
            "Ireland:Israel:Italy:Jamaica:Japan:Jordan:Kenya:Korea(North):Korea(South):Kuwait:Lebanon:Liberia:Libya:" +
            "Madagascar:Malawi:Malaysia:Mali:Mauritania:Mauritius:Mexico:Mongolia:Morocco:Mozambique:Namibia:Nepal:" +
            "Netherlands:New Zealand:Nicaragua:Niger:Nigeria:Norway:Oman:Pakistan:Panama:Paraguay:Peru:Philippines:" +
            "Poland:Portugal:Qatar:Romania:Russia:Rwanda:Saudi Arabia:Senegal:Sierra Leone:Singapore:Somalia:" +
            "South Africa:Spain:Sri Lanka:Sudan:Swaziland:Sweden:Switzerland:Syria:Taiwan:Tanzania:Thailand:Togo:" +
            "Turkey:Uganda:Ukraine:U.A.E:United Kingdom:U.S.A:Uruguay:Venezuela:Vietnam:Yemen:Yugoslavia:Zambia:Zimbabwe:",
            allCapital = "Kabul:Tirane:Algiers:Luanda:Buenos Aires:Canberra:Vienna:Nassau:Bahrain:Dhaka:Brussels:" +
                    "Lapaz:Gaborone:Brasilia:Sofia:Ouagadougou:Bujumbura:Phenom Penh:Yaunde:Ottawa:Bangui:N'Djamena:" +
                    "Santiago:Beijing:Bogota:Moroni:Kinshasa:San Jose:Abidjan:Zagreb:Havana:Nicosia:Prague:Copenhagen:" +
                    "Djibouti:Quito:Cairo:San salvador:Asmara:Tallinn:Addis Ababa:Helsinki:Paris:Libreville:Banjul:" +
                    "Tibilisi:Berlin:Accra:Athens:Guatemala City:Conakry:Port au Prince:Tegucigalpa:Budapest:New Delhi:" +
                    "Jakarta:Tehran:Baghdad:Dublin:Jerusalem:Rome:Kingston:Tokyo:Amman:Nairobi:Pyongyang:Seoul:" +
                    "Kuwait City:Beirut:Monrovia:Tripoli:Antananarivo:Lilongwe:Kuala Lampur:Bamako:Nouakchott:" +
                    "Port Louis:Mexico City:Ulan Bator:Rabat:Maputo:Windhoek:Kathmandu:Amsterdam:Auckland:Managua:Niamey:" +
                    "Lagos:Oslo:Muscat:Islamabad:Panama City:Ascension:Lima:Manila:Warsaw:Lisbon:Doha:Bucharest:Moscow:" +
                    "Kigali:Riyadh:Dakar:Freetown:Singapore:Moqdisho:Pretoria:Madrid:Colombo:Khartoum:Mbabane:Stockholm:" +
                    "Bern:Damascus:Taipei:Dar Es Salam:Bangkok:Lome:Ankara:Kampala:Kiev:Abu Dhabi:London:Washington D.C:" +
                    "Montevideo:Caracas:Hanoi:Sana'a:Belgrade:Lusaka:Harare:";
    public ArrayList<String> allInfo = new ArrayList<>();


    public Files() {
        while (!allCountry.equals("") || !allCapital.equals("")) {
            String information = allCountry.substring(0, allCountry.indexOf(":")) + ":" + allCapital.substring(0, allCapital.indexOf(":"));
            allCapital = allCapital.substring(allCapital.indexOf(":") + 1);
            allCountry = allCountry.substring(allCountry.indexOf(":") + 1);
            allInfo.add(information);
        }
    }

    public int sizeOfAllInfo() {
        return allInfo.size();
    }
}
