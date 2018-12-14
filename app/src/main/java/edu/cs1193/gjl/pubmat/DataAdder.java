package edu.cs1193.gjl.pubmat;

import org.androidannotations.annotations.Bean;

import edu.cs1193.gjl.pubmat.realmObjects.OrgPost;
import edu.cs1193.gjl.pubmat.realmObjects.Organization;

public class DataAdder {

    @Bean
    RealmOperator realm;

    public void populateOrgs()
    {
        Organization o1 = new Organization();
        Organization o2 = new Organization();
        Organization o3 = new Organization();
        Organization o4 = new Organization();

        o1.setOrgName("CompSAt");
        o1.setOrgDesc("MIS and CS parent Organization");
        o1.setOrgPicPath("CompSAtimage.jpg");
        o1.setOrgShortDesc("MIS and CS parent Organization");
        o1.setPostCount(0);

        o2.setOrgName("THINC");
        o2.setOrgDesc("Unacredited d20s");
        o2.setOrgPicPath("THINCimage.jpg");
        o2.setOrgShortDesc("Unacredited d20s");
        o2.setPostCount(1);

        o3.setOrgName("WriterSkill");
        o3.setOrgDesc("Org for writers everywhere!");
        o3.setOrgPicPath("WriterSkillimage.jpg");
        o3.setOrgShortDesc("Org for writers everywhere");
        o3.setPostCount(0);

        o4.setOrgName("Toushin");
        o4.setOrgDesc("Org nobody has ever heard about");
        o4.setOrgPicPath("Toushinimage.jpg");
        o4.setOrgShortDesc("Org nobody has ever heard about");
        o4.setPostCount(0);

        realm.saveOrg(o1);
        realm.saveOrg(o2);
        realm.saveOrg(o3);
        realm.saveOrg(o4);
    }

    public void populatePosts()
    {
        OrgPost op = new OrgPost();

        op.setPostID("1CompSAt");
        op.setPostCaption("NothingToSeeHere");
        op.setPostPhoto("1CompSAtimage.jpg");
        op.setOriginalPoster("CompSAt");

        realm.saveOrgPost(op);
    }

}
