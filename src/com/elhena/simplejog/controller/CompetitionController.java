/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.controller;

import java.util.Collections;

import com.elhena.simplejog.controller.model.Controller;
import com.elhena.simplejog.model.Competition;
import com.elhena.simplejog.model.Race;
import com.elhena.simplejog.util.logger.Log;
import com.elhena.simplejog.view.CompetitionFrame;

public class CompetitionController extends Controller {
	
	// Attributes
	private CompetitionFrame frame;
	private Competition competition;
	private SetRaceController raceController;
	private ViewRaceController viewController;
	
	// Constructor
	public CompetitionController(FrontController controller) {
		super(controller);
		
		raceController = new SetRaceController(this);
		viewController = new ViewRaceController(this);
	}
	
	// Method : Set competition
	public void setCompetition(Competition competition) {
		this.competition = competition;
	}
	
	// Method : Get Frame
	public CompetitionFrame getFrame() {
		return frame;
	}
	
	// Method : Open competition frame
	public void openFrame() {
		if (frame == null)
			frame = new CompetitionFrame(this);
		
		frame.setVisible(true);
	}
	
	// Method : Close competition frame
	public void closeFrame() {
		if (frame != null)
			frame.dispose();
	}
	
	// Method : Open set race frame
	public void openSetRaceFrame(Race race) {
		raceController.openFrame(race);
	}
	
	// Method : Close set race frame
	public void closeSetRaceFrame() {
		raceController.closeFrame();
	}
	
	// Method : Open view race frame
	public void openViewRaceFrame(Race race) {
		viewController.openFrame(race);
	}
	
	// Method : Close view race frame
	public void closeViewRaceFrame() {
		viewController.closeFrame();
	}
	
	// Method : Get competition
	public Competition getCompetition() {
		return competition;
	}
	
	// Method : Get next number available
	public int getNextNumberAvailable() {
		int counter = 1;
		
		if (competition.getRaces().size() == 0)
			return 1;
		
		else {
			for (Race r : competition.getRaces()) {
				if (r.getNumber() != counter)
					return counter;
				
				counter++;
			}
		}
		
		return counter;
	}
	
	// Method : Get if a number exists already
	public boolean numberIsAvailable(int number) {
		for (Race r : competition.getRaces()) {
			if (r.getNumber() == number)
				return false;
		}
		
		return true;
	}
	
	// Method : Add race
	public void addRace(Race race) {
		competition.addRace(race);
		Collections.sort(competition.getRaces());
		frame.updateTable();
		Log.i("Jogger '" + race.getJogger().getName() + "' has been added to competition");
	}
	
	// Method : Remove race
	public void removeRace(Race race) {
		String name = race.getJogger().getName();
		
		competition.removeRace(race);
		frame.updateTable();
		Log.i("Jogger '" + name + "' has been deleted");
	}
	
	// Method : Refresh races
	public void refreshRaces() {
		frame.updateTable();
	}
}
