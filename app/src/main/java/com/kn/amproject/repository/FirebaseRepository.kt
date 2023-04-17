package com.kn.amproject.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.kn.amproject.data.Tool
import com.kn.amproject.data.User

class FirebaseRepository {
    private val REPO_DEBUG = "REPO_DEBUG"

    private val storage = FirebaseStorage.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val cloud = FirebaseFirestore.getInstance()

    //upload user photo to storage
    fun uploadUserPhoto(bytes: ByteArray) {
        storage.getReference("users")
            //find or create jpg
            .child("${auth.currentUser!!.uid}.jpg")
            //put or post photo
            .putBytes(bytes)
            .addOnCompleteListener {
                Log.d(REPO_DEBUG, "COMPLETE UPLOAD PHOTO")
            }
            .addOnSuccessListener {
                getPhotoDownloadUrl(it.storage)
            }
            .addOnFailureListener {
                Log.d(REPO_DEBUG, it.message.toString())
            }
    }

    //get from storage an url to photo in order to update it in a cloud with users data
    private fun getPhotoDownloadUrl(storage: StorageReference) {
        storage.downloadUrl
            .addOnSuccessListener {
                updateUserPhoto(it.toString())
            }
            .addOnFailureListener {
                Log.d(REPO_DEBUG, it.message.toString())
            }
    }

    //update url to photo in users cloud
    private fun updateUserPhoto(url: String?) {
        cloud.collection("users")
            .document(auth.currentUser!!.uid)
            .update("image", url)
            .addOnSuccessListener {
                Log.d(REPO_DEBUG, "UPDATE USER PHOTO!")
            }
            .addOnFailureListener {
                Log.d(REPO_DEBUG, it.message.toString())
            }
    }

    fun getUserData(): LiveData<User> {
        val cloudResult = MutableLiveData<User>()
        //currently logged in user
        val uid = auth.currentUser?.uid
        //get more info about the current user
        cloud.collection("users")
            .document(uid!!)
            .get()
            .addOnSuccessListener {
                //mapping to our user object
                val user = it.toObject(User::class.java)
                //setting our live data initiated in a higher scope
                cloudResult.postValue(user)
            }
            .addOnFailureListener {
                Log.d(REPO_DEBUG, it.message.toString())
            }

        return cloudResult
    }

    fun getTools(): LiveData<List<Tool>> {
        val cloudResult = MutableLiveData<List<Tool>>()

        cloud.collection("tools")
            .get()
            .addOnSuccessListener {
                val user = it.toObjects(Tool::class.java)
                cloudResult.postValue(user)
            }
            .addOnFailureListener {
                Log.d(REPO_DEBUG, it.message.toString())
            }
        return cloudResult
    }

    fun getFavTools(list: List<String>?): LiveData<List<Tool>> {
        val cloudResult = MutableLiveData<List<Tool>>()

        if (!list.isNullOrEmpty())
            cloud.collection("tools")
                .whereIn("id", list)
                .get()
                .addOnSuccessListener {
                    val resultList = it.toObjects(Tool::class.java)
                    cloudResult.postValue(resultList)
                }
                .addOnFailureListener {
                    Log.d(REPO_DEBUG, it.message.toString())
                }
        return cloudResult
    }

    fun addFavTool(tool: Tool, context: Context, msg: String) {
        cloud.collection("users")
            .document(auth.currentUser?.uid!!)
            .update("favTools", FieldValue.arrayUnion(tool.id))
            .addOnSuccessListener {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Log.d(REPO_DEBUG, it.message.toString())
            }
    }

    fun removeFavTool(tool: Tool, context: Context, msg: String) {
        cloud.collection("users")
            .document(auth.currentUser?.uid!!)
            .update("favTools", FieldValue.arrayRemove(tool.id))
            .addOnSuccessListener {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Log.d(REPO_DEBUG, it.message.toString())
            }
    }

    fun createNewUser(user: User) {
        cloud.collection("users")
            .document(user.uid!!)
            .set(user)
    }

    fun editProfileData(map: Map<String, String>) {
        cloud.collection("users")
            .document(auth.currentUser!!.uid)
            .update(map)
            .addOnSuccessListener {
                Log.d(REPO_DEBUG, "Zaktualizowano dane!")
            }
            .addOnFailureListener {
                Log.d(REPO_DEBUG, it.message.toString())
            }
    }
}