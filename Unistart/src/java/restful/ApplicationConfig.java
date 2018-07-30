/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author TNT
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
        resources.add(Knowledge.service.ChapterFacadeREST.class);
        resources.add(Knowledge.service.GradeFacadeREST.class);
        resources.add(Knowledge.service.SubjectFacadeREST.class);
        resources.add(Knowledge.service.UnitFacadeREST.class);
        resources.add(customer.service.ClassFacadeREST.class);
        resources.add(customer.service.SessionFacadeREST.class);
        resources.add(restful.AnswerFacadeREST.class);
        resources.add(restful.ArticleFacadeREST.class);
        resources.add(restful.AverageRateFacadeREST.class);
        resources.add(restful.BranchFacadeREST.class);
        resources.add(restful.CharacterFacadeREST.class);
        resources.add(restful.EntranceInfoFacadeREST.class);
        resources.add(restful.FieldFacadeREST.class);
        resources.add(restful.FieldTypeFacadeREST.class);
        resources.add(restful.GenitiveFacadeREST.class);
        resources.add(restful.ImageSchoolFacadeREST.class);
        resources.add(restful.LocationFacadeREST.class);
        resources.add(restful.QuestionFacadeREST.class);
        resources.add(restful.RateCriteriaFacadeREST.class);
        resources.add(restful.RateDetailFacadeREST.class);
        resources.add(restful.RateFacadeREST.class);
        resources.add(restful.RegionFacadeREST.class);
        resources.add(restful.ReportFacadeREST.class);
        resources.add(restful.SchoolFacadeREST.class);
        resources.add(restful.SubjectCombinationFacadeREST.class);
        resources.add(restful.TypeFacadeREST.class);
        resources.add(restful.UsersFacadeREST.class);
    }
    
}
