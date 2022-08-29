package com.grupo6.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.grupo6.myapplication.databinding.ActivityLoginBinding
import java.util.regex.Pattern
public  var usuarioLogeado = ""
class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var manejadorArchivo: FileHandler
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        manejadorArchivo = SharedPreferencesManager(this)



        // Initialize Firebase Auth
        auth = Firebase.auth

        // Aquí empiezo con el binging
        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        LeerDatosDePreferencias()

        //Cambio de pantalla a la pantalla de registro
        binding.btnRegistrarse.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        //CAmbio a la pantalla principal
        binding.btnIniciarSesion.setOnClickListener{
            val email =binding.etUsername.text.toString()
            val clave = binding.etContra.text.toString()
            //Validaciones de datos requeridos y formatos
            if(!ValidarDatosRequeridos())
                return@setOnClickListener
            //Guardar datos en preferencias.
            GuardarDatosEnPreferencias()
            AutenticarUsuario(email, clave)

        }
    }


    private fun AutenticarUsuario(email:String, password:String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    //Si pasa validación de datos requeridos, ir a pantalla principal
                    val intent = Intent(this, Inicio::class.java)
                    usuarioLogeado = auth.currentUser!!.email.toString().substringBefore("@")
                    intent.putExtra(EXTRA_LOGIN, auth.currentUser!!.email)
                    startActivity(intent)
                    //finish()
                } else {
                    Log.w(EXTRA_LOGIN, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, task.exception!!.message,
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun ValidarDatosRequeridos():Boolean {
        val email = binding.etUsername.text.toString()
        val clave = binding.etContra.text.toString()
        if (email.isEmpty()) {
            binding.etUsername.setError("El email es obligatorio")
            binding.etUsername.requestFocus()
            return false
        } else {
            val pattern: Pattern = Patterns.EMAIL_ADDRESS
            var esValido = pattern.matcher(email).matches()
            if (!esValido) {
                binding.etUsername.setError("El email no es correcto")
                binding.etUsername.requestFocus()
                return false
            }

        }
        if (clave.isEmpty()) {
            binding.etContra.setError("La clave es obligatoria")
            binding.etContra.requestFocus()
            return false

        }
        if (clave.length <= 7) {
            binding.etContra.setError("La clave debe tener al menos 8 caracteres")
            binding.etContra.requestFocus()
            return false
        }
        return true
    }

    private fun GuardarDatosEnPreferencias() {
        val email = binding.etUsername.text.toString()
        val clave = binding.etContra.text.toString()
        val listadoAGrabar: Pair<String, String>
        if(binding.checkBoxRecordarme.isChecked){
            listadoAGrabar = email to clave
        }
        else{
            listadoAGrabar ="" to ""
        }
        manejadorArchivo.SaveInformation(listadoAGrabar)
    }

    private fun LeerDatosDePreferencias(){
        val listadoLeido = manejadorArchivo.ReadInformation()
        if(listadoLeido.first != null){
            binding.checkBoxRecordarme.isChecked = true
        }
        binding.etUsername.setText ( listadoLeido.first )
        binding.etContra.setText ( listadoLeido.second )
    }
}