package co.molzol.molzol;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.ResultCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.molzol.util.Constants;
import co.molzol.entity.UserAccount;
import co.molzol.entity.UserDetail;
import co.molzol.entity.UserTransaction;
import co.molzol.util.NetworkUtil;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int RC_SIGN_IN = 555;
    private NavigationView navigationView;
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference userDatabaseRef;

    StoreDialogFragment storeDialogFragment;
    RedeemDialogFragment redeemDialogFragment;
    FragmentManager fragmentManager;
    DrawerLayout drawer;

    TextView mainViewBalanceTxt;
    TextView getBonusTxt;
    TextView getBonusSignUpTxt;
    TextView login_name;
    TextView login_gmail_id;
    ImageView login_image;
    TextView login_link;
    View navigationHeader;
    ImageView walletArrowImg;
    ProgressBar spinner;

    private UserDetail userDetail;
    private String rupeeSign = "₹";

    private List<AuthUI.IdpConfig> providers = Arrays.asList(
            new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()
    );
    static {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationHeader=navigationView.getHeaderView(0);

        fragmentManager = getFragmentManager();
        storeDialogFragment = new StoreDialogFragment();
        redeemDialogFragment = new RedeemDialogFragment();

        spinner = (ProgressBar)findViewById(R.id.mainProgressBar);
        login_name = navigationHeader.findViewById(R.id.login_name);
        login_gmail_id = navigationHeader.findViewById(R.id.login_gmail_id);
        login_image = navigationHeader.findViewById(R.id.login_image);
        login_link = navigationHeader.findViewById(R.id.login_link);
        walletArrowImg = findViewById(R.id.walletArrowImg);
        walletArrowImg.setVisibility(View.INVISIBLE);
        login_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                launchSignIn();
                spinner.setVisibility(View.VISIBLE);
            }
        });

        spinner.setVisibility(View.VISIBLE);
        mainViewBalanceTxt = findViewById(R.id.mainViewBalanceTxt);
        getBonusTxt = findViewById(R.id.getBonusTxt);
        getBonusSignUpTxt = findViewById(R.id.getBonusSignUpTxt);
        if(userDetail!= null){
            mainViewBalanceTxt.setVisibility(View.VISIBLE);
            getBonusSignUpTxt.setVisibility(View.INVISIBLE);
            getBonusTxt.setVisibility(View.INVISIBLE);
            walletArrowImg.setVisibility(View.VISIBLE);
            mainViewBalanceTxt.setText(rupeeSign+userDetail.getUserAccount().getTotalBalance());
        } else{
            mainViewBalanceTxt.setText(rupeeSign+0);
            mainViewBalanceTxt.setVisibility(View.INVISIBLE);
            getBonusSignUpTxt.setVisibility(View.VISIBLE);
            getBonusTxt.setVisibility(View.VISIBLE);
            walletArrowImg.setVisibility(View.INVISIBLE);
        }
        getBonusSignUpTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchSignIn();
                spinner.setVisibility(View.VISIBLE);
            }
        });
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        if(firebaseUser != null){
            spinner.setVisibility(View.VISIBLE);
            setLoggedInHeader();
            userDatabaseRef = FirebaseDatabase.getInstance().getReference("user/"+firebaseUser.getUid());
            userDatabaseRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    userDetail = dataSnapshot.getValue(UserDetail.class);

                    if(userDetail!= null){
                        mainViewBalanceTxt.setVisibility(View.VISIBLE);
                        getBonusSignUpTxt.setVisibility(View.INVISIBLE);
                        getBonusTxt.setVisibility(View.INVISIBLE);
                        mainViewBalanceTxt.setText(rupeeSign+userDetail.getUserAccount().getTotalBalance());
                        walletArrowImg.setVisibility(View.VISIBLE);
                    } else{
                        mainViewBalanceTxt.setText(rupeeSign+0);
                        mainViewBalanceTxt.setVisibility(View.INVISIBLE);
                        getBonusSignUpTxt.setVisibility(View.VISIBLE);
                        getBonusTxt.setVisibility(View.VISIBLE);
                        walletArrowImg.setVisibility(View.INVISIBLE);
                    }

                    spinner.setVisibility(View.GONE);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                    spinner.setVisibility(View.GONE);
                }

            });

        }else{
            userDetail = null;
            setLoggedOutHeader();
            mainViewBalanceTxt.setText(rupeeSign+0);
            mainViewBalanceTxt.setVisibility(View.INVISIBLE);
            walletArrowImg.setVisibility(View.INVISIBLE);
            getBonusSignUpTxt.setVisibility(View.VISIBLE);
            getBonusTxt.setVisibility(View.VISIBLE);
            spinner.setVisibility(View.GONE);
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            search();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            //removeStoresFragment();
        } else if (id == R.id.nav_redeem) {
            redeem();
        } else if (id == R.id.nav_signout) {
            spinner.setVisibility(View.VISIBLE);
            AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
                public void onComplete(@NonNull Task<Void> task) {
                    userDetail = null;
                    setLoggedOutHeader();
                    mainViewBalanceTxt.setText(rupeeSign+0);
                    mainViewBalanceTxt.setVisibility(View.INVISIBLE);
                    walletArrowImg.setVisibility(View.INVISIBLE);
                    getBonusSignUpTxt.setVisibility(View.VISIBLE);
                    getBonusTxt.setVisibility(View.VISIBLE);
                    spinner.setVisibility(View.GONE);
                }
            });
        } else if (id == R.id.nav_stores) {
            launchStoresActivity();
        } else if (id == R.id.nav_share) {
            share();
        } else if (id == R.id.nav_rete) {
            rateIt();
        }

        drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == ResultCodes.OK) {
                // Successfully signed in
                firebaseAuth = FirebaseAuth.getInstance();
                firebaseUser = firebaseAuth.getCurrentUser();
                // ...
                setLoggedInHeader();

                userDatabaseRef = FirebaseDatabase.getInstance().getReference("user/"+firebaseUser.getUid());
                userDatabaseRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        userDetail = dataSnapshot.getValue(UserDetail.class);
                        if(userDetail!= null){
                            mainViewBalanceTxt.setVisibility(View.VISIBLE);
                            getBonusSignUpTxt.setVisibility(View.INVISIBLE);
                            getBonusTxt.setVisibility(View.INVISIBLE);
                            mainViewBalanceTxt.setText(rupeeSign+userDetail.getUserAccount().getTotalBalance());
                            walletArrowImg.setVisibility(View.VISIBLE);
                        } else{
                            mainViewBalanceTxt.setText(rupeeSign+0);
                            mainViewBalanceTxt.setVisibility(View.INVISIBLE);
                            getBonusSignUpTxt.setVisibility(View.VISIBLE);
                            getBonusTxt.setVisibility(View.VISIBLE);
                            walletArrowImg.setVisibility(View.INVISIBLE);
                            addUser(firebaseUser);
                        }
                        spinner.setVisibility(View.GONE);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        spinner.setVisibility(View.GONE);
                        System.out.println("The read failed: " + databaseError.getCode());
                    }

                });

            } else {
                // Sign in failed, check response for error code
                userDetail = null;
                setLoggedOutHeader();
                mainViewBalanceTxt.setText(rupeeSign+0);
                mainViewBalanceTxt.setVisibility(View.INVISIBLE);
                walletArrowImg.setVisibility(View.INVISIBLE);
                getBonusSignUpTxt.setVisibility(View.VISIBLE);
                getBonusTxt.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.GONE);
            }

        }
    }

    private void setLoggedInHeader(){

        Uri photouri = firebaseUser.getPhotoUrl();
        String name = firebaseUser.getDisplayName();
        String gmail_id = firebaseUser.getEmail();
        Log.d("login", photouri.toString());
        if(!StringUtils.isEmpty(name)){
            login_name.setText(firebaseUser.getDisplayName());
        }else{
            login_name.setText("A MolZol User");
        }
        if(!StringUtils.isEmpty(gmail_id)){
            login_gmail_id.setText(firebaseUser.getEmail());
        }
        if(null!=photouri){
            Context context = login_image.getContext();
            Picasso.with(context).load(photouri).fit().centerInside().into(login_image);
        }
        login_image.setVisibility(View.VISIBLE);
        login_gmail_id.setVisibility(View.VISIBLE);
        login_name.setVisibility(View.VISIBLE);
        login_link.setVisibility(View.GONE);
    }

    private void setLoggedOutHeader(){

        login_image.setVisibility(View.INVISIBLE);
        login_gmail_id.setVisibility(View.INVISIBLE);
        login_name.setVisibility(View.INVISIBLE);
        login_link.setVisibility(View.VISIBLE);

    }

    private void launchSignIn(){
        if(!NetworkUtil.isNetworkAvailable(this)){
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setIsSmartLockEnabled(false, true)
                        .build(),
                RC_SIGN_IN);
    }

    public void addStoreDialogFragment(int storeImg, String storeName, String text1, String text2, String text3){

        Bundle args = new Bundle();
        args.putInt("storeImage", storeImg);
        args.putString("storeName", storeName);
        args.putString("text1", text1);
        args.putString("text2", text2);
        args.putString("text3", text3);
        storeDialogFragment.setArguments(args);
        storeDialogFragment.show(fragmentManager, "Store Dialog Fragment");

    }

    private void share(){
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Bubble Fusion");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, Constants.shareText+ " "+ Constants.playStoreUri);
        startActivity(Intent.createChooser(sharingIntent, "Share with"));
    }

    public void rateIt() {
        final Uri uri = Uri.parse(Constants.playStoreUri);
        final Intent rateAppIntent = new Intent(Intent.ACTION_VIEW, uri);

        if (getPackageManager().queryIntentActivities(rateAppIntent, 0).size() > 0) {
            startActivity(rateAppIntent);
        } else {
    /* handle your error case: the device has no way to handle market urls */
        }
    }

    public void allStores(View v) {

        launchStoresActivity();
    }

    public void redeem(View v) {
        redeem();
    }

    private void redeem(){
        launchWalletActivity();
    }

    private void addUser(FirebaseUser firebaseUser){

        UserDetail userDetail = new UserDetail();

        userDetail.setName(firebaseUser.getDisplayName());
        userDetail.setGmail(firebaseUser.getEmail());
        if(firebaseUser.getPhoneNumber()!= null) {
            userDetail.setPhoneNumber(Integer.getInteger(firebaseUser.getPhoneNumber()));
        }
        userDetail.setUpdatedDate(null);
        userDetail.setCreatedDate(new Date());
        userDetail.setDisableFlag(false);

        UserAccount userAccount = new UserAccount();
        userAccount.setConfirmedBalance(100);
        userAccount.setPendingBalance(0);
        userAccount.setTotalBalance(100);
        userAccount.setUpdatedDate(new Date());
        UserTransaction userTransaction = new UserTransaction();
        userTransaction.setTranAmount(100);
        userTransaction.setTranMode(Constants.TRAN_MODE.EARN);
        userTransaction.setEarnMode(Constants.EARN_MODE.SIGN_UP_BONUS);
        userTransaction.setCreatedDate(new Date());
        userTransaction.setUpdatedDate(new Date());
        userTransaction.setTransactionRef("Sign In Bonus");
        userTransaction.setTranStatus(Constants.TRAN_STATUS.CONFIRM);
        Map<String, UserTransaction> UserTransactionMap = new HashMap<>();
        UserTransactionMap.put("1234_tran", userTransaction);
        userAccount.setUserTransactions(UserTransactionMap);
        userDetail.setUserAccount(userAccount);

        /*
        TODO - No Gift Vouchers on sign up keeping it here for refreneces
        UserGiftVoucher giftVoucher = new UserGiftVoucher();
        giftVoucher.setAmount(500);
        giftVoucher.setGiftVoucherName(Constants.GIFT_VOUCHER_NAME.FLIPKART);
        giftVoucher.setVoucherRef1("12345678985415662");
        Map<String, UserGiftVoucher> userGiftVoucherMap = new HashMap<>();
        userGiftVoucherMap.put("1234_tran", giftVoucher);
        userAccount.setGiftVouchers(userGiftVoucherMap);*/

        userDatabaseRef = FirebaseDatabase.getInstance().getReference("user/"+firebaseUser.getUid());

        userDatabaseRef.setValue(userDetail);
    }

    private void launchWalletActivity(){
        if(null!=userDetail){
            Intent intent = new Intent(this, WalletActivity.class);
            intent.putExtra("userDetail",userDetail);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Please Log In To Continue", Toast.LENGTH_LONG).show();
        }

    }

    private void launchStoresActivity(){
        Intent intent = new Intent(this, StoresActivity.class);
        startActivity(intent);
    }

    private void search() {
        Intent intent = new Intent(this, SearchResultActivity.class);
        startActivity(intent);
    }

    public void goToStore(View v){

        int id = v.getId();

        if(id == R.id.store_flipkart){
            addStoreDialogFragment(R.drawable.flipkart1, "Flipkart", "#UpTo5%CashBack", "abcd2", "abcd3");
        }else if(id == R.id.store_amazon){
            addStoreDialogFragment(R.drawable.amazon, "Amazon", "#UpTo5%CashBack", "abcd2", "abcd3");
        }else if(id == R.id.store_snapdeal){
            addStoreDialogFragment(R.drawable.snapdeal, "Snapdeal", "#ComingSoon", "abcd2", "abcd3");
        }else if(id == R.id.store_infibeam){
            addStoreDialogFragment(R.drawable.infibeam2, "Infibeam", "#ComingSoon", "abcd2", "abcd3");
        }else if(id == R.id.store_ebay){
            addStoreDialogFragment(R.drawable.ebay2, "Ebay", "#ComingSoon", "abcd2", "abcd3");
        }else if(id == R.id.store_jabong){
            addStoreDialogFragment(R.drawable.jabong1, "Jabong", "#ComingSoon", "abcd2", "abcd3");
        }else if(id == R.id.store_myntra){
            addStoreDialogFragment(R.drawable.myntra1, "Myntra", "#ComingSoon", "abcd2", "abcd3");
        }else if(id == R.id.store_shopclues){
            addStoreDialogFragment(R.drawable.shopclues, "Shopclues", "#ComingSoon", "abcd2", "abcd3");
        }else if(id == R.id.store_paytm){
            addStoreDialogFragment(R.drawable.paytm, "Paytm", "#ComingSoon", "abcd2", "abcd3");
        }else if(id == R.id.store_croma){
            addStoreDialogFragment(R.drawable.croma, "Croma", "#ComingSoon", "abcd2", "abcd3");
        }else if(id == R.id.store_indiatimes){
            addStoreDialogFragment(R.drawable.indtimesshopping, "Indiatimes", "#ComingSoon", "abcd2", "abcd3");
        }else if(id == R.id.store_trending){
            addStoreDialogFragment(R.drawable.trendin, "Trending", "#ComingSoon", "abcd2", "abcd3");
        }else if(id == R.id.store_shop19){
            addStoreDialogFragment(R.drawable.shop19, "Shop19", "#ComingSoon", "abcd2", "abcd3");
        }else if(id == R.id.store_homeshop){
            addStoreDialogFragment(R.drawable.homeshop181, "Homeshop", "#ComingSoon", "abcd2", "abcd3");
        }else if(id == R.id.store_cilory){
            addStoreDialogFragment(R.drawable.cilory, "Cilory", "#ComingSoon", "abcd2", "abcd3");
        }else{
            //TODO
        }

    }

}