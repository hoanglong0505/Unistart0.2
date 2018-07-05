/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Admin
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(service.AnswerFacadeREST.class);
        resources.add(service.ArticleFacadeREST.class);
        resources.add(service.BranchFacadeREST.class);
        resources.add(service.CharacterFacadeREST.class);
        resources.add(service.EntranceInfoFacadeREST.class);
        resources.add(service.FieldFacadeREST.class);
        resources.add(service.FieldTypeFacadeREST.class);
        resources.add(service.GenititeFacadeREST.class);
        resources.add(service.LocationFacadeREST.class);
        resources.add(service.QuestionFacadeREST.class);
        resources.add(service.RateCriteriaFacadeREST.class);
        resources.add(service.RateDetailFacadeREST.class);
        resources.add(service.RateFacadeREST.class);
        resources.add(service.RegionFacadeREST.class);
        resources.add(service.ReportFacadeREST.class);
        resources.add(service.SchoolFacadeREST.class);
        resources.add(service.SubjectCombinationFacadeREST.class);
        resources.add(service.TypeFacadeREST.class);
        resources.add(service.UserFacadeREST.class);
    }
    
}
