package services;

import models.Manifestation;

import java.util.ArrayList;

public class ManifestationService {
    private ArrayList<Manifestation> manifestations;
    private String path;

    public ManifestationService(String path) {
        this.path = path;
    }

    public ArrayList<Manifestation> getManifestations() {
        return manifestations;
    }
}
