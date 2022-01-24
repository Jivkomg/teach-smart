package bg.sofia.uni.fmi.piss.project.tm.utils.dtos;

import java.util.ArrayList;
import java.util.List;


public class MicrosoftCoursesResponse {
    private List<Module> modules = new ArrayList<>();

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }
}
