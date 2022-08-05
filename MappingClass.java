import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MappingClass {
	private ArrayList<Result> resultList;

	public MappingClass() {
		resultList = new ArrayList<Result>();

	}

	public MappingClass(ArrayList<Result> resultList) {

		this.resultList = resultList;
	}

	public ArrayList<Result> getResultList() {
		return resultList;
	}

	public void setResultList(ArrayList<Result> resultList) {
		this.resultList = resultList;
	}

	public void mapping(ArrayList<Class> classList, ArrayList<Avalara> avalaraList, UNSPSC tree) {
		for (Avalara avalara : avalaraList) {

			int maxPercentage = 0;
			ArrayList<Class> sameClass = new ArrayList<>();

			String title = modifyString(avalara.getDescription()).toLowerCase();
			for (int i = 0; i < classList.size(); i++) {
				Class classNode = classList.get(i);
				int percentage = commonWordsCount(title, classNode.getClassTitle().toLowerCase());
				if (percentage > maxPercentage) {
					sameClass.clear();
					sameClass.add(classNode);
					maxPercentage = percentage;

				} else if (percentage == maxPercentage)
					sameClass.add(classNode);

			}
			int sameClassIndex = 0;
			int sameCommodityIndex = 0;
			maxPercentage = 0;
			for (int i = 0; i < sameClass.size(); i++) {
				Class sameClassNode = sameClass.get(i);
				ArrayList<Commodity> commodityList = sameClassNode.getCommodityNodes();

				for (int j = 0; j < commodityList.size(); j++) {
					Commodity commodityNode = commodityList.get(j);
					title = commodityModification(title);

					int percentage = 0;

					percentage = commonWordsCount(title, commodityNode.getCommodityTitle().toLowerCase()); // removeKnownPatterns(avalara.getDescription())

					if (percentage > maxPercentage) {
						maxPercentage = percentage;
						sameClassIndex = i;
						sameCommodityIndex = j;
					}
				}

			}

			if (maxPercentage == 0) {
				title = modifyString(avalara.getDescription()).toLowerCase();
				int segmentIndex = 0;
				int familyIndex = 0;
				int classIndex = 0;
				int commodityIndex = 0;

				for (int i = 0; i < tree.getSegmentNodes().size(); i++) {
					Segment treeSegment = tree.getSegmentNodes().get(i);
					for (int j = 0; j < treeSegment.getFamilyNodes().size(); j++) {
						Family treeFamily = treeSegment.getFamilyNodes().get(j);
						for (int k = 0; k < treeFamily.getClassNodes().size(); k++) {
							Class treeClass = treeFamily.getClassNodes().get(k);
							for (int l = 0; l < treeClass.getCommodityNodes().size(); l++) {

								Commodity treeCommodity = treeClass.getCommodityNodes().get(l);
								String fullTitle = treeSegment.getSegmentTitle() + " " + treeFamily.getFamilyTitle()
										+ " " + treeClass.getClassTitle() + " " + treeCommodity.getCommodityTitle();

								int percentage = commonWordsCount(title, fullTitle.toLowerCase());
								if (percentage > maxPercentage) {
									maxPercentage = percentage;
									segmentIndex = i;
									familyIndex = j;
									classIndex = k;
									commodityIndex = l;

								}
							}
						}
					}
				}
				if(maxPercentage==0) {
					Result result = new Result(avalara.getTaxCode(),avalara.getDescription(),"",0);
					resultList.add(result);
				}
				else {
					Commodity correctCommodity = tree.getSegmentNodes().get(segmentIndex).getFamilyNodes().get(familyIndex)
							.getClassNodes().get(classIndex).getCommodityNodes().get(commodityIndex);
					Result result = new Result(avalara.getTaxCode(), avalara.getDescription(), correctCommodity.getCommodityTitle(),
							correctCommodity.getCommodityID());
					resultList.add(result);

				}


			} else {


				Class correctClass = sameClass.get(sameClassIndex);
				Commodity treeCommodity = correctClass.getCommodityNodes().get(sameCommodityIndex);
				Result result = new Result(avalara.getTaxCode(), avalara.getDescription(), treeCommodity.getCommodityTitle(),
						treeCommodity.getCommodityID());
				resultList.add(result);




			}

		}

	}

	public String constructResults() {
		StringBuilder stringBuilder = new StringBuilder();
		String categorys = "Commodity Title,"+"Commodity ID,"+"Avalara Tax Code,"+"Avalara Description\n";
		stringBuilder.append(categorys);
		for (Result line : resultList) {
			stringBuilder.append(line.toString() + "\n");
		}
		return stringBuilder.toString();
	}

	public void print(String fileName) {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(fileName);
			Writer out = new OutputStreamWriter(fos, "UTF-8");
			out.write(constructResults());
			out.close();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String commodityModification(String avalara) {
		String modified = avalara;
		if (modified.indexOf("clothing accessories ") != -1)
			modified = modified.replaceAll("clothing accessories ", "").trim();

		if(modified.indexOf("Food and food ingredients - beverages") != -1)
			modified = modified.replaceAll("Food and food ingredients - beverages", "");
		if (modified == "" || modified.length() == 0)
			return "empty";

		return modified;
	}

	private String addSynonyms(String s) {

		CreateHashMap chm =  new CreateHashMap();
		HashMap<String, String> hashMap = chm.getHashMap();
		if (s.toLowerCase().contains("digital books")) {
			s = s + " " + hashMap.get("digital books");
		}
		if (s.toLowerCase().contains("image") || s.toLowerCase().contains("streaming") || s.toLowerCase().contains("download")) {
			s = s + " " + hashMap.get("image");
		}
		if (s.toLowerCase().contains("system software")) {
			s = s + " " + hashMap.get("system software");
		}
		if (s.toLowerCase().contains("cloud")) {
			s = s + " " + hashMap.get("cloud");
		}
		if (s.toLowerCase().contains("digital")) {
			s = s + " " + hashMap.get("digital");
		} 		if (s.toLowerCase().contains("game") && !s.toLowerCase().contains("bingo")) {
			s = s + " " + hashMap.get("game");
		}
		if (s.toLowerCase().contains("digital audio works")) {
			s = s + " " + hashMap.get("digital audio works");
		}
		if (s.toLowerCase().contains("gift")) {
			s = s + " " + hashMap.get("gift");
		}
		if (s.toLowerCase().contains("digital image")) {
			s = s + " " + hashMap.get("digital image");
		}
		if (s.toLowerCase().contains("digital magazines")) {
			s = s + " " + hashMap.get("digital magazines");
		}
		if (s.toLowerCase().contains("mailing lists")) {
			s = s + " " + hashMap.get("mailing lists");
		}
		if (s.toLowerCase().contains("movies")) {
			s = s + " " + hashMap.get("movies");
		}
		if (s.toLowerCase().contains("streaming")) {
			s = s + " " + hashMap.get("streaming");
		}
		if (s.toLowerCase().contains("newspaper")) {
			s = s + " " + hashMap.get("newspaper");
		}
		if (s.toLowerCase().contains("photograph")) {
			s = s + " " + hashMap.get("photograph");
		}
		if (s.toLowerCase().contains("vehicle")) {
			s = s + " " + hashMap.get("vehicle");
		}
		if (s.toLowerCase().contains("loading unloading")) {
			s = s + " " + hashMap.get("loading unloading");
		}
		if (s.toLowerCase().contains("shipments")) {
			s = s + " " + hashMap.get("shipments");
		}
		if (s.toLowerCase().contains("course")) {
			s = s + " " + hashMap.get("course");
		}
		if (s.toLowerCase().contains("billiard")) {
			s = s + " " + hashMap.get("billiard");
		}
		if (s.toLowerCase().contains("international")) {
			s = s + " " + hashMap.get("international");
		}
		if (s.toLowerCase().contains("concert")) {
			s = s + " " + hashMap.get("concert");
		}
		if (s.toLowerCase().contains("adult")) {
			s = s + " " + hashMap.get("adult");
		}
		if (s.toLowerCase().contains("coupons")) {
			s = s + " " + hashMap.get("coupons");
		}
		if (s.toLowerCase().contains("sales") || s.toLowerCase().contains("fees")) {
			s = s + " " + hashMap.get("sales");
		}
		if (s.toLowerCase().contains("plant")) {
			s = s + " " + hashMap.get("plant");
		}
		if (s.toLowerCase().contains("juice")) {
			s = s + " " + hashMap.get("juice");
		}
		if (s.toLowerCase().contains("containers")) {
			s = s + " " + hashMap.get("containers");
		}
		if (s.toLowerCase().contains("drink")) {
			s = s + " " + hashMap.get("drink");
		}
		if (s.toLowerCase().contains("tire")) {
			s = s + " " + hashMap.get("tire");
		}
		if (s.toLowerCase().contains("bicycle")) {
			s = s + " " + hashMap.get("bicycle");
		}
		if (s.toLowerCase().contains("books")) {
			s = s + " " + hashMap.get("books");
		}
		if (s.toLowerCase().contains("battery")) {
			s = s + " " + hashMap.get("battery");
		}

		if (s.toLowerCase().contains("carpet")) {
			s = hashMap.get("carpet")  + " " + s;
		}
		if (s.toLowerCase().contains("child")) {
			s = hashMap.get("child")  + " " + s;
		}
		if (s.toLowerCase().contains("and related products")) {
			s = s.replaceAll("and related products", "accessories");
		}
		if (s.toLowerCase().contains("ear")) {
			s = hashMap.get("ear")  + " " + s;
		}
		if (s.toLowerCase().contains("foot lets")) {
			s = hashMap.get("foot lets")  + " " + s;
		}
		if (s.toLowerCase().contains("sheepskin")) {
			s = hashMap.get("sheepskin")  + " " + s;
		}
		if (s.toLowerCase().contains("fur")) {
			s = hashMap.get("fur")  + " " + s;
		}
		if (s.toLowerCase().contains("golf clothing")) {
			s = hashMap.get("golf clothing")  + " " + s;
		}
		if (s.toLowerCase().contains("insole")) {
			s = hashMap.get("insole")  + " " + s;
		}
		if (s.toLowerCase().contains("pantyhose") || s.toLowerCase().contains("socks and stocking")) {
			s = hashMap.get("pantyhouse")  + " " + s;
		}
		if (s.toLowerCase().contains("poncho")) {
			s = hashMap.get("poncho")  + " " + s;
		}
		if (s.toLowerCase().contains("rainwear") || s.toLowerCase().contains("protective")) {
			s = hashMap.get("rainwear")  + " " + s;
		}
		if (s.toLowerCase().contains("scarves") || s.toLowerCase().contains("suspender")
				|| s.toLowerCase().contains("handkerchiefs") || s.toLowerCase().contains("umbrellas")
				|| s.toLowerCase().contains("sweatbands")) {
			s = hashMap.get("scarves")  + " " + s;
		}
		if (s.toLowerCase().contains("underwear")) {
			s = hashMap.get("underwear")  + " " + s;
		}
		if (s.toLowerCase().contains("aqua")) {
			s = hashMap.get("aqua")  + " " + s;
		}
		if (s.toLowerCase().contains("shoe")) {
			s = hashMap.get("shoe")  + " " + s;
		}
		if (s.toLowerCase().contains("blanket")) {
			s = hashMap.get("blanket")  + " " + s;
		}
		if (s.toLowerCase().contains("costume")) {
			s = hashMap.get("costume")  + " " + s;
		}
		if (s.toLowerCase().contains("briefcase")) {
			s = hashMap.get("briefcase")  + " " + s;
		}
		if (s.toLowerCase().contains("cosmetic") || s.toLowerCase().contains("hair")
				|| s.toLowerCase().contains("shower")) {
			s = hashMap.get("cosmetic")  + " " + s;
		}
		if (s.toLowerCase().contains("handbag")) {
			s = hashMap.get("handbag")  + " " + s;
		}
		if (s.toLowerCase().contains("sunglass")) {
			s = hashMap.get("sunglass")  + " " + s;
		}
		if (s.toLowerCase().contains("wallet")) {
			s = hashMap.get("wallet")  + " " + s;
		}
		if (s.toLowerCase().contains("wet")) {
			s = hashMap.get("wet")  + " " + s;
		}
		if (s.toLowerCase().contains("belt") || s.toLowerCase().contains("buckle")) {
			s = hashMap.get("belt")  + " " + s;
		}
		if (s.toLowerCase().contains("mask")) {
			s = hashMap.get("mask")  + " " + s;
		}
		if (s.toLowerCase().contains("helmet")) {
			s = hashMap.get("helmet")  + " " + s;
		}
		if (s.toLowerCase().contains("safety")) {
			s = hashMap.get("safety")  + " " + s;
		}
		if (s.toLowerCase().contains("gloves") || s.toLowerCase().contains("aprons")) {
			s = hashMap.get("gloves")  + " " + s;
		}
		if (s.toLowerCase().contains("ski")) {
			s = hashMap.get("ski")  + " " + s;
		}
		if (s.toLowerCase().contains("athletic")) {
			s = hashMap.get("athletic")  + " " + s;
		}
		if (s.toLowerCase().contains("swim")) {
			s = hashMap.get("swim")  + " " + s;
		}
		if (s.toLowerCase().contains("footwear")) {
			s = hashMap.get("footwear")  + " " + s;
		}
		s = s.replaceAll("commercial", "");
		if (s.toLowerCase().contains("residential")) {
			s = hashMap.get("residential")  + " " + s;
		}
		if (s.toLowerCase().contains("air conditioners")) {
			s = hashMap.get("air conditioners")  + " " + s;
		}
		if (s.toLowerCase().contains("washers")) {
			s = hashMap.get("washers")  + " " + s;
		}
		if (s.toLowerCase().contains("fans")) {
			s = hashMap.get("fans")  + " " + s;
		}
		if (s.toLowerCase().contains("thermostat")) {
			s = hashMap.get("thermostat")  + " " + s;
		}
		if (s.toLowerCase().contains("refrigirator") || s.toLowerCase().contains("compactor") || s.toLowerCase().contains("oven")) {
			s = hashMap.get("refrigirator")  + " " + s;
		}
		if (s.toLowerCase().contains("room")) {
			s = hashMap.get("room")  + " " + s;
		}
		if (s.toLowerCase().contains("dispose")) {
			s = hashMap.get("dispose")  + " " + s;
		}
		if (s.toLowerCase().contains("dryer")) {
			s = hashMap.get("dryer")  + " " + s;
		}
		if (s.toLowerCase().contains("cleaner")) {
			s = s.replaceAll("cleaner","cleaning");
		}
		if (s.toLowerCase().contains("dispenser")) {
			s = s.replaceAll("dispenser","dispensing");
		}
		if (s.toLowerCase().contains("beverage") && !s.toLowerCase().contains("label")) {
			s = hashMap.get("beverage")  + " " + s;
		}

		if(s.toLowerCase().contains("carbonated beverages")){
			s = s.replaceAll("food", "");
		}

		if (s.toLowerCase().contains("label")) {
			s = s.replaceAll("label", "");
		}
		if (s.toLowerCase().contains("fruit juice")) {
			s = hashMap.get("fruit juice")  + " " + s;
		}
		if (s.toLowerCase().contains("yogurt")) {
			s = hashMap.get("yogurt")  + " " + s;
		}
		if (s.toLowerCase().contains("water")) {
			s = hashMap.get("water")  + " " + s;
		}
		if (s.toLowerCase().contains("jam")) {
			s = hashMap.get("jam")  + " " + s;
		}
		if(s.toLowerCase().contains(" food beverage carbonated beverages")){
			s = s.replaceAll(" food beverage carbonated beverages", "");
		}
		if (s.toLowerCase().contains("candy") || s.toLowerCase().contains("chewing gum")) {
			s = hashMap.get("chewing gum")  + " " + s;
		}
		if (s.toLowerCase().contains("sandwich")) {
			s = hashMap.get("sandwich")  + " " + s;
		}
		if (s.toLowerCase().contains("pretzels") || s.toLowerCase().contains("confection") || s.toLowerCase().contains("dried fruit")){
			s = hashMap.get("pretzels")  + " " + s;
		}
		if (s.toLowerCase().contains("dip")){
			s = hashMap.get("dip")  + " " + s;
		}
		if (s.toLowerCase().trim().contains("food food ingredients")){
			s = s.trim().replaceAll("food food ingredients", "");
		}
		if (s.toLowerCase().contains("flag")){
			s = hashMap.get("flag")  + " " + s;
		}
		if (s.toLowerCase().contains("honey")){
			s = hashMap.get("honey")  + " " + s;
		}
		if (s.toLowerCase().contains("yogurt") || s.toLowerCase().contains("ice cream")){
			s = hashMap.get("yogurt")  + " " + s;
		}
		if (s.toLowerCase().contains("dinnerware") || s.toLowerCase().contains("flatware") || s.toLowerCase().contains("glassware")){
			s = hashMap.get("dinnerware")  + " " + s;
		}
		if (s.toLowerCase().trim().contains("glassware")){
			s = s.trim().replaceAll("glassware", "glasses");
		}
		if (s.toLowerCase().contains("bathroom")){
			s = hashMap.get("bathroom")  + " " + s;
		}
		if (s.toLowerCase().contains("tabletop items")){
			s = hashMap.get("tabletop items")  + " " + s;
		}
		if (s.toLowerCase().contains("wrapping supplies")){
			s = hashMap.get("wrapping supplies")  + " " + s;
		}
		if (s.toLowerCase().contains("gun locks")){
			s = hashMap.get("gun locks")  + " " + s;
		}
		if (s.toLowerCase().contains("light bulbs")){
			s = hashMap.get("light bulbs")  + " " + s;
		}
		if (s.toLowerCase().contains("cable")){
			s = hashMap.get("cable")  + " " + s;
		}
		return s;
	}


	private String removeStrings(String s) {

		s = s.replaceAll("\\((business-to-business)\\) ?", "");
		s = s.replaceAll("\\((business-to-customer)\\) ?", "");
		s = s.replaceAll("ready to drink", "");
		s = s.replaceAll("\\((rtd)\\) ?", "");
		s = s.replaceAll("rtd", "");
		s = s.replaceAll("nutrition", "");
		s = s.replaceAll("Food and food ingredients-beverages", "");
		s = s.replaceAll("repair of tpp", "");
		s = s.replaceAll("Food & food ingredients -", " ");
		s = s.replaceAll("industry", "");
		s = s.replaceAll("min. 4 mils thick; for long-term re-use", "");
		s = s.replaceAll(" use of this code will apply the chicago amusement tax to all transactions made to chicago addresses", "");
		s = s.replaceAll("containers less than 200 ml (approximately 0.42 pint)", "");
		s = s.replaceAll("containers 200 ml (approximately 0.42 pint) or more", "");
		s = s.replaceAll("for long-term re-use; min. capacity 15l; washable/disinfectable; label shows name and location of manufacturer, true statement that bag contains no heavy metals, percentage of postconsumer material, if any", "");
//		s = s.repaceAll("min. 2.25 mils thick; for long-term re-use; min. capacity 15l; washable/disinfectable; label shows name and location of manufacturer\\, true statement that bag contains no heavy metals, percentage of postconsumer material, if any", "");
		s = s.replaceAll(" min. capacity 1/8th barrel (approx. 14.5l); contains min. 40% postconsumer material and no old growth fiber; is compostable and recyclable; label shows recyclable, % of recycled content, name and location of manufacturer", "");
		s = s.replaceAll("max. capacity 8# (approx. 5.2l); contains min. 20% postconsumer material; is compostable and recyclable; label shows percentage of recycled content", "");
		s = s.replaceAll("for use by retailers not passing a bag tax on to customers when wholesaler already collected the bag tax; *see additional code description", "");
		return s;
	}

	private String modifyString(String s) {

		String cleanedString = removeStrings(s);
		String synonymString = addSynonyms(cleanedString);
//		String modified = synonymString.replaceAll("[()]", "").replaceAll("[-+.^:/*,]", " ");
		String finalString = removeKnownPatterns(synonymString);
		return finalString;

	}

	// removed the word equipment
	private String removeKnownPatterns(String s){
		String modified = s.replaceAll("[()]", "").replaceAll("[-+.^:/*,]", " ");
//		modified = modified.replaceAll("food & food ingredients", " ");
		Pattern p = Pattern.compile(
				"\\b(&|I|I've|&|:|%|=|;|unknown|includes|based|note|contain|efficient|equipment|excludes|core|contains|required|supplement|supporters|related|including|limited|mixed|tone|entirely|Reimbursed|installation|associated|etc|measurable|paid|tangible|personal|property|stated|charges|sale|municipally|wheel|privately|owned|separately|combined|contract|option|see additional avatax system tax code information|streamed|similar|access|see additional code description|partly|covered|seen|a|about|above|across|after|again|against|all|almost|alone|along|already|also|although|always|among|an|and|another|any|anybody|anyone|anything|anywhere|are|area|areas|around|as|ask|asked|asking|asks|at|away|b|back|backed|backing|backs|be|became|because|become|becomes|been|before|began|behind|being|beings|best|better|between|big|both|but|by|By|c|containing|came|can|cannot|case|cases|certain|certainly|clear|clearly|come|could|d|did|differ|different|differently|do|does|done|down|down|download|downloaded|downed|downing|downs|during|e|each|early|either|end|ended|ending|ends| enough|even|evenly|ever|every|everybody|everyone|everything|everywhere|f|face|faces|fact|facts|far|felt|few|find|finds|first|for|four|from|full|fully|further|furthered|furthering|furthers|g|gave|general|generally|get|gets|give|given|gives|go|going|good|got|great|greater|greatest|group|grouped|grouping|groups|h|had|has|have|having|he|her|here|herself|high|high|high|higher|highest|him|himself|his|how|however|i|if|important|in|interest|interested|intended|interesting|interests|into|is|it|its|itself|j|just|k|keep|keeps|kind|knew|know|known|knows|l| large|largely|last|later|latest|least|leave|less|let|lets|like|likely|load|long|longer|longest|m|meets|made|make|making|man|many|may|me|member|members|men|might|more|most|mostly|mr|mrs|much|must|my|myself|n|necessary|need|needed|needing|needs|never|new|new|newer|newest|next|no|nobody|non|noone|not|nothing|now| nowhere|number|numbers|o|of|off|often|old|older|oldest|on|once|one|only|open|opened|opening|opens|or|order|ordered|ordering|orders|other|others|our|out|over|p|part|parted|parting|parts|per|perhaps|place|places|point|pointed|pointing|points|possible|present|previously|purchased|presented|presenting|presents|problem|problems|put|puts|q|quite|r|required|rather|really|right|right|room|rooms|s|said|same|saw|say|says|second|seconds|see|seem|seemed|seeming|seems|sees|several|shall|she|should|show|showed|showing|shows|side|sides|since|sold|small|smaller|smallest|so|some|somebody|someone|something|somewhere|state|states|still|still|such|sure|t|to|take|taken|than|that|the|their|them|then|there|therefore|these|they|thing|things|think|thinks|this|those|though|thought|thoughts|three|through|thus|to|today|together|too|took|toward|turn|turned|turning|turns|two|u|users|under|until|up|upon|us|use|used|user|uses|v|via|very|w|want|wanted|wanting|wants|was|way|ways|we|well|wells|went|were|what|when|where|whether|which|while|who|whole|whose|why|will|with|within|without|work|worked|working|works|would|x|y|year|years|yet|you|young|younger|youngest|your|yours.....)\\b\\s?");
		Matcher m = p.matcher(modified);
		String modifiedString = m.replaceAll("");
		return modifiedString;
	}

	private int commonWordsCount(String unspsc, String avalara) {
		unspsc = unspsc.toLowerCase().trim();
		avalara = avalara.toLowerCase().trim();
		if (unspsc.length() == 0 || unspsc == "" || avalara.length() == 0 || avalara == "")
			return 0;
		int count = 0;
		Set<String> set = new HashSet<>();
		String[] strings = unspsc.split(" ");
		for (String string : strings) {
			if (!set.contains(string) && !set.contains(string + "s")) {
				set.add(string);
				set.add(string + "s");
			}
		}
		for (String string : set) {
			if (avalara.indexOf(string) != -1 || (avalara + "s").indexOf(string) != -1)
				count++;
		}
		return count;
	}
}