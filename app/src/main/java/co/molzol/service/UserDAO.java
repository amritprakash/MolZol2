/*
package co.molzol.service;

import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import co.molzol.entity.UserDetail;

*/
/**
 * Created by hp on 18-12-2017.
 *//*


public class UserDAO {

    private String uid;
    private UserDetail userDetail;
    private DatabaseReference userDatabaseRef;

    String TAG = "USERDAO";
    public UserDAO(String uid){
        this.uid = uid;
        userDatabaseRef = FirebaseDatabase.getInstance().getReference("user/"+uid);

        */
/*userDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                userDetail = dataSnapshot.getValue(UserDetail.class);
                Log.d(TAG, ""+userDetail.getUserAccount().getTotalBalance());


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }

        });*//*

    }

    public UserDetail getUser(final TextView mainViewBalanceTxt){

        userDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Log.d(TAG, ""+dataSnapshot.exists());

                //if(dataSnapshot.exists()){
                    userDetail = dataSnapshot.getValue(UserDetail.class);
                    Log.d(TAG, ""+userDetail.getUserAccount().getTotalBalance());
                if(userDetail!= null){
                    mainViewBalanceTxt.setText(userDetail.getUserAccount().getTotalBalance());
                } else{
                    mainViewBalanceTxt.setText(0);
                }
                /*/
/*//*
*/
/*}else{
                    //userDetail = null;
                //}*//*
*/
/*
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }

        });

        return userDetail;
    }

    public void addNewUser(UserDetail user){

        userDatabaseRef.setValue(user);

    }

}
*/
