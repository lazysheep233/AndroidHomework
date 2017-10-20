package es.source.code.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import es.source.code.model.User;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginOrRegister extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_register);
        // Set up the login form.
        SharedPreferences preferences=getSharedPreferences("userrecord", Context.MODE_PRIVATE);
        String name=preferences.getString("userName", "defaultname");


        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);


        populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        Button buttonlogin = (Button)findViewById(R.id.button2);
        Button buttonjoin = (Button)findViewById(R.id.button);
        if (name!=null){
            buttonjoin.setVisibility(View.GONE);
            mEmailSignInButton.setText(name);
            Toast.makeText(LoginOrRegister.this, "欢迎回来："+name, Toast.LENGTH_SHORT).show();
            mEmailView.setText(name);
        }
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        //// TODO: 2017/9/27 返回button监听

        final ProgressBar loginProgress = (ProgressBar)findViewById(R.id.progressBar);
        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentnew = new Intent();
                intentnew.setClass(LoginOrRegister.this, MainScreen2.class);
                intentnew.putExtra("from", "Return");
                startActivity(intentnew);


            }
        });

        //TODO 注册按钮监听

        //final ProgressBar loginProgress = (ProgressBar)findViewById(R.id.progressBar);
        buttonjoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                attemptjoin();


            }
        });
    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */

    public  void attemptjoin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.

        //定义String用来接收用户名和密码
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;
        // TODO: 2017/9/27 自动生成代码判断输入在这里 需要修改为正则表达式判断
        // Check for a valid password, if the user entered one.
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
        if (TextUtils.isEmpty(email) ) {
            mEmailView.setError("不能为空");
            focusView = mEmailView;
            cancel = true;
        }

        if (TextUtils.isEmpty(password) ) {
            mPasswordView.setError("不能为空");
            focusView = mPasswordView;
            cancel = true;
        }

        if (!TextUtils.isEmpty(email)&&!email.matches(regex)){
            mEmailView.setError("6-16位字母和数字组合");
            focusView = mEmailView;
            cancel = true;
        }

        if (!TextUtils.isEmpty(password)&&!password.matches(regex)){
            mPasswordView.setError("6-16字母和数字组合");
            focusView = mPasswordView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            showProgress(true);
            joinToMainScreen(true);
            //mAuthTask = new UserLoginTask(email, password);
            //mAuthTask.execute((Void) null);
        }
    }
    //修改原private为public
    public void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.

        //定义String用来接收用户名和密码
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;
        // TODO: 2017/9/27 自动生成代码判断输入在这里 需要修改为正则表达式判断
        // Check for a valid password, if the user entered one.
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
        if (TextUtils.isEmpty(email) ) {
            mEmailView.setError("不能为空");
            focusView = mEmailView;
            cancel = true;
        }

        if (TextUtils.isEmpty(password) ) {
            mPasswordView.setError("不能为空");
            focusView = mPasswordView;
            cancel = true;
        }

        if (!TextUtils.isEmpty(email)&&!email.matches(regex)){
            mEmailView.setError("6-16位字母和数字组合");
            focusView = mEmailView;
            cancel = true;
        }

        if (!TextUtils.isEmpty(password)&&!password.matches(regex)){
            mPasswordView.setError("6-16字母和数字组合");
            focusView = mPasswordView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            //showProgress(true);
            loginToMainScreen(true);
            //mAuthTask = new UserLoginTask(email, password);
            //mAuthTask.execute((Void) null);
        }

        /*
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
        }
        */
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)

    private  void loginToMainscreen (final  boolean state){
        Intent intentnew = new Intent();
        intentnew.setClass(LoginOrRegister.this, MainScreen2.class);
        //intentnew.putExtra("loginmessage", "LoginSuccess");
        startActivity(intentnew);
    }

    private void joinToMainScreen (final boolean state){
        //final ProgressBar loginProgress = (ProgressBar)findViewById(R.id.progressBar);

        //loginProgress.setVisibility(View.VISIBLE);

        //attemptLogin();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //loginProgress.setVisibility(View.INVISIBLE);
                //登录成功后新建对象
                User loginUser = new User();
                loginUser.setName(mEmailView.getText().toString());
                loginUser.setPassword(mPasswordView.getText().toString());
                loginUser.setOldUser(false);

                Intent intentnew = new Intent(LoginOrRegister.this,MainScreen2.class);
                Bundle bundlenew = new Bundle();
                bundlenew.putSerializable("usr",loginUser);
                bundlenew.putString("from","RegisterSuccess");
                intentnew.putExtras(bundlenew);
                startActivity(intentnew);
            }
        },2000);


    }

    private void loginToMainScreen (final boolean state){
        //final ProgressBar loginProgress = (ProgressBar)findViewById(R.id.progressBar);

        //loginProgress.setVisibility(View.VISIBLE);
        User newUser = new User();
                newUser.setName(mEmailView.getText().toString());
                newUser.setPassword(mPasswordView.getText().toString());
                newUser.setOldUser(true);

        SharedPreferences sharedPreferences = getSharedPreferences("userrecord", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器

        editor.putString("userName", mEmailView.getText().toString());

        editor.commit();//提交修改


                newUser.setLoginState(true);
                //传递类的同时传递字符串
                Intent intentnew = new Intent(LoginOrRegister.this,MainScreen2.class);
                Bundle bundlenew = new Bundle();
                bundlenew.putSerializable("usr",newUser);
                bundlenew.putString("from","LoginSuccess");
                intentnew.putExtras(bundlenew);
                //intentnew.putExtra("from", "LoginSuccess");
                //intentnew.putExtra("usr",newUser);
                //mProgressView.setVisibility(View.GONE);
                //mLoginFormView.setVisibility(View.GONE);
                startActivity(intentnew);
                finish();
        //attemptLogin();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //loginProgress.setVisibility(View.INVISIBLE);
//                //登录成功后新建对象
//                User newUser = new User();
//                newUser.setName(mEmailView.getText().toString());
//                newUser.setPassword(mPasswordView.getText().toString());
//                newUser.setOldUser(true);
//                //传递类的同时传递字符串
//                Intent intentnew = new Intent(LoginOrRegister.this,MainScreen2.class);
//                Bundle bundlenew = new Bundle();
//                bundlenew.putSerializable("usr",newUser);
//                bundlenew.putString("from","LoginSuccess");
//                intentnew.putExtras(bundlenew);
//                //intentnew.putExtra("from", "LoginSuccess");
//                //intentnew.putExtra("usr",newUser);
//                mProgressView.setVisibility(View.GONE);
//                mLoginFormView.setVisibility(View.GONE);
//                startActivity(intentnew);
//            }
//        },2000);


    }

    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });



        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);

        }


    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginOrRegister.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }


}

