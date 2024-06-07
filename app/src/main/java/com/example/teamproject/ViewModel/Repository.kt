package com.example.teamproject.ViewModel

import com.example.teamproject.Item.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class Repository(private val table: DatabaseReference) {

    suspend fun InsertUser(user: User){
        table.child(user.id).setValue(user).await()  //key값은 학번으로 구분
    }

    fun UpdateUser(user:User){
        table.child(user.id).setValue(user)
    }

    fun DeleteUser(user:User){
        table.child(user.id).removeValue()
    }

    fun getUsers(id:String, passwd:String) : Flow<List<User>> = callbackFlow{
        val listener = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {    //변화가 감지될 때마다 여기로 데이터를 전달함
                val userList = mutableListOf<User>()
                for(itemSnapShot in snapshot.children){
                    val user = itemSnapShot.getValue(User::class.java)
                    user?.let{
                        if(user.id == id && user.passwd == passwd) {
                            userList.add(it)
                        }
                    }
                }

                trySend(userList)
            }

            override fun onCancelled(error: DatabaseError) {   //실패했을 때 정보를 넘김
                TODO("Not yet implemented")
            }
        }

        table.addValueEventListener(listener)

        awaitClose{
            table.removeEventListener(listener)
        }
    }


    fun getUserId(id:String) : Flow<List<User>> = callbackFlow{
        val listener = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {    //변화가 감지될 때마다 여기로 데이터를 전달함
                val userList = mutableListOf<User>()
                for(itemSnapShot in snapshot.children){
                    val user = itemSnapShot.getValue(User::class.java)
                    user?.let{
                        if(user.id == id) {
                            userList.add(it)
                        }
                    }
                }

                trySend(userList)
            }

            override fun onCancelled(error: DatabaseError) {   //실패했을 때 정보를 넘김
                TODO("Not yet implemented")
            }
        }

        table.addValueEventListener(listener)

        awaitClose{
            table.removeEventListener(listener)
        }
    }
    fun getAll(): Flow<List<User>> = callbackFlow {
        val listener = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {    //변화가 감지될 때마다 여기로 데이터를 전달함
                val userList = mutableListOf<User>()
                for(itemSnapShot in snapshot.children){
                    val user = itemSnapShot.getValue(User::class.java)
                    user?.let{
                        userList.add(it)
                    }
                }

                trySend(userList)
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        table.addValueEventListener(listener)
        awaitClose { table.removeEventListener(listener) }
    }
}