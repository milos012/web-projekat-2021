package controllers;

import models.Manifestation;
import services.ManifestationService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/manifestations")
public class ManifestationController {

    @Context
    ServletContext servletContext;

    @Context
    HttpServletRequest httpServletRequest;

    private ManifestationService getManifestationService() {
        ManifestationService manifestationService = (ManifestationService) httpServletRequest.getAttribute("ManifestationService");
        if (manifestationService == null) {
            manifestationService = new ManifestationService(httpServletRequest.getRealPath("."));
            httpServletRequest.setAttribute("ManifestationService", manifestationService);
        }
        return manifestationService;
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Manifestation> getAllManifestations() {
        ManifestationService manifestationService = getManifestationService();
        List<Manifestation> manifestations = manifestationService.getManifestations();
        httpServletRequest.getSession().setAttribute("manifestations", manifestations);
        return manifestations;
    }

}
