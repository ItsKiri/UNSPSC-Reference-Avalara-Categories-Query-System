import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

public class ReadingAvalara {
	private ArrayList<Avalara> avalaraList = new ArrayList<>();

	public ReadingAvalara() {

	}

	public ReadingAvalara(ArrayList<Avalara> avalaraList) {

		this.avalaraList = avalaraList;
	}

	public ArrayList<Avalara> getAvalaraList() {
		return avalaraList;
	}

	public void setAvalaraList(ArrayList<Avalara> avalaraList) {
		this.avalaraList = avalaraList;
	}

	public ArrayList<Avalara> readCSV(String fileName) {
		ArrayList<String> list = new ArrayList<>();

		try {
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
			String line = null;
			reader.readLine();
			while ((line = reader.readLine()) != null) {
				list.add(line);
			}
			int count = 0;
			for (String row : list) {
				if(count>855)
					break;
				String[] strings = row.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

				if (strings.length >= 2 && strings[0] != "") {
					Avalara avalara = new Avalara(strings[0], strings[1]);
					avalaraList.add(avalara);
					count++;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return avalaraList;

	}

	public String constructResults() {
		StringBuilder stringBuilder = new StringBuilder();
		for (Avalara line : avalaraList) {
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

}
