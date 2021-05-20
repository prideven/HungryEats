package com.prideven.android.hungryeats

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.gms.common.SignInButton
import com.google.firebase.auth.FirebaseAuth
import com.prideven.android.hungryeats.databinding.LoginBinding

class LoginAndRegisterActivity : AppCompatActivity() {
    var loginEmail: EditText? = null
    var loginPassword: EditText? = null
    private val signInButton: SignInButton? = null
    var firebaseAuth: FirebaseAuth? = null
    private var lbinding: LoginBinding? = null
    private var auth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        lbinding = DataBindingUtil.setContentView(this, R.layout.login)
        firebaseAuth = FirebaseAuth.getInstance()
        if (firebaseAuth!!.currentUser != null) {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
            return
        }
        lbinding.loginButton.setOnClickListener {
            val email = lbinding.email.text.toString()
            val password = lbinding.password.text.toString()
            auth!!.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            applicationContext,
                            "Successfully Logged In",
                            Toast.LENGTH_LONG
                        ).show()
                        startActivity(
                            Intent(
                                applicationContext,
                                MainActivity::class.java
                            )
                        )
                        finish()
                    } else {
                        Toast.makeText(
                            baseContext,
                            "E-mail or password is wrong",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
        lbinding.registerButton.setOnClickListener(View.OnClickListener {
            val email = lbinding.email.text.toString()
            val password = lbinding.password.text.toString()
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(
                    applicationContext,
                    "Please fill in the required fields",
                    Toast.LENGTH_SHORT
                ).show()
                return@OnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(
                    applicationContext,
                    "Please fill in the required fields",
                    Toast.LENGTH_SHORT
                ).show()
            }
            if (password.length < 6) {
                Toast.makeText(
                    applicationContext,
                    "Password must be at least 6 characters",
                    Toast.LENGTH_SHORT
                ).show()
            }
            firebaseAuth!!.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        startActivity(
                            Intent(
                                applicationContext,
                                LoginAndRegisterActivity::class.java
                            )
                        )
                        finish()
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "E-mail or password is wrong",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        })
    } //    auth.signInWithEmailAndPassword (loginEmail, loginPassword).addOnCompleteListener(this,

    //    OnCompleteListener { task ->
    //        if(task.isSuccessful) {
    //            Toast.makeText(this, "Successfully Logged In", Toast.LENGTH_LONG).show()
    //            val intent = Intent(this, MainActivity::class.java)
    //            startActivity(intent)
    //            finish()
    //        }else {
    //            Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show()
    //        }
    //    })
    //    @Override
    //    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    //        super.onActivityResult(requestCode, resultCode, data);
    //    }
    companion object {
        private const val RC_SIGN_IN = 9001
    }
}