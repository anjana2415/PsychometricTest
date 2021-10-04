package com.example.psychometrictest.TEST;

import android.util.ArrayMap;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.psychometrictest.MycompleteListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FirebaseQuery {
    public static FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    public static List<skilltest> f_skilllist = new ArrayList<>();
    public  static int f_selected_cat_index = 0;
   public static List<TestModel> f_testList = new ArrayList<>();
   public static int f_selected_test_index = 0;

    private static final String TAG = "FirebaseQuery";


    public static void createUserData(String email, String name, MycompleteListener mycompleteListener){
        Map<String, Object>  userData = new ArrayMap<>();
        userData.put("EMAILID",email);
        userData.put("FIRST_NAME",name);
        userData.put(" SCORE_AGRE",0);
        userData.put(" SCORE_CON",0);
        userData.put(" SCORE_EXT",0);
        userData.put(" SCORE_NEU",0);
        userData.put(" SCORE_OPEN",0);
        DocumentReference userDoc = firestore.collection("USERS").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        WriteBatch batch = firestore.batch();
        batch.set(userDoc,userData);
        DocumentReference countDoc = firestore.collection("USERS").document("TOTAL_USERS");
        batch.update(countDoc,"COUNT", FieldValue.increment(1));
        batch.commit()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                       mycompleteListener.onSuccess();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                          mycompleteListener.OnFailure();
                    }
                });

    }
    public static void loadCategories(final MycompleteListener completeListener)
    {
        // take data from database
        f_testList.clear();
        firestore.collection("SKILLTEST").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Map<String, QueryDocumentSnapshot> documentList = new ArrayMap<>();
                        for(QueryDocumentSnapshot doc: queryDocumentSnapshots)
                        {
                            documentList.put(doc.getId(), doc);
                        }
                        QueryDocumentSnapshot catListdoc = documentList.get("Categories");
                        //count variable
                        long catCount = catListdoc.getLong("COUNT");
                        for (int i=1; i<= catCount; i++)
                        {
                            String catId = catListdoc.getString("CAT"+ String.valueOf(i) +"_ID");
                            QueryDocumentSnapshot catDoc = documentList.get(catId);
                            int nooftest = catDoc.getLong("NO_OF_TEST").intValue();

                            String catName = catDoc.getString("CAT_NAME");
//                            f_skilllist.add(new skilltest(catId,catName,nooftest));


                        }
                        completeListener.onSuccess();


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        completeListener.OnFailure();

                    }
                });



    }

 
      //  public static void loadQuestions(MycompleteListener completeListener){
       // f_question.clear();
        //firestore.collection("QUESTIONS_COLL")
          //      .whereEqualTo("SKILL_CATEGORY",f_skilllist.get(f_selected_cat_index).getDocId())
            //    .get()
              //  .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                //    @Override
                  //  public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    //   for (DocumentSnapshot doc : queryDocumentSnapshots)
                      // {
                        // f_question.add(new QuestionModel(
                          //       doc.getString("QUESTION"),
                            //     doc.getString("A"),
                              //   doc.getString("B"),
                                // doc.getString("C"),
                                 //oc.getString("D"),
                                // doc.getLong("ANSWER").intValue()

                         //));
                       //}
                       //completeListener.onSuccess();
                    //}
                //}).addOnFailureListener(new OnFailureListener() {
            //@Override
            //public void onFailure(@NonNull Exception e) {
              // completeListener.OnFailure();
            //}
        //});
    //}


}
