package onl.andres;

import java.io.File;
import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SongInfo {

	private final String artist;
	private final String title;

	public SongInfo(File file) throws Exception {
		String fileName = file.getName();
		Matcher matcher = Pattern.compile("(.*)-(.*)\\..*").matcher(fileName);
		if (matcher.matches()) {
			this.artist = cleanUp(matcher.group(1));
			this.title = cleanUp(matcher.group(2));
		} else {
			throw new Exception("Invalid filename format");
		}
	}

	private String cleanUp(String str) {
		if(str == null || str.trim().isEmpty()) return str;
		return Normalizer.normalize(str, Normalizer.Form.NFKD)
			.replaceAll("[^0-9a-zA-Z _]", "")
			.replaceAll("[ _]+", " ")
			.trim();
	}

	public String getArtist() {
		return artist;
	}

	public String getTitle() {
		return title;
	}
}
