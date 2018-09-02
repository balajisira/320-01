package learn.ubs.hack.base;

import java.util.List;
import java.util.Set;

public class Profile {
    private int id;
    private Employee employee;
    private List <Projects> projets;
    private Set<Skill> skills;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Projects> getProjets() {
        return projets;
    }

    public void setProjets(List<Projects> projets) {
        this.projets = projets;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }
}
