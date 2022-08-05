import java.util.HashMap;
public class CreateHashMap {
    private HashMap<String, String> hashMap;

    public CreateHashMap() {
        this.hashMap = new HashMap<>();
        initHashMap();
    }

    public HashMap<String, String> getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap<String, String> hashMap) {
        this.hashMap = hashMap;
    }

    private void initHashMap() {
        hashMap = new HashMap<>();
        hashMap.put("digital", " electrical print");
        hashMap.put("digital books", "printed publications");
        hashMap.put("image", "audio video picture frame");
        hashMap.put("streaming", "audio video picture frame");
        hashMap.put("download", "audio video picture frame");
        hashMap.put("system software", "maintenance and support");
        hashMap.put("cloud", "infrastructure service");
        hashMap.put("digital", "electrical print");
        hashMap.put("game", "computer software");
        hashMap.put("digital audio works", "integrated circuits workstation");
        hashMap.put("gift", "novelty certificate paper");
        hashMap.put("digital image", "printers Audio visual equipment");
        hashMap.put("digital magazines", "electronic publications");
        hashMap.put("mailing lists", "services");
        hashMap.put("movies", "entertainment theatre film");
        hashMap.put("streaming", "equipment");
        hashMap.put("newspaper", "publication");
        hashMap.put("photograph", "art");
        hashMap.put("vehicle", "transport");
        hashMap.put("loading unloading", "material handling");
        hashMap.put("shipments", "cargo possessions");
        hashMap.put("course", "park amusement");
        hashMap.put("billiard", "table games");
        hashMap.put("international", "freight forwarders transport");
        hashMap.put("concert", "performance");
        hashMap.put("adult", "club night");
        hashMap.put("coupons", "paper");
        hashMap.put("sales", "tax");
        hashMap.put("fees", "tax");
        hashMap.put("plant", "tree shrub");
        hashMap.put("juice", "fresh");
        hashMap.put("containers", "storage");
        hashMap.put("drink", "alcohol");
        hashMap.put("tire", "tube");
        hashMap.put("bicycle", "pedal");
        hashMap.put("books", "printed");
        hashMap.put("battery", "cell");
        hashMap.put("carpet", "rugs mat");
        hashMap.put("child", "infant toddler baby");
        hashMap.put("ear", "hearing");
        hashMap.put("foot lets", "hoisery socks");
        hashMap.put("sheepskin", "leather");
        hashMap.put("fur", "leather sheep");
        hashMap.put("golf clothing", "gloves");
        hashMap.put("insole", "Ergonomic");
        hashMap.put("pantyhose", "hoisery");
        hashMap.put("socks and stocking", "hoisery");
        hashMap.put("poncho", "coat jacket");
        hashMap.put("rainwear", "safety");
        hashMap.put("protective", "safety");
        hashMap.put("scarves", "clothing");
        hashMap.put("suspender", "clothing");
        hashMap.put("handkerchiefs", "clothing");
        hashMap.put("umbrellas", "clothing");
        hashMap.put("sweatbands", "clothing");
        hashMap.put("underwear", "undergarment");
        hashMap.put("aqua", "water");
        hashMap.put("shoe", "footwear");
        hashMap.put("blanket", "night");
        hashMap.put("costume", "drama play");
        hashMap.put("briefcase", "business");
        hashMap.put("cosmetic", "bath body");
        hashMap.put("hair", "bath body");
        hashMap.put("shower", "bath body");
        hashMap.put("handbag", "purse bag");
        hashMap.put("sunglass", "vision eye");
        hashMap.put("wallet", "cash");
        hashMap.put("wet", "scuba");
        hashMap.put("belt", "sewing");
        hashMap.put("buckle", "sewing");
        hashMap.put("mask", "respiration protection");
        hashMap.put("helmet", "head protection");
        hashMap.put("safety", "protection");
        hashMap.put("gloves", "apparel");
        hashMap.put("aprons", "apparel");
        hashMap.put("ski", "skiing snow");
        hashMap.put("athletic", "sports");
        hashMap.put("swim", "surf");
        hashMap.put("footwear", "shoes");
        hashMap.put("residential", "domestic");
        hashMap.put("air conditioners", "cooling");
        hashMap.put("washers", "washing laundry");
        hashMap.put("fans", "air");
        hashMap.put("thermostat", "temperature");
        hashMap.put("refrigirator", "kitchen appliance");
        hashMap.put("compactor", "kitchen appliance");
        hashMap.put("oven", "kitchen appliance");
        hashMap.put("room", "fan");
        hashMap.put("dispose", "recycling");
        hashMap.put("dryer", "laundry");
        hashMap.put("beverage", "food");
        hashMap.put("fruit juice", "fresh tropical");
        hashMap.put("yogurt", "dessert ice");
        hashMap.put("water", "non alcoholic");
        hashMap.put("jam", "jelly sweet");
        hashMap.put("candy", "confectionary");
        hashMap.put("chewing gum", "confectionary");
        hashMap.put("sandwich","rolls");
        hashMap.put("pretzels","snack");
        hashMap.put("confection","snack");
        hashMap.put("dried fruit","snack");
        hashMap.put("dip","sauce");
        hashMap.put("flag","signage");
        hashMap.put("honey","jam jellies");
        hashMap.put("yogurt","dessert");
        hashMap.put("ice cream","dessert");
        hashMap.put("dinnerware","tabletop serving");
        hashMap.put("flatware","tabletop serving");
        hashMap.put("glassware","tabletop serving");
        hashMap.put("bathroom", "restroom");
        hashMap.put("tabletop items", "furniture");
        hashMap.put("wrapping supplies", "aluminium foil");
        hashMap.put("gun locks", "security hardware cable");
        hashMap.put("light bulbs", "lamps");
        hashMap.put("cable", "copper electrical");

        setHashMap(hashMap);

//         if(s.toLowerCase().contains(" food beverage carbonated beverages")){
//            s = s.replaceAll(" food beverage carbonated beverages", "");
//        }
// if (s.toLowerCase().trim().contains("food food ingredients")){
//            s = s.trim().replaceAll("food food ingredients", "");
//        }
//         if (s.toLowerCase().trim().contains("glassware")){
//            s = s.trim().replaceAll("glassware", "glasses");
//        }


//		if (s.toLowerCase().contains("game") && !s.toLowerCase().contains("bingo")) {
//            s = s + " computer software";
//        }

//        if (s.toLowerCase().contains("and related products")) {
//            s = s.replaceAll("and related products", "accessories");
//        }

//        s = s.replaceAll("commercial", "");
//        if (s.toLowerCase().contains("cleaner")) {
//            s = s.replaceAll("cleaner","cleaning");
//        }
//        if (s.toLowerCase().contains("dispenser")) {
//            s = s.replaceAll("dispenser","dispensing");
//        }
//        if (s.toLowerCase().contains("beverage") && !s.toLowerCase().contains("label")) {
//            s = " food " + s;
//        }

//        if(s.toLowerCase().contains("carbonated beverages")){
//            s = s.replaceAll("food", "");
//        }

//        if (s.toLowerCase().contains("label")) {
//            s = s.replaceAll("label", "");
//        }



    }
}
