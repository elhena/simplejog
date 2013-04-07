/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.controller;

import java.util.Collections;
import java.util.Date;

import com.elhena.simplejog.controller.model.Controller;
import com.elhena.simplejog.io.CompetitionWriter;
import com.elhena.simplejog.model.Competition;
import com.elhena.simplejog.model.CompetitionStatus;
import com.elhena.simplejog.model.Race;
import com.elhena.simplejog.util.logger.Log;
import com.elhena.simplejog.view.CompetitionFrame;

public class CompetitionController extends Controller {
	
	// Attributes
	private CompetitionFrame frame;
	private Competition competition;
	private SetRaceController raceController;
	private ViewRaceController viewController;
	private ArrivalCaptureController captureController;
	private AboutController aboutController;
	
	// Constructor
	public CompetitionController(FrontController controller) {
		super(controller);
		
		raceController = new SetRaceController(this);
		viewController = new ViewRaceController(this);
		captureController = new ArrivalCaptureController(this);
		aboutController = new AboutController(this);
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
	
	// Method : Open arrival capture frame
	public void openArrivalCaptureFrame() {
		captureController.openFrame();
	}
	
	// Method : Close arrival capture frame
	public void closeArrivalCaptureFrame() {
		captureController.closeFrame();
	}
	
	// Method : Open about frame
	public void openAboutFrame() {
		aboutController.openFrame();
	}
	
	// Method : Close about frame
	public void closeAboutFrame() {
		aboutController.closeFrame();
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
	
	// Method : Start the competition
	public void startCompetition() {
		Date startTime = new Date();
		competition.setStatus(CompetitionStatus.RUNNING);
		competition.setStartTime(startTime);
		
		frame.startCompetition();
		openArrivalCaptureFrame();
		Log.i("Competition '" + competition.getName() + "' is running");
	}
	
	// Method : Stop the competition
	public void stopCompetition() {
		Date endTime = new Date();
		frame.stopCompetition();
		competition.setStatus(CompetitionStatus.FINISHED);
		for (Race r : competition.getRaces()) {
			if (r.getEndTime() == null)
				r.setEndTime(endTime);
		}
		
		closeArrivalCaptureFrame();
		Log.i("Competition '" + competition.getName() + "' is finished");
	}
	
	// Method : Add new arrival
	public Race addArrival(int number) {
		Date arrival = new Date();
		
		for (Race r : competition.getRaces()) {
			if (r.getNumber() == number && r.getEndTime() == null) {
				r.setEndTime(arrival);
				refreshRaces();
				Log.i("Jogger '" + r.getJogger().getName() + "' has finished his race"); return r;
			}
		}
		
		return null;
	}
	
	// Method : Check if everyone is arrived
	public void checkJoggerCurrentlyRunning() {
		int error = 0;
		
		for (Race r : competition.getRaces()) {
			if (r.getEndTime() == null)
				error++;
		}
		
		if (error == 0)
			stopCompetition();
	}
	
	// Method : Save competition
	public void saveCompetition(String path) {
		CompetitionWriter writer = new CompetitionWriter(path);
		writer.write(getCompetition());
		writer.close();
		Log.i("Competiton '" + getCompetition().getName() + "' has been saved in '" + path + "' file");
	}
}
