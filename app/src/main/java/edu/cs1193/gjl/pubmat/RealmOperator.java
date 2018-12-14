package edu.cs1193.gjl.pubmat;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;

import edu.cs1193.gjl.pubmat.realmObjects.OrgPost;
import edu.cs1193.gjl.pubmat.realmObjects.Organization;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

@EBean
public class RealmOperator {

    /*
        If possible, please use this class to perform your Realm tasks
     */


    Realm realm = Realm.getDefaultInstance();

    public Organization getOrgByID(int orgID)
    {
        realm.beginTransaction();
        RealmQuery<Organization> query = realm.where(Organization.class);
        query.equalTo("orgID", orgID);

        Organization o = query.findFirst();
        realm.commitTransaction();

        return o;
    }

    public Organization getOrgByName(int orgName)
    {
        realm.beginTransaction();
        RealmQuery<Organization> query = realm.where(Organization.class);
        query.equalTo("orgName", orgName);

        Organization o = query.findFirst();
        realm.commitTransaction();

        return o;
    }

    public void saveOrg(Organization org)
    {
        realm.beginTransaction();
        Organization managed = realm.copyToRealmOrUpdate(org);
        realm.commitTransaction();

        //return managed;
    }

    public ArrayList<OrgPost> getPostsfromOrg(int orgID) {
        realm.beginTransaction();

        RealmQuery<OrgPost> query = realm.where(OrgPost.class);
        query.equalTo("originalPoster", orgID);


        RealmResults<OrgPost> o = query.findAll();
        ArrayList<OrgPost> orgPosts = new ArrayList<>();


        for(OrgPost oP : o)
        {
            orgPosts.add(oP);
        }
        realm.commitTransaction();

        return orgPosts;
    }

}
