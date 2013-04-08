/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;

import com.elhena.simplejog.app.model.Application;
import com.elhena.simplejog.model.Competition;
import com.elhena.simplejog.model.Race;
import com.elhena.simplejog.util.logger.Log;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

public class CompetitionPDFExporter {

	// Attributes
	private FileOutputStream output;
	private Document document;
	
	// Constructor
	public CompetitionPDFExporter(String path) {
		try {
			output = new FileOutputStream(new File(path));
			document = new Document(PageSize.A4);
			PdfWriter.getInstance(document, output);
		} 
		
		catch (FileNotFoundException e) {
			Log.x(e.getMessage());
			
			if (Log.debug)
				e.printStackTrace();
		} 
		
		catch (DocumentException e) {
			Log.x(e.getMessage());
			
			if (Log.debug)
				e.printStackTrace();
		} 
	}
	
	public void generate(Competition competition) {
		try {
			document.addTitle(competition.getName() + " : r�sultats");
			
			document.open();
			document.add(new Phrase(competition.getName() + " @ " + competition.getLocation() + " - D�part: " + new SimpleDateFormat("HH:mm:ss").format(competition.getStartTime())));
			document.add(Chunk.NEWLINE);
			document.add(new Chunk(new LineSeparator()));
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(new Phrase("Classement g�n�ral (" + competition.getRaces().size() + " participants)"));
			document.add(Chunk.NEWLINE);
			
			PdfPTable table = new PdfPTable(4);
			table.setWidthPercentage(100);
			int rank = 1;
			
			table.addCell("Place");
			table.addCell("Nom");
			table.addCell("Heure de fin");
			table.addCell("Dur�e");
			
			// Sort of races
			Collections.sort(competition.getRaces(), new Comparator<Race>() {
				@Override
				public int compare(Race r1, Race r2) {
					return (int) (r1.getDuration().getTime() - r2.getDuration().getTime());
				}
			});
			
			for (Race r : competition.getRaces()) {
				table.addCell(new Integer(rank).toString());
				table.addCell(r.getJogger().getName() + " (" + r.getNumber() + ")");
				table.addCell(new SimpleDateFormat("HH:mm:ss").format(r.getEndTime()));
				table.addCell(new SimpleDateFormat("HH:mm:ss").format(r.getDuration()));
				rank++;
			}
			
			document.add(table);
			document.add(Chunk.NEWLINE);
			document.add(new Phrase("G�r� � l'aide de " + Application.NAME + " " + Application.VERSION));
			document.add(Chunk.NEWLINE);
			document.add(new Phrase(Application.COPYRIGHT));
			document.close();
		} 
		
		catch (DocumentException e) {
			Log.x(e.getMessage());
			
			if (Log.debug)
				e.printStackTrace();
		}
		
	}
}
