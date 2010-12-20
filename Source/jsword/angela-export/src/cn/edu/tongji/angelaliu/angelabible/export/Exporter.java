package cn.edu.tongji.angelaliu.angelabible.export;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.List;

import org.crosswire.jsword.book.Book;
import org.crosswire.jsword.book.BookData;
import org.crosswire.jsword.book.BookException;
import org.crosswire.jsword.book.BookFilters;
import org.crosswire.jsword.book.BookMetaData;
import org.crosswire.jsword.book.Books;
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
	 */
	public static void main(String[] args) throws NoSuchKeyException,
			BookException, FileNotFoundException {
		List<Book> lbmds = Books.installed().getBooks(
				BookFilters.getOnlyBibles());

		int i = 0;
		for (Book book : lbmds) {
			exportBook(book);

			i++;
		}

	}

	private static void exportBook(Book book) throws BookException,
			FileNotFoundException {

		String title = book.getName();
		String initial = book.getInitials();
		String language = book.getLanguage().getName();
		String osisRef = book.getOsisID();

		PrintStream out = new PrintStream(new FileOutputStream("" + initial
				+ ".sql"));
		out.printf("/*insert book %s into Version table*/\n",initial);
		out.printf("update [dbo].[Version] set osisRef='%s', title='%s', language='%s' where initial='%s';\n",osisRef,title,language,initial);
		out.printf("if @@ROWCOUNT=0\n\t");
		out.printf("insert into [dbo].[Version]"
				+ "(osisRef,title,initial,language) "
				+ "values('%s','%s','%s','%s');\n\n", osisRef, title, initial,
				language);

		Key allKeys = book.getGlobalKeyList();

		int cardinality = allKeys.getCardinality();
		int percent=0,report=100;
		for (int i = 0; i < cardinality; ++i) {
			Key current = allKeys.get(i);
			printKey(out,book,current);
			
			if(i*report/cardinality > percent){
				percent =i*report/cardinality;
				System.out.println(initial+":\t"+percent);
			}
		}

		// printKey(out, book, allKeys.get(0));
		// printKey(out, book, allKeys.get(1));
	}

	private static void printKey(PrintStream out, Book book, Key key)
			throws BookException {
		String osisId = key.getOsisID();
		printOsisId(out, osisId);
		String initial = book.getInitials();
		String text = getText(new BookData(book, key));
		out.printf("/*insert %s:%s:'%s' into Text table*/\n",initial,osisId,text);
		out.printf("update [dbo].[Text] set text='%s' where initial='%s' and osisId='%s';\n",text,initial,osisId);
		out.printf("if @@ROWCOUNT=0\n\tinsert into [dbo].[Text]" + "(initial,osisId,text) "
				+ "values('%s','%s','%s');\ngo\n\n", initial, osisId, text);
	}

	private static void printOsisId(PrintStream out, String osisId) {
		String[] lst = osisId.split("\\.");
		String book = lst[0];
		int chapter = Integer.parseInt(lst[1]);
		int verse = Integer.parseInt(lst[2]);
		out.printf("/*insert %s into Key table*/\n",osisId);
		out.printf("update [dbo].[key] "
				+ "set [book]='%s',[chapter]='%s',[verse]='%s' "
				+ "where [osisId]='%s';\n", book, chapter, verse, osisId);
		out.printf("if @@ROWCOUNT=0 \n\tinsert into"
				+ " [dbo].[key](osisId,book,chapter,verse)"
				+ " values ('%s','%s','%s','%s');\ngo\n\n", osisId, book, chapter,
				verse);
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
		*/
		String result = verse.getValue();
		
		result = result.replaceAll("'", "''");
		//if (!result.equals(sb.toString())) System.out.println(result);
		return result;

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

	private static void printOsis(PrintStream out, Element osis) {
		Element osisText = osis.getChild("osisText");
		Element div = osisText.getChild("div");
		printDiv(out, div);
	}

	private static void printDiv(PrintStream out, Element div) {
		printElement(out, div);

		out.println();
	}

	private static void printElement(PrintStream out, Element element) {
		out.print("<" + element.getName() + " ");
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
				printElement(out, child);
			} else {
				out.print("<" + child.getName() + ">" + child.getText() + "<\\"
						+ child.getName() + ">");
			}
		}
		out.print("<\\" + element.getName() + ">");
	}

}
