package com.salimmahboubi.contactsio;

import android.content.ContentProviderOperation;
import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;
import java.util.ArrayList;

/**
 * Created by Salim Mahboubi on 27/02/2016.
 */
public class Contact {


    // TODO Test the pluck out of it
    Context context;
    private String displayName = null;
    private String familyName = null;
    private String firstName = null;
    private String mobileNumber = null;
    private String homeNumber = null;
    private String workNumber = null;
    private String mainNumber = null;
    private String faxNumber = null;
    private String email = null;
    private String companyName = null;
    private String title = null;

    /**Getters & Setters*/
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    public String getMainNumber() {
        return mainNumber;
    }

    public void setMainNumber(String mainNumber) {
        this.mainNumber = mainNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    /**ENDOF Getters and Setters*/


    public Contact(Context context) {
        this.context = context;
    }

    public void insertInAndroidContacts() {

        ArrayList<ContentProviderOperation> cpoList = new ArrayList<ContentProviderOperation>();

        cpoList.add(ContentProviderOperation.newInsert(
                ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build());
                /* TODO Check if there is a use to inserting a custom ID
                if (!id.equals(""))
                    contactAddItem(cpoList, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE,
                    ContactsContract.CommonDataKinds.StructuredName.CONTACT_ID, id);*/
        //------------------------------------------------------ Names
        if (displayName != null)
            contactAddItem(cpoList, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE, ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, displayName);


        if (getFamilyName() != null)

            contactAddItem(cpoList, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE, ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME, getFamilyName());


        if (firstName != null)
            contactAddItem(cpoList, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE, ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, firstName);


        //------------------------------------------------------ Mobile Number
        if (mobileNumber != null)



            contactAddItem(cpoList, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE, ContactsContract.CommonDataKinds.Phone.NUMBER, mobileNumber, ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);

        //------------------------------------------------------ Home Numbers
        if (homeNumber != null)
            contactAddItem(cpoList, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE, ContactsContract.CommonDataKinds.Phone.NUMBER, homeNumber, ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_HOME);


        //------------------------------------------------------ Work Numbers
        if (workNumber != null)
            contactAddItem(cpoList, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE, ContactsContract.CommonDataKinds.Phone.NUMBER, workNumber, ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_WORK);


        if (mainNumber != null)

            contactAddItem(cpoList, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE, ContactsContract.CommonDataKinds.Phone.NUMBER, mainNumber, ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MAIN);


        if (faxNumber != null)


            contactAddItem(cpoList, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE, ContactsContract.CommonDataKinds.Phone.NUMBER, faxNumber, ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_FAX_WORK);

        //------------------------------------------------------ Email
        if (email != null)

            contactAddItem(cpoList, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE, ContactsContract.CommonDataKinds.Email.DATA, email, ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK);

        //------------------------------------------------------ Organisation

        if (!companyName.equals("") && !title.equals(""))


            contactAddItem(cpoList, ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE,
                    ContactsContract.CommonDataKinds.Organization.COMPANY, companyName,
                    ContactsContract.CommonDataKinds.Organization.TYPE, ContactsContract.CommonDataKinds.Organization.TYPE_WORK,
                    ContactsContract.CommonDataKinds.Organization.TITLE, title,
                    ContactsContract.CommonDataKinds.Organization.TYPE, ContactsContract.CommonDataKinds.Organization.TYPE_WORK);


        // Asking the Contact provider to apply the insertion of the new contact
        try {
            context.getContentResolver().applyBatch(ContactsContract.AUTHORITY, cpoList);
            Log.i("DroidContacts.Contact", "***********Apply finished*************");
        } catch (Exception e) {
            e.printStackTrace();

            Log.e("DroidContacts.Contact", "ERRROR inserting: " + e.toString());

            Log.e("DroidContacts.Contact", "ERRROR inserting: " + e.getMessage() + "/" + e.getCause().toString());
        }
    }
    protected void contactAddItem(ArrayList< ContentProviderOperation > cpoList, String mimeType, String key, String value) {
        try {
            cpoList.add(ContentProviderOperation.newInsert(
                            ContactsContract.Data.CONTENT_URI)
                            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                            .withValue(ContactsContract.Data.MIMETYPE, mimeType)
                            .withValue(key, value).build()
            );
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e("DroidContacts.Contact", "ERRROR inserting at contactAddItem: " + e.toString());
            Log.e("DroidContacts.Contact", "FOLLOW " + e.getMessage() + "/" + e.getCause().toString());
            Log.e("DroidContacts.Contact", "FOLLOW :: Key :" + key + " Value : " + value +" Mimetype : " + mimeType  );
        }
    }

    protected void contactAddItem(ArrayList< ContentProviderOperation > cpoList, String mimeType, String key, String value, String key2, int value2 ) {
        try {
            cpoList.add(ContentProviderOperation.newInsert(
                            ContactsContract.Data.CONTENT_URI)
                            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                            .withValue(ContactsContract.Data.MIMETYPE, mimeType)
                            .withValue(key, value)
                            .withValue(key2, value2)
                            .build()

            );
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e("DroidContacts.Contact", "ERRROR inserting at contactAddItem: " + e.toString());
            Log.e("DroidContacts.Contact", "FOLLOW " + e.getMessage() + "/" + e.getCause().toString());
            Log.e("DroidContacts.Contact", "FOLLOW :: Key :" + key + " Value : " + value + " Mimetype : " + mimeType);
            Log.e("DroidContacts.Contact", "FOLLOW :: Key2 :" + key2 + " Value 2: " + value2 );

        }
    }

    protected void contactAddItem(ArrayList< ContentProviderOperation > cpoList, String mimeType, String key, String value, String key2, int value2, String key3, String value3, String key4, int value4 ) {
        try {
            cpoList.add(ContentProviderOperation.newInsert(
                            ContactsContract.Data.CONTENT_URI)
                            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                            .withValue(ContactsContract.Data.MIMETYPE, mimeType)
                            .withValue(key, value)
                            .withValue(key2, value2)
                            .withValue(key3, value3)
                            .withValue(key4, value4)
                            .build()

            );
        }
        catch (Exception e){
            Log.e("DroidContacts.Contact", "ERRROR inserting at contactAddItem: " + e.toString());
            Log.e("DroidContacts.Contact", "FOLLOW " + e.getMessage() + "/" + e.getCause().toString());
            Log.e("DroidContacts.Contact", "FOLLOW :: Key :" + key + " Value : " + value + " Mimetype : " + mimeType);
            Log.e("DroidContacts.Contact", "FOLLOW :: Key2 :" + key2 + " Value 2: " + value2 );
            Log.e("DroidContacts.Contact", "FOLLOW :: Key3 :" + key3 + " Value 3: " + value3 );
        }
    }

}