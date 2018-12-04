package de.hsma.igt.flightsystem.controllers;

import java.util.List;

public interface IController {
	
	void createObjects(List<Object> objects);
	void updateObjects(List<Object> objects);
	void deleteObjects(List<Object> objects);
}
