package regexProject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections4.map.HashedMap;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.*;


public class regmatcherUI extends Application {

	Label l1,l2,l3,l4,l5;
	TextArea regex, srctext, resulttext, unmatchedtext;
	
	HBox hb1,hb2, hb3,hb4,hb5,hb6,hb7,hb8,hb9;
	VBox vb1, vb2,vb3,vb4;
	CheckBox c1,c2,c3,c4,c5,c6,c7,c8;
	
	Button b1,b2,b3,b4,b5;
	
	static int objectcounter=1;
	
	DecimalFormat df = new DecimalFormat("0.00");
	
	public void start(Stage stage) throws Exception
	{
		l1 = new Label("Regular Expression");
		regex = new TextArea();
		regex.setPrefColumnCount(100);
		regex.setPrefRowCount(3);
		regex.setWrapText(true);
		
		hb1 = new HBox();
		hb1.setSpacing(15);
		hb1.setPadding(new Insets(10,10,10,10));
		hb1.getChildren().addAll(l1);
		
		hb2 = new HBox();
		hb2.setSpacing(15);
		hb2.setPadding(new Insets(10,10,10,10));
		hb2.getChildren().addAll(regex);
		
		vb1 = new VBox();
		vb1.setAlignment(Pos.BASELINE_CENTER);
		vb1.getChildren().addAll(hb1,hb2);
		
		l2 = new Label("Source Text");
		srctext = new TextArea();
		srctext.setPrefColumnCount(100);
		srctext.setPrefRowCount(40);
		srctext.setWrapText(true);
		srctext.setText("This tool will do Regex Testing and BL Publisher and Title Year Range check\n\n" 
		+ "Input Pattern for BL Publisher and Title Year Range test\n" 
				+"1) Title|Date \n" 
		+"2) Title\n" + "3) ISSN_00000000");
		
		
		hb3 = new HBox();
		hb3.setSpacing(15);
		hb3.setPadding(new Insets(10,10,10,10));
		hb3.getChildren().addAll(l2);
		
		hb4 = new HBox();
		hb4.setSpacing(15);
		hb4.setPadding(new Insets(10,10,10,10));
		hb4.getChildren().addAll(srctext);
		
		vb2 = new VBox();
		vb2.setAlignment(Pos.BASELINE_CENTER);
		vb2.getChildren().addAll(hb3,hb4);
		
		c1=new CheckBox("Case Insensitive"); c1.setSelected(true);
		c2=new CheckBox("Unicode Case");
		c3=new CheckBox("Allow WhiteSpace and Comments");
		c4=new CheckBox("Dot Matches Line Seprator");
		c5=new CheckBox("Use Unix Line Seprator");
		c6=new CheckBox("Ignore Metacharacters");
		c7=new CheckBox("Anchor Match Lines");
		c8=new CheckBox("Literal");
		
		GridPane checkboxgrid= new GridPane();
		checkboxgrid.add(c1, 0, 1);
		checkboxgrid.add(c2, 1, 1);
		checkboxgrid.add(c3, 2, 1);
		checkboxgrid.add(c4, 3, 1);
		checkboxgrid.add(c5, 0, 2);
		checkboxgrid.add(c6, 1, 2);
		checkboxgrid.add(c7, 2, 2);
		checkboxgrid.add(c8, 3, 2);
		
		
		checkboxgrid.setPadding(new Insets(10,10,10,10));
		//checkboxgrid.setVgap(10);
		checkboxgrid.setHgap(10);
			
		b1 =  new Button("Search");
		b2 =  new Button("New Window");
		b3 =  new Button("Trim Space");
		b4 =  new Button("Reset");
		b5 =  new Button("Check BL-Dates-Range");
		l5=new Label();
		
		hb5= new HBox();
		hb5.setSpacing(15);
		hb5.setPadding(new Insets(10,10,10,10));
		hb5.getChildren().addAll(b1,b2,b3,b4,b5,l5);
		
		resulttext = new TextArea();
		resulttext.setPrefColumnCount(100);
		resulttext.setPrefRowCount(10);
		resulttext.setWrapText(true);
		
		unmatchedtext = new TextArea();
		unmatchedtext.setPrefColumnCount(100);
		unmatchedtext.setPrefRowCount(10);
		unmatchedtext.setWrapText(true);
		
		GridPane gp=new GridPane();
		gp.add(vb1, 0, 1); //RegularExpression
		
		gp.setColumnSpan(vb1, 4);
		
		
		gp.add(checkboxgrid, 0, 2); //Checkbox
		gp.setColumnSpan(checkboxgrid, 4);
		
		gp.add(hb5, 0, 3); //Search Button
		gp.setColumnSpan(hb5, 4);
		
		
		gp.add(vb2, 0, 4); //Source Text
		
		Tab matchTab = new Tab("Matched Result", resulttext); 
		Tab unmatchTab = new Tab("Un Matched Result", unmatchedtext);
		
		
		TabPane resultTab = new TabPane();
		resultTab.getTabs().addAll(matchTab, unmatchTab);
		gp.add(resultTab, 3, 4);
		
		gp.setPadding(new Insets(10,10,10,10));
		
		
		b1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String regexStr = regex.getText() ;
				ArrayList<String> src = new ArrayList<>();
				Pattern p;
	
				for(String s:srctext.getText().split("\n"))
				{
					src.add(s);
				}
				
				Iterator<String> itr =src.iterator();
				
				int one=0, two=0, three=0, four=0, five=0, six=0, seven=0,eight=0;
				if(c1.isSelected())
					one = Pattern.CASE_INSENSITIVE;
				if(c2.isSelected())
					two=Pattern.UNICODE_CASE;
				if(c3.isSelected())
					three=Pattern.COMMENTS;
				if(c4.isSelected())
					four=Pattern.DOTALL;
				if(c5.isSelected())
					five=Pattern.UNIX_LINES;
				if(c6.isSelected())
					six=Pattern.UNICODE_CHARACTER_CLASS;
				if(c7.isSelected())
					seven=Pattern.MULTILINE;
				if(c8.isSelected())
					eight=Pattern.LITERAL;
				
				p = Pattern.compile(regexStr,one|two|three|four|five|six|seven|eight);
					
				Matcher m;
				int grpcount;
				String srtext, label;
				int recordfound=0, recordnotfound=0, findgroup=0;
				
				resulttext.setText("");
				unmatchedtext.setText("");
				

				long starttime=System.nanoTime();
				while(itr.hasNext())
				{
					srtext = itr.next();
					m  = p.matcher(srtext);
					
					grpcount = m.groupCount();
					
					
					if(m.matches())
					{
						recordfound++;
						for(int i=0;i<=grpcount;i++)
						{
							if(i==0) {label="Text: ";}else {label="Group "+(i-1);}
							//resulttext.appendText("("+(i-1)+") "+ m.group(i) +"\n");
							resulttext.appendText("("+label+") "+ m.group(i) +"\n");
						}
						resulttext.appendText("\n");
					}
					else
					{
						if(m.find(0))
						{
							int i=0, index=0;
							String textmatch="";
							
							unmatchedtext.appendText("Text: "+srtext+"\n");
							while(m.find(i) && i<=m.groupCount())
							{
									unmatchedtext.appendText("Group: (" + i +") " + m.group(i) +"\n");
									//index = srctext.getText().indexOf(m.group(0));
									//textmatch = m.group(0);
									//srctext.selectRange(textmatch.charAt(0), index + textmatch.length());
									i++;
								
							}unmatchedtext.appendText("\n");
							//srctext.selectRange(0, textmatch.length());
						}else
						{
						recordnotfound++;
						unmatchedtext.appendText(srtext +"\n");
						}
					}
				}
				
				
				long endtime=System.nanoTime();
				
				long elapsedtime = endtime-starttime;
				
				Double elapsedtimeseconds =(double) elapsedtime/1000000000; 
				
				Double elapsedtimeminutes =(double) (elapsedtimeseconds/60);
				
				
				l5.setText("Processing Time: " + df.format(elapsedtimeseconds) + " seconds."+ " " + "Matched Text: " + recordfound + ". UnMatched Text: " + recordnotfound);
			}
		});
		
		b2.setOnAction(new EventHandler<ActionEvent>()
				{
					public void handle(ActionEvent ae)
					{
						++objectcounter;
						try
						{
						Stage stage = new Stage();
						new regmatcherUI().start(stage);;
						}catch(Exception e) {}
						
					}
				});
		
		b3.setOnAction(new EventHandler<ActionEvent>()
				{
					public void handle(ActionEvent ae)
					{
						ArrayList<String> src = new ArrayList<String>();
						for(String s:srctext.getText().split("\n"))
						{
							src.add(s);
							srctext.setText("");
						}
						
						Iterator<String> iterator=src.iterator();
						
						while(iterator.hasNext())
						{
							String temp=iterator.next();
							srctext.appendText(temp.trim()+"\n");
						}
						
					}
				});

		b4.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent ae)
			{
				regex.setText("");
				srctext.setText("");
				resulttext.setText("");
				unmatchedtext.setText("");
				l5.setText("");
			}
			
		});	
		
		b5.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent ae)
			{
				resulttext.setText("");
				unmatchedtext.setText("");
				l5.setText("");
				
				excelproject proj=new excelproject();
				ArrayList<String> src = new ArrayList<>();
				ArrayList<String> resultAL = new ArrayList<>();
				ArrayList<String> exceldata=null;
				
				long starttime=System.nanoTime();
				
				try
				{
				exceldata = proj.getExcelData();
				}catch(Exception e){
					resulttext.setText(e.getMessage());
				}
				//HashedMap<String, String> title = new HashedMap<String, String>();
				//HashedMap<String, String> cs = new HashedMap<String, String>();
				
				HashedMap<String, BlPublisher> title = new HashedMap<>();
				HashedMap<String, BlPublisher> cs = new HashedMap<>();
				
				
				int noOfRecords=0;
				int foundRecords=0;
				int notFoundRecords=0;
				
				
				
				
				for(String data:exceldata)
				{
					BlPublisher pub = new BlPublisher();
					
					String[] splitdata=data.split("\\|");
					pub.setCS(splitdata[0]);
					pub.setISSN(splitdata[1]);
					pub.setTitle(splitdata[2]);
					pub.setPublisher(splitdata[3]);
					pub.setStartYear(splitdata[4]);
					pub.setStartVolume(splitdata[5]);
					pub.setStartIssue(splitdata[6]);
					
					//title.put(splitdata[2], data);
					//cs.put(splitdata[0], data);
					
					title.put(pub.getTitle(), pub);
					cs.put(pub.getCS(), pub);
				}
				
				for(String s:srctext.getText().split("\n"))
				{
					++noOfRecords;
					src.add(s);
					
					if(s.matches(".*[\\|][0-9]{4}"))
					{
						String srctitle =s.split("\\|")[0]; 
						String srctitledate = s.split("\\|")[1];
						String date="";
						Double ddate=null, srcddate=null;
						
						
						if(title.containsKey(srctitle))
						{
							//date=title.get(srctitle).split("\\|")[4];
							
							date=title.get(srctitle).getStartYear();
							ddate.parseDouble(date);
							srcddate.parseDouble(srctitledate);
							
							if(srcddate.parseDouble(srctitledate)==ddate.parseDouble(date) || srcddate.parseDouble(srctitledate)>ddate.parseDouble(date))
							{
								++foundRecords;
								resulttext.appendText("Input Title: [" + srctitle + "]\n" + title.get(srctitle).toString()+"\n\n");
								
							}else {
									++notFoundRecords;
									unmatchedtext.appendText(srctitle + " " + srctitledate + ": Date is not in BL range \n" + title.get(srctitle).toString()+"\n\n");
							}
						}else
						{
							++notFoundRecords;
							unmatchedtext.appendText(srctitle + " " + srctitledate+ ": Title is not in BL range \n");
						}
					}
					if(!s.matches("ISSN_[0-9]{8}_?[0-9]?[0-9]?") && title.containsKey(s))
					{
						++foundRecords;
						resulttext.appendText("JT: [" + s + "]\n" + title.get(s).toString()+"\n\n");
					}
					if(s.matches("ISSN_[0-9]{8}_?[0-9]?[0-9]?") && cs.containsKey(s))
					{
						++foundRecords;
						resulttext.appendText("ISSN: [" + s + "]\n" + cs.get(s).toString()+"\n\n");
					}
					if(!title.containsKey(s) && !cs.containsKey(s) && !s.matches(".*[\\|][0-9]{4}"))
					{
						++notFoundRecords;
						unmatchedtext.appendText(s+"\n");
					}
				}
				
				/*
				try
				{
				resultAL = proj.search(src);
				}catch(Exception e)
				{
					resulttext.appendText("" +e.getMessage());
				}
				String[] splitText;
				ArrayList<String> jt= new ArrayList<String>();
				ArrayList<String> issn= new ArrayList<String>();
				
				for(String s:resultAL)
				{
					++foundRecords;
					splitText = s.split("\\|");
					jt.add(splitText[2]);
					issn.add(splitText[0]);
					resulttext.appendText("JT: " + splitText[2] + "\n" +s+"\n\n");
				}
				
				for(String s:src)
				{
					if(!jt.contains(s) && !issn.contains(s)) {
						++notFoundRecords;
						unmatchedtext.appendText(s+ "\n\n");
						}
				}*/
				
				long endtime=System.nanoTime();
				
				long elapsedtime = endtime-starttime;
								
				double elapsedtimeseconds =(double) elapsedtime/1000000000;
				
				double elapsedtimeminutes =(double) elapsedtimeseconds/60; 

				l5.setText("Processing Time " + df.format(elapsedtimeminutes) + " minutes. Total Records: " + noOfRecords + ", Records Found: " + foundRecords + ", Not Found: " + notFoundRecords);
				
			}
		});
		Scene root = new Scene(gp,1000,800);
		
		stage.setScene(root);
		stage.setTitle("Regex Matcher Tool-"+objectcounter);
		stage.show();
	}
	
	
	public static void main(String[] arg)
	{
		launch(arg);
	}
	
}
