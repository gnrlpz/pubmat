package edu.cs1193.gjl.pubmat;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;

import edu.cs1193.gjl.pubmat.realmObjects.OrgPost;
import edu.cs1193.gjl.pubmat.realmObjects.Organization;
import edu.cs1193.gjl.pubmat.realmObjects.User;
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

    public Organization getOrgByName(String orgName)
    {
        realm.beginTransaction();
        RealmQuery<Organization> query = realm.where(Organization.class);
        query.equalTo("orgName", orgName);

        Organization o = query.findFirst();
        realm.commitTransaction();

        return o;
    }

    public User getUserByName(String userName)
    {
        realm.beginTransaction();
        RealmQuery<User> query = realm.where(User.class);
        query.equalTo("username", userName);

        User o = query.findFirst();
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

    public void saveOrgPost(OrgPost oP)
    {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(oP);
        realm.commitTransaction();
    }

    public ArrayList<OrgPost> getPostsfromOrg(String orgID) {
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

    public User saveUser(User u)
    {
        realm.beginTransaction();
        User managed = realm.copyToRealmOrUpdate(u);
        realm.commitTransaction();

        return managed;
    }

    public ArrayList<OrgPost> getPosts()
    {
        realm.beginTransaction();
        RealmResults<OrgPost> results = realm.where(OrgPost.class).findAll();
        realm.commitTransaction();
        ArrayList<OrgPost> ap = new ArrayList<>();
        for(OrgPost o : results)
        {
            ap.add(o);
        }

        return ap;
    }

    public void populatePosts()
    {
        OrgPost op = new OrgPost();

        op.setPostID("1CompSAt");
        op.setPostCaption("NothingToSeeHere");
        op.setPostPhoto("1CompSAtimage.jpg");
        op.setOriginalPoster("CompSAt");

        OrgPost op2 = new OrgPost();

        op2.setPostID("2CompSAt");
        op2.setPostCaption("fjkalfjalkfjalksafsafs");
        op2.setPostPhoto("1CompSAtimage.jpg");
        op2.setOriginalPoster("CompSAt");

        realm.beginTransaction();
        realm.copyToRealm(op);
        realm.copyToRealm(op2);
        realm.commitTransaction();
    }

    public void close() { realm.close(); }

}
