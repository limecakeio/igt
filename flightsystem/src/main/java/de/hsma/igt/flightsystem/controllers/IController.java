package de.hsma.igt.flightsystem.controllers;

import java.util.List;

public interface IController<T> {
	
	public void createObjects(List<T> objects);
	public void updateObjects(List<T> objects);
	public void deleteObjects(List<T> objects);
	public List<T> readObjects();
}
