/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.view.jtable.model;

import java.text.SimpleDateFormat;

import javax.swing.table.AbstractTableModel;

import com.elhena.simplejog.model.Competition;

public class RaceTableModel extends AbstractTableModel {

	// Constants
	private static final long serialVersionUID = 1L;
	
	// Attributes
	private final String[] columns = new String[] {"Nom", "Départ", "Arrivée", "Durée"};
	private Competition competition;
	
	
	// Constructor
	public RaceTableModel(Competition competition) {
		this.competition = competition;
	}
	
	@Override
	public int getColumnCount() {
		return columns.length;
	}
	
	@Override
	public String getColumnName(int index) {
		return columns[index];
	}

	@Override
	public int getRowCount() {
		return competition.getRaces().size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
			case 0: return competition.getRaces().get(rowIndex).getJogger().getName();
			case 1: return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(competition.getRaces().get(rowIndex).getCompetition().getStartTime());
			case 2: return new SimpleDateFormat("HH:mm:ss").format(competition.getRaces().get(rowIndex).getEndTime());
			case 3: return competition.getRaces().get(rowIndex).getDuration();
			default: return "Inconnu";
		}
	}
}
