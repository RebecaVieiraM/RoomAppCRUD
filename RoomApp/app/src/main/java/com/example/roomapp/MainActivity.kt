package com.example.roomapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Query
import androidx.room.util.query
import com.example.roomapp.model.UsuarioModel
import com.example.roomapp.repository.UsuarioDataBase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //Instancia de Room
        val usuarioDatabase = UsuarioDataBase.getDataBase(this).usuarioDAO()//Aqui estamos instanciando a classe do Room, ao mesmo tempo que estamos retornando a classe de usuarioDao

        //Insert, observe como fazemos o insert, passando uma instância de UsuarioModel, com os atributos alterados:
        val retornoInsert = usuarioDatabase.insertUser(UsuarioModel().apply {
            //this.id_usuario = 1
            this.nome = "Rebeca Vieira Maia"
            this.idade = 31
        })

        usuarioDatabase.insertUser(UsuarioModel().apply {
            //this.id_usuario = 2
            this.nome = "Rafael"
            this.idade = 67
        })
        usuarioDatabase.insertUser(UsuarioModel().apply {
            //this.id_usuario = 3
            this.nome = "Jamile Alves"
            this.idade = 12
        })
        usuarioDatabase.updateUser(UsuarioModel().apply {
            this.id_usuario = 4
            this.nome = "Rafael Oliveira"
            this.idade = 64
        })
        usuarioDatabase.updateUser(UsuarioModel().apply {
            this.id_usuario = 7
            this.nome = "Paola"
            this.idade = 27
        })
        usuarioDatabase.deleteUser(UsuarioModel().apply {
            this.id_usuario = 3
        })
        usuarioDatabase.deleteUser(UsuarioModel().apply {
            this.id_usuario = 2
        })

        val retornoSelectMultiplo = usuarioDatabase.getAll()//Retorna todos os registros

        for(item in retornoSelectMultiplo){
            Log.d("Retorno Múltiplo", "id_usuario: ${item.id_usuario}, nome: ${item.nome}, idade: ${item.idade}")
        }
    }
}
// 1- copiar as dependencias do room e colar no gradle script dependencies:
//    val room_version = "2.5.0"
//
//    implementation("androidx.room:room-ktx:$room_version")
//    kapt("androidx.room:room-compiler:$room_version")

//    implementation("android.activity:activity:1.8.1")
//
// 2- com.example.roomapp/ new/kotlin class/file nome: UsuarioModel(instrucoes la)
//
//12- copiar codigos a partir dos imports e colar nessa activity