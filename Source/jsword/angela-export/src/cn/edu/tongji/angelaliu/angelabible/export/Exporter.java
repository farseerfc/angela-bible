package cn.edu.tongji.angelaliu.angelabible.export;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import org.crosswire.jsword.book.Book;
import org.crosswire.jsword.book.BookData;
import org.crosswire.jsword.book.BookException;
import org.crosswire.jsword.book.BookFilters;
import org.crosswire.jsword.book.BookMetaData;
import org.crosswire.jsword.book.Books;
import org.crosswire.jsword.book.OSISUtil;
import org.crosswire.jsword.passage.Key;
import org.crosswire.jsword.passage.NoSuchKeyException;
import org.jdom.Attribute;
import org.jdom.Element;

public class Exporter {

	protected static Key[] gen11 = null;
	protected static BookMetaData[] bmds = null;
	protected static Book[] bibles = null;
	
	private static HashSet<String> verseChild=new HashSet<String>(); 

	/**
	 * @param args
	 * @throws NoSuchKeyException
	 * @throws BookException
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws NoSuchKeyException,
			BookException, FileNotFoundException, UnsupportedEncodingException {
		List<Book> lbmds = Books.installed().getBooks(
				BookFilters.getOnlyBibles());

		int i = 0;
		for (Book book : lbmds) {
			exportBook(book);

			i++;
		}

	}

	private static void exportBook(Book book) throws BookException,
			FileNotFoundException, UnsupportedEncodingException {

		String title = book.getName();
		String initial = book.getInitials();
		String language = book.getLanguage().getName();
		String osisRef = book.getOsisID();
		
		Locale locale= Locale.ENGLISH ;
		if(language.equals("Hebrew"))
			locale= new Locale("he");
		else if (language.equals("Chinese"))
			locale= Locale.CHINESE;
			

		PrintStream out = new PrintStream(new FileOutputStream("" + initial
				+ ".sql"),true,"UNICODE");
		out.printf(locale,"use angelabible;\n");
		out.printf(locale,"/*insert book %s into Version table*/\n",initial);
		out.printf(locale,"update [dbo].[Version] set osisRef=N'%s', title=N'%s', language=N'%s',describe=N'' where initial=N'%s';\n",osisRef,title,language,initial);
		out.printf(locale,"if @@ROWCOUNT=0\n\t");
		out.printf(locale,"insert into [dbo].[Version]"
				+ "(osisRef,title,initial,language,describe) "
				+ "values(N'%s',N'%s',N'%s',N'%s',N'');\n\n", osisRef, title, initial,
				language);

		Key allKeys = book.getGlobalKeyList();

		int cardinality = allKeys.getCardinality();
		int percent=0,report=100,lastChar=0;
		for (int i = 0; i < cardinality; ++i) {
			Key current = allKeys.get(i);
			printKey(out,locale,book,current);
			
			if(i*report/cardinality > percent){
				percent =i*report/cardinality;
				String output=initial+":\t"+percent+"\r\n";
				StringBuilder sb = new StringBuilder();
				for(int j =0; j<lastChar;++j){
					sb.append('\b');
				}
				sb.append(output);
				lastChar = output.length();
				System.out.print(sb.toString());
			}
		}

		out.close();
		// printKey(out, book, allKeys.get(0));
		// printKey(out, book, allKeys.get(1));
	}

	private static void printKey(PrintStream out,Locale locale, Book book, Key key)
			throws BookException {
		String osisId = key.getOsisID();
		String[] lst = osisId.split("\\.");
		String book1 = lst[0];
		int chapter = Integer.parseInt(lst[1]);
		int verse = Integer.parseInt(lst[2]);
		String initial = book.getInitials();
		String text = getText(new BookData(book, key));
		
		out.printf(locale,"EXEC InsertText %d,%d,'%s','%s','%s','%s' ;\ngo\n",
				chapter,verse,book1,osisId,initial,text);
		
		
//		out.printf(locale,"/*insert %s into Key table*/\n",osisId);
//		out.printf(locale,"update [dbo].[key] "
//				+ "set [book]=N'%s',[chapter]=N'%s',[verse]=N'%s' "
//				+ "where [osisId]=N'%s';\n", book1, chapter, verse, osisId);
//		out.printf(locale,"if @@ROWCOUNT=0 \n\tinsert into"
//				+ " [dbo].[key](osisId,book,chapter,verse)"
//				+ " values (N'%s',N'%s',N'%s',N'%s');\ngo\n\n", osisId, book1, chapter,
//				verse);
//		out.printf(locale,"/*insert %s:%s:N'%s' into Text table*/\n",initial,osisId,text);
//		out.printf(locale,"update [dbo].[Text] set text=N'%s' where initial=N'%s' and osisId=N'%s';\n",text,initial,osisId);
//		out.printf(locale,"if @@ROWCOUNT=0\n\tinsert into [dbo].[Text]" + "(initial,osisId,text) "
//				+ "values(N'%s',N'%s',N'%s');\ngo\n\n", initial, osisId, text);
//		
	}

	private static String getText(BookData bookData) throws BookException {
		Element osis = bookData.getOsis();
		Element osisText = osis.getChild("osisText");
		Element div = osisText.getChild("div");
		Element verse = div.getChild("verse");
		/*
		StringBuilder sb = new StringBuilder();

		for (Object obj : verse.getChildren()) {
			if (!(obj instanceof Element))
				continue;
			Element child = (Element) obj;
			String childName=child.getName().toLowerCase().trim();
			if(! verseChild.contains(childName)){
				System.out.println(childName);
				verseChild.add(childName);
			}
			if ("w".equals(childName))
				sb.append(child.getText() + " ");
		}

		sb.append(verse.getText().trim());

		// printElement(System.out,verse);
		String result=sb.toString();
		
		 
		String result = verse.getValue();
		
		result = result.replaceAll("'", "''");
		
		*/
		//if (!result.equals(sb.toString())) System.out.println(result);
		
		return OSISUtil.getCanonicalText(osis).replaceAll("'", "''");

	}
	
	private static String getElement(Element element){
		StringBuilder sb = new StringBuilder();
		return element.getValue();
	}

	/*
	 * *****************************************************************
	 * following code are for test use. They are not called currently *
	 * *****************************************************************
	 */

	private static void printOsis(PrintStream out,Locale locale, Element osis) {
		Element osisText = osis.getChild("osisText");
		Element div = osisText.getChild("div");
		printDiv(out,locale, div);
	}

	private static void printDiv(PrintStream out,Locale locale, Element div) {
		printElement(out,locale, div);

		out.println();
	}

	private static void printElement(PrintStream out,Locale locale, Element element) {
		
		out.printf(locale,"%s","<" + element.getName() + " ");
		for (Object att : element.getAttributes()) {
			Attribute attri = (Attribute) att;
			out.print(attri.getName() + "=" + attri.getValue());
		}
		out.print(">" + element.getText());
		for (Object obj : element.getChildren()) {
			if (!(obj instanceof Element))
				continue;
			Element child = (Element) obj;
			if (child.getChildren().size() > 0) {
				printElement(out,locale, child);
			} else {
				out.print("<" + child.getName() + ">" + child.getText() + "<\\"
						+ child.getName() + ">");
			}
		}
		out.printf(locale,"%s","<\\" + element.getName() + ">");
	}

}
