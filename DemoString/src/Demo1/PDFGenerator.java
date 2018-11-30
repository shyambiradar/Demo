package Demo1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImage;
import com.itextpdf.text.pdf.PdfIndirectObject;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

public class PDFGenerator {
	static int count = 0;

	public static void process(Document document, JSONObject json) throws JSONException, DocumentException {
		try {
			for (String k : json.keySet()) {
				Object object = json.get(k);
				/*
				 * String html = "<html>" + "<head>" + "<style>" + "u{color : #084975;} " +
				 * "div{color: #F99D25;}" + "</style>" + "</head>" + "<body>" +
				 * "<div> This process call " + "<u style='font-color:red;'>"+(++count)+"</u> "
				 * + "</div>" + "</body>" + "</html>";
				 * 
				 * HTMLWorker htmlWorker = new HTMLWorker(document);
				 */

				if (object instanceof JSONArray) {
					JSONArray list = json.getJSONArray(k);
					process(document, list);
				} else if (object instanceof JSONObject) {
					process(document, json.getJSONObject(k));
				} else {
					// htmlWorker.parse(new StringReader(html));
					document.add(new Paragraph(k + " =>  " + json.get(k)));
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void process(Document document, JSONArray json) throws JSONException, DocumentException {
		for (int x = 0; x < json.length(); x++) {
			Object object = json.get(x);
			if (object instanceof JSONArray) {
				JSONArray list = json.getJSONArray(x);
				process(document, list);
			} else if (object instanceof JSONObject) {
				process(document, json.getJSONObject(x));
			} else {
				document.add(new Paragraph(json.get(x).toString()));
			}

		}
	}
	public static final String IMG3 = "C:\\Users\\shyam\\Desktop\\pdf images\\HeaderICICI-02.png";
	public static final String IMG2 = "C:\\Users\\shyam\\Desktop\\pdf images\\ICICI-Logo-02.png";
	public static final String IMG1 = "C:\\Users\\shyam\\Desktop\\pdf images\\11002.png";
	public static final String IMG4 = "C:\\Users\\shyam\\Desktop\\pdf images\\CrApFo-03.png";
	
	
	public static File jsonTopdf(JSONObject json) throws IOException, DocumentException {

		Document document = new Document(PageSize.A4, 70, 55, 40, 55);
		File file = File.createTempFile("consulta", ".pdf");
		System.out.println("file  " + file);
		FileOutputStream output = new FileOutputStream(file);

		PdfWriter writer = PdfWriter.getInstance(document, output);
		// writer.setEncryption("a".getBytes(), "b".getBytes(),
		// PdfWriter.ALLOW_PRINTING, PdfWriter.STANDARD_ENCRYPTION_128);
		writer.createXmpMetadata();
		writer.setBoxSize("art", new Rectangle(36, 54, 559, 788));

		document.open();
		document.addCreationDate();
		document.addTitle("documento");
		document.newPage();
		Font font = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.MAGENTA);
		//document.add(new Paragraph("Test", font));

		Font font1 = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.CYAN);
		//Paragraph para = new Paragraph("Test", font1);
		//para.setLeading(0, 1);
		
//			Image IMG1 = Image.getInstance(IMG2);
//	        IMG1.setWidthPercentage(10);
//	        IMG1.scaleToFit(300, 500);
//		
//			PdfPTable table1 = new PdfPTable(1);
//			 PdfPCell cell11 = new PdfPCell();
//	        table1.addCell(createImageCell(IMG2));
//	        cell11.addElement(IMG1);
//	        cell11.setHorizontalAlignment(Element.ALIGN_RIGHT);
//	        cell11.setFixedHeight(80);
//	        cell11.setVerticalAlignment(Element.ALIGN_RIGHT);
//	        table1.setHorizontalAlignment(Element.ALIGN_RIGHT);
//	        table1.setWidthPercentage(15);
//	        table1.addCell(cell11);
//	        document.add(table1);
//	        
//		 	PdfPTable table = new PdfPTable(2);
//	        table.setWidthPercentage(100);
//	        table.setTotalWidth(488);
//	        table.setLockedWidth(true);
//	        table.addCell(createImageCell(IMG2));
//	        table.addCell(createImageCell(IMG3));
//	        document.add(table);
			PdfPTable table = new PdfPTable(1);
			//table.setWidthPercentage(50);
			Image img1 = Image.getInstance(IMG2);
			img1.scaleAbsolute(200f, 30f);
			Image img2 = Image.getInstance(IMG3);
			img2.scaleAbsolute(340f, 30f);
			table.setWidthPercentage(120);
			//PdfPCell cell12 = new PdfPCell();
			Paragraph p = new Paragraph();
			p.add(new Chunk(img1, 0, 0, true));
			p.add(new Chunk(img2, 0, 0, true));
			PdfPCell cell12 = new PdfPCell();
			cell12.setBorder(Rectangle.NO_BORDER);
			cell12.addElement(p);
			table.addCell(cell12);
	        document.add(table);
		
	        
	        PdfPTable table1 = new PdfPTable(1);
	        table1.setWidthPercentage(120);
	        Image IMG3 = Image.getInstance(IMG1);
	        IMG3.scaleAbsolute(80f, 100f);
	        //IMG.setAbsolutePosition(500, 500);
	        
	        Paragraph p1 = new Paragraph();
	        p1.setIndentationLeft(460);
			p1.add(new Chunk(IMG3, 0, 0, true));
			PdfPCell cell13 = new PdfPCell();
			cell13.setBorder(Rectangle.NO_BORDER);
			cell13.addElement(p1);
			table1.addCell(cell13);
	        document.add(table1);
	        
	        
	        PdfPTable table123 = new PdfPTable(1);
	        table123.setWidthPercentage(120);
	        Image IMG123 = Image.getInstance(IMG4);
	        IMG123.scaleAbsolute(600f, 20f);
	        //IMG123.setAbsolutePosition(00, 200);
	        
	        Paragraph p123 = new Paragraph();
	        p123.setIndentationLeft(-30);
			p123.add(new Chunk(IMG123, 0, 0, true));
			PdfPCell cell123 = new PdfPCell();
			cell123.setBorder(Rectangle.NO_BORDER);
			cell123.addElement(p123);
			table123.addCell(cell123);
	        document.add(table123);
	       
	        
	        
//	        IMG.setWidthPercentage(10);
//	        IMG.scaleToFit(300, 500);
	        
//	        PdfPTable table11 = new PdfPTable(1);
//	        PdfPCell cell1 = new PdfPCell();
//	        cell1.addElement(IMG);
//	        cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
//	        cell1.setFixedHeight(80);
//	        cell1.setVerticalAlignment(Element.ALIGN_RIGHT);
//	        table11.setHorizontalAlignment(Element.ALIGN_RIGHT);
//	        table11.setWidthPercentage(15);
//	        table11.addCell(cell1);
	        //document.add(table11);
	        
	        Image strIMG1 = Image.getInstance(IMG4);
	        strIMG1.setWidthPercentage(100);
	        strIMG1.scaleToFit(500, 500);
	        
	        PdfPTable strip1 = new PdfPTable(1);
	        PdfPCell strcell1 = new PdfPCell();
	        //strcell1.addElement(strIMG1);
	       // strcell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
//	        strcell1.setFixedHeight(80);
	        //strcell1.setVerticalAlignment(Element.ALIGN_RIGHT);
	       
	        strip1.setWidthPercentage(100);
	        strip1.addCell(strIMG1);
	        //document.add(strip1);
	        
			
			
	        
	        
		//PdfPTable table = new PdfPTable(1);
		//table.setWidthPercentage(100);
		PdfPCell cell = new PdfPCell();
		cell.setMinimumHeight(50);
		//cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		//cell.addElement(para);
		//table.addCell(cell);
		//document.add(table);
		//document.add(new Paragraph("\n"));
		PdfPTable table111 = new PdfPTable(1);
		//PdfPCell cell11;
		//cell1 = new PdfPCell(new Phrase("TO:\n\n   name"));
		//table1.addCell(cell1);
		//cell1 = new PdfPCell(new Phrase("TO:\n\n\u00a0\u00a0\u00a0name"));
		//table1.addCell(cell1);
		//cell1 = new PdfPCell();
		//cell1.addElement(new Paragraph("TO:"));
		//Paragraph p = new Paragraph("name");
		//p.setIndentationLeft(10);
		//cell1.addElement(p);
		//table1.addCell(cell1);
		//cell11 = new PdfPCell();
		//cell1.addElement(new Paragraph("TO:"));
		//p = new Paragraph("name");
		//p.setAlignment(Element.ALIGN_RIGHT);
		//cell1.addElement(p);
		//table111.addCell(cell11);
		//document.add(table111);

		// process(document, json);

		document.close();

		return file;
	}
	
	
	public static PdfPCell createImageCell(String path) throws DocumentException, IOException {
        Image img = Image.getInstance(path);
        img.setAbsolutePosition(100, 200);//setFixedPosition(100,250);
        PdfPCell cell = new PdfPCell(img, true);
        return cell;
    }
	public static void main(String args[]) {
		try {
			JSONObject json = new JSONObject(
					"{\"loanDetails\":{\"purposeOfLoan\":\"3443\",\"tenureRequired\":\"500\",\"loanamountRequired\":\"5000\",\"typeofInterest\":\"Variable\",\"promotion\":\"508086\"},\"aapList\":[{\"empId\":{\"TWEYears\":\"1\",\"officeCountry_desc\":\"INDIA\",\"DIBMonths\":\"10\",\"officeState\":\"MAHARASHTRA\",\"companyName_desc\":\"GOVERNMENT\",\"officePincode\":\"400060\",\"officeCity\":\"MUMBAI\",\"companyName\":\"1\",\"TWEMonths\":\"11\",\"officeCountry\":\"1\",\"occupationType\":\"1\",\"DIBYears\":\"11\",\"officeAddressLine2\":\"adasds\",\"officeAddressLine3\":\"-\",\"officeOfficialEmail\":\"s@g.com\",\"officeAddressLine1\":\"qwewe\",\"officeLandlineNo\":\"16555656\",\"officeStdcode\":\"22\"},\"personalId\":{},\"contactId\":{},\"documentsDetails\":[],\"applicantStatus\":\"null\",\"loanId\":\"165\"},{\"empId\":{\"TWEYears\":\"1\",\"officeCountry_desc\":\"INDIA\",\"DIBMonths\":\"1\",\"officeState\":\"MAHARASHTRA\",\"companyName_desc\":\"GOVERNMENT\",\"officePincode\":\"400060\",\"officeCity\":\"MUMBAI\",\"companyName\":\"1\",\"TWEMonths\":\"1\",\"officeCountry\":\"1\",\"occupationType\":\"1\",\"DIBYears\":\"1\",\"officeAddressLine2\":\"adasds\",\"officeAddressLine3\":\"-\",\"officeOfficialEmail\":\"hitesh@g.com\",\"officeAddressLine1\":\"qwewe\",\"officeLandlineNo\":\"23568923\",\"officeStdcode\":\"22\"},\"personalId\":{\"noOfDependants_other\":\"1\",\"firstName\":\"sdsds\",\"lastName\":\"PAWAR\",\"qualification\":\"1\",\"verifyPanno\":\"N\",\"middleName\":\"M\",\"verifyAadharNo\":\"N\",\"aadharNo\":\"123456789000\",\"category\":\"2\",\"noOfDependants_child\":\"1\",\"maritalStatus\":\"Unmarried\",\"religion\":\"1\"},\"contactId\":{\"PreferredMailingAddress\":\"Current Address\",\"mobileNumber\":\"9969715205\",\"emailid\":\"siddhesh@m.com\",\"residenceStatus\":\"1\",\"residenceSTD\":\"022\",\"residenceLandline\":\"02256566\"},\"documentsDetails\":[],\"applicantStatus\":\"primary\",\"loanId\":\"90\"},{\"empId\":{\"TWEYears\":\"1\",\"officeCountry_desc\":\"INDIA\",\"DIBMonths\":\"1\",\"officeState\":\"MAHARASHTRA\",\"companyName_desc\":\"GOVERNMENT\",\"officePincode\":\"400060\",\"officeCity\":\"MUMBAI\",\"companyName\":\"1\",\"TWEMonths\":\"1\",\"officeCountry\":\"1\",\"occupationType\":\"1\",\"DIBYears\":\"1\",\"officeAddressLine2\":\"adasds\",\"officeAddressLine3\":\"-\",\"officeOfficialEmail\":\"hitesh@g.com\",\"officeAddressLine1\":\"qwewe\",\"officeLandlineNo\":\"2589662\",\"officeStdcode\":\"22\"},\"personalId\":{\"lastName\":\"PAWAR\",\"panno\":\"qwert1234t\",\"verifyPanno\":\"Y\",\"verifyAadharNo\":\"Y\",\"aadharNo\":\"123456789000\",\"noOfDependants_child\":\"1\",\"religion\":\"1\",\"noOfDependants_other\":\"1\",\"firstName\":\"SHUBHAM\",\"qualification\":\"1\",\"middleName\":\"Manoj\",\"category\":\"1\",\"maritalStatus\":\"Married\"},\"contactId\":{\"PreferredMailingAddress\":\"Permenant Address\",\"mobileNumber\":\"9969715205\",\"emailid\":\"shubham@mobi.com\",\"residenceStatus\":\"1\",\"residenceSTD\":\"022\",\"residenceLandline\":\"02256566\"},\"documentsDetails\":[],\"applicantStatus\":\"primary\",\"loanId\":\"2\"},{\"empId\":{\"TWEYears\":\"1\",\"officeCountry_desc\":\"INDIA\",\"DIBMonths\":\"1\",\"officeState\":\"maharashtra\",\"companyName_desc\":\"GOVERNMENT\",\"officePincode\":\"400060\",\"officeCity\":\"mumbai\",\"companyName\":\"1\",\"TWEMonths\":\"1\",\"officeCountry\":\"1\",\"occupationType\":\"1\",\"DIBYears\":\"1\",\"officeAddressLine2\":\"Krishna Villa\",\"officeAddressLine3\":\"121\",\"officeOfficialEmail\":\"s@g.com\",\"officeAddressLine1\":\"Radha Niwas\",\"officeLandlineNo\":\"23568923\",\"officeStdcode\":\"22\"},\"personalId\":{\"lastName\":\"Girkar\",\"panno\":\"bxwpb6747d\",\"verifyPanno\":\"Y\",\"verifyAadharNo\":\"Y\",\"aadharNo\":\"123456789000\",\"noOfDependants_child\":\"1\",\"religion\":\"1\",\"noOfDependants_other\":\"1\",\"firstName\":\"Siddhesh\",\"qualification\":\"1\",\"middleName\":\"Mohan\",\"category\":\"1\",\"maritalStatus\":\"Married\"},\"contactId\":{\"PreferredMailingAddress\":\"Permenant Address\",\"mobileNumber\":\"9969715205\",\"emailid\":\"siddhesh@mobitrail.com\",\"residenceStatus\":\"1\",\"residenceSTD\":\"022\",\"residenceLandline\":\"02256566\"},\"documentsDetails\":[],\"applicantStatus\":\"primary\",\"loanId\":\"1\"}]}");
			File f = jsonTopdf(json);
			String SRC = f.getPath();// "resources/pdfs/hello.pdf";
			String DEST = File.createTempFile("consulta", ".pdf").getPath();// "results/stamper/hello_with_image_id.pdf";
			String IMG = "C:\\\\Users\\\\shyam\\\\Desktop\\\\images\\\\CrApFo-03.png";
			
			String IMG1 = "C:\\Users\\shyam\\Desktop\\images\\HeaderICICI-02.png";
			String IMG2 = "C:\\Users\\shyam\\Desktop\\images\\ICICI CF-01.png";
			String IMG3 = "C:\\Users\\shyam\\Desktop\\images\\11002.jpg";

			manipulatePdf(SRC, DEST, IMG,IMG1,IMG2,IMG3);
			

			if (new File(SRC).exists()) {
				System.out.println("File Delete " + new File(SRC).delete());
			}
			/*
			 * String html = "<html>" + "<head>" + "<style>" + "u{color : #084975;} " +
			 * "div{color: #F99D25;}" + "</style>" + "</head>" + "<body>" +
			 * "<div> This process call " + "<u style='font-color:red;'>"+(++count)+"</u> "
			 * + "</div>" + "</body>" + "</html>";
			 */
			// IO
			// File htmlSource = new File("C:\\Users\\Ankita\\Desktop\\input.html");
			// File pdfDest = File.createTempFile("consulta", ".pdf");
			// File pdfDest = new File("output.pdf");

			// pdfHTML specific code
			// ConverterProperties converterProperties = new ConverterProperties();
			// HtmlConverter.convertToPdf(html,new PdfWriter(new
			// FileOutputStream(pdfDest)));//convertToPdf(new FileInputStream(htmlSource),
			// new FileOutputStream(pdfDest),converterProperties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void addFooterImage(String fileInput, String fileOutput) throws Exception{

	      PdfReader pdfInputReader = new PdfReader(fileInput); 
	      PdfStamper pdfOutputStamper = new PdfStamper(pdfInputReader,new FileOutputStream(fileOutput)); 
	      Image image = Image.getInstance("C:\\\\Users\\\\shyam\\\\Desktop\\\\images\\\\11002.jpg"); 
	      for(int i=1; i<= pdfInputReader.getNumberOfPages(); i++){
	          PdfContentByte content = pdfOutputStamper.getUnderContent(i);
	          image.setAbsolutePosition(100f, 700f);
	          content.addImage(image);
	      }
	      pdfOutputStamper.close(); 
	}
	
	public static void manipulatePdf(String src, String dest, String IMG,String IMG1,String IMG2,String IMG3) throws IOException, DocumentException {
		PdfReader reader = new PdfReader(src);
		PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
		System.out.println("DESC " + dest);
		
		
		Image image = Image.getInstance(IMG);
		PdfImage stream = new PdfImage(image, "", null);
		stream.put(new PdfName("ITXT_SpecialId"), new PdfName(""));
		PdfIndirectObject ref = stamper.getWriter().addToBody(stream);
		image.setDirectReference(ref.getIndirectReference());
		image.setAbsolutePosition(50, 590);
		image.scaleAbsolute(500f, 15f);
		image.setBorderColor(BaseColor.BLUE);

		PdfContentByte over = stamper.getOverContent(1);
		//over.addImage(image);
		
		
		stamper.close();
		reader.close();
	}
}