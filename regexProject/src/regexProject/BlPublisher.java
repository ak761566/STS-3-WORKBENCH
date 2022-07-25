package regexProject;

public class BlPublisher {
	
	private String CS, ISSN,Title, Publisher, StartYear, StartVolume, StartIssue;

	public String getCS() {
		return CS;
	}

	public void setCS(String cS) {
		CS = cS;
	}

	public String getISSN() {
		return ISSN;
	}

	public void setISSN(String iSSN) {
		ISSN = iSSN;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getPublisher() {
		return Publisher;
	}

	public void setPublisher(String publisher) {
		Publisher = publisher;
	}

	public String getStartYear() {
		return StartYear;
	}

	public void setStartYear(String startYear) {
		StartYear = startYear;
	}

	public String getStartVolume() {
		return StartVolume;
	}

	public void setStartVolume(String startVolume) {
		StartVolume = startVolume;
	}

	public String getStartIssue() {
		return StartIssue;
	}

	public void setStartIssue(String startIssue) {
		StartIssue = startIssue;
	}
	
	@Override
	public String toString()
	{
		//return CS + "|" + ISSN + "|" + Title + "|" + Publisher + "|" + StartYear + "|" + StartVolume + "|" + StartIssue;
		
		return "CS: " + CS +"\n" 
				+ "ISSN: " + ISSN +"\n"
				+ "Title: " + Title + "\n"
				+ "Publisher: " + Publisher + "\n"
				+ "Start Year: " + StartYear.split("\\.")[0] + "\n"
				+ "Start Volume: " + StartVolume.split("\\.")[0] + "\n"
				+ "Start Issue: " + StartIssue.split("\\.")[0];
	}
	
	public String searchTitle(String title)
	{
		if(Title.equalsIgnoreCase(title))
			return toString();
		else
			return "false";
	}
	
	public String searchTitleYear(String title, String year)
	{
		Double srcYear = Double.parseDouble(year);
		Double startYear = Double.parseDouble(StartYear);
		
		if(Title.equalsIgnoreCase(title) && (srcYear == startYear || srcYear > startYear)  )
			return toString();
		else
			return "false";
		
	}
	

}
