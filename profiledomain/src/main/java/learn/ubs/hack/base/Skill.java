package learn.ubs.hack.base;

public class Skill {
    private int profileId;
    private SkillClass skillClass;
    private String skillName;
    private SkillStrength skillStrength;

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public SkillClass getSkillClass() {
        return skillClass;
    }

    public void setSkillClass(SkillClass skillClass) {
        this.skillClass = skillClass;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public SkillStrength getSkillStrength() {
        return skillStrength;
    }

    public void setSkillStrength(SkillStrength skillStrength) {
        this.skillStrength = skillStrength;
    }
}
