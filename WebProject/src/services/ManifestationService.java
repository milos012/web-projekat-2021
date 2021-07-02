package services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.Manifestation;

public class ManifestationService {
    private ArrayList<Manifestation> manifestations;
    private String path;

    public ManifestationService(String path) {
        this.path = path;
    }

    public ArrayList<Manifestation> getManifestations() {
        return manifestations;
    }
    
    public boolean addManifestation(Manifestation manifestation) {
		if(!checkDateAndLocation(manifestation.getManifestationDateTime(), manifestation.getLocation().getAddress()))
			return false;
		
		manifestation.setPosterPath("../images/default.png");
		manifestations.add(manifestation);
		writeManifestations();
		
		return true;
	}
    
    public boolean checkDateAndLocation(LocalDateTime localDateTime, String adresa) {
    	for (Manifestation manifestation : manifestations) {
			if(manifestation.isActive()) {
				if(manifestation.getLocation().getAddress().equals(adresa) && localDateTime.toLocalDate().equals(manifestation.getManifestationDateTime())) {
					return false;
				}
			}
		}
    	return true;
    }
    
    private void writeManifestations() {
		String data = path + File.separator + "data" + File.separator;
		ObjectMapper maper = new ObjectMapper();
		try {
			maper.writeValue(Paths.get(data+ "manifestations.json").toFile(), manifestations);
		} catch (IOException e) {
			System.out.println("Error, writing manifestations failed!");
		}
		
	}
}
